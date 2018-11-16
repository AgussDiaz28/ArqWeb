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

import Dao.UsuarioDAO;
import Entity.Usuario;

@Path("/usuario")
public class UsuarioControler {
	
	@GET
	@Path("/")
	@Produces(MediaType.APPLICATION_JSON) 
	public List<Usuario> getAllUsuarios() {
		return UsuarioDAO.getInstance().findAll();
	}
	
	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Usuario getUsuarioById(@PathParam("id") String msg) throws Exception {
		int id = Integer.valueOf(msg);
		Usuario usuario = UsuarioDAO.getInstance().findById(id);
		if(usuario!=null)
			return usuario;
		else
			throw new Exception();
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response createUsuario(Usuario usuario) {
		Usuario result = UsuarioDAO.getInstance().persist(usuario);
		if	(result != null) {
			return Response.status(201).entity(usuario).build();
		}else {
			return Response.status(404).build();
		}
	}
}
