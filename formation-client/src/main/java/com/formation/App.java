package com.formation;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.ui.DockPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.MenuBar;
import com.google.gwt.user.client.ui.MenuItem;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.SimplePanel;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class App implements EntryPoint {

  private final SamplePage samplePage;
  private final ShowcasePage showcasePage;
  private final SimplePanel simplePanel;

  public App() {
    samplePage = new SamplePage();
    showcasePage = new ShowcasePage();
    simplePanel = new SimplePanel();
  }

  /**
   * This is the entry point method.
   */
  public void onModuleLoad() {
    DockPanel dockPabel = new DockPanel();
    dockPabel.setWidth("100%");
    MenuBar menuBar = new MenuBar();
    dockPabel.add(simplePanel, DockPanel.CENTER);
    menuBar.addItem(new MenuItem("Sample", () -> History.newItem("sample")));
    menuBar.addItem(new MenuItem("Showcase", () -> History.newItem("showcase")));
    dockPabel.add(menuBar, DockPanel.NORTH);
    RootPanel.get().add(dockPabel);

    History.addValueChangeHandler(event -> handleToken(event.getValue()));

    if (History.getToken().isEmpty()) {
      History.newItem("sample");
    } else {
      handleToken(History.getToken());
    }
  }

  private void handleToken(String token) {
    switch (token) {
      case "sample":
        simplePanel.setWidget(samplePage);
        break;
      case "showcase":
        simplePanel.setWidget(showcasePage);
        break;
      default:
        simplePanel.setWidget(new Label("404"));
        break;
    }
  }
}
