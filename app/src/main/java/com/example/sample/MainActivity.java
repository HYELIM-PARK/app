package com.example.sample;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;

import com.google.android.material.textfield.TextInputEditText;

public class MainActivity extends AppCompatActivity {

    TextInputEditText TextInputEditText_email, TextInputEditText_password;
    RelativeLayout RelativeLayout_login;
    String emailOK = "123@gmail.com";
    String passwordOK = "1234";

    String inputEmail = "";
    String inputPassword = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        TextInputEditText_email     = findViewById(R.id.TextInputEditText_email);
        TextInputEditText_password  = findViewById(R.id.TextInputEditText_password);
        RelativeLayout_login        = findViewById(R.id.RelativeLayout_login);

        //1. 값을 가져온다 - 검사 (123@gmail.com / 1234)
        //2. 클릭을 감지한다
        //3. 1번의 값을 다음 액티비티로 넘긴다 (화면이동)

//        RelativeLayout_login.setClickable(false);
        RelativeLayout_login.setEnabled(false);
        TextInputEditText_email.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
//                Log.d("LIM", s + "," + count);
                if (s != null) {
                    inputEmail = s.toString();
                    RelativeLayout_login.setEnabled(validation());
//                    if (validation()) {
////                        RelativeLayout_login.setClickable(true);
//                        RelativeLayout_login.setEnabled(true);
//                    } else {
//                        RelativeLayout_login.setEnabled(false);
//                    }

                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        TextInputEditText_password.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
//                Log.d("LIM", s + "," + count);
                if (s != null) {
                    inputPassword = s.toString();
                    RelativeLayout_login.setEnabled(validation());
//                    if (validation()) {
////                        RelativeLayout_login.setClickable(true);
//                        RelativeLayout_login.setEnabled(true);
//                    } else {
//                        RelativeLayout_login.setEnabled(false);
//                    }

                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


        //클릭 가능 여부 설정 (클릭 리스너 설정)
//        RelativeLayout_login.setClickable(true);
        RelativeLayout_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = TextInputEditText_email.getText().toString();
                String password = TextInputEditText_password.getText().toString();

                //Intent를 사용해 값 주고받기
                //  (현재 위치 클래스인 MainActivity 명확하게 표시해주기)
                Intent intent = new Intent(MainActivity.this, LoginResultActivity.class);
                intent.putExtra("email", email);
                intent.putExtra("password", password);
                startActivity(intent);

            }
        });

    }

    public boolean validation() {
        return inputEmail.equals(emailOK)  && inputPassword.equals(passwordOK);
    }

}