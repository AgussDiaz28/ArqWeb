package Dao;

import java.util.List;

import javax.persistence.EntityManager;

import Entity.Calificacion;
import Entity.Trabajo;
import Entity.Usuario;

public class CalificacionDAO implements DAO<Calificacion,Integer>{

	private static CalificacionDAO daoCalificacion;

	private CalificacionDAO(){}

	public static CalificacionDAO getInstance() {
		if(daoCalificacion == null)
			daoCalificacion = new CalificacionDAO();
		return daoCalificacion;
	}

	public Calificacion findById(Integer id) {
		EntityManager entityManager=EMF.createEntityManager();
		Calificacion c = entityManager.find(Calificacion.class, id);
		entityManager.close();
		return c;
	}

	public Calificacion persist(Calificacion calificacion) {
		EntityManager entityManager=EMF.createEntityManager();
		entityManager.getTransaction().begin();
		entityManager.persist(calificacion);
		entityManager.getTransaction().commit();
		entityManager.close();
		return calificacion;
	}

	public void calificarTrabajo(Integer id_trabajo, Integer id_usuario, Integer nota) {
		Usuario user = UsuarioDAO.getInstance().findById(id_usuario);
		Trabajo trabajo = TrabajoDAO.getInstance().findById(id_trabajo);
		if(trabajo == null) 
			throw new IllegalArgumentException("Trabajo_id no existe");

		if(user == null) 
			throw new IllegalArgumentException("Usuario_id no existe");

		if (!(nota >= 0 && nota <= 10))
			throw new IllegalArgumentException("Nota debe ser entre 0 a 10");
		
		if(!user.getTrabajosEnEvaluacion().contains(trabajo)) 
			throw new IllegalArgumentException("El usuario no es revisor de ese trabajo");

		Calificacion c = new Calificacion();
		c.setEvaluador(user);
		c.setTrabajo(trabajo);
		c.setNota(nota);

		this.persist(c);
	}

	public List<Calificacion> findAll() {
		throw new UnsupportedOperationException();
	}

	public boolean delete(Integer id) {
		throw new UnsupportedOperationException();
	}

	public Calificacion update(Integer id, Calificacion entity) {
		throw new UnsupportedOperationException();
	}
}