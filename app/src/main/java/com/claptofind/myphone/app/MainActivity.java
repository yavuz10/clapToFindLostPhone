package com.claptofind.myphone.app;

import android.annotation.TargetApi;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.PowerManager;
import android.provider.Settings;
import android.util.Log;
import android.widget.LinearLayout;

import androidx.core.content.ContextCompat;
import com.claptofind.myphone.app.databinding.ActivityMainBinding;
import com.facebook.ads.Ad;
import com.facebook.ads.AdError;
import com.facebook.ads.AdSize;
import com.facebook.ads.AdView;
import com.facebook.ads.AudienceNetworkAds;
import com.facebook.ads.InterstitialAd;
import com.facebook.ads.InterstitialAdListener;


import java.util.ArrayList;
import java.util.List;

public class MainActivity extends HelperActivity {
    public Intent intent;
    ActivityMainBinding val;
    List<Intent> POWERMANAGER_INTENTS = new ArrayList<Intent>();
    public static int ACTION_MANAGE_OVERLAY_PERMISSION_REQUEST_CODE = 5469;
    public static String ID = "001";
    public static Notification notification;
    public static NotificationManager notificationManager;

    String b, b2;

    private InterstitialAd interstitialAd;

    public AdView adView;
    int Adsflag = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        val = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(val.getRoot());

        AudienceNetworkAds.initialize(this);


        interstitialAd = new InterstitialAd(this, "751699785486707_751700198819999");
        //interstitialAd = new InterstitialAd(this, "TEST_AD_TYPE#670865427186006_670866143852601");
        // Create listeners for the Interstitial Ad
        InterstitialAdListener interstitialAdListener = new InterstitialAdListener() {
            @Override
            public void onInterstitialDisplayed(Ad ad) {
                // Interstitial ad displayed callback
                Log.e("TAG", "Interstitial ad displayed.");
            }

            @Override
            public void onInterstitialDismissed(Ad ad) {
                // Interstitial dismissed callback
                Log.e("TAG", "Interstitial ad dismissed.");
                if (Adsflag == 1) {
                    Intent intent = new Intent(MainActivity.this, FlashLightActivity.class);
                    startActivity(intent);
                } else if (Adsflag == 2) {
                    Intent intent = new Intent(MainActivity.this, VibrationActivity.class);
                    startActivity(intent);
                }
                else if (Adsflag == 3) {

                    Intent intent = new Intent(MainActivity.this, RingtoneActivity.class);
                    startActivity(intent);

                }
                else if (Adsflag == 4) {
                    if (getPreference("startButton").equals("NO")) {
                        val.startText.setText("Stop");
                        val.startText.setTextColor(ContextCompat.getColor(MainActivity.this, R.color.main_orange));
                        val.startCardBack.setCardBackgroundColor(ContextCompat.getColor(MainActivity.this, R.color.main_orange));
                        val.startCardView.setCardBackgroundColor(ContextCompat.getColor(MainActivity.this, R.color.white));
                        setPreference("startButton", "YES");

                        if (Build.VERSION.SDK_INT >= 26) {
                            startForegroundService(intent);

                        } else {
                            startService(intent);
                        }

                    } else {
                        val.startText.setText("Start");
                        val.startText.setTextColor(ContextCompat.getColor(MainActivity.this, R.color.white));
                        val.startCardBack.setCardBackgroundColor(ContextCompat.getColor(MainActivity.this, R.color.white));
                        val.startCardView.setCardBackgroundColor(ContextCompat.getColor(MainActivity.this, R.color.main_orange));
                        setPreference("startButton", "NO");
                        stopService(intent);
                    }
                }
            }

            @Override
            public void onError(Ad ad, AdError adError) {
                // Ad error callback
                Log.e("TAG", "Interstitial ad failed to load: " + adError.getErrorMessage());
            }

            @Override
            public void onAdLoaded(Ad ad) {
                // Interstitial ad is loaded and ready to be displayed
                Log.d("TAG", "Interstitial ad is loaded and ready to be displayed!");
                // Show the ad
            }

            @Override
            public void onAdClicked(Ad ad) {
                // Ad clicked callback
                Log.d("TAG", "Interstitial ad clicked!");
            }

            @Override
            public void onLoggingImpression(Ad ad) {
                // Ad impression logged callback
                Log.d("TAG", "Interstitial ad impression logged!");
            }
        };

