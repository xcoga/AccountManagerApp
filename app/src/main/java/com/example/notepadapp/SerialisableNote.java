package com.example.notepadapp;

import java.io.Serializable;

public class SerialisableNote implements Serializable {

    String description;
    String AccountName;
    String AccountPassword;

    long oldTimeStamp;

    long newTimeStamp;

    public SerialisableNote (){

    }

    public SerialisableNote (String description, String AccountName, String AccountPassword, long oldTimeStamp, long newTimeStamp){
        this.description = description;
        this.AccountName = AccountName;
        this.AccountPassword = AccountPassword;
        this.oldTimeStamp = oldTimeStamp;
        this.newTimeStamp = newTimeStamp;
    }

    public long getNewTimeStamp() {
        return newTimeStamp;
    }

    public void setNewTimeStamp(long newTimeStamp) {
        this.newTimeStamp = newTimeStamp;
    }

    public long getOldTimeStamp() {
        return oldTimeStamp;
    }

    public void setOldTimeStamp(long oldTimeStamp) {
        this.oldTimeStamp = oldTimeStamp;
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
