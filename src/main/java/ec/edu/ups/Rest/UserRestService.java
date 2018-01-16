package ec.edu.ups.Rest;
import ec.edu.ups.Services.*;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Logger;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.NoResultException;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.ValidationException;
import javax.validation.Validator;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import ec.edu.ups.Dao.PersonaDao;
import ec.edu.ups.Data.UserRepository;
import ec.edu.ups.Model.Persona;
import ec.edu.ups.Services.UserService;


@Path("/user")
@RequestScoped
public class UserRestService {
	
	@Inject
	private Logger log;
	
	@Inject
	private Validator validator;
	
	@Inject
	private UserRepository repository;
	
	@Inject
	UserService userService;
	
	@Inject 
	PersonaDao personaDao;
	
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Persona> listAllUsers() {
		return repository.findAllOrderedByName();
	}
	
	

	@GET
	@Path("/{id:[0-9][0-9]*}")
	@Produces(MediaType.APPLICATION_JSON)
	public Persona lookupUserById(@PathParam("id") int id) {
		Persona persona = repository.findById(id);
		if (persona == null) {
			System.out.println("no found");
			throw new WebApplicationException(Response.Status.NOT_FOUND);
		}
		return persona;
	}
	
	
	/**
     * Creates a new user from the values provided. Performs validation, and will return a JAX-RS response with either 200 ok,
     * or with a map of fields, and related errors.
     */
	
	/*
	 * WS para registrar una cuenta 
	 */
	@POST
	@Path("/register")
	@Produces("application/json")
	@Consumes("application/json")
	public boolean register(Persona user) {
		Response.ResponseBuilder builder = null;
		//Response rs= new Response();
		
		try {
			
			// Validates member using bean validation
						System.out.println(user.getPassword().toString());
						//user.setPassword(new Sha256Hash(user.getPassword()).toHex());
						System.out.println(user.toString());
						validateUser(user);
			personaDao.guardar(user);
			
			return true;
				
		}catch (ConstraintViolationException ce) {
            // Handle bean validation issues
			return false;
            //builder = createViolationResponse(ce.getConstraintViolations());
        } catch (ValidationException e) {
            // Handle the unique constrain violation
            Map<String, String> responseObj = new HashMap<>();
            responseObj.put("email", "Este email ya se encuentra en uso");
            builder = Response.status(Response.Status.CONFLICT).entity(responseObj);
            return false;
        } catch (Exception e) {
            // Handle generic exceptions
            Map<String, String> responseObj = new HashMap<>();
            return false;
            //responseObj.put("error", e.getMessage());
            //builder = Response.status(Response.Status.BAD_REQUEST).entity(responseObj);
        }
	}
		
	
	@GET
	@Path("/delete/{id:[0-9][0-9]*}")
	@Produces(MediaType.APPLICATION_JSON)
	public Persona dropUserById(@PathParam("id") int id) {
		Persona user = repository.findById(id);
		if (user == null) {
			throw new WebApplicationException(Response.Status.NOT_FOUND);
		}
		userService.delete(user);
		return user;
	}
	
	@GET
	@Path("/deleteByEmail")
	@Produces(MediaType.APPLICATION_JSON)
	public Persona dropUserByEmail(@QueryParam ("email") String email,
			@QueryParam("pass") String pass) {
		Persona user = repository.findByEmail(email,pass);
		if (user == null) {
			throw new WebApplicationException(Response.Status.NOT_FOUND);
		}
		userService.delete(user);
		return user;
	}
	
	
	
	
	@POST
	@Path("/update")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateUser(Persona persona) {
		Response.ResponseBuilder builder = null;
		try {
			// Validates member using bean validation
			//validateUser(user);
			
			userService.update(persona);
			
			// Create an "ok" response
			builder = Response.ok();
		} catch (ConstraintViolationException ce) {
            // Handle bean validation issues
            builder = createViolationResponse(ce.getConstraintViolations());
        } catch (ValidationException e) {
            // Handle the unique constrain violation
            Map<String, String> responseObj = new HashMap<>();
            responseObj.put("email", "Este email ya se encuentra en uso");
            builder = Response.status(Response.Status.CONFLICT).entity(responseObj);
        } catch (Exception e) {
            // Handle generic exceptions
            Map<String, String> responseObj = new HashMap<>();
            responseObj.put("error", e.getMessage());
            builder = Response.status(Response.Status.BAD_REQUEST).entity(responseObj);
        }
		return builder.build();
	}
	
	
	 /**
     * Creates a JAX-RS "Bad Request" response including a map of all violation fields, and their message. This can then be used
     * by clients to show violations.
     * 
     * @param violations A set of violations that needs to be reported
     * @return JAX-RS response containing all violations
     */
	 private Response.ResponseBuilder createViolationResponse(Set<ConstraintViolation<?>> violations) {
	        log.fine("Validation completed. violations found: " + violations.size());

	        Map<String, String> responseObj = new HashMap<>();

	        for (ConstraintViolation<?> violation : violations) {
	            responseObj.put(violation.getPropertyPath().toString(), violation.getMessage());
	        }

	        return Response.status(Response.Status.BAD_REQUEST).entity(responseObj);
	    }
	
	
	 /**
	     * <p>
	     * Validates the given Member variable and throws validation exceptions based on the type of error. If the error is standard
	     * bean validation errors then it will throw a ConstraintValidationException with the set of the constraints violated.
	     * </p>
	     * <p>
	     * If the error is caused because an existing member with the same email is registered it throws a regular validation
	     * exception so that it can be interpreted separately.
	     * </p>
	     * 
	     * @param member Member to be validated
	     * @throws ConstraintViolationException If Bean Validation errors exist
	     * @throws ValidationException If member with the same email already exists
	     */
	
	   private void validateUser(Persona persona) throws ConstraintViolationException, ValidationException {
	        // Create a bean validator and check for issues.
	        Set<ConstraintViolation<Persona>> violations = validator.validate(persona);

	        if (!violations.isEmpty()) {
	            throw new ConstraintViolationException(new HashSet<ConstraintViolation<?>>(violations));
	        }

	        // Check the uniqueness of the email address
	    /*    if (emailAlreadyExists(persona.getEmail())) {
	            throw new ValidationException("Unique Email Violation");
	        }
	    }
	*/
	   }
	   
	   /**
	     * Checks if a member with the same email address is already registered. This is the only way to easily capture the
	     * "@UniqueConstraint(columnNames = "email")" constraint from the Member class.
	     * 
	     * @param email The email to check
	     * @return True if the email already exists, and false otherwise
	     */
	
	   public boolean emailAlreadyExists(String email,String pass) {
	        Persona persona= null;
	        try {
	        	persona = repository.findByEmail(email,pass);
	        } catch (NoResultException e) {
	            // ignore
	        }
	        return persona != null;
	    }

}
