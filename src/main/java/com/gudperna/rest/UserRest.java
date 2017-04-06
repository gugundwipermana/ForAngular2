package com.gudperna.rest;

import javax.ws.rs.GET;
import javax.ws.rs.DELETE; 
import javax.ws.rs.POST; 
import javax.ws.rs.PUT; 
import javax.ws.rs.OPTIONS; 

import javax.ws.rs.PathParam;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType; 

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.SecurityContext;

import java.util.List;

import com.gudperna.util.ConnectionUtil;
import com.gudperna.dao.UserDAO;
import com.gudperna.dao.impl.UserDAOImpl;

import com.gudperna.model.User;


import javax.ws.rs.core.GenericEntity;

@Path("users")
public class UserRest {

	UserDAO userService = new UserDAOImpl(ConnectionUtil.getConnection());


    // @GET
    // @Path("/cek")
    // public Response options() {
    //     return Response.ok("")
    //             .header("Access-Control-Allow-Origin", "*")
    //             .header("Access-Control-Allow-Headers", "origin, content-type, accept, authorization")
    //             .header("Access-Control-Allow-Credentials", "true")
    //             .header("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS, HEAD")
    //             .header("Access-Control-Max-Age", "1209600")
    //             .build();
    // }

    // @OPTIONS
    // @Path("{path : .*}")
    // public Response options() {
    //     return Response.ok("")
    //             .header("Access-Control-Allow-Origin", "*")
    //             .header("Access-Control-Allow-Headers", "origin, content-type, accept, authorization")
    //             .header("Access-Control-Allow-Credentials", "true")
    //             .header("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS, HEAD")
    //             .header("Access-Control-Max-Age", "1209600")
    //             .build();
    // }

//	@GET
//	@Produces(MediaType.APPLICATION_JSON)
//	public List<User> getUsers() {
//		List<User> listUser = userService.getAll(); 
//		return listUser; 
//
//        /*return Response
//            .status(200)
//            .header("Access-Control-Allow-Origin", "*")
//            .header("Access-Control-Allow-Headers", "origin, content-type, accept, authorization")
//            .header("Access-Control-Allow-Credentials", "true")
//            .header("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS, HEAD")
//            .header("Access-Control-Max-Age", "1209600")
//            .entity(listUser)
//            .build();*/
//	}


/*    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUser() {
        List<User> listUser = userService.getAll();
        
        GenericEntity<List<User>> list = new GenericEntity<List<User>>(listUser) { };
        return Response.ok(list)
            .header("Access-Control-Allow-Origin", "*")
            .header("Access-Control-Allow-Headers", "origin, content-type, accept, authorization")
            .header("Access-Control-Allow-Credentials", "true")
            .header("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS, HEAD")
            .header("Access-Control-Max-Age", "1209600")
            .build();
    }
*/


    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUser() {
        List<User> listUser = userService.getAll();
        
        GenericEntity<List<User>> list = new GenericEntity<List<User>>(listUser) { };
        return Response.ok(list)
            .build();
    }


	@GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUserById(@PathParam("id") int id) { 

        return Response.ok(userService.getById(id))
            .build();
    }





    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response addUser(User user) { 
    	userService.insert(user);

        return Response.status(201)
            .entity("Success")
            .build();
    }

    @PUT 
    @Produces(MediaType.APPLICATION_JSON) 
    public Response updateUser(User user) { 
    	userService.edit(user);

        return Response.status(201)
            .entity("Success")
            .build();
    }

    // @DELETE 
    // @Path("/{id}") 
    // @Produces(MediaType.APPLICATION_JSON) 
    // public void deleteUser(@PathParam("id") int id) {
    // 	userService.delete(id);
    // }

    @DELETE 
    @Path("/{id}") 
    @Produces(MediaType.APPLICATION_JSON) 
    public Response deleteUser(@PathParam("id") int id) {
        userService.delete(id);

        return Response.status(201)
            .entity("Success")
            .build();
    }
	
}

// /src/main/java/com/gudperna/rest/UserRest.java