package info.arindamsharma.reachout.client.fb;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.RootPanel;

public class LoginStatus {
  private static final String PERMISSIONS =
    "read_stream,publish_stream,create_event,rsvp_event,sms,offline_access," +
    "user_about_me,user_events,user_location,email,friends_location,friends_about_me";

  public static native void getLoginStatusNative() /*-{
    $wnd.FB.getLoginStatus(function(response) {
      if (response.session) {
        $entry(@info.arindamsharma.reachout.client.fb.LoginStatus::isLoggedIn(Z)(true));
      } else {
        $entry(@info.arindamsharma.reachout.client.fb.LoginStatus::isLoggedIn(Z)(false));
      }
    });
  }-*/;
  
  public static void isLoggedIn(boolean loginStatus) {
    if (!loginStatus) {
      showLoginButton();
    } else {
      RootPanel.get("dynamicContainer").clear();
      new UserInfo().getUserData();
    }
  }

  private static native void loginNative(String permissions) /*-{
    $wnd.FB.login(function(response) {
      if (response.session) {
        $entry(@info.arindamsharma.reachout.client.fb.LoginStatus::isLoggedIn(Z)(true));
      }
    }, {perms: permissions});
  }-*/;
  

  private static void showLoginButton() {
    Button loginButton = new Button("Login");
    RootPanel.get("loginButtonContainer").add(loginButton);
    loginButton.addClickHandler(new ClickHandler() {
      
      @Override
      public void onClick(ClickEvent event) {
        loginNative(PERMISSIONS);
      }
    });
  }
}
