package ec.edu.ups.Controller;

import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import ec.edu.ups.Dao.ImagenDAO;
import ec.edu.ups.Model.Imagen;

/*
 * controlador de Imagen
 */
@ManagedBean
@RequestScoped
public class ImagenController {

	//bean properties
	//Instancias
	private Imagen Imagen;
	//id se va a recibir como parametro y sirve como identificador
	private String id;
	//Listado
	private List<Imagen> imagenes;
	
	
	@Inject
	private ImagenDAO iDAO;
	
	@Inject
    private FacesContext facesContext;
	
	@PostConstruct
	public void init() {
		Imagen = new Imagen();
		loadimagenes();
	}
	
	/*
	 * metodo que carga los datos propiedad a editar buscando por el id
	 */
	public String loadDatosEditar(String nombre) {
		Imagen = iDAO.leer(nombre);
		return "crearImagen"; //Llama a la pagina para que cargue el objeto persona cargado
	}
	
	public Imagen getImagen() {
		return Imagen;
	}

	public void setImagen(Imagen Imagen) {
		this.Imagen = Imagen;
	}

	public List<Imagen> getimagenes() {
		return imagenes;
	}

	public void setimagenes(List<Imagen> imagenes) {
		this.imagenes = imagenes;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		loadDatosEditar(id);
		this.id = id;
	}

	@Override
	public String toString() {
		return "ImagenController [Imagen=" + Imagen + ", imagenes=" + imagenes + ", id=" + id + ", iDAO=" + iDAO + "]";
	}
	
	public String eliminar(String nombre) {//ActionController
		System.out.println(toString());
		try{
			iDAO.borrar(nombre);
			loadimagenes();
		}catch(Exception e){
			String errorMessage = getRootErrorMessage(e);
            FacesMessage m = new FacesMessage(FacesMessage.SEVERITY_ERROR, errorMessage, "Error al eliminar Imagen especificada.");
            facesContext.addMessage(null, m);
		}
		return "listadoimagenes";
	}
	
	public String guardar() {//ActionController
		System.out.println(toString());
		
		try{
			if (this.id!=null){
				iDAO.insertar(Imagen);
			}else{
				iDAO.actualizar(Imagen);
			}
			loadimagenes();
		}catch(Exception e){
			String errorMessage = getRootErrorMessage(e);
            FacesMessage m = new FacesMessage(FacesMessage.SEVERITY_ERROR, errorMessage, "No se pudo guardar.");
            facesContext.addMessage(null, m);
		}
		loadimagenes();
		return "listadoimagenes";
	}
	/*
	 * metodo para obtener la lista de imagenes
	 */
	private void loadimagenes() {
		imagenes=iDAO.listadoImagenes();
	}
	
	/*
	 * metodo para mostrar mensaje de validacion
	 */
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
