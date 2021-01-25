package ClasseJava;

import java.sql.ResultSet;

public class Login {
	private String user;
	private String password;
	private ResultSet rs;
	private BaseDeDonnees db;
	
	
	public Login (String user, String password) {
		this.user = user;
		this.password = password;
	}
	
	public boolean verification () {
		db = new BaseDeDonnees();
		rs = db.selectAll("employe", "username = '" +user+"' AND password = '" +password+ "'");
		try {
			rs.next();
			if(user.equals(rs.getString("username")) && password.equals(rs.getString("password"))) {
				return true;
			}
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return false;		
	}
}
