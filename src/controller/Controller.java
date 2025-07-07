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

	private Map<String, Generico> guestMap;
	private Map<String, Amministratore> adminMap;
	private Gate[] gateArray;

	public static Controller getInstance() {
		if( instance == null )
			instance = new Controller();
		return instance;
	}

	public Controller() {
		guestMap   = new HashMap<>();
		adminMap   = new HashMap<>();
		gateArray  = new Gate[ 8 ];
	}

	/**
	 * Il metodo login permette a un utente di accedere alla propria homepaage
	 * oppure essere respinto.
	 *
	 * Il metodo verifica se le credenziali inserite corrispondono alle credenziali di accesso
	 * dell'utente root, di un amministratore o di un utente generico. Il controllo viene
	 * dapprima effettuato negli array associativi; in caso di fallimento viene effettuato nella
	 * base di dati.
	 *
	 * @param username
	 * @param password
	 * @return corrisponde a accesso consentito per l'utente root = 2,
	 * accesso consentito all'amministratore = 1, accesso consentito all'utente generico = 0 e
	 * accesso negato = 3.
	 */

	public int login( String username, String password ) {

		final int ACCESSO_GENERICO       = 0;
		final int ACCESSO_AMMINISTRATORE = 1;
		final int ACCESSO_NEGATO         = 2;

		Generico guest = guestMap.get( username );

		if( guest != null ) {
			if (password.equals( guest.getPassword() ) ) return ACCESSO_GENERICO;
			return ACCESSO_NEGATO;
		}

		try {
			Generico guest1 = new GenericoImplementDao().get( username );

			if( guest1 != null ) {
				if (password.equals(guest1.getPassword())) {
					guestMap.put( username, guest1 );
					return ACCESSO_GENERICO;
				} else {
					return ACCESSO_NEGATO;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return ACCESSO_NEGATO;
		}

		Amministratore admin = adminMap.get( username );

		if( admin != null ) {
			if ( password.equals( admin.getPassword() ) ) return ACCESSO_AMMINISTRATORE;
			return ACCESSO_NEGATO;
		}

		try {
			Amministratore admin1 = new AmministratoreImplementDao().get( username );
			if( admin1 != null ) {
				if ( password.equals(admin1.getPassword() ) ) {
					adminMap.put( username, admin1 ); return ACCESSO_AMMINISTRATORE;
				} else {
					return ACCESSO_NEGATO;
				}
			}
			return ACCESSO_NEGATO;
		} catch ( SQLException e ) {
			e.printStackTrace();
			return ACCESSO_NEGATO;
		}
	}

	/**
	 * La prenotazione può essere aggiunta da parte di un utente generico
	 * solo se essa risulta programmata. In caso contrario viene restituito
	 * un codice di errore.
	 *
	 *
	 * @param chiaveSessione
	 * @param codice
	 * @param nome
	 * @param cognome
	 * @param bagagli
	 * @return corrispondono a volo inesistente = 0,
	 * 	prenotazione già esistente = 1, volo non programmato = 2,
	 * 	posti non disponibili nell'aereo = 3, prenotazione aggiunta con
	 * 	successo = 4.
	 */
	public int aggiungiPrenotazione( String chiaveSessione, String codice,
									 String nome, String cognome, String bagagli ) {

		final int SUCCESSO 				   = 0;
		final int DUPLICE_PRENOTAZIONE     = 1;
		final int NESSUN_POSTO_DISPONIBILE = 2;
		final int VOLO_INESISTENTE 		   = 3;
		final int VOLO_NON_PROGRAMMATO     = 4;

		// Viene determinato un utente generico in base alla chiaveSessione. Essa corrisponde
		// allo username dell'utente attualmente connesso.
		Generico generico = guestMap.get( chiaveSessione );
		VoloInPartenza volo = recuperaVolo( codice );

		if( volo != null ) {

			if( !volo.getStato().equals( "PROGRAMMATO" ) )
				return VOLO_NON_PROGRAMMATO;

			Prenotazione p = costruisciPrenotazione( volo, generico,
					nome, cognome, bagagli );
			// verifica se esistono posti disponibili all'interno dell'aereo.
			if ( p != null ) {
				if ( !generico.esistePrenotazione( p ) ) {
					// La prenotazione non è duplicata
					generico.aggiungiPrenotazione( p );
					return SUCCESSO;
				} else {
					// Esiste una prenotazione equivalente.
					p.rimuoviVolo();
					return DUPLICE_PRENOTAZIONE;
				}
			}
			return NESSUN_POSTO_DISPONIBILE;
		}
		return VOLO_INESISTENTE;
	}

	/**
	 * Il metodo consente di recuperare un partendo dal suo codice.
	 * I Voli vengono identificati mediante il codice del volo, quindi
	 * non possono esistere due voli con lo stesso codice. Se il codice
	 * esiste allora viene recuperato uno ed un solo volo.
	 *
	 * @param codice
	 * @return corrisponde al codice del volo di partenza
	 */

	private VoloInPartenza recuperaVolo( String codice ) {

		Volo volo = null;
		boolean status = false;

		for( Amministratore admin : adminMap.values() ) {
			volo = admin.trovaVolo( codice );
			if( volo != null ) {
				status = true; break;
			}
		}

		if( status && volo instanceof VoloInPartenza ) {
			return ( VoloInPartenza ) volo;
		}
		return null;
	}

	/**
	 * Il metodo ha il compito di costruire una prenotazione ed è
	 * strettamente collegato al metodo aggiungiPrenotazione().
	 * Come prima cosa viene contrallo se esistono posti disponibili all'
	 * interno dell'aereo. In caso affermativo viene assegnato il posto
	 * al passeggero, il numero di biglietto, la prenotazione
	 * viene marcata come Sospesa e viene aggiunta al volo specificato;
	 * in caso negativo viene restituito un messaggio di errore.
	 *
	 * Una volta che la prenotazione è stata aggiunta all'insieme delle
	 * prenotazioni associate a quel volo, non ci resta che capire se il
	 * numero di posti minimo è stato superato. In caso affermativo, ogni
	 * prenotazione associata a quel volo viene marcata come Confermata.
	 *
	 *
	 * @param volo
	 * @param nome
	 * @param cognome
	 * @param bagagli
	 * @return
	 */
	private Prenotazione costruisciPrenotazione( VoloInPartenza volo, Generico generico,
										 String nome, String cognome, String bagagli ) {

		int numeroPosto = volo.occupaPosto();
		String numeroBiglietto = volo.getCodice() + String.valueOf( numeroPosto );

		// Esistono posti disponibili all'interno dell'aereo
		if ( numeroPosto >= 0 ) {

			Prenotazione p = new Prenotazione( volo, generico, nome, cognome, numeroBiglietto,
					String.valueOf( numeroPosto ), bagagli, "SOSPESA" );
			volo.addPrenotazione( p );

			if ( volo.numeroMinimoPostiSuperato() ) {
				System.out.println( "eccomi" );
				for ( Prenotazione s : volo.getPrenotazioni() ) {
					s.setStatoPrenotazione( "CONFERMATA" );
				}
			}

			return p;
		}
		return null;
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

	public String recuperaStatoPrenotazione(  String chiaveSessione, String codiceVolo,
											  String nomePasseggero, String cognomePasseggero,
											  String numeroBagagli ) {
		Generico guest = guestMap.get( chiaveSessione );
		Prenotazione prenotazione = guest.cercaPrenotazione( codiceVolo, nomePasseggero,
				cognomePasseggero, numeroBagagli );

		if( prenotazione != null ) {
			return prenotazione.getStato();
		} return null;
	}

	public int modificaVolo( String chiaveSessione, String codice, String statoVolo, String compagnia,
							     String aeroportoOrigine, String aeroportoDestinazione, String gate ) {

		final int SUCCESSO         = 0;
		final int ORARI_NON_VALIDI = 1;
		final int VOLO_INESISTENTE = 2;

		Amministratore admin = adminMap.get( chiaveSessione );
		Volo volo = null;

		for( Volo element : admin.getVoliGestiti() ) {
			if( codice.equals( element.getCodice() ) )
				volo = element;
		}

		if( volo != null ) {

			/* Inizio sezione controlli */

			if( !checkOrario( volo,gate ) )
				return ORARI_NON_VALIDI;

			if( volo instanceof VoloInPartenza )
				( ( VoloInPartenza ) volo ).setAeroportoDestinazione(
						aeroportoDestinazione );
			else if( volo instanceof  VoloInArrivo )
				( ( VoloInArrivo ) volo ).setAeroportoOrigine(
						aeroportoOrigine );

			volo.setCompagnia( compagnia );
			volo.setStato( statoVolo );
			volo.setGate( gate );

			/* Fine sezione controlli */

			propagaStatoPrenotazione( volo, statoVolo );
			return SUCCESSO;
		}
		return VOLO_INESISTENTE;
	}

	/**
	 * Il seguente metodo consente di propagare lo stato di una
	 * prenotazione ad ogni prenotazione, associata a un determinato volo,
	 * in base allo stato del volo stesso. Se un volo viene
	 * marcato come "Cancellato", allora ogni prenotazione associata a quel volo
	 * viene anch'essa contrassegnata come "Cancellata". Mentre se lo stato del
	 * volo risulta "In ritardo" allora ogni sua prenotazione viene marcata come
	 * "Sospesa"
	 *
	 * @param volo
	 * @param statoVolo
	 */
	private void propagaStatoPrenotazione( Volo volo, String statoVolo ) {

		ArrayList<Prenotazione> lista = volo.getPrenotazioni();

		if( statoVolo.equals( "CANCELLATO" ) ) {
			for( Prenotazione p : lista ) {
				p.setStatoPrenotazione("CANCELLATA");
			}

		} else if( statoVolo.equals( "IN_RITARDO" ) ) {
			for( Prenotazione p : lista )
				p.setStatoPrenotazione( "SOSPESA" );
		}
	}

	private boolean voloDuplicato( Volo volo ) {

		for( Amministratore amm : adminMap.values() ) {
			if( amm.checkVolo( volo ) ) return true;
		}
		return false;
	}

	private boolean checkOrario( Volo volo, String numeroGate ) {

		int numero = Integer.parseInt( numeroGate );

		if( gateArray[ numero ] == null ) {
			gateArray[ numero ] = new Gate( numeroGate );
			return true;
		} else {
			if( volo.getGate().equals( numeroGate ) )
				return true;
		}

		if( volo instanceof VoloInArrivo ) {
			if( !gateArray[ numero ].aggiungiVolo(
					( VoloInArrivo ) volo ) )
				return false;
		} else {
			if( !gateArray[ numero ].aggiungiVolo(
					( VoloInPartenza ) volo ) )
				return false;
		}
		return true;
	}

	public void cancellaPrenotazione( String chiaveSessione, String nomePasseggero,
									  String cognomePasseggero, String codiceVolo,
									  String numeroBagaagli ) {

		Generico guest = guestMap.get( chiaveSessione );
		Prenotazione p = guest.cercaPrenotazione( codiceVolo, nomePasseggero,
				cognomePasseggero, numeroBagaagli );

		int posto = Integer.parseInt( p.getPostoAssegnato() );
		Volo volo = p.getVoloAssociato();

		if( volo instanceof VoloInPartenza ) {
			( ( VoloInPartenza ) volo).riassegnaPosto( posto );
		}

		if( p != null ) {
			guest.rimuoviPrenotazione( p );

		}
	}

	public int addVolo( String chiaveSessione, String codice, String compagnia,
						 String numeroGate, String partenza, String arrivo,
						 String data, String orarioArrivoOra, String orarioArrivoMinuti,
						 String orarioPartenzaOra, String orarioPartenzaMinuti,
						 int numeroPosti, String tipoVolo ) {

		final int SUCCESSO         = 0;
		final int VOLO_ESISTENTE   = 1;
		final int ORARI_NON_VALIDI = 2;

		Amministratore admin = adminMap.get( chiaveSessione );
		Volo volo = null;

		if( tipoVolo.equals( "Partenza" ) ) {
			volo = add( admin, codice, compagnia, numeroGate,
					arrivo, data, orarioPartenzaOra, orarioPartenzaMinuti,
					orarioArrivoOra, orarioArrivoMinuti, numeroPosti );
		} else {
			volo = add( admin, codice, compagnia, numeroGate,
					partenza, data,  orarioPartenzaOra, orarioPartenzaMinuti,
					orarioArrivoOra, orarioArrivoMinuti );
		}

		if( volo == null ) {
			return ORARI_NON_VALIDI;
		} else {

			for( Amministratore amm : adminMap.values() ) {
				if( amm.checkVolo( volo ) ) return VOLO_ESISTENTE;
			}

			admin.inserisciVolo( volo );
			return SUCCESSO;
		}
	}

	// Metodo per l'arrivo
	public Volo add( Amministratore admin, String codice, String compagnia,
					 String numeroGate, String partenza, String data,
					 String orarioPartenzaOra, String orarioPartenzaMinuti,
					 String orarioArrivoOra, String orarioArrivoMinuti ) {

		int numero = Integer.parseInt( numeroGate );

		if( gateArray[ numero ] == null ) {
			gateArray[ numero ] = new Gate( numeroGate );
		}

		Orario orarioPartenza = new Orario( Integer.valueOf( orarioPartenzaOra ),
				Integer.valueOf( orarioPartenzaMinuti ) );
		Orario orarioArrivo   = new Orario( Integer.valueOf( orarioArrivoOra ),
				Integer.valueOf( orarioArrivoMinuti ) );

		VoloInArrivo volo = new VoloInArrivo( admin, codice, compagnia, data, orarioPartenza,
				orarioArrivo, "PROGRAMMATO", gateArray[ numero ], partenza );

		if( !gateArray[ numero ].aggiungiVolo( volo ) ) {
			volo = null; return null;
		}
		return volo;
	}

	// Metodo per la partenza
	public Volo add( Amministratore admin, String codice, String compagnia,
					 String numeroGate, String arrivo, String data,
					 String orarioPartenzaOra, String orarioPartenzaMinuti,
					 String orarioArrivoOra, String orarioArrivoMinuti,
					 int numeroPosti ) {

		int numero = Integer.parseInt( numeroGate );

		if( gateArray[ numero ] == null ) {
			gateArray[ numero ] = new Gate( numeroGate );
		}

		Orario orarioPartenza = new Orario( Integer.valueOf( orarioPartenzaOra ),
				Integer.valueOf( orarioPartenzaMinuti ) );
		Orario orarioArrivo   = new Orario( Integer.valueOf( orarioArrivoOra ),
				Integer.valueOf( orarioArrivoMinuti ) );

		VoloInPartenza volo = new VoloInPartenza( admin, codice,
				compagnia, data, orarioPartenza, orarioArrivo, "Programmato",
				gateArray[ numero ], arrivo, numeroPosti );

		if( !gateArray[ numero ].aggiungiVolo( volo ) ) {
			volo = null; return null;
		}
		return volo;
	}

	public void cancellaVolo( String codiceVolo ) {

		int numero;
		Volo volo = null;

		for( Amministratore admin : adminMap.values() ) {
			volo = admin.trovaVolo( codiceVolo );
			if( volo != null ) {
				numero = Integer.valueOf( volo.getGate() );
				gateArray[ numero ] = null;

				volo.rimuoviGate();
				admin.rimuoviVolo( admin.trovaVolo( codiceVolo ) );
			}
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

	public String[][] recuperaAmministratori() {

			String[][] matrice = new String[ adminMap.size() ][ 2 ];

			int i = 0;

			for( Map.Entry<String, Amministratore> entry : adminMap.entrySet() ) {
				matrice[ i ][ 0 ] = entry.getKey();
				matrice[ i ][ 1 ] = Integer.toString( entry.getValue().contaVoliGestiti() );

				i++;
			}
			return matrice;
	}

	public String[][] recuperaVoliAmministratore( String chiaveSessione ) {

		Amministratore admin = adminMap.get( chiaveSessione );

		int numeroVoli = admin.contaVoliGestiti();
		String[][] voli = new String[ numeroVoli ][ 9 ];

		ArrayList<Volo> list = admin.getVoliGestiti();

		for( int i = 0; i < voli.length; i++ ) {
			voli[ i ][ 0 ] = list.get( i ).getCodice();
			voli[ i ][ 1 ] = list.get( i ).getCompagniaAerea();

			String orarioArrivo =  list.get( i ).getOrarioArrivo().getOra() +
					"." + list.get( i ).getOrarioArrivo().getMinuti();
			voli[ i ][ 2 ] = orarioArrivo;

			String orarioPartenza =  list.get( i ).getOrarioPartenza().getOra() +
					"." + list.get( i ).getOrarioPartenza().getMinuti();

			voli[ i ][ 3 ] = orarioPartenza;
			voli[ i ][ 4 ] = list.get( i ).getStato();
			voli[ i ][ 5 ] = list.get( i ).getGate();
			voli[ i ][ 6 ] = list.get( i ).getData();

			if( list.get( i ) instanceof VoloInPartenza ) {
				voli[ i ][ 7 ] = "Napoli";
				voli[ i ][ 8 ] = ( ( VoloInPartenza )
						list.get( i ) ).getAeroportoDestinazione();
			} else if( list.get( i ) instanceof VoloInArrivo ) {
				voli[ i ][ 7 ] = ( ( VoloInArrivo )
						list.get( i ) ).getAeroportoOrigine();
				voli[ i ][ 8 ] = "Napoli";
			}
		}

		return voli;
	}

	public String[][] recuperaPrenotazioniGenerico( String chiaveSessione ) {

		Generico guest = guestMap.get( chiaveSessione );
		int numeroPrenotazioni = guest.contaPrenotazioni();

		String[][] prenotazioni = new String[ numeroPrenotazioni ][ 9 ];
		ArrayList<Prenotazione> list = guest.getPrenotazioni();

		for( int i = 0; i < prenotazioni.length; i++ ) {
			prenotazioni[ i ][ 0 ] = list.get( i ).getNomePasseggero();
			prenotazioni[ i ][ 1 ] = list.get( i ).getCognomePasseggero();
			prenotazioni[ i ][ 2 ] = list.get( i ).getNumeroBagagli();
			prenotazioni[ i ][ 3 ] = list.get( i ).getVoloAssociato().getCodice();
			prenotazioni[ i ][ 4 ] = list.get( i ).getStato();
			prenotazioni[ i ][ 5 ] = list.get( i ).getNumeroBiglietto();
			prenotazioni[ i ][ 6 ] = list.get( i ).getPostoAssegnato();

			if( list.get( i ).getVoloAssociato() instanceof VoloInPartenza ) {
				prenotazioni[ i ][ 7 ] = "Napoli";
				prenotazioni[ i ][ 8 ] = ( ( VoloInPartenza )
						list.get( i ).getVoloAssociato()).getAeroportoDestinazione();
			}
			else if( list.get( i ).getVoloAssociato() instanceof VoloInArrivo ) {
				prenotazioni[ i ][ 7 ] = ( ( VoloInArrivo )
						list.get( i ).getVoloAssociato()).getAeroportoOrigine();
				prenotazioni[ i ][ 8 ] = "Napoli";
			}
		}

		return prenotazioni;
	}

	public int modificaCredenziali( String chiaveSessione, String username, String password ) {

		Amministratore vecchioAmm = adminMap.get( chiaveSessione );
		AmministratoreImplementDao admin = new AmministratoreImplementDao();

		if ( vecchioAmm != null ) {
			Amministratore nuovoAmm = new Amministratore( username,password,
					vecchioAmm.getVoliGestiti() ) ;
			try {
				admin.update( vecchioAmm,nuovoAmm );

				vecchioAmm.setUsername( username );
				vecchioAmm.setPassword( password );

				adminMap.remove( chiaveSessione );
				adminMap.put( vecchioAmm.getUsername(), vecchioAmm );

				return 0;
			} catch (SQLException e) {
				e.printStackTrace();
				return 1;
			}
		}

		Generico vecchioGenerico = guestMap.get( chiaveSessione );
		GenericoImplementDao guest = new GenericoImplementDao();

		if( vecchioGenerico != null ) {
			Generico nuovoGenerico = new Generico( username,password );

			try {
				guest.update( vecchioGenerico,nuovoGenerico );

				vecchioGenerico.setUsername( username );
				vecchioGenerico.setPassword( password );

				guestMap.remove( chiaveSessione );
				guestMap.put( vecchioGenerico.getUsername(), vecchioGenerico );

				return 0;
			} catch (SQLException e) {
				e.printStackTrace();
				return 1;
			}
		}
		return 2;
	}

	public String recuperaTipoVolo( String codice ) {

		for( Amministratore admin : adminMap.values() ) {
			for( Volo volo : admin.getVoliGestiti() ) {

				if( volo.getCodice().equals( codice ) ) {
					if (volo instanceof VoloInPartenza)
						return "Partenza";
					else
						return "Arrivo";
				}
			}
		}

		return null;
	}
}
