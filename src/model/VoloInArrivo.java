package model;

public class VoloInArrivo extends Volo {

	// Variabili di istanza.
	private String aeroportoOrigine;

	public VoloInArrivo( Amministratore admin, String codice,
						 String compagniaAerea, String data, Orario orarioPartenza,
						 Orario orarioArrivo, String stato, Gate gate, String aeroportoOrigine ) {

		super( admin, codice, compagniaAerea, data,
				orarioPartenza, orarioArrivo, stato, gate );
		this.aeroportoOrigine = aeroportoOrigine;
	}

	public void setAeroportoOrigine( String aeroporto ) {
		aeroportoOrigine = aeroporto;
	}

	public String getAeroportoOrigine() {
		return aeroportoOrigine;
	}


	/**
	 *
	 * Due esemplari di VoloInArrivo sono considerati equivalenti se e solo se
	 * si riferiscono allo stesso oggetto, hanno lo stesso codice oppure
	 * le date, gli orari di arrivo, di partenza, il numero di gate, la compagnia, lo
	 * 	stato e l'aeroporto di partenza coincidono.
	 *
	 * @param volo
	 * @return
	 */
	public boolean equals( Volo volo ) {

		if ( volo instanceof VoloInArrivo ) {

			if ( super.equals( volo ) ) {
				return true;
			} else {
				if( !( volo.getData().equals( data ) ) )
					return false;

				if( !( volo.getOrarioArrivo().equals( orarioArrivo ) ) )
					return false;

				if( !( volo.getOrarioPartenza().equals( orarioPartenza ) ) )
					return false;

				if( !( volo.getStato().equals( stato ) ) )
					return false;

				if( !( volo.getGate().equals( gate ) ) )
					return false;

				return ( ( VoloInArrivo ) volo ).getAeroportoOrigine().equals(
						aeroportoOrigine );
			}
		} else {
			return super.equals( volo );
		}
	}
}
