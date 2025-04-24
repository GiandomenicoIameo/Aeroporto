package model.utenti;

protected class Utente {

    private String username;
    private String password;

    protected Utente( String username, String password ) {
        this.username = username;
        this.password = username;
    }

    protected void modificaUsername( String username ) {
        // definire dei controlli sul valore in input.
        this.username = username;
    }

     protected void modificaPassword( String password ) {
        // definire dei controlli sul valore in input.
        this.password = password;
    }

    protected String mostraUsername() {
        return username;
    }
}
