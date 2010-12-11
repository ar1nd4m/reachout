package info.arindamsharma.reachout.client;

import com.google.gwt.user.client.Window;

public class Logger {
  
  private static final boolean alertOnLog = false;
  
  private static final Logger logger = new Logger();
  
  protected Logger() {
  }
  
  public static void log(String msg) {
    log(msg, alertOnLog);
  }
  
  public static void log(String msg, boolean alert) {
    logger.logImpl(msg);
    if (alert) {
      Window.alert(msg);
    }
  }
  
  private native void logImpl(String msg) /*-{
    console.log(msg);
  }-*/;
}
