package ec.edu.ups.Controller;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;

import ec.edu.ups.Dao.ProvinciaDAO;
import ec.edu.ups.Model.Provincia;
import ec.edu.ups.Model.Sector;


/*
 * controlador provincia
 */
@ManagedBean
@ViewScoped
public class ProvinciaControlador {
	
	@Inject
	private ProvinciaDAO pdao; //instancia obj de acceso a datos de la provincia
	
	private Provincia provincia = null;
	
	//coleecion de provincias para leer la lista
	private List<Provincia> provincias;
	
	private int id;
	
	@PostConstruct
	public void init(){
		provincia = new Provincia();
		loadProvincias();
		
		
		provincia.addSector(new Sector());
		
	}

	
/*
 * carga la lista de provincias
 */
	public void loadProvincias(){
		
		provincias = pdao.listaProvincias();
		
	}
	/*
	 * carga los datos de la provincia que se van a editar
	 */
	public String loadDatosEditar(int id){
		System.out.println("Cargando datos de personas a editar" + id);
		provincia = pdao.leer(id);
		return "mantenimientoSector";
		
		
	}
	
   public String eliminarDatos(int id){
		
		
	   
	   pdao.borrar(id);
		return null;
		
	}//eliminar
   
   /*public String eliminarSector(Sector sec){
	   
	   try {
			if(this.id!=0) {
				System.out.println("hola entro a eliminar");
				provincia.getSectores().remove(sec);
				
			}else
				provincia.getSectores().remove(sec);
		}catch (Exception e) {
			
		}
	   
		return null;
		
	}//eliminar

	*/
   /*
    * me permite guardar una nueva provincia con sus sectores
    */
	public String guardar(){
		
		System.out.println("guardando Provincia "+provincia);
		
		pdao.guardar(provincia);
		loadProvincias();
		
		return "listadoProvincia";
	}
	
	//me permite agregar mas sectores
	public String agregaSector(){
		provincia.getSectores().add(new Sector());
		return null;
	}
	
	
	//getters and seters

	public int getId() {
		return id;
	}



	public void setId(int id) {
		this.id = id;
		
		loadDatosEditar(id);////con parametros
		
	}
	
	
	
	
	public ProvinciaDAO getPdao() {
		return pdao;
	}


	public void setPdao(ProvinciaDAO pdao) {
		this.pdao = pdao;
	}


	public Provincia getProvincia() {
		return provincia;
	}


	public void setProvincia(Provincia provincia) {
		this.provincia = provincia;
	}


	public List<Provincia> getProvincias() {
		return provincias;
	}


	public void setProvincias(List<Provincia> provincias) {
		this.provincias = provincias;
	}

}
