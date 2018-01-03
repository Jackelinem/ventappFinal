package ec.edu.ups.Dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import ec.edu.ups.Model.Provincia;

/*
 * objeto de acceso a datos de Provincia
 */
@Stateless 
public class ProvinciaDAO {
	
	@Inject
	private EntityManager em;
	
	/*
	 * metodo de guardar o actualizar Provincia
	 */
	public void guardar(Provincia p){
		
		Provincia aux = leer(p.getCodigo());
		if (aux!=null){
			actualizar(p);
		}else{
			insertar(p);
		}
		
	}
	
	/*
	 * metodo de insertar provincia
	 */
	public void insertar(Provincia p){
		
		em.persist(p);
		
	}
	
	/*
	 * metodo de actualizar Provincia
	 */
	public void actualizar (Provincia p){
		
		em.merge(p);
		
		
	}
	
	/*
	 * metodo de borrar provincia
	 */
	public void borrar (int id){
		Provincia p = leer(id);
		em.remove(p);
		
	}

	/*
	 * metodo de leer provincia por ID
	 */
	public Provincia leer (int id){
		Provincia p = em.find(Provincia.class, id);
		
		return p;
		
	}
	
	/*
	 * metodo de listar provincias
	 */
	public List<Provincia> listaProvincias(){
		
		Query query = em.createQuery("SELECT p FROM Provincia p",Provincia.class);
		List<Provincia> listado = query.getResultList();
		return listado;
	}
	
	/*
	 * metodo de obtener Provincia para el combo box
	 */
	public List<Provincia> getProvincias(String prov){
		String sql = "SELECT a FROM Provincia a "
				+ "WHERE alias =?";
		Query q = em.createQuery(sql,Provincia.class);
		q.setParameter(1, prov);
		List<Provincia> admin = q.getResultList();
		System.out.println(prov + " Tamaño  "+admin.size());
		return admin;
	}
	
}
