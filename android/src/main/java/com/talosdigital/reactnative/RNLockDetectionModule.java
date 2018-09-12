
package com.talosdigital.reactnative;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.Callback;

import com.talosdigital.reactnative.ScreenReceiver;
import android.content.BroadcastReceiver;

public class RNLockDetectionModule extends ReactContextBaseJavaModule {

  private final ReactApplicationContext reactContext;

  public RNLockDetectionModule(ReactApplicationContext reactContext) {
    super(reactContext);
    this.reactContext = reactContext;
  }

  @Override
  public String getName() {
    return "RNLockDetection";
  }

  /**
   * PUBLIC REACT API
   *
   * isAvailable() Returns true if the fingerprint reader can be used
   */
  @ReactMethod
  public void nowIsLocked(final Promise promise) {
    try {
      BroadcastReceiver myReceiver = new ScreenReceiver();
      promise.resolve(myReceiver.onReceive());
    } catch (Exception ex) {
      promise.reject("ERR_UNEXPECTED_EXCEPTION", ex);
    }
  }
}