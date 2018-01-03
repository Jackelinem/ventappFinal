package ec.edu.ups.Dao;

import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import ec.edu.ups.Model.Imagen;

/*
 * objeto de acceso a datos de Imagen
 */
@Stateless//Especificacion de tipo de EJB
public class ImagenDAO {
	
	@Inject //Inyección de EntityManager para uso de persistencia de objetos
	EntityManager em;
	
	//Métodos CRUD
	public void insertar(Imagen img){
		em.persist(img);
	}
	
	public Imagen leer(String nombre) {
		Imagen img = em.find(Imagen.class, nombre);
		return img;
	}
	
	public void actualizar(Imagen img){
		em.merge(img);
	}
	
	public void borrar(String nombre){
		em.remove(leer(nombre));
	}
	
	//Objeto de Negocio Para guardar o actualizar Ciudad
	public void guardar(Imagen im){
		Imagen img = leer(im.getNombreImagen());
		if(img==null)
			insertar(im);
		else
			actualizar(im);
	}
	
	//Listado de todos las imágenes
	public List<Imagen> listadoImagenes(){
		String sql = "SELECT n FROM Imagen n";
		Query q =em.createQuery(sql,Imagen.class );
		List<Imagen> catalogos = q.getResultList();
		return catalogos;
	}
}
