package Dao;

import java.util.Calendar;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import Entity.LugarTrabajo;
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
	
	public Usuario update(Integer id, Usuario actual) {
		EntityManager entityManager = EMF.createEntityManager();
		Usuario usuario = entityManager.find(Usuario.class, id );
		if(usuario == null) {
			entityManager.close();
			throw new IllegalArgumentException("el usuario no existe");			
		}
		entityManager.getTransaction().begin();
		usuario.setApellido(actual.getApellido());
		usuario.setNombre(actual.getNombre());
		//Lugar de trabajo no se puede cambiar, como asi tampoco sus trabajos y sus palabras claves,
		//las acciones se realizaran en tareas aparte
		//usuario.setPalabraClave(palabrasClave);
		//usuario.setArticulosPropios(actual.getArticulosPropios());
		//usuario.setLugarTrabajo(actual.getLugarTrabajo());
		entityManager.flush();
		entityManager.getTransaction().commit();
		entityManager.close();
		return usuario;	
	}

	public boolean asignarPalabraClave(Integer id_usuario, Integer id_palabraClave) {
		EntityManager entityManager=EMF.createEntityManager();
		Usuario user = entityManager.find(Usuario.class, id_usuario);
		PalabrasClave palabraClave = entityManager.find(PalabrasClave.class,id_palabraClave);

		if(palabraClave == null) {
			entityManager.close();
			throw new IllegalArgumentException("la palabra clave no existe");
		}
		
		if(user == null) {
			entityManager.close();
			throw new IllegalArgumentException("el usuario no existe");			
		}
		entityManager.getTransaction().begin();
		user.setPalabraClave(palabraClave);
		entityManager.getTransaction().commit();
		entityManager.close();
		return true;
	}

	public Usuario findById(Integer id) {
		EntityManager entityManager=EMF.createEntityManager();
		Usuario u = entityManager.find(Usuario.class, id);
		entityManager.close();
		return u;
	}

	public List<Trabajo> findAllTrabajosEnEvaluacion(Integer id){
		EntityManager entityManager=EMF.createEntityManager();
		Usuario user = entityManager.find(Usuario.class, id);

		if(user == null) {
			entityManager.close();
			throw new IllegalArgumentException("el autor no existe");
		}

		if(!user.isEvaluador()) {
			entityManager.close();
			throw new IllegalArgumentException("el usuario no es evaluador");
		}

		Query query = entityManager.createQuery("SELECT t FROM Trabajo t JOIN t.evaluadores et WHERE et.id = :id");
		query.setParameter("id", id);
		List<Trabajo> trabajos = query.getResultList();
		entityManager.close();
		return trabajos;
	}

	public List<Trabajo> findTrabajosEnEvaluacionEnRango(Integer id, Calendar inicio, Calendar fin){
		EntityManager entityManager=EMF.createEntityManager();
		Usuario user = entityManager.find(Usuario.class, id);

		if(user == null) {
			entityManager.close();
			throw new IllegalArgumentException("el autor no existe");
		}

		if(!user.isEvaluador()) {
			entityManager.close();
			throw new IllegalArgumentException("el usuario no es evaluador");
		}

		Query query = entityManager.createQuery("SELECT t FROM Trabajo t JOIN t.evaluadores et WHERE et.id = :id AND t.fecha >= :inicio AND t.fecha <= :fin");
		query.setParameter("id", id);
		query.setParameter("inicio", inicio);
		query.setParameter("fin", fin);
		List<Trabajo> trabajos = query.getResultList();
		entityManager.close();
		return trabajos;
	}

	public List<Trabajo> findAllTrabajosEnInvestigacion(Integer id){
		EntityManager entityManager=EMF.createEntityManager();
		Usuario user = entityManager.find(Usuario.class, id);
		if(user == null) {
			entityManager.close();
			throw new IllegalArgumentException("el autor no existe");
		}
		Query query = entityManager.createQuery("SELECT t FROM Trabajo t JOIN t.autores at WHERE at.id = :id");
		query.setParameter("id", id);
		List<Trabajo> trabajos = query.getResultList();
		entityManager.close();
		return trabajos;
	}

	public List<Trabajo> findAllTrabajosEnInvestigacionEnviados(Integer id){
		EntityManager entityManager=EMF.createEntityManager();
		Usuario user = entityManager.find(Usuario.class, id);

		if(user == null) {
			entityManager.close();
			throw new IllegalArgumentException("el autor no existe");
		}

//TO BE IMPLEMENTED IN JPQL
//		Query query = entityManager.createQuery("SELECT DISTINCT(t) FROM Trabajo t, Usuario u "
//				+ "JOIN u.trabajosEnInvestigacion ti "
//				+ "LEFT JOIN u.trabajosEnEvaluacion te "
//				+ "LEFT JOIN u.trabajosPendientes tp "
//				+ "WHERE u.id = :id");
		
		Query query = entityManager.createNativeQuery("SELECT DISTINCT(t.id ),t.* from trabajo t "
				+ "join autor_trabajo sat on (t.id = sat.trabajo_id and sat.autor_id = :id) "
				+ "left join evaluador_trabajo et on (et.trabajo_id = t.id) "
				+ "left join evaluador_trabajoPendiente etp on (etp.trabajoPendiente_id = t.id)",Trabajo.class);
		
		query.setParameter("id", id);
		List<Trabajo> trabajos = query.getResultList();
		entityManager.close();
		return trabajos;	

	}

	public List<Trabajo> findTrabajosInvestigacionByAreaInvestigacion(Integer autor_id, Integer evaluador_id, Integer pc_id){
		EntityManager entityManager=EMF.createEntityManager();
		Usuario user1 = entityManager.find(Usuario.class, autor_id);
		Usuario user2 = entityManager.find(Usuario.class, evaluador_id);

		if(user1 == null) {
			entityManager.close();
			throw new IllegalArgumentException("el autor no existe");
		}

		if(user2 == null) {
			entityManager.close();
			throw new IllegalArgumentException("el evaluador no existe");
		}

		Query query = entityManager.createQuery("SELECT t FROM Trabajo t JOIN t.autores at JOIN t.evaluadores et JOIN t.palabrasClave tpc WHERE at.id = :autor_id AND et.id = :evaluador_id AND tpc.id = :pc_id");
		query.setParameter("autor_id", autor_id);
		query.setParameter("evaluador_id", evaluador_id);
		query.setParameter("pc_id", pc_id);
		List<Trabajo> trabajos = query.getResultList();
		entityManager.close();
		return trabajos;
	}

	public List<Usuario> findAll() {
		throw new UnsupportedOperationException();
	}

	public boolean delete(Integer id_usuario) {
		EntityManager entityManager=EMF.createEntityManager();
		Usuario usuario = entityManager.find(Usuario.class, id_usuario);	
		if(usuario == null) {
			entityManager.close();
			throw new IllegalArgumentException("el usuario no existe");
		}
		entityManager.getTransaction().begin();
		entityManager.remove(usuario);
		entityManager.getTransaction().commit();
		entityManager.close();
		return true;
	}

	public boolean setLugarTrabajo(Integer id_usuario, Integer id_lugarTrabajo) {
		EntityManager entityManager=EMF.createEntityManager();
		Usuario usuario = entityManager.find(Usuario.class, id_usuario);
		LugarTrabajo lugarTrabajo = entityManager.find(LugarTrabajo.class,id_lugarTrabajo);

		if(lugarTrabajo == null) {
			entityManager.close();
			throw new IllegalArgumentException("el lugar de trabajo no existe");
		}

		if(usuario == null) {
			entityManager.close();
			throw new IllegalArgumentException("el usuario no existe");
		}		

		entityManager.getTransaction().begin();
		usuario.setLugarTrabajo(lugarTrabajo);
		entityManager.getTransaction().commit();
		entityManager.close();

		return true;
	}

	public boolean addTrabajoInvestigacion(Integer id_usuario, Integer id_trabajo) {
		EntityManager entityManager=EMF.createEntityManager();
		Usuario usuario = entityManager.find(Usuario.class, id_usuario);
		Trabajo trabajo = entityManager.find(Trabajo.class,id_trabajo);

		if(trabajo == null) {
			entityManager.close();
			throw new IllegalArgumentException("el trabajo no existe");
		}

		if(usuario == null) {
			entityManager.close();
			throw new IllegalArgumentException("el usuario no existe");	
		}

		if(usuario.getTrabajosPendientes().contains(trabajo) || usuario.getTrabajosEnEvaluacion().contains(trabajo)) {
			entityManager.close();
			throw new UnsupportedOperationException("el usuario es revisor de este trabajo");			
		}

		entityManager.getTransaction().begin();		
		usuario.setTrabajoEnInvestigacion(trabajo);
		usuario.setEsAutor(true);
		entityManager.getTransaction().commit();
		entityManager.close();
		return true;
	}	

	public boolean addTrabajoPendiente(Integer id_usuario, Integer id_trabajo) {
		EntityManager entityManager=EMF.createEntityManager();
		Usuario usuario = entityManager.find(Usuario.class, id_usuario);
		Trabajo trabajo = entityManager.find(Trabajo.class,id_trabajo);

		if(trabajo == null) {
			entityManager.close();
			throw new IllegalArgumentException("el trabajo no existe");
		}	

		if(usuario == null) {
			entityManager.close();
			throw new IllegalArgumentException("el usuario no existe");	
		}

		if (!usuario.esEvaluadorApto(trabajo)) {
			entityManager.close();
			throw new UnsupportedOperationException("el revisor no es apto");	
		}

		entityManager.getTransaction().begin();
		usuario.setTrabajoPendiente(trabajo);
		entityManager.getTransaction().commit();
		entityManager.close();
		return true;
	}

	private boolean addTrabajoEvaluacion(Usuario usuario, Trabajo trabajo, EntityManager entityManager) {

		if(usuario.getTrabajosEnEvaluacion().size() >= 3 ) {			
			entityManager.close();
			throw new UnsupportedOperationException("el revisor no puede aceptar mas trabajos");	
		}

		entityManager.getTransaction().begin();
		usuario.removeTrabajoPendiente(trabajo);
		usuario.setTrabajoEnEvaluacion(trabajo);
		usuario.setEsEvaluador(true);
		entityManager.getTransaction().commit();
		entityManager.close();
		return true;
	}

	public boolean aceptarTrabajo(Integer id_usuario, Integer id_trabajo) {
		EntityManager entityManager=EMF.createEntityManager();
		Usuario usuario = entityManager.find(Usuario.class, id_usuario);
		Trabajo trabajo = entityManager.find(Trabajo.class,id_trabajo);

		if(trabajo == null) {
			entityManager.close();
			throw new IllegalArgumentException("el trabajo no existe");
		}

		if(usuario == null) {
			entityManager.close();
			throw new IllegalArgumentException("el usuario no existe");	
		}

		if(!usuario.getTrabajosPendientes().contains(trabajo)) {
			entityManager.close();
			throw new UnsupportedOperationException("el revisor no posee este trabajo en su lista de trabajos pendientes");	
		}		

		return this.addTrabajoEvaluacion(usuario, trabajo, entityManager);
	}

	public boolean rechazarTrabajo(Integer id_usuario, Integer id_trabajo) {
		EntityManager entityManager=EMF.createEntityManager();
		Usuario usuario = entityManager.find(Usuario.class, id_usuario);
		Trabajo trabajo = entityManager.find(Trabajo.class,id_trabajo);

		if(trabajo == null) {
			entityManager.close();
			throw new IllegalArgumentException("el trabajo no existe");
		}

		if(usuario == null) {
			entityManager.close();
			throw new IllegalArgumentException("el usuario no existe");	
		}		

		entityManager.getTransaction().begin();
		usuario.removeTrabajoPendiente(trabajo);
		entityManager.getTransaction().commit();
		entityManager.close();		
		return true;
	}



}
