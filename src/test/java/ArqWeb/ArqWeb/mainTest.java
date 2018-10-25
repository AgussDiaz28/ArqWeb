package ArqWeb.ArqWeb;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertNull;
import static org.testng.Assert.assertTrue;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;

import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import Dao.EMF;
import Dao.LugarTrabajoDAO;
import Dao.PalabrasClaveDAO;
import Dao.TipoTrabajoDAO;
import Dao.TrabajoDAO;
import Dao.UsuarioDAO;
import Entity.LugarTrabajo;
import Entity.PalabrasClave;
import Entity.TipoTrabajo;
import Entity.Trabajo;
import Entity.Usuario;

public class mainTest {
	
	private PalabrasClaveDAO pcDAO;
	private UsuarioDAO uDAO;
	private TrabajoDAO tDAO;
	private TipoTrabajoDAO ttDAO;
	private LugarTrabajoDAO ltDAO;
	private EntityManager EM;
	
	@BeforeSuite
	public void init() {
		this.pcDAO = PalabrasClaveDAO.getInstance();	
		this.uDAO = UsuarioDAO.getInstance();
		this.tDAO = TrabajoDAO.getInstance();
		this.ttDAO = TipoTrabajoDAO.getInstance();
		this.ltDAO = LugarTrabajoDAO.getInstance();
		this.EM = EMF.createEntityManager();
	}
	
	@Test(priority=1)
	public void crearTipoTrabajos() {
		TipoTrabajo typeT1 = new TipoTrabajo();
		typeT1.setTipo("articulo");
		typeT1.setCondEvaluacion(true);

		TipoTrabajo typeT2 = new TipoTrabajo();
		typeT2.setTipo("poster");
		typeT2.setCondEvaluacion(false);	
		
		EM.getTransaction().begin();
		this.ttDAO.persist(typeT1,this.EM);
		this.ttDAO.persist(typeT2,this.EM);
		EM.getTransaction().commit();
		
		assertEquals(typeT1,this.ttDAO.findById(typeT1.getId(),this.EM));
		assertEquals(typeT2,this.ttDAO.findById(typeT2.getId(),this.EM));
	}
	
	@Test(priority=2)
	public void crearLugarTrabajo() {
		LugarTrabajo lt = new LugarTrabajo();
		lt.setNombre("qwavee");
		EM.getTransaction().begin();
		this.ltDAO.persist(lt,this.EM);
		EM.getTransaction().commit();
		
		assertEquals(lt,this.ltDAO.findById(lt.getId(),this.EM));
	}
	
	@Test(priority=3)
	public void crearPalabrasClaves() {
		PalabrasClave pc1 = new PalabrasClave();
		pc1.setEsExperto(true);
		pc1.setPalabra("mysql");
		
		PalabrasClave pc2 = new PalabrasClave();
		pc2.setEsExperto(false);
		pc2.setPalabra("database");
		
		EM.getTransaction().begin();
		this.pcDAO.persist(pc1,this.EM);
		this.pcDAO.persist(pc2,this.EM);
		EM.getTransaction().commit();
		
		assertEquals(pc1,this.pcDAO.findById(pc1.getId(),this.EM));
		assertEquals(pc2,this.pcDAO.findById(pc2.getId(),this.EM));
	}
	
