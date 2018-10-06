package Dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import Entity.TipoTrabajo;

public class TipoTrabajoDAO implements DAO<TipoTrabajo,Integer>{
	
	private static TipoTrabajoDAO daoTipoTrabajoDAO;
	
	private TipoTrabajoDAO(){}

	public static TipoTrabajoDAO getInstance() {
		if(daoTipoTrabajoDAO == null)
			daoTipoTrabajoDAO = new TipoTrabajoDAO();
		return daoTipoTrabajoDAO;
	}

	public TipoTrabajo findById(Integer id) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("Trabajo-Especial");
		EntityManager entityManager = emf.createEntityManager();
		TipoTrabajo tipoTrabajo = entityManager.find(TipoTrabajo.class, id);
		entityManager.close();
		return tipoTrabajo;	
	}

	public TipoTrabajo persist(TipoTrabajo tipoTrabajo) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("Trabajo-Especial");
		EntityManager entityManager = emf.createEntityManager();
		entityManager.getTransaction().begin();
		entityManager.persist(tipoTrabajo);
		entityManager.getTransaction().commit();
		entityManager.close();
		return tipoTrabajo;
	}

	public List<TipoTrabajo> findAll() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("Trabajo-Especial");
		EntityManager entityManager = emf.createEntityManager();
		throw new UnsupportedOperationException();
	}

	public boolean delete(Integer id) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("Trabajo-Especial");
		EntityManager entityManager = emf.createEntityManager();
		throw new UnsupportedOperationException();
	}

	public TipoTrabajo update(Integer id, TipoTrabajo entity) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("Trabajo-Especial");
		EntityManager entityManager = emf.createEntityManager();
		throw new UnsupportedOperationException();
	}

}