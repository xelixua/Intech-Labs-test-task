package xyz.maksimenko.DAO;

import java.util.List;

import xyz.maksimenko.javaeett.User;

public interface UserDAO {
	public void addUser(User user);
	public User getUser(Long userId);
	public void updateUser(User user);
	public void removeUser(Long userId);
	public List<User> listUsers();
	public User getUserByLogin(String login);
}
