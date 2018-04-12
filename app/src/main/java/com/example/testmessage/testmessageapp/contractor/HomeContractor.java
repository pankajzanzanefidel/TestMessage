package com.example.testmessage.testmessageapp.contractor;

import com.example.testmessage.testmessageapp.database.DatabaseHouse;
import com.example.testmessage.testmessageapp.database.dataenetities.DbModelContact;
import com.example.testmessage.testmessageapp.database.dataenetities.DbModelMessage;

import java.io.InputStream;
import java.util.List;

public interface HomeContractor {


    interface IViewHome extends IView {

        void onContactLoadSuccess(List<DbModelContact> dbModelContacts);

        void onContactLoadFail();

        void onParseCsvSuccess(List<DbModelContact> dbModelContacts);

        void onParseCsvFail(String strError);

        void onSearchContactSuccess(List<DbModelContact> dbModelContacts);

        void onSearchContactFail();

        void onSaveMessageSuccess();

        void onSaveMessageFail();
    }

    interface IPresenterHome extends IPresenter {
        void loadContacts(DatabaseHouse databaseHouse, String name);

        void insertContact(DatabaseHouse databaseHouse, List<DbModelContact> dbModelContacts);

        void parseCSV(DatabaseHouse databaseHouse, InputStream inputStream);

        void saveMessage(DatabaseHouse databaseHouse, DbModelMessage dbModelMessage);
    }
}
