package com.shakdwipeea.feed.resources;

import com.codahale.metrics.annotation.Timed;
import com.mongodb.rx.client.MongoDatabase;
import com.shakdwipeea.feed.service.UserService;
import com.shakdwipeea.feed.model.ErrorResponse;
import com.shakdwipeea.feed.model.User;
import com.shakdwipeea.feed.util.Util;

import javax.ws.rs.*;
import javax.ws.rs.container.AsyncResponse;
import javax.ws.rs.container.Suspended;
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
    private UserService userService;

    public ApiResources(MongoDatabase mongoDatabase) {
        this.userService = new UserService(mongoDatabase);
    }

    @GET
    @Path("/hello")
    @Timed
    public Response sayHello() {
        return Response.ok().entity("I said hello").build();
    }

    @Path("/login")
    @POST
    public void login(@Suspended final AsyncResponse asyncResponse,User user) {
        user.setPassword(Util.Encrypt(user.getPassword()));
        userService.findUser(user)
                .defaultIfEmpty(ErrorResponse.toDocument("Wrong credentials"))
                .doOnNext(Util::addNoError)
                .subscribe((document) -> asyncResponse.resume(Util.makeResponse(document).build()));
    }

    @Path("/signup")
    @POST
    public void signup(@Suspended final AsyncResponse asyncResponse, User user) {
        user.setPassword(Util.Encrypt(user.getPassword()));
        userService.findUser(user)
                .map((document) -> ErrorResponse.toDocument("User Exists"))
                .switchIfEmpty(userService.addUser(user))
                .doOnNext(Util::addNoError)
                .subscribe(document -> asyncResponse.resume(Util.makeResponse(document).build()));
    }
}
