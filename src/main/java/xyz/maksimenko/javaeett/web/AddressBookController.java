package xyz.maksimenko.javaeett.web;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import xyz.maksimenko.DAO.UserDAO;
import xyz.maksimenko.javaeett.AddressBookItem;
import xyz.maksimenko.javaeett.Message;
import xyz.maksimenko.javaeett.User;
import xyz.maksimenko.javaeett.service.AddressBookService;

@RestController
public class AddressBookController {
	
	@Autowired
	private AddressBookService abService;
	@Autowired
	private UserDAO userDao;
	
	@RequestMapping(value = "/addAddressBookItem/{userId}", method = RequestMethod.GET)
	public ResponseEntity addItem(@PathVariable("itemId") Long userId) {
		String currentUser = SecurityContextHolder.getContext().getAuthentication().getName();
		User itemUser = userDao.getUser(userId);

		AddressBookItem adItem = new AddressBookItem();
		adItem.setOwnerlogin(currentUser);
		adItem.setItemuserlogin(itemUser.getLogin());
		abService.addItem(adItem);
		//return "redirect:/";
		return new ResponseEntity(HttpStatus.CREATED);
	}
	
	@RequestMapping(value = "removeAddressBookItem/{itemId}")
	public ResponseEntity deleteItem(@PathVariable("itemId") Long itemId) {
		abService.removeItem(itemId);
		//return "redirect:/";
		return new ResponseEntity(HttpStatus.OK);
	}
	
	@RequestMapping("/listAddressBook")
	public List<AddressBookItem> listAddressBook(Map<String, Object> map) {
		String currentUser = SecurityContextHolder.getContext().getAuthentication().getName();
		/*map.put("addressbookitem", new AddressBookItem());
		map.put("message", new Message());
		map.put("user", new User());
		map.put("addressBookList", abService.listItemsForUser(currentUser));*/
		
		//return "mainpage";
		return abService.listItemsForUser(currentUser);
	}
}
