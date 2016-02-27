package xyz.maksimenko.javaeett;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "users")
public class User implements Serializable{
	@Id
	@Column(name = "userid")
	@GenericGenerator(name="autoincrement",strategy="increment")
	@GeneratedValue(generator="autoincrement")
	private Long userId;
	
	@Column(name = "username")
	private String username;
	
	@Column(name = "login")
	private String login;
	
	@Column(name = "email")
	private String email;
	
	@Column(name = "passwordhash")
	private String passwordHash;
	
	@OneToMany
	@Cascade(CascadeType.ALL)
	@JoinColumn(name="fromuserlogin", referencedColumnName="login")
	private Set<Message> messagesFrom = new HashSet<Message>();
	
	@OneToMany
	@Cascade(CascadeType.ALL)
	@JoinColumn(name="touserlogin", referencedColumnName="login")
	private Set<Message> messagesTo = new HashSet<Message>();
	
	@OneToMany
	@Cascade(CascadeType.ALL)
	@JoinColumn(name="owneruserlogin", referencedColumnName="login")
	private Set<AddressBookItem> abRecoreds = new HashSet<AddressBookItem>();
	
	@OneToMany
	@Cascade(CascadeType.ALL)
	@JoinColumn(name="itemuserlogin", referencedColumnName="login")
	private Set<AddressBookItem> asAbItem = new HashSet<AddressBookItem>();
	
	@OneToMany
	@Cascade(CascadeType.ALL)
	@JoinColumn(name="login", referencedColumnName="login")
	private Set<UserRole> userRoles = new HashSet<UserRole>();
	
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPasswordHash() {
		return passwordHash;
	}
	public void setPasswordHash(String passwordHash) {
		this.passwordHash = passwordHash;
	}
	public Set<Message> getMessagesFrom() {
		return messagesFrom;
	}
	public void setMessagesFrom(Set<Message> messagesFrom) {
		this.messagesFrom = messagesFrom;
	}
	public Set<Message> getMessagesTo() {
		return messagesTo;
	}
	public void setMessagesTo(Set<Message> messagesTo) {
		this.messagesTo = messagesTo;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public Set<UserRole> getUserRoles() {
		return userRoles;
	}
	public void setUserRoles(Set<UserRole> userRoles) {
		this.userRoles = userRoles;
	}
}
