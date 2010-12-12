package info.arindamsharma.reachout.server.service;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import info.arindamsharma.reachout.client.service.SaveUserLocationService;
import info.arindamsharma.reachout.server.UserLocationServices;
import info.arindamsharma.reachout.shared.UserData;

@SuppressWarnings("serial")
public class SaveUserLocationServiceImpl extends RemoteServiceServlet implements SaveUserLocationService {

  @Override
  public boolean saveLocation(UserData user) throws IllegalArgumentException {
    return new UserLocationServices().saveLocation(user);
  }

}
