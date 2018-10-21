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
import javax.persistence.Table;

import Dao.CalificacionDAO;

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
	@JoinTable(
			name = "usuario_palabraClave",
			joinColumns = { @JoinColumn(name = "usuario_id") },
			inverseJoinColumns = { @JoinColumn(name = "palabraClave_id") }
			)
	private Set<PalabrasClave> palabrasClave;

	/* Forma correcta de definir ManyToMany:
	 * 
	 * @ManyToMany
	 * @JoinTable y asignas el nombre a la tabla
	 * luego asignas nombres a las columnas
	 * 
	 * las columnas no hace falta pero es mas prolijo
	 * si definimos tanto nombre de la tabla como de las columnas
	 * en vez de dejarlo en manos de hibernate.
	 * 
	 * hay que revisar el resto y emprolijarlo para que nos cree la base de datos
	 * como nosotros lo queremos
	 */
	@ManyToMany 
	@JoinTable(
			name = "autor_trabajo",
			joinColumns = { @JoinColumn(name = "autor_id") },
			inverseJoinColumns = { @JoinColumn(name = "trabajo_id") }
			)
	private Set<Trabajo> trabajosEnInvestigacion;

	@ManyToMany
	@JoinTable(
			name = "evaluador_trabajo",
			joinColumns = { @JoinColumn(name = "evaluador_id") },
			inverseJoinColumns = { @JoinColumn(name = "trabajo_id") }
			)
	private Set<Trabajo> trabajosEnEvaluacion;

	@ManyToMany
	@JoinTable(
			name = "evaluador_trabajoPendiente",
			joinColumns = { @JoinColumn(name = "evaluador_id") },
			inverseJoinColumns = { @JoinColumn(name = "trabajoPendiente_id") }
			)
	private Set<Trabajo> trabajosPendientes;	

	@Column(nullable = false)
	private boolean esExperto;

	//-----CONSTRUCTOR-----

	public Usuario() {
		this.palabrasClave = new HashSet<PalabrasClave>();
		this.trabajosEnInvestigacion = new HashSet<Trabajo>();
		this.trabajosEnEvaluacion = new HashSet<Trabajo>();
		this.trabajosPendientes = new HashSet<Trabajo>();
		this.esExperto = false;
	}

	public Usuario(String nombre, String apellido, Set<PalabrasClave> palabrasClave) {
		this.nombre = nombre;
		this.apellido = apellido;
		this.palabrasClave = palabrasClave;
		this.palabrasClave = new HashSet<PalabrasClave>();
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
		return esAutor;
	}

	public boolean isEvaluador() {
		return esEvaluador;
	}

	public Set<PalabrasClave> getPalabrasClave() {
		return palabrasClave;
	}

	public void setPalabraClave(PalabrasClave palabrasClave) {
		this.palabrasClave.add(palabrasClave);
		
		if(palabrasClave.isExperto()) {
			this.esExperto = true;
		}
		
		palabrasClave.addUsuario(this);
	}

	public Set<Trabajo> getTrabajosEnInvestigacion() {
		return trabajosEnInvestigacion;
	}

	public void setTrabajoEnInvestigacion(Trabajo trabajo) {
		this.trabajosEnInvestigacion.add(trabajo);
	}

	public Set<Trabajo> getTrabajosEnEvaluacion() {
		return trabajosEnEvaluacion;
	}

	public void setTrabajoEnEvaluacion(Trabajo trabajo) {
		this.trabajosEnEvaluacion.add(trabajo);
	}

	public Set<Trabajo> getTrabajosPendientes() {
		return trabajosPendientes;
	}

	public void setTrabajoPendiente(Trabajo trabajo) {
		this.trabajosPendientes.add(trabajo);
	}

	public boolean isExperto() {
		return esExperto;
	}

	public int getId() {
		return id;
	}

	//---- BUSINESS LOGIC ----

	public void addTrabajoInvestigacion(Trabajo trabajo) {
		trabajo.setAutor(this);
		this.setTrabajoEnInvestigacion(trabajo);
		this.esAutor = true;
	}

	private void addTrabajoEvaluacion(Trabajo trabajo) {
		if(this.trabajosEnEvaluacion.size() <= 3 ) {
			this.setTrabajoEnEvaluacion(trabajo);
			this.esEvaluador = true;
			trabajo.setEvaluador(this);
		}
	}

	public void addTrabajoPendiente(Trabajo trabajo) {

		this.trabajosPendientes.add(trabajo);
	}

	public void aceptarTrabajo(Trabajo trabajo) {
		if(this.trabajosPendientes.contains(trabajo)) {
			this.trabajosPendientes.remove(trabajo);			
			this.addTrabajoEvaluacion(trabajo);
		}
	}

	public void rechazarTrabajo(Trabajo trabajo) {
		this.trabajosPendientes.remove(trabajo);
	}

	public Calificacion calificarTrabajo(Trabajo trabajo, int nota) {
		if(nota >= 0) {
			Calificacion c = new Calificacion();
			c.setEvaluador(this);
			c.setTrabajo(trabajo);
			c.setNota(nota);
			//TODO persistir calificacion con CalificacionDAO
			return c;
		}
		return null;
	}

	public String toString() {
		return this.apellido+", "+this.nombre;
	}

	//TODO - checkear que ande
	private boolean esEvaluadorApto(Trabajo t) {
		Set<PalabrasClave> clavesTrabajo = t.getPalabrasClave();
		if(t.getTipoTrabajo().isFullCheckNeeded()) {
			return this.palabrasClave.containsAll(clavesTrabajo);
		}else {
			for(PalabrasClave e: clavesTrabajo) {
				if(this.palabrasClave.contains(e)) {
					return true;
				}
			}
//			for (Iterator<PalabrasClave> i = clavesTrabajo.iterator(); i.hasNext();) {
//				if(this.palabrasClave.contains(i.next())) {
//					return true; 
//				}				 
//			}
		}
		return false;
	}
}