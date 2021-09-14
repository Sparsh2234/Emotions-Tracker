package com.example.emotiontracker.db_connect;

import android.app.Activity;
import android.util.Log;
import android.widget.Toast;

import com.example.emotiontracker.MainActivity;
import com.example.emotiontracker.fragments.TrackerFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLEncoder;
import java.time.LocalDateTime;

public class DatabaseConnector {

    private static final String TAG = "DatabaseConnector";

    String result = "";
    int retrievedEmotionLevel = 0;

    public void executeDatabaseConnection(Activity activity, String executeType, String userName, int emotionLevel) {

        String emotionRetrieveURL = "http://192.168.1.116:8888/emotion_retrieve.php";
        String emotionPostURL = "http://192.168.1.116:8888/emotion_post.php";

        new BackgroundWorker() {
            @Override
            public void onPreExecute() {

            }

            @Override
            public void doInBackground() {

                if (executeType.equals("Get Emotion")) {
                    try {
                        URL url = new URL(emotionRetrieveURL);
                        HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
                        httpURLConnection.setRequestMethod("POST");
                        httpURLConnection.setDoOutput(true);
                        httpURLConnection.setDoInput(true);

                        OutputStream outputStream = httpURLConnection.getOutputStream();
                        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream));

                        String getData = URLEncoder.encode("user_name", "UTF-8") + "=" + URLEncoder.encode(userName, "UTF-8");
                        bufferedWriter.write(getData);
                        bufferedWriter.flush();
                        bufferedWriter.close();
                        outputStream.close();

                        InputStream inputStream = httpURLConnection.getInputStream();
                        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "ISO-8859-1"));

                        String line =  "";
                        result = "";

                        while ((line = bufferedReader.readLine()) != null) {
                            result += line;
                        }

                        retrievedEmotionLevel = Integer.parseInt(result);

                        bufferedReader.close();
                        inputStream.close();
                        httpURLConnection.disconnect();


                    } catch (ProtocolException e) {
                        e.printStackTrace();
                    } catch (MalformedURLException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

                else if (executeType.equals("Post Emotion")) {


                    try {
                        URL url = new URL(emotionPostURL);
                        HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
                        httpURLConnection.setRequestMethod("POST");
                        httpURLConnection.setDoOutput(true);
                        httpURLConnection.setDoInput(true);

                        OutputStream outputStream = httpURLConnection.getOutputStream();
                        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream));

                        String postData = URLEncoder.encode("name", "UTF-8") + "=" + URLEncoder.encode(userName, "UTF-8") + "&" +
                                URLEncoder.encode("emotion_level", "UTF-8") + "=" + URLEncoder.encode(String.valueOf(emotionLevel), "UTF-8");
                        bufferedWriter.write(postData);
                        bufferedWriter.flush();
                        bufferedWriter.close();
                        outputStream.close();

                        InputStream inputStream = httpURLConnection.getInputStream();
                        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "iso-8859-1"));

                        result = "";
                        String line = "";

                        while ((line = bufferedReader.readLine()) != null) {
                            result += (line);
                        }

                        bufferedReader.close();
                        inputStream.close();
                        httpURLConnection.disconnect();


                    } catch (ProtocolException e) {
                        e.printStackTrace();
                    } catch (MalformedURLException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }


                }

            }

            @Override
            public void onPostExecute() {

                if (executeType.equals("Get Emotion")) {

                    TrackerFragment trackerFragment = new TrackerFragment();
                    trackerFragment.addToYValueList(retrievedEmotionLevel);

                }


            }
        }.execute();
    }

    public String getResult() {
        return result;
    }

    public int getRetrievedEmotionLevel() {
        return retrievedEmotionLevel;
    }



}
