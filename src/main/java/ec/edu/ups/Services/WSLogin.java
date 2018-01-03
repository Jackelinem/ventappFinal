package ec.edu.ups.Services;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import ec.edu.ups.Dao.PersonaDao;
import ec.edu.ups.Model.Persona;

@Path("/login")
public class WSLogin {
	
	
	@Inject
	PersonaDao personaDao;
	
	private Persona persona;
	
			@GET
			@Path("/user")
			@Produces("application/json")
		public String login(
				@QueryParam ("email")String email, 
				@QueryParam("pass") String password){
				

				persona = new Persona();
				persona = personaDao.buscarUser(email,password);
				
				
				 if(persona != null)
					{
					System.out.println("redireccion");
					return"index";
					}
			
					
				return "No existe user";
			}
	
			
			
	
	
}
