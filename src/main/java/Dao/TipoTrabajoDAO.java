package Dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import Entity.Calificacion;
import Entity.TipoTrabajo;

public class TipoTrabajoDAO implements DAO<TipoTrabajo,Integer>{
	
	private static TipoTrabajoDAO daoTipoTrabajoDAO;
	
	private TipoTrabajoDAO(){}

	public static TipoTrabajoDAO getInstance() {
		if(daoTipoTrabajoDAO == null)
			daoTipoTrabajoDAO = new TipoTrabajoDAO();
		return daoTipoTrabajoDAO;
	}

	public TipoTrabajo findById(Integer id, EntityManager entityManager) {
		return entityManager.find(TipoTrabajo.class, id);
	}

	public TipoTrabajo persist(TipoTrabajo tipoTrabajo, EntityManager entityManager) {
		entityManager.persist(tipoTrabajo);
		return tipoTrabajo;
	}
	
	public EntityManager getEntityManager() {
		return EMF.createEntityManager();	
	}

	public TipoTrabajo merge(TipoTrabajo tipoTrabajo, EntityManager entityManager) {
		return entityManager.merge(tipoTrabajo);
	}
	
	public List<TipoTrabajo> findAll( EntityManager entityManager) {
		throw new UnsupportedOperationException();
	}

	public boolean delete(Integer id, EntityManager entityManager) {
		throw new UnsupportedOperationException();
	}

	public TipoTrabajo update(Integer id, TipoTrabajo entity, EntityManager entityManager) {
		throw new UnsupportedOperationException();
	}

}