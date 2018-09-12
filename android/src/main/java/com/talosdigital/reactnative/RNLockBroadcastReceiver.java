package com.talosdigital.reactnative;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class ScreenReceiver extends BroadcastReceiver {

    @Override
    public String onReceive(Context context, Intent intent) {
        if (intent.getAction().equals(Intent.ACTION_SCREEN_OFF)) {
            return "PHONE LOCKED";
        } else if (intent.getAction().equals(Intent.ACTION_SCREEN_ON)) {
            return "PHONE UNLOCKED";
        }
    }

}