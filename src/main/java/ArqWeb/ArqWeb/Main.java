package ArqWeb.ArqWeb;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import Dao.CalificacionDAO;
import Dao.PalabrasClaveDAO;
import Dao.TipoTrabajoDAO;
import Dao.TrabajoDAO;
import Dao.UsuarioDAO;
import Entity.Calificacion;
import Entity.PalabrasClave;
import Entity.TipoTrabajo;
import Entity.Trabajo;
import Entity.Usuario;

public class Main {
	public static void main(String[] args) {
//		ArrayList<Usuario> Usuarios = cargarUsuarios();
//		ArrayList<Trabajo> Trabajos = cargarTrabajos();
//		ArrayList<PalabrasClave> PalabrasClave = cargarPalabrasClaves();
//		ArrayList<TipoTrabajo> TipoTrabajos = cargarTipoTrabajos();
//		
//		Usuarios.get(1).setPalabraClave(PalabrasClave.get(0));
//		Usuarios.get(1).setPalabraClave(PalabrasClave.get(1));
//		Usuarios.get(1).setPalabraClave(PalabrasClave.get(2));
//		Usuarios.get(1).setPalabraClave(PalabrasClave.get(3));
//		Usuarios.get(1).setPalabraClave(PalabrasClave.get(4));
//		
//		Trabajos.get(1).setPalabraClave(PalabrasClave.get(1));
//		Trabajos.get(1).setPalabraClave(PalabrasClave.get(2));
//		Trabajos.get(1).setPalabraClave(PalabrasClave.get(3));
//		Trabajos.get(1).setTipoTrabajo(TipoTrabajos.get(0));
//		
//		Trabajos.get(2).setPalabraClave(PalabrasClave.get(1));
//		Trabajos.get(2).setPalabraClave(PalabrasClave.get(2));
//		Trabajos.get(2).setTipoTrabajo(TipoTrabajos.get(1));
//		
//		Trabajos.get(3).setPalabraClave(PalabrasClave.get(5));
//		Trabajos.get(3).setTipoTrabajo(TipoTrabajos.get(2));
//		
//		Usuarios.get(2).addTrabajoInvestigacion(Trabajos.get(2));
//		Usuarios.get(3).addTrabajoInvestigacion(Trabajos.get(2));
//		
//		Usuarios.get(4).addTrabajoPendiente(Trabajos.get(1));
//		Usuarios.get(4).addTrabajoPendiente(Trabajos.get(2));
//		Usuarios.get(4).addTrabajoPendiente(Trabajos.get(3));
//		
//		Usuarios.get(1).addTrabajoPendiente(Trabajos.get(3));
//		Usuarios.get(1).addTrabajoPendiente(Trabajos.get(2));
//		Usuarios.get(1).addTrabajoPendiente(Trabajos.get(1));
//		
//		Usuarios.get(1).aceptarTrabajo(Trabajos.get(2));
//				
//		Usuarios.get(1).calificarTrabajo(Trabajos.get(2), 10);
//		
//		persistTipoTrabajos(TipoTrabajos);
//		persistPalabrasClave(PalabrasClave);
//		persistTrabajos(Trabajos);
//		persistUsers(Usuarios);

	}
	
	
//	private static boolean convertToBoolean(String num)
//	{
//		if	(num.equals("1")) {
//			return true;
//		}else if (num.equals("0")) {
//			return false;
//		}else {
//			throw new IllegalArgumentException("El valor solo puede ser 0 o 1");
//		}
//	}
//	
//	public static ArrayList<String> readCSV(String FilePath) {
//		String line = "";
//		String cvsSplitBy = ",";
//		ArrayList<String> arreglo = new ArrayList<String>();
//		try (BufferedReader br = new BufferedReader(new FileReader(FilePath))) {
//			while ((line = br.readLine()) != null) {
//				String[] items = line.split(cvsSplitBy);
//				for (int i = 0; i<items.length;i++) {
//					arreglo.add(items[i]);
//				}
//			}
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		return arreglo;
//	}
//	
//	public static ArrayList<Usuario> cargarUsuarios() {
//		ArrayList<String> arreglo = readCSV("src/resources/usuarios.csv");
//		ArrayList<Usuario> arregloU = new ArrayList<Usuario>();
//		UsuarioDAO uDAO = UsuarioDAO.getInstance();	
//		for(int i=0;i<arreglo.size();i+=2){
//			Usuario u2	= 	new Usuario();
//			u2.setNombre(arreglo.get(i));
//			u2.setApellido(arreglo.get(i+1));
//			arregloU.add(u2);
//			//uDAO.persist(u2);
//		}  
//		return arregloU;
//	}
//	
//	public static ArrayList<Trabajo> cargarTrabajos() {
//		ArrayList<String> arreglo = readCSV("src/resources/trabajos.csv");
//		ArrayList<Trabajo> arregloT = new ArrayList<Trabajo>();
//		TrabajoDAO tDAO = TrabajoDAO.getInstance();
//		for(int i=0;i<arreglo.size();i++){
//			Trabajo t2	= 	new Trabajo();
//			t2.setDescripcion(arreglo.get(i));
//			arregloT.add(t2);
//			//tDAO.persist(t2);
//		}  
//		return arregloT;
//	}
//	
//	public static ArrayList<PalabrasClave> cargarPalabrasClaves() {
//		ArrayList<String> arreglo = readCSV("src/resources/palabrasClaves.csv");
//		ArrayList<PalabrasClave> arregloPC = new ArrayList<PalabrasClave>();
//		PalabrasClaveDAO pDAO = PalabrasClaveDAO.getInstance();
//		for(int i=0;i<arreglo.size();i+=2){
//			PalabrasClave p2	= 	new PalabrasClave();
//			p2.setPalabra(arreglo.get(i));
//			p2.setEsExperto(convertToBoolean(arreglo.get(i+1)));
//			arregloPC.add(p2);
//			//pDAO.persist(p2);
//		}  
//		return arregloPC;
//	}
//	
//	public static ArrayList<TipoTrabajo> cargarTipoTrabajos() {
//		ArrayList<String> arreglo = readCSV("src/resources/tipoTrabajo.csv");
//		ArrayList<TipoTrabajo> arregloPC = new ArrayList<TipoTrabajo>();
//		TipoTrabajoDAO ttDAO = TipoTrabajoDAO.getInstance();
//		for(int i=0;i<arreglo.size();i+=2){
//			TipoTrabajo tt2	= 	new TipoTrabajo();
//			tt2.setTipo(arreglo.get(i));
//			tt2.setCondEvaluacion(convertToBoolean(arreglo.get(i+1)));
//			arregloPC.add(tt2);
//			//ttDAO.persist(tt2);
//		}  
//		return arregloPC;
//	}
//	
//	public static void persistUsers(ArrayList<Usuario> Usuarios) {
//		UsuarioDAO uDAO = UsuarioDAO.getInstance();
//		for (Usuario U: Usuarios) {
//			uDAO.persist(U);
//		}
//	}
//	
//	public static void persistTrabajos(ArrayList<Trabajo> Trabajos) {
//		TrabajoDAO tDAO = TrabajoDAO.getInstance();
//		for (Trabajo T: Trabajos) {
//			tDAO.persist(T);
//		}
//	}
//	
//	public static void persistTipoTrabajos(ArrayList<TipoTrabajo> TipoTrabajos) {
//		TipoTrabajoDAO ttDAO = TipoTrabajoDAO.getInstance();
//		for (TipoTrabajo TT: TipoTrabajos) {
//			ttDAO.persist(TT);
//		}
//	}
//	
//	public static void persistPalabrasClave(ArrayList<PalabrasClave> PalabrasClave) {
//		PalabrasClaveDAO pcDAO = PalabrasClaveDAO.getInstance();
//		for (PalabrasClave PC: PalabrasClave) {
//			pcDAO.persist(PC);
//		}
//	}
}
