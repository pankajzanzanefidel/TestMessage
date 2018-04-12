package com.example.testmessage.testmessageapp.presenter;

import android.graphics.ColorSpace;

import com.example.testmessage.testmessageapp.contractor.HomeContractor;
import com.example.testmessage.testmessageapp.contractor.IView;
import com.example.testmessage.testmessageapp.database.DatabaseHouse;
import com.example.testmessage.testmessageapp.database.dataenetities.DbModelContact;
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
    public void loadContacts(DatabaseHouse databaseHouse) {

        new ModelHome().getAllContact(iContactLoad, databaseHouse);

    }

    @Override
    public void insertContact(DatabaseHouse databaseHouse, List<DbModelContact> dbModelContacts) {
        new ModelHome().insertContact(iContactLoad, databaseHouse, dbModelContacts);
    }

    @Override
    public void parseCSV(DatabaseHouse databaseHouse, InputStream inputStream) {
        new ModelHome().parseCSV(callbackParseCsv, databaseHouse, inputStream);
    }




    ModelHome.IContactLoad iContactLoad = new ModelHome.IContactLoad() {
        @Override
        public void onContactLoadSuccess(List<DbModelContact> dbModelContacts) {
            iViewHome.onContactLoadSuccess(dbModelContacts);
        }

        @Override
        public void onContactLoadFail() {

        }
    };

    ModelHome.IParseCsv callbackParseCsv = new ModelHome.IParseCsv() {

        @Override
        public void onParseSuccess(List<DbModelContact> dbModelContacts) {
            iViewHome.onParseCsvSuccess(dbModelContacts);
        }

        @Override
        public void onParseFail(String strError) {
            iViewHome.onParseCsvFail(strError);
        }
    };

    ModelHome.ISaveMessage callBackSavedMessage = new ModelHome.ISaveMessage() {
        @Override
        public void onMessageSavedSuccess() {

        }

        @Override
        public void onMessageSavedFail() {

        }
    };
}
