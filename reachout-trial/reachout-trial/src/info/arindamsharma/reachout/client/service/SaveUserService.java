package info.arindamsharma.reachout.client.service;

import info.arindamsharma.reachout.shared.RpcConstants;
import info.arindamsharma.reachout.shared.UserData;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("../" + RpcConstants.SAVE_USER)
public interface SaveUserService extends RemoteService {
  boolean saveLocation(UserData user) throws IllegalArgumentException;
}
