package com.example.emotiontracker.models;

public class Emotions {


    public enum Emotion {
        Happy,
        Anxious,
        Frustrated,
        Overwhelmed,
        Angry
    }

    public Emotions() {}

    public int getEmotionValue(Emotion emotion) {

        switch (emotion) {
            case Happy:
                return 1;
            case Anxious:
                return 2;
            case Frustrated:
                return 3;
            case Overwhelmed:
                return 4;
            case Angry:
                return 5;
        }

        return 0;
    }

}
