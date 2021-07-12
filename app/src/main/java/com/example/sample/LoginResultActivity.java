package com.example.sample;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class LoginResultActivity extends AppCompatActivity {

    TextView TextView_get;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView_get = findViewById(R.id.TextView_get);


        //MainActivity 클래스에서 보낸 데이터 받기
        Intent intent = getIntent();

        Bundle bundle = intent.getExtras();
        String email = bundle.getString("email");
        String password = bundle.getString("password");

        TextView_get.setText("email: " + email + " / password: " + password);

    }
}