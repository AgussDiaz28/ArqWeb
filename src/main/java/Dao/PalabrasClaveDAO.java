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

	public PalabrasClave findById(Integer id) {
		EntityManager entityManager = EMF.createEntityManager();
		PalabrasClave palabrasClave = entityManager.find(PalabrasClave.class, id);
		entityManager.close();
		return palabrasClave;	
	}

	public PalabrasClave persist(PalabrasClave palabrasClave) {
		EntityManager entityManager = EMF.createEntityManager();
		entityManager.getTransaction().begin();
		entityManager.persist(palabrasClave);
		entityManager.getTransaction().commit();
		entityManager.close();
		return palabrasClave;
	}
	
	public PalabrasClave merge(PalabrasClave palabrasClave) {
		EntityManager entityManager = EMF.createEntityManager();
		entityManager.getTransaction().begin();
		PalabrasClave pc = entityManager.merge(palabrasClave);
		entityManager.getTransaction().commit();
		entityManager.close();
		return pc;
	}

	public List<PalabrasClave> findAll() {
		EntityManager entityManager = EMF.createEntityManager();
		throw new UnsupportedOperationException();
	}

	public boolean delete(Integer id) {
		EntityManager entityManager = EMF.createEntityManager();
		throw new UnsupportedOperationException();
	}

	public PalabrasClave update(Integer id, PalabrasClave entity) {
		EntityManager entityManager = EMF.createEntityManager();
		throw new UnsupportedOperationException();
	}

}