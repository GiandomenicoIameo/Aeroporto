package model;
import java.util.ArrayList;

public class Gate {

	private ArrayList<Volo> voli;
	private String numeroGate;

	public Gate( String numero ) {
		voli = new ArrayList<Volo>();
		numeroGate = numero;
	}

	public void aggiungiVolo( Volo volo ) {
		voli.add( volo );
	}

	public void setNumeroGate( String numero ) {
		numeroGate = numero;
	}

	public String getNumeroGate() {
		return numeroGate;
	}
}
