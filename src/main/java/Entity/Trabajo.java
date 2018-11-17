package Entity;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashSet;
import java.util.Iterator;
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

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;



@Entity
@JsonIdentityInfo(generator=ObjectIdGenerators.PropertyGenerator.class, property="id")
@Table(name="trabajo")
public class Trabajo {
	
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@ManyToMany(fetch = FetchType.EAGER,mappedBy="trabajosEnInvestigacion")	
	private Set<Usuario> autores;
	
	@ManyToMany(fetch = FetchType.EAGER,mappedBy="trabajosEnEvaluacion")
	private Set<Usuario> evaluadores;
	
	@ManyToMany(fetch = FetchType.LAZY)
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
	
	@Column
	private Calendar fecha;
	
	//-----CONSTRUCTOR-----
	
	public Trabajo() {
		this.autores = new HashSet<Usuario>();
		this.evaluadores = new HashSet<Usuario>();
		this.palabrasClave = new HashSet<PalabrasClave>();
		this.esExperto = false;
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
	
	public TipoTrabajo getTipoTrabajo() {
		return tipoTrabajo;
	}
	
	public Calendar getFecha() {
		return fecha;
	}

	public void setFecha(Calendar fecha) {
		this.fecha = fecha;
	}

	public void setTipoTrabajo(TipoTrabajo tipoTrabajo) {
		this.tipoTrabajo = tipoTrabajo;
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
		
		if(palabraClave.isExperto()) {
			this.esExperto = true;
		}
	}
	
	public String toString() {
		SimpleDateFormat sdf = new SimpleDateFormat("dd MMM yyyy");
		String result = "id: "+this.id+"; titulo: "+this.titulo+"; descripcion: "+this.descripcion+"; fecha: ";
		if(this.fecha != null) {
			result += sdf.format(this.fecha.getTime());
		}else {
			result += "null";
		}
		result += "; palabras clave: ";
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
	
	@Override
	public boolean equals(Object o) {
		if(o == null) {
			return false;
		}
		
		if(!(o instanceof Trabajo)) {
			return false;
		}
		Trabajo t = (Trabajo) o;
		return (this.id == t.getId() );
	}
}