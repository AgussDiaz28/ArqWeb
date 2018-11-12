package Dao;

import java.util.List;

import javax.persistence.EntityManager;
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
		EntityManager entityManager=EMF.createEntityManager();
		TipoTrabajo tt = entityManager.find(TipoTrabajo.class, id);
		entityManager.close();
		return tt;
	}

	public TipoTrabajo persist(TipoTrabajo tipoTrabajo) {
		EntityManager entityManager=EMF.createEntityManager();
		entityManager.getTransaction().begin();
		entityManager.persist(tipoTrabajo);
		entityManager.getTransaction().commit();
		entityManager.close();
		return tipoTrabajo;
	}
	
	public List<TipoTrabajo> findAll() {
		throw new UnsupportedOperationException();
	}

	public boolean delete(Integer id) {
		throw new UnsupportedOperationException();
	}

	public TipoTrabajo update(Integer id, TipoTrabajo entity) {
		throw new UnsupportedOperationException();
	}

}