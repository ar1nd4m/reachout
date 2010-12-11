package info.arindamsharma.reachout.client.fb;

import com.google.gwt.event.shared.EventHandler;

public interface FacebookScriptLoadHandler extends EventHandler {
	void onScriptLoadComplete(FacebookScriptLoadEvent event);
}
