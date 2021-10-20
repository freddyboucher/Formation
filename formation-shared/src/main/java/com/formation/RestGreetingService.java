package com.formation;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.fusesource.restygwt.client.DirectRestService;

@Path("greeting")
public interface RestGreetingService extends DirectRestService {

  @GET
  @Path("greetServer")
  @Produces(MediaType.APPLICATION_JSON)
  GreetingResponse greetServer(@QueryParam("name") String name) throws IllegalArgumentException;

  @GET
  @Path("fetchAll")
  @Produces(MediaType.APPLICATION_JSON)
  List<GreetingResponse> fetchAll();
}
