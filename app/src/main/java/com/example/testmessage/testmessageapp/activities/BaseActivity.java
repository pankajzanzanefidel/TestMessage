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

    private static final int MY_PERMISSIONS_REQUEST_SEND_SMS = 0;
    private static final int MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE = 1;
    private static final int MY_PERMISSIONS_REQUEST_WRITE_EXTERNAL_STORAGE = 2;
    PreferenceUtils preferenceUtils = null;

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
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.SEND_SMS)
                != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.SEND_SMS)) {
            } else {
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.SEND_SMS},
                        MY_PERMISSIONS_REQUEST_SEND_SMS);
            }
        } else {
           /* Toast.makeText(getApplicationContext(), "Already allowed",
                    Toast.LENGTH_LONG).show();*/
        }
////
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {


            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.READ_EXTERNAL_STORAGE)) {

            } else {
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                        MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE);
            }
        } else {
          /*  Toast.makeText(getApplicationContext(), "Already allowed",
                    Toast.LENGTH_LONG).show();*/
        }


        ///
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
            } else {
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                        MY_PERMISSIONS_REQUEST_WRITE_EXTERNAL_STORAGE);
            }
        } else {
          /*  Toast.makeText(getApplicationContext(), "Already allowed",
                    Toast.LENGTH_LONG).show();*/
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {


        if (grantResults.length > 0
                && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            preferenceUtils.putBoolean(Constants.SHARED_PREFERNCE.PREFERENCE_SMS_PERRMISION, true);

        } else {
            preferenceUtils.putBoolean(Constants.SHARED_PREFERNCE.PREFERENCE_SMS_PERRMISION, false);
            return;
        }

       /* switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_SEND_SMS: {
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(getApplicationContext(), " permissin granted",
                            Toast.LENGTH_LONG).show();
                    preferenceUtils.putBoolean(Constants.SHARED_PREFERNCE.PREFERENCE_SMS_PERRMISION, true);

                } else {
                    Toast.makeText(getApplicationContext(),
                            "permission nOt granted", Toast.LENGTH_LONG).show();
                    preferenceUtils.putBoolean(Constants.SHARED_PREFERNCE.PREFERENCE_SMS_PERRMISION, false);
                    return;
                }
            }
            case MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE: {
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(getApplicationContext(), " permissin granted",
                            Toast.LENGTH_LONG).show();
                    preferenceUtils.putBoolean(Constants.SHARED_PREFERNCE.PREFERENCE_SMS_PERRMISION, true);

                } else {
                    Toast.makeText(getApplicationContext(),
                            "permission nOt granted", Toast.LENGTH_LONG).show();
                    preferenceUtils.putBoolean(Constants.SHARED_PREFERNCE.PREFERENCE_SMS_PERRMISION, false);
                    return;
                }
            }
        }
*/

    }
}
