package com.example.notepadapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Login extends AppCompatActivity {
    Button login;
    EditText loginPIN;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        login = findViewById(R.id.Login);
        loginPIN = findViewById(R.id.login_PIN);

        //TODO new code
        SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", MODE_PRIVATE);
        int retrievedPIN = sharedPreferences.getInt("MyPIN", 0);

        Log.d("PIN received", String.valueOf(retrievedPIN));

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int userInputPIN = Integer.parseInt(loginPIN.getText().toString());
                if (userInputPIN==retrievedPIN){
                    finish();
                }

                else {
                    Toast.makeText(Login.this,"PIN incorrect!",Toast.LENGTH_SHORT);
                }

            }
        });












    }
}