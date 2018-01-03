package ec.edu.ups.Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;

@Entity
//@Table(name="Ciudad")//Asignacion de nombre a entidad de datos
public class Ciudad {

	@Id //Generacion de codigo automatica
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int codigoCiudad;
	
	@Size(min=1, max=100) //Validacion de tamaño de campo en bd
	@Column(unique=true) //Especificacion de campo unico
	private String nombreCiudad;

	public int getCodigoCiudad() {
		return codigoCiudad;
	}

	public void setCodigoCiudad(int codigoCiudad) {
		this.codigoCiudad = codigoCiudad;
	}

	public String getNombreCiudad() {
		return nombreCiudad;
	}

	public void setNombreCiudad(String nombreCiudad) {
		this.nombreCiudad = nombreCiudad;
	}

	
}
