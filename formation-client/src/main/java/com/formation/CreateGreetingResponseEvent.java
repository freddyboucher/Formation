package com.formation;

import com.google.gwt.event.shared.GwtEvent;

public class CreateGreetingResponseEvent extends GwtEvent<CreateGreetingResponseHandler> {

  public static final Type<CreateGreetingResponseHandler> TYPE = new Type<>();
  private final GreetingResponse greetingResponse;

  public CreateGreetingResponseEvent(GreetingResponse greetingResponse) {
    this.greetingResponse = greetingResponse;
  }

  @Override
  protected void dispatch(CreateGreetingResponseHandler handler) {
    handler.onCreate(this);
  }

  @Override
  public Type<CreateGreetingResponseHandler> getAssociatedType() {
    return TYPE;
  }

  public GreetingResponse getGreetingResponse() {
    return greetingResponse;
  }

}
