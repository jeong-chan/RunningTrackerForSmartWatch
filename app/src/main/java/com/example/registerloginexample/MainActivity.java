package com.example.registerloginexample;

import android.annotation.SuppressLint;
import android.content.ComponentName;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

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

}


