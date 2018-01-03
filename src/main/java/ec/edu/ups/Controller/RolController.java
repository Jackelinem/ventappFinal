package ec.edu.ups.Controller;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;

import ec.edu.ups.Dao.RolDao;
import ec.edu.ups.Model.Propiedad;
import ec.edu.ups.Model.Rol;

/*
 * controlador del rol
 */
@ManagedBean
@ViewScoped
public class RolController {

	/*
	 * bean properties
	 */
	private Rol rol;
	private List<Rol> listRoles;//lista de roles
	private int id;
	
	@Inject
	private RolDao rolDao;
	
	
	@PostConstruct
	public void init() {
		rol =new Rol();
		loadRoles();
		
	}
	
	/*
	 * metodo para guardar nuevo rol
	 */
	public String guardar() {
		System.out.println(toString());
		
		rolDao.guardar(rol);
		init();
		return null;
	}
	
	/*
	 * metodo que me permite eliminar un rol
	 */
	public String eliminar(int codigo) {
		System.out.println(toString());
		
			try {
				
				rolDao.eliminar(codigo);
				loadRoles();
				
			}
			catch (Exception e) {
				System.out.println("EERORRORO");
				
			}
		
		
			return"Roles";
		}
	/*
	 * metodo para cargar la lista de roles
	 */
	public String loadDatosEditar(int codigo) {
		
		rol =rolDao.leer(codigo);
		return"RolesEditar";
	}

	//getters and setters
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
		loadDatosEditar(id);
	}

	public List<Rol> getListRoles() {
		return listRoles;
	}

	public void setListRoles(List<Rol> listRoles) {
		this.listRoles = listRoles;
	}

	public Rol getRol() {
		return rol;
	}

	public void setRol(Rol rol) {
		this.rol = rol;
	}
	
	private void loadRoles() {
		listRoles =rolDao.listadoRoles();
	}
	
}
