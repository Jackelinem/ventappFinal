package ec.edu.ups.Services;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import ec.edu.ups.Dao.ComentariosDAO;
import ec.edu.ups.Model.Comentarios;
import ec.edu.ups.Model.Propiedad;
import ec.edu.ups.Utils.Response;

@Path("/comentarios")
public class ComentariosService {

	@Inject
	ComentariosDAO comentariosDAO;
	
	@POST
	@Path("/register")
	@Produces("application/json")
	public Response register(@QueryParam("id") int id, @QueryParam("comentario") String comentario) {
		Response rs= new Response();
		
		try {
			Comentarios com = new Comentarios();
			com.setId(id);
			com.setComentario(comentario);
			comentariosDAO.save(com);
			rs.setCodigo(405);
			rs.setMsj("datos guardados");
			return rs;		
		}
		catch (Exception e) {
			// TODO: handle exception
			rs.setCodigo(402);
			rs.setMsj("error al inserar");
			
			return rs;
		}
		
	}
	
}
