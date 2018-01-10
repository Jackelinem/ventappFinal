package ec.edu.ups.Model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonIgnore;

//Entidad Propiedad
@Entity
public class Propiedad implements Serializable{
	

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int codigo;
	

	@Size(min=1, max=50)
	private String direccion;
	
	@NotEmpty
	private String urlVideo;
	
	@Size(min=1, max=50)
	@NotEmpty
	private String descripcion;
	
	@NotEmpty
	private String estado;
	
	@NotEmpty
	private String tipo;
	
	@NotEmpty
	private String costo;
	
	
	// relacion bidireccional muchas propiedades pueden pertenecer a un sector
	@ManyToOne
	@JoinColumn(name="sector")
	private Sector sector;
	
	// relacion bidireccional muchas propiedades pueden pertenecer a una persona
	@ManyToOne
	@JoinColumn(name="persona")
	private Persona persona;
	
	// relacion bidireccional muchas propiedades pueden pertenecer a una categoria
	@ManyToOne
	@JoinColumn(name="categoria")
	private Categoria categoria;
	
	
	private double longuitud;
	
	
	private double latitud;
	
	@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.EAGER)
	@JoinColumn(name="propiedad", referencedColumnName="codigo")
	private List<Imagen> imagenes;

// getters and setters
	public int getCodigo() {
		return codigo;
	}


	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}


	public String getDireccion() {
		return direccion;
	}


	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}


	public String getUrlVideo() {
		return urlVideo;
	}


	public void setUrlVideo(String urlVideo) {
		this.urlVideo = urlVideo;
	}


	public String getDescripcion() {
		return descripcion;
	}


	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}


	public String getEstado() {
		return estado;
	}


	public void setEstado(String estado) {
		this.estado = estado;
	}


	public String getTipo() {
		return tipo;
	}


	public void setTipo(String tipo) {
		this.tipo = tipo;
	}


	public String getCosto() {
		return costo;
	}


	public void setCosto(String costo) {
		this.costo = costo;
	}


	public double getLonguitud() {
		return longuitud;
	}


	public void setLonguitud(double longuitud) {
		this.longuitud = longuitud;
	}


	public double getLatitud() {
		return latitud;
	}


	public void setLatitud(double latitud) {
		this.latitud = latitud;
	}




	public Sector getSector() {
		return sector;
	}


	public void setSector(Sector sector) {
		this.sector = sector;
	}




	public Persona getPersona() {
		return persona;
	}




	public void setPersona(Persona persona) {
		this.persona = persona;
	}




	public Categoria getCategoria() {
		return categoria;
	}




	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}


	

	public List<Imagen> getImagenes() {
		return imagenes;
	}


	public void setImagenes(List<Imagen> imagenes) {
		this.imagenes = imagenes;
	}


	//metodo que me permite agregar varios telefonos
	public void addImagen(Imagen img){
		if (imagenes==null)
			imagenes = new ArrayList<Imagen>();
			imagenes.add(img);
			
		
	
	}
	
	
}
