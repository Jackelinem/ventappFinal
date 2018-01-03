package ec.edu.ups.Controller;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.inject.Inject;

import ec.edu.ups.Dao.ComentariosDAO;
//import ec.edu.ups.Dao.SectorDAO;
import ec.edu.ups.Model.Comentarios;
import ec.edu.ups.Model.Sector;

/*
 * controlador de comentario
 */
@ManagedBean
@SessionScoped
public class ComentarioController {
	
	private Comentarios com;//instancia objeto comentario
	
	
	private List<Comentarios> listComentarios;//lista de comentarios
	
	@Inject
	private ComentariosDAO comDao;//instancia objeto de acceso a datos de Comentario
	
	@PostConstruct
	public void init(){
		com = new Comentarios();
		listComentarios = comDao.getComentarios();
	}
	
	/*
	 * bean controllers
	 */
	
	/**
	 * Metodo para guardar un nuevo registro de un Comentario
	 * @return
	 */
	public String guardarComentario(){
		comDao.save(com);
		init();
		return null;
	}
	
	/**
	 * Meto para elminar un registro del objeto Comentario
	 * @param id para identificar que registro eliminar
	 * @return
	 */
	public String eliminarComentario(int id){
		comDao.borrar(id);
		init();
		return null;
	}
	
	/**
	 * Metodo para actualizar los datos de un Comentario
	 * @param id 
	 * @return
	 */
	public String actualizarComentario(int id){
		 Comentarios com = comDao.getComentario(id);
		 this.com=com;
		return null;
	}

/*
 * getters and setters
 */
	public Comentarios getCom() {
		return com;
	}


	public void setCom(Comentarios com) {
		this.com = com;
	}

	public List<Comentarios> getListComentarios() {
		return listComentarios;
	}


	public void setListComentarios(List<Comentarios> listComentarios) {
		this.listComentarios = listComentarios;
	}

	public ComentariosDAO getComDao() {
		return comDao;
	}

	public void setComDao(ComentariosDAO comDao) {
		this.comDao = comDao;
	}


	

}
