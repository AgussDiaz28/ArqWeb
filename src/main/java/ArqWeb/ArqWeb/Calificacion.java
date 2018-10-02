package ArqWeb.ArqWeb;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="calificacion")
public class Calificacion {
	
	@Id @GeneratedValue
	private int id;
	
	@Column(nullable = false)
	private Trabajo trabajo;
	
	@Column(nullable = false)
	private Usuario evaluador;
	
	@Column(nullable = false)
	private int notaNumerica;
	
	@Column(nullable = true)
	private String nota;
	
	//-----CONSTRUCTOR-----
	
	public Calificacion() {
		
	}
	
	//-----GETTERS & SETTERS-----
	
	public Trabajo getTrabajo() {
		return trabajo;
	}

	public void setTrabajo(Trabajo trabajo) {
		this.trabajo = trabajo;
	}

	public Usuario getEvaluador() {
		return evaluador;
	}

	public void setEvaluador(Usuario evaluador) {
		this.evaluador = evaluador;
	}

	public int getNotaNumerica() {
		return notaNumerica;
	}

	public void setNotaNumerica(int notaNumerica) {
		this.notaNumerica = notaNumerica;
	}

	public String getNota() {
		return nota;
	}

	public void setNota(String nota) {
		this.nota = nota;
	}

	public int getId() {
		return id;
	}

	
}
