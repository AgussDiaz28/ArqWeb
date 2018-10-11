package Dao;

import java.util.Calendar;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import Entity.Trabajo;
import Entity.Usuario;

public class UsuarioDAO implements DAO<Usuario,Integer>{

	private static UsuarioDAO daoUsuario;

	private UsuarioDAO(){}

	public static UsuarioDAO getInstance() {
		if(daoUsuario == null)
			daoUsuario = new UsuarioDAO();
		return daoUsuario;
	}

	public Usuario persist(Usuario usuario) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("Trabajo-Especial");
		EntityManager entityManager = emf.createEntityManager();
		entityManager.getTransaction().begin();
		entityManager.persist(usuario);
		entityManager.getTransaction().commit();
		entityManager.close();
		return usuario;
	}

	public Usuario findById(Integer id) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("Trabajo-Especial");
		EntityManager entityManager = emf.createEntityManager();
		Usuario usuario = entityManager.find(Usuario.class, id);
		entityManager.close();
		return usuario;	
	}
	
	public List<Trabajo> findAllTrabajosEnEvaluacion(Integer id){
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("Trabajo-Especial");
		EntityManager entityManager = emf.createEntityManager();
		Usuario user = this.findById(id);
		if(user != null) {
			if(user.isEvaluador()) {
				Query query = entityManager.createQuery("SELECT t.* FROM trabajo t JOIN evaluador_trabajo et ON t.id = et.trabajo_id WHERE et.evaluador_id = id");
				List<Trabajo> trabajos = query.getResultList();
				entityManager.close();
				return trabajos;
			}			
		}
		throw new UnsupportedOperationException();
	}
	
	public List<Trabajo> findTrabajosEnEvaluacionEnRango(Integer id, Calendar inicio, Calendar fin){
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("Trabajo-Especial");
		EntityManager entityManager = emf.createEntityManager();
		Usuario user = this.findById(id);
		if(user != null) {
			if(user.isEvaluador()) {
				Query query = entityManager.createQuery("SELECT t.* FROM trabajo t JOIN evaluador_trabajo et ON t.id = et.trabajo_id WHERE et.evaluador_id = id HAVING t.fecha > inicio AND t.fecha < fin");
				List<Trabajo> trabajos = query.getResultList();
				entityManager.close();
				return trabajos;
			}			
		}
		throw new UnsupportedOperationException();
	}
	
	public List<Trabajo> findAllTrabajosEnInvestigacion(Integer id){
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("Trabajo-Especial");
		EntityManager entityManager = emf.createEntityManager();
		Usuario user = this.findById(id);
		if(user != null) {
			if(user.isEvaluador()) {
				Query query = entityManager.createQuery("SELECT t.* FROM trabajo t JOIN autor_trabajo at ON t.id = at.trabajo_id WHERE at.autor_id = id");
				List<Trabajo> trabajos = query.getResultList();
				entityManager.close();
				return trabajos;
			}			
		}
		throw new UnsupportedOperationException();
	}

	public List<Usuario> findAll() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("Trabajo-Especial");
		EntityManager entityManager = emf.createEntityManager();
		throw new UnsupportedOperationException();
	}

	public Usuario update(Integer id, Usuario entity) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("Trabajo-Especial");
		EntityManager entityManager = emf.createEntityManager();
		throw new UnsupportedOperationException();
	}

	public boolean delete(Integer id) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("Trabajo-Especial");
		EntityManager entityManager = emf.createEntityManager();

		Usuario usuario = this.findById(id);		
		if(usuario != null) {
			entityManager.getTransaction().begin();
			entityManager.remove(usuario);
			entityManager.getTransaction().commit();
			entityManager.close();
			return true;
		}
		return false;
	}

}
