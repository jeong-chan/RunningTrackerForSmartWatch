package com.example.registerloginexample;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;


public class PersonalActivity extends AppCompatActivity {



    private static String IP_ADDRESS = "cw20173068.dothome.co.kr";
    private static String TAG = "phpexample";
    private String mJsonString;
    public static double login_user_weight;
    private TextView show_id,show_name, show_age, show_height, show_weight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal);

       // System.out.println("personalActivity 실행");
        String loginID = ((LoginActivity)LoginActivity.context_ID).ID_user;

      //  System.out.println("된거니?"+loginID);

        GetData task = new GetData();
        task.execute("http://" + IP_ADDRESS + "/query.php", loginID);


    }

    private class GetData extends AsyncTask<String, Void, String>{

        ProgressDialog progressDialog;
        String errorString = null;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            progressDialog = ProgressDialog.show(PersonalActivity.this,
                    "Please Wait", null, true, true);
        }


        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);

            progressDialog.dismiss();


            // mTextViewResult.setText(result);
            Log.d(TAG, "response - " + result);

            mJsonString = result;
            showResult();

        }


        @Override
        protected String doInBackground(String... params) {

            String serverURL = params[0];
            String postParameters = "userID=" + params[1];


            try {

                URL url = new URL(serverURL);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();


                httpURLConnection.setReadTimeout(5000);
                httpURLConnection.setConnectTimeout(5000);
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoInput(true);
                httpURLConnection.connect();


                OutputStream outputStream = httpURLConnection.getOutputStream();
                outputStream.write(postParameters.getBytes("UTF-8"));
                outputStream.flush();
                outputStream.close();


                int responseStatusCode = httpURLConnection.getResponseCode();
                Log.d(TAG, "response code - " + responseStatusCode);

                InputStream inputStream;
                if(responseStatusCode == HttpURLConnection.HTTP_OK) {
                    inputStream = httpURLConnection.getInputStream();
                }
                else{
                    inputStream = httpURLConnection.getErrorStream();
                }


                InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "UTF-8");
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

                StringBuilder sb = new StringBuilder();
                String line;

                while((line = bufferedReader.readLine()) != null){
                    sb.append(line);
                }

                bufferedReader.close();

                return sb.toString().trim();


            } catch (Exception e) {

                Log.d(TAG, "InsertData: Error ", e);
                errorString = e.toString();

                return null;
            }

        }
    }


    private void showResult(){

        show_id = findViewById(R.id.textView_list_id);
        show_name = findViewById(R.id.textView_list_name);
        show_age = findViewById(R.id.textView_list_age);
        show_height = findViewById(R.id.textView_list_height);
        show_weight = findViewById(R.id.textView_list_weight);

        String TAG_JSON="webnautes";
        String TAG_ID = "userID";
        String TAG_NAME = "userName";
        String TAG_AGE ="userAge";
        String TAG_HEIGHT ="userHeight";
        String TAG_WEIGHT ="userWeight";


        try {
            JSONObject jsonObject = new JSONObject(mJsonString);
            JSONArray jsonArray = jsonObject.getJSONArray(TAG_JSON);

            for(int i=0;i<jsonArray.length();i++){

                JSONObject item = jsonArray.getJSONObject(i);

                String id = item.getString(TAG_ID);
                String name = item.getString(TAG_NAME);
                String age = item.getString(TAG_AGE);
                String height = item.getString(TAG_HEIGHT);
                String weight = item.getString(TAG_WEIGHT);

                show_id.setText(id);
                show_name.setText(name);
                show_age.setText(age);
                show_height.setText(height);
                show_weight.setText(weight);

                login_user_weight = Double.parseDouble(weight);
            }



        } catch (JSONException e) {

            Log.d(TAG, "showResult : ", e);
        }

    }

}