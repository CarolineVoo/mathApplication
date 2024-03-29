package com.example.mathapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.preference.Preference;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onButtonClickStart(View view){
        Intent intent = new Intent(this, PlayActivity.class);
        startActivity(intent);
    }

    public void onButtonClickStatistic(View view){
        Intent intent = new Intent(this, StatisticActivity.class);
        startActivity(intent);
    }

    public void onButtonClickPreferences(View view){
        Intent intent = new Intent(this, PreferencesActivity.class);
        startActivity(intent);
    }
}
