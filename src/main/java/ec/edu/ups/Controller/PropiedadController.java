package ec.edu.ups.Controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.model.SelectItem;
import javax.inject.Inject;

import ec.edu.ups.Dao.CategoriaDao;
import ec.edu.ups.Dao.PersonaDao;
import ec.edu.ups.Dao.PropiedadDao;
import ec.edu.ups.Dao.ProvinciaDAO;
import ec.edu.ups.Model.Categoria;
import ec.edu.ups.Model.Persona;
import ec.edu.ups.Model.Propiedad;
import ec.edu.ups.Model.Provincia;
import ec.edu.ups.Model.Sector;

/*
 * controlador de propiedad
 */
@ManagedBean
@ViewScoped
public class PropiedadController {
	
	//bean properties
	private Provincia provincia; //instancia Provincia
	private Sector sector;//instancia sector
	private Persona persona;// instancia persona
	private Categoria categoria;//instancia categoria
	
	private Propiedad propiedad;//instancia categoria
	private List<Propiedad> listpropiedades;//lista de propiedades
	private List<Provincia> provinciasDeBase;//lista provincias ya registradas
	private List<Categoria> categoriasDeBase;//lista de Categorias ya registradas
	
	//id me permite saber si es nuevo registro 
	private int id;
	
	//indice para obtener el sector de una lista de sectores que pertenece a la provincia
	private int indice;
	
	private String provin;
	
	//codigo persona para validar existencia de persona
	private int codigoPersona;
	
	//correo para buscar persona propietaria 
	private String correo="";
	
	//lista de personas de la base
	private List<Persona> listaPersonas;
	
	/*
	 * listas y campos para manejar los combos
	 */
	private List<SelectItem> listaProvincias;
	private String provi;
	
	private List<SelectItem> listaSectores;
	private String sect;
	
	private List<SelectItem> listaCategorias;
	private String cat;
	
	@Inject
	private PropiedadDao propiedadDao;
	
	@Inject
	private ProvinciaDAO provDao;
	
	@Inject
	private PersonaDao perDao;
	
	@Inject
	private CategoriaDao catDao;
	
	
	@PostConstruct
	public void init() {
		persona =new Persona();
		provincia=new Provincia();
		sector=new Sector();
		propiedad =new Propiedad();
		categoria=new Categoria();
		sector.addPropiedadr(new Propiedad());
		listaSectores= new ArrayList<SelectItem>();
		
		//carga los combos
		cargarProvincias();
		cargarCategorias();
		//carga la propiedad
		loadPropiedades();
	}

	
	/*public String guardar() {
		System.out.println(toString());
		
		propiedadDao.guardar(propiedad);
		
		return null;
	}*/
	
	public String eliminar(int codigo) {
		System.out.println("Entro a eliminar "+codigo+toString());
		
			try {
				
				propiedadDao.eliminar(codigo);
				loadPropiedades();
				
			}
			catch (Exception e) {
				System.out.println("EERORRORO");
				
			}
		return null;
	}
	
	/*
	 * metodo que carga los datos propiedad a editar buscando por el id
	 */
	public String loadDatosEditar(int codigo) {
		
		propiedad =propiedadDao.leer(codigo);
		return"EditarPropiedad";
	}
	


/*
 * metodo para obtener la lista de propiedades
 */
	private void loadPropiedades() {
		
	listpropiedades=propiedadDao.listadoPropiedades();
	}
	
	
	
/*
 * metodo que me busca la persona mediante el correo 
 * para agregarle al objeto propietario
 */
	public void añadirPersona() {
		listaPersonas=perDao.getPersonasXemail(correo);
		
		if(listaPersonas.size()!=0) {
			persona=listaPersonas.get(0);
			codigoPersona=persona.getCodigo();
			propiedad.setPersona(persona);
		}
		
	}
	
	/*
	 * metodo para cargar categorias a la lista del combo
	 */
	private void cargarCategorias(){
		listaCategorias = new ArrayList<SelectItem>();
		categoriasDeBase=catDao.listadoCategoria();
		for (int i = 0; i < categoriasDeBase.size(); i++) {
			listaCategorias.add(new SelectItem(categoriasDeBase.get(i).getAlias(),categoriasDeBase.get(i).getDescripcion()));
		}
		
	}
	
	/*
	 * metodo para cargar provincias a la lista del combo
	 */
	private void cargarProvincias(){
		listaProvincias = new ArrayList<SelectItem>();
		provinciasDeBase=provDao.listaProvincias();
		for (int i = 0; i < provinciasDeBase.size(); i++) {
			listaProvincias.add(new SelectItem(provinciasDeBase.get(i).getAlias(),provinciasDeBase.get(i).getNombre()));
		}
		
	}
	
	/*
	 * metodo para cargar sectores a la lista del combo
	 */
	private void cargarSectores(List<Sector> sectores){
		listaSectores = new ArrayList<SelectItem>();
		System.out.println("Entro a la carga de sectores ");
		for (int i = 0; i < sectores.size(); i++) {
			listaSectores.add(new SelectItem(sectores.get(i).getAlias(),sectores.get(i).getNombre()));
		}
	}
	
	/*
	 * metodo que me permite obtener la provincia seleccionada y llama a cargar los sectores
	 * de aquella provincia
	 */
	public void agregarSectoresCombo(){
		
		System.out.println("se obtiene ");
			provincia=provDao.getProvincias(provi).get(0);
			System.out.println("se obtiene "+provincia);
			cargarSectores(provincia.getSectores());
	
	}
	