        // For auto play video ads, it's recommended to load the ad
        // at least 30 seconds before it is shown
        interstitialAd.loadAd(
                interstitialAd.buildLoadAdConfig()
                        .withAdListener(interstitialAdListener)
                        .build());


        createChannel();
        POWERMANAGER_INTENTS.add(new Intent().setComponent(
                new ComponentName(
                        "com.miui.securitycenter",
                        "com.miui.permcenter.autostart.AutoStartManagementActivity"
                )
        ));
        POWERMANAGER_INTENTS.add(new Intent().setComponent(
                new ComponentName(
                        "com.letv.android.letvsafe",
                        "com.letv.android.letvsafe.AutobootManageActivity"
                )
        ));
        POWERMANAGER_INTENTS.add(new Intent().setComponent(
                new ComponentName(
                        "com.huawei.systemmanager",
                        "com.huawei.systemmanager.optimize.process.ProtectActivity"
                )
        ));
        POWERMANAGER_INTENTS.add(new Intent().setComponent(
                new ComponentName(
                        "com.huawei.systemmanager",
                        "com.huawei.systemmanager.startupmgr.ui.StartupNormalAppListActivity"
                )
        ));
        POWERMANAGER_INTENTS.add(new Intent().setComponent(
                new ComponentName(
                        "com.huawei.systemmanager",
                        "com.huawei.systemmanager.appcontrol.activity.StartupAppControlActivity"
                )
        ));
        POWERMANAGER_INTENTS.add(new Intent().setComponent(
                new ComponentName(
                        "com.coloros.safecenter",
                        "com.coloros.safecenter.permission.startup.StartupAppListActivity"
                )
        ));

        POWERMANAGER_INTENTS.add(new Intent().setComponent(
                new ComponentName(
                        "com.coloros.safecenter",
                        "com.coloros.safecenter.startupapp.StartupAppListActivity"
                )
        ));
        POWERMANAGER_INTENTS.add(new Intent().setComponent(
                new ComponentName(
                        "com.oppo.safe",
                        "com.oppo.safe.permission.startup.StartupAppListActivity"
                )
        ));
        POWERMANAGER_INTENTS.add(new Intent().setComponent(
                new ComponentName(
                        "com.iqoo.secure",
                        "com.iqoo.secure.ui.phoneoptimize.AddWhiteListActivity"
                )
        ));
        POWERMANAGER_INTENTS.add(new Intent().setComponent(
                new ComponentName(
                        "com.iqoo.secure",
                        "com.iqoo.secure.ui.phoneoptimize.BgStartUpManager"
                )
        ));
        POWERMANAGER_INTENTS.add(new Intent().setComponent(
                new ComponentName(
                        "com.vivo.permissionmanager",
                        "com.vivo.permissionmanager.activity.BgStartUpManagerActivity"
                )
        ));
        POWERMANAGER_INTENTS.add(new Intent().setComponent(
                new ComponentName(
                        "com.samsung.android.lool",
                        "com.samsung.android.sm.ui.battery.BatteryActivity"
                )
        ));
        POWERMANAGER_INTENTS.add(new Intent().setComponent(
                new ComponentName(
                        "com.htc.pitroad",
                        "com.htc.pitroad.landingpage.activity.LandingPageActivity"
                )
        ));
        POWERMANAGER_INTENTS.add(new Intent().setComponent(
                new ComponentName(
                        "com.asus.mobilemanager",
                        "com.asus.mobilemanager.MainActivity"
                )
        ));
        POWERMANAGER_INTENTS.add(new Intent().setComponent(
                new ComponentName(
                        "com.transsion.phonemanager",
                        "com.itel.autobootmanager.activity.AutoBootMgrActivity"
                )
        ));

