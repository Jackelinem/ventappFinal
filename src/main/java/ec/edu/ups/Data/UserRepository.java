package ec.edu.ups.Data;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import ec.edu.ups.Model.Persona;

@ApplicationScoped
public class UserRepository {
	
	@Inject
	private EntityManager em;
	
	
	public Persona findById(int id) {
		return em.find(Persona.class, id);
	}
	
	
	public Persona findByEmail(String email, String pass) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery< Persona > criteria = cb.createQuery( Persona .class);
		Root< Persona > user = criteria.from( Persona .class);
		criteria.select(user).where(cb.equal(user.get("email"), email) ,(cb.equal(user.get("password"), pass)));
		try{
			Persona persona = em.createQuery(criteria).getSingleResult();
			return persona;
		}catch (Exception e) {
			// TODO: handle exception
			return null;
		}
		 
	}
	
	public List<Persona> findAllOrderedByName() {
		CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Persona> criteria = cb.createQuery(Persona.class);
        Root<Persona> user = criteria.from(Persona.class);
        criteria.select(user).orderBy(cb.asc(user.get("name")));
        return em.createQuery(criteria).getResultList();
	}
	

}
