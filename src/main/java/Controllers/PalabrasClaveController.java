package Controllers;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import Dao.PalabrasClaveDAO;
import Entity.PalabrasClave;

@Path("/palabras-clave")
public class PalabrasClaveController {
	@GET @Path("/{id}") @Produces(MediaType.APPLICATION_JSON)
	public Response getPalabrasClaveById(@PathParam("id") String msg) {
		int id=Integer.valueOf(msg);
		PalabrasClave pc = PalabrasClaveDAO.getInstance().findById(id);
		return Response.status(201).entity(pc).build();
	}	
}
