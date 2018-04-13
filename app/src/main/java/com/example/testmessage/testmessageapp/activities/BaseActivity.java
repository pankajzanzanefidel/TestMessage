package com.example.testmessage.testmessageapp.activities;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.example.testmessage.testmessageapp.R;
import com.example.testmessage.testmessageapp.contractor.IView;
import com.example.testmessage.testmessageapp.helper.Constants;
import com.example.testmessage.testmessageapp.helper.PreferenceUtils;

public class BaseActivity extends AppCompatActivity implements IView {

    PreferenceUtils preferenceUtils = null;

    private String[] strRequiredPermissions = new String[]{
            Manifest.permission.SEND_SMS,
            android.Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.VIBRATE};

    private ProgressDialog progressDialog = null;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        preferenceUtils = PreferenceUtils.getINSTANCE(this);
    }

    protected void showSpinner() {
        if (progressDialog == null)
            progressDialog = ProgressDialog.show(this, "", getString(R.string.please_wait), true);
        else if (!progressDialog.isShowing())
            progressDialog.show();
    }

    protected void showSpinner(String title, String message) {
        if (progressDialog == null)
            progressDialog = ProgressDialog.show(this, title, message, true);
        else if (!progressDialog.isShowing())
            progressDialog.show();
    }

    protected void hideSpinner() {
        if (progressDialog == null)
            return;

        if (progressDialog.isShowing()) {
            progressDialog.dismiss();
            progressDialog = null;
        }

    }

    @Override
    public void toggleProgressIndigator(boolean show) {

        if (show) {
            showSpinner();
        } else {
            hideSpinner();
        }
    }

    @Override
    public void toggleProgressIndigator(boolean show, String title, String message) {
        if (show) {
            showSpinner(title, message);
        } else {
            hideSpinner();
        }
    }


    public void checkPermission() {

        ActivityCompat.requestPermissions(this,
                strRequiredPermissions,
                Constants.REQUESTCODE.REQUEST_ALL_PERMISSIONS);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {

        if (requestCode == Constants.REQUESTCODE.REQUEST_ALL_PERMISSIONS && grantResults.length > 0) {

            for(int i=0;i<grantResults.length;i++){

                if(grantResults[i] == PackageManager.PERMISSION_GRANTED){
                    //Should show rationale here and ask permission again
                }
            }
        }

    }
}
