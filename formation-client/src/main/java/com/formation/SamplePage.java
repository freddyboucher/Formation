package com.formation;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Element;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.UIObject;

public class SamplePage extends UIObject {

  private static final Binder uiBinder = GWT.create(Binder.class);

  public SamplePage() {
    setElement(uiBinder.createAndBindUi(this));
  }

  interface Binder extends UiBinder<Element, SamplePage> {}
}
