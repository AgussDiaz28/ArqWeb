package ArqWeb.ArqWeb;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

import javax.persistence.EntityManager;

import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import Dao.EMF;
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
	private EntityManager EM;
	
	@BeforeSuite
	public void init() {
		this.pcDAO = PalabrasClaveDAO.getInstance();	
		this.uDAO = UsuarioDAO.getInstance();
		this.tDAO = TrabajoDAO.getInstance();
		this.ttDAO = TipoTrabajoDAO.getInstance();
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
		
		this.ttDAO.persist(typeT1);
		this.ttDAO.persist(typeT2);
		
		assertEquals(typeT1,this.ttDAO.findById(typeT1.getId()));
		assertEquals(typeT2,this.ttDAO.findById(typeT2.getId()));
	}
	
	@Test
	public void crearPalabrasClaves() {
		PalabrasClave pc1 = new PalabrasClave();
		pc1.setEsExperto(true);
		pc1.setPalabra("mysql");
		
		PalabrasClave pc2 = new PalabrasClave();
		pc2.setEsExperto(false);
		pc2.setPalabra("database");
		
		this.pcDAO.persist(pc1);
		this.pcDAO.persist(pc2);
		
		assertEquals(pc1,this.pcDAO.findById(pc1.getId()));
		assertEquals(pc2,this.pcDAO.findById(pc2.getId()));
	}
	
	@Test
	public void crearTrabajos() {
		
		Trabajo t1 = new Trabajo();
		Trabajo t2 = new Trabajo();
		Trabajo t3 = new Trabajo();
		Trabajo t4 = new Trabajo();
		
		tDAO.persist(t1);
		tDAO.persist(t2);
		tDAO.persist(t3);
		tDAO.persist(t4);
		
		assertEquals(t1,tDAO.findById(t1.getId()));
		assertEquals(t2,tDAO.findById(t2.getId()));
		assertEquals(t3,tDAO.findById(t3.getId()));
		assertEquals(t4,tDAO.findById(t4.getId()));
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
		
		uDAO.persist(u1);
		uDAO.persist(u2);
		uDAO.persist(u3);
		uDAO.persist(u4);
		uDAO.persist(u5);
		uDAO.persist(u6);
		
		assertEquals(u1,uDAO.findById(u1.getId()));
		assertEquals(u2,uDAO.findById(u2.getId()));
		assertEquals(u3,uDAO.findById(u3.getId()));
		assertEquals(u4,uDAO.findById(u4.getId()));
		assertEquals(u5,uDAO.findById(u5.getId()));
		assertEquals(u6,uDAO.findById(u6.getId()));
		
	}
	
	@Test
	public void setPalabrasClaveUsuarios() {
		Usuario u1 = this.uDAO.findById(1);
		Usuario u2 = this.uDAO.findById(2);
		Usuario u3 = this.uDAO.findById(3);
		Usuario u4 = this.uDAO.findById(4);
		Usuario u5 = this.uDAO.findById(5);
		Usuario u6 = this.uDAO.findById(6);
		
		PalabrasClave pc1 = this.pcDAO.findById(1);
		PalabrasClave pc2  = this.pcDAO.findById(2);
		
		
		u1.setPalabraClave(pc1);
		u2.setPalabraClave(pc1);
		u3.setPalabraClave(pc1);
		u3.setPalabraClave(pc2);
		u4.setPalabraClave(pc2);
		u5.setPalabraClave(pc2);
		u6.setPalabraClave(pc2);
		
	}
	
	@Test
	public void setTipoTrabajos() {
		Trabajo t1 = this.tDAO.findById(1);
		Trabajo t2 = this.tDAO.findById(2);
		Trabajo t3 = this.tDAO.findById(3);
		Trabajo t4 = this.tDAO.findById(4);
		
		TipoTrabajo typeT1 = this.ttDAO.findById(1);
		TipoTrabajo typeT2 = this.ttDAO.findById(2);
		
		t1.setTipoTrabajo(typeT1);
		t2.setTipoTrabajo(typeT1);
		t3.setTipoTrabajo(typeT2);
		t4.setTipoTrabajo(typeT2);	
	}
	
	@Test
	public void setPalabrasClaveTrabajos() {
		Trabajo t1 = this.tDAO.findById(1);
		Trabajo t2 = this.tDAO.findById(2);
		Trabajo t3 = this.tDAO.findById(3);
		Trabajo t4 = this.tDAO.findById(4);
		
		PalabrasClave pc1 = this.pcDAO.findById(1);
		PalabrasClave pc2  = this.pcDAO.findById(2);
		
		t1.setPalabraClave(pc1);
		t1.setPalabraClave(pc2);
		t2.setPalabraClave(pc2);
		t3.setPalabraClave(pc1);
		t3.setPalabraClave(pc2);
		t4.setPalabraClave(pc2);
		
		assertEquals(this.tDAO.findById(0).getTipoTrabajo().getId(),this.ttDAO.findById(1));
		assertEquals(this.tDAO.findById(1).getTipoTrabajo().getId(),this.ttDAO.findById(2));
		assertEquals(this.tDAO.findById(2).getTipoTrabajo().getId(),this.ttDAO.findById(1));
		assertEquals(this.tDAO.findById(3).getTipoTrabajo().getId(),this.ttDAO.findById(2));
	}
	
	@Test 
	public void checkEvaluadorApto() {
		/* usuario:
		 * 		id: 2; experto: true; palabras clave: mysql, database
		 * 
		 */
		UsuarioDAO uDAO = UsuarioDAO.getInstance();
		Usuario u1 = uDAO.findById(0); //(mysql)
		Usuario u2 = uDAO.findById(2); //(mysql y database)

		TrabajoDAO tDAO = TrabajoDAO.getInstance();
		Trabajo t1 = tDAO.findById(0); //articulo (mysql y database)
		Trabajo t2 = tDAO.findById(2); //poster (mysql y database)
					
		assertFalse(u1.addTrabajoPendiente(t1)); //no es apto
		assertTrue(u2.addTrabajoPendiente(t1)); //es apto
		
		assertTrue(u1.addTrabajoPendiente(t2)); //es apto
		assertTrue(u2.addTrabajoPendiente(t2)); //es apto
		
		u1.addTrabajoInvestigacion(t1);
		LugarTrabajo lt = new LugarTrabajo();
		lt.setNombre("qwavee");
		u1.setLugarTrabajo(lt);
		u2.setLugarTrabajo(lt);
		assertFalse(u2.addTrabajoPendiente(t1)); //no es apto
	}

	@Test
	public void checkAutorNoEvaluaSuTrabajo() {
		/* usuario:
		 * 		id: 0; experto: true; autor: trabajo (id:0; experto: true)
		 * 
		 */
		UsuarioDAO uDAO = UsuarioDAO.getInstance();
		Usuario u = uDAO.findById(0);

		TrabajoDAO tDAO = TrabajoDAO.getInstance();
		Trabajo t = tDAO.findById(0);


		//no debe poder evaluar un trabajo del cual es autor
		u.addTrabajoInvestigacion(t);		
		assertFalse(u.addTrabajoPendiente(t));
	}

	//lo de arriba pero a la inversa - un evaluador no puede declararse como autor de un trabajo q el mismo evalua
	@Test
	public void checkEvaluadorNoEsAutor() {
		/* usuario:
		 * 		id: 0; experto: true; autor: trabajo (id:0; experto: true)
		 * 
		 */
		UsuarioDAO uDAO = UsuarioDAO.getInstance();
		Usuario u = uDAO.findById(0);

		TrabajoDAO tDAO = TrabajoDAO.getInstance();
		Trabajo t = tDAO.findById(0);

		//no debe poder ser autor de un trabajo que tiene en pendiente de evaluacion o que ya acepto para evaluar
		u.addTrabajoPendiente(t);		
		assertFalse(u.addTrabajoInvestigacion(t));		

		u.aceptarTrabajo(t);
		assertFalse(u.addTrabajoInvestigacion(t));		
	}

	@Test
	public void checkEvaluarMaximoTres() {
		UsuarioDAO uDAO = UsuarioDAO.getInstance();
		Usuario u = uDAO.findById(2);

		TrabajoDAO tDAO = TrabajoDAO.getInstance();
		Trabajo t1 = tDAO.findById(0);
		Trabajo t2 = tDAO.findById(1);
		Trabajo t3 = tDAO.findById(2);
		Trabajo t4 = tDAO.findById(3);
		u.addTrabajoPendiente(t1);
		u.addTrabajoPendiente(t2);
		u.addTrabajoPendiente(t3);
		u.addTrabajoPendiente(t4);
		
		assertTrue(u.aceptarTrabajo(t1));
		assertTrue(u.aceptarTrabajo(t2));
		assertTrue(u.aceptarTrabajo(t3));
		
		//no debe poder aceptar mas de 3 trabajos
		assertFalse(u.aceptarTrabajo(t4));
	}
}
