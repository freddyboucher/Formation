package com.formation;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.annotation.WebServlet;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

/**
 * The server side implementation of the RPC service.
 */
@WebServlet("/app/greet")
public class GreetingServiceImpl extends RemoteServiceServlet implements GreetingService {

  public GreetingResponse greetServer(String input) throws IllegalArgumentException {
    // Verify that the input is valid.
    if (!FieldVerifier.isValidName(input)) {
      // If the input is not valid, throw an IllegalArgumentException back to
      // the client.
      throw new IllegalArgumentException("Name must be at least 4 characters long");
    }

    GreetingResponse response = new GreetingResponse();

    response.setServerInfo(getServletContext().getServerInfo());
    response.setUserAgent(getThreadLocalRequest().getHeader("User-Agent"));

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
