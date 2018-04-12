package com.example.testmessage.testmessageapp.contractor;

import com.example.testmessage.testmessageapp.database.DatabaseHouse;
import com.example.testmessage.testmessageapp.database.dataenetities.DbModelContact;

import java.io.InputStream;
import java.util.List;

public interface HomeContractor {


    interface IViewHome extends IView {

        void onContactLoadSuccess(List<DbModelContact> dbModelContacts);

        void onContactLoadFail();

        void onParseCsvSuccess(List<DbModelContact> dbModelContacts);

        void onParseCsvFail(String strError);

    }

    interface IPresenterHome extends IPresenter {
        void loadContacts(DatabaseHouse databaseHouse);

        void insertContact(DatabaseHouse databaseHouse, List<DbModelContact> dbModelContacts);

        void parseCSV(DatabaseHouse databaseHouse, InputStream inputStream);


    }
}
