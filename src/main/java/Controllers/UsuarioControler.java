package Controllers;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import Dao.UsuarioDAO;
import Entity.Trabajo;
import Entity.Usuario;
import WebExceptions.RecursoDuplicado;
import WebExceptions.RecursoNoExiste;

@Path("/usuario")
public class UsuarioControler {
		
	public UsuarioControler(){
	}
	
	@GET @Path("/{id}") @Produces(MediaType.APPLICATION_JSON)
	public Response getUserById(@PathParam("id") String msg) {
		int id = Integer.valueOf(msg);
		Usuario usuario = UsuarioDAO.getInstance().findById(id);
		return Response.status(201).entity(usuario).build();
	}
	
	@GET @Path("/trabajosEnviados/{id}") @Produces(MediaType.APPLICATION_JSON)
	public Response getTrabajosEnviadosById(@PathParam("id") String msg) {
		int id = Integer.valueOf(msg);
		List<Trabajo> trabajos = UsuarioDAO.getInstance().findAllTrabajosEnInvestigacionEnviados(id);
		return Response.status(201).entity(trabajos).build();
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response createUsuario(Usuario usuario) {
		Usuario result= UsuarioDAO.getInstance().persist(usuario);
		if(result==null) {
			throw new RecursoDuplicado(usuario.getId());
		}else {
			return Response.status(201).entity(usuario).build();
		}
	}
	
	@PUT
	@Path("/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response updateUsuario(@PathParam("id") int id, Usuario usuario) {
		Usuario result= UsuarioDAO.getInstance().update(id, usuario);
		if(result==null) {
			throw new RecursoNoExiste(id);
		}else {
			return Response.status(200).entity(usuario).build();
		}
	}
	
	@POST
	@Path("/asignar/{idUsuario}/{idTrabajo}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response asignarTrabajoRevisor(@PathParam("idUsuario") Integer pidUsuario,
											@PathParam("idTrabajo") Integer pidTrabajo) throws Exception {
		Boolean result = UsuarioDAO.getInstance().addTrabajoPendiente(pidUsuario, pidTrabajo);
		if (result) {
			Usuario usuario = UsuarioDAO.getInstance().findById(pidUsuario);
			return Response.status(201).entity(usuario).build();
		}
		throw new Exception("Ocurrio un problema durante la asignacion del Trabajo con id:"+pidTrabajo+" al usuario con id: "+pidUsuario);
	}
	
	@POST
	@Path("/aceptar/{idUsuario}/{idTrabajo}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response aceptarTrabajo(@PathParam("idUsuario") int pidUsuario,
											@PathParam("idTrabajo") int pidTrabajo) throws Exception {
		Boolean result = UsuarioDAO.getInstance().aceptarTrabajo(pidUsuario, pidTrabajo);
		if (result) {
			Usuario usuario = UsuarioDAO.getInstance().findById(pidUsuario);
			return Response.status(201).entity(usuario).build();
		}
		throw new Exception("Ocurrio un problema al aceptar el Trabajo con id:"+pidTrabajo+" al usuario con id: "+pidUsuario);
	}
	
	@GET @Path("/trabajosRevisados/{id}") @Produces(MediaType.APPLICATION_JSON)
	public Response getTrabajosRevisadosById(@PathParam("id") String msg) {
		int id=Integer.valueOf(msg);
		List<Trabajo> trabajos = UsuarioDAO.getInstance().findAllTrabajosEnEvaluacion(id);
		return Response.status(201).entity(trabajos).build();
	}
	
}
