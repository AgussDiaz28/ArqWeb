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
@Table(name="palabras_clave")
public class PalabrasClave {
	
	@Id @GeneratedValue
	private int id;
	
	@Column(nullable = false)
	private String palabra;
	
	@Column(nullable = false)
	private boolean esExperto; //experto o simple
	
	@ManyToMany(mappedBy="palabrasClave")
	private Set<Usuario> usuarios;
	
	//-----CONSTRUCTOR-----
	
	public PalabrasClave() {
		this.usuarios = new HashSet<Usuario>();	
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

	public void setEsExperto(boolean esExperto) {
		this.esExperto = esExperto;
	}

	public Set<Usuario> getUsuarios() {
		return usuarios;
	}
	
	public int getId() {
		return id;
	}
	
	
}