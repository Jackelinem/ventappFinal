package ec.edu.ups.Dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import ec.edu.ups.Model.Ciudad;

/*
 * objeto de acceso a datos de Ciudad
 */
@Stateless //Especificacion de tipo de EJB
public class CiudadDAO {
	
	@Inject //Inyección de EntityManager para uso de persistencia de objetos
	EntityManager em;
	
	//Métodos CRUD
	public void insertar(Ciudad ciudad){
		em.persist(ciudad);
	}
	
	public Ciudad leer(String nombre) {
		Ciudad c = em.find(Ciudad.class, nombre);
		return c;
	}
	
	public void actualizar(Ciudad ciudad){
		em.merge(ciudad);
	}
	
	public void borrar(String nombre){
		em.remove(leer(nombre));
	}
	
	//Objeto de Negocio Para guardar o actualizar Ciudad
	public void guardar(Ciudad ciudad){
		Ciudad c = leer(ciudad.getNombreCiudad());
		if(c==null)
			insertar(ciudad);
		else
			actualizar(ciudad);
	}
	
	//Listado de todos las ciudades
	public List<Ciudad> listadoCiudades(){
		String sql = "SELECT n FROM Ciudad n";
		Query q =em.createQuery(sql,Ciudad.class );
		List<Ciudad> ciudades = q.getResultList();
		return ciudades;
	}
	
}
