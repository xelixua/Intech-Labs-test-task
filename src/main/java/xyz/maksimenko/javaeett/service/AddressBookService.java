package xyz.maksimenko.javaeett.service;

import java.util.List;

import xyz.maksimenko.javaeett.AddressBookItem;

public interface AddressBookService {
	public void addItem(AddressBookItem item);
	public void removeItem(Long itemId);
	public List<AddressBookItem> listItemsForUser(String login);
}
