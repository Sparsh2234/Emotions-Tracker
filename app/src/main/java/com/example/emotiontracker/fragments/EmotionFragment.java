package com.example.emotiontracker.fragments;

import android.content.DialogInterface;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.emotiontracker.R;
import com.example.emotiontracker.db_connect.DatabaseConnector;
import com.example.emotiontracker.models.Emotions;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link EmotionFragment #newInstance} factory method to
 * create an instance of this fragment.
 */
public class EmotionFragment extends Fragment {


    RelativeLayout happyButton, anxiousButton, frustratedButton, overwhelmedButton, angryButton;
    int emotionLevel;
    Emotions emotions = new Emotions();

    public EmotionFragment() {
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
        View view = inflater.inflate(R.layout.fragment_emotion, container, false);

        configureEmotionButtons(view);

        return view;

    }

    private void configureEmotionButtons(View view) {
        happyButton = view.findViewById(R.id.happyEmotionButton);
        anxiousButton = view.findViewById(R.id.anxiousEmotionButton);
        frustratedButton = view.findViewById(R.id.frustratedEmotionButton);
        overwhelmedButton = view.findViewById(R.id.overwhelmedEmotionButton);
        angryButton = view.findViewById(R.id.angryEmotionButton);


        happyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                emotionLevel = emotions.getEmotionValue(Emotions.Emotion.Happy);
                showHappyRecordedDialogue();
            }
        });

        anxiousButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                emotionLevel = emotions.getEmotionValue(Emotions.Emotion.Anxious);
                showUpsetRecordedDialogue();
            }
        });

        frustratedButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                emotionLevel = emotions.getEmotionValue(Emotions.Emotion.Frustrated);
                showUpsetRecordedDialogue();
            }
        });

        overwhelmedButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                emotionLevel = emotions.getEmotionValue(Emotions.Emotion.Overwhelmed);
                showUpsetRecordedDialogue();
            }
        });

        angryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                emotionLevel = emotions.getEmotionValue(Emotions.Emotion.Angry);
                showUpsetRecordedDialogue();
            }
        });
    }


    private void showUpsetRecordedDialogue() {
        new MaterialAlertDialogBuilder(getContext())
                .setTitle("Recorded!")
                .setMessage("We hope you feel better soon. Your entry has been recorded and will be reflected in the Tracker")
                .setPositiveButton("Continue", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        DatabaseConnector databaseConnector = new DatabaseConnector();
                        databaseConnector.executeDatabaseConnection(getActivity(), "Post Emotion" , getString(R.string.username_sample), emotionLevel);

                    }
                }).setNegativeButton("Cancel Entry", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        }).show();
    }

    private void showHappyRecordedDialogue() {
        new MaterialAlertDialogBuilder(getContext())
                .setTitle("Recorded!")
                .setMessage("We're glad you're doing well. Your entry has been recorded and will be reflected in the Tracker")
                .setPositiveButton("Continue", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        DatabaseConnector databaseConnector = new DatabaseConnector();
                        databaseConnector.executeDatabaseConnection(getActivity(), "Post Emotion" ,"placeHolder", emotionLevel);

                    }
                }).setNegativeButton("Cancel Entry", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        }).show();
    }

}