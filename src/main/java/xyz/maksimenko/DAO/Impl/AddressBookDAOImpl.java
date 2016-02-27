package xyz.maksimenko.DAO.Impl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import xyz.maksimenko.DAO.AddressBookDAO;
import xyz.maksimenko.javaeett.AddressBookItem;

@Repository
public class AddressBookDAOImpl implements AddressBookDAO {
	
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public void addItem(AddressBookItem item) {
		sessionFactory.getCurrentSession().save(item);

	}

	@Override
	public void removeItem(Long itemId) {
		AddressBookItem item = (AddressBookItem) sessionFactory.getCurrentSession().load(
				AddressBookItem.class, itemId);
		if (null != item) {
			sessionFactory.getCurrentSession().delete(item);
		}

	}

	@Override
	public List<AddressBookItem> listItemsForUser(String userlogin) {
		return sessionFactory.getCurrentSession().createQuery("from AddressBookItem where owneruserlogin  = :userlogin").setString("userlogin", userlogin).list();

	}

	@Override
	public List<AddressBookItem> listItems() {
		return sessionFactory.getCurrentSession().createQuery("from AddressBookItem").list();
	}

}
