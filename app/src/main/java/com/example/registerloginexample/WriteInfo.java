package com.example.registerloginexample;

import android.content.Context;

public class WriteInfo {
    private String title;
    private String Contents;
    private String publisher;

    public WriteInfo(String title, String Contents, String publisher){
        this.Contents = Contents;
        this.title = title;
        this.publisher = publisher;
    }

    public String getTitle() {
        return this.title;
    }

    public String getPublisher() {
        return publisher;
    }

    public String getContents() {
        return this.Contents;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setContents(String contents) {
        Contents = contents;
    }
}
