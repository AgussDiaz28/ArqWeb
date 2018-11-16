package Controllers;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import Dao.TrabajoDAO;
import Entity.Trabajo;

@Path("/trabajo")
public class TrabajoController {
	
	@GET
	@Produces(MediaType.APPLICATION_JSON) 
	public List<Trabajo> getAllUsuarios() {
		return TrabajoDAO.getInstance().findAll();
	}
	
	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Trabajo getUsuarioById(@PathParam("id") String msg) throws Exception {
		int id = Integer.valueOf(msg);
		Trabajo trabajo = TrabajoDAO.getInstance().findById(id);
		if(trabajo!=null)
			return trabajo;
		else
			throw new Exception();
	}

}
