package com.example.technicalassignement1;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class MainActivity extends AppCompatActivity {

    protected Button settingsButton = null;
    protected Button eventAButton = null;
    protected Button eventBButton = null;
    protected Button eventCButton = null;
    protected Button showCountsButton = null;
    protected TextView totalCountsView;
    protected SharedPreferenceHelper sharedPreferenceHelper;
    int counterOne = 0;
    int counterTwo = 0;
    int counterThree = 0;
    int totalCounts = 0;
    public static ArrayList<String> counterList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(TAG, "The onCreate() event");
        setupUI();
        sharedPreferenceHelper = new SharedPreferenceHelper(MainActivity.this);
    }

    @SuppressLint("SetTextI18n")
    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "The onStart() event");

        //Retrieve data from SharedPreference file
        String nameOne = sharedPreferenceHelper.getSettings().getNameOne();
        String nameTwo = sharedPreferenceHelper.getSettings().getNameTwo();
        String nameThree = sharedPreferenceHelper.getSettings().getNameThree();
        int maxCount = sharedPreferenceHelper.getSettings().getMaxCount();
        totalCounts = sharedPreferenceHelper.getTotalCounts();

        //Checks if buttons names are empty
        if (nameOne == null || nameTwo == null || nameThree == null) {
            goToSettingsActivity();
        }
        else {
            //Set names for button with retrieved data
              eventAButton.setText(nameOne);
              eventBButton.setText(nameTwo);
              eventCButton.setText(nameThree);
              totalCountsView.setText("Total Count: " + totalCounts);
        }
    }
    protected void goToSettingsActivity(){
        Intent intent = new Intent(this, SettingsActivity.class);
        startActivity(intent);
    }
    protected void goToDataActivity(){
        Intent intent = new Intent(this, DataActivity.class);
        startActivity(intent);
    }
    protected void setupUI(){
        settingsButton = (Button) findViewById(R.id.settingsButton);
        settingsButton.setOnClickListener(onClickGoSettings);
        eventAButton = (Button) findViewById(R.id.eventAButton);
        eventAButton.setOnClickListener(OnClickEventAButton);
        eventBButton = (Button) findViewById(R.id.eventBButton);
        eventBButton.setOnClickListener(OnClickEventBButton);
        eventCButton = (Button) findViewById(R.id.eventCButton);
        eventCButton.setOnClickListener(OnClickEventCButton);
        showCountsButton = (Button) findViewById(R.id.showCountsButton);
        showCountsButton.setOnClickListener(onClickGoData);
        totalCountsView = (TextView) findViewById(R.id.totalCountsView);
    }
    private Button.OnClickListener onClickGoSettings = new Button.OnClickListener(){ //Set up function for SETTINGS button
        @Override
        public void onClick(View view) {
            goToSettingsActivity();
        }
    };
    private Button.OnClickListener onClickGoData = new Button.OnClickListener(){ //Set up function for SHOW COUNTS button
        @Override
        public void onClick(View view) {
            goToDataActivity();
            sharedPreferenceHelper.saveList(counterList); //Send dynamic array (history list) to SharedPreferences file

        }
    };
    private Button.OnClickListener OnClickEventAButton = new Button.OnClickListener(){
        @Override
        public void onClick(View view) {
            //counterOne increments everytime button is pressed
            counterOne = sharedPreferenceHelper.getCounterOne();
            if (sharedPreferenceHelper.getTotalCounts() < sharedPreferenceHelper.getSettings().getMaxCount()) { //Checks if totalCount exceeds max allowed counts
                counterOne++;
                sharedPreferenceHelper.saveCounterOne(counterOne); //Saves value (number of times pressed) in SharedPreferences file
                updateTotalCounts(); //Called to update total counts TextView everytime button is pressed
                counterList.add(sharedPreferenceHelper.getSettings().getNameOne()); //Name adds to dynamic array everytime button is pressed
            }
            else {
                Toast.makeText(MainActivity.this, "AMOUNT OF COUNTS EXCEEDED", Toast.LENGTH_LONG).show();
            }
        }
    };
    private Button.OnClickListener OnClickEventBButton = new Button.OnClickListener(){
        @Override
        public void onClick(View view) {
            counterTwo = sharedPreferenceHelper.getCounterTwo();
            if (sharedPreferenceHelper.getTotalCounts() < sharedPreferenceHelper.getSettings().getMaxCount()) {
                counterTwo++;
                sharedPreferenceHelper.saveCounterTwo(counterTwo);
                updateTotalCounts();
                counterList.add(sharedPreferenceHelper.getSettings().getNameTwo());
            }
            else{
                Toast.makeText(MainActivity.this, "AMOUNT OF COUNTS EXCEEDED", Toast.LENGTH_LONG).show();
            }
        }
    };
    private Button.OnClickListener OnClickEventCButton = new Button.OnClickListener(){
        @Override
        public void onClick(View view) {
            counterThree = sharedPreferenceHelper.getCounterThree();
            if (sharedPreferenceHelper.getTotalCounts() < sharedPreferenceHelper.getSettings().getMaxCount()) {
                counterThree++;
                sharedPreferenceHelper.saveCounterThree(counterThree);
                updateTotalCounts();
                counterList.add(sharedPreferenceHelper.getSettings().getNameThree());
            }
            else{
                Toast.makeText(MainActivity.this, "AMOUNT OF COUNTS EXCEEDED", Toast.LENGTH_LONG).show();
            }
        }
    };
    @SuppressLint("SetTextI18n")
    public void updateTotalCounts(){
        totalCounts = sharedPreferenceHelper.getTotalCounts();
        totalCountsView.setText("Total Count: " + totalCounts);
    }
}



