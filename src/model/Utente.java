package model;

public abstract class Utente {

    protected String username;
    protected String password;

     protected Utente( String username, String password ) {
         this.username = username;
         this.password = password;
     }

    protected void setUsername( String username ) {
        this.username = username;
    }

    protected void setPassword( String password ) {
        this.password = password;
    }

    protected String getUsername() {
        return username;
    }

    protected String getPassword() {
        return password;
    }
}
