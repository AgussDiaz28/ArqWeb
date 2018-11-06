package Dao;

import java.util.Calendar;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import Entity.Calificacion;
import Entity.PalabrasClave;
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
		EntityManager entityManager=EMF.createEntityManager();
		entityManager.getTransaction().begin();
		entityManager.persist(usuario);
		entityManager.getTransaction().commit();
		entityManager.close();
		return usuario;
	}

	public void asignarPalabraClave(Integer id_usuario, Integer id_palabraClave) {
		PalabrasClave palabraClave = PalabrasClaveDAO.getInstance().findById(id_palabraClave);
		if(palabraClave == null) 
			throw new IllegalArgumentException("la palabra clave no existe");
		
		EntityManager entityManager=EMF.createEntityManager();
		Usuario user = entityManager.find(Usuario.class, id_usuario);
		if(user == null) {
			entityManager.close();
			throw new IllegalArgumentException("el usuario no existe");			
		}
		entityManager.getTransaction().begin();
		user.setPalabraClave(palabraClave);
		entityManager.getTransaction().commit();
		entityManager.close();
	}

	public Usuario findById(Integer id) {
		EntityManager entityManager=EMF.createEntityManager();
		Usuario u = entityManager.find(Usuario.class, id);
		entityManager.close();
		return u;
	}

	public List<Trabajo> findAllTrabajosEnEvaluacion(Integer id){
		Usuario user = this.findById(id);
		if(user != null) {
			if(user.isEvaluador()) {
				EntityManager entityManager=EMF.createEntityManager();
				Query query = entityManager.createNativeQuery("SELECT t.* FROM trabajo t JOIN evaluador_trabajo et ON t.id = et.trabajo_id WHERE et.evaluador_id = :id",Trabajo.class);
				query.setParameter("id", id);
				List<Trabajo> trabajos = query.getResultList();
				entityManager.close();
				return trabajos;
			}			
		}
		throw new UnsupportedOperationException();
	}

	public List<Trabajo> findTrabajosEnEvaluacionEnRango(Integer id, Calendar inicio, Calendar fin){
		Usuario user = this.findById(id);
		if(user != null) {
			if(user.isEvaluador()) {
				EntityManager entityManager=EMF.createEntityManager();
				Query query = entityManager.createNativeQuery("SELECT t.* FROM trabajo t JOIN evaluador_trabajo et ON t.id = et.trabajo_id WHERE et.evaluador_id = :id AND t.fecha >= :inicio AND t.fecha <= :fin",Trabajo.class);
				query.setParameter("id", id);
				query.setParameter("inicio", inicio);
				query.setParameter("fin", fin);
				List<Trabajo> trabajos = query.getResultList();
				entityManager.close();
				return trabajos;
			}			
		}
		throw new UnsupportedOperationException();
	}

	public List<Trabajo> findAllTrabajosEnInvestigacion(Integer id){
		Usuario user = this.findById(id);
		if(user != null) {
			EntityManager entityManager=EMF.createEntityManager();
			Query query = entityManager.createNativeQuery("SELECT t.* FROM trabajo t JOIN autor_trabajo at ON t.id = at.trabajo_id WHERE at.autor_id = :id",Trabajo.class);
			query.setParameter("id", id);
			List<Trabajo> trabajos = query.getResultList();
			entityManager.close();
			return trabajos;
		}
		throw new UnsupportedOperationException();
	}

	public List<Trabajo> findAllTrabajosEnInvestigacionEnviados(Integer id){
		Usuario user = this.findById(id);
		if(user != null) {
			EntityManager entityManager=EMF.createEntityManager();
			Query query = entityManager.createNativeQuery("SELECT t.* FROM trabajo t JOIN autor_trabajo at ON t.id = at.trabajo_id JOIN evaluador_trabajoPendiente et ON t.id = et.trabajoPendiente_id WHERE at.autor_id = :id",Trabajo.class);
			query.setParameter("id", id);
			List<Trabajo> trabajos = query.getResultList();
			entityManager.close();
			return trabajos;	
		}
		throw new UnsupportedOperationException();
	}

	public List<Trabajo> findTrabajosInvestigacionByAreaInvestigacion(Integer autor_id, Integer evaluador_id, Integer pc_id){
		Usuario user1 = this.findById(autor_id);
		Usuario user2 = this.findById(evaluador_id);
		if(user1 != null && user2 != null) {
			EntityManager entityManager=EMF.createEntityManager();
			Query query = entityManager.createNativeQuery("SELECT t.* FROM trabajo t JOIN autor_trabajo at ON t.id = at.trabajo_id JOIN evaluador_trabajo et ON et.trabajo_id = t.id JOIN trabajo_palabraClave tpc ON tpc.palabraClave_id = :pc_id AND tpc.trabajo_id = t.id WHERE at.autor_id = :autor_id AND et.evaluador_id = :evaluador_id",Trabajo.class);
			query.setParameter("autor_id", autor_id);
			query.setParameter("evaluador_id", evaluador_id);
			query.setParameter("pc_id", pc_id);
			List<Trabajo> trabajos = query.getResultList();
			entityManager.close();
			return trabajos;	
		}
		throw new UnsupportedOperationException();
	}

	public List<Usuario> findAll() {
		throw new UnsupportedOperationException();
	}

	public Usuario update(Integer id, Usuario entity) {
		throw new UnsupportedOperationException();
	}

	public boolean delete(Integer id) {
		Usuario usuario = this.findById(id);		
		if(usuario != null) {
			EntityManager entityManager=EMF.createEntityManager();
			entityManager.getTransaction().begin();
			entityManager.remove(usuario);
			entityManager.getTransaction().commit();
			entityManager.close();
			return true;
		}
		return false;
	}

}
