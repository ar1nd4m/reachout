package info.arindamsharma.reachout.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.maps.client.geom.LatLng;
import com.google.gwt.user.client.Window;

public class Logger {
  
  private static final boolean alertOnLog = false;
  
  private static final Logger logger = new Logger();
  
  protected Logger() {
  }
  
  public static void log(LatLng latLng, String prefix) {
    log(prefix + " Lat/Lng: " + latLng.getLatitude() + "," + latLng.getLongitude());
  }
  
  public static void log(String msg) {
    log(msg, alertOnLog);
  }
  
  public static void log(String msg, boolean alert) {
    logger.logImpl(msg);
    GWT.log(msg);
    if (alert) {
      Window.alert(msg);
    }
  }
  
  private native void logImpl(String msg) /*-{
    if ($wnd.console && $wnd.console.log) {
      $wnd.console.log(msg);
    }
  }-*/;
}
