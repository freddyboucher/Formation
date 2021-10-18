package com.formation;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.shared.GWT;
import com.google.gwt.place.shared.PlaceHistoryHandler;
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

  private static final PlaceHistoryHandler.Historian historian = GWT.create(PlaceHistoryHandler.Historian.class);

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
    menuBar.addItem(new MenuItem("Sample", () -> historian.newItem("sample", true)));
    menuBar.addItem(new MenuItem("Showcase", () -> historian.newItem("showcase", true)));
    dockPabel.add(menuBar, DockPanel.NORTH);
    RootPanel.get().add(dockPabel);

    historian.addValueChangeHandler(event -> handleToken(event.getValue()));

    if (historian.getToken().isEmpty()) {
      historian.newItem("sample", true);
    } else {
      handleToken(historian.getToken());
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
