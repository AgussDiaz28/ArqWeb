package Controllers;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import Dao.CalificacionDAO;
import Entity.Calificacion;

@Path("/calificacion")
public class CalificacionController {
	
	@GET @Path("/{id}") @Produces(MediaType.APPLICATION_JSON)
	public Response getCalificacionById(@PathParam("id") String msg) {
		int id=Integer.valueOf(msg);
		Calificacion calificacion = CalificacionDAO.getInstance().findById(id);
		return Response.status(201).entity(calificacion).build();
	}	
}
