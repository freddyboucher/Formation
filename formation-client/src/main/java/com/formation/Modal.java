package com.formation;

import static com.formation.JQuery.$;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.Widget;

public class Modal extends Composite {
  private static final Binder uiBinder = GWT.create(Binder.class);
  @UiField Label header;
  @UiField SimplePanel body;
  @UiField SimplePanel footer;

  public Modal() {
    initWidget(uiBinder.createAndBindUi(this));
  }

  public void center() {
    RootPanel.get().add(this);
    $(getElement()).modal("show");
  }

  public void hide() {
    $(getElement()).modal("hide");
    RootPanel.get().remove(this);
  }

  public Label getHeader() {
    return header;
  }

  public SimplePanel getBody() {
    return body;
  }

  public SimplePanel getFooter() {
    return footer;
  }

  interface Binder extends UiBinder<Widget, Modal> {}

}
