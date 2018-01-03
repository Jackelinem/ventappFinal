package ec.edu.ups.Services;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import ec.edu.ups.Dao.PropiedadDao;
import ec.edu.ups.Data.PropiedadRepository;
import ec.edu.ups.Model.Persona;
import ec.edu.ups.Model.Propiedad;
import ec.edu.ups.Utils.Response;

@Path("/propiedad")
public class PropiedadService {
	
	
	@Inject
	PropiedadDao propiedadDao;
	
	//private Propiedad propiedad;
	
	@Inject
	PropiedadRepository repository;
	
	
	@POST
	@Path("/register")
	@Produces("application/json")
	@Consumes("application/json")
	public Response register(Propiedad propiedad) {
		Response rs= new Response();
		
		try {
			
			propiedadDao.guardar(propiedad);
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
	
	
	@GET
	@Path("/{id:[0-9][0-9]*}")//cualquier codigo que sea int 
	@Produces(MediaType.APPLICATION_JSON)
	public Propiedad lookupUserById(@PathParam("id") int id) {
		Propiedad propiedad = repository.findById(id);
		if (propiedad == null) {
			//throw new WebApplicationException(Response.Status.NOT_FOUND);
			System.out.println("no existe");
		}
		return propiedad;
	}
	
	
	
	
	
	
	//desarrollo
	
	@GET
	@Path("/categoria")
	@Produces("application/json")
	public Propiedad getPropiedad(@QueryParam("costo") int id) {
		Propiedad propiedad = new Propiedad();
		propiedad.setCodigo(id);
		//propiedad.setCosto("");
		propiedad.setDireccion("cuenca");
		return propiedad;
	}

	
	
	
	
	
	

}
