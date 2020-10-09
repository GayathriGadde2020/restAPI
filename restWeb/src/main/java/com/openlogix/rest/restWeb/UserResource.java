package com.openlogix.rest.restWeb;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import Implementation.UserDAO;

/**
 * Root resource (exposed at "myresource" path)
 */
@Path("users")
public class UserResource {

	/**
	 * Method handling HTTP GET requests. The returned object will be sent to the
	 * client as "text/plain" media type.
	 *
	 * @return String that will be returned as a text/plain response.
	 */
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public String getUsersFromDB() {
		System.out.println("User called changed again");
		UserDAO userInfo = new UserDAO();
		String data = userInfo.getUsers();
		return data;
	}

}
