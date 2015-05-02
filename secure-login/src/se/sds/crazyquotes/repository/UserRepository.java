package se.sds.crazyquotes.repository;

import se.sds.crazyquotes.model.User;

public interface UserRepository {
	
	public void createUser(User user);
	
	public boolean validateUser(User user);

}
