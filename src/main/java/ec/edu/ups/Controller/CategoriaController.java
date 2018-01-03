package ec.edu.ups.Controller;

import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import ec.edu.ups.Dao.CategoriaDao;
import ec.edu.ups.Model.Categoria;

/*
 * Controlador de categorias
 */
@ManagedBean
@SessionScoped
public class CategoriaController {
		
	//bean properties
		private Categoria categoria;//instancia objeto Catenogoria
		private List<Categoria> listCategorias;//instancia lista de categorias
		
		
		@Inject
		private CategoriaDao categoriaDao;
		
		@Inject
	    private FacesContext facesContext;
		
		@PostConstruct
		public void init() {
			categoria =new Categoria();
			listCategorias=categoriaDao.listadoCategoria();
			
		}
		
		
		//bean controller
		
		public String loadDatosEditar(int codigo) {
			
			categoria =categoriaDao.leer(codigo);
			return"categoriasEditar";
		}



		/**
		 * Metodo para guardar un nuevo registro de una Categoria
		 * @return null
		 */
		public String guardarCategoria(){
			try{
				categoriaDao.save(categoria);
				init();
			}catch(Exception e){
				String errorMessage = getRootErrorMessage(e);
	            FacesMessage m = new FacesMessage(FacesMessage.SEVERITY_ERROR, errorMessage, "Error al eliminar catalogo especificada.");
	            facesContext.addMessage(null, m);
			}
			
			return null;
		}
		
		/**
		 * Meto para elminar un registro de Categoria
		 * @param id para identificar que registro eliminar
		 * @return
		 */
		public String eliminarCategoria(int id){
			categoriaDao.eliminar(id);
			init();
			return null;
		}
		
		/**
		 * Metodo para actualizar los datos de una Categoria
		 * @param id identifica que registro quiere actualizar 
		 * @return
		 */
		public String actualizarCategoria(int id){
			 Categoria cate = categoriaDao.leer(id);
			 this.categoria=cate;
			return null;
		}
	
		//getters and setters
		
		public Categoria getCategoria() {
			return categoria;
		}

		public void setCategoria(Categoria categoria) {
			this.categoria = categoria;
		}

		public List<Categoria> getListCategorias() {
			return listCategorias;
		}

		public void setListCategorias(List<Categoria> listCategorias) {
			this.listCategorias = listCategorias;
		}
		
		
		public CategoriaDao getCategoriaDao() {
			return categoriaDao;
		}



		public void setCategoriaDao(CategoriaDao categoriaDao) {
			this.categoriaDao = categoriaDao;
		}
		
		private String getRootErrorMessage(Exception e) {
	        String errorMessage = "Registration failed. See server log for more information";
	        if (e == null) {
	            return errorMessage;
	        }
	        Throwable t = e;
	        while (t != null) {
	            errorMessage = t.getLocalizedMessage();
	            t = t.getCause();
	        }
	        return errorMessage;
	    }
	
}
