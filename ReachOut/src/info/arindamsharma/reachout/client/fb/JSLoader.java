package info.arindamsharma.reachout.client.fb;

import info.arindamsharma.reachout.client.Logger;
import info.arindamsharma.reachout.client.ReachOut;

public class JSLoader {
	private static ReachOut rootApp;
	
	private static native void exportStaticMethods() /*-{
		$wnd.onFacebookScriptLoad = @info.arindamsharma.reachout.client.fb.JSLoader::onScriptLoad();
	}-*/;
	

	private static native void initializeFb() /*-{
		$wnd.FB.init({
	      appId  : '129188633806946',
	      status : true, // check login status
	      cookie : true, // enable cookies to allow the server to access the session
	      xfbml  : true  // parse XFBML
    	});
	}-*/;
	
	private static native void addFbScriptNative() /*-{
		var e = $doc.createElement('script'); e.async = true;
		e.src = $doc.location.protocol +
  			'//connect.facebook.net/en_US/all.js';
		$doc.getElementById('fb-root').appendChild(e);
	}-*/;
	
	private static void onScriptLoad() {
	  Logger.log("on fb scriptLoad called");
		initializeFb();
		Logger.log("calling the Reachout callback");
		rootApp.onFacebookLoad();
	}
	
	public static void addFbScript(ReachOut rootApp) {
	  JSLoader.rootApp = rootApp; 
		exportStaticMethods();
		addFbScriptNative();
		Logger.log("added native script");
	}
}