	/*
	 * metodo de selecion de sector y carga el sector a la propiedad
	 */
	public void seleccionarSectoresCombo(){
		System.out.println("entro selecion sector ");
		try {
			for (int i = 0; i < provincia.getSectores().size(); i++) {
				if(provincia.getSectores().get(i).getAlias().equals(sect)) {
					System.out.println("entro "+provincia.getSectores().get(i).getAlias());
					propiedad.setSector(provincia.getSectores().get(i));
					indice=i;
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	}
	
	/*
	 * metodo que obtiene la selecion de la categoria y la busca para agregarla a la propiedad
	 */
	public void seleccionarCategoriasCombo(){
		System.out.println("entro selecion Categoria "+ categoriasDeBase.size());
		try {
			for (int i = 0; i < categoriasDeBase.size(); i++) {
				if(categoriasDeBase.get(i).getAlias().equals(cat)) {
					
					propiedad.setCategoria(categoriasDeBase.get(i));
					System.out.println("entro selecion Categoria alias"+ categoriasDeBase.get(i).getAlias() + categoriasDeBase.get(i).getCodigo());
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	}

	/*
	 * metodo ´para guardar la propiedad desde una actualizacion de la provincia
	public String registrarPropiedad() {
		System.out.println("Sector "+provincia.getSectores().get(indice).getAlias());
		provincia.getSectores().get(indice).getPropiedades().add(propiedad);
		provDao.actualizar(provincia);
		init();
		return null;
	}*/

	/*
	 * metodo que me permite guardar la propiedad una ves que se han cargado las entidades relacionadas
	 */
	public String savePropiedad() {
		System.out.println("Sector alias"+propiedad.getCategoria().getDescripcion());
		propiedadDao.guardar(propiedad);
		init();
		return null;
	}
	
	
	
	//getters and setters
	
	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
		
		loadDatosEditar(id);
	}


	public List<Propiedad> getListpropiedades() {
		return listpropiedades;
	}


	public void setListpropiedades(List<Propiedad> listpropiedades) {
		this.listpropiedades = listpropiedades;
	}


	public Propiedad getPropiedad() {
		return propiedad;
	}


	public void setPropiedad(Propiedad propiedad) {
		this.propiedad = propiedad;
	}
	
	
	public Provincia getProvincia() {
		return provincia;
	}


	public void setProvincia(Provincia provincia) {
		this.provincia = provincia;
	}


	public Sector getSector() {
		return sector;
	}


	public void setSector(Sector sector) {
		this.sector = sector;
	}


	public List<Provincia> getProvinciasDeBase() {
		return provinciasDeBase;
	}


	public void setProvinciasDeBase(List<Provincia> provinciasDeBase) {
		this.provinciasDeBase = provinciasDeBase;
	}


	public String getProvin() {
		return provin;
	}


	public void setProvin(String provin) {
		this.provin = provin;
	}


	public List<SelectItem> getListaProvincias() {
		return listaProvincias;
	}


	public void setListaProvincias(List<SelectItem> listaProvincias) {
		this.listaProvincias = listaProvincias;
	}


	public String getProvi() {
		return provi;
	}


	public void setProvi(String provi) {
		this.provi = provi;
	}


	public List<SelectItem> getListaSectores() {
		return listaSectores;
	}


	public void setListaSectores(List<SelectItem> listaSectores) {
		this.listaSectores = listaSectores;
	}


	public String getSect() {
		return sect;
	}


	public void setSect(String sect) {
		this.sect = sect;
	}


	public PropiedadDao getPropiedadDao() {
		return propiedadDao;
	}


	public void setPropiedadDao(PropiedadDao propiedadDao) {
		this.propiedadDao = propiedadDao;
	}


	public ProvinciaDAO getProvDao() {
		return provDao;
	}


	public void setProvDao(ProvinciaDAO provDao) {
		this.provDao = provDao;
	}


	public int getIndice() {
		return indice;
	}


	public void setIndice(int indice) {
		this.indice = indice;
	}

	

	public Persona getPersona() {
		return persona;
	}


	public void setPersona(Persona persona) {
		this.persona = persona;
	}


	public int getCodigoPersona() {
		return codigoPersona;
	}


	public void setCodigoPersona(int codigoPersona) {
		this.codigoPersona = codigoPersona;
	}


	public List<Persona> getListaPersonas() {
		return listaPersonas;
	}


	public void setListaPersonas(List<Persona> listaPersonas) {
		this.listaPersonas = listaPersonas;
	}


	public PersonaDao getPerDao() {
		return perDao;
	}


	public void setPerDao(PersonaDao perDao) {
		this.perDao = perDao;
	}
	

	
	public String getCorreo() {
		return correo;
	}


	public void setCorreo(String correo) {
		this.correo = correo;
	}
	
	


	public Categoria getCategoria() {
		return categoria;
	}


	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}


	public List<Categoria> getCategoriasDeBase() {
		return categoriasDeBase;
	}


	public void setCategoriasDeBase(List<Categoria> categoriasDeBase) {
		this.categoriasDeBase = categoriasDeBase;
	}


	public List<SelectItem> getListaCategorias() {
		return listaCategorias;
	}


	public void setListaCategorias(List<SelectItem> listaCategorias) {
		this.listaCategorias = listaCategorias;
	}


	public String getCat() {
		return cat;
	}


	public void setCat(String cat) {
		this.cat = cat;
	}


	public CategoriaDao getCatDao() {
		return catDao;
	}


	public void setCatDao(CategoriaDao catDao) {
		this.catDao = catDao;
	}

	
}
