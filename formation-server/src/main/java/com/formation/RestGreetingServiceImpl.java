package com.formation;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.Context;

public class RestGreetingServiceImpl implements RestGreetingService {

  @Context protected HttpServletRequest request;
  @Context protected ServletContext context;

  @Override
  public GreetingResponse greetServer(String input) throws IllegalArgumentException {
    // Verify that the input is valid.
    if (!FieldVerifier.isValidName(input)) {
      // If the input is not valid, throw an IllegalArgumentException back to
      // the client.
      throw new IllegalArgumentException("Name must be at least 4 characters long");
    }

    GreetingResponse response = new GreetingResponse();

    response.setServerInfo(context.getServerInfo());
    response.setUserAgent(request.getHeader("User-Agent"));

    response.setGreeting("Hello, " + input + "!");

    return response;
  }

  @Override
  public List<GreetingResponse> fetchAll() {
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
