package ArqWeb.ArqWeb;

import static org.testng.Assert.assertEquals;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import Dao.TrabajoDAO;
import Entity.Trabajo;
import Entity.Usuario;

public class TrabajoTest {
	
	private Trabajo trabajo;
	
	@Test(dataProvider = "datosTrabajos")
	public void setPalabraConDP(boolean n, Usuario u)
	{
		
	}

	@DataProvider
	public Object[][] datosTrabajos() {
		System.out.println("dp()");
		Object [][] ob=new Object[10][2];

		String csvFile = "src/resources/trabajos.csv";
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
		TrabajoDAO tDAO = TrabajoDAO.getInstance();	
		for(int i=0;i<10;i++){
			Trabajo t2	= 	new Trabajo();
			t2.setDescripcion(arreglo.get(i));
			ob[i][0]	=	new Integer(i);
			ob[i][1]	=	t2;
			tDAO.persist(t2);
			
		}  
		return ob;
	}


}
