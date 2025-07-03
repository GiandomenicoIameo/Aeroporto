package model;
import java.util.ArrayList;

public class Generico extends Utente {

	private ArrayList<Prenotazione> prenotazioni;

	private String nome;
	private String cognome;
	private String telefono;
	private int anni;

	// protected Generico( String username, String password ) {
	// 	super( username, password );
	// }

	// Un utente generico potrebbe non avere effettuato nessuna prenotazione
	// quindi la creazione di un esemplare di Generico non richiede la
	// creazione di un esemplare di Prenotazione.

	public Generico() {
		prenotazioni = new ArrayList<>();
	}

	public void setUsername( String username ) {
		super.setUsername( username );
	}

	public void setPassword( String password ) {
		super.setPassword( password );
	}

	public void setNome( String nome ) {
		this.nome = nome;
	}

	public void setCognome( String cognome ) {
		this.cognome = cognome;
	}

	public void setTelefono( String telefono ) {
		this.telefono = telefono;
	}

	public void setAnni( int anni ) {
		this.anni = anni;
	}

	public String getUsername() {
		return super.getUsername();
	}

	public String getPassword() {
		return super.getPassword();
	}

	public String getNome() {
		return nome;
	}

	public String getCognome() {
		return cognome;
	}

	public String getTelefono() {
		return telefono;
	}

	public int getAnni() {
		return anni;
	}

	public ArrayList<Prenotazione> getPrenotazioni() {
		return prenotazioni;
	}

	public void effettuaPrenotazione( Prenotazione prenotazione ) {
			prenotazioni.add( prenotazione );
	}

	public Prenotazione rimuoviPrenotazione( Prenotazione prenotazione ) {
		prenotazioni.remove( prenotazione );
		prenotazione.getVoloAssociato().rimuoviPrenotazione( prenotazione );
		return null;
	}

	public String visualizzaStatoPrenotazione( Prenotazione prenotazione ) {
		if( prenotazioni.contains( prenotazione ) )
			return prenotazione.getStato();
		return "Prenotazione non esistente!";
	}

	public int contaPrenotazioni() {
		return prenotazioni.size();
	}

	public boolean cercaPrenotazione( String codice ) {
		for( Prenotazione p : prenotazioni ) {
			if( p.getVoloAssociato().getCodice().equals( codice ) )
				return true;
		}
		return false;
	}

	public Prenotazione cercaPrenotazione( String codice, String nome, String cognome, String bagagli ) {
		for( Prenotazione p : prenotazioni ) {
			if( p.getVoloAssociato().getCodice().equals( codice ) && nome.equals( p.getNomePasseggero() ) &&
				cognome.equals( p.getCognomePasseggero() ) && p.getNumeroBagagli().equals( bagagli ) )
				return p;
		}
		return null;
	}

	public boolean confrontaPrenotazione( Prenotazione prenotazione ) {

		for( Prenotazione p : prenotazioni ) {
			if( p.equals( prenotazione ) ) return true;
		}
		return false;
	}

	public String[] visualizzaPrenotazione( Prenotazione prenotazione ) {
		if( prenotazioni.contains( prenotazione ) )
			return prenotazione.toStringa();
		return null;
	}

//	public String[][] getPrenotazioni() {
//
//		if ( prenotazioni.size() == 0 ) {
//			System.out.println( "dimensione 0" );
//			return null;
//		}
//		else {
//			String[][] matrice = new String[ prenotazioni.size() ][];
//
//			int passo = 0;
//			for( Prenotazione p : prenotazioni )
//				matrice[ passo++ ] = p.toStringa();
//			return matrice;
//		}
//	}
}
