package Controllers;

import javax.persistence.EntityManager;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import Dao.EMF;
import Dao.UsuarioDAO;
import Entity.Usuario;

@Path("/usuario")
public class UsuarioControler {
	
	private EntityManager EM;
	
	public UsuarioControler(){
		this.EM = EMF.createEntityManager();	
	}
	
	@GET @Path("/{id}") @Produces(MediaType.APPLICATION_JSON)
	public Response getUserById(@PathParam("id") String msg) {
		Usuario user = new Usuario();
//		int id=Integer.valueOf(msg);
//		Usuario usuario = UsuarioDAO.getInstance().findById(id,this.EM);
		//return user;
		//return "<html> " + "<title>" + "Hello Jersey" + "</title>";
		return Response.status(201).entity(user).build();
	}
	
}
