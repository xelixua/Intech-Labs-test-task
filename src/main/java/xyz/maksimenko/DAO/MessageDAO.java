package xyz.maksimenko.DAO;

import java.util.List;

import xyz.maksimenko.javaeett.Message;
import xyz.maksimenko.javaeett.User;

public interface MessageDAO {
	public void addMessage(Message message);
	public Message getMessage(Long messageId);
	public List<Message> getMessagesForUser(String login);
	public void removeMessage(Long messageId);
}
