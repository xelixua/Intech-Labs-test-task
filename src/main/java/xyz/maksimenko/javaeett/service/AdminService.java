package xyz.maksimenko.javaeett.service;

import java.util.List;

import xyz.maksimenko.javaeett.User;

public interface AdminService {
	public List<User> listUsers();
	public void addUser(User user) throws Exception;
	public void removeUser(Long userId);
	public void addAdminRights(Long userId);
	public void removeAdminRights(Long userId);
	
}
