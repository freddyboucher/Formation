package com.formation;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.Widget;

public class Template extends Composite {
  private static final Binder uiBinder = GWT.create(Binder.class);

  @UiField Anchor sampleAnchor;
  @UiField Anchor showcaseAnchor;
  @UiField SimplePanel simplePanel;

  public Template() {
    initWidget(uiBinder.createAndBindUi(this));
  }

  public Anchor getSampleAnchor() {
    return sampleAnchor;
  }

  public Anchor getShowcaseAnchor() {
    return showcaseAnchor;
  }

  public SimplePanel getSimplePanel() {
    return simplePanel;
  }

  interface Binder extends UiBinder<Widget, Template> {}
}
