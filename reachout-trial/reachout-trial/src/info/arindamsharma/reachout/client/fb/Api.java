package info.arindamsharma.reachout.client.fb;

import info.arindamsharma.reachout.client.Logger;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.json.client.JSONObject;

public class Api {
  
  public static native void callNative(String path, FbCallback<JavaScriptObject> callback) /*-{
    $wnd.FB.api(path, function(response) {
      $entry(@info.arindamsharma.reachout.client.fb.Api::callbackCaller(Linfo/arindamsharma/reachout/client/fb/FbCallback;Lcom/google/gwt/core/client/JavaScriptObject;)(callback, response));
    });
  }-*/;
  
  private static void callbackCaller(FbCallback<JavaScriptObject> callback, JavaScriptObject response) {
    callback.onReturn(response);
  }
  
  public static void call(String path, final FbCallback<JSONObject> callback) {
    Logger.log("calling api :" + path);
    callNative(path, new FbCallback<JavaScriptObject>() {
      @Override
      public void onReturn(JavaScriptObject result) {
        callback.onReturn(new JSONObject(result));
      }
    });
  }
}
