package com.example.testmessage.testmessageapp.database.dataenetities;


import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import com.example.testmessage.testmessageapp.helper.Constants;

@Entity(tableName = Constants.DatabaseConstants.CONTACTS.TABLE_NAME_CONTACT)
public class DbModelContact {


    @PrimaryKey @NonNull
    @ColumnInfo(name = Constants.DatabaseConstants.CONTACTS.COLUMN_NAME_CONATACT_NUMBER)
    private String number = null;

    @ColumnInfo(name = Constants.DatabaseConstants.CONTACTS.COLUMN_NAME_CONATACT_NAME)
    private String name = null;

    @ColumnInfo(name = Constants.DatabaseConstants.CONTACTS.COLUMN_NAME_CONATACT_SURNAME)
    private String surname = null;

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }
}
