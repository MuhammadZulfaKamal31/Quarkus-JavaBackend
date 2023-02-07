package org.acme;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/hello")
public class GreetingResource {

    @GET
    @Path("world")
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() {
        return "Hello, I am Optimus Prime";
    }

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String hello2() {
        return "Hello from RESTEasy Reactive";
    }
}