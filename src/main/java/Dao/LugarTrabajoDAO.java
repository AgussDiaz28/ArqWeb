package Dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import Entity.Calificacion;
import Entity.LugarTrabajo;
import Entity.TipoTrabajo;

public class LugarTrabajoDAO implements DAO<LugarTrabajo,Integer>{
	
	private static LugarTrabajoDAO daoLugarTrabajoDAO;
	
	private LugarTrabajoDAO(){}

	public static LugarTrabajoDAO getInstance() {
		if(daoLugarTrabajoDAO == null)
			daoLugarTrabajoDAO = new LugarTrabajoDAO();
		return daoLugarTrabajoDAO;
	}

	public LugarTrabajo findById(Integer id, EntityManager entityManager) {
		return entityManager.find(LugarTrabajo.class, id);	
	}

	public LugarTrabajo persist(LugarTrabajo lugarTrabajo, EntityManager entityManager) {
		entityManager.persist(lugarTrabajo);
		return lugarTrabajo;
	}
	
	public EntityManager getEntityManager() {
		return EMF.createEntityManager();	
	}

	public LugarTrabajo merge(LugarTrabajo lugarTrabajo, EntityManager entityManager) {
		return entityManager.merge(lugarTrabajo);
	}
	
	public List<LugarTrabajo> findAll( EntityManager entityManager) {
		throw new UnsupportedOperationException();
	}

	public boolean delete(Integer id, EntityManager entityManager) {
		throw new UnsupportedOperationException();
	}

	public LugarTrabajo update(Integer id, LugarTrabajo entity, EntityManager entityManager) {
		throw new UnsupportedOperationException();
	}

}