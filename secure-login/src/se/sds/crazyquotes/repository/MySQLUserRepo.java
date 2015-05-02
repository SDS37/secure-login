package se.sds.crazyquotes.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.Scanner;
import java.sql.DriverManager;

import se.sds.crazyquotes.model.User;
import se.sds.crazyquotes.repository.UserRepository;
import se.sds.crazyquotes.security.PasswordHash;

public class MySQLUserRepo implements UserRepository {
	
	PasswordHash passwordHash = new PasswordHash();

	Scanner input = new Scanner(System.in);
	Collection<User> userCollection = new LinkedHashSet<User>();

	Connection connection = null;
	PreparedStatement preparedstatement = null;
	ResultSet resultset = null;
	
	User user;

	@SuppressWarnings("static-access")
	@Override
	public void createUser(User user) {
		try {
			connectingDB();
			preparedstatement = connection.prepareStatement(""
					+ "INSERT INTO users (email,password) VALUES(?,?)");
			preparedstatement.setString(1, user.getEmail());
			preparedstatement.setString(2, passwordHash.createHash(user.getPassword()));
			preparedstatement.executeUpdate();
			
			System.out.println("\nNew user created: " + user.getEmail() + "\n");
			
		} catch (Exception e) {
			System.out.println("Error: " + e.getMessage());

		} finally {
			tryCatch();
		}
	}

	@Override
	public boolean validateUser(User user) {
		
		boolean validate = false;
		
		try {
			connectingDB();
			preparedstatement = connection.prepareStatement(""
					+ "SELECT * FROM users where email = ?");
			preparedstatement.setString(1, user.getEmail());
			resultset = preparedstatement.executeQuery();
			
			resultset.next();
			
			String hashedPassword = resultset.getString("password");
			
			System.out.println("\nInputted password:");
			System.out.println(user.getPassword());
			
			System.out.println("Retrived hashed password:");
			System.out.println(hashedPassword);
			
			System.out.println("Are they equal?");
			System.out.println(PasswordHash.validatePassword(user.getPassword(), hashedPassword));
			
			validate = PasswordHash.validatePassword(user.getPassword(), hashedPassword);
			
		} catch (Exception e) {
			System.out.println("Error: " + e.getMessage());
			validate = false;
		} finally {
			tryCatch();
		}		
		return validate;
	}
	
	public void connectingDB() throws SQLException {
//		GRANT ALL ON crazyquotes.* TO 'crazy_quotes'@'localhost';
		connection = DriverManager.getConnection("jdbc:mysql://localhost/crazyquotes", "crazy_quotes", "crazyquotes");
	}
	
	public void tryCatch() {
		try {
			if (resultset != null) {
				resultset.close();
			}
			if (preparedstatement != null) {
				preparedstatement.close();
			}
			if (connection != null) {
				connection.close();
			}
		} catch (Exception e) {
			System.out.println("Error: " + e.getMessage());
		}
	}
}
