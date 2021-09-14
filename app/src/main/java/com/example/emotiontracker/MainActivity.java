package com.example.emotiontracker;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;

import com.example.emotiontracker.fragments.EmotionFragment;
import com.example.emotiontracker.fragments.ProfileFragment;
import com.example.emotiontracker.fragments.TrackerFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {


    BottomNavigationView bottomNavigationView;

    final FragmentManager fm = getSupportFragmentManager();

    final Fragment emotions = new EmotionFragment();
    final Fragment tracker = new TrackerFragment();
    final Fragment profile = new ProfileFragment();
    Fragment active = emotions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNavigationView = findViewById(R.id.bottom_navigation);

        setupBottomNavigation();
    }

    private void setupBottomNavigation() {

        fm.beginTransaction().add(R.id.main_container, profile, "3").hide(profile).commit();
        fm.beginTransaction().add(R.id.main_container, tracker, "2").hide(tracker).commit();
        fm.beginTransaction().add(R.id.main_container, emotions, "1").commit();


        bottomNavigationView.setOnItemSelectedListener(item -> {
            int id = item.getItemId();

            switch(id) {
                case R.id.page_1:
                    getSupportFragmentManager().beginTransaction().hide(active).show(emotions).commit();
                    active = emotions;
                    return true;
                case R.id.page_2:
                    getSupportFragmentManager().beginTransaction().hide(active).show(tracker).commit();
                    active = tracker;
                    return true;
                case R.id.page_3:
                    getSupportFragmentManager().beginTransaction().hide(active).show(profile).commit();
                    active = profile;
                    return true;
            }

            return false;
        });
    }

}