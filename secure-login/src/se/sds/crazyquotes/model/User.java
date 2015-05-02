package se.sds.crazyquotes.model;

public class User {
	
	private final int userId;
	private final String email;
	private final String password;
	
	public User(int userId, String email, String password) {
		this.userId = userId;
		this.email = email;
		this.password = password;
	}

	public int getUserId() {
		return userId;
	}
	
	public String getEmail() {
		return email;
	}
	
	public String getPassword() {
		return password;
	}
	
}
