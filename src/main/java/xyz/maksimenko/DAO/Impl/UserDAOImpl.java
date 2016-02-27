package xyz.maksimenko.DAO.Impl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import xyz.maksimenko.DAO.UserDAO;
import xyz.maksimenko.javaeett.User;

@Repository
public class UserDAOImpl implements UserDAO{
	
	@Autowired
	private SessionFactory sessionFactory;
	public User getUserByLogin(String login){
		List<User> loginList =  sessionFactory.getCurrentSession().createQuery("from User where login = :login").setString("login", login).list();
		if(loginList.size() > 0){
			return loginList.get(0);
		}
		return null;
	}

	@Override
	public void addUser(User user) {
		sessionFactory.getCurrentSession().save(user);		
	}

	@Override
	public User getUser(Long userId) {
		try{
			return (User) sessionFactory.getCurrentSession().createQuery("from User where userid = :userId").setLong("userId", userId).list().get(0);
		} catch (NullPointerException e){
			return null;
		}
	}

	@Override
	public void updateUser(User user) {
		sessionFactory.getCurrentSession().update(user);
		
	}

	@Override
	public void removeUser(Long userId) {
		User user = (User) sessionFactory.getCurrentSession().load(
				User.class, userId);
		if (null != user) {
			sessionFactory.getCurrentSession().delete(user);
		}
	}

	@Override
	public List<User> listUsers() {
		return sessionFactory.getCurrentSession().createQuery("from User").list();
	}

}
