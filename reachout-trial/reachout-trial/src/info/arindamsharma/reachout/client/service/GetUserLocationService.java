package info.arindamsharma.reachout.client.service;

import info.arindamsharma.reachout.shared.UserData;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("getUserLocation")
public interface GetUserLocationService extends RemoteService {
  UserData getLocation(long fbid) throws IllegalArgumentException;
}
