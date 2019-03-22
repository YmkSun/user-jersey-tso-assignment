package com.ymk.tso.controller.rest;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.ymk.tso.data.KeyValueData;
import com.ymk.tso.util.Utility;

/**
 * Home Controller
 * @author yemyokyaw
 *
 */
@Path("/base")
public class HomeController {
	
	/**
	 * getUserRoleList get from enum
	 * @return
	 */
	@GET
	@Path("roleList")
	@Produces(MediaType.APPLICATION_JSON)
	public List<KeyValueData> getUserRoleList() {
		return Utility.getUserRoleList();
	}
	
	/**
	 * getDeptList get from enum
	 * @return
	 */
	@GET
	@Path("departments")
	@Produces(MediaType.APPLICATION_JSON)
	public List<KeyValueData> getDeptList() {
		return Utility.getDepartmentList();
	}
}
