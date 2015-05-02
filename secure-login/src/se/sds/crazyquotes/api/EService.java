package se.sds.crazyquotes.api;

import se.sds.crazyquotes.model.User;
import se.sds.crazyquotes.repository.MySQLUserRepo;

public final class EService {

	private MySQLUserRepo userRepository;
	
	public EService() {
		userRepository = new MySQLUserRepo();
	}

	public void createUser(User user) {
		userRepository.createUser(user);
	}

	public boolean validateUser(User user) {
		return userRepository.validateUser(user);		
	}
	
}
