package com.formation;

import static com.formation.JQuery.$;

import java.util.Arrays;
import java.util.List;

import org.fusesource.restygwt.client.Method;
import org.fusesource.restygwt.client.MethodCallback;
import org.fusesource.restygwt.client.REST;

import com.github.nmorel.gwtjackson.client.JsonSerializationContext;
import com.github.nmorel.gwtjackson.client.ObjectMapper;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
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
    CellTable<GreetingResponse> cellTable = new CellTable<>(Integer.MAX_VALUE, GWT.create(DefaultResources.class), GreetingResponse::getId);
    cellTable.setSkipRowHoverCheck(true);
    cellTable.setSkipRowHoverFloatElementCheck(true);
    cellTable.setSkipRowHoverStyleUpdate(true);
    cellTable.setStyleName("table table-striped table-hover");

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
    rowCountButton.setStyleName("btn btn-secondary mt-2");
    rowCountButton.addClickHandler(event -> Window.alert(String.valueOf($(cellTable.getElement()).find("tbody:first tr").length)));
    root.add(rowCountButton);

    App.EVENT_BUS.addHandler(CreateGreetingResponseEvent.TYPE, event -> {
      GreetingResponse greetingResponse = event.getGreetingResponse();
      greetingResponse.setId((long) cellTable.getRowCount());
      dataProvider.updateRowData(cellTable.getRowCount(), Arrays.asList(greetingResponse));
    });

    Button exportButton = new Button("Export");
    exportButton.setStyleName("btn btn-secondary mt-2 ms-2");
    exportButton.addClickHandler(new ClickHandler() {
      @Override
      public void onClick(ClickEvent event) {
        Window.alert(GreetingResponseObjectMapper.INSTANCE.write(cellTable.getVisibleItems(), JsonSerializationContext.builder().indent(true).build()));
      }
    });
    root.add(exportButton);
  }

  public interface GreetingResponseObjectMapper extends ObjectMapper<List<GreetingResponse>> {
    GreetingResponseObjectMapper INSTANCE = GWT.create(GreetingResponseObjectMapper.class);
  }

  public static void sayHello() {
    Window.alert("Hello");
  }
}
