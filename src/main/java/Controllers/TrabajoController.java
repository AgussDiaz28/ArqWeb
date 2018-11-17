package Controllers;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import Dao.TrabajoDAO;
import Dao.UsuarioDAO;
import Entity.Trabajo;
import Entity.Usuario;
import WebExceptions.RecursoDuplicado;

@Path("/trabajo")
public class TrabajoController {
	
	@GET @Path("/{id}") @Produces(MediaType.APPLICATION_JSON)
	public Response getTrabajoById(@PathParam("id") String msg) {
		int id = Integer.valueOf(msg);
		Trabajo trabajo = TrabajoDAO.getInstance().findById(id);
		return Response.status(201).entity(trabajo).build();
	}	
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response createTrabajo(Trabajo trabajo) {
		Trabajo result= TrabajoDAO.getInstance().persist(trabajo);
		if(result == null) {
			throw new RecursoDuplicado(trabajo.getId());
		}else {
			return Response.status(201).entity(trabajo).build();
		}
	}
}