	/**
	 * a) especificado en el persistence.xml se inicializa la base de datos si no esta creada
	 * b) Crear 10 usuarios
	 * d-i) en la mayoria de los test mas abajo hacemos un READ del usuario
	 * si no nos trajera el usuario con todos sus datos en la tupla, no funcionarian dichos test.
	 * 
	 * Queda implicito que el c-i) funciona. EL toString de usuario solo muestra id, nombre y apellido por simplicidad
	 * cuando hay que listar todos los trabajos con sus propiedades (todos sus autores y evaluadores)
	 */
	@Test(priority=4)
	public void crearUsuarios() {
		Usuario u1 = new Usuario();
		u1.setNombre("aaa");
		u1.setApellido("111");

		Usuario u2 = new Usuario();
		u2.setNombre("bbb");
		u2.setApellido("222");

		Usuario u3 = new Usuario();
		u3.setNombre("ccc");
		u3.setApellido("333");

		Usuario u4 = new Usuario();
		u4.setNombre("ddd");
		u4.setApellido("444");

		Usuario u5 = new Usuario();
		u5.setNombre("eee");
		u5.setApellido("555");
		
		Usuario u6 = new Usuario();
		u6.setNombre("fff");
		u6.setApellido("666");
		
		Usuario u7 = new Usuario();
		u7.setNombre("ggg");
		u7.setApellido("777");
		
		Usuario u8 = new Usuario();
		u8.setNombre("hhh");
		u8.setApellido("888");
		
		Usuario u9 = new Usuario();
		u9.setNombre("iii");
		u9.setApellido("999");
		
		Usuario u10 = new Usuario();
		u10.setNombre("jjj");
		u10.setApellido("101010");
		
		EM.getTransaction().begin();
		uDAO.persist(u1,this.EM);
		uDAO.persist(u2,this.EM);
		uDAO.persist(u3,this.EM);
		uDAO.persist(u4,this.EM);
		uDAO.persist(u5,this.EM);
		uDAO.persist(u6,this.EM);
		uDAO.persist(u7,this.EM);
		uDAO.persist(u8,this.EM);
		uDAO.persist(u9,this.EM);
		uDAO.persist(u10,this.EM);
		EM.getTransaction().commit();
		
		assertEquals(u1,uDAO.findById(u1.getId(),this.EM));
		assertEquals(u2,uDAO.findById(u2.getId(),this.EM));
		assertEquals(u3,uDAO.findById(u3.getId(),this.EM));
		assertEquals(u4,uDAO.findById(u4.getId(),this.EM));
		assertEquals(u5,uDAO.findById(u5.getId(),this.EM));
		assertEquals(u6,uDAO.findById(u6.getId(),this.EM));
		assertEquals(u7,uDAO.findById(u7.getId(),this.EM));
		assertEquals(u8,uDAO.findById(u8.getId(),this.EM));
		assertEquals(u9,uDAO.findById(u9.getId(),this.EM));
		assertEquals(u10,uDAO.findById(u10.getId(),this.EM));
	}
	
	/**
	 * c) Crear 10 trabajos
	 */
	@Test(priority=5)
	public void crearTrabajos() {
		
		Trabajo t1 = new Trabajo();
		Trabajo t2 = new Trabajo();
		Trabajo t3 = new Trabajo();
		Trabajo t4 = new Trabajo();
		Trabajo t5 = new Trabajo();
		Trabajo t6 = new Trabajo();
		Trabajo t7 = new Trabajo();
		Trabajo t8 = new Trabajo();
		Trabajo t9 = new Trabajo();
		Trabajo t10 = new Trabajo();
		
		EM.getTransaction().begin();
		tDAO.persist(t1,this.EM);
		tDAO.persist(t2,this.EM);
		tDAO.persist(t3,this.EM);
		tDAO.persist(t4,this.EM);
		tDAO.persist(t5,this.EM);
		tDAO.persist(t6,this.EM);
		tDAO.persist(t7,this.EM);
		tDAO.persist(t8,this.EM);
		tDAO.persist(t9,this.EM);
		tDAO.persist(t10,this.EM);
		EM.getTransaction().commit();
		
		assertEquals(t1,tDAO.findById(t1.getId(),this.EM));
		assertEquals(t2,tDAO.findById(t2.getId(),this.EM));
		assertEquals(t3,tDAO.findById(t3.getId(),this.EM));
		assertEquals(t4,tDAO.findById(t4.getId(),this.EM));
		assertEquals(t5,tDAO.findById(t5.getId(),this.EM));
		assertEquals(t6,tDAO.findById(t6.getId(),this.EM));
		assertEquals(t7,tDAO.findById(t7.getId(),this.EM));
		assertEquals(t8,tDAO.findById(t8.getId(),this.EM));
		assertEquals(t9,tDAO.findById(t9.getId(),this.EM));
		assertEquals(t10,tDAO.findById(t10.getId(),this.EM));
	}
	
	@Test(priority=6)
	public void setPalabrasClaveUsuarios() {
		Usuario u1 = this.uDAO.findById(1,this.EM);
		Usuario u2 = this.uDAO.findById(2,this.EM);
		Usuario u3 = this.uDAO.findById(3,this.EM);
		Usuario u4 = this.uDAO.findById(4,this.EM);
		Usuario u5 = this.uDAO.findById(5,this.EM);
		Usuario u6 = this.uDAO.findById(6,this.EM);
		Usuario u7 = this.uDAO.findById(7,this.EM);
		Usuario u8 = this.uDAO.findById(8,this.EM);
		Usuario u9 = this.uDAO.findById(9,this.EM);
		Usuario u10 = this.uDAO.findById(10,this.EM);
		
		PalabrasClave pc1 = this.pcDAO.findById(1,this.EM);
		PalabrasClave pc2  = this.pcDAO.findById(2,this.EM);
		
		EM.getTransaction().begin();
		u1.setPalabraClave(pc1);
		u2.setPalabraClave(pc1);
		u3.setPalabraClave(pc1);
		u3.setPalabraClave(pc2);
		u4.setPalabraClave(pc2);
		u5.setPalabraClave(pc2);
		u6.setPalabraClave(pc1);
		u6.setPalabraClave(pc2);
		u7.setPalabraClave(pc1);
		u8.setPalabraClave(pc2);
		u9.setPalabraClave(pc1);
		u10.setPalabraClave(pc2);
		EM.getTransaction().commit();
	}
	
