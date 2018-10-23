package Entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="lugar_trabajo")
public class LugarTrabajo {
	
	@Id @GeneratedValue
	private int id;
	
	@Column(nullable = false)
	private String nombre;
	
	@Column
	private String direccion;
	
	public LugarTrabajo() {
		
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public int getId() {
		return id;
	}
	
	public boolean equals(Object o) {
		if(o == null) {
			return false;
		}
		
		if(!(o instanceof LugarTrabajo)) {
			return false;
		}
		LugarTrabajo lt = (LugarTrabajo) o;
		return (this.id == lt.getId() && this.nombre.equals(lt.getNombre()));
	}
}
