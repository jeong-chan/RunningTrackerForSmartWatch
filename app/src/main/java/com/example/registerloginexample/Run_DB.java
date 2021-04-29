package com.example.registerloginexample;


import java.util.ArrayList;

// 사용자의 총 운동데이터와 경로공유에서 사용하는 경로 데이터 관리
public class Run_DB {

    private ArrayList<String> run_distance =new ArrayList<String>();
    private ArrayList<String> run_time =new ArrayList<String>();
    private ArrayList<String> run_kcal =new ArrayList<String>();
    private ArrayList<String> latitude =new ArrayList<String>();
    private ArrayList<String> longitude=new ArrayList<String>();
    private ArrayList<String> run_date=new ArrayList<String>();
    public ArrayList<String> getRun_date(){return run_date;};

    public ArrayList<String> getRun_distance(){
        return run_distance;
    }

    public ArrayList<String> getRun_time(){return run_time;}

    public ArrayList<String> getRun_kcal(){
        return run_kcal;
    }
/*
    public ArrayList<String> get_latitude(){
        return latitude;
    }

    public ArrayList<String> get_longitude(){
        return longitude;
    }
*/
    public void setRun_distance (ArrayList<String> run_distance){
        this.run_distance.clear();
        this.run_distance = run_distance;
    }

    public void setRun_time (ArrayList<String> run_time){
        this.run_time.clear();
        this.run_time = run_time;
    }

    public void setRun_kcal (ArrayList<String> run_kcal){
        this.run_kcal.clear();
        this.run_kcal = run_kcal;
    }

    public void setRun_date (ArrayList<String> run_date){
        this.run_date.clear();
        this.run_date = run_date;
    }
/*
    //위도값 리스트 초기화후 다시 저장
    public void setLatitude (ArrayList<String> latitude){
        this.latitude.clear();
        this.latitude = latitude;
    }

    public void setLongitude (ArrayList<String> longitude){
        this.longitude.clear();
        this.longitude = longitude;
    }*/
}

