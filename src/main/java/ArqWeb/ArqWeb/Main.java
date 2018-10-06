package ArqWeb.ArqWeb;

import java.util.HashSet;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import Entity.PalabrasClave;
import Entity.Trabajo;
import Entity.Usuario;

public class Main {
	public static void main(String[] args) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("Trabajo-Especial");
		EntityManager entitymanager = emf.createEntityManager();
		entitymanager.getTransaction( ).begin( );

		//NOMBRE DE ESQUEMA SistemaDeCACIC

		//usuario 1
		Usuario user1 = new Usuario();
		user1.setApellido("Strella");
		user1.setNombre("Nicolas");
		entitymanager.persist( user1 );

		//usuario 2
		Usuario user2 = new Usuario();
		user2.setApellido("Diaz");
		user2.setNombre("Agustin");
		entitymanager.persist( user2 );

		//usuario 3
		Usuario user3 = new Usuario();
		user3.setApellido("Diaz");
		user3.setNombre("Bruno");
		entitymanager.persist( user3 );

		//usuario 4
		Usuario user4 = new Usuario();
		user4.setApellido("Kent");
		user4.setNombre("Klark");
		entitymanager.persist( user4 );

		//usuario 5
		Usuario user5 = new Usuario();
		user5.setApellido("Stark");
		user5.setNombre("Tony");
		entitymanager.persist( user5 );

		//usuario 6
		Usuario user6 = new Usuario();
		user6.setApellido("Parker");
		user6.setNombre("Peter");
		entitymanager.persist( user6 );

		//usuario 7
		Usuario user7 = new Usuario();
		user7.setApellido("jordan");
		user7.setNombre("Hal");
		entitymanager.persist( user7 );

		//usuario 8
		Usuario user8 = new Usuario();
		user8.setApellido("Wilson");
		user8.setNombre("Wade");
		entitymanager.persist( user8 );

		//usuario 9
		Usuario user9 = new Usuario();
		user9.setApellido("Castle");
		user9.setNombre("Frank");
		entitymanager.persist( user9 );

		//usuario 10
		Usuario user10 = new Usuario();
		user10.setApellido("Murdock");
		user10.setNombre("Matt");
		entitymanager.persist( user10 );


		PalabrasClave pc1 = new PalabrasClave();
		pc1.setPalabra("AI");
		pc1.setEsExperto(true);
		entitymanager.persist( pc1 );
		PalabrasClave pc2 = new PalabrasClave();
		pc2.setPalabra("BIGDATA");
		pc2.setEsExperto(true);
		entitymanager.persist( pc2 );
		PalabrasClave pc3 = new PalabrasClave();
		pc3.setPalabra("PHP");
		entitymanager.persist( pc3 );
		PalabrasClave pc4 = new PalabrasClave();
		pc4.setPalabra("MONGODB");
		entitymanager.persist( pc4 );
		PalabrasClave pc5 = new PalabrasClave();
		pc5.setPalabra("METAPROGRAMMING");
		pc5.setEsExperto(true);
		entitymanager.persist( pc5 );
		PalabrasClave pc6 = new PalabrasClave();
		pc6.setPalabra("OBJETS");
		entitymanager.persist( pc6 );
		PalabrasClave pc7 = new PalabrasClave();
		pc7.setPalabra("DEEPLEARNING");
		pc7.setEsExperto(true);
		entitymanager.persist( pc7 );
		PalabrasClave pc8 = new PalabrasClave();
		pc8.setPalabra("SWIFT");
		entitymanager.persist( pc8 );
		PalabrasClave pc9 = new PalabrasClave();
		pc9.setPalabra("MACHINELEARNING");
		pc9.setEsExperto(true);
		entitymanager.persist( pc9 );
		PalabrasClave pc10 = new PalabrasClave();
		pc10.setPalabra("FRAMEWORK");
		entitymanager.persist( pc10 );

		HashSet<PalabrasClave> gp = new HashSet<PalabrasClave>();
		gp.add(pc1);
		gp.add(pc2);
		gp.add(pc4);

		Trabajo t1 = new Trabajo();
		user1.addTrabajoInvestigacion(t1);
		entitymanager.persist( t1 );
		entitymanager.persist( user1 );
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
