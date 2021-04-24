package com.example.registerloginexample;


import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

//사용자의 경로,거리, 칼로리, 시간 DB에 저장
public class InsertRunData extends StringRequest {

    //서버 URL설정 ( PHP파일 연동 )
    final static private String URL = "http://cw20173068.dothome.co.kr/rundata.php";
    private Map<String, String> map;

    //java.sql.Date date = new java.sql.Date(new java.util.Date().getTime());


    public InsertRunData(String userID, String run_Time, String run_Distance, String run_Kcal, String run_Date, Response.Listener<String> listener) {
        super(Method.POST, URL, listener, null);


        System.out.println(run_Kcal+"시간은 "+ run_Time);


        map = new HashMap<>();
        map.put("userID",userID);
        map.put("run_Time",run_Time);
        map.put("run_Distance",run_Distance);
        map.put("run_Kcal",run_Kcal);
        map.put("run_Date",run_Date);


    }



    @Override
    protected Map<String, String> getParams() throws AuthFailureError {
        return map;
    }
}
