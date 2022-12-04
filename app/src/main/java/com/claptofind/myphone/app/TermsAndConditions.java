package com.claptofind.myphone.app;


import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.claptofind.myphone.app.databinding.ActivityTermsAndConditionsBinding;


public class TermsAndConditions extends HelperActivity {
ActivityTermsAndConditionsBinding val;
String b;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        b=getPreference("terms_value");
        Log.d("TAG", "onCreate: ");

        if(!b.equals("NO"))
        {
            val=ActivityTermsAndConditionsBinding.inflate(getLayoutInflater());
            setContentView(val.getRoot());
        }
        else
        {
            Intent intent=new Intent(TermsAndConditions.this,StartActivity.class);
            startActivity(intent);
        }


    }

    public void Accept(View view) {

        setPreference("startButton","NO");
        setPreference("ring","YES");
        setPreference("vibration","YES");
        setPreference("flash","YES");
        setPreference("flash_value","medium");
        setPreference("vibration_value","medium");

        setPreference("terms_value","NO");
        Intent intent=new Intent(TermsAndConditions.this,StartActivity.class);
        startActivity(intent);
    }
}