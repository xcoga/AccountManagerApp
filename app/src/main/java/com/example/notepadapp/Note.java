package com.example.notepadapp;
import java.io.Serializable;

import io.realm.RealmObject;

public class Note extends RealmObject {
    String description;
    String AccountName;
    String AccountPassword;

    //timestamp is going to be used as our unique id
    long timeStamp;

    public Note(){

    }

    public Note(String description, String AccountName, String AccountPassword){
        this.description = description;
        this.AccountName = AccountName;
        this.AccountPassword = AccountPassword;
    }

    public long getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(long timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String getDescription() {
        return description;
    }

    public String getAccountName(){
        return AccountName;
    }

    public String getAccountPassword() {
        return AccountPassword;
    }

    public void setAccountName(String accountName) {
        this.AccountName = accountName;
    }

    public void setAccountPassword(String accountPassword) {
        this.AccountPassword = accountPassword;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
