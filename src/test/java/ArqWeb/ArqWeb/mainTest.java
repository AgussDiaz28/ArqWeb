package ArqWeb.ArqWeb;

import static org.testng.Assert.assertEquals;

import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import Dao.UsuarioDAO;
import Entity.Usuario;

public class mainTest {
	
	@BeforeSuite
	public static void setUpBeforeSuite() throws Exception 
	{
		System.out.println("Arranca el Test de Funcionalidad");
	}
	
	@BeforeTest
	public void crearYpersistirUsuario() {
		UsuarioDAO uDAO = UsuarioDAO.getInstance();
		Usuario u = new Usuario();
		uDAO.persist(u);
		assertEquals(u.getId(),uDAO.findById(u.getId()));
	}
	
	@Test
	public void crearPalabrasClaves() {
		
	}

	@Test
	public void crearTrabajos() {
		
	}

	

}
