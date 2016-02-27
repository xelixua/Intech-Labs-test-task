package xyz.maksimenko.javaeett.web;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import xyz.maksimenko.javaeett.User;
import xyz.maksimenko.javaeett.service.UserService;

@Controller
public class UserController {
	
	@Autowired
	private  UserService userService;
	
	@RequestMapping(value = "/changePassword", method = RequestMethod.POST)
	public @ResponseBody ResponseEntity changePassword(@RequestParam("newpassword") String newPassword){
		String currentUser = SecurityContextHolder.getContext().getAuthentication().getName();
		userService.changePassword(newPassword, currentUser);
		//return "redirect:" + MessageController.DEF_PATH;
		return new ResponseEntity(HttpStatus.ACCEPTED);
	}
	
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public String registerUser(@ModelAttribute("user") @Valid User user,
			BindingResult result) {
		try {
			userService.registerUser(user);
		} catch (Exception e){
			//user already registered
			return "/register.jsp?alreadyregistered=true";
		}
		return "redirect:/listUsers";
	}

}
