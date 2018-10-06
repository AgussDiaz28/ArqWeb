package ArqWeb.ArqWeb;

import java.util.HashSet;
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
	
	//TODO - preguntar si esta bien la implementacion del equals
	@Override
	public boolean equals(Object t) {
		return this.id == ((Trabajo) t).getId();
	}

	//-----CONSTRUCTOR-----
	
	public Trabajo() {
		this.autores = new HashSet<Usuario>();
		this.evaluadores = new HashSet<Usuario>();
		this.palabrasClave = new HashSet<PalabrasClave>();
	}
	
	//-----GETTERS & SETTERS-----

	public int getId() {
		return id;
	}

	public Set<Usuario> getAutores() {
		return autores;
	}

	public void setAutor(Usuario autor) {
		this.autores.add(autor);
	}

	public Set<Usuario> getEvaluadores() {
		return evaluadores;
	}

	public void setEvaluador(Usuario evaluador) {
		this.evaluadores.add(evaluador);
	}

	public boolean isExperto() {
		return esExperto;
	}
	
	public Set<PalabrasClave> getPalabrasClave() {
		return palabrasClave;
	}

	public void setPalabraClave(PalabrasClave palabraClave) {
		this.palabrasClave.add(palabraClave);
	}
}