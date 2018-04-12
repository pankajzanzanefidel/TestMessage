package com.example.testmessage.testmessageapp.database.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.example.testmessage.testmessageapp.database.dataenetities.DbModelContact;
import com.example.testmessage.testmessageapp.database.dataenetities.DbModelMessage;
import com.example.testmessage.testmessageapp.helper.Constants;

import java.util.List;

@Dao
public interface MessageDao {

    @Query("SELECT * FROM " + Constants.DatabaseConstants.MESSAGE.TABLE_NAME_MESSAGE)
    List<DbModelMessage> getAllMessage();

    @Insert
    void insertAll(List<DbModelMessage> dbModelMessages);

    @Insert
    void insert(DbModelMessage dbModelMessage);

    @Delete
    void delete(DbModelMessage dbModelMessage);

    @Update
    void update(DbModelMessage dbModelMessage);

    @Query("SELECT * FROM " +Constants.DatabaseConstants.MESSAGE.TABLE_NAME_MESSAGE+ " WHERE "+ Constants.DatabaseConstants.MESSAGE.COLUMN_NAME_JOB_ID+" = :jobId")
    DbModelMessage getModelMessage(int jobId);

    @Query("SELECT * FROM " +Constants.DatabaseConstants.MESSAGE.TABLE_NAME_MESSAGE+ " WHERE "
            + Constants.DatabaseConstants.MESSAGE.COLUMN_NAME_MESSAGE_STATE+" = :state")
    List<DbModelMessage> getListMessages(int state);

    @Query("DELETE FROM " + Constants.DatabaseConstants.MESSAGE.TABLE_NAME_MESSAGE)
    void deleteAll();
}
