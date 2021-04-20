package com.example.registerloginexample;

public class Track {
    private String profile;
    private String id;
    private int pw;
    private String userName;


    public Track(){}

    public Track(String profile, String id, int pw, String userName){
            this.userName = userName;
            this.pw = pw;
            this.id = id;
            this.profile = profile;
    }

    public String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPw() {
        String passw = String.valueOf(pw);
        return passw;
    }

    public void setPw(int pw) {
        this.pw = pw;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
