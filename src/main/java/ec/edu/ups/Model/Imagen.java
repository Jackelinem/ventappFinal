package ec.edu.ups.Model;

import java.io.Serializable;

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
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonIgnore;



@Entity
//@Table(name="Imagen") //Asignacion de nombre a entidad de datos
public class Imagen implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id //Generacion de codigo automatica
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int codigoImagen;
	
	@Size(min=1, max=100) //Validacion de tamaño de campo en bd
	@Column(unique=true) //Especificacion de campo unico
	private String nombreImagen;
	
	@Size(min=1, max=100) //Validacion de tamaño de campo en bd
	private String descripcionImagen;
	
	@Lob
	@Column(name="img")
	private byte[] img;
	
	@ManyToOne(cascade=CascadeType.ALL, fetch=FetchType.EAGER)
	@JoinColumn(name="codigo")
	@JsonIgnore
	private Propiedad propiedad;
	
	public String getNombreImagen() {
		return nombreImagen;
	}

	public void setNombreImagen(String nombreImagen) {
		this.nombreImagen = nombreImagen;
	}
	
	public int getCodigoImagen() {
		return codigoImagen;
	}

	public void setCodigoImagen(int codigoImagen) {
		this.codigoImagen = codigoImagen;
	}

	public String getDescripcionImagen() {
		return descripcionImagen;
	}

	public void setDescripcionImagen(String descripcionImagen) {
		this.descripcionImagen = descripcionImagen;
	}

	public byte[] getImg() {
		return img;
	}

	public void setImg(byte[] img) {
		this.img = img;
	}

	
	
}
