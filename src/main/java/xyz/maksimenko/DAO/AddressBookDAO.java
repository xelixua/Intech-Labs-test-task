package xyz.maksimenko.DAO;

import java.util.List;

import xyz.maksimenko.javaeett.AddressBookItem;

public interface AddressBookDAO {
	public void addItem(AddressBookItem item);
	public void removeItem(Long itemId);
	public List<AddressBookItem> listItemsForUser(String userlogin);
	public List<AddressBookItem> listItems();
}
