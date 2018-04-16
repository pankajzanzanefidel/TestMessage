package com.example.testmessage.testmessageapp.presenter;

import android.graphics.ColorSpace;
import android.util.Log;

import com.example.testmessage.testmessageapp.contractor.HomeContractor;
import com.example.testmessage.testmessageapp.contractor.IView;
import com.example.testmessage.testmessageapp.database.DatabaseHouse;
import com.example.testmessage.testmessageapp.database.dataenetities.DbModelContact;
import com.example.testmessage.testmessageapp.database.dataenetities.DbModelMessage;
import com.example.testmessage.testmessageapp.models.ModelHome;

import java.io.InputStream;
import java.util.List;

public class PresenterHome implements HomeContractor.IPresenterHome {


    private HomeContractor.IViewHome iViewHome;

    @Override
    public void attach(IView view) {
        this.iViewHome = (HomeContractor.IViewHome) view;
    }

    @Override
    public void dettach() {
        iViewHome = null;
    }

    @Override
    public void loadContacts(DatabaseHouse databaseHouse, String name) {
        new ModelHome().getAllContact(callBackSearchContact, databaseHouse, name);
    }


    public void loadAllContacts(DatabaseHouse databaseHouse) {
        new ModelHome().getAllContact2(callBackSearchContact, databaseHouse);
    }

    @Override
    public void insertContact(DatabaseHouse databaseHouse, List<DbModelContact> dbModelContacts) {
        new ModelHome().insertContact(callBackIcontactLoad, databaseHouse, dbModelContacts);
    }

    @Override
    public void parseCSV(DatabaseHouse databaseHouse, InputStream inputStream) {
        iViewHome.toggleProgressIndigator(true);
        new ModelHome().parseCSV(callbackParseCsv, databaseHouse, inputStream);
    }

    @Override
    public void saveMessage(DatabaseHouse databaseHouse, DbModelMessage dbModelMessage) {
        new ModelHome().saveMessage(callBackSavedMessage, databaseHouse, dbModelMessage);
    }


    ModelHome.IContactLoad callBackIcontactLoad = new ModelHome.IContactLoad() {
        @Override
        public void onContactLoadSuccess(List<DbModelContact> dbModelContacts) {
          //  iViewHome.toggleProgressIndigator(false);
            iViewHome.onContactLoadSuccess(dbModelContacts);
        }

        @Override
        public void onContactLoadFail() {

            //iViewHome.toggleProgressIndigator(false);
        }
    };

    ModelHome.IParseCsv callbackParseCsv = new ModelHome.IParseCsv() {

        @Override
        public void onParseSuccess(List<DbModelContact> dbModelContacts) {
            iViewHome.toggleProgressIndigator(false);
            iViewHome.onParseCsvSuccess(dbModelContacts);
        }

        @Override
        public void onParseFail(String strError) {
            iViewHome.toggleProgressIndigator(false);
            iViewHome.onParseCsvFail(strError);
        }
    };


    ModelHome.ISearchContact callBackSearchContact = new ModelHome.ISearchContact() {
        @Override
        public void onContactSearchSuccess(List<DbModelContact> dbModelContacts) {
            Log.e("inside", " --  " + dbModelContacts.size());
            iViewHome.onSearchContactSuccess(dbModelContacts);
        }

        @Override
        public void onCOntactSearchFail() {

        }
    };

    ModelHome.ISaveMessage callBackSavedMessage = new ModelHome.ISaveMessage() {
        @Override
        public void onMessageSavedSuccess() {
            iViewHome.onSaveMessageSuccess();
        }

        @Override
        public void onMessageSavedFail() {
            iViewHome.onSaveMessageFail();
        }
    };
}
