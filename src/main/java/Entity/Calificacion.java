package Entity;

import java.time.LocalDateTime;
import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;

@Entity
@Table(name="calificacion")
public class Calificacion {
	
	@Id @GeneratedValue
	private int id;
	
	@OneToOne
	@JoinColumn(nullable=false)
	private Trabajo trabajo;
	
	@OneToOne
	@JoinColumn(nullable=false)
	private Usuario evaluador;
	
	@Column
	@CreationTimestamp
	private Calendar fecha;
	
	@Column(nullable = false)
	private int nota;
	
	@Column(nullable = true)
	private String observacion;
	
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

	public int getNota() {
		return nota;
	}

	public void setNota(int nota) {
		this.nota = nota;
	}

	public String getObservacion() {
		return observacion;
	}

	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}

	public int getId() {
		return id;
	}

	
}
