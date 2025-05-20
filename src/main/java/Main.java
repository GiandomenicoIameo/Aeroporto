import model.*;

public class Main {
	public static void main( String[] args ) {

		Generico mario = new Generico( "Mario Caruso", "Mario", "Caruso" );

		Prenotazione prenotazione1 = new Prenotazione();
		Prenotazione prenotazione2 = new Prenotazione();

		System.out.println( mario.setPrenotazione( prenotazione1 ) );
		System.out.println( mario.setPrenotazione( prenotazione2 ) );

		System.out.println( mario.contaPrenotazioni() );
		System.out.println( mario.rimuoviPrenotazione( prenotazione2 ) );
		System.out.println( mario.contaPrenotazioni() );

		System.out.println( mario.visualizzaStatoPrenotazione( prenotazione2 ) );
	}
}
