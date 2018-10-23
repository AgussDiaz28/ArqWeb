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
	
	@Column(nullable = false)
	private boolean condEvaluacion; //si es 1 evalua todos, si es 0 evalua que contenga al menos una palabra clave
	
	//-----CONSTRUCTOR-----
	
	public TipoTrabajo() {
		
	}
	
	public boolean isFullCheckNeeded() {
		return condEvaluacion;
	}

	public void setCondEvaluacion(boolean condEvaluacion) {
		this.condEvaluacion = condEvaluacion;
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