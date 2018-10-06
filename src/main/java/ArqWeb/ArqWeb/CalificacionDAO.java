package ArqWeb.ArqWeb;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class CalificacionDAO implements DAO<Calificacion,Integer>{
	
	private static CalificacionDAO daoCalificacion;
	
	private CalificacionDAO(){}

	public static CalificacionDAO getInstance() {
		if(daoCalificacion == null)
			daoCalificacion = new CalificacionDAO();
		return daoCalificacion;
	}

	public Calificacion findById(Integer id) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("Trabajo-Especial");
		EntityManager entityManager = emf.createEntityManager();
		Calificacion calificacion = entityManager.find(Calificacion.class, id);
		entityManager.close();
		return calificacion;	
	}

	public Calificacion persist(Calificacion calificacion) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("Trabajo-Especial");
		EntityManager entityManager = emf.createEntityManager();
		entityManager.getTransaction().begin();
		entityManager.persist(calificacion);
		entityManager.getTransaction().commit();
		entityManager.close();
		return calificacion;
	}

	public List<Calificacion> findAll() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("Trabajo-Especial");
		EntityManager entityManager = emf.createEntityManager();
		throw new UnsupportedOperationException();
	}

	public boolean delete(Integer id) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("Trabajo-Especial");
		EntityManager entityManager = emf.createEntityManager();
		throw new UnsupportedOperationException();
	}

	public Calificacion update(Integer id, Calificacion entity) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("Trabajo-Especial");
		EntityManager entityManager = emf.createEntityManager();
		throw new UnsupportedOperationException();
	}

}