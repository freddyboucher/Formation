package com.formation;

import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * The async counterpart of <code>GreetingService</code>.
 */
public interface GreetingServiceAsync {
  void greetServer(String input, AsyncCallback<GreetingResponse> callback);

  void fetchAll(AsyncCallback<List<GreetingResponse>> callback);
}
