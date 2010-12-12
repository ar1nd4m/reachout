package info.arindamsharma.reachout.server.service;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import info.arindamsharma.reachout.client.service.GetUserLocationService;
import info.arindamsharma.reachout.server.UserLocationServices;
import info.arindamsharma.reachout.shared.UserData;

@SuppressWarnings("serial")
public class GetUserLocationServiceImpl extends RemoteServiceServlet implements GetUserLocationService {

  @Override
  public UserData getLocation(long fbid) throws IllegalArgumentException {
    return new UserLocationServices().getLocation(fbid);
  }

}
