package com.formation;

import static com.formation.JQuery.$;

import java.util.List;

import org.fusesource.restygwt.client.Method;
import org.fusesource.restygwt.client.MethodCallback;
import org.fusesource.restygwt.client.REST;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.view.client.AsyncDataProvider;
import com.google.gwt.view.client.HasData;

import jsinterop.annotations.JsPackage;
import jsinterop.annotations.JsType;

@JsType(namespace = JsPackage.GLOBAL)
public class ShowcasePage extends Composite {
  private final RestGreetingService greetingService = GWT.create(RestGreetingService.class);
  private final AsyncDataProvider<GreetingResponse> dataProvider;

  public ShowcasePage() {
    CellTable<GreetingResponse> cellTable = new CellTable<>(GreetingResponse::getId);
    Column<GreetingResponse, String> greetingColumn = new TextColumn<GreetingResponse>() {
      @Override
      public String getValue(GreetingResponse object) {
        return object.getGreeting();
      }
    };
    cellTable.addColumn(greetingColumn, "Greeting");
    Column<GreetingResponse, String> serverInfoColumn = new TextColumn<GreetingResponse>() {
      @Override
      public String getValue(GreetingResponse object) {
        return object.getServerInfo();
      }
    };
    cellTable.addColumn(serverInfoColumn, "Server Info");
    Column<GreetingResponse, String> userAgentColumn = new TextColumn<GreetingResponse>() {
      @Override
      public String getValue(GreetingResponse object) {
        return object.getUserAgent();
      }
    };
    cellTable.addColumn(userAgentColumn, "User Agent");
    FlowPanel root = new FlowPanel();
    root.add(cellTable);
    initWidget(root);
    cellTable.setWidth("100%");
    dataProvider = new AsyncDataProvider<GreetingResponse>(GreetingResponse::getId) {
      @Override
      protected void onRangeChanged(HasData display) {
        REST.withCallback(new MethodCallback<List<GreetingResponse>>() {
          @Override
          public void onFailure(Method method, Throwable exception) {
            Window.alert(exception.getMessage());
          }

          @Override
          public void onSuccess(Method method, List<GreetingResponse> response) {
            dataProvider.updateRowData(0, response);
          }
        }).call(greetingService).fetchAll();
      }
    };
    dataProvider.addDataDisplay(cellTable);

    Button rowCountButton = new Button("Row count");
    rowCountButton.addClickHandler(event -> Window.alert(String.valueOf($(cellTable.getElement()).find("tbody:first tr").length)));
    rowCountButton.getElement().getStyle().setMarginTop(20, Style.Unit.PX);
    root.add(rowCountButton);
  }

  public static void sayHello() {
    Window.alert("Hello");
  }
}
