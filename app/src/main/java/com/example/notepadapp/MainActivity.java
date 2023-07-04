package com.example.notepadapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
import android.view.View;

import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.notepadapp.databinding.ActivityMainBinding;

import android.view.Menu;
import android.view.MenuItem;

import io.realm.Case;
import io.realm.Realm;
import io.realm.RealmChangeListener;
import io.realm.RealmResults;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration appBarConfiguration;
    private ActivityMainBinding binding;

    RecyclerView recyclerView;
    MyNotesAdapter myAdapter;
    RealmResults<Note> notesList;

    private static final int REQUEST_CODE_NOTE_UPDATE = 1;

    Realm realm;



    @Override
    protected void onCreate(Bundle savedInstanceState) {

        SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", MODE_PRIVATE);
        boolean isFirstTime = sharedPreferences.getBoolean("isFirstTime", true);

        if (isFirstTime) {
            // Perform actions for the first time
            startActivity(new Intent(MainActivity.this,setPin.class));

            // Once the necessary actions are completed, set the flag to false
            /*SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putBoolean("isFirstTime", false);
            editor.apply();*/
        }

        else{
            startActivity(new Intent(MainActivity.this, Login.class));


        }

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton addNoteBtn = findViewById(R.id.fab);
        addNoteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,AddNewNote.class));
            }
        });

        Realm.init(getApplicationContext());
        realm = Realm.getDefaultInstance();

        notesList = realm.where(Note.class).findAll();


        recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        myAdapter = new MyNotesAdapter(MainActivity.this,notesList);
        recyclerView.setAdapter(myAdapter);

        notesList.addChangeListener(new RealmChangeListener<RealmResults<Note>>() {
            @Override
            public void onChange(RealmResults<Note> notes) {
                myAdapter.notifyDataSetChanged();
            }
        });






    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_NOTE_UPDATE && resultCode == RESULT_OK) {
            if (data != null) {
                SerialisableNote updatedData = (SerialisableNote) data.getSerializableExtra("updatedNote");

                for (int i=0;i<notesList.size();i++){
                    Note note = notesList.get(i);
                    if (note.getTimeStamp() == updatedData.getOldTimeStamp()){
                        Realm realm = Realm.getDefaultInstance();
                        realm.beginTransaction();

                        note.setDescription(updatedData.getDescription());
                        note.setAccountName(updatedData.getAccountName());
                        note.setAccountPassword(updatedData.getAccountPassword());
                        note.setTimeStamp(updatedData.getNewTimeStamp());

                        realm.commitTransaction();
                        //notify that item is changed at position i
                        myAdapter.notifyItemChanged(i);
                        break;

                    }
                }


            }
        }
    }





    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        MenuItem menuItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) menuItem.getActionView();
        searchView.setQueryHint("Type here to search");

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                filter(newText);
                return false;
            }

        });


        return super.onCreateOptionsMenu(menu);
    }



    private void filter(String text){
        RealmResults<Note> filteredList;


        filteredList = realm.where(Note.class).contains("description",text, Case.INSENSITIVE).findAll();

        myAdapter.filterList(filteredList);
    }

}