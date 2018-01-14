package ec.edu.ups.Services;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
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

@Stateless
public class UserService {
	

	@Inject
	PersonaDao personaDao;
	private Persona persona;
	
	@Inject
	private UserRepository repository;
	
	@PersistenceContext
	private EntityManager em;
	
	
	public Persona find (int id) {
		return em.find(Persona.class, id);
	}
	
	public void delete(Persona user) {
		em.remove(em.contains(user) ? user : em.merge(user));
	}
	
	public void update(Persona user) {
		em.merge(user);
	}
	
		
	}
	

		
	



	

