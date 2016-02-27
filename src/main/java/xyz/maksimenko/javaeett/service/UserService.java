package xyz.maksimenko.javaeett.service;

import xyz.maksimenko.javaeett.User;

public interface UserService {
	public void registerUser(User user) throws Exception;
	public void changePassword(String newPassword, String login);
}
