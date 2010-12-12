package info.arindamsharma.reachout.server.model;

import info.arindamsharma.reachout.shared.UserData;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;
import javax.jdo.annotations.Unique;

import com.google.appengine.api.datastore.Key;

@PersistenceCapable
public class UserDataModel {

  @SuppressWarnings("unused")
  @PrimaryKey
  @Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
  private Key key;
  
  @Unique
  @Persistent
  private long fbid;
  
  @Persistent
  private String email;
  
  @Persistent
  private double longitude;
  
  @Persistent
  private double latitude;
  
  @Persistent
  private String locationText;

  public UserDataModel(UserData user) {
    super();
    this.fbid = user.fbid;
    updateData(user);
  }
  
  public void updateData(UserData user) {
    this.email = user.email;
    this.latitude = user.latitude;
    this.longitude = user.longitude;
    this.locationText = user.locationText;    
  }

  public UserData toRpcVersion() {
    UserData user = new UserData();
    user.fbid = this.fbid;
    user.email = this.email;
    user.latitude = this.latitude;
    user.longitude = this.longitude;
    user.locationText = this.locationText;
    return user;
  }
  
}
