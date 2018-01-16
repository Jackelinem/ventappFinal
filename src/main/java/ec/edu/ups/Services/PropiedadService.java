package ec.edu.ups.Services;

import java.util.List;
import java.util.Set;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import ec.edu.ups.Dao.CategoriaDao;
import ec.edu.ups.Dao.PersonaDao;
import ec.edu.ups.Dao.PropiedadDao;
import ec.edu.ups.Dao.SectorDao;
import ec.edu.ups.Data.PropiedadRepository;
import ec.edu.ups.Model.Categoria;
import ec.edu.ups.Model.Persona;
import ec.edu.ups.Model.Propiedad;
import ec.edu.ups.Model.Sector;
import ec.edu.ups.Utils.Response;

@Path("/propiedad")
public class PropiedadService {
	
	
	@Inject
	PropiedadDao propiedadDao;
	
	@Inject
	CategoriaDao cdao;
	
	@Inject
	SectorDao sdao;
	//private Propiedad propiedad;
	
	@Inject
	PropiedadRepository repository;
	
	@Inject 
	PersonaDao personaDao;
	
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
	
	@GET
	@Path("/listabyUser")//cualquier codigo que sea int 
	@Produces(MediaType.APPLICATION_JSON)
	public Set<Propiedad> listadopropiedad(@QueryParam("id") int id){
		Set<Propiedad> ltsPropiedad= personaDao.listaPropiedadesbyUser(id).getPropiedades();
		return ltsPropiedad;
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

	@GET
	@Path("/categoriaid")
	@Produces("application/json")
	public Categoria getCategoriaId(@QueryParam("id") int id) {
		Categoria cat = cdao.read(id);
		System.out.println(cat);
		return cat;
	}
	
	@GET
	@Path("/sectorid")
	@Produces("application/json")
	public Sector getSectorId(@QueryParam("id") int id) {
		Sector s = sdao.leer(id);
		System.out.println(s);
		return s;
	}

	@GET
	@Path("/sectores")
	@Produces("application/json")
	public List<Sector> getSectores() {
		List<Sector> sectores = sdao.getSectores();		
		return sectores;
	}
	
	@GET
	@Path("/categorias")
	@Produces("application/json")
	public List<Categoria> getCategorias() {
		List<Categoria> categorias = cdao.getCategorias2();		
		return categorias;
	}
	
	@GET
	@Path("/propiedades")
	@Produces("application/json")
	public List<Propiedad> getPropiedades() {
		List<Propiedad> propiedades = propiedadDao.listadoPropiedades();		
		return propiedades;
	}
	
	@GET
	@Path("/propiedadid")
	@Produces("application/json")
	public Propiedad getPropiedadId(@QueryParam("id") int id) {
		Propiedad s = propiedadDao.leer(id);
		System.out.println(s);
		return s;
	}
	
	
	
	
	

}
