package xyz.maksimenko.javaeett.web;

import java.util.Calendar;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import xyz.maksimenko.javaeett.Message;
import xyz.maksimenko.javaeett.User;
import xyz.maksimenko.javaeett.service.MessagingService;

@Controller
public class MessageController {
	private static final Logger LOG=Logger.getLogger(MessageController.class);
	public static final byte BY_FROM = 0;
	public static final byte BY_DATE = 1;
	public static final byte BY_SUBJECT = 2;
	public static final String DEF_PATH = "/index/0";
	
	@Autowired
	private MessagingService messagingService;
	
	@RequestMapping("/index/{sortBy}")
	public String listMessages(Map<String, Object> map,@PathVariable("sortBy") byte sortBy) {
		String currentUser = SecurityContextHolder.getContext().getAuthentication().getName();
		List<Message> dbList = messagingService.listMessagesForUser(currentUser);
		
		Comparator<Message> comparator;
		switch(sortBy){
		case (byte) 0:
			comparator = new FromComparator();
			break;
		case (byte) 1:
			comparator = new DateComparator();
			break;
		default:
			comparator = new SubjectComparator();
			break;
		}
		dbList.sort(comparator);
		map.put("message", new Message());
		map.put("user", new User());
		map.put("messagesList", dbList);

		LOG.info("User " + currentUser + " is on index");
		return "mainpage";
	}
	
	@RequestMapping("/")
	public String home(){
		return "redirect:" + DEF_PATH;
	}
	
	
	@RequestMapping(value = "/sendMessage", method = RequestMethod.POST)
	public ResponseEntity sendMessage(@ModelAttribute("message") Message message,
			BindingResult result) {
		System.out.println(message.getSubject());
		String currentUser = SecurityContextHolder.getContext().getAuthentication().getName();
		message.setFromuserlogin(currentUser);
		message.setDate(Calendar.getInstance().getTimeInMillis() / 1000);
		messagingService.sendMessage(message);
		LOG.info("User " + currentUser + " /sendMessage");
		//return "redirect:" + DEF_PATH;
		return new ResponseEntity(HttpStatus.CREATED);
	}
	
	@RequestMapping("/deleteMessage/{messageId}")
	public ResponseEntity deleteMessage(@PathVariable("messageId") Long messageId) {
		String currentUser = SecurityContextHolder.getContext().getAuthentication().getName();
		messagingService.removeMessage(messageId);
		LOG.info("User " + currentUser + " deleting message id " + messageId);
		//return "redirect:" + DEF_PATH;
		return new ResponseEntity(HttpStatus.OK);
	}

}
