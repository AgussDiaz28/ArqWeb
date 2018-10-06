package Daos;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import ArqWeb.ArqWeb.PalabrasClave;

public class PalabrasClaveDAO implements DAO<PalabrasClave,Integer>{
	
	private static PalabrasClaveDAO daoPalabrasClave;
	
	private PalabrasClaveDAO(){}

	public static PalabrasClaveDAO getInstance() {
		if(daoPalabrasClave == null)
			daoPalabrasClave = new PalabrasClaveDAO();
		return daoPalabrasClave;
	}

	public PalabrasClave findById(Integer id) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("Trabajo-Especial");
		EntityManager entityManager = emf.createEntityManager();
		PalabrasClave palabrasClave = entityManager.find(PalabrasClave.class, id);
		entityManager.close();
		return palabrasClave;	
	}

	public PalabrasClave persist(PalabrasClave palabrasClave) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("Trabajo-Especial");
		EntityManager entityManager = emf.createEntityManager();
		entityManager.getTransaction().begin();
		entityManager.persist(palabrasClave);
		entityManager.getTransaction().commit();
		entityManager.close();
		return palabrasClave;
	}

	public List<PalabrasClave> findAll() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("Trabajo-Especial");
		EntityManager entityManager = emf.createEntityManager();
		throw new UnsupportedOperationException();
	}

	public boolean delete(Integer id) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("Trabajo-Especial");
		EntityManager entityManager = emf.createEntityManager();
		throw new UnsupportedOperationException();
	}

	public PalabrasClave update(Integer id, PalabrasClave entity) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("Trabajo-Especial");
		EntityManager entityManager = emf.createEntityManager();
		throw new UnsupportedOperationException();
	}

}