package com.formation;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.view.client.ListDataProvider;

public class ShowcasePage extends Composite {
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
    ListDataProvider<GreetingResponse> dataProvider = new ListDataProvider<>(GreetingResponse::getId);
    dataProvider.getList().addAll(generateGreetingResponses());
    dataProvider.addDataDisplay(cellTable);
  }

  private static List<GreetingResponse> generateGreetingResponses() {
    ArrayList<GreetingResponse> list = new ArrayList<>();
    for (int i = 0; i < 10; i++) {
      GreetingResponse greetingResponse = new GreetingResponse();
      greetingResponse.setId((long) i);
      greetingResponse.setGreeting("Greeting " + i);
      greetingResponse.setServerInfo("Server Info " + i);
      greetingResponse.setUserAgent("User Agent " + i);
      list.add(greetingResponse);
    }
    return list;
  }
}
