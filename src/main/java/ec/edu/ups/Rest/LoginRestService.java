package ec.edu.ups.Rest;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import ec.edu.ups.Dao.PersonaDao;
import ec.edu.ups.Data.UserRepository;
import ec.edu.ups.Model.Persona;



@Path("/authentication")
public class LoginRestService {
	

	@Inject
	PersonaDao personaDao;
	private Persona persona;
	
	@Inject
	private UserRepository repository;
	

	
	//@GET
	//@Produces(MediaType.APPLICATION_JSON)
	public String verify() {
		System.out.println("Usuario logueado!!");
		return "OK";
	}
	
	/*
	 * 
	 * WS para el inicio de session
	 */
	@GET
	@Path("/query")
	@Produces(MediaType.APPLICATION_JSON)
	public Persona loginResponse(
			@QueryParam("email")String email, //parametros del login 
			@QueryParam("pass") String password){
		

		Persona persona = repository.findByEmail(email,password);
		
		System.out.println("Autenticado: " + persona.toString());
		System.out.println("authentication");
		/*
			persona = new Persona();
			persona = personaDao.buscarUser(email,password);
			try{
			 if(persona != null)
				{

				System.out.println("Autenticado: " + persona.toString());
				System.out.println("authentication");*/
				
				//verify();

				return persona;
				}
			//}
		/*	catch (Exception e){
				
				System.out.println("user no existe");
			
				
			}*/
			
		//	return null;
		
//	}
	
	


}
