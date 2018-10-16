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
		EntityManager entityManager = EMF.createEntityManager();
		entityManager.getTransaction().begin();
		entityManager.persist(trabajo);
		entityManager.getTransaction().commit();
		entityManager.close();
		return trabajo;
	}

	public Trabajo findById(Integer id) {
		EntityManager entityManager = EMF.createEntityManager();
		Trabajo trabajo = entityManager.find(Trabajo.class, id);
		entityManager.close();
		return trabajo;	
	}

	public List<Trabajo> getTrabajoConPropiedades(Integer trabajoId) {
		EntityManager entityManager = EMF.createEntityManager();
		Query query = entityManager.createQuery("SELECT t.* FROM trabajo t JOIN autor_trabajo at ON t.id = at.trabajo_id JOIN evaluador_trabajo et ON t.id = et.trabajo_id WHERE t.id = :trabajoId");
		query.setParameter("trabajoId", trabajoId);
		List<Trabajo> trabajo = query.getResultList();
		entityManager.close();
		return trabajo;
	}
	
	public List<Trabajo> getTrabajoConMismasPalabrasClave(Integer palabraClaveId) {
		EntityManager entityManager = EMF.createEntityManager();
		Query query = entityManager.createQuery("SELECT t.* FROM trabajo t JOIN trabajo_palabraClave tp ON t.id = tp.trabajo_id WHERE tp.palabraClave_id = :palabraClaveId");
		query.setParameter("palabraClaveId", palabraClaveId);
		List<Trabajo> trabajo = query.getResultList();
		entityManager.close();
		return trabajo;
	}

	public List<Trabajo> findAll() {
		EntityManager entityManager = EMF.createEntityManager();
		throw new UnsupportedOperationException();
	}

	public Trabajo update(Integer id, Trabajo entity) {
		EntityManager entityManager = EMF.createEntityManager();
		throw new UnsupportedOperationException();
	}

	public boolean delete(Integer id) {
		EntityManager entityManager = EMF.createEntityManager();

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
