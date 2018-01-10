package ec.edu.ups.Model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
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
import javax.persistence.JoinColumns;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;

import com.fasterxml.jackson.annotation.JsonIgnore;


/*
 * Entidad principal de la persona que tendra propiedades
 */
@Entity
public class Persona implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int codigo;
	
	
	@NotNull
	@Column (name="per_nombres", length=10)
	@Size(min=4,max=20)
	private String nombres;
	
	@NotNull
	@Column (name="per_apellido", length=10)
	@Size(max=10)
	private String apellido;
	
	@Temporal (value = TemporalType.DATE)
	@Column (name="per_fecha", length=10)
	private Date fecha;
	
	
	
	//relacion uno a muchos una persona puede tener varios telefonos
	@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.EAGER)
	@JoinColumn(name="Persona", referencedColumnName="codigo")
	private Set<Telefono> telefonos=new HashSet<>();
	
	//relacion uno a muchos una persona puede tener varias propiedades
	@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.EAGER)
	@JoinColumn(name="Persona", referencedColumnName="codigo")
	private Set<Propiedad> propiedades=new HashSet<>();
	

	@Size(min=4,max=50)
	@Column (name="per_direccion", length=10)
	private String direccion;
	
	@Email
	@Column(name="per_email",unique=true)
	private String email;
	
	@Column(name="per_clave")
	private String password;
	
	@ManyToOne(cascade=CascadeType.ALL, fetch=FetchType.EAGER)
	@JoinColumn(name="rol")
	@JsonIgnore
	private Rol rol;
	
	///getter and setter

	
	public int getCodigo() {
		return codigo;
	}

	public Rol getRol() {
		return rol;
	}

	public void setRol(Rol rol) {
		this.rol = rol;
	}

	public Set<Telefono> getTelefonos() {
		return telefonos;
	}

	public void setTelefonos(Set<Telefono> telefonos) {
		this.telefonos = telefonos;
	}

	public Set<Propiedad> getPropiedades() {
		return propiedades;
	}

	public void setPropiedades(Set<Propiedad> propiedades) {
		this.propiedades = propiedades;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	
	

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getNombres() {
		return nombres;
	}


	public void setNombres(String nombres) {
		this.nombres = nombres;
	}
	

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "Persona [codigo=" + codigo + ", cedula=" + apellido+ ", nombres=" + nombres + ", fecha=" + fecha
				+ ", telefonos=" + telefonos + ", direccion=" + direccion + ", email=" + email + ", password="
				+ password + "]";
	}

	//metodo que me permite agregar varios telefonos
	public void addTelefono(Telefono telefono){
		if (telefonos.isEmpty()){
			telefonos = new HashSet<Telefono>();
			telefonos.add(telefono);
			
		}
	}
}
