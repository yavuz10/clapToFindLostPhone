package com.claptofind.myphone.app;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.view.Window;
import android.widget.Button;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Objects;

public class HelperActivity extends AppCompatActivity {
    public void DeniedPermissionBox()
    {
        new AlertDialog.Builder(this).setMessage(R.string.chk_permission_dialog).setPositiveButton(R.string.go_to_setting, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // navigate to settings
                Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS,
                        Uri.parse("package:" + getPackageName()));
                intent.addCategory(Intent.CATEGORY_DEFAULT);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        }).setNegativeButton(R.string.exit, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // leave?
                dialog.dismiss();
            }
        }).show();


    }
    private static final String PREFS_NAME = "PREFS";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_helper);
    }
    public void rateus() {

        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=" + getPackageName())));
    }

    public void shareApp()
    {
        Intent shareIntent = new Intent(Intent.ACTION_SEND);
        shareIntent.setType("text/plain");
        shareIntent.putExtra(Intent.EXTRA_SUBJECT, R.string.app_name);
        String shareMessage = "\nLet me recommend you this application\n\n";
        shareMessage = shareMessage + "https://play.google.com/store/apps/details?id=" + getPackageName() + "\n\n";
        shareIntent.putExtra(Intent.EXTRA_TEXT, shareMessage);
        startActivity(Intent.createChooser(shareIntent, "Choose one"));

    }

    public  boolean setPreference( String key, String value) {
        SharedPreferences settings = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        SharedPreferences.Editor editor = settings.edit();
        editor.putString(key, value);
        return editor.commit();
    }

    public String getPreference(String key) {
        SharedPreferences settings = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        return settings.getString(key, "true");
    }


    public void BackPressed() {


        final Dialog secondalert = new Dialog(this);
        secondalert.requestWindowFeature(Window.FEATURE_NO_TITLE);
        secondalert.setContentView(R.layout.exit_layout);
        secondalert.setCancelable(false);
        Button yesButton = secondalert.findViewById(R.id.yesButton);
        Button noButton = secondalert.findViewById(R.id.noButton);

        yesButton.setOnClickListener(v -> {
            secondalert.dismiss();
            finishAffinity();
        });

        noButton.setOnClickListener(v -> secondalert.dismiss());
        secondalert.show();
        Objects.requireNonNull(secondalert.getWindow()).setLayout(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
    }

}