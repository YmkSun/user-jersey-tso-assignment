package com.ymk.tso.controller.rest;

import java.util.List;

import javax.websocket.server.PathParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.ymk.tso.data.UserData;
import com.ymk.tso.service.UserService;

@Path("/user")
public class UserController {

	@GET
	@Path("list")
	@Produces(MediaType.APPLICATION_JSON)
	public List<UserData> getAllUsers(@QueryParam("searchVal") String val) {
		return UserService.getInstance().getAll((val != null) ? val : "");
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public UserData getById(@QueryParam("id") Long id) {
		return UserService.getInstance().getById((id != null) ? id : 0L);
	}
	
	@POST
	@Path("add")
	@Produces(MediaType.APPLICATION_JSON)    
	@Consumes(MediaType.APPLICATION_JSON)
	public UserData save(UserData data) {
		return UserService.getInstance().save(data);
	}
	
	@PUT
	@Path("edit")
	@Produces(MediaType.APPLICATION_JSON)    
	@Consumes(MediaType.APPLICATION_JSON)
	public UserData update(UserData data) {
		return UserService.getInstance().update(data.getId(), data);
	}
	
	@PUT
	@Path("delete")
	@Produces(MediaType.APPLICATION_JSON)    
	@Consumes(MediaType.APPLICATION_JSON)
	public int delete(@QueryParam("id") Long id) {
		return UserService.getInstance().delete(id);
	}
	
	@DELETE
	@Path("clean")
	@Produces(MediaType.APPLICATION_JSON)    
	@Consumes(MediaType.APPLICATION_JSON)
	public String clean(@QueryParam("id") Long id) {
		return UserService.getInstance().clean(id);
	}

}
