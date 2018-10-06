package Dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import Entity.Trabajo;

public class TrabajoDAO implements DAO<Trabajo,Integer>{
	
	private static TrabajoDAO daoTrabajo;
	
	private TrabajoDAO(){}

	public static TrabajoDAO getInstance() {
		if(daoTrabajo == null)
			daoTrabajo = new TrabajoDAO();
		return daoTrabajo;
	}

	public Trabajo persist(Trabajo trabajo) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("Trabajo-Especial");
		EntityManager entityManager = emf.createEntityManager();
		entityManager.getTransaction().begin();
		entityManager.persist(trabajo);
		entityManager.getTransaction().commit();
		entityManager.close();
		return trabajo;
	}

	public Trabajo findById(Integer id) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("Trabajo-Especial");
		EntityManager entityManager = emf.createEntityManager();
		Trabajo trabajo = entityManager.find(Trabajo.class, id);
		entityManager.close();
		return trabajo;	
	}
	
	public List<Trabajo> findAll() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("Trabajo-Especial");
		EntityManager entityManager = emf.createEntityManager();
		throw new UnsupportedOperationException();
	}
	
	public Trabajo update(Integer id, Trabajo entity) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("Trabajo-Especial");
		EntityManager entityManager = emf.createEntityManager();
		throw new UnsupportedOperationException();
	}

	public boolean delete(Integer id) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("Trabajo-Especial");
		EntityManager entityManager = emf.createEntityManager();
		
		Trabajo trabajo = this.findById(id);		
		if(trabajo != null) {
			entityManager.getTransaction().begin();
			entityManager.remove(trabajo);
			entityManager.getTransaction().commit();
			entityManager.close();
			return true;
		}
		return false;
	}
}
