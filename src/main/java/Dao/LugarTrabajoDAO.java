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

	public LugarTrabajo findById(Integer id) {
		EntityManager entityManager=EMF.createEntityManager();
		LugarTrabajo lt = entityManager.find(LugarTrabajo.class, id);
		entityManager.close();
		return lt;
	}

	public LugarTrabajo persist(LugarTrabajo lugarTrabajo) {
		EntityManager entityManager=EMF.createEntityManager();
		entityManager.getTransaction().begin();
		entityManager.persist(lugarTrabajo);
		entityManager.getTransaction().commit();
		entityManager.close();
		return lugarTrabajo;
	}
	
	public List<LugarTrabajo> findAll() {
		throw new UnsupportedOperationException();
	}

	public boolean delete(Integer id) {
		throw new UnsupportedOperationException();
	}

	public LugarTrabajo update(Integer id, LugarTrabajo entity) {
		throw new UnsupportedOperationException();
	}

}