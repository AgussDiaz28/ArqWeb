package ArqWeb.ArqWeb;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import Dao.UsuarioDAO;
import Entity.Usuario;

public class UsuarioTest {
	private Usuario user;
	private String userName = "Agustin" ;
	private String userLastName = "Diaz" ;

	@BeforeSuite
	public static void setUpBeforeSuite() throws Exception 
	{
		System.out.println("Arranca el Test de Palabras Claves");
	}

	@Test
	public void newPalabra()
	{
		this.user = new Usuario();
		assertNotNull(this.user);
	}

	@Test 
	public void setNombre()
	{
		assertEquals(this.user.getNombre(), 0);
		this.user.setNombre(this.userName);
	}

	@Test 
	public void setApellido()
	{
		assertEquals(this.user.getApellido(), 0);
		this.user.setApellido(this.userLastName);
	}

	@AfterTest
	public void getNombre() 
	{
		assertEquals(this.user.getNombre(),this.userName);
	}

	@AfterTest
	public void getApellido() 
	{
		assertEquals(this.user.getApellido(),this.userLastName);
	}

	@Test(dataProvider = "datosUsuarios")
	public void setPalabraConDP(boolean n, Usuario u)
	{
		this.user.setNombre(u.getNombre());
		assertEquals(this.user.getNombre(), u.getNombre());
		this.user.setApellido(u.getApellido());
		assertEquals(this.user.getApellido(), u.getApellido());
		
	}

	@DataProvider
	public Object[][] datosUsuarios() {
		System.out.println("dp()");
		Object [][] ob=new Object[10][2];

		String csvFile = "src/resources/usuarios.csv";
		String line = "";
		String cvsSplitBy = ",";
		ArrayList<String> arreglo = new ArrayList<String>();
		try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
			while ((line = br.readLine()) != null) {
				String[] items = line.split(cvsSplitBy);
				for (int i = 0; i<items.length;i++) {
					arreglo.add(items[i]);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}	
		for(int i=0;i<10;i++){
			Usuario u2	= 	new Usuario();
			u2.setNombre(arreglo.get(i));
			u2.setApellido(arreglo.get(i+1));
			ob[i][0]	=	new Integer(i);
			ob[i][1]	=	u2;
			UsuarioDAO uDAO = UsuarioDAO.getInstance();
			System.out.println(u2.getId());
			System.out.println(u2.getNombre());
			System.out.println(u2.getApellido());
			//uDAO.persist(u2);
		}  
		return ob;
	}

}
