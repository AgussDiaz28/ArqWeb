package ArqWeb.ArqWeb;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertTrue;

import java.util.Calendar;
import java.util.List;

import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

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

	/* Bugs:
	 * 	Hay que ejecutar los test 2 veces
	 * 	1- para crear el schema
	 * 	2- para cargar los datos
	 * 	3- funciona a partir dela tercera ejecucion 
	 */
	@Test
	public void init() {
		this.pcDAO = PalabrasClaveDAO.getInstance();	
		this.uDAO = UsuarioDAO.getInstance();
		this.tDAO = TrabajoDAO.getInstance();
		this.ttDAO = TipoTrabajoDAO.getInstance();
		this.ltDAO = LugarTrabajoDAO.getInstance();
	}

	@Test(dependsOnMethods= {"init"})
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

	@Test(dependsOnMethods= {"crearTipoTrabajos"})
	public void crearLugarTrabajo() {
		LugarTrabajo lt = new LugarTrabajo();
		lt.setNombre("qwavee");
		this.ltDAO.persist(lt);

		assertEquals(lt,this.ltDAO.findById(lt.getId()));
	}

	@Test(dependsOnMethods= {"crearLugarTrabajo"})
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

	/**
	 * a) especificado en el persistence.xml se inicializa la base de datos si no esta creada
	 * b) Crear 10 usuarios
	 * d-i) en la mayoria de los test mas abajo hacemos un READ del usuario
	 * si no nos trajera el usuario con todos sus datos en la tupla, no funcionarian dichos test.
	 * 
	 * Queda implicito que el c-i) funciona. EL toString de usuario solo muestra id, nombre y apellido por simplicidad
	 * cuando hay que listar todos los trabajos con sus propiedades (todos sus autores y evaluadores)
	 */
	@Test(dependsOnMethods= {"crearPalabrasClaves"})
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

		uDAO.persist(u1);
		uDAO.persist(u2);
		uDAO.persist(u3);
		uDAO.persist(u4);
		uDAO.persist(u5);
		uDAO.persist(u6);
		uDAO.persist(u7);
		uDAO.persist(u8);
		uDAO.persist(u9);
		uDAO.persist(u10);

		assertEquals(u1,uDAO.findById(u1.getId()));
		assertEquals(u2,uDAO.findById(u2.getId()));
		assertEquals(u3,uDAO.findById(u3.getId()));
		assertEquals(u4,uDAO.findById(u4.getId()));
		assertEquals(u5,uDAO.findById(u5.getId()));
		assertEquals(u6,uDAO.findById(u6.getId()));
		assertEquals(u7,uDAO.findById(u7.getId()));
		assertEquals(u8,uDAO.findById(u8.getId()));
		assertEquals(u9,uDAO.findById(u9.getId()));
		assertEquals(u10,uDAO.findById(u10.getId()));
	}

	/**
	 * c) Crear 10 trabajos
	 */
	@Test(dependsOnMethods= {"crearUsuarios"})
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

		tDAO.persist(t1);
		tDAO.persist(t2);
		tDAO.persist(t3);
		tDAO.persist(t4);
		tDAO.persist(t5);
		tDAO.persist(t6);
		tDAO.persist(t7);
		tDAO.persist(t8);
		tDAO.persist(t9);
		tDAO.persist(t10);

		assertEquals(t1,tDAO.findById(t1.getId()));
		assertEquals(t2,tDAO.findById(t2.getId()));
		assertEquals(t3,tDAO.findById(t3.getId()));
		assertEquals(t4,tDAO.findById(t4.getId()));
		assertEquals(t5,tDAO.findById(t5.getId()));
		assertEquals(t6,tDAO.findById(t6.getId()));
		assertEquals(t7,tDAO.findById(t7.getId()));
		assertEquals(t8,tDAO.findById(t8.getId()));
		assertEquals(t9,tDAO.findById(t9.getId()));
		assertEquals(t10,tDAO.findById(t10.getId()));
	}

	@Test(dependsOnMethods= {"crearTrabajos"})
	public void setPalabrasClaveUsuarios() {
		Usuario u1 = this.uDAO.findById(1);
		Usuario u2 = this.uDAO.findById(2);
		Usuario u3 = this.uDAO.findById(3);
		Usuario u4 = this.uDAO.findById(4);
		Usuario u5 = this.uDAO.findById(5);
		Usuario u6 = this.uDAO.findById(6);
		Usuario u7 = this.uDAO.findById(7);
		Usuario u8 = this.uDAO.findById(8);
		Usuario u9 = this.uDAO.findById(9);
		Usuario u10 = this.uDAO.findById(10);

		PalabrasClave pc1 = this.pcDAO.findById(1);
		PalabrasClave pc2  = this.pcDAO.findById(2);

		assertTrue(this.uDAO.asignarPalabraClave(u1.getId(),pc1.getId()));
		assertTrue(this.uDAO.asignarPalabraClave(u2.getId(),pc1.getId()));
		assertTrue(this.uDAO.asignarPalabraClave(u3.getId(),pc1.getId()));
		assertTrue(this.uDAO.asignarPalabraClave(u3.getId(),pc2.getId()));
		assertTrue(this.uDAO.asignarPalabraClave(u4.getId(),pc2.getId()));
		assertTrue(this.uDAO.asignarPalabraClave(u5.getId(),pc2.getId()));
		assertTrue(this.uDAO.asignarPalabraClave(u6.getId(),pc1.getId()));
		assertTrue(this.uDAO.asignarPalabraClave(u6.getId(),pc2.getId()));
		assertTrue(this.uDAO.asignarPalabraClave(u7.getId(),pc1.getId()));
		assertTrue(this.uDAO.asignarPalabraClave(u8.getId(),pc2.getId()));
		assertTrue(this.uDAO.asignarPalabraClave(u9.getId(),pc1.getId()));
		assertTrue(this.uDAO.asignarPalabraClave(u9.getId(),pc2.getId()));
		assertTrue(this.uDAO.asignarPalabraClave(u10.getId(),pc2.getId()));
	}

	@Test(dependsOnMethods= {"setPalabrasClaveUsuarios"})
	public void setTipoTrabajos() {
		Trabajo t1 = this.tDAO.findById(1);
		Trabajo t2 = this.tDAO.findById(2);
		Trabajo t3 = this.tDAO.findById(3);
		Trabajo t4 = this.tDAO.findById(4);
		Trabajo t5 = this.tDAO.findById(5);
		Trabajo t6 = this.tDAO.findById(6);
		Trabajo t7 = this.tDAO.findById(7);
		Trabajo t8 = this.tDAO.findById(8);
		Trabajo t9 = this.tDAO.findById(9);
		Trabajo t10 = this.tDAO.findById(10);

		TipoTrabajo typeT1 = this.ttDAO.findById(1);
		TipoTrabajo typeT2 = this.ttDAO.findById(2);

		this.tDAO.asignarTipoTrabajo(t1.getId(), typeT1.getId());
		this.tDAO.asignarTipoTrabajo(t2.getId(), typeT1.getId());
		this.tDAO.asignarTipoTrabajo(t3.getId(), typeT2.getId());
		this.tDAO.asignarTipoTrabajo(t4.getId(), typeT2.getId());
		this.tDAO.asignarTipoTrabajo(t5.getId(), typeT1.getId());
		this.tDAO.asignarTipoTrabajo(t6.getId(), typeT1.getId());
		this.tDAO.asignarTipoTrabajo(t7.getId(), typeT2.getId());
		this.tDAO.asignarTipoTrabajo(t8.getId(), typeT2.getId());
		this.tDAO.asignarTipoTrabajo(t9.getId(), typeT1.getId());
		this.tDAO.asignarTipoTrabajo(t10.getId(), typeT2.getId());

		assertEquals(this.tDAO.findById(1).getTipoTrabajo(),this.ttDAO.findById(1));
		assertEquals(this.tDAO.findById(2).getTipoTrabajo(),this.ttDAO.findById(1));
		assertEquals(this.tDAO.findById(3).getTipoTrabajo(),this.ttDAO.findById(2));
		assertEquals(this.tDAO.findById(4).getTipoTrabajo(),this.ttDAO.findById(2));
		assertEquals(this.tDAO.findById(5).getTipoTrabajo(),this.ttDAO.findById(1));
		assertEquals(this.tDAO.findById(6).getTipoTrabajo(),this.ttDAO.findById(1));
		assertEquals(this.tDAO.findById(7).getTipoTrabajo(),this.ttDAO.findById(2));
		assertEquals(this.tDAO.findById(8).getTipoTrabajo(),this.ttDAO.findById(2));
		assertEquals(this.tDAO.findById(9).getTipoTrabajo(),this.ttDAO.findById(1));
		assertEquals(this.tDAO.findById(10).getTipoTrabajo(),this.ttDAO.findById(2));
	}

	@Test(dependsOnMethods= {"setTipoTrabajos"})
	public void setPalabrasClaveTrabajos() {
		Trabajo t1 = this.tDAO.findById(1);
		Trabajo t2 = this.tDAO.findById(2);
		Trabajo t3 = this.tDAO.findById(3);
		Trabajo t4 = this.tDAO.findById(4);
		Trabajo t5 = this.tDAO.findById(5);
		Trabajo t6 = this.tDAO.findById(6);
		Trabajo t7 = this.tDAO.findById(7);
		Trabajo t8 = this.tDAO.findById(8);
		Trabajo t9 = this.tDAO.findById(9);
		Trabajo t10 = this.tDAO.findById(10);

		PalabrasClave pc1 = this.pcDAO.findById(1);
		PalabrasClave pc2  = this.pcDAO.findById(2);		

		assertTrue(this.tDAO.asignarPalabraClave(t1.getId(), pc1.getId()));
		assertTrue(this.tDAO.asignarPalabraClave(t1.getId(), pc2.getId()));
		assertTrue(this.tDAO.asignarPalabraClave(t2.getId(), pc2.getId()));
		assertTrue(this.tDAO.asignarPalabraClave(t3.getId(), pc1.getId()));
		assertTrue(this.tDAO.asignarPalabraClave(t3.getId(), pc2.getId()));
		assertTrue(this.tDAO.asignarPalabraClave(t4.getId(), pc2.getId()));
		assertTrue(this.tDAO.asignarPalabraClave(t5.getId(), pc2.getId()));
		assertTrue(this.tDAO.asignarPalabraClave(t6.getId(), pc1.getId()));
		assertTrue(this.tDAO.asignarPalabraClave(t6.getId(), pc2.getId()));
		assertTrue(this.tDAO.asignarPalabraClave(t7.getId(), pc1.getId()));
		assertTrue(this.tDAO.asignarPalabraClave(t8.getId(), pc2.getId()));
		assertTrue(this.tDAO.asignarPalabraClave(t9.getId(), pc1.getId()));
		assertTrue(this.tDAO.asignarPalabraClave(t10.getId(), pc2.getId()));
	}

	//no evalua trabajos con compañeros en mismo lugar de trabajo
	@Test(dependsOnMethods= {"setPalabrasClaveTrabajos"}, expectedExceptions = {IllegalArgumentException.class, UnsupportedOperationException.class})
	public void checkEvaluadorNoApto() {
		Usuario u1 = this.uDAO.findById(1);
		Usuario u2 = this.uDAO.findById(3);

		Trabajo t1 = this.tDAO.findById(1);
		
		LugarTrabajo lt = this.ltDAO.findById(1);	

		this.uDAO.addTrabajoInvestigacion(u1.getId(),t1.getId());		
		this.uDAO.setLugarTrabajo(u1.getId(), lt.getId());
		this.uDAO.setLugarTrabajo(u2.getId(), lt.getId());

		this.uDAO.addTrabajoPendiente(u2.getId(),t1.getId());
	}
	
	@Test(dependsOnMethods= {"checkEvaluadorNoApto"})
	public void checkEvaluadorApto() {
		Usuario u1 = this.uDAO.findById(1);
		Usuario u2 = this.uDAO.findById(3);

		Trabajo t1 = this.tDAO.findById(3);

		assertTrue(this.uDAO.addTrabajoPendiente(u1.getId(),t1.getId()));
		assertTrue(this.uDAO.addTrabajoPendiente(u2.getId(),t1.getId()));
	}
	
	@Test(dependsOnMethods= {"checkEvaluadorApto"}, expectedExceptions = {IllegalArgumentException.class, UnsupportedOperationException.class})
	public void checkAutorNoEvaluaSuTrabajo() {
		Usuario u = this.uDAO.findById(1);
		Trabajo t = this.tDAO.findById(1);

		this.uDAO.addTrabajoInvestigacion(u.getId(), t.getId());
		this.uDAO.addTrabajoPendiente(u.getId(),t.getId());
	}
	
	@Test(dependsOnMethods= {"checkAutorNoEvaluaSuTrabajo"})
	public void checkEvaluarMaximoTres() {
		Usuario u = this.uDAO.findById(6);

		Trabajo t1 = this.tDAO.findById(1);
		Trabajo t2 = this.tDAO.findById(2);
		Trabajo t3 = this.tDAO.findById(3);
		
		this.uDAO.addTrabajoPendiente(u.getId(),t1.getId());
		this.uDAO.addTrabajoPendiente(u.getId(),t2.getId());
		this.uDAO.addTrabajoPendiente(u.getId(),t3.getId());
		
		assertTrue(this.uDAO.aceptarTrabajo(u.getId(), t1.getId()));
		assertTrue(this.uDAO.aceptarTrabajo(u.getId(), t2.getId()));
		assertTrue(this.uDAO.aceptarTrabajo(u.getId(), t3.getId()));
	}

	@Test(dependsOnMethods= {"checkEvaluarMaximoTres"}, expectedExceptions = {IllegalArgumentException.class, UnsupportedOperationException.class})
	public void checkEvaluarCuartoTrabajo() {
		Usuario u = this.uDAO.findById(6);
		
		Trabajo t4 = this.tDAO.findById(4);
		
		this.uDAO.addTrabajoPendiente(u.getId(),t4.getId());

		this.uDAO.aceptarTrabajo(u.getId(), t4.getId());
	}

	/**
	 * d-ii) devuelve todos los trabajos asignados de un revisor
	 * 
	 * en el test anterior se asignaron 4 trabajos al usuario con id: 3 y acepto 3 de estos (el maximo que puede aceptar)
	 */
	@Test(dependsOnMethods= {"checkEvaluarCuartoTrabajo"})
	public void getAllTrabajosEvaluacion() {
		Usuario u = this.uDAO.findById(6);
		List<Trabajo> tList = this.uDAO.findAllTrabajosEnEvaluacion(u.getId());

		assertEquals(tList.size(),3);

		//		for(Trabajo t: tList) {
		//			System.out.println(t.toString());
		//		}
	}


	/**
	 * d-iii) devuelve todos los trabajos asignados a un revisor que estan en un rango de fecha
	 * 
	 * devuelve solo el trabajo 1 y 3
	 */
	@Test(dependsOnMethods= {"getAllTrabajosEvaluacion"})
	public void getAllTrabajosEnRangoFecha() {
		Usuario u = this.uDAO.findById(6);

		Trabajo t1 = this.tDAO.findById(1);
		Trabajo t2 = this.tDAO.findById(2);
		Trabajo t3 = this.tDAO.findById(3);

		Calendar c1 = Calendar.getInstance();
		Calendar c2 = Calendar.getInstance();
		Calendar c3 = Calendar.getInstance();
		c1.set(2018, 11, 1);
		c2.set(2018, 0, 1);
		c3.set(2018, 9, 1);

		this.tDAO.setFecha(t1.getId(), c1);
		this.tDAO.setFecha(t2.getId(), c2);
		this.tDAO.setFecha(t3.getId(), c3);

		Calendar inicio = Calendar.getInstance();
		Calendar fin = Calendar.getInstance();

		inicio.set(2018, 7, 5);
		fin.set(2018, 11, 28);

		List<Trabajo> tList = this.uDAO.findTrabajosEnEvaluacionEnRango(u.getId(), inicio, fin);

		assertEquals(tList.size(), 2);

		//		for(Trabajo t: tList) {
		//			System.out.println(t.toString());
		//		}
	}

	/**
	 * d-iv) devuelve los trabajos de investigacion enviados. Puede ser autor de muchos trabajos pero solo haber enviado
	 * algunos para revision. ESOS son los trabajos que devuelve
	 */
	@Test(dependsOnMethods= {"getAllTrabajosEnRangoFecha"})
	public void getAllTrabajosEnviados() {
		Usuario u1 = this.uDAO.findById(1);
		Usuario u2 = this.uDAO.findById(9);

		Trabajo t1 = this.tDAO.findById(1);
		Trabajo t2 = this.tDAO.findById(2);
		Trabajo t3 = this.tDAO.findById(4);

		assertTrue(this.uDAO.addTrabajoInvestigacion(u1.getId(), t1.getId()));
		assertTrue(this.uDAO.addTrabajoInvestigacion(u1.getId(), t2.getId()));
		assertTrue(this.uDAO.addTrabajoInvestigacion(u1.getId(), t3.getId()));

		List<Trabajo> tList = this.uDAO.findAllTrabajosEnInvestigacion(u1.getId());
		assertEquals(tList.size(), 3);

		assertTrue(this.uDAO.addTrabajoPendiente(u2.getId(),t1.getId()));
		assertTrue(this.uDAO.addTrabajoPendiente(u2.getId(),t3.getId()));

		List<Trabajo> tListEnviados = this.uDAO.findAllTrabajosEnInvestigacionEnviados(u1.getId());
		
		//uusuario 1 envio el trabajo 1 y 4 al usuario 9 pero en otro test envio el trabajo 4 al usuario 6
		assertEquals(tListEnviados.size(), 3);

		assertTrue(this.uDAO.aceptarTrabajo(u2.getId(), t1.getId()));
		assertTrue(this.uDAO.aceptarTrabajo(u2.getId(), t3.getId()));

		//		for(Trabajo t: tListEnviados) {
		//			System.out.println(t.toString());
		//		}
	}

	/**
	 * e) consultar trabajos de investigacion y sus propiedades
	 */
	@Test(dependsOnMethods= {"getAllTrabajosEnviados"})
	public void CheckTrabajoConPropiedades() {
//		Trabajo t = this.tDAO.getTrabajoConPropiedades(1);
//		String data = t.toString();

		Usuario u1 = this.uDAO.findById(1);

		assertNotNull(this.uDAO.findAllTrabajosEnInvestigacion(u1.getId()));
	}

	/**
	 * f) Dado un autor y un revisor, devuelve trabajos de investigacion (ya enviados y aceptados) en una determinada area de investigacion dada por la palabra clave del trabajo
	 * basicamente, un filtro de trabajos por palabra clave.
	 * 
	 * g) la base de datos se dropea y re-crea en cada inicializacion (especificado en el persistence.xml)
	 */
	@Test(dependsOnMethods= {"CheckTrabajoConPropiedades"})
	public void getTrabajosInvestigacionByArea() {
		/* 
		 * usuario 1 tiene los trabajos: 1, 2, 4
		 * usuario 2 revisa los trabajos 1 y 4
		 * trabajo1 tiene palabra clave 1 y 2
		 * trabajo2 y trabajo4 tienen palabra clave 2 unicamente
		 */
		Usuario u1 = this.uDAO.findById(1);
		Usuario u2 = this.uDAO.findById(6);

		PalabrasClave pc = this.pcDAO.findById(2);

		List<Trabajo> tList = this.uDAO.findTrabajosInvestigacionByAreaInvestigacion(u1.getId(), u2.getId(), pc.getId());


		//el resultado del size sera = 1 para el caso de palabra clave 1 (primer parametro) y sería = 2 para el caso de palabra clave con id 2
		assertEquals(tList.size(),2);		
	}
}
