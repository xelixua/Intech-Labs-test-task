package xyz.maksimenko.javaeett;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "userroles")
public class UserRole {
	@Id
	@Column(name = "roleid")
	@GenericGenerator(name="autoincrement",strategy="increment")
	@GeneratedValue(generator="autoincrement")
	private Long roleId;
	
	@Column(name = "login")
	private String login;
	
	@Column(name = "role")
	private String role;

	public Long getRoleid() {
		return roleId;
	}

	public void setRoleid(Long roleId) {
		this.roleId = roleId;
	}
	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}
}
