package xyz.maksimenko.DAO;

import java.util.List;

import xyz.maksimenko.javaeett.User;
import xyz.maksimenko.javaeett.UserRole;

public interface UserRoleDAO {
	public void addRole(UserRole role);
	public List<UserRole> getRolesForUser(Long userId);
	public void removeRole(Long roleId);
	
}
