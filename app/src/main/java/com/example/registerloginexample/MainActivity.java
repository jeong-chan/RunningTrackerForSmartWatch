package com.example.registerloginexample;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
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
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, FragMonday.OnTimePickerSetListener {
    private Uri uri;
    private FirebaseStorage storage;
    private StorageReference storageRef;
    private StorageReference mountainImagesRef;
    private Button mStartBtn, mStopBtn, mPauseBtn;
    public static TextView mTimeTextView,mSpeedTextView,mDistanceView,mKcalView;
    public static int mSec,Sec,min,hour;
    private Thread timeThread = null;
    private static Boolean isRunning = false;
    private GoogleMap snapmap;
    public String location_lat, location_long, skcal, sdistance, timer;
    private ArrayList<String> login_latitude = new ArrayList<String>();
    private ArrayList<String> login_longitude = new ArrayList<String>();
    //private String mJsonString;
    private String location_ID = LoginActivity.user_db.getMember_id();// 현재 로그인한 사용자의 ID
    private Date date = new Date();
    SimpleDateFormat datetime = new SimpleDateFormat("yyyy-MM-dd");
    private String run_date = datetime.format(date);

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

                deleteLocation();
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
                for (int i= 0; i<login_longitude.size(); i++) {
                  ArrayList<LatLng> login_latlng = new ArrayList<>();
                  login_latlng.add(new LatLng(Double.parseDouble(login_latitude.get(i)), Double.parseDouble(login_longitude.get(i))));
                }
                saveRunData();
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
                                GoogleMap.SnapshotReadyCallback callback = new GoogleMap.SnapshotReadyCallback() {

                                    @Override
                                    public void onSnapshotReady(Bitmap snapshot) {
                                        WritePostActivity.imageView1_test.setImageBitmap(snapshot);
                                    }
                                };
                                snapmap = fragMonday.getmMap();

                                PolylineOptions rectOptions = new PolylineOptions()
                                        .add(new LatLng(35.24154869294329, 128.69568999787336))
                                        .add(new LatLng(35.242188354851606, 128.6951750137158));

                                Polyline polyline = snapmap.addPolyline(rectOptions);
                                //여기서 디비에서 리스트로 LatLng받아와서 추가하면 맵에 그려짐 + 줌설정까지하면 완벽
                                //그리고 파일화? 하고 디비랑 연동해서 게시판에 등록하고
                                //게시판 이미지뷰에 클릭 이벤트 넣어서 MainActiviy에 똑같은 라인 그려지도록 저장
                                //그러려면 리스트 하나를 이미지랑 같이 저장해둬야함
                                snapmap.snapshot(callback);

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
            timer = Integer.toString(msg.arg1);
            @SuppressLint("DefaultLocale") String result = String.format("%02d:%02d:%02d:%02d", hour, min, Sec, mSec);
            mTimeTextView.setText(result);
        }
    };

    public void onTimePickerSet(String latitude, String longitude, Double kcal, Double distance) {
        DecimalFormat form = new DecimalFormat("#.##");
        location_lat = latitude;
        location_long = longitude;
        System.out.println("경도는!!!!!!!!!!!!  " + longitude);
        skcal = Double.toString(Double.parseDouble(form.format(kcal)));
        sdistance = Double.toString(Double.parseDouble(form.format(distance / 1000)));
        login_latitude.add(latitude);
        login_longitude.add(longitude);
        saveLocation();
    }

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

    //사용자가 달린 경로 DB에 저장
    public void saveLocation() {
        if (isRunning == true) {
            Response.Listener<String> responseListener = new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    try {
                        JSONObject jsonObject = new JSONObject(response);
                        boolean success = jsonObject.getBoolean("success");
                        if (success) {//회원등록에 성공한 경우
                            System.out.println("성공!!!!!!!!!!!!!!!!!!!");

                        } else {//회원등록에 실패한 경우
                            System.out.println("실패.............");
                            return;
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }
            };

            {
                //서버로 Volley를 이용해서 요청을 함
                InsertLocaion insertLocaion = new InsertLocaion(location_ID, location_lat, location_long, run_date, responseListener);
                RequestQueue queue = Volley.newRequestQueue(MainActivity.this);
                queue.add(insertLocaion);
            }
        } else {
            System.out.println("달리는 중이 아닙니다.");
        }

    }
    //사용자의 최신 경로만 남을수 있도록 DB에서 이전 경로 삭제
    public void deleteLocation() {

        Response.Listener<String> responseListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    boolean success = jsonObject.getBoolean("success");
                    if (success) {//회원등록에 성공한 경우
                        System.out.println("삭제성공!!!!!!!!!!!!!!!!!!!");

                    } else {//회원등록에 실패한 경우
                        System.out.println("삭제실패.............");
                        return;
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        };

        {
            //서버로 Volley를 이용해서 요청을 함
            DeleteLocation deleteLocation = new DeleteLocation(location_ID, run_date, responseListener);
            RequestQueue queue = Volley.newRequestQueue(MainActivity.this);
            queue.add(deleteLocation);
        }

    }

    public void saveRunData() {

        Response.Listener<String> responseListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    boolean success = jsonObject.getBoolean("success");
                    if (success) {//회원등록에 성공한 경우
                        System.out.println("성공2!!!!!!!!!!!!!!!!!!!");

                    } else {//회원등록에 실패한 경우
                        System.out.println("실패2.............");
                        return;
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        };
        //서버로 Volley를 이용해서 요청을 함
        InsertRunData insertRunData = new InsertRunData(location_ID, timer, sdistance, skcal, run_date, responseListener);
        RequestQueue queue = Volley.newRequestQueue(MainActivity.this);
        queue.add(insertRunData);

    }

}




