package Dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import Entity.TipoTrabajo;
import Entity.Trabajo;

public class TrabajoDAO implements DAO<Trabajo,Integer>{

	private static TrabajoDAO daoTrabajo;

	private TrabajoDAO(){}

	public static TrabajoDAO getInstance() {
		if(daoTrabajo == null)
			daoTrabajo = new TrabajoDAO();
		return daoTrabajo;
	}

	public Trabajo persist(Trabajo trabajo, EntityManager entityManager) {
		entityManager.persist(trabajo);
		return trabajo;
	}
	
	public EntityManager getEntityManager() {
		return EMF.createEntityManager();	
	}
	
	public Trabajo merge(Trabajo trabajo, EntityManager entityManager) {
		return entityManager.merge(trabajo);
	}

	public Trabajo findById(Integer id, EntityManager entityManager) {
		return entityManager.find(Trabajo.class, id);
	}

	public Trabajo getTrabajoConPropiedades(Integer trabajoId, EntityManager entityManager) {
		Query query = entityManager.createNativeQuery("SELECT * FROM trabajo WHERE id = :trabajoId",Trabajo.class);
		query.setParameter("trabajoId", trabajoId);
		return (Trabajo) query.getSingleResult();
	}
	
	public List<Trabajo> getTrabajoConMismasPalabrasClave(Integer palabraClaveId, EntityManager entityManager) {
		Query query = entityManager.createNativeQuery("SELECT t.* FROM trabajo t JOIN trabajo_palabraClave tp ON t.id = tp.trabajo_id WHERE tp.palabraClave_id = :palabraClaveId",Trabajo.class);
		query.setParameter("palabraClaveId", palabraClaveId);
		return query.getResultList();
	}

	public List<Trabajo> findAll( EntityManager entityManager) {
		Query query = entityManager.createNativeQuery("SELECT * FROM trabajo",Trabajo.class);
		return query.getResultList();
	}

	public Trabajo update(Integer id, Trabajo entity, EntityManager entityManager) {
		throw new UnsupportedOperationException();
	}

	public boolean delete(Integer id, EntityManager entityManager) {
		Trabajo trabajo = this.findById(id, entityManager);		
		if(trabajo != null) {
			entityManager.remove(trabajo);
			return true;
		}
		return false;
	}
}
