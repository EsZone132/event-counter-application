package com.example.technicalassignement1;

import static android.text.TextUtils.isEmpty;
import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;
import static com.example.technicalassignement1.MainActivity.counterList;
import static java.lang.String.valueOf;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
public class SettingsActivity extends AppCompatActivity {
    protected Button saveButton;
    protected EditText nameOneEditText = null;
    protected EditText nameTwoEditText = null;
    protected EditText nameThreeEditText = null;
    protected EditText maxEditText = null;
    protected TextView counterOneName;
    protected TextView counterTwoName;
    protected TextView counterThreeName;
    protected TextView maxCountViews;
    protected Toolbar toolbar;
    protected SharedPreferenceHelper sharedPreferenceHelper;
    String nameOne,nameTwo, nameThree;
    int maxCount = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        Log.d(TAG, "The onCreate() event");
        setupUI();
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Settings Activity");
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true); //Back button
        sharedPreferenceHelper = new SharedPreferenceHelper(SettingsActivity.this);

        if(nameOneEditText == null || nameTwoEditText == null || nameThreeEditText == null || maxEditText == null){ //Checks if fields are empty
            editMode();
        }
        else if (!isEmpty(sharedPreferenceHelper.getSettings().getNameOne())) {
            displayMode();
        }
        else
        {
            saveButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    nameOne = nameOneEditText.getText().toString();
                    nameTwo = nameTwoEditText.getText().toString();
                    nameThree = nameThreeEditText.getText().toString();
                    maxCount = Integer.parseInt(maxEditText.getText().toString());
                    Settings userSettings = new Settings(nameOne, nameTwo, nameThree, maxCount);
                    sharedPreferenceHelper.saveSettings(userSettings);
                    createSettings();

                    if (isEmpty(sharedPreferenceHelper.getSettings().getNameOne()) || isEmpty(sharedPreferenceHelper.getSettings().getNameTwo()) || isEmpty(sharedPreferenceHelper.getSettings().getNameThree()) || isEmpty(String.valueOf(sharedPreferenceHelper.getSettings().getMaxCount()))) {
                        editMode();
                        Toast toast = Toast.makeText(getApplicationContext(), "Empty Field", Toast.LENGTH_SHORT);
                        toast.show();
                    }
                    else if (isCountValid(sharedPreferenceHelper.getSettings().getMaxCount())) {
                        Toast toast = Toast.makeText(getApplicationContext(), "Invalid Maximum Count Value", Toast.LENGTH_SHORT);
                        toast.show();
                    }
                    else{

                        Toast toast = Toast.makeText(getApplicationContext(), "Saved Successfully", Toast.LENGTH_SHORT);
                        toast.show();

                        displayMode(); // Goes to display mode when SAVE button is clicked

                        sharedPreferenceHelper.clearList(sharedPreferenceHelper.getCounterList()); // Empties values inside keys allocated for array in SharedPreferences
                        counterList.clear(); // Empties dynamic array of previous counter names to set up new dynamic array with new counter names for scrollable list
                        sharedPreferenceHelper.initializeCounter(); // set counter to 0 for new counter names
                        //sharedPreferenceHelper.clearAll(); //Uncomment to clear all data in SharedPreferences
                    }
                }
            });
        }
        //Displayed on the activity layout as they were saved in the SharedPreference file
        nameOneEditText.setText(sharedPreferenceHelper.getSettings().getNameOne());
        nameTwoEditText.setText(sharedPreferenceHelper.getSettings().getNameTwo());
        nameThreeEditText.setText(sharedPreferenceHelper.getSettings().getNameThree());
        maxEditText.setText(valueOf(sharedPreferenceHelper.getSettings().getMaxCount()));
    }
    @Override
    protected void onResume(){
        super.onResume();
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                nameOne = nameOneEditText.getText().toString();
                nameTwo = nameTwoEditText.getText().toString();
                nameThree = nameThreeEditText.getText().toString();
                maxCount = Integer.parseInt(maxEditText.getText().toString());
                Settings userSettings = new Settings(nameOne, nameTwo, nameThree, maxCount);
                sharedPreferenceHelper.saveSettings(userSettings);
                createSettings();

                if (isEmpty(sharedPreferenceHelper.getSettings().getNameOne()) || isEmpty(sharedPreferenceHelper.getSettings().getNameTwo()) || isEmpty(sharedPreferenceHelper.getSettings().getNameThree()) || isEmpty(String.valueOf(sharedPreferenceHelper.getSettings().getMaxCount()))) {
                    editMode();
                    Toast toast = Toast.makeText(getApplicationContext(), "Empty Field", Toast.LENGTH_SHORT);
                    toast.show();
                }
                else if (isCountValid(sharedPreferenceHelper.getSettings().getMaxCount())) {
                    Toast toast = Toast.makeText(getApplicationContext(), "Invalid Maximum Count Value", Toast.LENGTH_SHORT);
                    toast.show();
                }
                else{

                    Toast toast = Toast.makeText(getApplicationContext(), "Saved Successfully", Toast.LENGTH_SHORT);
                    toast.show();

                    displayMode(); // Goes to display mode when SAVE button is clicked

                    sharedPreferenceHelper.clearList(sharedPreferenceHelper.getCounterList()); // Empties values inside keys allocated for array in SharedPreferences
                    counterList.clear(); // Empties dynamic array of previous counter names to set up new dynamic array with new counter names for scrollable list
                    sharedPreferenceHelper.initializeCounter(); // set counter to 0 for new counter names
                    //sharedPreferenceHelper.clearAll(); //Uncomment to clear all data in SharedPreferences
                }
            }
        });

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.edit_menu, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem editItem){
        if (editItem.getItemId() == R.id.edit_settings) {
            editMode();
            return true;
        }
        return super.onOptionsItemSelected(editItem);
    }
    protected void setupUI(){
        saveButton = (Button) findViewById(R.id.saveButton);
        nameOneEditText = (EditText) findViewById(R.id.nameOneEditText);
        nameTwoEditText = (EditText) findViewById(R.id.nameTwoEditText);
        nameThreeEditText = (EditText) findViewById(R.id.nameThreeEditText);
        maxEditText = (EditText) findViewById(R.id.maxEditText);
        counterOneName = (TextView) findViewById(R.id.counterOneName);
        counterTwoName = (TextView) findViewById(R.id.counterTwoName);
        counterThreeName = (TextView) findViewById(R.id.counterThreeName);
        maxCountViews = (TextView) findViewById(R.id.maxCountViews);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
    }
    protected void displayMode(){
        saveButton.setVisibility(View.GONE);
        nameOneEditText.setEnabled(false);
        nameTwoEditText.setEnabled(false);
        nameThreeEditText.setEnabled(false);
        maxEditText.setEnabled(false);
    }
    public void editMode(){
        sharedPreferenceHelper.clearSettings();
        saveButton.setVisibility(View.VISIBLE);
        nameOneEditText.setEnabled(true);
        nameTwoEditText.setEnabled(true);
        nameThreeEditText.setEnabled(true);
        maxEditText.setEnabled(true);
    }
    public void createSettings(){
        //Creates Settings object with user input and stores it in SharedPreferences
        nameOne = nameOneEditText.getText().toString();
        nameTwo = nameTwoEditText.getText().toString();
        nameThree = nameThreeEditText.getText().toString();
        maxCount = Integer.parseInt(maxEditText.getText().toString());
        Settings userSettings = new Settings(nameOne, nameTwo, nameThree, maxCount);
        sharedPreferenceHelper.saveSettings(userSettings);
    }
    public boolean isCountValid(int maxCount){
        return maxCount < 5 || maxCount > 200;
    }
}