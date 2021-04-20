package com.example.registerloginexample;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MainRequest extends StringRequest {

    //서버 URL설정 ( PHP파일 연동 )
    final static private String URL = "http://cw20173068.dothome.co.kr/location.php";
    private Map<String, String> map;



    public MainRequest(String loginID, String latitude, String longitude, Response.Listener<String> listener) {
        super(Request.Method.POST, URL, listener, null);

        map = new HashMap<>();
        map.put("locationID",loginID);
        map.put("location_lat",latitude);
        map.put("location_long",longitude);



    }

    @Override
    protected Map<String, String> getParams() throws AuthFailureError {
        return map;
    }

    // 사용자의 개인정보와 현재 달린 경도와 위도
    public static class User_DB {
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

    // 사용자의 총 운동데이터와 경로공유에서 사용하는 경로 데이터 관리
    public static class Run_DB {

        private ArrayList<String> run_distance =new ArrayList<String>();
        private ArrayList<String> run_time =new ArrayList<String>();
        private ArrayList<String> run_kcal =new ArrayList<String>();
        private ArrayList<String> latitude =new ArrayList<String>();
        private ArrayList<String> longitude=new ArrayList<String>();

        public ArrayList<String> getRun_distance(){
            return run_distance;
        }

        public ArrayList<String> getRun_time(){return run_time;}

        public ArrayList<String> getRun_kcal(){
            return run_kcal;
        }

        public ArrayList<String> get_latitude(){
            return latitude;
        }

        public ArrayList<String> get_longitude(){
            return longitude;
        }

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

    //사용자의 경로,거리, 칼로리, 시간 DB에 저장
    public static class RundataRequest extends StringRequest {

        //서버 URL설정 ( PHP파일 연동 )
        final static private String URL = "http://cw20173068.dothome.co.kr/rundata.php";
        private Map<String, String> map;

        //java.sql.Date date = new java.sql.Date(new java.util.Date().getTime());


        public RundataRequest(String loginID, String runTime, String runDis, String runKcal, String run_date, Response.Listener<String> listener) {
            super(Request.Method.POST, URL, listener, null);


            map = new HashMap<>();
            map.put("userID",loginID);
            map.put("run_Time",runTime);
            map.put("run_Distance",runDis);
            map.put("run_Kcal",runKcal);
            map.put("run_Date",run_date);


        }



        @Override
        protected Map<String, String> getParams() throws AuthFailureError {
            return map;
        }
    }

    //사용자의 경로,거리, 칼로리, 시간 DB에 저장
    public static class InsertLocaion extends StringRequest {

        //서버 URL설정 ( PHP파일 연동 )
        final static private String URL = "http://cw20173068.dothome.co.kr/location.php";

        private Map<String, String> map;

        //java.sql.Date date = new java.sql.Date(new java.util.Date().getTime());


        public InsertLocaion(String loginID, String latitude, String longitude, String run_date, Response.Listener<String> listener) {

            super(Request.Method.POST, URL, listener, null);
            map = new HashMap<>();
            map.put("locationID",loginID);
            map.put("location_lat",latitude);
            map.put("location_long",longitude);
            map.put("run_Date",run_date);

        }


        @Override
        protected Map<String, String> getParams() throws AuthFailureError {
            return map;
        }
    }

    // 사용자의 운동데이터 가져오기
    public static class GetRunData extends StringRequest {

        //서버 URL설정 ( PHP파일 연동 )
        final static private String URL = "http://cw20173068.dothome.co.kr/getrun.php";

        private Map<String, String> map;
        //java.sql.Date date = new java.sql.Date(new java.util.Date().getTime());

        public GetRunData(String loginID, Response.Listener<String> listener) {
            super(Request.Method.POST, URL, listener, null);

            System.out.println(loginID);
            map = new HashMap<>();
            map.put("userID",loginID);


        }


        @Override
        protected Map<String, String> getParams() throws AuthFailureError {
            return map;
        }
    }

    // 경로공유에서 원하는 사용자의 경로 데이터 가져오기
    public static class GetLocation extends StringRequest {

        //서버 URL설정 ( PHP파일 연동 )
        final static private String URL = "http://cw20173068.dothome.co.kr/getlocation.php";

        private Map<String, String> map;
        //java.sql.Date date = new java.sql.Date(new java.util.Date().getTime());

        public GetLocation(String loginID, Response.Listener<String> listener) {
            super(Request.Method.POST, URL, listener, null);

            System.out.println(loginID);
            map = new HashMap<>();
            map.put("userID", loginID);


        }


        @Override
        protected Map<String, String> getParams() throws AuthFailureError {
            return map;
        }
    }

    //사용자의 경로,거리, 칼로리, 시간 DB에 저장
    public static class DeleteLocation extends StringRequest {

        //서버 URL설정 ( PHP파일 연동 )
        final static private String URL = "http://cw20173068.dothome.co.kr/delete.php";

        private Map<String, String> map;
        //java.sql.Date date = new java.sql.Date(new java.util.Date().getTime());

        public DeleteLocation(String loginID, String run_date, Response.Listener<String> listener) {

            super(Request.Method.POST, URL, listener, null);
            map = new HashMap<>();
            map.put("locationID",loginID);
            map.put("run_Date",run_date);

        }


        @Override
        protected Map<String, String> getParams() throws AuthFailureError {
            return map;
        }
    }
}