	@Test(priority=7)
	public void setTipoTrabajos() {
		Trabajo t1 = this.tDAO.findById(1,this.EM);
		Trabajo t2 = this.tDAO.findById(2,this.EM);
		Trabajo t3 = this.tDAO.findById(3,this.EM);
		Trabajo t4 = this.tDAO.findById(4,this.EM);
		Trabajo t5 = this.tDAO.findById(5,this.EM);
		Trabajo t6 = this.tDAO.findById(6,this.EM);
		Trabajo t7 = this.tDAO.findById(7,this.EM);
		Trabajo t8 = this.tDAO.findById(8,this.EM);
		Trabajo t9 = this.tDAO.findById(9,this.EM);
		Trabajo t10 = this.tDAO.findById(10,this.EM);
		
		TipoTrabajo typeT1 = this.ttDAO.findById(1,this.EM);
		TipoTrabajo typeT2 = this.ttDAO.findById(2,this.EM);
		
		EM.getTransaction().begin();
		t1.setTipoTrabajo(typeT1);
		t2.setTipoTrabajo(typeT1);
		t3.setTipoTrabajo(typeT2);
		t4.setTipoTrabajo(typeT2);
		t5.setTipoTrabajo(typeT1);	
		t6.setTipoTrabajo(typeT1);	
		t7.setTipoTrabajo(typeT2);	
		t8.setTipoTrabajo(typeT2);	
		t9.setTipoTrabajo(typeT1);	
		t10.setTipoTrabajo(typeT2);	
		EM.getTransaction().commit();
		
		assertEquals(this.tDAO.findById(1,this.EM).getTipoTrabajo(),this.ttDAO.findById(1,this.EM));
		assertEquals(this.tDAO.findById(2,this.EM).getTipoTrabajo(),this.ttDAO.findById(1,this.EM));
		assertEquals(this.tDAO.findById(3,this.EM).getTipoTrabajo(),this.ttDAO.findById(2,this.EM));
		assertEquals(this.tDAO.findById(4,this.EM).getTipoTrabajo(),this.ttDAO.findById(2,this.EM));
		assertEquals(this.tDAO.findById(5,this.EM).getTipoTrabajo(),this.ttDAO.findById(1,this.EM));
		assertEquals(this.tDAO.findById(6,this.EM).getTipoTrabajo(),this.ttDAO.findById(1,this.EM));
		assertEquals(this.tDAO.findById(7,this.EM).getTipoTrabajo(),this.ttDAO.findById(2,this.EM));
		assertEquals(this.tDAO.findById(8,this.EM).getTipoTrabajo(),this.ttDAO.findById(2,this.EM));
		assertEquals(this.tDAO.findById(9,this.EM).getTipoTrabajo(),this.ttDAO.findById(1,this.EM));
		assertEquals(this.tDAO.findById(10,this.EM).getTipoTrabajo(),this.ttDAO.findById(2,this.EM));
	}
	
	@Test(priority=8)
	public void setPalabrasClaveTrabajos() {
		Trabajo t1 = this.tDAO.findById(1,this.EM);
		Trabajo t2 = this.tDAO.findById(2,this.EM);
		Trabajo t3 = this.tDAO.findById(3,this.EM);
		Trabajo t4 = this.tDAO.findById(4,this.EM);
		Trabajo t5 = this.tDAO.findById(5,this.EM);
		Trabajo t6 = this.tDAO.findById(6,this.EM);
		Trabajo t7 = this.tDAO.findById(7,this.EM);
		Trabajo t8 = this.tDAO.findById(8,this.EM);
		Trabajo t9 = this.tDAO.findById(9,this.EM);
		Trabajo t10 = this.tDAO.findById(10,this.EM);
		
		PalabrasClave pc1 = this.pcDAO.findById(1,this.EM);
		PalabrasClave pc2  = this.pcDAO.findById(2,this.EM);
		
		EM.getTransaction().begin();
		t1.setPalabraClave(pc1);
		t1.setPalabraClave(pc2);
		t2.setPalabraClave(pc2);
		t3.setPalabraClave(pc1);
		t3.setPalabraClave(pc2);
		t4.setPalabraClave(pc2);
		t5.setPalabraClave(pc2);
		t6.setPalabraClave(pc1);
		t6.setPalabraClave(pc2);
		t7.setPalabraClave(pc1);
		t8.setPalabraClave(pc2);
		t9.setPalabraClave(pc1);
		t10.setPalabraClave(pc2);
		EM.getTransaction().commit();
	}
	
