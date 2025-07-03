package model;

import java.util.ArrayList;

public class VoloInPartenza extends Volo {

	private boolean[] posti;
	private int numeroMinimoPosti;

	private String aeroportoDestinazione;
	private ArrayList<Prenotazione> prenotazioni = new ArrayList<>();

	public VoloInPartenza( Amministratore admin, Gate gate ) {
		super( admin, gate );
	}

	public void setNumeroPosti( int numeroPosti ) {
		posti = new boolean[ numeroPosti ];
		numeroMinimoPosti = ( numeroPosti * 3 ) / 4;
	}

	public void setAeroportoDestinazione( String aeroporto ) {
		aeroportoDestinazione = aeroporto;
	}

	public String getAeroportoDestinazione() {
		return aeroportoDestinazione;
	}

	public int getNumeroPosti() {
		return posti.length;
	}

	public boolean numeroPostiSuperato() {
		if( numeroPostiOccupati() >= numeroMinimoPosti )
			return true;
		return false;
	}

	public ArrayList<Prenotazione> getPrenotazioni() {
		return prenotazioni;
	}

	public void aggiungiPrenotazione( Prenotazione prenotazione ) {

//		int numeroPosto = occupaPosto();
		prenotazioni.add( prenotazione );

//		if( numeroPosto >= 0 ) {
//			prenotazioni.add( prenotazione );
//			prenotazione.setPostoAssegnato(  String.valueOf( numeroPosto ) );
//			return true;
//		}
//		return false;
	}

	public void rimuoviPrenotazione( Prenotazione prenotazione ) {
		prenotazioni.remove( prenotazione );
		posti[ Integer.parseInt( prenotazione.getPostoAssegnato() ) ] = false;
	}

	public int contaPrenotazioniAssociate() {
		return prenotazioni.size();
	}

	public int numeroPostiOccupati() {

		int counter = 0;
		for( int pos = 0; pos < posti.length; pos++ ) {
			if( posti[ pos ] == true ) counter++;
		}
		return counter;
	}

	public String toString() {
		return String.format( "%s: %s\n%s: %s\n%s: %s\n%s: %s\n%s: %s\n%s: %s\n%s: %s\n%s: %s\n%s: %s\n",
					"Codice volo", 	  	     	 codice,
					"Compagnia aerea", 	     	 compagniaAerea,
					"Data di partenza", 		 data,
					"Orario di partenza",		 orarioPartenza,
					"Orario di arrivo",			 orarioArrivo,
					"Partenza",					 "Napoli",
					"Destinazione",				 getAeroportoDestinazione(),
					"Numero gate",				 gate.getNumeroGate(),
					"Stato del volo",			 stato );
	}

	public int occupaPosto() {

		for( int pos = 0; pos < posti.length; pos++ ) {
			if( posti[ pos ] == false ) {
				posti[ pos ] = true; return pos;
			}
		}
		return -1;
	}
}
