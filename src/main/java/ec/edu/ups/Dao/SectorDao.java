package ec.edu.ups.Dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import ec.edu.ups.Model.Sector;

/*
 * objeto de acceso a datos de Provincia
 */
@Stateless 
public class SectorDao {
	
	@Inject
	private EntityManager em;
	


	/*
	 * metodo de leer provincia por ID
	 */
	public Sector leer (int id){
		Sector p = em.find(Sector.class, id);
		p.getPropiedades().size();
		return p;
		
	}
	
	
	
	/*
	 * metodo de obtener Provincia para el combo box
	 */
	public List<Sector> getSectores(){
		String sql = "SELECT distinct a FROM Sector a ";
		Query q = em.createQuery(sql,Sector.class);
		
		List<Sector> admin = q.getResultList();
		System.out.println( " Tamaño  "+admin.size());
		return admin;
	}
	
}
