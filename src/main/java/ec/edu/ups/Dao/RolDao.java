package ec.edu.ups.Dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import ec.edu.ups.Model.Rol;

/*
 * objeto de acceso a datos del Rol
 */
@Stateless
public class RolDao {
	
	@Inject 
	private EntityManager em;
	
	/*
	 * metodo de guardar o actualizar rol
	 */
	public void guardar(Rol rol) {
		Rol r =leer(rol.getCodigo());
		
		System.out.println(rol.getCodigo());
		if(r==null)
			insertar(rol);
		else
			actualizar(rol);
	
	}	
	
	/*
	 * metodo de insertar rol
	 */
	
	public void insertar(Rol rol) {	
		em.persist(rol);
	}
	
	/*
	 * metodo de actualizar rol
	 */
	public void actualizar(Rol rol) {
		em.merge(rol);
	}

	/*
	 * metodo de eliminar rol
	 */
	public void eliminar(int codigo) {
		em.remove(leer(codigo));
	}
	
	/*
	 * metodo de leer rol mediante codigo
	 */
	public Rol leer(int codigo) {
		Rol r=em.find(Rol.class, codigo);
		
		return r;
	}
	
	/*
	 * metodo de obtener lista de roles
	 */
	public List<Rol> listadoRoles(){
		String jpql = "Select p From Rol p";
		Query query = em.createQuery(jpql,Rol.class);
		List<Rol> listado = query.getResultList();
		
		
		System.out.println(listado.size());
		return listado;
		
	}
	
	public Rol leerRol(String ro){
		try {
			String jpql = "Select p From Rol p where tipo = :ro";
			System.out.println(jpql);
			Query query = em.createQuery(jpql,Rol.class);
			query.setParameter("ro", ro);
			Rol r= (Rol) query.getSingleResult();
			
			System.out.println("Rol obtenido  "+r);
			return r;
		}
		catch (Exception e){}
		
		return null;
		
	}
	
	
	

}
