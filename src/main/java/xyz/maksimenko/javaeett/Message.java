package xyz.maksimenko.javaeett;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "messages")
public class Message implements Serializable{
	@Id
	@Column(name = "messageid")
	@GenericGenerator(name="autoincrement",strategy="increment")
	@GeneratedValue(generator="autoincrement")
	private Long messageId;
	
	@Column(name="fromuserlogin")
	private String fromuserlogin;
	
	@Column(name="touserlogin")
	private String touserlogin;
	
	@Column(name="date")
	private Long date;
	
	@Column(name="subject")
	private String subject;
	
	@Column(name="text")
	private String text;
	
	public Long getMessageId() {
		return messageId;
	}
	public void setMessageId(Long messageId) {
		this.messageId = messageId;
	}
	public Long getDate() {
		return date;
	}
	public void setDate(Long date) {
		this.date = date;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getFromuserlogin() {
		return fromuserlogin;
	}
	public void setFromuserlogin(String fromuserlogin) {
		this.fromuserlogin = fromuserlogin;
	}
	public String getTouserlogin() {
		return touserlogin;
	}
	public void setTouserlogin(String touserlogin) {
		this.touserlogin = touserlogin;
	}
}