	@Test(priority=9)
	public void checkEvaluadorApto() {
		Usuario u1 = this.uDAO.findById(1,this.EM);
		Usuario u2 = this.uDAO.findById(3,this.EM);

		Trabajo t1 = this.tDAO.findById(1,this.EM);
		Trabajo t2 = this.tDAO.findById(3,this.EM);
		
		assertFalse(u1.addTrabajoPendiente(t1));
		assertTrue(u2.addTrabajoPendiente(t1));
		
		assertTrue(u1.addTrabajoPendiente(t2));
		assertTrue(u2.addTrabajoPendiente(t2));
	}
	
	@Test(priority=10)
	public void checkEvaluadorApto2() {
		Usuario u1 = this.uDAO.findById(1,this.EM);
		Usuario u2 = this.uDAO.findById(3,this.EM);

		Trabajo t1 = this.tDAO.findById(1,this.EM);
		
		LugarTrabajo lt = this.ltDAO.findById(1, this.EM);	
		
		u1.addTrabajoInvestigacion(t1);		
		u1.setLugarTrabajo(lt);
		u2.setLugarTrabajo(lt);
		assertFalse(u2.addTrabajoPendiente(t1));
	}

	@Test(priority=11)
	public void checkAutorNoEvaluaSuTrabajo() {
		Usuario u = this.uDAO.findById(1,this.EM);
		Trabajo t = this.tDAO.findById(1,this.EM);
		
		u.addTrabajoInvestigacion(t);		
		assertFalse(u.addTrabajoPendiente(t));
	}

	@Test(priority=12)
	public void checkEvaluarMaximoTres() {
		Usuario u = this.uDAO.findById(3,this.EM);

		Trabajo t1 = this.tDAO.findById(1,this.EM);
		Trabajo t2 = this.tDAO.findById(2,this.EM);
		Trabajo t3 = this.tDAO.findById(3,this.EM);
		Trabajo t4 = this.tDAO.findById(4,this.EM);
		
		
		this.EM.getTransaction().begin();
		u.addTrabajoPendiente(t1);
		u.addTrabajoPendiente(t2);
		u.addTrabajoPendiente(t3);
		u.addTrabajoPendiente(t4);
		
		assertTrue(u.aceptarTrabajo(t1));
		assertTrue(u.aceptarTrabajo(t2));
		assertTrue(u.aceptarTrabajo(t3));
		
		assertFalse(u.aceptarTrabajo(t4));
		this.EM.getTransaction().commit();
	}
	
	/**
	 * d-ii) devuelve todos los trabajos asignados de un revisor
	 * 
	 * en el test anterior se asignaron 4 trabajos al usuario con id: 3 y acepto 3 de estos (el maximo que puede aceptar)
	 */
	@Test(priority=13)
	public void getAllTrabajosEvaluacion() {
		Usuario u = this.uDAO.findById(3, this.EM);
		List<Trabajo> tList = uDAO.findAllTrabajosEnEvaluacion(u.getId(), this.EM);
		
		assertEquals(tList.size(),u.getTrabajosEnEvaluacion().size());
		
//		for(Trabajo t: tList) {
//			System.out.println(t.toString());
//		}
	}
	
	
	/**
	 * d-iii) devuelve todos los trabajos asignados a un revisor que estan en un rango de fecha
	 * 
	 * devuelve solo el trabajo 1 y 3
	 */
	@Test(priority=14)
	public void getAllTrabajosEnRangoFecha() {
		Usuario u = this.uDAO.findById(3,this.EM);
		
		Trabajo t1 = this.tDAO.findById(1,this.EM);
		Trabajo t2 = this.tDAO.findById(2,this.EM);
		Trabajo t3 = this.tDAO.findById(3,this.EM);
		
		Calendar c1 = Calendar.getInstance();
		Calendar c2 = Calendar.getInstance();
		Calendar c3 = Calendar.getInstance();
		c1.set(2018, 11, 1);
		c2.set(2018, 0, 1);
		c3.set(2018, 9, 1);
		
		this.EM.getTransaction().begin();
		
		t1.setFecha(c1);
		t2.setFecha(c2);
		t3.setFecha(c3);
		
		this.EM.getTransaction().commit();
		
		Calendar inicio = Calendar.getInstance();
		Calendar fin = Calendar.getInstance();
		
		inicio.set(2018, 7, 5);
		fin.set(2018, 11, 28);
		
		List<Trabajo> tList = uDAO.findTrabajosEnEvaluacionEnRango(u.getId(), inicio, fin, this.EM);
		
		assertEquals(tList.size(), 2);
		
//		for(Trabajo t: tList) {
//			System.out.println(t.toString());
//		}
	}
	
