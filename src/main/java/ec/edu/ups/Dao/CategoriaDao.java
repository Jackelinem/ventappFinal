package ec.edu.ups.Dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import ec.edu.ups.Model.Categoria;

/*
 * objeto de acceso a datos de la entidad Categoria
 */
@Stateless
public class CategoriaDao {
	
	

		
		@Inject 
		private EntityManager em;
		
		/*
		 * metodo de guardar o actualizar nuevo categoria
		 */
		public void save( Categoria cat){
			System.out.println("Categoria "+cat.getCodigo());
			if(em.find(Categoria.class, cat.getCodigo())==null)
				insertar(cat);
			else
				actualizar(cat);
		}
		
		/*
		 * metodo de insertar nuevo categoria
		 */
		public void insertar(Categoria categoria) {	
			em.persist(categoria);
		}
		
		/*
		 * metodo de actualizar  categoria
		 */
		public void actualizar(Categoria categoria) {
			em.merge(categoria);
		}

		/*
		 * metodo de eliminar  categoria
		 */
		public void eliminar(int codigo) {
			em.remove(leer(codigo));
		}
		
		/*
		 * metodo de buscar categoria
		 */
		public Categoria leer(int codigo) {
			Categoria c=em.find(Categoria.class, codigo);
			return c;
		}
		
		/*
		 * metodo de leer todas las categorias
		 */
		public List<Categoria> listadoCategoria(){
			String jpql = "Select p From Categoria p";// p es un alias
			Query query = em.createQuery(jpql,Categoria.class);
			List<Categoria> listado = query.getResultList();
			
			
			System.out.println(listado.size());
			return listado;
			
		}
		
		
		
	
	
	
	

}
