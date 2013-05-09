package model.operations;

public class Admin {

	public static final String password = "password";
	
	public static boolean isAdmin(String string){
		return string.equals("admin");
	}
	
	public static boolean authenticate(String string){
		return password.equals(string);
	}
}
