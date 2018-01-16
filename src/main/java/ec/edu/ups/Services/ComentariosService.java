package ec.edu.ups.Services;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import ec.edu.ups.Dao.ComentariosDAO;
import ec.edu.ups.Dao.PropiedadDao;
import ec.edu.ups.Model.Comentarios;
import ec.edu.ups.Model.Propiedad;
import ec.edu.ups.Utils.Response;

@Path("/comentarios")
public class ComentariosService {

	@Inject
	ComentariosDAO comentariosDAO;
	
	@Inject
	PropiedadDao propiedadDAO;
	
	
	@GET
	@Path("/register")
	@Produces("application/json")
	public boolean register(@QueryParam("id") int id, @QueryParam("comentario") String comentario) {
		
		
		try {
			
			Propiedad p = propiedadDAO.leer(id);
			
			Comentarios com = new Comentarios();
			com.setComentario(comentario);
			com.setPropiedad(p);
			System.out.println("-> "+com.toString());
			comentariosDAO.save(com);
			//p.getLtscomentarios().add(com);
			//propiedadDAO.actualizar(p);
			
			return true;		
		}
		catch (Exception e) {
			// TODO: handle exceptio
			e.printStackTrace();
			return false;
		}
		
	}
	
}
