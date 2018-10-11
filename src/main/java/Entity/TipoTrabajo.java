package Entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="tipo_trabajo")
public class TipoTrabajo {
	
	@Id @GeneratedValue
	private int id;
	
	@Column(nullable = false)
	private String tipo;
	
	//-----CONSTRUCTOR-----
	
	public TipoTrabajo() {
		
	}
	
	public TipoTrabajo(String tipo) {
		this.tipo = tipo;
	}
	
	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public int getId() {
		return id;
	}

}