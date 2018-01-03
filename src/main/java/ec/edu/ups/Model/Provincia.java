package ec.edu.ups.Model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


@Entity
public class Provincia implements Serializable {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	@Id
	@Column (name="prov_codigo")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int codigo;
	
	
	@NotNull
	@Column (name="prov_nombre")
	@Size(min=4,max=20)
	private String nombre;
	
	
	@Column (name="prov_alias", length=3)
	private String alias;
	
	//relacion uno a muchos una provincia o ciudad puede tener varios sectores
	@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.EAGER)
	@JoinColumn(name="id", referencedColumnName="prov_codigo")
	private List<Sector> sectores;
	
	//metodo que me permite agregar mas sectores
	public void addSector(Sector sector){
		if (sectores == null){
			sectores = new ArrayList<>();
			sectores.add(sector);
			
		}
	}


	@Override
	public String toString() {
		return "Provincia [codigo=" + codigo + ", nombre=" + nombre + ", alias=" + alias + ", sectores=" + sectores
				+ "]";
	}


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


	public List<Sector> getSectores() {
		return sectores;
	}


	public void setSectores(List<Sector> sectores) {
		this.sectores = sectores;
	}

}
