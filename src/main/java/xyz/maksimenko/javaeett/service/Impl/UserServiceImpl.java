package xyz.maksimenko.javaeett.service.Impl;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import xyz.maksimenko.DAO.UserDAO;
import xyz.maksimenko.javaeett.User;
import xyz.maksimenko.javaeett.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserDAO userDAO;

	@Override
	@Transactional 
	public void changePassword(String newPassword, String login) {
		User user = userDAO.getUserByLogin(login);
		if(user != null){
			user.setPasswordHash(newPassword);
			userDAO.updateUser(user);
		}
	}

	@Override
	@Transactional 
	public void registerUser(User user) throws Exception {
		if(userDAO.getUserByLogin(user.getLogin()) != null){
			throw new Exception("User already registered");
		} 
		userDAO.addUser(user);
	}

}
