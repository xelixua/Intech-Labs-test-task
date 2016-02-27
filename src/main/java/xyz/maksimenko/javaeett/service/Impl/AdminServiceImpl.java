package xyz.maksimenko.javaeett.service.Impl;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import xyz.maksimenko.DAO.UserDAO;
import xyz.maksimenko.DAO.UserRoleDAO;
import xyz.maksimenko.javaeett.User;
import xyz.maksimenko.javaeett.UserRole;
import xyz.maksimenko.javaeett.service.AdminService;

@Service
public class AdminServiceImpl implements AdminService {
	
	private final String ADMIN = "ROLE_ADMIN";
	private final String USER = "ROLE_USER";
	
	@Autowired
	private UserDAO userDAO;
	@Autowired
	private UserRoleDAO userRoleDAO;

	@Transactional
	public List<User> listUsers() {
		return userDAO.listUsers();
	}

	@Transactional
	public void addUser(User user) throws Exception {
		if(userDAO.getUserByLogin(user.getLogin()) == null){
			UserRole userRole = new UserRole();
			userRole.setLogin(user.getLogin());
			userRole.setRole(USER);
			Set<UserRole> userRoles = user.getUserRoles();
			userRoles.add(userRole);
			user.setUserRoles(userRoles);
			userDAO.addUser(user);
			return;
		}
		throw new Exception("User already exists");

	}

	@Transactional
	public void removeUser(Long userId) {
		userDAO.removeUser(userId);
	}

	@Transactional
	public void addAdminRights(Long userId) {
		User user = userDAO.getUser(userId);
		Set<UserRole> roles = (Set<UserRole>) user.getUserRoles();
		Iterator<UserRole> it = roles.iterator();
		
		//check if user already has admin role
		while(it.hasNext()){
			if(it.next().getRole().equals(ADMIN)){
				return;
			}
		}
		UserRole adminRole = new UserRole();
		adminRole.setRole(ADMIN);
		adminRole.setLogin(user.getLogin());
		userRoleDAO.addRole(adminRole);
	}

	@Transactional
	public void removeAdminRights(Long userId) {
		User user = userDAO.getUser(userId);
		Set<UserRole> roles = (Set<UserRole>) user.getUserRoles();
		Iterator<UserRole> it = roles.iterator();
		while(it.hasNext()){
			UserRole role = it.next();
			if(role.getRole().equals(ADMIN)){
				userRoleDAO.removeRole(role.getRoleid());
				return;
			}
		}
	}
}