        b = getPreference("battery");

        if (!b.equals("NO")) {
            checkPermission();
        }

        if (!Build.BRAND.equalsIgnoreCase("oppo")) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                PowerManager powerManager = (PowerManager) getSystemService(POWER_SERVICE);

                b2 = getPreference("switchBattery");


                if (!b2.equals("YES")) {

                    if (!powerManager.isIgnoringBatteryOptimizations(getPackageName())) {


                        for (Intent intent : POWERMANAGER_INTENTS) {
                            if (getPackageManager().resolveActivity(
                                    intent, PackageManager.MATCH_DEFAULT_ONLY
                            ) != null
                            ) {
                                startActivity(intent);
                                setPreference("switchBattery", "YES");
                                break;

                            }

                        }
                    }
                }

            }

        }
        intent = new Intent(this, DetectionServiceForeground.class);

        if (getPreference("startButton").equals("YES")) {
            val.startText.setText("Stop");
            val.startText.setTextColor(ContextCompat.getColor(this, R.color.main_orange));
            val.startCardBack.setCardBackgroundColor(ContextCompat.getColor(this, R.color.main_orange));
            val.startCardView.setCardBackgroundColor(ContextCompat.getColor(this, R.color.white));

        } else {
            val.startText.setText("Start");
            val.startText.setTextColor(ContextCompat.getColor(this, R.color.white));
            val.startCardBack.setCardBackgroundColor(ContextCompat.getColor(this, R.color.white));
            val.startCardView.setCardBackgroundColor(ContextCompat.getColor(this, R.color.main_orange));
            setPreference("startButton", "NO");
        }


        val.flash.setOnClickListener(v -> {

                Intent intent = new Intent(MainActivity.this, FlashLightActivity.class);
                startActivity(intent);




        });
        val.vibration.setOnClickListener(v -> {


                Intent intent = new Intent(MainActivity.this, VibrationActivity.class);
                startActivity(intent);



        });
        val.ringtone.setOnClickListener(v -> {



                Intent intent = new Intent(MainActivity.this, RingtoneActivity.class);
                startActivity(intent);



        });
        /*val.rate.setOnClickListener(v -> rateus());
        val.share.setOnClickListener(v -> shareApp());
        val.more.setOnClickListener(v -> startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/developer?id=AppZone+Labs"))));
*/
        val.startText.setOnClickListener(v -> {


                if (getPreference("startButton").equals("NO")) {
                    val.startText.setText("Stop");
                    val.startText.setTextColor(ContextCompat.getColor(this, R.color.main_orange));
                    val.startCardBack.setCardBackgroundColor(ContextCompat.getColor(this, R.color.main_orange));
                    val.startCardView.setCardBackgroundColor(ContextCompat.getColor(this, R.color.white));
                    setPreference("startButton", "YES");

                    if (Build.VERSION.SDK_INT >= 26) {
                        startForegroundService(intent);

                    } else {
                        startService(intent);
                    }

                } else {
                    val.startText.setText("Start");
                    val.startText.setTextColor(ContextCompat.getColor(this, R.color.white));
                    val.startCardBack.setCardBackgroundColor(ContextCompat.getColor(this, R.color.white));
                    val.startCardView.setCardBackgroundColor(ContextCompat.getColor(this, R.color.main_orange));
                    setPreference("startButton", "NO");
                    stopService(intent);
                }



        });


    }

    @Override
    protected void onResume() {
        super.onResume();
        interstitialAd = new InterstitialAd(this, "751699785486707_751700198819999");
        //interstitialAd = new InterstitialAd(this, "IMG_16_9_APP_INSTALL#YOUR_PLACEMENT_ID");
        // Create listeners for the Interstitial Ad
        InterstitialAdListener interstitialAdListener = new InterstitialAdListener() {
            @Override
            public void onInterstitialDisplayed(Ad ad) {
                // Interstitial ad displayed callback
                Log.e("TAG", "Interstitial ad displayed.");
            }

            @Override
            public void onInterstitialDismissed(Ad ad) {
                // Interstitial dismissed callback
                Log.e("TAG", "Interstitial ad dismissed.");
                if (Adsflag == 1) {
                    Intent intent = new Intent(MainActivity.this, FlashLightActivity.class);
                    startActivity(intent);
                } else if (Adsflag == 2) {
                    Intent intent = new Intent(MainActivity.this, VibrationActivity.class);
                    startActivity(intent);
                }
                else if (Adsflag == 3) {

                    Intent intent = new Intent(MainActivity.this, RingtoneActivity.class);
                    startActivity(intent);

                }
                else if (Adsflag == 4) {
                    if (getPreference("startButton").equals("NO")) {
                        val.startText.setText("Stop");
                        val.startText.setTextColor(ContextCompat.getColor(MainActivity.this, R.color.main_orange));
                        val.startCardBack.setCardBackgroundColor(ContextCompat.getColor(MainActivity.this, R.color.main_orange));
                        val.startCardView.setCardBackgroundColor(ContextCompat.getColor(MainActivity.this, R.color.white));
                        setPreference("startButton", "YES");

                        if (Build.VERSION.SDK_INT >= 26) {
                            startForegroundService(intent);

                        } else {
                            startService(intent);
                        }

                    } else {
                        val.startText.setText("Start");
                        val.startText.setTextColor(ContextCompat.getColor(MainActivity.this, R.color.white));
                        val.startCardBack.setCardBackgroundColor(ContextCompat.getColor(MainActivity.this, R.color.white));
                        val.startCardView.setCardBackgroundColor(ContextCompat.getColor(MainActivity.this, R.color.main_orange));
                        setPreference("startButton", "NO");
                        stopService(intent);
                    }
                }

            }

            @Override
            public void onError(Ad ad, AdError adError) {
                // Ad error callback
                Log.e("TAG", "Interstitial ad failed to load: " + adError.getErrorMessage());
            }

            @Override
            public void onAdLoaded(Ad ad) {
                // Interstitial ad is loaded and ready to be displayed
                Log.d("TAG", "Interstitial ad is loaded and ready to be displayed!");
                // Show the ad
            }

            @Override
            public void onAdClicked(Ad ad) {
                // Ad clicked callback
                Log.d("TAG", "Interstitial ad clicked!");
            }

            @Override
            public void onLoggingImpression(Ad ad) {
                // Ad impression logged callback
                Log.d("TAG", "Interstitial ad impression logged!");
            }
        };

        // For auto play video ads, it's recommended to load the ad
        // at least 30 seconds before it is shown
        interstitialAd.loadAd(
                interstitialAd.buildLoadAdConfig()
                        .withAdListener(interstitialAdListener)
                        .build());
    }


    private void createChannel() {
        if (Build.VERSION.SDK_INT >= 26) {

            notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
            NotificationChannel notificationChannel = new NotificationChannel(ID, "Test Channel 1", NotificationManager.IMPORTANCE_LOW);
            notificationChannel.setDescription("xyz");
            notificationChannel.enableLights(true);
            notificationChannel.setShowBadge(true);
            notificationManager.createNotificationChannel(notificationChannel);
            return;
        }
        notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
    }

    public void checkPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            if (!Settings.canDrawOverlays(this)) {
                Intent intent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION,
                        Uri.parse("package:" + getPackageName()));
                startActivityForResult(intent, ACTION_MANAGE_OVERLAY_PERMISSION_REQUEST_CODE);
            }
        }
    }

    @TargetApi(Build.VERSION_CODES.Q)
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == ACTION_MANAGE_OVERLAY_PERMISSION_REQUEST_CODE) {
            if (!Settings.canDrawOverlays(this)) {
                // You don't have permission
                checkPermission();
            } else {
                SharedPreferences.Editor editor = getSharedPreferences("save", MODE_PRIVATE).edit();
                editor.putBoolean("switch", true);
                editor.apply();
                setPreference("battery", "NO");
            }
        }

    }

    @Override
    public void onBackPressed() {
        BackPressed();
    }

}