package com.example.sporteam.sporteam;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by redbend on 7/14/16.
 */
public class LoginActivity extends AppCompatActivity {

        EditText email;
        EditText pass;
        Button toRegister;
        Button toLogin;

        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_login);

            /**
             * Defining all layout items
             **/
            email = (EditText) findViewById(R.id.emailLogin);
            pass = (EditText) findViewById(R.id.passLogin);
            toRegister = (Button) findViewById(R.id.registerButt);
            toLogin = (Button) findViewById(R.id.loginButt);

            email.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    email.setText("");
                }
            });

            pass.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    pass.setText("");
                }
            });

            toRegister.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent( LoginActivity.this, registerActivity.class);
                    startActivity(intent);
                }
            });

            toLogin.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    if (  (email.getText().toString().equals("")) || ( pass.getText().toString().equals("")) ) {
                        Toast.makeText(getApplicationContext(),
                                "One or more fields are empty", Toast.LENGTH_SHORT).show();
                    }else {
                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                        startActivity(intent);
                    }
                }
            });
        }

}