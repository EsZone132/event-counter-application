package com.example.technicalassignement1;

import android.content.Context;
import android.content.SharedPreferences;
import java.util.ArrayList;
import java.util.Set;

public class SharedPreferenceHelper {

    private SharedPreferences sharedPreferences;

    int counterOne = 0;
    int counterTwo = 0;
    int counterThree = 0;
    int totalCounts = 0;

    public SharedPreferenceHelper(Context context) {
        sharedPreferences = context.getSharedPreferences("Prefs", Context.MODE_PRIVATE);
    }
    public void saveSettings(Settings settings) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("name_one", settings.getNameOne());
        editor.putString("name_two", settings.getNameTwo());
        editor.putString("name_three", settings.getNameThree());
        editor.putInt("max_count", settings.getMaxCount());
        editor.apply();
    }
    public Settings getSettings() {
        Settings settings = new Settings();
        String nameOne = sharedPreferences.getString("name_one", null);
        String nameTwo = sharedPreferences.getString("name_two", null);
        String nameThree = sharedPreferences.getString("name_three", null);
        int maxCount = sharedPreferences.getInt("max_count", 0);

        settings.setNameOne(nameOne);
        settings.setNameTwo(nameTwo);
        settings.setNameThree(nameThree);
        settings.setMaxCount(maxCount);

        return settings;
    }
    public int getCounterOne() {
        counterOne = sharedPreferences.getInt("counter_one", 0);
        return counterOne;
    }
    public int getCounterTwo() {
        counterTwo = sharedPreferences.getInt("counter_two", 0);
        return counterTwo;
    }
    public int getCounterThree() {
        counterThree = sharedPreferences.getInt("counter_three", 0);
        return counterThree;
    }
    public int getTotalCounts() {
        totalCounts = getCounterOne() + getCounterTwo() + getCounterThree();
        return totalCounts;
    }
    public void saveCounterOne(int counterOne) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("counter_one", counterOne);
        editor.apply();
    }
    public void saveCounterTwo(int counterTwo) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("counter_two", counterTwo);
        editor.apply();
    }
    public void saveCounterThree(int counterThree) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("counter_three", counterThree);
        editor.apply();
    }
    public void initializeCounter() {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("counter_one", 0);
        editor.putInt("counter_two", 0);
        editor.putInt("counter_three", 0);
        editor.apply();
    }
    public void saveList(ArrayList<String> counterList) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("arraySize", counterList.size());
        for(int i = 0; i < counterList.size(); i++){
            editor.remove("element_"+ i);
            editor.putString("element_" + i, counterList.get(i));
        }
        editor.apply();
    }
    public ArrayList<String> getCounterList(){
        ArrayList<String> counterList = new ArrayList<>();
        int size = sharedPreferences.getInt("arraySize", 0);
        for(int i = 0; i < size; i++){
            counterList.add(sharedPreferences.getString("element_" + i, null));
        }
        return counterList;
    }
    public void clearList(ArrayList<String> counterList){
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.remove("arraySize");
        for(int i = 0; i < counterList.size(); i++){
            editor.remove("element_"+ i);
        }
        editor.apply();
    }
    public void clearAll(){
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();
    }
    public void clearSettings(){
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.remove("name_one");
        editor.remove("name_two");
        editor.remove("name_three");
        editor.remove("max_count");
        editor.apply();
    }
}

