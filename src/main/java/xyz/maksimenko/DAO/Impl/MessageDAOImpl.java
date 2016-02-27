package xyz.maksimenko.DAO.Impl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import xyz.maksimenko.DAO.MessageDAO;
import xyz.maksimenko.javaeett.Message;
import xyz.maksimenko.javaeett.User;

@Repository
public class MessageDAOImpl implements MessageDAO {

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public void addMessage(Message message){
		sessionFactory.getCurrentSession().save(message);
	}

	@Override
	public Message getMessage(Long messageId){
		return (Message) sessionFactory.getCurrentSession().createQuery("from Message where messageid = :messageId").setLong("messageId", messageId).list().get(0);


	}

	@Override
	public void removeMessage(Long messageId) {
		Message message = (Message) sessionFactory.getCurrentSession().load(
				Message.class, messageId);
		if (null != message) {
			sessionFactory.getCurrentSession().delete(message);
		}

	}

	@Override
	public List<Message> getMessagesForUser(String username) {
		return sessionFactory.getCurrentSession().createQuery("from Message where touserlogin = :username").setString("username", username).list();
	}


}
