package ArqWeb.ArqWeb;

import java.util.HashSet;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.hibernate.mapping.Set;

public class Main {
	public static void main(String[] args) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("Trabajo-Especial");
		EntityManager entitymanager = emf.createEntityManager();
		entitymanager.getTransaction( ).begin( );

		//NOMBRE DE ESQUEMA SistemaDeCACIC
		
		Usuario user = new Usuario();
		user.setApellido("Strella");
		user.setNombre("Nicolas");
		user.setEsExperto(false);
		entitymanager.persist( user );
		
		PalabrasClave pc1 = new PalabrasClave();
		pc1.setPalabra("AI");
		pc1.setEsExperto(true);
		PalabrasClave pc2 = new PalabrasClave();
		pc2.setPalabra("BIGDATA");
		pc2.setEsExperto(true);
		PalabrasClave pc3 = new PalabrasClave();
		pc3.setPalabra("PHP");
		PalabrasClave pc4 = new PalabrasClave();
		pc4.setPalabra("MONGODB");
		PalabrasClave pc5 = new PalabrasClave();
		pc5.setPalabra("METAPROGRAMMING");
		pc5.setEsExperto(true);
		PalabrasClave pc6 = new PalabrasClave();
		pc6.setPalabra("OBJETS");
		PalabrasClave pc7 = new PalabrasClave();
		pc7.setPalabra("DEEPLEARNING");
		pc7.setEsExperto(true);
		PalabrasClave pc8 = new PalabrasClave();
		pc8.setPalabra("SWIFT");
		PalabrasClave pc9 = new PalabrasClave();
		pc9.setPalabra("MACHINELEARNING");
		pc9.setEsExperto(true);
		PalabrasClave pc10 = new PalabrasClave();
		pc10.setPalabra("FRAMEWORK");
		
		HashSet<PalabrasClave> gp = new HashSet<PalabrasClave>();
		gp.add(pc1);
		gp.add(pc2);
		gp.add(pc4);
		
		HashSet<Usuario> ga = new HashSet<Usuario>();
		
		Trabajo t1 = new Trabajo();
		
		/*
		Equipo equipo = new Equipo( 2 , "Boca", null );
		entitymanager.persist( equipo );

		Jugador jugador = new Jugador(1, "Juan", "Perez", 28, equipo );
		entitymanager.persist( jugador );
		
		Calendar calendar = Calendar.getInstance();
		calendar.set(2018,06,20);
		
		Torneo torneo = new Torneo(0, "Nacional", calendar);
		entitymanager.persist( torneo );
		
		*/
		
		entitymanager.getTransaction( ).commit( );
		entitymanager.close();
		emf.close();
	}
}
