package com.example.technicalassignement1;

import static com.example.technicalassignement1.MainActivity.counterList;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.Objects;


public class DataActivity extends AppCompatActivity {
    protected TextView counterOneDisplay;
    protected TextView counterTwoDisplay;
    protected TextView counterThreeDisplay;
    protected TextView totalEvents;
    protected Toolbar toolbar;
    protected SharedPreferenceHelper sharedPreferenceHelper;
    private boolean eventMode = true;
    protected ArrayList<String> changedCounterList = new ArrayList<>();
    int totalOne = 0;
    int totalTwo = 0;
    int totalThree = 0;
    int totalCounts = 0;
    String nameOne = null;
    String nameTwo = null;
    String nameThree = null;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data);
        setupUI();
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Data Activity");
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        sharedPreferenceHelper = new SharedPreferenceHelper(DataActivity.this);

        nameOne = sharedPreferenceHelper.getSettings().getNameOne();
        nameTwo = sharedPreferenceHelper.getSettings().getNameTwo();
        nameThree = sharedPreferenceHelper.getSettings().getNameThree();
        totalCounts = sharedPreferenceHelper.getTotalCounts();
        totalOne = sharedPreferenceHelper.getCounterOne();
        totalTwo = sharedPreferenceHelper.getCounterTwo();
        totalThree = sharedPreferenceHelper.getCounterThree();
        ArrayList<String> counterList = new ArrayList<>(sharedPreferenceHelper.getCounterList());

        counterOneDisplay.setText(nameOne + ": " + totalOne + " events");
        counterTwoDisplay.setText(nameTwo + ": " + totalTwo + " events");
        counterThreeDisplay.setText(nameThree + ": " + totalThree + " events");
        totalEvents.setText("Total events: " + totalCounts);

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new CustomAdapter(getApplicationContext(), counterList));

        String counterOne = "1";
        String counterTwo = "2";
        String counterThree = "3";
        for (int i = 0; i < counterList.size(); i++){
            if(Objects.equals(counterList.get(i), sharedPreferenceHelper.getSettings().getNameOne())){
                changedCounterList.add(counterOne);
            }
            else if(Objects.equals(counterList.get(i), sharedPreferenceHelper.getSettings().getNameTwo())){
                changedCounterList.add(counterTwo);
            }
            else if (Objects.equals(counterList.get(i), sharedPreferenceHelper.getSettings().getNameThree())){
                changedCounterList.add(counterThree);
            }
        }
    }
    @Override
    public void onResume(){
        super.onResume();
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem editItem){
        if (editItem.getItemId() == R.id.toggleNames) {
            eventMode = !eventMode;
            changeList(eventMode);
            return true;
        }
        return super.onOptionsItemSelected(editItem);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.toggle_menu, menu);
        return true;
    }
    public void setupUI(){
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        counterOneDisplay = (TextView) findViewById(R.id.counterOneDisplay);
        counterTwoDisplay = (TextView) findViewById(R.id.counterTwoDisplay);
        counterThreeDisplay = (TextView) findViewById(R.id.counterThreeDisplay);
        totalEvents = (TextView) findViewById(R.id.totalEvents);
    }
    @SuppressLint("SetTextI18n")
    public void changeList(boolean eventMode){
        if(eventMode){
            RecyclerView recyclerView = findViewById(R.id.recyclerView);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
            recyclerView.setAdapter(new CustomAdapter(getApplicationContext(), counterList));
            counterOneDisplay.setText(nameOne + ": " + totalOne + " events");
            counterTwoDisplay.setText(nameTwo + ": " + totalTwo + " events");
            counterThreeDisplay.setText(nameThree + ": " + totalThree + " events");
        }
        else{
            RecyclerView recyclerView = findViewById(R.id.recyclerView);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
            recyclerView.setAdapter(new CustomAdapter(getApplicationContext(), changedCounterList));
            counterOneDisplay.setText("Counter 1: " + totalOne + " event");
            counterTwoDisplay.setText("Counter 2: " + totalTwo + " event");
            counterThreeDisplay.setText("Counter 3: " + totalThree + " event");
        }
    }
}

