package xyz.maksimenko.javaeett.service;

import java.util.List;

import xyz.maksimenko.javaeett.Message;
import xyz.maksimenko.javaeett.User;

public interface MessagingService {
	public void sendMessage(Message message);
	public void removeMessage(Long messageId);
	public void showMessage(Long messageId);
	public List<Message> listMessagesForUser(String username);
}
