package com.example.registerloginexample;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class CheckPostActivity extends MainActivity {

    public static ImageView posted_imageView;
    public static FirebaseStorage mFireStorage;
    public static StorageReference storageRef;
    public static TextView check_result_distance_view, check_result_time_view, check_result_kcal_view, check_spinner_city, check_spinner_sigungu;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_post);
        initFirestore();

        posted_imageView = (ImageView) findViewById(R.id.Check_imageView);
        check_result_time_view = (TextView) findViewById(R.id.Check_post_time);
        check_result_kcal_view = (TextView) findViewById(R.id.Check_post_kcal);
        check_result_distance_view = (TextView) findViewById(R.id.Check_post_distance);
        check_spinner_city = (TextView) findViewById(R.id.spinner_city);
        check_spinner_sigungu = (TextView) findViewById(R.id.spinner_sigungu);

    }

    public void initFirestore() {
        mFireStorage = FirebaseStorage.getInstance();
        storageRef = mFireStorage.getReference();
    }
}
