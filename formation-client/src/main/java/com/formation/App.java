package com.formation;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.DockPanel;
import com.google.gwt.user.client.ui.MenuBar;
import com.google.gwt.user.client.ui.MenuItem;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.SimplePanel;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class App implements EntryPoint {

  /**
   * This is the entry point method.
   */
  public void onModuleLoad() {
    DockPanel dockPabel = new DockPanel();
    dockPabel.setWidth("100%");
    MenuBar menuBar = new MenuBar();
    SamplePage samplePage = new SamplePage();
    SimplePanel simplePanel = new SimplePanel();
    dockPabel.add(simplePanel, DockPanel.CENTER);
    menuBar.addItem(new MenuItem("Sample", () -> simplePanel.setWidget(samplePage)));
    dockPabel.add(menuBar, DockPanel.NORTH);
    RootPanel.get().add(dockPabel);
  }
}
