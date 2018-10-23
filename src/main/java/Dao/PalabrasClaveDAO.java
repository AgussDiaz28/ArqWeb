package Dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import Entity.Calificacion;
import Entity.PalabrasClave;

public class PalabrasClaveDAO implements DAO<PalabrasClave,Integer>{
	
	private static PalabrasClaveDAO daoPalabrasClave;
	
	private PalabrasClaveDAO(){}

	public static PalabrasClaveDAO getInstance() {
		if(daoPalabrasClave == null)
			daoPalabrasClave = new PalabrasClaveDAO();
		return daoPalabrasClave;
	}

	public PalabrasClave findById(Integer id, EntityManager entityManager) {
		return entityManager.find(PalabrasClave.class, id);
	}

	public PalabrasClave persist(PalabrasClave palabrasClave, EntityManager entityManager) {
		entityManager.persist(palabrasClave);
		return palabrasClave;
	}
	
	public EntityManager getEntityManager() {
		return EMF.createEntityManager();	
	}
	
	public PalabrasClave merge(PalabrasClave palabrasClave, EntityManager entityManager) {
		return entityManager.merge(palabrasClave);
	}

	public List<PalabrasClave> findAll( EntityManager entityManager) {
		throw new UnsupportedOperationException();
	}

	public boolean delete(Integer id, EntityManager entityManager) {
		throw new UnsupportedOperationException();
	}

	public PalabrasClave update(Integer id, PalabrasClave entity, EntityManager entityManager) {
		throw new UnsupportedOperationException();
	}

}