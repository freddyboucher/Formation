package com.formation;

import java.io.Serializable;

@SuppressWarnings("serial")
public class GreetingResponse implements Serializable {
  private Long id;
  private String greeting;
  private String serverInfo;
  private String userAgent;

  public String getGreeting() {
    return greeting;
  }

  public void setGreeting(String greeting) {
    this.greeting = greeting;
  }

  public String getServerInfo() {
    return serverInfo;
  }

  public void setServerInfo(String serverInfo) {
    this.serverInfo = serverInfo;
  }

  public String getUserAgent() {
    return userAgent;
  }

  public void setUserAgent(String userAgent) {
    this.userAgent = userAgent;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }
}
