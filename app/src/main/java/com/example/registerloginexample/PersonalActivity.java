package com.example.registerloginexample;


import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.TextView;


public class PersonalActivity extends AppCompatActivity {


    private TextView show_id,show_name, show_age, show_height, show_weight, show_gender;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal);

        show_id = findViewById(R.id.textView_list_id);
        show_name = findViewById(R.id.textView_list_name);
        show_age = findViewById(R.id.textView_list_age);
        show_height = findViewById(R.id.textView_list_height);
        show_weight = findViewById(R.id.textView_list_weight);
        show_gender = findViewById(R.id.textView_list_gender);

        show_id.setText(LoginActivity.user_db.getMember_id());
        show_name.setText(LoginActivity.user_db.getMember_name());
        show_age.setText(Integer.toString(LoginActivity.user_db.getMember_age()));
        show_height.setText(Integer.toString(LoginActivity.user_db.getMember_height()));
        show_weight.setText(Double.toString(LoginActivity.user_db.getMember_weight()));
        show_gender.setText(LoginActivity.user_db.getMember_gender());
    }
}