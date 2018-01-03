package ec.edu.ups.Controller;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;

import ec.edu.ups.Dao.PersonaDao;
import ec.edu.ups.Model.Persona;
import ec.edu.ups.Model.Telefono;

/*
 * controlador de Persona
 */
@ManagedBean
@ViewScoped
public class PersonaControlador {
	
	@Inject
	private PersonaDao pdao;
	
	//instancia objeto persona
	private Persona persona;
	
	//lista de personas
	private List<Persona> personas;
	
	// campo id para saber si es registro nuevo
	private int id;
	
	@PostConstruct
	public void init(){
		persona = new Persona();
		loadPersonas();
		persona.addTelefono(new Telefono());
		
	}

	/*
	 * getters and setters
	 */
	public int getId() {
		return id;
	}



	public void setId(int id) {
		this.id = id;
		
		loadDatosEditar(id);////con parametros
		
	}
	
	public Persona getPersona() {
		return persona;
	}

	public List<Persona> getPersonas() {
		return personas;
	}



	public void setPersonas(List<Persona> personas) {
		this.personas = personas;
	}



	public void setPersona(Persona persona) {
		this.persona = persona;
	}
	
	/*
	 * llena la lista de personas
	 */
	public void loadPersonas(){
		
		personas = pdao.listarPersona();
		
	}
	
	/*
	 * carga los datos que se van a editar mediante la busqueda con un id
	 */
	
	public String loadDatosEditar(int id){
		System.out.println("Cargando datos de personas a editar" + id);
		persona = pdao.leer(id);
		return "crearPersona";
		
		
	}
	
   public String eliminarDatos(int id){
		
	   pdao.borrar(id);
	   loadPersonas();
		return null;
		
	}
   
   /*
    * metodo que me permite borrar un telefono de la lista
    */
   
   public String eliminarTelefono(Telefono tel){
	   
	   try {
			if(this.id!=0) {
				System.out.println("hola entro a eliminar");
				persona.getTelefonos().remove(tel);
				
				
			}else
				persona.getTelefonos().remove(tel);
		}catch (Exception e) {
			
		}
	   
		return null;
		
	}

	/*
	 * metodo que me permite guardar una Persona
	 */
	public String guardar(){
		
		System.out.println(persona);
		pdao.guardar(persona);
		
		loadPersonas();
		return "index";
	}
	
	/*
	 * metodo que me permite editar una Persona
	 */
	public String editarPersona(){
		
		System.out.println(persona);
		pdao.guardar(persona);
		
		loadPersonas();
		return "ListadoPersona";
	}
	
	/*
	 * metodo permite agregar mas telefonos
	 */
	public String agregaTelefono(){
		persona.getTelefonos().add(new Telefono());
		return null;
	}
}
