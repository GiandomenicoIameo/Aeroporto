import gui.Login;
import model.*;
import controller.*;
import database.ConnessioneDatabase;
import java.sql.*;
import dao.*;
import implement.postgresql.*;
import java.util.ArrayList;
import controller.Controller;

public class Main {
	public static void main( String[] args ) {

		Login login = new Login();
		login.setLocationRelativeTo(null);
		login.setVisible( true );
	}
}
