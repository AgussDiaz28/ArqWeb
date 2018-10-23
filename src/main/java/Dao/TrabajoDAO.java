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

	public List<Trabajo> getTrabajoConPropiedades(Integer trabajoId, EntityManager entityManager) {
		Query query = entityManager.createQuery("SELECT * FROM trabajo WHERE id = :trabajoId");
		query.setParameter("trabajoId", trabajoId);
		return query.getResultList();
	}
	
	public List<Trabajo> getTrabajoConMismasPalabrasClave(Integer palabraClaveId, EntityManager entityManager) {
		Query query = entityManager.createQuery("SELECT t.* FROM trabajo t JOIN trabajo_palabraClave tp ON t.id = tp.trabajo_id WHERE tp.palabraClave_id = :palabraClaveId");
		query.setParameter("palabraClaveId", palabraClaveId);
		return query.getResultList();
	}

	public List<Trabajo> findAll( EntityManager entityManager) {
		throw new UnsupportedOperationException();
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
