package info.arindamsharma.reachout.client.fb;

import info.arindamsharma.reachout.client.Logger;
import info.arindamsharma.reachout.client.Reachout_trial;
import info.arindamsharma.reachout.client.service.GetUserLocationService;
import info.arindamsharma.reachout.client.service.GetUserLocationServiceAsync;
import info.arindamsharma.reachout.client.service.SaveUserLocationService;
import info.arindamsharma.reachout.client.service.SaveUserLocationServiceAsync;
import info.arindamsharma.reachout.shared.UserData;

import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.JsArray;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.json.client.JSONObject;
import com.google.gwt.maps.client.MapWidget;
import com.google.gwt.maps.client.Maps;
import com.google.gwt.maps.client.control.SmallMapControl;
import com.google.gwt.maps.client.event.MarkerDragEndHandler;
import com.google.gwt.maps.client.geocode.Geocoder;
import com.google.gwt.maps.client.geocode.LocationCallback;
import com.google.gwt.maps.client.geocode.Placemark;
import com.google.gwt.maps.client.geom.LatLng;
import com.google.gwt.maps.client.geom.LatLngBounds;
import com.google.gwt.maps.client.geom.Size;
import com.google.gwt.maps.client.overlay.Marker;
import com.google.gwt.maps.client.overlay.MarkerOptions;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DockLayoutPanel;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;

public class UserInfo {

  private final SaveUserLocationServiceAsync saveService = GWT
          .create(SaveUserLocationService.class);
  private final GetUserLocationServiceAsync getService = GWT.create(GetUserLocationService.class);
  
  private long fbid;
  private LatLng latLng;

  public void getUserData() {
    Api.call("/me", new FbCallback<JSONObject>() {
      @Override
      public void onReturn(JSONObject result) {
        showUserInfo(result);
      }
    });
  }

  private void showUserInfo(JSONObject userData) {
    FlowPanel panel = new FlowPanel();
    fbid = Long.parseLong(userData.get("id").isString().stringValue());
    final Image profileLogo = new Image("http://graph.facebook.com/"
            + userData.get("id").isString().stringValue() + "/picture");
    profileLogo.setAltText("user picture");
    panel.add(profileLogo);

    panel.add(new Label(userData.get("name").isString().stringValue()));
    panel.add(new Label(userData.get("location").isObject().get("name").isString().stringValue()));

    addMap(userData.get("location").isObject().get("name").isString().stringValue(), panel);

    RootPanel.get("dynamicContainer").clear();
    RootPanel.get("dynamicContainer").add(panel);

  }

  private void addMap(final String locationValue, final FlowPanel panel) {
    if (!Reachout_trial.mapsLoaded) {
      Maps.loadMapsApi("", "2", false, new Runnable() {
        @Override
        public void run() {
          Reachout_trial.mapsLoaded = true;
          addMap(locationValue, panel);
        }
      });
      return;
    }
    
    getService.getLocation(fbid, new AsyncCallback<UserData>() {

      @Override
      public void onFailure(Throwable caught) {
        Logger.log("failed :" + caught.getMessage());
        Geocoder geocoder = new Geocoder();
        geocoder.getLocations(locationValue, new LocationCallback() {
          @Override
          public void onSuccess(JsArray<Placemark> locations) {
            Placemark p = locations.get(0);
            showMapOnScreen(p.getPoint(), p.getExtendedData().getBounds(), panel);
          }
          
          @Override
          public void onFailure(int statusCode) {
            Logger.log("Failed with status : " + statusCode);
          }
        });
      }

      @Override
      public void onSuccess(UserData result) {
        if (result == null) {
          onFailure(new Throwable("user not found"));
          return;
        }
        LatLng location = LatLng.newInstance(result.latitude, result.longitude);
        showMapOnScreen(location, null, panel);
      }
    });
  }

  private void showMapOnScreen(LatLng location, LatLngBounds bounds, final FlowPanel panel) {
    Logger.log("at the map builder");
    Logger.log(location, "From fb");

    final MapWidget map = new MapWidget(location, 2);
    map.setSize("100%", "100%");
    map.setGoogleBarEnabled(true);
    map.setContinuousZoom(true);
    map.setDoubleClickZoom(true);
    map.addControl(new SmallMapControl());
    MarkerOptions markerOptions = MarkerOptions.newInstance();
    markerOptions.setDraggable(true);
    Marker marker = new Marker(location, markerOptions);
    map.addOverlay(marker);
    marker.addMarkerDragEndHandler(new MarkerDragEndHandler() {
      @Override
      public void onDragEnd(MarkerDragEndEvent event) {
        latLng = event.getSender().getLatLng();
        Logger.log(latLng, "Moved marker");
      }
    });

    final DockLayoutPanel dock = new DockLayoutPanel(Unit.PX);
    dock.addNorth(map, 500);

    final Button saveLocationChangesButton = new Button("Save Location");
    panel.add(saveLocationChangesButton);
    saveLocationChangesButton.addClickHandler(new ClickHandler() {
      @Override
      public void onClick(ClickEvent event) {
        UserData user = new UserData();
        user.fbid = fbid;
        user.latitude = latLng.getLatitude();
        user.longitude = latLng.getLongitude();
        saveService.saveLocation(user, new AsyncCallback<Boolean>() {
          @Override
          public void onFailure(Throwable caught) {
            Logger.log(caught.getMessage());
          }

          @Override
          public void onSuccess(Boolean result) {
            saveLocationChangesButton.setEnabled(false);
          }
        });
      }
    });

    panel.add(dock);
    panel.setHeight("700px");
    Logger.log("Added map");

    if (bounds != null) {
      int zoom = map.getMapTypes()[0].getBoundsZoomLevel(bounds, 
              Size.newInstance(dock.getOffsetWidth(), 500));
      map.setZoomLevel(Math.max(zoom, 7));
      Logger.log("Zoom level set to : " + zoom);
    } else {
      map.setZoomLevel(14);
    }
  }
}
