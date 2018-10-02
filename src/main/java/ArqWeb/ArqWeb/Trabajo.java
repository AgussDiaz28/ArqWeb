package ArqWeb.ArqWeb;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;


@Entity
@Table(name="trabajo")
public class Trabajo {
	
	@Id @GeneratedValue
	private int id;
	
	@ManyToMany(mappedBy="trabajosEnInvestigacion")
	private Set<Usuario> autores;
	
	@ManyToMany(mappedBy="trabajosEnEvaluacion")
	private Set<Usuario> evaluadores;
	
	@ManyToMany
	private Set<PalabrasClave> palabrasClave;
	
	@Column(nullable = false)
	private boolean esExperto;
	
	//-----CONSTRUCTOR-----
	
	public Trabajo() {
		
	}
	
	//-----GETTERS & SETTERS-----

	public int getId() {
		return id;
	}

	public Set<Usuario> getAutores() {
		return autores;
	}

	public void setAutores(Set<Usuario> autores) {
		this.autores = autores;
	}

	public Set<Usuario> getEvaluadores() {
		return evaluadores;
	}

	public void setEvaluadores(Set<Usuario> evaluadores) {
		this.evaluadores = evaluadores;
	}

	public boolean isEsExperto() {
		return esExperto;
	}

	public void setEsExperto(boolean esExperto) {
		this.esExperto = esExperto;
	}
}