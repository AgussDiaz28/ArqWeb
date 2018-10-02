package ArqWeb.ArqWeb;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name="usuario")
public class Usuario {
	
	@Id @GeneratedValue
	private int id;

	@Column(nullable = false)
	private String nombre;
	
	@Column(nullable = false)
	private String apellido;
	
	@Column(nullable = false)
	private boolean esAutor;
	
	@Column(nullable = false)
	private boolean esEvaluador;
	
	@ManyToMany
	private Set<PalabrasClave> palabrasClave;
	
	@ManyToMany
	private Set<Trabajo> trabajosEnInvestigacion;
	
	@ManyToMany
	private Set<Trabajo> trabajosEnEvaluacion;
	
	@Column(nullable = true)
	private boolean esExperto;

	//-----CONSTRUCTOR-----
	
	public Usuario() {

	}
	
	//-----GETTERS & SETTERS-----
	
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public boolean isEsAutor() {
		return esAutor;
	}

	public void setEsAutor(boolean esAutor) {
		this.esAutor = esAutor;
	}

	public boolean isEsEvaluador() {
		return esEvaluador;
	}

	public void setEsEvaluador(boolean esEvaluador) {
		this.esEvaluador = esEvaluador;
	}

	public Set<PalabrasClave> getPalabrasClave() {
		return palabrasClave;
	}

	public void setPalabrasClave(Set<PalabrasClave> palabrasClave) {
		this.palabrasClave = palabrasClave;
	}

	public Set<Trabajo> getTrabajosEnInvestigacion() {
		return trabajosEnInvestigacion;
	}

	public void setTrabajosEnInvestigacion(Set<Trabajo> trabajosEnInvestigacion) {
		this.trabajosEnInvestigacion = trabajosEnInvestigacion;
	}

	public Set<Trabajo> getTrabajosEnEvaluacion() {
		return trabajosEnEvaluacion;
	}

	public void setTrabajosEnEvaluacion(Set<Trabajo> trabajosEnEvaluacion) {
		this.trabajosEnEvaluacion = trabajosEnEvaluacion;
	}

	public boolean isEsExperto() {
		return esExperto;
	}

	public void setEsExperto(boolean esExperto) {
		this.esExperto = esExperto;
	}

	public int getId() {
		return id;
	}

	public Usuario(String nombre, String apellido, Set<PalabrasClave> palabrasClave) {
		this.nombre = nombre;
		this.apellido = apellido;
		this.palabrasClave = palabrasClave;
	}
}