package Entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;


@Entity
@Table(name="palabras_clave")
public class PalabrasClave {
	
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@Column(nullable = false)
	private String palabra;
	
	@Column(nullable = false)
	private boolean esExperto; //experto o simple
	
	@ManyToMany(mappedBy="palabrasClave")
	@JsonIgnore
	private Set<Usuario> usuarios;
	
	@ManyToMany(mappedBy="palabrasClave")
	@JsonIgnore
	private Set<Trabajo> trabajos;
	
	//-----CONSTRUCTOR-----
	
	public PalabrasClave() {
		this.usuarios = new HashSet<Usuario>();
		this.trabajos = new HashSet<Trabajo>();	
	}
	
	public PalabrasClave(String palabra, boolean esExperto) {
		this.palabra = palabra;
		this.esExperto = esExperto;
	}

	//-----GETTERS & SETTERS-----
	
	public String getPalabra() {
		return palabra;
	}

	public void setPalabra(String palabra) {
		this.palabra = palabra;
	}

	public boolean isExperto() {
		return esExperto;
	}	
	
	public Set<Trabajo> getTrabajos() {
		return trabajos;
	}
	
	public Set<Usuario> getUsuarios() {
		return usuarios;
	}

	public void addTrabajo(Trabajo trabajo) {
		this.trabajos.add(trabajo);
	}

	public void addUsuario(Usuario usuario) {
		this.usuarios.add(usuario);
	}

	public void setEsExperto(boolean esExperto) {
		this.esExperto = esExperto;
	}
	
	public int getId() {
		return id;
	}
	
	@Override
	public boolean equals(Object o) {
		if(o == null) {
			return false;
		}
		if(!(o instanceof PalabrasClave)) {
			return false;
		}
		PalabrasClave pc = (PalabrasClave) o;
		return (this.id == pc.getId() && this.palabra.equals(pc.getPalabra()));
	}
	
}