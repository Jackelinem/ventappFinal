package ec.edu.ups.Services;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;

import ec.edu.ups.Dao.PersonaDao;
import ec.edu.ups.Model.Persona;
import ec.edu.ups.Utils.Response;
import ec.edu.ups.Data.UserRepository;

@Path("/user")
public class UserService {
	

	@Inject
	PersonaDao personaDao;
	private Persona persona;
	
	@Inject
	private UserRepository repository;
	
	
	/*
	 * 
	 * WS para el inicio de session
	 */
	@GET
	@Path("/login")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Persona loginResponse(
			@QueryParam("email")String email, //parametros del login 
			@QueryParam("pass") String password){
		
			persona = new Persona();
			persona = personaDao.buscarUser(email,password);
			 if(persona != null)
				{
				System.out.println("redireccion");

			//	System.out.println("envio parametros" +persona.toString());
				return persona;
				}
		
			return null;
	}
		
	/*
	 * WS para registrar una cuenta 
	 */
	@POST
	@Path("/register")
	@Produces("application/json")
	@Consumes("application/json")
	public Response register(Persona persona) {
		Response rs= new Response();
		
		try {
			
			personaDao.guardar(persona);
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
	public Persona lookupUserById(@PathParam("id") int id) {
		Persona user = repository.findById(id);
		if (user == null) {
			//throw new WebApplicationException(Response.Status.NOT_FOUND);
			System.out.println("no existe");
		}
		return user;
	}
	
	
	//lista de todos los usuarios existentes
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Persona> listAllUsers() {
		return repository.findAllOrderedByName();
	}

	
	/*Lista los usuarios por el id 
	 * http://localhost:8080/Ventapp/ventapp/user/2
	 * */
	 


	 
		@GET
	//	@Path("/{email:[0-9][0-9][.]}")//cualquier codigo que sea int 
		@Produces(MediaType.APPLICATION_JSON)
		@Path("/email")
		@Consumes("application/json")
		public Persona lookupUserByEmail(
				@QueryParam("email") String email) {
			Persona user = repository.findByEmail(email);
			if (user == null) {
				//throw new WebApplicationException(Response.Status.NOT_FOUND);
				System.out.println("no existe");
			}
			return user;
		}
		
	
	
	}



	

