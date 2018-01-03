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
	
	
	public Persona findByEmail(String email) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery< Persona > criteria = cb.createQuery( Persona .class);
		Root< Persona > user = criteria.from( Persona .class);
		// Swap criteria statements if you would like to try out type-safe criteria queries, a new
        // feature in JPA 2.0
        // criteria.select(member).where(cb.equal(member.get(Member_.name), email));
		criteria.select(user).where(cb.equal(user.get("email"), email));
		return em.createQuery(criteria).getSingleResult();
	}
	
	public List<Persona> findAllOrderedByName() {
		CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Persona> criteria = cb.createQuery(Persona.class);
        Root<Persona> user = criteria.from(Persona.class);
        criteria.select(user).orderBy(cb.asc(user.get("name")));
        return em.createQuery(criteria).getResultList();
	}
	

}
