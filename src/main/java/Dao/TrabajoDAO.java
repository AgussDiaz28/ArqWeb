package Dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

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

	public List<Trabajo> getTrabajoConPropiedades(Integer id) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("Trabajo-Especial");
		EntityManager entityManager = emf.createEntityManager();
		Query query = entityManager.createQuery("SELECT * FROM trabajo t JOIN autores_trabajo at ON t.id = at.trabajo_id JOIN evaluadores_trabajo et ON t.id = et.trabajo_id WHERE t.id = id");
		List<Trabajo> trabajo = query.getResultList(); //lo deje asi porq tengo duda de si devuelve una tupla o varias por el tema del join. imagino q habra cosas duplicadas, probar DISTINCT
		entityManager.close();
		return trabajo;
	}
	
	public List<Trabajo> getTrabajoConMismasPalabrasClave(Integer user_id) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("Trabajo-Especial");
		EntityManager entityManager = emf.createEntityManager();
		Query query = entityManager.createQuery("SELECT * FROM trabajo t JOIN autores_trabajo at ON t.id = at.trabajo_id JOIN evaluadores_trabajo et ON t.id = et.trabajo_id WHERE t.id = id"); //matchear palabras clave
		List<Trabajo> trabajo = query.getResultList();
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
