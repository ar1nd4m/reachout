package info.arindamsharma.reachout.server.service;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import info.arindamsharma.reachout.client.service.SaveUserService;
import info.arindamsharma.reachout.server.UserActions;
import info.arindamsharma.reachout.shared.UserData;

@SuppressWarnings("serial")
public class SaveUserServiceImpl extends RemoteServiceServlet implements SaveUserService {

  @Override
  public boolean saveLocation(UserData user) throws IllegalArgumentException {
    return new UserActions().saveLocation(user);
  }

}
