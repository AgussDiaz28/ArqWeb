package ArqWeb.ArqWeb;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

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
