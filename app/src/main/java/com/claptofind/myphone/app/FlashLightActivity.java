package com.claptofind.myphone.app;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import com.claptofind.myphone.app.databinding.ActivityFlashLightBinding;


public class FlashLightActivity extends HelperActivity {
ActivityFlashLightBinding val;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        val=ActivityFlashLightBinding.inflate(getLayoutInflater());
        setContentView(val.getRoot());

        if(getPreference("flash").equals("YES"))
        {
            val.flashSwitch.setChecked(true);
        }
        else
        {
            val.flashSwitch.setChecked(false);
        }

        if(getPreference("flash_value").equals("slow"))
        {
            val.slow.setChecked(true);
            val.medium.setChecked(false);
            val.fast.setChecked(false);
        }
        else if(getPreference("flash_value").equals("medium"))
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

        val.flashSwitch.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if(isChecked)
            {
                setPreference("flash","YES");
            }
            else
            {
                setPreference("flash","NO");
            }
        });


        val.radioGroup.setOnCheckedChangeListener((group, checkedId) -> {
            switch(checkedId){
                case R.id.slow:
                    setPreference("flash_value","slow");
                    // do operations specific to this selection
                    break;
                case R.id.medium:

                    setPreference("flash_value","medium");
                    // do operations specific to this selection
                    break;
                case R.id.fast:
                    setPreference("flash_value","fast");
                    // do operations specific to this selection
                    break;
            }
        });


        val.home.setOnClickListener(v -> {
            Intent intent=new Intent(FlashLightActivity.this, MainActivity.class);
            startActivity(intent);

        });
        val.vibration.setOnClickListener(v -> {
            Intent intent=new Intent(FlashLightActivity.this, VibrationActivity.class);
            startActivity(intent);

        });
        val.ringtone.setOnClickListener(v -> {
            Intent intent=new Intent(FlashLightActivity.this,RingtoneActivity.class);
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