package info.arindamsharma.reachout.server;

import info.arindamsharma.reachout.client.service.GetUserService;
import info.arindamsharma.reachout.client.service.SaveUserService;
import info.arindamsharma.reachout.server.model.UserDataModel;
import info.arindamsharma.reachout.shared.UserData;

import java.util.Iterator;
import java.util.List;

import javax.jdo.PersistenceManager;

import com.google.gwt.core.client.GWT;
import com.google.inject.Inject;
import com.google.inject.Provider;

public class UserActions implements SaveUserService, GetUserService {
  private static final String SELECT_USER_ON_FBID = "select from " + UserDataModel.class.getName() + " where fbid == %d";
  
  private final Provider<PersistenceManager> pmProvider;
  
  @Inject
  public UserActions(Provider<PersistenceManager> pmProvider) {
    this.pmProvider = pmProvider;
  }
  
  private UserDataModel getUserDataModel(long fbid) {
    PersistenceManager pm = pmProvider.get();
    String query = String.format(SELECT_USER_ON_FBID, fbid);
    GWT.log("Running query: " + query);
    
    try {
      @SuppressWarnings("unchecked")
      List<UserDataModel> users = (List<UserDataModel>) pm.newQuery(query).execute();
      
      Iterator<UserDataModel> it = users.iterator();
      if (it.hasNext()) {
        return it.next();
      } else {
        return null;
      }
    } catch(Exception e) {
      return null;
    } finally {
      pm.close();
    }
  }
  
  @Override
  public boolean saveLocation(UserData user) throws IllegalArgumentException {
    UserDataModel udm = getUserDataModel(user.fbid);
    if (udm == null) {
      udm = new UserDataModel(user);
    } else {
      udm.updateData(user);
    }
    PersistenceManager pm = pmProvider.get();
    try {
      pm.makePersistent(udm);
      return true;
    } catch(Exception e) {
      return false;
    } finally {
      pm.close();
    }
  }
  
  @Override
  public UserData getLocation(long fbid) throws IllegalArgumentException {
    UserDataModel model = getUserDataModel(fbid);
    if (model != null) {
      return model.toRpcVersion();
    } else {
      return null;
    }
  }
}
