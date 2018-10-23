package ArqWeb.ArqWeb;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import Dao.PalabrasClaveDAO;
import Dao.TipoTrabajoDAO;
import Dao.TrabajoDAO;
import Dao.UsuarioDAO;
import Entity.PalabrasClave;
import Entity.TipoTrabajo;
import Entity.Trabajo;
import Entity.Usuario;

public class mainTest {

	@BeforeSuite
	public void crearYpersistirEntidades() 
	{
		//preguntar si restriccion de evaluacion maximo de 3 articulos tambien lo es para poster y resumen o no

		TipoTrabajoDAO ttDAO = TipoTrabajoDAO.getInstance();
		PalabrasClaveDAO pcDAO = PalabrasClaveDAO.getInstance();
		TrabajoDAO tDAO = TrabajoDAO.getInstance();
		UsuarioDAO uDAO = UsuarioDAO.getInstance();


		//-----------TIPO TRABAJO-----------
		TipoTrabajo typeT1 = new TipoTrabajo();
		typeT1.setTipo("articulo");
		typeT1.setCondEvaluacion(true);

		TipoTrabajo typeT2 = new TipoTrabajo();
		typeT2.setTipo("poster");
		typeT2.setCondEvaluacion(false);		


		//-----------PALABRAS CLAVE-----------
		PalabrasClave pc1 = new PalabrasClave();
		pc1.setEsExperto(true);
		pc1.setPalabra("mysql");

		PalabrasClave pc2 = new PalabrasClave();
		pc2.setEsExperto(false);
		pc2.setPalabra("database");


		//-----------TRABAJOS-----------	
		/* TRABAJOS:
		 * 		id: 0; tipo: articulo; experto: true (mysql y database)
		 *  	id: 1; tipo: articulo; experto: false
		 *   	id: 2; tipo: poster; experto: true (mysql y database)
		 *   	id: 3; tipo: poster; experto: false 
		 */
		Trabajo t1 = new Trabajo();
		Trabajo t2 = new Trabajo();
		Trabajo t3 = new Trabajo();
		Trabajo t4 = new Trabajo();

		t1.setTipoTrabajo(typeT1);
		t2.setTipoTrabajo(typeT1);
		t3.setTipoTrabajo(typeT2);
		t4.setTipoTrabajo(typeT2);	

		t1.setPalabraClave(pc1);
		t1.setPalabraClave(pc2);
		t2.setPalabraClave(pc2);
		t3.setPalabraClave(pc1);
		t3.setPalabraClave(pc2);
		t4.setPalabraClave(pc2);


		//-----------USUARIOS-----------
		/* USUARIOS:
		 * 		id: 0; experto: true (mysql)
		 * 		id: 1; experto: true (mysql)
		 *		id: 2; experto: true (mysql y database)
		 * 		id: 3; experto: false (database)
		 * 		id: 4; experto: false (database)
		 * 		id: 5; experto: false (database)
		 */	
		Usuario u1 = new Usuario();
		u1.setNombre("aaa");
		u1.setApellido("111");
		u1.setPalabraClave(pc1);

		Usuario u2 = new Usuario();
		u2.setNombre("bbb");
		u2.setApellido("222");
		u2.setPalabraClave(pc1);

		Usuario u3 = new Usuario();
		u3.setNombre("ccc");
		u3.setApellido("333");
		u3.setPalabraClave(pc1);
		u3.setPalabraClave(pc2);

		Usuario u4 = new Usuario();
		u4.setNombre("ddd");
		u4.setApellido("444");
		u4.setPalabraClave(pc2);

		Usuario u5 = new Usuario();
		u5.setNombre("eee");
		u5.setApellido("555");
		u5.setPalabraClave(pc2);

		Usuario u6 = new Usuario();
		u6.setNombre("fff");
		u6.setApellido("666");
		u6.setPalabraClave(pc2);

		//PERSISTIR		
		ttDAO.persist(typeT1);
		ttDAO.persist(typeT2);

		pcDAO.persist(pc1);
		pcDAO.persist(pc2);

		tDAO.persist(t1);
		tDAO.persist(t2);
		tDAO.persist(t3);
		tDAO.persist(t4);

		uDAO.persist(u1);
		uDAO.persist(u2);
		uDAO.persist(u3);
		uDAO.persist(u4);
		uDAO.persist(u5);
		uDAO.persist(u6);

		//test persistencia entidades
		assertEquals(typeT1.getId(),ttDAO.findById(typeT1.getId()));
		assertEquals(typeT2.getId(),ttDAO.findById(typeT2.getId()));

		assertEquals(pc1.getId(),pcDAO.findById(pc1.getId()));
		assertEquals(pc2.getId(),pcDAO.findById(pc2.getId()));

		assertEquals(t1.getId(),tDAO.findById(t1.getId()));
		assertEquals(t2.getId(),tDAO.findById(t2.getId()));
		assertEquals(t3.getId(),tDAO.findById(t3.getId()));
		assertEquals(t4.getId(),tDAO.findById(t4.getId()));

		assertEquals(u1.getId(),uDAO.findById(u1.getId()));
		assertEquals(u2.getId(),uDAO.findById(u2.getId()));
		assertEquals(u3.getId(),uDAO.findById(u3.getId()));
		assertEquals(u4.getId(),uDAO.findById(u4.getId()));
		assertEquals(u5.getId(),uDAO.findById(u5.getId()));
		assertEquals(u6.getId(),uDAO.findById(u6.getId()));

		//test persistencia relaciones
		assertEquals(tDAO.findById(0).getTipoTrabajo().getId(),ttDAO.findById(0));
		assertEquals(tDAO.findById(1).getTipoTrabajo().getId(),ttDAO.findById(1));
		assertEquals(tDAO.findById(2).getTipoTrabajo().getId(),ttDAO.findById(0));
		assertEquals(tDAO.findById(3).getTipoTrabajo().getId(),ttDAO.findById(1));

		//palabras clave de trabajos
		for(int i = 0; i<4; i++) {
			for(PalabrasClave pc: tDAO.findById(i).getPalabrasClave()) {
				if(i%2 == 0) {
					assertEquals(pc.getId(),pcDAO.findById(0));
					assertEquals(pc.getId(),pcDAO.findById(1));
				}else {
					assertEquals(pc.getId(),pcDAO.findById(1));
				}				
			}
		}

		//palabras clave de usuarios
		for(int i = 0; i<6; i++) {
			for(PalabrasClave pc: uDAO.findById(i).getPalabrasClave()) {
				if(i<2) {
					assertEquals(pc.getId(),pcDAO.findById(0));
				}else if(i>2) {
					assertEquals(pc.getId(),pcDAO.findById(1));
				}
			}
		}

		//el usuario con id: 2 tiene dos palabras clave (mysql y database)
		for(PalabrasClave pc: uDAO.findById(2).getPalabrasClave()) {
			assertEquals(pc.getId(),pcDAO.findById(0));
			assertEquals(pc.getId(),pcDAO.findById(1));
		}
	}

	@Test void checkEvaluadorApto() {
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
		u1.setLugarTrabajo(1);
		u2.setLugarTrabajo(1);
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
