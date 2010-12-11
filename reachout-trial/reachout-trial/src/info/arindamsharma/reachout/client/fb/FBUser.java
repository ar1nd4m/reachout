package info.arindamsharma.reachout.client.fb;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.json.client.JSONObject;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;

public class FBUser {

	private final String fbid;
	
	public FBUser(String fbid) {
		this.fbid = fbid;
	}
	
	public void fetchData() {
		makeFBApiCall("/" + fbid, new AsyncCallback<JavaScriptObject>() {
			@Override
			public void onFailure(Throwable caught) {
			}
			@Override
			public void onSuccess(JavaScriptObject result) {
				Window.alert(new JSONObject(result).toString());
			}
			
		});
	}
	
	private native boolean makeFBApiCall(String path, AsyncCallback<JavaScriptObject> callback) /*-{
		var app=this;
		$wnd.FB.api(path, function(response) {
			app.@info.arindamsharma.reachout.client.fb.FBUser::callbackSuccess(Lcom/google/gwt/user/client/rpc/AsyncCallback;Lcom/google/gwt/core/client/JavaScriptObject;)(callback, response);
		});
		return true;
	}-*/;
	
	/*
     * Called when method succeeded.
     */
    protected void callbackSuccess(AsyncCallback<JavaScriptObject> callback, JavaScriptObject obj) {
        callback.onSuccess ( obj );
    }
}
