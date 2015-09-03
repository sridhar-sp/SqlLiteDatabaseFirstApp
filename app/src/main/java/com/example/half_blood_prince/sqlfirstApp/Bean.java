package com.example.half_blood_prince.sqlfirstapp;

/**
 * Created by Half-Blood-Prince on 02-09-2015.
 */
public class Bean {
    private int mID;
    private String mName;
    private String mPhone;

    private String mSex;


    Bean(){}

    Bean(String mName,String mPhone,String mSex){
        this.mName = mName;
        this.mPhone = mPhone;
        this.mSex = mSex;
    }

    Bean(int mID,String mName,String mPhone,String mSex){
        this.mID = mID;
        this.mName = mName;
        this.mPhone = mPhone;
        this.mSex = mSex;
    }

    public int getmID(){
        return this.mID;
    }
    public String getmName(){
        return this.mName;
    }
    public String getmPhone(){
        return this.mPhone;
    }

    public String getmSex() {
        return mSex;
    }

    public void setmID(int mID){
        this.mID = mID;
    }

    public void setmName(String mName){
        this.mName = mName;
    }

    public void setmPhone(String mPhone){ this.mPhone = mPhone; }

    public void setmSex(String mSex) {this.mSex = mSex; }


}
