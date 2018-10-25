package ArqWeb.ArqWeb;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertNull;
import static org.testng.Assert.assertTrue;

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
	
	@Test
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
	
	@Test
	public void crearLugarTrabajo() {
		LugarTrabajo lt = new LugarTrabajo();
		lt.setNombre("qwavee");
		EM.getTransaction().begin();
		this.ltDAO.persist(lt,this.EM);
		EM.getTransaction().commit();
		
		assertEquals(lt,this.ltDAO.findById(lt.getId(),this.EM));
	}
	
	@Test
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
	
	@Test
	public void crearTrabajos() {
		
		Trabajo t1 = new Trabajo();
		Trabajo t2 = new Trabajo();
		Trabajo t3 = new Trabajo();
		Trabajo t4 = new Trabajo();
		
		EM.getTransaction().begin();
		tDAO.persist(t1,this.EM);
		tDAO.persist(t2,this.EM);
		tDAO.persist(t3,this.EM);
		tDAO.persist(t4,this.EM);
		EM.getTransaction().commit();
		
		assertEquals(t1,tDAO.findById(t1.getId(),this.EM));
		assertEquals(t2,tDAO.findById(t2.getId(),this.EM));
		assertEquals(t3,tDAO.findById(t3.getId(),this.EM));
		assertEquals(t4,tDAO.findById(t4.getId(),this.EM));
	}
	
	@Test
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
		
		EM.getTransaction().begin();
		uDAO.persist(u1,this.EM);
		uDAO.persist(u2,this.EM);
		uDAO.persist(u3,this.EM);
		uDAO.persist(u4,this.EM);
		uDAO.persist(u5,this.EM);
		uDAO.persist(u6,this.EM);
		EM.getTransaction().commit();
		
		assertEquals(u1,uDAO.findById(u1.getId(),this.EM));
		assertEquals(u2,uDAO.findById(u2.getId(),this.EM));
		assertEquals(u3,uDAO.findById(u3.getId(),this.EM));
		assertEquals(u4,uDAO.findById(u4.getId(),this.EM));
		assertEquals(u5,uDAO.findById(u5.getId(),this.EM));
		assertEquals(u6,uDAO.findById(u6.getId(),this.EM));
		
	}
	
	@Test
	public void setPalabrasClaveUsuarios() {
		Usuario u1 = this.uDAO.findById(1,this.EM);
		Usuario u2 = this.uDAO.findById(2,this.EM);
		Usuario u3 = this.uDAO.findById(3,this.EM);
		Usuario u4 = this.uDAO.findById(4,this.EM);
		Usuario u5 = this.uDAO.findById(5,this.EM);
		Usuario u6 = this.uDAO.findById(6,this.EM);
		
		PalabrasClave pc1 = this.pcDAO.findById(1,this.EM);
		PalabrasClave pc2  = this.pcDAO.findById(2,this.EM);
		
		EM.getTransaction().begin();
		u1.setPalabraClave(pc1);
		u2.setPalabraClave(pc1);
		u3.setPalabraClave(pc1);
		u3.setPalabraClave(pc2);
		u4.setPalabraClave(pc2);
		u5.setPalabraClave(pc2);
		u6.setPalabraClave(pc2);
		EM.getTransaction().commit();
	}
	
	@Test
	public void setTipoTrabajos() {
		Trabajo t1 = this.tDAO.findById(1,this.EM);
		Trabajo t2 = this.tDAO.findById(2,this.EM);
		Trabajo t3 = this.tDAO.findById(3,this.EM);
		Trabajo t4 = this.tDAO.findById(4,this.EM);
		
		TipoTrabajo typeT1 = this.ttDAO.findById(1,this.EM);
		TipoTrabajo typeT2 = this.ttDAO.findById(2,this.EM);
		
		EM.getTransaction().begin();
		t1.setTipoTrabajo(typeT1);
		t2.setTipoTrabajo(typeT1);
		t3.setTipoTrabajo(typeT2);
		t4.setTipoTrabajo(typeT2);	
		EM.getTransaction().commit();
		
		assertEquals(this.tDAO.findById(1,this.EM).getTipoTrabajo(),this.ttDAO.findById(1,this.EM));
		assertEquals(this.tDAO.findById(2,this.EM).getTipoTrabajo(),this.ttDAO.findById(1,this.EM));
		assertEquals(this.tDAO.findById(3,this.EM).getTipoTrabajo(),this.ttDAO.findById(2,this.EM));
		assertEquals(this.tDAO.findById(4,this.EM).getTipoTrabajo(),this.ttDAO.findById(2,this.EM));
	}
	
	@Test
	public void setPalabrasClaveTrabajos() {
		Trabajo t1 = this.tDAO.findById(1,this.EM);
		Trabajo t2 = this.tDAO.findById(2,this.EM);
		Trabajo t3 = this.tDAO.findById(3,this.EM);
		Trabajo t4 = this.tDAO.findById(4,this.EM);
		
		PalabrasClave pc1 = this.pcDAO.findById(1,this.EM);
		PalabrasClave pc2  = this.pcDAO.findById(2,this.EM);
		
		EM.getTransaction().begin();
		t1.setPalabraClave(pc1);
		t1.setPalabraClave(pc2);
		t2.setPalabraClave(pc2);
		t3.setPalabraClave(pc1);
		t3.setPalabraClave(pc2);
		t4.setPalabraClave(pc2);
		EM.getTransaction().commit();
	}
	
	@Test 
	public void zcheckEvaluadorApto() {
		Usuario u1 = this.uDAO.findById(1,this.EM);
		Usuario u2 = this.uDAO.findById(3,this.EM);

		Trabajo t1 = this.tDAO.findById(1,this.EM);
		Trabajo t2 = this.tDAO.findById(3,this.EM);
		
		assertFalse(u1.addTrabajoPendiente(t1));
		assertTrue(u2.addTrabajoPendiente(t1));
		
		assertTrue(u1.addTrabajoPendiente(t2));
		assertTrue(u2.addTrabajoPendiente(t2));
	}
	
	@Test 
	public void zcheckEvaluadorApto2() {
		Usuario u1 = this.uDAO.findById(1,this.EM);
		Usuario u2 = this.uDAO.findById(3,this.EM);

		Trabajo t1 = this.tDAO.findById(1,this.EM);
		
		LugarTrabajo lt = this.ltDAO.findById(1, this.EM);	
		
		u1.addTrabajoInvestigacion(t1);		
		u1.setLugarTrabajo(lt);
		u2.setLugarTrabajo(lt);
		assertFalse(u2.addTrabajoPendiente(t1));
	}

	@Test
	public void zzcheckAutorNoEvaluaSuTrabajo() {
		Usuario u = this.uDAO.findById(1,this.EM);
		Trabajo t = this.tDAO.findById(1,this.EM);
		
		u.addTrabajoInvestigacion(t);		
		assertFalse(u.addTrabajoPendiente(t));
	}

	@Test
	public void zzzcheckEvaluarMaximoTres() {
		Usuario u = this.uDAO.findById(3,this.EM);

		Trabajo t1 = this.tDAO.findById(1,this.EM);
		Trabajo t2 = this.tDAO.findById(2,this.EM);
		Trabajo t3 = this.tDAO.findById(3,this.EM);
		Trabajo t4 = this.tDAO.findById(4,this.EM);
		
		u.addTrabajoPendiente(t1);
		u.addTrabajoPendiente(t2);
		u.addTrabajoPendiente(t3);
		u.addTrabajoPendiente(t4);
		
		assertTrue(u.aceptarTrabajo(t1));
		assertTrue(u.aceptarTrabajo(t2));
		assertTrue(u.aceptarTrabajo(t3));
		
		assertFalse(u.aceptarTrabajo(t4));
	}
}
