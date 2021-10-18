package com.formation;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;

import com.google.gwt.logging.server.RemoteLoggingServiceImpl;

public class MyRemoteLoggingServiceImpl extends RemoteLoggingServiceImpl {
  @Override
  public void init(ServletConfig config) throws ServletException {
    super.init(config);
    setSymbolMapsDirectory(config.getServletContext().getRealPath("WEB-INF/deploy/app/symbolMaps/"));
  }
}
