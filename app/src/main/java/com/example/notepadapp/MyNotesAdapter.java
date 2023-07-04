package com.example.notepadapp;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.io.Serializable;
import java.text.DateFormat;
import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import io.realm.Realm;
import io.realm.RealmResults;


public class MyNotesAdapter extends RecyclerView.Adapter<MyNotesAdapter.MyViewHolder>{
    Context context;
    RealmResults<Note> notesList;

    private MainActivity mActivity;
    private static final int REQUEST_CODE_NOTE_UPDATE = 1;



    public MyNotesAdapter(Context context, RealmResults<Note> notesList){
        this.context = context;
        this.notesList = notesList;
        mActivity = (MainActivity) context;
    }




    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.item_view,parent,false));
    }


    @Override
    public void onBindViewHolder(@NonNull MyNotesAdapter.MyViewHolder holder, int position) {
        Note note = notesList.get(position);



        holder.Account_description.setText(note.getDescription());
        holder.Account_username.setText(note.getAccountName());
        holder.Account_password.setText(note.getAccountPassword());
        holder.uniqueID.setText("unique timestamp id:" + Long.toString(note.getTimeStamp()));


        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {

                PopupMenu menu = new PopupMenu(context,v);
                menu.getMenu().add("DELETE");
                menu.getMenu().add("UPDATE");
                menu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {

                    @Override
                    public boolean onMenuItemClick(MenuItem item) {

                        if(item.getTitle().equals("DELETE")){
                            //delete the note
                            Realm realm = Realm.getDefaultInstance();
                            realm.beginTransaction();
                            note.deleteFromRealm();
                            realm.commitTransaction();
                            Toast.makeText(context,"Note deleted",Toast.LENGTH_SHORT).show();
                        }

                        if (item.getTitle().equals("UPDATE")){
                            Intent intent = new Intent(context, updateNote.class);
                            //pass the Note data to serialisableNote

                            SerialisableNote tempNote = new SerialisableNote(note.getDescription(),note.getAccountName(),note.getAccountPassword(),note.getTimeStamp(),System.currentTimeMillis());
                            intent.putExtra("Update_note", tempNote); // Pass the relevant data to NoteUpdate Activity
                            mActivity.startActivityForResult(intent, REQUEST_CODE_NOTE_UPDATE);
                            Toast.makeText(context,"Update Note",Toast.LENGTH_SHORT).show();
                        }
                        return true;
                    }
                });
                menu.show();

                return true;
            }
        });

    }







    @Override
    public int getItemCount() {
        return notesList.size();
    }

    public void filterList(RealmResults<Note> filteredList){
        notesList = filteredList;
        notifyDataSetChanged();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder{

        TextView Account_description;
        TextView Account_username;
        TextView Account_password;

        TextView uniqueID;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            Account_description = itemView.findViewById(R.id.note_description);
            Account_username = itemView.findViewById(R.id.note_AccountName);
            Account_password = itemView.findViewById(R.id.note_AccountPassword);
            uniqueID = itemView.findViewById(R.id.uniqueID);
        }
    }




}
