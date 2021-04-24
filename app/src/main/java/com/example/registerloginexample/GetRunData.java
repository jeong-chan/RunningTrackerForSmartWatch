package com.example.registerloginexample;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;
// 사용자의 운동데이터 가져오기
public class GetRunData extends StringRequest {

    //서버 URL설정 ( PHP파일 연동 )
    final static private String URL = "http://cw20173068.dothome.co.kr/getrun.php";

    private Map<String, String> map;
    //java.sql.Date date = new java.sql.Date(new java.util.Date().getTime());

    public GetRunData(String loginID, Response.Listener<String> listener) {
        super(Request.Method.POST, URL, listener, null);

        //System.out.println(loginID);
        map = new HashMap<>();
        map.put("userID",loginID);


    }


    @Override
    protected Map<String, String> getParams() throws AuthFailureError {
        return map;
    }
}