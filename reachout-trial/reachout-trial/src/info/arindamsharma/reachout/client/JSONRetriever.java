package info.arindamsharma.reachout.client;

import info.arindamsharma.reachout.client.fb.FbCallback;

import com.google.gwt.http.client.Request;
import com.google.gwt.http.client.RequestBuilder;
import com.google.gwt.http.client.RequestCallback;
import com.google.gwt.http.client.RequestException;
import com.google.gwt.http.client.Response;
import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONParser;
import com.google.gwt.json.client.JSONValue;

public class JSONRetriever {

  private final String url;
  private final FbCallback<JSONObject> callback;
  
  public JSONRetriever(String url, FbCallback<JSONObject> callback) {
    this.url = url;
    this.callback = callback;
  }
  
  public void send() {
    RequestBuilder requestBuilder = new RequestBuilder(RequestBuilder.GET, url);
    requestBuilder.setHeader("Access-Control-Allow-Origin", "*");
    requestBuilder.setCallback(new RequestCallback() {
      
      @Override
      public void onResponseReceived(Request request, Response response) {
        if (response.getStatusCode() == Response.SC_OK) {
          JSONValue jsonValue = JSONParser.parseStrict(response.getText());
          JSONObject jsonObject = jsonValue.isObject();
          callback.onReturn(jsonObject);
        }
      }
      
      @Override
      public void onError(Request request, Throwable exception) {
        Logger.log(exception.getLocalizedMessage(), true);
      }
    });
    try {
      requestBuilder.send();
    } catch(RequestException e) {
      Logger.log("Request Exception : " + e.getLocalizedMessage());
    }
  }
}
