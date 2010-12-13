package info.arindamsharma.reachout.server.service;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import info.arindamsharma.reachout.client.service.GetUserService;
import info.arindamsharma.reachout.server.UserActions;
import info.arindamsharma.reachout.shared.UserData;

@SuppressWarnings("serial")
public class GetUserServiceImpl extends RemoteServiceServlet implements GetUserService {

  @Override
  public UserData getLocation(long fbid) throws IllegalArgumentException {
    return new UserActions().getLocation(fbid);
  }

}
