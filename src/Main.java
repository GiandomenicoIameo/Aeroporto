import gui.Login;
import model.*;
import controller.*;
import database.ConnessioneDatabase;
import java.sql.*;
import dao.*;
import implement.postgresql.*;
import java.util.ArrayList;
import controller.Controller;

public class Main {
	public static void main( String[] args ) {

		// Controller controller = new Controller();
		// int res = controller.login( "Mario99","password" );
  //
		Login login = new Login();
		login.setLocationRelativeTo(null);
		login.setVisible( true );
  //
		// // Spazio utente generico.
  //
		// Generico mario = controller.getUtente( "Mario99" );
		// // Costruzione delle prenotazioni
		// Prenotazione prenotazione = new Prenotazione( mario, milano );
  //
		// prenotazione.setNomePasseggero( mario.getNome() );
		// prenotazione.setNumeroBiglietto( 10 );
		// prenotazione.setPostoAssegnato( 2 );
		// prenotazione.setNumeroBagagli( 1 );
  //
		// controller.effettua( mario.getUsername(), prenotazione );
		// System.out.println( mario.visualizzaPrenotazione( prenotazione ) );

		// GenericoImplementDao utente = new GenericoImplementDao();
		// ArrayList<Generico> list = new ArrayList<>();
  //
		// try {
		// 	list = utente.getAll();
  //
		// 	for( Generico user : list ) {
		// 		System.out.println( "Username: " + user.getUsername() + "," +
		// 							"Password: " + user.getPassword() );
		// 	}
  //
		// } catch( SQLException e ) {
		// 	e.printStackTrace();
		// }

		// Qui di seguito si supporrà il login abbia avuto successo.
		// I costruttori delle classi Generico e Amministratore sono stati
		// dichiarati public.

		// // I voli sono già stati preimpostati dal sistema.
  //
		// Volo roma = new Volo( admin );
  //
		// roma.setCodice( "U0002" );
		// roma.setCompagnia( "Lufthansa" );
		// roma.setDestinazione( "Roma" );
		// roma.setData( "2027-01-01" );
		// roma.setDurata( "44 minuti" );
		// roma.setGate( 3 );
  //
		// // Spazio Amministratore.
		// System.out.println( "Voli associati all'Amministratore:\n" );
		// // L'amministratore visualizza i voli associati.
		// admin.visualizzaVoliAssociati();
		// admin.modificaStatoVolo( milano, "Decollato" );
  //
		// // Spazio utente generico.
		// Generico fred = new Generico( "Fred" );
  //
		// // Costruzione delle prenotazioni
		// Prenotazione prenotazione1 = new Prenotazione( fred, milano );
		// Prenotazione prenotazione2 = new Prenotazione( fred, roma );
  //
		// prenotazione1.setNomePasseggero( fred.getNome() );
		// prenotazione1.setNumeroBiglietto( 10 );
		// prenotazione1.setPostoAssegnato( 2 );
		// prenotazione1.setNumeroBagagli( 1 );
  //
		// prenotazione2.setNomePasseggero( fred.getNome() );
		// prenotazione2.setNumeroBiglietto( 3 );
		// prenotazione2.setPostoAssegnato( 4 );
		// prenotazione2.setNumeroBagagli( 2 );
  //
		// // Effettua le prenotazioni.
		// fred.effettuaPrenotazione( prenotazione1 );
		// fred.effettuaPrenotazione( prenotazione2 );
  //
		// System.out.println( "Prenotazioni effettuate dall'utente\n" );
		// System.out.println( fred.visualizzaPrenotazione( prenotazione1 ) );
		// System.out.println( fred.visualizzaPrenotazione( prenotazione2 ) );
  //
		// // L'utente cerca una prenotazione in base al codice del volo.
		// System.out.println( fred.cercaPrenotazione( "U0002" ) );
	}
}
