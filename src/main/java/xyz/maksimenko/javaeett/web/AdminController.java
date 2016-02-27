package xyz.maksimenko.javaeett.web;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import xyz.maksimenko.javaeett.Message;
import xyz.maksimenko.javaeett.User;
import xyz.maksimenko.javaeett.service.AdminService;

@RestController
public class AdminController {

	@Autowired
	private AdminService adminService;
	
	@RequestMapping("/listUsers")
	public List<User> listUsers(Map<String, Object> map) {
		/*map.put("user", new User());
		map.put("message", new Message());
		map.put("usersList", adminService.listUsers());*/
		
		//return "mainpage";
		return adminService.listUsers();
	}
	
	@RequestMapping(value = "/addUser", method = RequestMethod.POST)
	public ResponseEntity addUser(@ModelAttribute("user") User user, 
			BindingResult result) {
		System.out.println("Admin controller addUser");
		try {
			adminService.addUser(user);
		} catch (Exception e) {
			//return "redirect:/listUsers?useralreadyexists=true";
			return new ResponseEntity(HttpStatus.ALREADY_REPORTED);
		}
		//return "redirect:/listUsers";
		return new ResponseEntity(HttpStatus.CREATED);
	}
	
	@RequestMapping(value = "removeUser/{userId}")
	public ResponseEntity deleteUser(@PathVariable("userId") Long userId){
		adminService.removeUser(userId);
		//return "redirect:/listUsers";
		return new ResponseEntity(HttpStatus.OK);
	}
	
	@RequestMapping(value = "addAdminRights/{userId}")
	public ResponseEntity addAdminRights(@PathVariable("userId") Long userId){
		adminService.addAdminRights(userId);
		//return "redirect:/listUsers";
		return new ResponseEntity(HttpStatus.OK);
	}
	
	@RequestMapping(value = "removeAdminRights/{userId}")
	public ResponseEntity removeAdminRights(@PathVariable("userId") Long userId){
		adminService.removeAdminRights(userId);
		//return "redirect:/listUsers";
		return new ResponseEntity(HttpStatus.OK);
	}
	
	@ModelAttribute("user")
	public User createModel() {
	    return new User();
	}
}
