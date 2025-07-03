package model;

public class Utente {

    protected String username;
    protected String password;
    protected boolean utenteAutenticato;
    // public Utente( String username, String password ) {
    //     this.username = username;
    //     this.password = password;
    // }

    protected void setUsername( String username ) {
        // definire dei controlli sul valore in input.
        this.username = username;
    }

    protected void setPassword( String password ) {
        // definire dei controlli sul valore in input.
        this.password = password;
    }

    protected void setUtenteAutenticato( boolean status ) {
        utenteAutenticato = status;
    }

    protected boolean isAutenticato() {
        return utenteAutenticato;
    }

    protected String getUsername() {
        return username;
    }

    protected String getPassword() {
        return password;
    }
}
