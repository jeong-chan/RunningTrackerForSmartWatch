package com.example.registerloginexample;

import android.annotation.SuppressLint;
import android.content.ComponentName;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.concurrent.atomic.AtomicMarkableReference;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Uri uri;
    private FirebaseStorage storage;
    private StorageReference storageRef;
    private StorageReference mountainImagesRef;
    private Button mStartBtn, mStopBtn, mPauseBtn;
    public static TextView mTimeTextView,mSpeedTextView,mDistanceView,mKcalView;
    public static int mSec,Sec,min,hour;
    private Thread timeThread = null;
    private static Boolean isRunning = false;

    @Override
    public void onClick(View view) {

    }

    public static Boolean getIsRunning() {
        return isRunning;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        storage  = FirebaseStorage.getInstance();
        storageRef  = storage.getReference();
        //뷰페이저 세팅
        ViewPager viewPager = findViewById(R.id.viewPager);
        VPAdapter fragmentPagerAdapter = new VPAdapter(getSupportFragmentManager());
        FragMonday fragMonday = new FragMonday();
        FragTuesday fragTuesday = new FragTuesday();
        FragWendnesday fragWendnesday = new FragWendnesday();
        fragmentPagerAdapter.addItem(fragMonday);
        fragmentPagerAdapter.addItem(fragTuesday);
        fragmentPagerAdapter.addItem(fragWendnesday);

        //탭과 페이지 연동
        TabLayout tabLayout = findViewById(R.id.tab_layout);
        viewPager.setAdapter(fragmentPagerAdapter);
        tabLayout.setupWithViewPager(viewPager);

        //액션바 설정하기//
        //액션바 타이틀 변경하기
        getSupportActionBar().setTitle("운동관리");
        //액션바 배경색 변경
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(0xFF339999));
        //홈버튼 표시
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //액션바 숨기기
        //hideActionBar();

        //초시계
        if (Build.VERSION.SDK_INT >= 21) {
            getWindow().setStatusBarColor(Color.parseColor("#4ea1d3"));
        }

        mStartBtn = (Button) findViewById(R.id.btn_runStart);
        mStopBtn = (Button) findViewById(R.id.btn_runStop);
        mPauseBtn = (Button) findViewById(R.id.btn_pause);
        mTimeTextView = (TextView) findViewById(R.id.timeView);
        mSpeedTextView = (TextView) findViewById(R.id.speedView);
        mDistanceView = (TextView)findViewById(R.id.distanceView);
        mKcalView = (TextView)findViewById(R.id.kcalView);

        //버튼클릭시 동작 수행
        mStartBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                isRunning = true;
                v.setVisibility(View.GONE);
                mStopBtn.setVisibility(View.VISIBLE);
                mPauseBtn.setVisibility(View.VISIBLE);

                timeThread = new Thread(new timeThread());
                timeThread.start();

            }
        });

        //스탑버튼 클릭시 동작수행
        mStopBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                isRunning = false;
                v.setVisibility(View.GONE);
                mStartBtn.setVisibility(View.VISIBLE);
                mPauseBtn.setVisibility(View.GONE);
                timeThread.interrupt();

                new AlertDialog.Builder(MainActivity.this)
                        .setTitle("알람 팝업")
                        .setMessage("공유하시겠습니까?")
                        .setNegativeButton("아니오", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Toast.makeText(getApplicationContext(),"취소",Toast.LENGTH_LONG).show();
                            }
                        })
                        .setPositiveButton("예", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
/*
                                    GoogleMap.SnapshotReadyCallback callback = new GoogleMap.SnapshotReadyCallback() {
                                        @Override
                                        public void onSnapshotReady(Bitmap bitmap) {
                                            try {
                                                String FileName = "TestRoute";
                                                saveBitmapToPNG(bitmap,FileName);


                                                storageRef.putFile(uri);
                                                mountainImagesRef = storageRef.child(FileName);

                                            } catch (Exception e) {
                                                e.printStackTrace();
                                            }
                                        }
                                    };
                                    fragMonday.getmMap().snapshot(callback);
*/
                                Intent intent = new Intent(MainActivity.this, WritePostActivity.class);
                                startActivity(intent);
                            }
                        })
                        .show();

                fragMonday.MapClear();
                fragMonday.setSpeed(0);
                fragMonday.setDistance(0);
                fragMonday.setKcal(0);
                mSpeedTextView.setText("0.00km/h");
                mDistanceView.setText("0.00km");
                mKcalView.setText("0.00Kcal");

            }
        });

        //일시정지버튼 클릭시 동작수행
        mPauseBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isRunning = !isRunning;
                if (isRunning) {
                    mPauseBtn.setText("PAUSE");
                } else {
                    mPauseBtn.setText("START");
                }
            }
        });

    }

    @SuppressLint("HandlerLeak")
    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            mSec = msg.arg1 % 100;
            Sec = (msg.arg1 / 100) % 60;
            min = (msg.arg1 / 100) / 60;
            hour = (msg.arg1 / 100) / 3600;

            @SuppressLint("DefaultLocale") String result = String.format("%02d:%02d:%02d:%02d", hour, min, Sec, mSec);
            mTimeTextView.setText(result);
        }
    };

    //초시계 동작
    public class timeThread implements Runnable{
        @Override
        public void run() {
            int i = 0;

            while(true){
                while (isRunning) {
                    Message msg = new Message();
                    msg.arg1 = i++;
                    handler.sendMessage(msg);

                    try{
                        Thread.sleep(10);
                    } catch (InterruptedException e){
                        e.printStackTrace();
                        runOnUiThread(new Runnable(){
                            @Override
                            public void run() {
                                mTimeTextView.setText("");
                                mTimeTextView.setText("00:00:00:00");
                            }
                        });
                        return;
                    }


                }
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    //액션버튼을 클릭했을때의 동작
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        Toast toast = Toast.makeText(getApplicationContext(), "", Toast.LENGTH_LONG);
        System.out.println("main화면");


        if (id == android.R.id.home) {
            Toast.makeText(this, "홈아이콘 클릭", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(MainActivity.this, LoginActivity.class);
            System.out.println("로그아웃 성공");
            startActivity(intent);
            return true;
        }

        if(id==R.id.action_btn1){
            toast.setText("프로필 클릭");
            System.out.println("프로필 성공");
            Intent intent = new Intent(MainActivity.this, PersonalActivity.class);
            startActivity(intent);

            return true;
        }

        toast.show();
        return super.onOptionsItemSelected(item);
    }

    //이전버튼 누르면 뒤로가기.
    @Override
    public void onBackPressed() {
        Toast.makeText(this, "Back button pressed.", Toast.LENGTH_SHORT).show();
        super.onBackPressed();
    }


    //액션바 숨기기
    private void hideActionBar() {
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null)
            actionBar.hide();
    }
/*
    private void saveBitmapToPNG(Bitmap bitmap, String name) {


        //내부저장소 캐시 경로를 받아옵니다.
        File storage = getCacheDir();

        //저장할 파일 이름
        String fileName = name + ".jpg";

        //storage 에 파일 인스턴스를 생성합니다.
        File tempFile = new File(storage, fileName);

        try {

            // 자동으로 빈 파일을 생성합니다.
            tempFile.createNewFile();

            // 파일을 쓸 수 있는 스트림을 준비합니다.
            FileOutputStream out = new FileOutputStream(tempFile);

            // compress 함수를 사용해 스트림에 비트맵을 저장합니다.
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, out);
            uri = Uri.fromFile(new File(String.valueOf(out)));

            // 스트림 사용후 닫아줍니다.
            out.close();

        } catch (FileNotFoundException e) {
            Log.e("MyTag","FileNotFoundException : " + e.getMessage());
        } catch (IOException e) {
            Log.e("MyTag","IOException : " + e.getMessage());
        }
    }
*/
}


