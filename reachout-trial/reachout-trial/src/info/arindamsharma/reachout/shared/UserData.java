package info.arindamsharma.reachout.shared;

import java.io.Serializable;

@SuppressWarnings("serial")
public class UserData implements Serializable {

  public long fbid;
  public String name;
  public String email;
  public double latitude;
  public double longitude;
  public String locationText;
    
  public UserData() {
    
  }
}
