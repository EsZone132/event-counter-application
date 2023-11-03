package com.example.technicalassignement1;

public class Settings extends SettingsActivity{
    private String nameOne;
    private String nameTwo;
    private String nameThree;
    private int maxCount;

    public Settings(String nameOne, String nameTwo, String nameThree, int maxCount) {
        this.nameOne = nameOne;
        this.nameTwo = nameTwo;
        this.nameThree = nameThree;
        this.maxCount = maxCount;
    }

    public Settings(){

    }
    public String getNameOne(){
        return nameOne;
    }
    public void setNameOne(String nameOne){
        this.nameOne = nameOne;
    }
    public String getNameTwo(){
        return nameTwo;
    }
    public void setNameTwo(String nameTwo){
        this.nameTwo = nameTwo;
    }
    public String getNameThree(){
        return nameThree;
    }
    public void setNameThree(String nameThree){
        this.nameThree = nameThree;
    }
    public int getMaxCount(){
        return maxCount;
    }
    public void setMaxCount(int maxCount){
        this.maxCount = maxCount;
    }
}
