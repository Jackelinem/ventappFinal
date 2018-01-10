package ec.edu.ups.Controller;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;

import ec.edu.ups.Dao.PersonaDao;
import ec.edu.ups.Dao.RolDao;
import ec.edu.ups.Model.*;



/*
 * controlador de Persona
 */
@ManagedBean
@ViewScoped
public class PersonaControlador {
	
	@Inject
	private PersonaDao pdao;
	
	@Inject
	private RolDao rdao;
	
	//instancia objeto persona
	private Persona persona;
	
	//lista de personas
	private List<Persona> personas;
	
	// campo id para saber si es registro nuevo
	private int id;
	
	private Rol rol;
	
	private String campoNombre="";
	private String campoApellido="";
	private String campoEmail="";
	
	@PostConstruct
	public void init(){
		rol=new Rol();
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
		
		this.setPersonas(pdao.listarPersona(campoEmail,campoNombre,campoApellido));
		
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
		
		rol=rdao.leerRol("invitado");
		System.out.println(persona);
		persona.setRol(rol);
		pdao.guardar(persona);
		
		loadPersonas();
		return "indexInvitado";
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

	public String getCampoNombre() {
		return campoNombre;
	}

	public void setCampoNombre(String campoNombre) {
		this.campoNombre = campoNombre;
	}

	public String getCampoApellido() {
		return campoApellido;
	}

	public void setCampoApellido(String campoApellido) {
		this.campoApellido = campoApellido;
	}

	public String getCampoEmail() {
		return campoEmail;
	}

	public void setCampoEmail(String campoEmail) {
		this.campoEmail = campoEmail;
	}

	public Rol getRol() {
		return rol;
	}

	public void setRol(Rol rol) {
		this.rol = rol;
	}
	
	
	
}
