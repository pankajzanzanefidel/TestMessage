package com.example.testmessage.testmessageapp.database.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.example.testmessage.testmessageapp.database.dataenetities.DbModelContact;
import com.example.testmessage.testmessageapp.helper.Constants;

import java.util.List;

@Dao
public interface ContactDao {

    @Query("SELECT * FROM " + Constants.DatabaseConstants.CONTACTS.TABLE_NAME_CONTACT)
    List<DbModelContact> getAllContact();

    @Insert
    void insertAll(List<DbModelContact> dbModelContacts);

    @Insert
    void insert(DbModelContact dbModelContact);

    @Delete
    void delete(DbModelContact customer);

    @Query("DELETE FROM " + Constants.DatabaseConstants.CONTACTS.TABLE_NAME_CONTACT)
    void deleteAll();
}
