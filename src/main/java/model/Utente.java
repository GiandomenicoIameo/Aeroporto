package model;

class Utente {

    protected String username;
    protected String password;
    protected String nome;

    // public Utente( String username, String password ) {
    //     this.username = username;
    //     this.password = password;
    // }

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

    protected String mostraPassword() {
        return password;
    }
}
