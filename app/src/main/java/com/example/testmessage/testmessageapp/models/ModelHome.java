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
    private ISearchContact iSearchContact = null;
    ISaveMessage callbackSavedMessage = null;
    private List<DbModelContact> dbModelContacts;

    public interface IContactLoad {
        void onContactLoadSuccess(List<DbModelContact> dbModelContacts);

        void onContactLoadFail();
    }

    public interface ISearchContact {
        void onContactSearchSuccess(List<DbModelContact> dbModelContacts);

        void onCOntactSearchFail();
    }

    public interface IParseCsv {
        void onParseSuccess(List<DbModelContact> dbModelContacts);

        void onParseFail(String strError);
    }


    public interface ISaveMessage {
        void onMessageSavedSuccess();

        void onMessageSavedFail();
    }

    public void getAllContact(final ISearchContact iSearchContact, final DatabaseHouse databaseHouse, final String name) {
        Log.e("inside", "getAllContact");
        AppExecutor.getINSTANCE().getDiskIO().execute(new Runnable() {
            @Override
            public void run() {
                //Query to contacts
                Log.e("inside", "getAllContact2");
                contactDao = databaseHouse.getContactDao();
                dbModelContacts = contactDao.getAllContact(name);
                Log.e("inside", "getAllContact3");

                AppExecutor.getINSTANCE().getMainThread().execute(new Runnable() {
                    @Override
                    public void run() {
                        Log.e("inside", "getAllContact4");
                        iSearchContact.onContactSearchSuccess(dbModelContacts);
                    }
                });
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

    public void saveMessage(final ISaveMessage callbackSavedMessage ,final DatabaseHouse databaseHouse, final DbModelMessage dbModelMessage) {
        AppExecutor.getINSTANCE().getDiskIO().execute(new Runnable() {
            @Override
            public void run() {
                //Insert into database
                MessageDao messageDao = databaseHouse.getMessageDao();


                messageDao.insert(dbModelMessage);

                AppExecutor.getINSTANCE().getMainThread().execute(new Runnable() {
                    @Override
                    public void run() {
                        callbackSavedMessage.onMessageSavedSuccess();
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

                    //Remove first Column, which is a Header in CSV
                    scoreList.remove(0);

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