	/**
	 * d-iv) devuelve los trabajos de investigacion enviados. Puede ser autor de muchos trabajos pero solo haber enviado
	 * algunos para revision. ESOS son los trabajos que devuelve
	 */
	@Test(priority=15)
	public void getAllTrabajosEnviados() {
		Usuario u1 = this.uDAO.findById(1,this.EM);
		Usuario u2 = this.uDAO.findById(6,this.EM);
		
		Trabajo t1 = this.tDAO.findById(1, this.EM);
		Trabajo t2 = this.tDAO.findById(2, this.EM);
		Trabajo t3 = this.tDAO.findById(4, this.EM);
		
		this.EM.getTransaction().begin();
		
		assertTrue(u1.addTrabajoInvestigacion(t1));
		assertTrue(u1.addTrabajoInvestigacion(t2));
		assertTrue(u1.addTrabajoInvestigacion(t3));
		
		this.EM.getTransaction().commit();
		
		List<Trabajo> tList = uDAO.findAllTrabajosEnInvestigacion(u1.getId(), this.EM);
		assertEquals(tList.size(), 3);
		
		this.EM.getTransaction().begin();
		
		assertTrue(u2.addTrabajoPendiente(t1));
		assertTrue(u2.addTrabajoPendiente(t3));
		
		this.EM.getTransaction().commit();
		
		//Problemas: nos trae una lista de Objects. No permite castear a Entity.Trabajo. Queriamos probar el toString()
		List<Trabajo> tListEnviados = uDAO.findAllTrabajosEnInvestigacionEnviados(u1.getId(), this.EM);
		assertEquals(tListEnviados.size(), 2);
	}
	
	/**
	 * e) consultar trabajos de investigacion y sus propiedades
	 */
	@Test(priority=16)
	public void CheckTrabajoConPropiedades() {
		Trabajo t = this.tDAO.getTrabajoConPropiedades(1, this.EM);
		String data = t.toString();
//		System.out.println(data);
		
		Usuario u1 = this.uDAO.findById(1,this.EM);
		
		//Problemas: nos trae una lista de Objects. No permite castear a Entity.Trabajo. Queriamos probar el toString()
		assertNotNull(uDAO.findAllTrabajosEnInvestigacion(u1.getId(), this.EM));
	}
	
	/**
	 * f) devuelve trabajos de investigacion en una determinada area de investigacion dada por la palabra clave del trabajo
	 * basicamente, un filtro de trabajos por palabra clave
	 * 
	 * g) la base de datos se dropea y re-crea en cada inicializacion (especificado en el persistence.xml)
	 */
	@Test(priority=17)
	public void getTrabajosInvestigacionByArea() {
		/* usuario 1 tiene los trabajos: 1, 2, 4
		 * trabajo1 tiene palabra clave 1 y 2
		 * trabajo2 y trabajo4 tienen palabra clave 2 unicamente
		 */
		Usuario u1 = this.uDAO.findById(1,this.EM);
		PalabrasClave pc = this.pcDAO.findById(1, this.EM);
		
		List<Trabajo> tList = uDAO.findTrabajosInvestigacionByAreaInvestigacion(u1.getId(), pc.getId(), this.EM);

		int count = 0;
		Set<Trabajo> trabajos = u1.getTrabajosEnInvestigacion();
		for(Trabajo t: trabajos) {
			if(t.getPalabrasClave().contains(pc)) {
				count++;
			}
		}
		
		//el count es 1 para el caso de palabra clave 1 y sería 3 para el caso de palabra clave con id 2
		assertEquals(count, tList.size());
	}
}
