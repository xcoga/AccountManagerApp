package com.example.notepadapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class setPin extends AppCompatActivity {

    Button finish_pin_setting;
    EditText pin;
    EditText confirm_pin;
    TextView description;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_pin);

        finish_pin_setting = findViewById(R.id.finished_setting_pin);
        pin = findViewById(R.id.pin);
        confirm_pin = findViewById(R.id.pin_confirm);
        description = findViewById(R.id.PIN_description);

        pin.setInputType(InputType.TYPE_CLASS_NUMBER);
        confirm_pin.setInputType(InputType.TYPE_CLASS_NUMBER);

        description.setText("Please remember the pin that you set here. If not, there is no way " +
                "to recover your data. And you must clear cache of app to use the app again.");

        finish_pin_setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int pin_val = Integer.parseInt(pin.getText().toString());
                int confirm_pin_val = Integer.parseInt(confirm_pin.getText().toString());
                int len_pin = pin.getText().toString().length();

                SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();


                if (len_pin<6) Toast.makeText(setPin.this, "PIN too short!", Toast.LENGTH_SHORT).show();
                else if (pin_val == confirm_pin_val){
                    editor.putInt("MyPIN", pin_val);
                    editor.apply();

                    Toast.makeText(setPin.this, "PIN set!", Toast.LENGTH_SHORT).show();

                    finish();
                }
                else Toast.makeText(setPin.this, "PINs do not match!", Toast.LENGTH_SHORT).show();

            }
        });

    }
}