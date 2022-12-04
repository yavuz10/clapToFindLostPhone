package com.claptofind.myphone.app;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcelable;

import com.claptofind.myphone.app.databinding.ActivityRingtoneBinding;


public class RingtoneActivity extends HelperActivity {
ActivityRingtoneBinding val;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        val=ActivityRingtoneBinding.inflate(getLayoutInflater());
        setContentView(val.getRoot());

        if(getPreference("ring").equals("YES"))
        {
            val.ringSwitch.setChecked(true);
        }
        else
        {
            val.ringSwitch.setChecked(false);
        }

        val.ringSwitch.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if(isChecked)
            {
                setPreference("ring","YES");
            }
            else
            {
                setPreference("ring","NO");
            }

        });


        val.changeRingtone.setOnClickListener(v -> {


            Intent intent = new Intent("android.intent.action.RINGTONE_PICKER");
            intent.putExtra("android.intent.extra.ringtone.TYPE", 2);
            intent.putExtra("android.intent.extra.ringtone.TITLE", getResources().getString(R.string.select_tone));
            intent.putExtra("android.intent.extra.ringtone.EXISTING_URI", (Parcelable) null);
            startActivityForResult(intent, 5);
        });

        val.flash.setOnClickListener(v -> {
            Intent intent=new Intent(RingtoneActivity.this, FlashLightActivity.class);
            startActivity(intent);

        });
        val.home.setOnClickListener(v -> {
            Intent intent=new Intent(RingtoneActivity.this, MainActivity.class);
            startActivity(intent);

        });
        val.vibration.setOnClickListener(v -> {
            Intent intent=new Intent(RingtoneActivity.this, VibrationActivity.class);
            startActivity(intent);

        });
        val.rate.setOnClickListener(v -> rateus());
        val.share.setOnClickListener(v -> shareApp());
        val.more.setOnClickListener(v -> startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/developer?id=Hallen+Chris+Solutions"))));


    }

    @Override
    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i2 == -1 && i == 5) {
            Uri uri = null;
            if (intent != null) {
                uri = (Uri) intent.getParcelableExtra("android.intent.extra.ringtone.PICKED_URI");
            }
            if (uri != null) {
                setPreference("ringtone_Name",uri.toString());
            }
        }
    }

    @Override
    public void onBackPressed() {
        Intent intent=new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}