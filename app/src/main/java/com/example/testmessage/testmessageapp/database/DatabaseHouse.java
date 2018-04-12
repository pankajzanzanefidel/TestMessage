package com.example.testmessage.testmessageapp.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.example.testmessage.testmessageapp.database.dao.ContactDao;
import com.example.testmessage.testmessageapp.database.dao.MessageDao;
import com.example.testmessage.testmessageapp.database.dataenetities.DbModelContact;
import com.example.testmessage.testmessageapp.database.dataenetities.DbModelMessage;
import com.example.testmessage.testmessageapp.helper.Constants;

import java.util.List;

@Database(entities = {DbModelContact.class, DbModelMessage.class}, version = 3)
public abstract class DatabaseHouse extends RoomDatabase {

    private static DatabaseHouse INSTANCE = null;

    public abstract ContactDao getContactDao();
    public abstract MessageDao getMessageDao();

    public static DatabaseHouse getSingleTon(Context applicationContext) {


        if (INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(applicationContext, DatabaseHouse.class, Constants.DatabaseConstants.DATABASE_NAME)
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return INSTANCE;
    }

    public void deleteAllTables(List<String> tableNames) {
        //This has to execute on a thread
        for (String table : tableNames) {
            INSTANCE.query("DELETE FROM " + table, null);
        }

    }
}
