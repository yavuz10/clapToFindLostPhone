package com.claptofind.myphone.app;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;


public class NotificationListener extends BroadcastReceiver {
    public void onReceive(Context context, Intent intent) {
        if (intent != null) {
            try {
                if (intent.getAction() == null) {
                    return;
                }
     /*           if (intent.getAction().equals(Constants.ACTION.MUSIC_ACTION)) {
                    if (!SharedPrefManager.getInstance(context).getRingtoneStatus()) {
                        SharedPrefManager.getInstance(context).setRingtoneStatus(true);
                        MainFragment.ringtone_img.setImageResource(R.drawable.phone_music_ic);

                        DetectionServiceForeground.contentView.setImageViewResource(R.id.btn_ring, R.drawable.phone_music_ic);
                        MainFragment.notificationManager.notify(Integer.parseInt(MainFragment.ID), MainFragment.notification);
                        return;
                    }
                    SharedPrefManager.getInstance(context).setRingtoneStatus(false);

                    MainFragment.ringtone_img.setImageResource(R.drawable.ringtoneoff);
                    DetectionServiceForeground.contentView.setImageViewResource(R.id.btn_ring, R.drawable.ringtoneoff);
                    MainFragment.notificationManager.notify(Integer.parseInt(MainFragment.ID), MainFragment.notification);
                }
                else if (intent.getAction().equals(Constants.ACTION.FLASH_ACTION)) {
                    if (!SharedPrefManager.getInstance(context).getFlashStatus()) {
                        SharedPrefManager.getInstance(context).setFlashStatus(true);

                        MainFragment.torch_img.setImageResource(R.drawable.torch);
                        DetectionServiceForeground.contentView.setImageViewResource(R.id.btn_flash, R.drawable.torch);
                        MainFragment.notificationManager.notify(Integer.parseInt(MainFragment.ID), MainFragment.notification);
                        return;
                    }
                    SharedPrefManager.getInstance(context).setFlashStatus(false);

                    MainFragment.torch_img.setImageResource(R.drawable.torchoff);
                    DetectionServiceForeground.contentView.setImageViewResource(R.id.btn_flash, R.drawable.torchoff);
                    MainFragment.notificationManager.notify(Integer.parseInt(MainFragment.ID), MainFragment.notification);
                }
                else if (intent.getAction().equals(Constants.ACTION.VIBE_ACTION)) {
                    if (!SharedPrefManager.getInstance(context).getVibrationStatus()) {
                        SharedPrefManager.getInstance(context).setViberationStatus(true);

                        MainFragment.vibrate_img.setImageResource(R.drawable.vibrate);
                        DetectionServiceForeground.contentView.setImageViewResource(R.id.btn_vib, R.drawable.vibrate);
                        MainFragment.notificationManager.notify(Integer.parseInt(MainFragment.ID), MainFragment.notification);
                        return;
                    }
                    SharedPrefManager.getInstance(context).setViberationStatus(false);

                    MainFragment.vibrate_img.setImageResource(R.drawable.vibrateoff);
                    DetectionServiceForeground.contentView.setImageViewResource(R.id.btn_vib, R.drawable.vibrateoff);
                    MainFragment.notificationManager.notify(Integer.parseInt(MainFragment.ID), MainFragment.notification);
                }
                else if (intent.getAction().equals(Constants.ACTION.SERVICE_CLOSE)) {
                    SharedPrefManager.getInstance(context).setDetectStatus(false);
                   MainFragment.toDeActivate();
                    Intent intent2 = new Intent(context, DetectionServiceForeground.class);
                    if (Build.VERSION.SDK_INT >= 26) {
                        context.stopService(intent2);
                    } else {
                        context.stopService(intent2);
                    }
                }*/
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
