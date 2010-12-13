package info.arindamsharma.reachout.client.service;

import info.arindamsharma.reachout.shared.RpcConstants;
import info.arindamsharma.reachout.shared.UserData;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("../" + RpcConstants.GET_USER)
public interface GetUserService extends RemoteService {
  UserData getLocation(long fbid) throws IllegalArgumentException;
}
