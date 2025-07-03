package controller;

import model.*;

import implement.postgresql.GenericoImplementDao;
import implement.postgresql.AmministratoreImplementDao;

import java.sql.*;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

public class Controller {

	private static Controller instance;

	private Amministratore root;
	private Map<String, Generico> guestMap;
	private Map<String, Amministratore> adminMap;

	public static Controller getInstance() {
		if( instance == null )
			instance = new Controller();
		return instance;
	}

	public Controller() {

		guestMap = new HashMap<>();
		adminMap = new HashMap<>();

		try {
			root = new AmministratoreImplementDao().get( "root" );
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public int login( String username, String password ) {

		if( username.equals( root.getUsername() ) && password.equals( root.getPassword() ) ) {
			return 2;
		}

		Generico guest = guestMap.get( username );

		if( guest != null ) {
			if (password.equals( guest.getPassword() ) ) return 0;
			return 3;
		}

		try {
			Generico guest1 = new GenericoImplementDao().get( username );
			if( guest1 != null ) {
				if (password.equals(guest1.getPassword())) {
					guestMap.put( username, guest1 );
					return 0;
				} else {
					return 3;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return 3;
		}

		Amministratore admin = adminMap.get( username );

		if( admin != null ) {
			System.out.println("eccomi");
			if ( password.equals( admin.getPassword() ) ) return 1;
			return 3;
		}
		try {
			Amministratore admin1 = new AmministratoreImplementDao().get( username );
			if( admin1 != null ) {
				if (password.equals(admin1.getPassword())) {
					adminMap.put( username, admin1 ); return 1;
				} else {
					return 3;
				}
			}
			return 3;
		} catch (SQLException e) {
			e.printStackTrace();
			return 3;
		}
	}

	public int aggiungiPrenotazione( String chiaveSessione, String codice, String nome, String cognome, String bagagli ) {

		Generico generico = guestMap.get( chiaveSessione );
		VoloInPartenza volo = null;

		boolean status = false;

		for( Amministratore admin : adminMap.values() ) {
			if( ( volo = ( VoloInPartenza ) admin.trovaVolo( codice ) ) != null )
				status = true;
		}

		if( status == false )
			return 1;

		if( volo.getStato().equals( "Programmato" )  ) {

			Prenotazione prenotazione = new Prenotazione( generico,volo );

			prenotazione.setNomePasseggero( nome );
			prenotazione.setCognomePasseggero( cognome );
			prenotazione.setNumeroBagagli( bagagli );

			int numeroPosto 	   = volo.occupaPosto();
			String numeroBiglietto = volo.getCodice() + String.valueOf( numeroPosto );

			if( numeroPosto >= 0 ) {
				prenotazione.setStatoPrenotazione( "Sospesa" );
				prenotazione.setPostoAssegnato( String.valueOf( numeroPosto ) );
				prenotazione.setNumeroBiglietto( numeroBiglietto );
				volo.aggiungiPrenotazione( prenotazione );
			} else {
				return 4;
			}

			if( volo.numeroPostiSuperato() ) {
				for( Prenotazione p : volo.getPrenotazioni() )
					p.setStatoPrenotazione( "Confermata" );
			}

			if( !generico.confrontaPrenotazione( prenotazione ) ) {
				generico.effettuaPrenotazione( prenotazione ); return 0;
			} return 2;
		} else {
			return 3;
		}
	}

	public String recuperaBiglietto( String chiaveSessione, String codiceVolo,
			String nomePasseggero, String cognomePasseggero, String numeroBagagli ) {

		Generico guest = guestMap.get( chiaveSessione );
		Prenotazione prenotazione = guest.cercaPrenotazione( codiceVolo, nomePasseggero,
				cognomePasseggero, numeroBagagli );

		if( prenotazione != null ) {
			return String.valueOf( prenotazione.getNumeroBiglietto() );
		} return null;
	}

	public String recuperaPostoAssegnato( String chiaveSessione, String codiceVolo,
										  String nomePasseggero, String cognomePasseggero,
										  String numeroBagagli ) {

		Generico guest = guestMap.get( chiaveSessione );
		Prenotazione prenotazione = guest.cercaPrenotazione( codiceVolo, nomePasseggero,
				cognomePasseggero, numeroBagagli );

		if( prenotazione != null ) {
			return String.valueOf( prenotazione.getPostoAssegnato() );
		} return null;
	}

	public void modificaVolo( String codice, String statoVolo, String compagnia,
							  String aeroportoOrigine, String aeroportoDestinazione ) {

		VoloInPartenza volo = null;
		boolean status = false;

		for( Amministratore admin : adminMap.values() ) {
			if( ( volo = ( VoloInPartenza ) admin.trovaVolo( codice ) ) != null )
				status = true;
		}

		if( status ) {

			volo.setCompagnia( compagnia );
			volo.setAeroportoDestinazione( aeroportoDestinazione );
			volo.setStato( statoVolo );
		}

		ArrayList<Prenotazione> lista = volo.getPrenotazioni();

		if( statoVolo.equals( "Cancellato" ) ) {
			for( Prenotazione p : lista )
				p.setStatoPrenotazione( "Cancellata" );

		} else if( statoVolo.equals( "In ritardo" ) ) {
			for( Prenotazione p : lista )
				p.setStatoPrenotazione( "Sospesa" );
		}
	}

	public void cancellaPrenotazione( String chiaveSessione, String nomePasseggero,
									  String cognomePasseggero, String codiceVolo,
									  String numeroBagaagli ) {

		Generico guest = guestMap.get( chiaveSessione );
		Prenotazione p = guest.cercaPrenotazione( codiceVolo, nomePasseggero,
				cognomePasseggero, numeroBagaagli );

		if( p != null ) {
			guest.rimuoviPrenotazione( p );
		}
	}

	public void aggiungiVolo( String codice, String compagnia,    String numeroGate,     String partenza, String arrivo,
							  String data,   String orarioArrivo, String orarioPartenza, String username ) {

		Amministratore admin = adminMap.get( username );
		Gate gate = new Gate( numeroGate );

		Volo volo = new Volo( admin,gate );

		volo.setCodice( codice );
		volo.setCompagnia( compagnia );
		volo.setData( data );
		//volo.setAeroportoDestinazione( arrivo );
		volo.setOrarioArrivo( orarioArrivo );
		volo.setOrarioPartenza( orarioPartenza );
		volo.setStato( "Programmato" );
	}
	// Fare l'overloading di questo metodo per i voli in arrivo e in partenza
	public void aggiungiVolo( Amministratore admin, String codice, String compagnia,    String numeroGate,     String partenza, String arrivo,
							  				        String data,   String orarioArrivo, String orarioPartenza, int numeroPosti, String username, String password ) {

		Gate gate = new Gate( numeroGate );
		VoloInPartenza volo = new VoloInPartenza( admin, gate );

		volo.setCodice( codice );
		volo.setCompagnia( compagnia );
		volo.setData( data );
		volo.setAeroportoDestinazione( arrivo );
		volo.setOrarioArrivo( orarioArrivo );
		volo.setOrarioPartenza( orarioPartenza );
		volo.setNumeroPosti( numeroPosti );
		volo.setStato( "Programmato" );

		System.out.println( volo.toString() );
	}

	public void cancellaVolo( String codiceVolo ) {

		for( Amministratore admin : adminMap.values() ) {
			if( admin.trovaVolo( codiceVolo ) != null )
				admin.rimuoviVolo( admin.trovaVolo( codiceVolo ) );
		}
	}

	public Amministratore aggiungiAmministratore( String username, String password ) {
		AmministratoreImplementDao admin = new AmministratoreImplementDao();

		try {
			Amministratore amm = new Amministratore( username, password );
			if( admin.add( amm ) ) {
				adminMap.put( amm.getUsername(), amm ); return amm;
			} else {
				return null;
			}
		} catch ( SQLException e ) {
			e.printStackTrace();
			return null;
		}
	}

//	public String[] visualizzaPrenotazioni( String chiaveSessione ) {
//		Generico generico  = guestMap.get( chiaveSessione );
//		return generico.getPrenotazioni();
//	}

	public String[] recuperaAmministratori() {

		AmministratoreImplementDao admin = new AmministratoreImplementDao();
		try {

			ArrayList<Amministratore> list = admin.getAll();
			String[] array = new String[ list.size() ];

			for( int i = 0; i < list.size(); i++ )
				array[ i ] = list.get( i ).getUsername();
			return array;

		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	public String[][] recuperaVoliAmministratore( String chiaveSessione ) {

		Amministratore admin = adminMap.get( chiaveSessione );

		int numeroVoli = admin.contaVoliGestiti();
		String[][] voli = new String[ numeroVoli ][ 7 ];

		ArrayList<Volo> list = admin.getVoliGestiti();

		for( int i = 0; i < voli.length; i++ ) {
			voli[ i ][ 0 ] = list.get( i ).getCodice();
			voli[ i ][ 1 ] = list.get( i ).getCompagniaAerea();
			voli[ i ][ 2 ] = list.get( i ).getOrarioArrivo();
			voli[ i ][ 3 ] = list.get( i ).getOrarioPartenza();
			voli[ i ][ 4 ] = list.get( i ).getStato();
			voli[ i ][ 5 ] = list.get( i ).getGate();
			voli[ i ][ 6 ] = list.get( i ).getData();
		}

		return voli;
	}

	public String[][] recuperaPrenotazioniGenerico( String chiaveSessione ) {

		Generico guest = guestMap.get( chiaveSessione );
		int numeroPrenotazioni = guest.contaPrenotazioni();

		String[][] prenotazioni = new String[ numeroPrenotazioni ][ 7 ];
		ArrayList<Prenotazione> list = guest.getPrenotazioni();

		for( int i = 0; i < prenotazioni.length; i++ ) {
			prenotazioni[ i ][ 0 ] = list.get( i ).getNomePasseggero();
			prenotazioni[ i ][ 1 ] = list.get( i ).getCognomePasseggero();
			prenotazioni[ i ][ 2 ] = list.get( i ).getNumeroBagagli();
			prenotazioni[ i ][ 3 ] = list.get( i ).getVoloAssociato().getCodice();
			prenotazioni[ i ][ 4 ] = list.get( i ).getStato();
			prenotazioni[ i ][ 5 ] = list.get( i ).getNumeroBiglietto();
			prenotazioni[ i ][ 6 ] = list.get( i ).getPostoAssegnato();
		}

		return prenotazioni;
	}
}
