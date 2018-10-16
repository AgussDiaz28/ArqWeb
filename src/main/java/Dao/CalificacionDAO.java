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

	public Calificacion findById(Integer id) {
		EntityManager entityManager = EMF.createEntityManager();
		Calificacion calificacion = entityManager.find(Calificacion.class, id);
		entityManager.close();
		return calificacion;	
	}

	public Calificacion persist(Calificacion calificacion) {
		EntityManager entityManager = EMF.createEntityManager();
		entityManager.getTransaction().begin();
		entityManager.persist(calificacion);
		entityManager.getTransaction().commit();
		entityManager.close();
		return calificacion;
	}

	public List<Calificacion> findAll() {
		EntityManager entityManager = EMF.createEntityManager();
		throw new UnsupportedOperationException();
	}

	public boolean delete(Integer id) {
		EntityManager entityManager = EMF.createEntityManager();
		throw new UnsupportedOperationException();
	}

	public Calificacion update(Integer id, Calificacion entity) {
		EntityManager entityManager = EMF.createEntityManager();
		throw new UnsupportedOperationException();
	}

}