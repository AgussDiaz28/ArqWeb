package Entity;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
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
	@JoinTable(
			name = "trabajo_palabraClave",
			joinColumns = { @JoinColumn(name = "trabajo_id") },
			inverseJoinColumns = { @JoinColumn(name = "palabraClave_id") }
		)
	private Set<PalabrasClave> palabrasClave;
	
	@Column(nullable = false)
	private boolean esExperto;
	
	@OneToOne
	private TipoTrabajo tipoTrabajo;
	
	@Column
	private String titulo;
	
	@Column
	private String descripcion;
	
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

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
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
	
	public String toString() {
		String result = "id: "+this.id+"; titulo: "+this.titulo+"; descripcion: "+this.descripcion+"; palabras clave: ";
		Iterator<PalabrasClave> it = this.palabrasClave.iterator();
		while(it.hasNext()) {
			PalabrasClave pc = it.next();
			result += pc.getPalabra()+" ";
		}
		result += "; autores: ";
		Iterator<Usuario> usA = this.autores.iterator();
		while(usA.hasNext()) {
			Usuario u = usA.next();
			result += u.toString()+" - ";
		}
		
		result += "; evaluadores: ";
		Iterator<Usuario> usE = this.evaluadores.iterator();
		while(usE.hasNext()) {
			Usuario u = usE.next();
			result += u.toString()+" - ";
		}
		return result;
	}
}