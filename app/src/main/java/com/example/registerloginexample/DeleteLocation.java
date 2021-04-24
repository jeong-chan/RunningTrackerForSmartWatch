package com.example.registerloginexample;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;
//사용자의 경로,거리, 칼로리, 시간 DB에 저장
public class DeleteLocation extends StringRequest {

    //서버 URL설정 ( PHP파일 연동 )
    final static private String URL = "http://cw20173068.dothome.co.kr/delete.php";

    private Map<String, String> map;
    //java.sql.Date date = new java.sql.Date(new java.util.Date().getTime());

    public DeleteLocation(String loginID, String run_date, Response.Listener<String> listener) {

        super(Request.Method.POST, URL, listener, null);
        map = new HashMap<>();
        map.put("userID",loginID);
        map.put("run_Date",run_date);

    }


    @Override
    protected Map<String, String> getParams() throws AuthFailureError {
        return map;
    }
}
