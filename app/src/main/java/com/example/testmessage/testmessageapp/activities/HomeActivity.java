package com.example.testmessage.testmessageapp.activities;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.testmessage.testmessageapp.R;
import com.example.testmessage.testmessageapp.adapter.CustomAdapter;
import com.example.testmessage.testmessageapp.contractor.HomeContractor;
import com.example.testmessage.testmessageapp.database.DatabaseHouse;
import com.example.testmessage.testmessageapp.database.dataenetities.DbModelContact;
import com.example.testmessage.testmessageapp.executor.AppExecutor;
import com.example.testmessage.testmessageapp.helper.Constants;
import com.example.testmessage.testmessageapp.helper.PreferenceUtils;
import com.example.testmessage.testmessageapp.presenter.PresenterHome;
import com.example.testmessage.testmessageapp.utils.PathUtil;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.util.List;

public class HomeActivity extends BaseActivity implements HomeContractor.IViewHome, View.OnClickListener, CLickListner {

    private PresenterHome presenterHome = null;
    private int REQUESTCODE_PICK_FILE = 101;
    private EditText editMessage = null;
    RecyclerView recyclerView = null;
    private boolean flagOpen = false;
    private int charOpenAt = 0;
    private int prevLength = 0;
    List<DbModelContact> dbModelContacts = null;

    private CustomAdapter customAdapter = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        preferenceUtils = PreferenceUtils.getINSTANCE(this);
        checkPermission();

        initView();
        init();

        AppExecutor.getINSTANCE().getDiskIO().execute(new Runnable() {
            @Override
            public void run() {

                StringBuilder builder = new StringBuilder();
                for (int i = 0; i < 100; i++) {

                    char c = (char) (65 + i);
                    builder.append("55555555" + i);
                    builder.append(",");
                    builder.append(c + "adam");
                    builder.append(",");
                    builder.append(c + "Adamowy");
                    builder.append(System.getProperty("line.separator"));
                }

                Log.d("WASTE", builder.toString());
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenterHome.dettach();
    }

    private void init() {
        presenterHome = new PresenterHome();
        presenterHome.attach(this);
    }

    private void initView() {
        findViewById(R.id.btnBrowse).setOnClickListener(this);
        recyclerView = findViewById(R.id.listview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        editMessage = (EditText) findViewById(R.id.editMessage);
        bussinessLogic();

    }


    public void bussinessLogic() {

        editMessage.addTextChangedListener(new TextWatcher() {

            Character lastChar;

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                if (s.length() > 0) {
                    lastChar = s.charAt(s.length() - 1);
                } else {
                    lastChar = null;
                }
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {


                if (s.length() <= 0) {
                    return;
                }
                if (s.charAt(s.length() - 1) == '{') {
                    flagOpen = true;
                    charOpenAt = s.length() - 1;
                } else if (s.charAt(s.length() - 1) == '}') {
                    flagOpen = false;
                    charOpenAt = 0;
                } else if (flagOpen && s.length() > charOpenAt) {
                    loadData(s.subSequence(charOpenAt + 1, s.length()).toString());
                } else {
                    flagOpen = false;
                    charOpenAt = 0;
                }
            }


            @Override
            public void afterTextChanged(Editable s) {

            }
        });


    }


    private void loadData(String text) {
        Log.e("WASTE", text);

        presenterHome.loadContacts(DatabaseHouse.getSingleTon(getApplicationContext()), text + "%");
    }

    @Override
    public void onContactLoadSuccess(List<DbModelContact> dbModelContacts) {
        //Contacts Loaded
        Toast.makeText(this, "loaded contacts: " + (dbModelContacts != null ? dbModelContacts.size() : 0), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onContactLoadFail() {

    }

    @Override
    public void onParseCsvSuccess(List<DbModelContact> dbModelContacts) {
        presenterHome.insertContact(DatabaseHouse.getSingleTon(getApplicationContext()), dbModelContacts);
    }

    @Override
    public void onParseCsvFail(String strError) {
        Toast.makeText(this, "Error while parsing", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onSearchContactSuccess(List<DbModelContact> dbModelContacts) {

        this.dbModelContacts = dbModelContacts;

        //if (null != dbModelContacts)
        customAdapter = new CustomAdapter(dbModelContacts);
        customAdapter.setClickListener(HomeActivity.this);
        recyclerView.setAdapter(customAdapter);
    }

    @Override
    public void onSearchContactFail() {

    }


    public void selectFileCode() {
        Intent mediaIntent = new Intent(Intent.ACTION_GET_CONTENT);
        mediaIntent.setType("*/*"); // Set MIME type as per requirement
        startActivityForResult(mediaIntent, REQUESTCODE_PICK_FILE);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {

            Uri uri = data.getData();
            Log.d("WASTE", "Selected File URI= " + uri);
            try {
                Log.d("WASTE", "Selected File Path= " + PathUtil.getPath(this, uri));
            } catch (URISyntaxException e) {
                e.printStackTrace();
            }

            try {
                File file = new File(PathUtil.getPath(this, uri));
                InputStream inStream = new FileInputStream(file);
                presenterHome.parseCSV(DatabaseHouse.getSingleTon(getApplicationContext()),
                        inStream);

            } catch (URISyntaxException e) {
                e.printStackTrace();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

        } else {
            Toast.makeText(this, "Cannot find file", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnBrowse:
                selectFileCode();
                break;
            case R.id.btnSend:

                break;

        }
    }

    @Override
    public void itemClicked(View view, int position) {

        DbModelContact dbModelContact = dbModelContacts.get(position);

        String name = dbModelContact.getName();
        String text = editMessage.getText().toString();

        int lastIndexOpenBrackect = text.lastIndexOf("{");

        String str = text.substring(lastIndexOpenBrackect, text.length());
        name = "{" + name + "};";
        text = text.replace(str, name);

        editMessage.setText("");
        editMessage.setText(text);
        editMessage.setSelection(text.length());


        Toast.makeText(HomeActivity.this, str + " CLicked  " + position + name, Toast.LENGTH_SHORT).show();

    }
}
