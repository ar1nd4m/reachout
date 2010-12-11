package info.arindamsharma.reachout.client.fb;

import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.ScriptElement;
import com.google.gwt.user.client.Window.Location;

import info.arindamsharma.reachout.client.Logger;

public class JSLoader {
	private static FbCallback<Boolean> callback;
	
	public static void setCallback(FbCallback<Boolean> callback) {
	  JSLoader.callback = callback;
	}
	
	private static native void exportStaticMethodsNative() /*-{
		$wnd.onFacebookScriptLoadGwt = @info.arindamsharma.reachout.client.fb.JSLoader::onScriptLoad();
	}-*/;
	

	private static native void initializeFbNative() /*-{
		$wnd.FB.init({
	      appId  : '129188633806946',
	      status : true, // check login status
	      cookie : true, // enable cookies to allow the server to access the session
	      xfbml  : true  // parse XFBML
    	});
	}-*/;
	
	private static native void addFbScriptDomNative() /*-{
		var e = $doc.createElement('script'); e.async = true;
		e.src = $doc.location.protocol +
  			'//connect.facebook.net/en_US/all.js';
		$doc.getElementById('fb-root').appendChild(e);
	}-*/;
	
	// Does not seem to work
	private static void addFbScriptDom() {
	  ScriptElement script = Document.get().createScriptElement(
	          Location.getProtocol() + "//connect.facebook.net/en_US/all.js");
	  Document.get().getElementById("fb-root").appendChild(script);
	}
	
	private static void onScriptLoad() {
	  Logger.log("on fb scriptLoad called");
		initializeFbNative();
		Logger.log("calling the Reachout callback");
		callback.onReturn(true);
	}
	
	public static void addFbScript() { 
		exportStaticMethodsNative();
		addFbScriptDomNative();
		//addFbScriptDom();
		Logger.log("added native script");
	}
}
