package Controllers;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import Dao.TipoTrabajoDAO;
import Entity.TipoTrabajo;

@Path("/tipo-trabajo")
public class TipoTrabajoController {
	
	@GET @Path("/{id}") @Produces(MediaType.APPLICATION_JSON)
	public Response getTipoTrabajoById(@PathParam("id") String msg) {
		int id=Integer.valueOf(msg);
		TipoTrabajo tt = TipoTrabajoDAO.getInstance().findById(id);
		return Response.status(201).entity(tt).build();
	}	
}
