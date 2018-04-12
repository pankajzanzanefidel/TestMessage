package com.example.testmessage.testmessageapp.database.dataenetities;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import com.example.testmessage.testmessageapp.enums.EnumMessageState;
import com.example.testmessage.testmessageapp.helper.Constants;


@Entity(tableName = Constants.DatabaseConstants.MESSAGE.TABLE_NAME_MESSAGE)
public class DbModelMessage {


    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = Constants.DatabaseConstants.MESSAGE.COLUMN_NAME_MESSAGE_ID)
    private int id;

    @ColumnInfo(name = Constants.DatabaseConstants.MESSAGE.COLUMN_NAME_JOB_ID)
    private int jobId = 0;

    @ColumnInfo(name = Constants.DatabaseConstants.MESSAGE.COLUMN_NAME_MESSAGE_NUMBERS)
    private String numbers = null;

    @ColumnInfo(name = Constants.DatabaseConstants.MESSAGE.COLUMN_NAME_MESSAGE_TEXT)
    private String text = null;

    @ColumnInfo(name = Constants.DatabaseConstants.MESSAGE.COLUMN_NAME_MESSAGE_STATE)
    private int state = EnumMessageState.PENDING.ordinal();

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getJobId() {
        return jobId;
    }

    public void setJobId(int jobId) {
        this.jobId = jobId;
    }

    public String getNumbers() {
        return numbers;
    }

    public void setNumbers(String numbers) {
        this.numbers = numbers;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
