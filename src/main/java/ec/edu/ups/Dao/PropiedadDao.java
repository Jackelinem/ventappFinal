package ec.edu.ups.Dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import ec.edu.ups.Model.Propiedad;


/*
 * objeto de acceso a datos de propiedad
 */
@Stateless
public class PropiedadDao {
	
	@Inject 
	private EntityManager em;
	
	
	/*
	 * metodo de guardar o actualizar Propiedad
	 */
	public void guardar(Propiedad propiedad) {
		Propiedad p =leer(propiedad.getCodigo());
		
		System.out.println(propiedad.getCodigo());
		if(p==null) {
			System.out.println("Inserta ");
			insertar(propiedad);
		}else {
			System.out.println("actualiza ");
			actualizar(propiedad);
		}
	
	}	
	
	/*
	 * insertar nueva Propiedad
	 */
	public void insertar(Propiedad propiedad) {	
		em.persist(propiedad);
	}
	
	/*
	 * metodo de actualizar Propiedad
	 */
	public void actualizar(Propiedad propiedad) {
		em.merge(propiedad);
	}

	/*
	 * metodo de eliminar Propiedad
	 */
	public void eliminar(int codigo) {
		em.remove(leer(codigo));
	}
	
	/*
	 * metodo de leer propiedad por ID
	 */
	public Propiedad leer(int codigo) {
		Propiedad p =em.find(Propiedad.class, codigo);
		
		return p;
	}
	
	/*
	 * metodo de listar propiedades
	 */
	public List<Propiedad> listadoPropiedades(){
		String jpql = "Select p From Propiedad p";
		Query query = em.createQuery(jpql,Propiedad.class);
		List<Propiedad> listado = query.getResultList();
		
		
		System.out.println(listado.size());
		return listado;
		
	}
	
}
