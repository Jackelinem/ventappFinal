package ec.edu.ups.Dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import ec.edu.ups.Model.Persona;

/*
 * objeto de acceso a datos de Persona
 */
@Stateless
public class PersonaDao {

	@Inject
	private EntityManager em;
	
	
	/*
	 * metodo de guardar o actualizar nuevo Persona
	 */
	public void guardar (Persona persona) {
		
		Persona p =leer(persona.getCodigo());
		
		if(p==null)
			insertar(persona);
		else
			actualizar(persona);
	}
	
	
	/*
	 * metodo de insertar nuevo Persona
	 */
	public void insertar(Persona persona) {
		em.persist(persona);
	}
	
	/*
	 * metodo de  actualizar nuevo Persona
	 */
	public void actualizar (Persona persona) {
		em.merge(persona);
	}
	
	/*
	 * metodo de borrar Persona
	 */
	public void borrar(int codigo) {
		em.remove(leer(codigo));
	}
	
	/*
	 * metodo de buscar Persona
	 */
	public Persona leer (int codigo) {
		Persona p = em.find(Persona.class, codigo);
		return p;
	}
	
	/*
	 * metodo de listar personas
	 */
	public List<Persona> listarPersona(String emp, String nom, String ap){
		String sql = "SELECT p FROM Persona p where email like :emp "
				+ "and nombres like :nom "
				+ "and apellido like :ap";
		Query q = em.createQuery(sql,Persona.class);
		q.setParameter("emp", emp+"%");
		q.setParameter("nom", nom+"%");
		q.setParameter("ap", ap+"%");
		List<Persona> list = q.getResultList();
		return list;
	}
	
	/*
	 * metodo de buscar personas para logueo
	 */
	/*public Persona buscar(Persona persona) {
		Persona usuario =null;
		
		try {
			Query query = em.createQuery("SELECT u.email, u.password FROM Persona u WHERE u.email = ?1 and u.password = ?2");
			query.setParameter(1, persona.getEmail());
			query.setParameter(2, persona.getPassword());
			System.out.println("LLegue user");
			
			List<Persona> listaUsuario = query.getResultList();
			if (!listaUsuario.isEmpty()) {
				usuario = listaUsuario.get(0);
			}
			System.out.println("de vuelta" +listaUsuario.size());
			return usuario;
		} catch (Exception e) {
			throw e;
		}
		

	}*/
	
	
	/*
	 * metodo de buscar persona por email
	 */
	public List<Persona> getPersonasXemail(String email){
		String sql = "SELECT a FROM Persona a "
				+ "WHERE email =?";
		Query q = em.createQuery(sql,Persona.class);
		q.setParameter(1, email);
		List<Persona> admin = q.getResultList();
		System.out.println(email + " Tamaño  "+admin.size());
		return admin;
	}
	
	/*
	 * metodo de buscar  persona para logueo
	 */
	public Persona buscarUser(String email, String pass) {
		
		
		try {
			Query query = em.createQuery("SELECT u FROM Persona u WHERE u.email = :email and u.password = :pass",Persona.class);
			
			query.setParameter("email", email);
			query.setParameter("pass", pass);
			
			Persona p= (Persona) query.getSingleResult();
			if (p!= null)
				return p;
			
		}
		catch (Exception e){}
		
		return null;
	}
	
	
	/*
	 * metodo de guardar o actualizar Persona
	 */
	public void guardaPersona(Persona persona) {
		Persona p = leer(persona.getCodigo());
		if(p==null) {
			guardaPersona(persona);
		}else {
			actualizar(persona);
		}
	}
}
