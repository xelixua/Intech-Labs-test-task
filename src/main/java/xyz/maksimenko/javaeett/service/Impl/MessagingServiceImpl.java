package xyz.maksimenko.javaeett.service.Impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import xyz.maksimenko.DAO.MessageDAO;
import xyz.maksimenko.javaeett.Message;
import xyz.maksimenko.javaeett.service.MessagingService;

@Service
public class MessagingServiceImpl implements MessagingService {
	
	@Autowired
	private MessageDAO messageDAO;

	@Transactional
	public void sendMessage(Message message) {
		messageDAO.addMessage(message);

	}

	@Transactional
	public void removeMessage(Long messageId) {
		messageDAO.removeMessage(messageId);

	}

	@Transactional
	public void showMessage(Long messageId) {
		messageDAO.getMessage(messageId);

	}

	@Transactional
	public List<Message> listMessagesForUser(String username) {
		return messageDAO.getMessagesForUser(username);
	}
}
