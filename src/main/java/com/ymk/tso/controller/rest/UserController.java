package com.ymk.tso.controller.rest;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.apache.log4j.Logger;

import com.ymk.tso.data.LoginData;
import com.ymk.tso.data.UserData;
import com.ymk.tso.service.UserService;

/**
 * User Controller
 * @author yemyokyaw
 *
 */
@Path("/user")
public class UserController {
	
	Logger logger = Logger.getLogger(UserController.class);

	/**
	 * signIn
	 * @param data
	 * @return
	 */
	@POST
	@Path("login")
	@Produces(MediaType.APPLICATION_JSON)    
	@Consumes(MediaType.APPLICATION_JSON)
	public LoginData signIn(LoginData data) {
		return UserService.getInstance().signIn(data);
	}

	/**
	 * getAllUsers
	 * @param val
	 * @param key
	 * @param auth
	 * @return
	 */
	@GET
	@Path("list")
	@Produces(MediaType.APPLICATION_JSON)
	public List<UserData> getAllUsers(@QueryParam("searchVal") String val, @QueryParam("uid") String key, @QueryParam("auth") String auth) {
		return UserService.getInstance().getAll((val != null) ? val : "", key, auth);
	}

	/**
	 * getById
	 * @param id
	 * @param key
	 * @param auth
	 * @return
	 */
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public UserData getById(@QueryParam("id") Long id, @QueryParam("uid") String key, @QueryParam("auth") String auth) {
		return UserService.getInstance().getById((id != null) ? id : 0L, key, auth);
	}
	
	/**
	 * save
	 * @param data
	 * @param key
	 * @param auth
	 * @return
	 */
	@POST
	@Path("add")
	@Produces(MediaType.APPLICATION_JSON)    
	@Consumes(MediaType.APPLICATION_JSON)
	public UserData save(UserData data, @QueryParam("uid") String key, @QueryParam("auth") String auth) {
		return UserService.getInstance().save(data, key, auth);
	}
	
	/**
	 * update
	 * @param data
	 * @param key
	 * @param auth
	 * @return
	 */
	@PUT
	@Path("edit")
	@Produces(MediaType.APPLICATION_JSON)    
	@Consumes(MediaType.APPLICATION_JSON)
	public UserData update(UserData data, @QueryParam("uid") String key, @QueryParam("auth") String auth) {
		return UserService.getInstance().update(data.getId(), data, key, auth);
	}
	
	/**
	 * delete: update the record's status as deleted
	 * @param data
	 * @param key
	 * @param auth
	 * @return
	 */
	@PUT
	@Path("delete")
	@Produces(MediaType.TEXT_PLAIN)    
	@Consumes(MediaType.APPLICATION_JSON)
	public int delete(UserData data, @QueryParam("uid") String key, @QueryParam("auth") String auth) {
		return UserService.getInstance().delete(data.getId(), key, auth);
	}
	
	/**
	 * clean
	 * @param id
	 * @param key
	 * @param auth
	 * @return
	 */
	@DELETE
	@Path("clean")
	@Produces(MediaType.APPLICATION_JSON)    
	@Consumes(MediaType.APPLICATION_JSON)
	public String clean(@QueryParam("id") Long id, @QueryParam("uid") String key, @QueryParam("auth") String auth) {
		return UserService.getInstance().clean(id, key, auth);
	}

}
