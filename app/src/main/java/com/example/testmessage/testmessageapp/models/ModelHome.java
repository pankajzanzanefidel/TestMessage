package com.example.testmessage.testmessageapp.models;

import android.util.Log;

import com.example.testmessage.testmessageapp.database.DatabaseHouse;
import com.example.testmessage.testmessageapp.database.dao.ContactDao;
import com.example.testmessage.testmessageapp.database.dao.MessageDao;
import com.example.testmessage.testmessageapp.database.dataenetities.DbModelContact;
import com.example.testmessage.testmessageapp.database.dataenetities.DbModelMessage;
import com.example.testmessage.testmessageapp.executor.AppExecutor;
import com.example.testmessage.testmessageapp.utils.CSVParser;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class ModelHome {


    private ContactDao contactDao = null;
    private MessageDao messageDao = null;
    private IContactLoad iContactLoad = null;
    private IContactSync contactSync = null;
    ISaveMessage callbackSavedMessage = null;

    public interface IContactLoad {
        void onContactLoadSuccess(List<DbModelContact> dbModelContacts);

        void onContactLoadFail();
    }

    public interface IContactSync {
        void onContactSyncSuccess();

        void onCOntactSyncFail();
    }

    public interface IParseCsv {
        void onParseSuccess(List<DbModelContact> dbModelContacts);

        void onParseFail(String strError);
    }


    public interface ISaveMessage {
        void onMessageSavedSuccess();

        void onMessageSavedFail();
    }

    public void getAllContact(final ModelHome.IContactLoad iContactLoad, DatabaseHouse databaseHouse) {

        AppExecutor.getINSTANCE().getDiskIO().execute(new Runnable() {
            @Override
            public void run() {
                //Query to contacts
            }
        });
    }


    public void insertContact(final ModelHome.IContactLoad iContactLoad, final DatabaseHouse databaseHouse, final List<DbModelContact> dbModelContacts) {

        AppExecutor.getINSTANCE().getDiskIO().execute(new Runnable() {
            @Override
            public void run() {
                //Insert into database
                contactDao = databaseHouse.getContactDao();

                contactDao.deleteAll();
                contactDao.insertAll(dbModelContacts);

                AppExecutor.getINSTANCE().getMainThread().execute(new Runnable() {
                    @Override
                    public void run() {
                        iContactLoad.onContactLoadSuccess(dbModelContacts);
                    }
                });
            }
        });
    }





    public void parseCSV(final IParseCsv callback, DatabaseHouse databaseHouse, final InputStream inputStream) {

        AppExecutor.getINSTANCE().getDiskIO().execute(new Runnable() {
            @Override
            public void run() {
                //Read from CSV
                CSVParser csvFile = new CSVParser(inputStream);
                List<String[]> scoreList = null;
                try {
                    scoreList = csvFile.read();

                    final List<DbModelContact> dbModelContacts = new ArrayList();
                    DbModelContact dbModelContact;
                    for (String[] scoreData : scoreList) {
                        // itemArrayAdapter.add(scoreData);
                        dbModelContact = new DbModelContact();
                        dbModelContact.setNumber(scoreData[0]);
                        dbModelContact.setName(scoreData[1]);
                        dbModelContact.setSurname(scoreData[2]);
                        dbModelContacts.add(dbModelContact);
                        Log.e("value", scoreData[0] + " " + scoreData[1] + " " + scoreData[2]);
                    }

                    //Return to Presenter
                    AppExecutor.getINSTANCE().getMainThread().execute(new Runnable() {
                        @Override
                        public void run() {

                            callback.onParseSuccess(dbModelContacts);
                        }
                    });

                } catch (final Exception e) {
                    e.printStackTrace();
                    AppExecutor.getINSTANCE().getMainThread().execute(new Runnable() {
                        @Override
                        public void run() {

                            callback.onParseFail(e.getLocalizedMessage());
                        }
                    });


                }
            }
        });
    }

}
