package Dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

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
	
	public void asignarTipoTrabajo(Integer id_trabajo, Integer id_tipoTrabajo) {
		/* mirar UsuarioDAO asignar palabraClave
		 * 
		 * no usar el findById();
		 * 
		 * usar esto: Trabajo t = entityManager.find(Trabajo.class, id);
		 * 
		 * recordar cerrar EM con close() antes del throw exception si trajo null
		 * 
		 * hacer el begin luego
		 * 
		 * */
	}

	public Trabajo getTrabajoConPropiedades(Integer trabajoId) {
		EntityManager entityManager=EMF.createEntityManager();
		Query query = entityManager.createNativeQuery("SELECT * FROM trabajo WHERE id = :trabajoId",Trabajo.class);
		query.setParameter("trabajoId", trabajoId);
		entityManager.close();
		return (Trabajo) query.getSingleResult();
	}
	
	public List<Trabajo> getTrabajoConMismasPalabrasClave(Integer palabraClaveId) {
		EntityManager entityManager=EMF.createEntityManager();
		Query query = entityManager.createNativeQuery("SELECT t.* FROM trabajo t JOIN trabajo_palabraClave tp ON t.id = tp.trabajo_id WHERE tp.palabraClave_id = :palabraClaveId",Trabajo.class);
		query.setParameter("palabraClaveId", palabraClaveId);
		entityManager.close();
		return query.getResultList();
	}

	public List<Trabajo> findAll() {
		EntityManager entityManager=EMF.createEntityManager();
		Query query = entityManager.createNativeQuery("SELECT * FROM trabajo",Trabajo.class);
		entityManager.close();
		return query.getResultList();
	}

	public Trabajo update(Integer id, Trabajo entity) {
		throw new UnsupportedOperationException();
	}

	public boolean delete(Integer id) {
		Trabajo trabajo = this.findById(id);		
		if(trabajo != null) {
			EntityManager entityManager=EMF.createEntityManager();
			entityManager.getTransaction().begin();
			entityManager.remove(trabajo);
			entityManager.getTransaction().commit();
			entityManager.close();
			return true;
		}
		return false;
	}
}
