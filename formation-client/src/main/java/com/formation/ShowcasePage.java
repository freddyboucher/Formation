package com.formation;

import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.view.client.AsyncDataProvider;
import com.google.gwt.view.client.HasData;

public class ShowcasePage extends Composite {
  private final GreetingServiceAsync greetingService = GWT.create(GreetingService.class);
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
    initWidget(cellTable);
    setWidth("100%");
    dataProvider = new AsyncDataProvider<GreetingResponse>(GreetingResponse::getId) {
      @Override
      protected void onRangeChanged(HasData display) {
        greetingService.fetchAll(new AsyncCallback<List<GreetingResponse>>() {
          @Override
          public void onFailure(Throwable caught) {
            Window.alert(caught.getMessage());
          }

          @Override
          public void onSuccess(List<GreetingResponse> result) {
            dataProvider.updateRowData(0, result);
          }
        });
      }
    };
    dataProvider.addDataDisplay(cellTable);

  }
}
