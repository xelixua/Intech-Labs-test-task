package xyz.maksimenko.javaeett.service.Impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import xyz.maksimenko.DAO.AddressBookDAO;
import xyz.maksimenko.javaeett.AddressBookItem;
import xyz.maksimenko.javaeett.User;
import xyz.maksimenko.javaeett.service.AddressBookService;

@Service
public class AddressBookServiceImpl implements AddressBookService {
	
	@Autowired
	private AddressBookDAO addressBookDAO;

	@Transactional
	public void addItem(AddressBookItem item) {
		addressBookDAO.addItem(item);

	}

	@Transactional
	public void removeItem(Long itemId) {
		addressBookDAO.removeItem(itemId);
	}

	@Transactional
	public List<AddressBookItem> listItemsForUser(String login) {
		return addressBookDAO.listItemsForUser(login);
	}

	@Transactional
	public List<AddressBookItem> listItem() {
		return addressBookDAO.listItems();
	}
}
