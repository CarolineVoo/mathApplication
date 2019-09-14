package com.example.mathapplication;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.Preference;
import android.util.Log;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.appcompat.app.AppCompatActivity;

public class PreferencesActivity extends AppCompatActivity {
    RadioGroup radioGroup;
    RadioButton task5;
    RadioButton task10;
    RadioButton task25;
    Integer task;
    SharedPreferences saveData;


    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preferences);

        radioGroup = findViewById(R.id.radioTasks);
        task5 = findViewById(R.id.radio5Tasks);
        task10 = findViewById(R.id.radio10Tasks);
        task25 = findViewById(R.id.radio25Tasks);

        task = 5;

        Log.d("START ", String.valueOf(task));

        saveData = getSharedPreferences("saveData", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = saveData.edit();
        editor.putInt("numTask", task);
        editor.putBoolean("task5", task5.isChecked());
        editor.putBoolean("task10", task10.isChecked());
        editor.putBoolean("task25", task25.isChecked());
        editor.apply();
    }

    public void checkedNumTask(View v){
        boolean checked = ((RadioButton) v).isChecked();

        switch (v.getId()){
            case R.id.radio5Tasks :
                if(checked){
                    task5.setChecked(true);
                    task10.setChecked(false);
                    task25.setChecked(false);
                    task = 5;
                    Log.d("Choose", String.valueOf(task));
                }
                break;
            case R.id.radio10Tasks :
                if(checked){
                    task5.setChecked(false);
                    task10.setChecked(true);
                    task25.setChecked(false);
                    task = 10;
                    Log.d("Choose", String.valueOf(task));
                }
                break;
            case R.id.radio25Tasks :
                if(checked){
                    task5.setChecked(false);
                    task10.setChecked(false);
                    task25.setChecked(true);
                    task = 25;
                    Log.d("Choose", String.valueOf(task));
                }
                break;
            default:
                task=5;
                break;
        }

    }

    @Override
    protected void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putBoolean("task5", task5.isChecked());
        savedInstanceState.putBoolean("task10", task10.isChecked());
        savedInstanceState.putBoolean("task25", task25.isChecked());
        savedInstanceState.putInt("currentTask", task);

    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        task5.setChecked(savedInstanceState.getBoolean("task5", false));
        task10.setChecked(savedInstanceState.getBoolean("task10", false));
        task25.setChecked(savedInstanceState.getBoolean("task25", false));
        task = savedInstanceState.getInt("currentTask");
    }

}