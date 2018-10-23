package Dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import Entity.Calificacion;

public class CalificacionDAO implements DAO<Calificacion,Integer>{
	
	private static CalificacionDAO daoCalificacion;
	
	private CalificacionDAO(){}

	public static CalificacionDAO getInstance() {
		if(daoCalificacion == null)
			daoCalificacion = new CalificacionDAO();
		return daoCalificacion;
	}

	public Calificacion findById(Integer id, EntityManager entityManager) {
		return entityManager.find(Calificacion.class, id);	
	}

	public Calificacion persist(Calificacion calificacion, EntityManager entityManager) {
		entityManager.persist(calificacion);
		return calificacion;
	}
	
	public EntityManager getEntityManager() {
		return EMF.createEntityManager();	
	}
	
	public Calificacion merge(Calificacion calificacion, EntityManager entityManager) {
		return entityManager.merge(calificacion);
	}

	public List<Calificacion> findAll(EntityManager entityManager) {
		throw new UnsupportedOperationException();
	}

	public boolean delete(Integer id, EntityManager entityManager) {
		throw new UnsupportedOperationException();
	}

	public Calificacion update(Integer id, Calificacion entity, EntityManager entityManager) {
		throw new UnsupportedOperationException();
	}
}