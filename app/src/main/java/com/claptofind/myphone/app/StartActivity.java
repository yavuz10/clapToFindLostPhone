package com.claptofind.myphone.app;

import android.Manifest;
import android.app.ActivityManager;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.claptofind.myphone.app.databinding.ActivityStartBinding;


public class StartActivity extends HelperActivity {
    ActivityStartBinding val;
    int PERMISSIONGET = 1;
    String[] GETPERMISSIONS = {
            Manifest.permission.RECORD_AUDIO,
            Manifest.permission.CAMERA
    };
    int valueOpenActivity=0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        val=ActivityStartBinding.inflate(getLayoutInflater());
        setContentView(val.getRoot());


        boolean b= isMyServiceRunning(DetectionServiceForeground.class);

        if(!b) {
            setPreference("startButton","NO");
        }

        if (!checkPermissions(getApplicationContext(), GETPERMISSIONS)) {
            ActivityCompat.requestPermissions(StartActivity.this, GETPERMISSIONS, PERMISSIONGET);
        }



    }
    public static boolean checkPermissions(Context context, String... permissions) {
        if (context != null && permissions != null) {
            for (String permission : permissions) {
                if (ActivityCompat.checkSelfPermission(context, permission) != PackageManager.PERMISSION_GRANTED) {
                    return false;
                }
            }
        }
        return true;
    }

    public void startCLick(View view) {


        if ( ContextCompat.checkSelfPermission(StartActivity.this, Manifest.permission.RECORD_AUDIO )
                == PackageManager.PERMISSION_GRANTED
                &&
                ContextCompat.checkSelfPermission(StartActivity.this, Manifest.permission.CAMERA )
                        == PackageManager.PERMISSION_GRANTED
        ) {

            startActivity(new Intent(StartActivity.this, MainActivity.class));
            finish();

        }
        else if ( ContextCompat.checkSelfPermission(StartActivity.this, Manifest.permission.RECORD_AUDIO )
                == PackageManager.PERMISSION_DENIED
                ||
                ContextCompat.checkSelfPermission(StartActivity.this, Manifest.permission.CAMERA )
                        == PackageManager.PERMISSION_DENIED
        )
        {
            new AlertDialog.Builder(StartActivity.this).setMessage(R.string.permissionenabled).setPositiveButton(R.string.go_to_setting, (dialog, which) -> {
                // navigate to settings
                Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS,
                        Uri.parse("package:" + getPackageName()));
                intent.addCategory(Intent.CATEGORY_DEFAULT);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }).setNegativeButton(R.string.goback, (DialogInterface.OnClickListener) (dialog, which) -> {
                // leave?
                dialog.dismiss();
            }).show();


            // Do something ...
        }
        else
        {
            if (!checkPermissions(getApplicationContext(), GETPERMISSIONS)) {
                ActivityCompat.requestPermissions(StartActivity.this, GETPERMISSIONS, PERMISSIONGET);

            }
        }


    }

    private boolean isMyServiceRunning(Class<?> serviceClass) {
        ActivityManager manager = (ActivityManager) getSystemService(ACTIVITY_SERVICE);
        for (ActivityManager.RunningServiceInfo service : manager.getRunningServices(Integer.MAX_VALUE)) {
            if (serviceClass.getName().equals(service.service.getClassName())) {
                return true;
            }
        }
        return false;
    }

}