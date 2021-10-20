package com.formation;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;

import com.google.gwt.logging.server.RemoteLoggingServiceImpl;

@WebServlet("/app/remote_logging")
public class MyRemoteLoggingServiceImpl extends RemoteLoggingServiceImpl {
  @Override
  public void init(ServletConfig config) throws ServletException {
    super.init(config);
    setSymbolMapsDirectory(config.getServletContext().getRealPath("WEB-INF/deploy/app/symbolMaps/"));
  }
}
