package com.example.emotiontracker.fragments;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.emotiontracker.R;
import com.example.emotiontracker.db_connect.DatabaseConnector;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import lecho.lib.hellocharts.model.Axis;
import lecho.lib.hellocharts.model.AxisValue;
import lecho.lib.hellocharts.model.Line;
import lecho.lib.hellocharts.model.LineChartData;
import lecho.lib.hellocharts.model.PointValue;
import lecho.lib.hellocharts.view.LineChartView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link TrackerFragment #newInstance} factory method to
 * create an instance of this fragment.
 */
public class TrackerFragment extends Fragment {

    DatabaseConnector databaseConnector = new DatabaseConnector();
    LineChartView lineChartView;
    int [] yAxisData = new int[1000];

    public static List yValueDatabaseList = new ArrayList();

    public TrackerFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_tracker, container, false);

        lineChartView = view.findViewById(R.id.chart);

        setupLineChart();

        return view;
    }

    public void setupLineChart() {


        String [] xAxisData = {"1"};

        databaseConnector.executeDatabaseConnection(getActivity(), "Get Emotion", getString(R.string.username_sample), 0);

        int emotionValueFromDB = databaseConnector.getRetrievedEmotionLevel();

        addToYValueList(emotionValueFromDB);

        for (int i = 0; i < yValueDatabaseList.size(); i++) {
            Log.d("AG", "setupLineChart: " + yValueDatabaseList.get(i));
            yAxisData[i] = (int) yValueDatabaseList.get(i);
        }

        List convertedYAxisPlotValues = new ArrayList();
        List convertedXAxisValues = new ArrayList();


        Line line  = new Line(convertedYAxisPlotValues).setColor(Color.parseColor("#9C27B0"));;

        for(int i = 0; i < xAxisData.length; i++){
            convertedXAxisValues.add(i, new AxisValue(i).setLabel(xAxisData[i]));
        }

        for (int i = 0; i < yAxisData.length; i++){
            convertedYAxisPlotValues.add(new PointValue(i, yAxisData[i]));
        }


        List lines = new ArrayList();
        lines.add(line);


        LineChartData data = new LineChartData();
        data.setLines(lines);



        Axis axis = new Axis();
        axis.setValues(convertedXAxisValues);
        axis.setTextSize(16);
        axis.setTextColor(Color.parseColor("#03A9F4"));
        data.setAxisXBottom(axis);

        Axis yAxis = new Axis();
        yAxis.setName("Emotion Levels");
        yAxis.setTextColor(Color.parseColor("#03A9F4"));
        yAxis.setTextSize(16);
        data.setAxisYLeft(yAxis);

        lineChartView.setLineChartData(data);

    }

    public void addToYValueList(int value) {
        yValueDatabaseList.add(value);
    }

}