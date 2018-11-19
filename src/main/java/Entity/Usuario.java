package Entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;


@Entity
@Table(name="usuario")
public class Usuario {

	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	@Column(nullable = false)
	private String nombre;

	@Column(nullable = false)
	private String apellido;

	@Column(nullable = false)
	private boolean es_autor;

	@Column(nullable = false)
	private boolean es_evaluador;

	@OneToOne
	private LugarTrabajo lugar_trabajo;

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(
			name = "usuario_palabra_clave",
			joinColumns = { @JoinColumn(name = "usuario_id") },
			inverseJoinColumns = { @JoinColumn(name = "palabra_clave_id") }
			)
	private Set<PalabrasClave> palabras_clave;

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(
			name = "autor_trabajo",
			joinColumns = { @JoinColumn(name = "autor_id") },
			inverseJoinColumns = { @JoinColumn(name = "trabajo_id") }
			)
	private Set<Trabajo> trabajosEnInvestigacion;

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(
			name = "evaluador_trabajo",
			joinColumns = { @JoinColumn(name = "evaluador_id") },
			inverseJoinColumns = { @JoinColumn(name = "trabajo_id") }
			)
	private Set<Trabajo> trabajosEnEvaluacion;

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(
			name = "evaluador_trabajo_pendiente",
			joinColumns = { @JoinColumn(name = "evaluador_id") },
			inverseJoinColumns = { @JoinColumn(name = "trabajo_pendiente_id") }
			)
	private Set<Trabajo> trabajosPendientes;	

	@Column(nullable = false)
	private boolean es_experto;

	//-----CONSTRUCTOR-----

	public Usuario() {
		this.palabras_clave = new HashSet<PalabrasClave>();
		this.trabajosEnInvestigacion = new HashSet<Trabajo>();
		this.trabajosEnEvaluacion = new HashSet<Trabajo>();
		this.trabajosPendientes = new HashSet<Trabajo>();
		this.es_experto = false;
		this.es_evaluador = false;
		this.es_autor = false;
	}

	public Usuario(String nombre, String apellido, Set<PalabrasClave> palabrasClave) {
		this.nombre = nombre;
		this.apellido = apellido;
		this.palabras_clave = palabrasClave;
		this.palabras_clave = new HashSet<PalabrasClave>();
		this.trabajosEnInvestigacion = new HashSet<Trabajo>();
		this.trabajosEnEvaluacion = new HashSet<Trabajo>();
		this.trabajosPendientes = new HashSet<Trabajo>();
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

	public boolean isAutor() {
		return es_autor;
	}

	public boolean isEvaluador() {
		return es_evaluador;
	}

	public Set<PalabrasClave> getPalabrasClave() {
		return palabras_clave;
	}

	public void setPalabraClave(PalabrasClave palabrasClave) {
		this.palabras_clave.add(palabrasClave);

		if(palabrasClave.isExperto()) {
			this.es_experto = true;
		}
	}

	public Set<Trabajo> getTrabajosEnInvestigacion() {
		return trabajosEnInvestigacion;
	}

	public Set<Trabajo> getTrabajosEnEvaluacion() {
		return trabajosEnEvaluacion;
	}

	public Set<Trabajo> getTrabajosPendientes() {
		return trabajosPendientes;
	}

	public void setTrabajoPendiente(Trabajo trabajo) {
		this.trabajosPendientes.add(trabajo);
	}

	public boolean isExperto() {
		return es_experto;
	}

	public int getId() {
		return id;
	}

	public LugarTrabajo getLugarTrabajo() {
		return this.lugar_trabajo;
	}

	public void setLugarTrabajo(LugarTrabajo lt) {
		this.lugar_trabajo = lt;
	}
	
	public void setTrabajoEnInvestigacion(Trabajo trabajo){
		this.trabajosEnInvestigacion.add(trabajo);
	}
	
	public void setTrabajoEnEvaluacion(Trabajo trabajo) {
		this.trabajosEnEvaluacion.add(trabajo);
	}
	
	public void setEsEvaluador(boolean valor) {
		this.es_evaluador = valor;
	}
	
	public void setEsAutor(boolean valor) {
		this.es_autor = valor;
	}
	
	public void removeTrabajoPendiente(Trabajo trabajo) {
		this.trabajosPendientes.remove(trabajo);
	}
	
	public String toString() {
		return this.apellido+", "+this.nombre;
	}
	
	public boolean esEvaluadorApto(Trabajo t) {
		if	(!this.trabajosEnInvestigacion.contains(t) && !this.trabajosEnEvaluacion.contains(t) ) {
			boolean mismoLugarTrabajo = false;
			for(Usuario u: t.getAutores()) {
				if(u.getLugarTrabajo().equals(this.lugar_trabajo)) {
					mismoLugarTrabajo = true;
				}
			}
			if(!mismoLugarTrabajo) {
				Set<PalabrasClave> clavesTrabajo = t.getPalabrasClave();
				if(t.getTipoTrabajo().isFullCheckNeeded()) {
					return this.palabras_clave.containsAll(clavesTrabajo);
				}else {
					for(PalabrasClave e: clavesTrabajo) {
						if(this.palabras_clave.contains(e)) {
							return true;
						}
					}
				}
			}			
		}
		return false;
	}
	
	@Override
	public boolean equals(Object o) {
		if(o == null) {
			return false;
		}
		if(!(o instanceof Usuario)) {
			return false;
		}
		
		Usuario u = (Usuario) o;
		return (this.id == u.getId() && this.nombre.equals(u.getNombre()) && this.apellido.equals(u.getApellido()));
	}
}