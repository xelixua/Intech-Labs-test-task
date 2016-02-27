package xyz.maksimenko.DAO.Impl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import xyz.maksimenko.DAO.UserRoleDAO;
import xyz.maksimenko.javaeett.UserRole;

@Repository
public class UserRoleDAOImpl implements UserRoleDAO{
	
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public void addRole(UserRole role) {
		sessionFactory.getCurrentSession().save(role);
		
	}

	@Override
	public List<UserRole> getRolesForUser(Long userId) {
		return sessionFactory.getCurrentSession().createQuery("from UserRole where userid = :userId").setLong("userId", userId).list();
	}

	@Override
	public void removeRole(Long roleId) {
		UserRole role = (UserRole) sessionFactory.getCurrentSession().load(
				UserRole.class, roleId);
		if (null != role) {
			sessionFactory.getCurrentSession().delete(role);
		}	
	}
}
