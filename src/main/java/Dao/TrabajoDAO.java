package Dao;

import java.util.Calendar;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import Entity.PalabrasClave;
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

	public Trabajo persist(Trabajo trabajo) {
		EntityManager entityManager=EMF.createEntityManager();
		entityManager.getTransaction().begin();
		entityManager.persist(trabajo);
		entityManager.getTransaction().commit();
		entityManager.close();
		return trabajo;
	}

	public Trabajo findById(Integer id) {
		EntityManager entityManager=EMF.createEntityManager();
		Trabajo t = entityManager.find(Trabajo.class, id);
		entityManager.close();
		return t;
	}

	public boolean asignarTipoTrabajo(Integer id_trabajo, Integer id_tipoTrabajo) {
		TipoTrabajo tipoTrabajo = TipoTrabajoDAO.getInstance().findById(id_tipoTrabajo);
		if(tipoTrabajo == null) 
			throw new IllegalArgumentException("el tipo de trabajo no existe");

		EntityManager entityManager=EMF.createEntityManager();
		Trabajo trabajo = entityManager.find(Trabajo.class, id_trabajo);
		if(trabajo == null) {
			entityManager.close();
			throw new IllegalArgumentException("el trabajo no existe");			
		}
		entityManager.getTransaction().begin();
		trabajo.setTipoTrabajo(tipoTrabajo);
		entityManager.getTransaction().commit();
		entityManager.close();
		return true;
	}

	public boolean asignarPalabraClave(Integer id_trabajo, Integer id_palabraClave) {
		PalabrasClave palabrasClave = PalabrasClaveDAO.getInstance().findById(id_palabraClave);
		if(palabrasClave == null) 
			throw new IllegalArgumentException("la palabra clave no existe");

		EntityManager entityManager=EMF.createEntityManager();
		Trabajo trabajo = entityManager.find(Trabajo.class, id_trabajo);
		if(trabajo == null) {
			entityManager.close();
			throw new IllegalArgumentException("el trabajo no existe");			
		}
		entityManager.getTransaction().begin();
		trabajo.setPalabraClave(palabrasClave);
		entityManager.getTransaction().commit();
		entityManager.close();
		return true;
	}
	
	public boolean setFecha(Integer id_trabajo, Calendar fecha) {
		EntityManager entityManager=EMF.createEntityManager();
		Trabajo trabajo = entityManager.find(Trabajo.class, id_trabajo);
		if(trabajo == null) {
			entityManager.close();
			throw new IllegalArgumentException("el trabajo no existe");			
		}
		entityManager.getTransaction().begin();
		trabajo.setFecha(fecha);
		entityManager.getTransaction().commit();
		entityManager.close();
		return true;		
	}

	public Trabajo getTrabajoConPropiedades(Integer trabajoId) {
		EntityManager entityManager=EMF.createEntityManager();
		Query query = entityManager.createQuery("SELECT t FROM Trabajo t WHERE t.id = :trabajoId");
		query.setParameter("trabajoId", trabajoId);
		entityManager.close();
		return (Trabajo) query.getSingleResult();
	}

	public List<Trabajo> getTrabajoConMismasPalabrasClave(Integer palabraClaveId) {
		EntityManager entityManager=EMF.createEntityManager();
		Query query = entityManager.createQuery("SELECT t FROM Trabajo t JOIN t.palabrasClave tp WHERE tp.id = :palabraClaveId");
		query.setParameter("palabraClaveId", palabraClaveId);
		entityManager.close();
		return query.getResultList();
	}

	public List<Trabajo> findAll() {
		EntityManager entityManager=EMF.createEntityManager();
		Query query = entityManager.createQuery("SELECT t FROM Trabajo t");
		entityManager.close();
		return query.getResultList();
	}

	public Trabajo update(Integer id, Trabajo entity) {
		throw new UnsupportedOperationException();
	}

	public boolean delete(Integer id) {
		EntityManager entityManager=EMF.createEntityManager();
		Trabajo trabajo = entityManager.find(Trabajo.class, id);
		if(trabajo == null) {
			entityManager.close();
			throw new IllegalArgumentException("el trabajo no existe");			
		}
		
		entityManager.getTransaction().begin();
		entityManager.remove(trabajo);
		entityManager.getTransaction().commit();
		entityManager.close();
		return true;
	}
}
