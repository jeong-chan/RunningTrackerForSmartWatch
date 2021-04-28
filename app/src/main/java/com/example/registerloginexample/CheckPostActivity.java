package com.example.registerloginexample;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class CheckPostActivity extends MainActivity {

    public static ImageView posted_imageView;
    public static FirebaseStorage mFireStorage;
    public static StorageReference storageRef;
    public static TextView check_result_distance_view, check_result_time_view,check_runningplace, check_result_kcal_view, check_spinner_city, check_spinner_sigungu, check_City, check_Sigungu, check_Title, check_comment;
    public static FirebaseDatabase database;
    public static DatabaseReference databaseRef;
    public CustomAdpter cstadpter;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_post);
        initFirestore();

        posted_imageView = (ImageView) findViewById(R.id.Check_imageView);
        check_City = (TextView) findViewById(R.id.Check_spinner_city);
        check_Sigungu = (TextView) findViewById(R.id.Check_spinner_sigungu);
        check_runningplace = (TextView) findViewById(R.id.Check_runnigplace);
        check_Title = (TextView) findViewById(R.id.Check_Title);
        check_comment = (TextView) findViewById(R.id.Check_Comment);
        check_result_time_view = (TextView) findViewById(R.id.Check_post_time);
        check_result_kcal_view = (TextView) findViewById(R.id.Check_post_kcal);
        check_result_distance_view = (TextView) findViewById(R.id.Check_post_distance);
        check_spinner_city = (TextView) findViewById(R.id.spinner_city);
        check_spinner_sigungu = (TextView) findViewById(R.id.spinner_sigungu);

        storageRef.child(cstadpter.idfordatabase+"_map_images").getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                Glide.with(getApplicationContext())
                        .load(uri)
                        .into(posted_imageView);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

            }
        });

        databaseRef.child("Track").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String time = String.valueOf(snapshot.child(cstadpter.idfordatabase).child("status").child("Time").getValue());
                check_result_time_view.setText(time);
                String distance = String.valueOf(snapshot.child(cstadpter.idfordatabase).child("status").child("Distance").getValue());
                check_result_distance_view.setText(distance);
                String kcal = String.valueOf(snapshot.child(cstadpter.idfordatabase).child("status").child("Kcal").getValue());
                check_result_kcal_view.setText(kcal);
                String city = String.valueOf(snapshot.child(cstadpter.idfordatabase).child("City").getValue());
                check_City.setText(city);
                String sigungu = String.valueOf(snapshot.child(cstadpter.idfordatabase).child("Sigungu").getValue());
                check_Sigungu.setText(sigungu);
                String runningplace = String.valueOf(snapshot.child(cstadpter.idfordatabase).child("RunningPlace").getValue());
                check_runningplace.setText(runningplace);
                String title = String.valueOf(snapshot.child(cstadpter.idfordatabase).child("Title").getValue());
                check_Title.setText(title);
                String comment = String.valueOf(snapshot.child(cstadpter.idfordatabase).child("Comment").getValue());
                check_comment.setText(comment);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

    public void initFirestore() {
        mFireStorage = FirebaseStorage.getInstance("gs://runtracker-df7a8.appspot.com/");
        storageRef = mFireStorage.getReference();
        database = FirebaseDatabase.getInstance();
        databaseRef = database.getReference();
    }


}
