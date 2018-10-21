package ArqWeb.ArqWeb;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import Dao.PalabrasClaveDAO;
import Dao.TipoTrabajoDAO;
import Dao.TrabajoDAO;
import Dao.UsuarioDAO;
import Entity.PalabrasClave;
import Entity.TipoTrabajo;
import Entity.Trabajo;
import Entity.Usuario;

public class Main {
	
	private static boolean convertToBoolean(String num)
	{
		if	(num.equals("1")) {
			return true;
		}else if (num.equals("0")) {
			return false;
		}else {
			throw new IllegalArgumentException("El valor solo puede ser 0 o 1");
		}
	}
	
	public static ArrayList<String> readCSV(String FilePath) {
		String line = "";
		String cvsSplitBy = ",";
		ArrayList<String> arreglo = new ArrayList<String>();
		try (BufferedReader br = new BufferedReader(new FileReader(FilePath))) {
			while ((line = br.readLine()) != null) {
				String[] items = line.split(cvsSplitBy);
				for (int i = 0; i<items.length;i++) {
					arreglo.add(items[i]);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return arreglo;
	}
	
	public static ArrayList<Usuario> cargarUsuarios() {
		ArrayList<String> arreglo = readCSV("src/resources/usuarios.csv");
		ArrayList<Usuario> arregloU = new ArrayList<Usuario>();
		UsuarioDAO uDAO = UsuarioDAO.getInstance();	
		for(int i=0;i<10;i++){
			Usuario u2	= 	new Usuario();
			u2.setNombre(arreglo.get(i));
			u2.setApellido(arreglo.get(i+1));
			arregloU.add(u2);
			uDAO.persist(u2);
		}  
		return arregloU;
	}
	
	public static ArrayList<Trabajo> cargarTrabajos() {
		ArrayList<String> arreglo = readCSV("src/resources/trabajos.csv");
		ArrayList<Trabajo> arregloT = new ArrayList<Trabajo>();
		TrabajoDAO tDAO = TrabajoDAO.getInstance();
		for(int i=0;i<10;i++){
			Trabajo t2	= 	new Trabajo();
			t2.setDescription(arreglo.get(i));
			arregloT.add(t2);
			tDAO.persist(t2);
		}  
		return arregloT;
	}
	
	public static ArrayList<PalabrasClave> cargarPalabrasClaves() {
		ArrayList<String> arreglo = readCSV("src/resources/palabrasClaves.csv");
		ArrayList<PalabrasClave> arregloPC = new ArrayList<PalabrasClave>();
		PalabrasClaveDAO pDAO = PalabrasClaveDAO.getInstance();
		for(int i=0;i<10;i++){
			PalabrasClave p2	= 	new PalabrasClave();
			p2.setPalabra(arreglo.get(i));
			p2.setEsExperto(convertToBoolean(arreglo.get(i+1)));
			arregloPC.add(p2);
			pDAO.persist(p2);
		}  
		return arregloPC;
	}
	
	public static ArrayList<TipoTrabajo> cargarTipoTrabajos() {
		ArrayList<String> arreglo = readCSV("src/resources/tiposTrabajos.csv");
		ArrayList<TipoTrabajo> arregloPC = new ArrayList<TipoTrabajo>();
		TipoTrabajoDAO ttDAO = TipoTrabajoDAO.getInstance();
		for(int i=0;i<10;i++){
			TipoTrabajo tt2	= 	new TipoTrabajo();
			tt2.setTipo(arreglo.get(i));
			
			arregloPC.add(tt2);
			ttDAO.persist(tt2);
		}  
		return arregloPC;
	}
	
	public static void main(String[] args) {
		
		ArrayList<Usuario> Usuarios = cargarUsuarios();
		ArrayList<Trabajo> Trabajos = cargarTrabajos();
		ArrayList<PalabrasClave> PalabrasClave = cargarPalabrasClaves();

		
	}
}
