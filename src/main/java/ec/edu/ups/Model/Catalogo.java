package ec.edu.ups.Model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
//@Table(name="Catalogo") //Asignacion de nombre a entidad de datos
public class Catalogo implements Serializable {
	
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id //Generacion de codigo automatica
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int codigoCatalogo;
	
	@Size(min=1, max=100) //Validacion de tamaño
	@Column(unique=true) //Especificacion de campo unico
	private String nombreCatalogo;
	
	@Temporal(TemporalType.DATE)
	@Column(name="FechaInicioCatalogo") //Especificacion de nombre en tabla
	@NotEmpty //Validacion de valor no vacio en campo en bd
	private Date FechaInicioCatalogo;
	
	@Temporal(TemporalType.DATE) //Campo como fecha
	@Column(name="FechaFinCatalogo")
	@NotEmpty //Validacion de valor no vacio en campo en bd
	private Date FechaFinCatalogo;
	
	@Column(name="estadoCatalogo",length=1) //Validacion de tamaño en bd
	private String estadoCatalogo; 
	
	public int getCodigoCatalogo() {
		return codigoCatalogo;
	}

	public void setCodigoCatalogo(int codigoCatalogo) {
		this.codigoCatalogo = codigoCatalogo;
	}

	public Date getFechaInicioCatalogo() {
		return FechaInicioCatalogo;
	}

	public void setFechaInicioCatalogo(Date fechaInicioCatalogo) {
		FechaInicioCatalogo = fechaInicioCatalogo;
	}

	public Date getFechaFinCatalogo() {
		return FechaFinCatalogo;
	}

	public void setFechaFinCatalogo(Date fechaFinCatalogo) {
		FechaFinCatalogo = fechaFinCatalogo;
	}

	public String getEstadoCatalogo() {
		return estadoCatalogo;
	}

	public void setEstadoCatalogo(String estadoCatalogo) {
		this.estadoCatalogo = estadoCatalogo;
	}

	public String getNombreCatalogo() {
		return nombreCatalogo;
	}

	public void setNombreCatalogo(String nombreCatalogo) {
		this.nombreCatalogo = nombreCatalogo;
	}
	
	

	
	
}
