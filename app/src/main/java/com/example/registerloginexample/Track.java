package com.example.registerloginexample;

public class Track {
    private String Sigungu;
    private String City;
    private String id;
    private String Title;
    private String RunningPlace;

    public String getRunningplace() {
        return RunningPlace;
    }

    public void setRunningplace(String runningplace) {
        RunningPlace = runningplace;
    }

    public Track() {
    }

    public Track(String sigungu, String id, String City, String Title, String runningplace) {
        this.City = City;
        this.Title = Title;
        this.id = id;
        this.Sigungu = sigungu;
        this.RunningPlace = runningplace;
    }

    public String getSigungu() {
        return Sigungu;
    }

    public void setSigungu(String sigungu) {
        Sigungu = sigungu;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCity() {
        return City;
    }

    public void setCity(String city) {
        City = city;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }
}
