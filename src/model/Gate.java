package model;
import java.util.ArrayList;

public class Gate {

	private ArrayList<Volo> voli = new ArrayList<>();
	private String numeroGate;

	public Gate( String numero ) {
		numeroGate = numero;
	}

	public boolean aggiungiVolo( VoloInPartenza volo ) {

		for( Volo element : voli ) {
			if (!element.getData().equals(volo.getData())) {
				continue;
			}

			if ( element instanceof VoloInPartenza &&
					element.getOrarioPartenza().equals(
						volo.getOrarioPartenza()) ||
				element instanceof VoloInArrivo &&
					element.getOrarioArrivo().equals(
						volo.getOrarioPartenza()))
				return false;

		}
		voli.add( volo );
		return true;
	}

	public boolean aggiungiVolo( VoloInArrivo volo ) {

		for( Volo element : voli ) {
			if ( !element.getData().equals(volo.getData() ) ) {
				continue;
			}

			if ( element instanceof VoloInPartenza &&
					element.getOrarioPartenza().equals(
							volo.getOrarioArrivo()) ||
				 element instanceof VoloInArrivo &&
					element.getOrarioArrivo().equals(
							volo.getOrarioArrivo()))
				return false;
		}

		voli.add( volo );
		return true;
	}

	public void rimuoviVolo( Volo volo ) {
		if( voli.contains( volo ) )
			voli.remove( volo );
	}

	public void setNumeroGate( String numero ) {
		numeroGate = numero;
	}

	public String getNumeroGate() {
		return numeroGate;
	}
}
