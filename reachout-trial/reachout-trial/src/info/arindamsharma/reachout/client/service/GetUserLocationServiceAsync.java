package info.arindamsharma.reachout.client.service;

import info.arindamsharma.reachout.shared.UserData;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface GetUserLocationServiceAsync {
  void getLocation(long fbid, AsyncCallback<UserData> callback);

}
