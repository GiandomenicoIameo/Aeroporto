package model;

public class Utente {

    private String rusername = "Mario";
    private String rpassword = "Caruso";

    private String username;
    private String password;

    public Utente( String username, String password ) {
        this.username = username;
        this.password = username;
    }

    public boolean login( String username, String password ) {

        if ( username == rusername && password == rpassword )
            return true;
        return false;
    }

    public void modificaUsername( String username ) {
        // definire dei controlli sul valore in input.
        this.username = username;
    }

    public void modificaPassword( String password ) {
        // definire dei controlli sul valore in input.
        this.password = password;
    }

    public String mostraUsername() {
        return username;
    }
}
