package com.claptofind.myphone.app;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import com.claptofind.myphone.app.databinding.ActivityVibrarionBinding;


public class VibrationActivity extends HelperActivity {
ActivityVibrarionBinding val;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // setContentView(R.layout.activity_vibrarion);
    val=ActivityVibrarionBinding.inflate(getLayoutInflater());
    setContentView(val.getRoot());


        if(getPreference("vibration").equals("YES"))
        {
            val.vibrationSwitch.setChecked(true);
        }
        else
        {
            val.vibrationSwitch.setChecked(false);
        }

        if(getPreference("vibration_value").equals("slow"))
        {
            val.slow.setChecked(true);
            val.medium.setChecked(false);
            val.fast.setChecked(false);
        }
        else if(getPreference("vibration_value").equals("medium"))
        {

            val.slow.setChecked(false);
            val.medium.setChecked(true);
            val.fast.setChecked(false);
        }
        else
        {

            val.slow.setChecked(false);
            val.medium.setChecked(false);
            val.fast.setChecked(true);
        }



        val.vibrationSwitch.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if(isChecked)
            {
                setPreference("vibration","YES");
            }
            else
            {
                setPreference("vibration","NO");
            }
        });


        val.radioGroup.setOnCheckedChangeListener((group, checkedId) -> {
            switch(checkedId){
                case R.id.slow:
                    setPreference("vibration_value","slow");
                    // do operations specific to this selection
                    break;
                case R.id.medium:

                    setPreference("vibration_value","medium");
                    // do operations specific to this selection
                    break;
                case R.id.fast:
                    setPreference("vibration_value","fast");
                    // do operations specific to this selection
                    break;
            }
        });


        val.flash.setOnClickListener(v -> {
            Intent intent=new Intent(VibrationActivity.this, FlashLightActivity.class);
            startActivity(intent);

        });
        val.home.setOnClickListener(v -> {
            Intent intent=new Intent(VibrationActivity.this, MainActivity.class);
            startActivity(intent);

        });
        val.ringtone.setOnClickListener(v -> {
            Intent intent=new Intent(VibrationActivity.this, RingtoneActivity.class);
            startActivity(intent);
        });
        val.rate.setOnClickListener(v -> rateus());
        val.share.setOnClickListener(v -> shareApp());
        val.more.setOnClickListener(v -> startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/developer?id=Hallen+Chris+Solutions"))));

    }

    @Override
    public void onBackPressed() {
        Intent intent=new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}