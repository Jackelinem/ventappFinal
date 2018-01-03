package ec.edu.ups.Data;

import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;


import ec.edu.ups.Model.Propiedad;

public class PropiedadRepository {
	
	
	@Inject
	private EntityManager em;
	
	
	public Propiedad findById(int id) {
		return em.find(Propiedad.class, id);
	}
	
	
	public Propiedad findByEmail(String email) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery< Propiedad > criteria = cb.createQuery(Propiedad .class);
		Root< Propiedad > propiedad = criteria.from( Propiedad.class);
		criteria.select(propiedad).where(cb.equal(propiedad.get("email"), email));
		return em.createQuery(criteria).getSingleResult();
	}
	

	public Propiedad findByCost(String cost) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery< Propiedad > criteria = cb.createQuery(Propiedad .class);
		Root< Propiedad > propiedad = criteria.from( Propiedad.class);
		criteria.select(propiedad).where(cb.equal(propiedad.get("costo"), cost));
		return em.createQuery(criteria).getSingleResult();
	}

	
	
	public List<Propiedad> findAllOrderedByName() {
		CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Propiedad> criteria = cb.createQuery(Propiedad.class);
        Root<Propiedad> propiedad = criteria.from(Propiedad.class);
        criteria.select(propiedad).orderBy(cb.asc(propiedad.get("name")));
        return em.createQuery(criteria).getResultList();
	}
	
	
	
}
