package Controllers;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import Dao.LugarTrabajoDAO;
import Entity.LugarTrabajo;

@Path("/lugar-trabajo")
public class LugarTrabajoController {
	
	@GET @Path("/{id}") @Produces(MediaType.APPLICATION_JSON)
	public Response getLugarTrabajoById(@PathParam("id") String msg) {
		int id=Integer.valueOf(msg);
		LugarTrabajo lt = LugarTrabajoDAO.getInstance().findById(id);
		return Response.status(201).entity(lt).build();
	}	
}


