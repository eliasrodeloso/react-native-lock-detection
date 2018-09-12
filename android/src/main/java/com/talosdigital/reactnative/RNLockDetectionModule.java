
package com.talosdigital.reactnative;

import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.Callback;
import com.facebook.react.modules.core.DeviceEventManagerModule;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.bridge.Arguments;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.app.KeyguardManager;

public class RNLockDetectionModule extends ReactContextBaseJavaModule {

  private final ReactApplicationContext reactContext;

  public RNLockDetectionModule(ReactApplicationContext reactContext) {
    super(reactContext);
    this.reactContext = reactContext;
    registerBroadcastReceiver();
  }

  @Override
  public String getName() {
    return "RNLockDetection";
  }

  private void registerBroadcastReceiver() {
    final IntentFilter theFilter = new IntentFilter();
    /** System Defined Broadcast */
    theFilter.addAction(Intent.ACTION_SCREEN_ON);
    theFilter.addAction(Intent.ACTION_SCREEN_OFF);

    BroadcastReceiver screenOnOffReceiver = new BroadcastReceiver() {
      @Override
      public void onReceive(Context context, Intent intent) {
        String strAction = intent.getAction();

        KeyguardManager myKM = (KeyguardManager) context.getSystemService(Context.KEYGUARD_SERVICE);

        if (strAction.equals(Intent.ACTION_SCREEN_OFF) || strAction.equals(Intent.ACTION_SCREEN_ON)) {
          if (myKM.inKeyguardRestrictedInputMode()) {
            sendEvent("phoneLocked", "LOCKED");
          } else {
            sendEvent("phoneUnlocked", "UNLOCKED");
          }
        }
      }
    };
    reactContext.registerReceiver(screenOnOffReceiver, theFilter);
  }

  private void sendEvent(String eventName, String newPhoneStatus) {
    WritableMap payload = Arguments.createMap();
    payload.putString("newPhoneStatus", newPhoneStatus);
    this.reactContext.getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter.class).emit(eventName, payload);
  }

}
