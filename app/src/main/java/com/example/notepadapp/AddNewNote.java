package com.example.notepadapp;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.navigation.ui.AppBarConfiguration;

import com.google.android.material.button.MaterialButton;

import io.realm.Realm;


public class AddNewNote extends AppCompatActivity {

    private AppBarConfiguration appBarConfiguration;


    private MaterialButton createNote;
    private Button back;



    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_note);

        createNote = findViewById(R.id.createNote);
        back = findViewById(R.id.back);

        EditText description = findViewById(R.id.account_description);
        EditText username = findViewById(R.id.account_username);
        EditText password = findViewById(R.id.account_password);

        Realm.init(getApplicationContext());
        Realm realm = Realm.getDefaultInstance();



        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AddNewNote.this, MainActivity.class);
                startActivity(intent);
            }
        });

        createNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String acc_description = description.getText().toString();
                String acc_username = username.getText().toString();
                String acc_password = password.getText().toString();

                realm.beginTransaction();
                Note note = realm.createObject(Note.class);
                note.setAccountName(acc_username);
                note.setAccountPassword(acc_password);
                note.setDescription(acc_description);
                note.setTimeStamp(System.currentTimeMillis());

                realm.commitTransaction();

                Toast.makeText(AddNewNote.this, "Note created", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }

}