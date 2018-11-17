package Controllers;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import Dao.UsuarioDAO;
import Entity.Usuario;

@Path("/usuario")
public class UsuarioControler {
		
	public UsuarioControler(){
	}
	
	@GET @Path("/{id}") @Produces(MediaType.APPLICATION_JSON)
	public Response getUserById(@PathParam("id") String msg) {
		int id=Integer.valueOf(msg);
		Usuario usuario = UsuarioDAO.getInstance().findById(id);
		return Response.status(201).entity(usuario).build();
	}	
}
