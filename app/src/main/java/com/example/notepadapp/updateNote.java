package com.example.notepadapp;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.notepadapp.databinding.ActivityUpdateNoteBinding;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;

public class updateNote extends AppCompatActivity {

    EditText accountDescription;
    EditText accountUsername;
    EditText accountPassword;
    Button back;
    MaterialButton UpdateNote;

    SerialisableNote note;





    @Override
    protected void onCreate(Bundle savedInstanceState) {

        //intent is deserialised back to serialisable note here
        SerialisableNote note = (SerialisableNote) getIntent().getSerializableExtra("Update_note");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_note);


        accountDescription = findViewById(R.id.account_description);
        accountUsername = findViewById(R.id.account_username);
        accountPassword = findViewById(R.id.account_password);
        back = findViewById(R.id.back);
        UpdateNote = findViewById(R.id.updateNote);

        //set the values
        accountDescription.setText(note.getDescription());
        accountUsername.setText(note.getAccountName());
        accountPassword.setText(note.getAccountPassword());

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(updateNote.this, MainActivity.class);
                startActivity(intent);
            }
        });

        UpdateNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String acc_description = accountDescription.getText().toString();
                String acc_username = accountUsername.getText().toString();
                String acc_password = accountPassword.getText().toString();

                note.setDescription(acc_description);
                note.setAccountName(acc_username);
                note.setAccountPassword(acc_password);

                Intent intent = new Intent();
                intent.putExtra("updatedNote",note);
                setResult(RESULT_OK,intent);
                finish();


                Toast.makeText(UpdateNote.getContext(), "Note updated", Toast.LENGTH_SHORT).show();
                finish();
            }
        });





    }

}