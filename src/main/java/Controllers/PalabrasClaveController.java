package Controllers;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import Dao.PalabrasClaveDAO;
import Dao.TrabajoDAO;
import Entity.PalabrasClave;
import Entity.Trabajo;
import WebExceptions.RecursoDuplicado;

@Path("/palabraClave")
public class PalabrasClaveController {
	
	@GET @Produces(MediaType.APPLICATION_JSON)
	public Response getPalabrasClave() {
		List<PalabrasClave> pc = PalabrasClaveDAO.getInstance().findAll();
		return Response.status(201).entity(pc).build();
	}	
	
	@GET @Path("/{id}") @Produces(MediaType.APPLICATION_JSON)
	public Response getPalabrasClaveById(@PathParam("id") String msg) {
		int id=Integer.valueOf(msg);
		PalabrasClave pc = PalabrasClaveDAO.getInstance().findById(id);
		return Response.status(201).entity(pc).build();
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response createPalabraClave(PalabrasClave pc) {
		PalabrasClave result= PalabrasClaveDAO.getInstance().persist(pc);
		if(result == null) {
			throw new RecursoDuplicado(pc.getId());
		}else {
			return Response.status(201).entity(pc).build();
		}
	}
}
