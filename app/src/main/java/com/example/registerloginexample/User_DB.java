package com.example.registerloginexample;


import java.util.ArrayList;

// 사용자의 개인정보와 현재 달린 경도와 위도
public class User_DB {
    private String member_id;
    private String member_name;
    private int member_age;
    private int member_height;
    private double member_weight;
    private String member_gender;
    private ArrayList<String> latitude =new ArrayList<String>();
    private ArrayList<String> longitude=new ArrayList<String>();



    public String getMember_id() {
        return member_id;
    }

    public String getMember_name() {
        return member_name;
    }

    public int getMember_age() {
        return member_age;
    }

    public int getMember_height() {
        return member_height;
    }

    public double getMember_weight() {
        return member_weight;
    }

    public String getMember_gender() {
        return member_gender;
    }


    public ArrayList<String> get_latitude(){
        return latitude;
    }
    public ArrayList<String> get_longitude(){
        return longitude;
    }

    public void setMember_id(String member_id) {
        this.member_id = member_id;
    }

    public void setMember_name(String member_name) {
        this.member_name = member_name;
    }


    public void setMember_age(int member_age) {
        this.member_age = member_age;
    }

    public void setMember_height(int member_height) {
        this.member_height = member_height;
    }

    public void setMember_weight(double member_weight) {
        this.member_weight = member_weight;
    }

    public void setMember_gender(String member_gender) {
        this.member_gender = member_gender;
    }


    //위도값 리스트 초기화후 다시 저장
    public void setLatitude (ArrayList<String> latitude){
        this.latitude.clear();
        this.latitude = latitude;
    }

    public void setLongitude (ArrayList<String> longitude){
        this.longitude.clear();
        this.longitude = longitude;
    }
}
