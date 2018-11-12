package Dao;

import java.util.List;

import javax.persistence.EntityManager;
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
		EntityManager entityManager=EMF.createEntityManager();
		PalabrasClave pc = entityManager.find(PalabrasClave.class, id);
		entityManager.close();
		return pc;
	}

	public PalabrasClave persist(PalabrasClave palabrasClave) {
		EntityManager entityManager=EMF.createEntityManager();
		entityManager.getTransaction().begin();
		entityManager.persist(palabrasClave);
		entityManager.getTransaction().commit();
		entityManager.close();
		return palabrasClave;
	}

	public List<PalabrasClave> findAll() {
		throw new UnsupportedOperationException();
	}

	public boolean delete(Integer id) {
		throw new UnsupportedOperationException();
	}

	public PalabrasClave update(Integer id, PalabrasClave entity) {
		throw new UnsupportedOperationException();
	}

}