package info.arindamsharma.reachout.client.fb;

import com.google.gwt.event.shared.GwtEvent;

public class FacebookScriptLoadEvent extends GwtEvent<FacebookScriptLoadHandler> {

	public static final Type<FacebookScriptLoadHandler> TYPE = new Type<FacebookScriptLoadHandler>();
	
	@Override
	public Type<FacebookScriptLoadHandler> getAssociatedType() {
		return TYPE;
	}

	@Override
	protected void dispatch(FacebookScriptLoadHandler handler) {
		handler.onScriptLoadComplete(this);
	}

}
