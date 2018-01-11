package ec.edu.ups.Model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

//Entidad sector relacionada con la provincia y las propiedades

@Entity
public class Sector implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int codigo;
	
	private String nombre;
	
	private String alias;
	
	//relacion uno a muchos un sector puede tener varias propiedades
	@OneToMany(mappedBy="sector")
	private List<Propiedad> propiedades;
	
	//getters and setters
	
	public int getCodigo() {
		return codigo;
	}
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getAlias() {
		return alias;
	}
	public void setAlias(String alias) {
		this.alias = alias;
	}
	public List<Propiedad> getPropiedades() {
		return propiedades;
	}
	public void setPropiedades(List<Propiedad> propiedades) {
		this.propiedades = propiedades;
	}
	
	//metodo que me permite agregar mas propiedades
	public void addPropiedadr(Propiedad propiedad){
		if (propiedades == null){
			propiedades = new ArrayList<>();
			propiedades.add(propiedad);
			
		}
	}
	
	

	
}
