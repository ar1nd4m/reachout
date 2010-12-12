package info.arindamsharma.reachout.client;

import info.arindamsharma.reachout.client.fb.FbCallback;
import info.arindamsharma.reachout.client.fb.JSLoader;
import info.arindamsharma.reachout.client.fb.LoginStatus;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.maps.client.Maps;

public class Reachout_trial implements EntryPoint {
  
  public static boolean mapsLoaded = false;
  /**
   * This is the entry point method.
   */
  public void onModuleLoad() {
    
    JSLoader.setCallback(new FbCallback<Boolean>() {
      @Override
      public void onReturn(Boolean result) {
        onFacebookLoad();
      }
    });
    JSLoader.addFbScript();
    
    Maps.loadMapsApi("", "2", false, new Runnable() {
      @Override
      public void run() {
        mapsLoaded = true;
      }
    });
  }

  public void onFacebookLoad() {
    Logger.log("fb script loaded complete");
    LoginStatus.getLoginStatusNative();
  }
}
