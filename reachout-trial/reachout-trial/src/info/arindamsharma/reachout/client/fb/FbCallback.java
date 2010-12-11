package info.arindamsharma.reachout.client.fb;

import info.arindamsharma.reachout.client.Logger;

import com.google.gwt.user.client.rpc.AsyncCallback;

public abstract class FbCallback<T> implements AsyncCallback<T> {
  public abstract void onReturn(T result);
  
  @Override
  final public void onFailure(Throwable caught) {
    Logger.log(caught.getMessage());
    onReturn(null);
  }
  @Override
  final public void onSuccess(T result) {
    onReturn(result);
  }
}
