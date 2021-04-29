package com.example.registerloginexample;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class LoginActivity extends AppCompatActivity {

    private EditText et_id, et_pass;
    private Button btn_login, btn_register;
    public static User_DB user_db = new User_DB();
    public static Run_DB run_db=new Run_DB();
    private ArrayList<String> runTime =new ArrayList<>();
    private ArrayList<String> runKcal =new ArrayList<>();
    private ArrayList<String> runDis =new ArrayList<>();
    private ArrayList<String> runDate =new ArrayList<>();
    public String ID_user;//PersonalActivity에서 접근할 변수
    private static String IP_ADDRESS = "cw20173068.dothome.co.kr";
    private static String TAG = "phpexample";

    private String mJsonString;
    // String loginID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        et_id = findViewById(R.id.et_id);
        et_pass = findViewById(R.id.et_passcheck);
        btn_login = findViewById(R.id.btn_login);
        btn_register = findViewById(R.id.btn_register);



        // 회원가입 버튼을 클릭 시 수행
        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //EditText에 현재 입력되어있는 값을 get(가져온다)한다.
                String userID = et_id.getText().toString();
                String userPass = et_pass.getText().toString();


                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            boolean success = jsonObject.getBoolean("success");
                            if (success) {//로그인에 성공한 경우
                                ID_user=jsonObject.getString("userID");
                                user_db.setMember_id(jsonObject.getString("userID"));
                                user_db.setMember_name(jsonObject.getString("userName"));
                                user_db.setMember_age(jsonObject.getInt("userAge"));
                                user_db.setMember_height(jsonObject.getInt("userHeight"));
                                user_db.setMember_weight(jsonObject.getDouble("userWeight"));
                                user_db.setMember_gender(jsonObject.getString("userGender"));
                                user_db.setMember_gender(jsonObject.getString("userGender"));

                                getrundata();
                                Toast.makeText(getApplicationContext(), "로그인에 성공하셨습니다.", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                                startActivity(intent);
                            } else {//로그인에 실패한 경우
                                Toast.makeText(getApplicationContext(), "로그인에 실패하셨습니다.", Toast.LENGTH_SHORT).show();
                                return;
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                };
                LoginRequest loginRequest = new LoginRequest(userID, userPass, responseListener);
                RequestQueue queue = Volley.newRequestQueue(LoginActivity.this);
                queue.add(loginRequest);
            }
        });



    }


    public void getrundata() {

        Response.Listener<String> responseListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray jsonArray = new JSONArray(response);
                    //boolean success = jsonObject.getBoolean("success");
                    for (int i =0; i<jsonArray.length();i++) {//회원등록에 성공한 경우dksl.

                        JSONObject jsonObject= jsonArray.getJSONObject(i);
                        System.out.println(jsonObject);

                        String runtime = jsonObject.getString("run_Time");
                        String rundis=jsonObject.getString("run_Distance");
                        String runkcal =jsonObject.getString("run_Kcal");
                        String rundate = jsonObject.getString("run_Date");

                        // System.out.println(rundate);
                        runTime.add(runtime);
                        runDis.add(rundis);
                        runKcal.add(runkcal);
                        runDate.add(rundate);


                        //System.out.println(runTime);
                    }

                    run_db.setRun_time(runTime);
                    run_db.setRun_distance(runDis);
                    run_db.setRun_kcal(runKcal);
                    run_db.setRun_date(runDate);

                    System.out.println("제발... DB에서 나왔니...?" + runTime+"  "+ runDis+"  "+runKcal+"  "+ runDate);


                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        };

        {
            //서버로 Volley를 이용해서 요청을 함
            GetRunData getRunData = new GetRunData(ID_user, responseListener);
            RequestQueue queue = Volley.newRequestQueue(LoginActivity.this);
            queue.add(getRunData);
        }

    }


}


