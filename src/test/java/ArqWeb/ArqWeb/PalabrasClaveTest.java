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

import Dao.PalabrasClaveDAO;
import Dao.UsuarioDAO;
import Entity.PalabrasClave;

public class PalabrasClaveTest {
	private PalabrasClave palabra; 
	private String palabraKey = "METAPROGRAMMING";

	@BeforeSuite
	public static void setUpBeforeSuite() throws Exception 
	{
		System.out.println("Arranca el Test de Palabras Claves");
	}

	@Test
	public void newPalabra()
	{
		this.palabra = new PalabrasClave();
		assertNotNull(this.palabra);
	}

	@Test 
	public void setNombre()
	{
		assertEquals(this.palabra.getPalabra(), null);
		this.palabra.setPalabra(this.palabraKey);
	}

	@AfterTest
	public void getPalabra() 
	{
		assertEquals(this.palabra.getPalabra(),this.palabraKey);
	}

	@Test(dataProvider = "datosPalabras")
	public void setPalabraConDP(boolean n, PalabrasClave p)
	{
		System.out.println(p.getPalabra());
		this.palabra.setPalabra(p.getPalabra());
		assertEquals(this.palabra.getPalabra(), p.getPalabra());
		this.palabra.setEsExperto(p.isExperto());
		assertEquals(this.palabra.isExperto(), p.isExperto());
	}

	private boolean convertToBoolean(String num)
	{
		if	(num.equals("1")) {
			return true;
		}else if (num.equals("0")) {
			return false;
		}else {
			throw new IllegalArgumentException("El valor solo puede ser 0 o 1");
		}
	}

	@DataProvider
	public Object[][] datosPalabras() {
		System.out.println("dp()");
		Object [][] ob = new Object[10][2];

		String csvFile = "src/resources/palabrasClaves.csv";
		String line = "";
		String cvsSplitBy = ",";
		ArrayList<String> arreglo = new ArrayList<String>();
		try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
			while ((line = br.readLine()) != null) {
				String[] items = line.split(cvsSplitBy);
				for (int i =0; i<items.length;i++) {
					System.out.println(items[i]);
					arreglo.add(items[i]);
				}
			}
		} 
		catch (IOException e) {
			e.printStackTrace();
		}	
		PalabrasClaveDAO pDAO = PalabrasClaveDAO.getInstance();
		for(int i=0;i<10;i++){
			PalabrasClave p2	= 	new PalabrasClave();
			p2.setPalabra(arreglo.get(i));
			p2.setEsExperto(this.convertToBoolean(arreglo.get(i+1)));
			ob[i][0]	=	new Integer(i);
			ob[i][1]	=	p2;
			pDAO.persist(p2);
		}  
		return ob;
	}

}
