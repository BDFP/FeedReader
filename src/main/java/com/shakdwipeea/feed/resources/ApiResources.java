package com.shakdwipeea.feed.resources;

import com.codahale.metrics.annotation.Timed;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * @author Akash
 *         Created on 15:11 14-09-2016
 */
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Path("/api")
public class ApiResources {

    @GET
    @Path("/hello")
    @Timed
    public Response sayHello() {
        return Response.ok().entity("I said hello").build();
    }
}
