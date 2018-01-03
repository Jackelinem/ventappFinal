package ec.edu.ups.Dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import ec.edu.ups.Model.Catalogo;

/*
 * objeto de acceso a datos de Catalogo
 */
@Stateless //Especificacion de tipo de EJB
public class CatalogoDAO {

	@Inject //Inyección de EntityManager para uso de persistencia de objetos
	EntityManager em;
	
	//Métodos CRUD
	public void insertar(Catalogo cat){
		em.persist(cat);
	}
	
	public Catalogo leer(String nombre) {
		Catalogo c = em.find(Catalogo.class, nombre);
		return c;
	}
	
	public void actualizar(Catalogo cat){
		em.merge(cat);
	}
	
	public void borrar(String nombre){
		em.remove(leer(nombre));
	}
	
	//Objeto de Negocio Para guardar o actualizar Ciudad
	public void guardar(Catalogo cat){
		Catalogo c = leer(cat.getNombreCatalogo());
		if(c==null)
			insertar(cat);
		else
			actualizar(cat);
	}
	
	//Listado de todos los catálogos
	public List<Catalogo> listadoCatalogos(){
		String sql = "SELECT n FROM Catalogo n";
		Query q =em.createQuery(sql,Catalogo.class );
		List<Catalogo> catalogos = q.getResultList();
		return catalogos;
	}
	
}
