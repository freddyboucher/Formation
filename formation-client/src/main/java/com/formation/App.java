package com.formation;

import org.fusesource.restygwt.client.Defaults;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.shared.GWT;
import com.google.gwt.place.shared.PlaceHistoryHandler;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.web.bindery.event.shared.EventBus;
import com.google.web.bindery.event.shared.SimpleEventBus;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class App implements EntryPoint {

  private static final PlaceHistoryHandler.Historian historian = GWT.create(PlaceHistoryHandler.Historian.class);
  public static final EventBus EVENT_BUS = new SimpleEventBus();

  private final Template template;
  private final SamplePage samplePage;
  private final ShowcasePage showcasePage;

  public App() {
    Defaults.setServiceRoot("api");
    template = new Template();
    samplePage = new SamplePage();
    showcasePage = new ShowcasePage();
  }

  /**
   * This is the entry point method.
   */
  public void onModuleLoad() {
    RootPanel.get().add(template);
    template.getSampleAnchor().addClickHandler(event -> historian.newItem("sample", true));
    template.getShowcaseAnchor().addClickHandler(event -> historian.newItem("showcase", true));

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
        template.getSimplePanel().setWidget(samplePage);
        break;
      case "showcase":
        template.getSimplePanel().setWidget(showcasePage);
        break;
      default:
        template.getSimplePanel().setWidget(new Label("404"));
        break;
    }
  }
}
