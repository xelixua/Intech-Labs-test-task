package xyz.maksimenko.javaeett;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "addressbook")
public class AddressBookItem {
	
	@Id
	@Column(name = "itemid")
	@GenericGenerator(name="autoincrement",strategy="increment")
	@GeneratedValue(generator="autoincrement")
	private Long itemId;
	
	@Column(name = "itemuserlogin")
	private String itemuserlogin;
	
	@Column(name = "owneruserlogin")
	private String ownerlogin;

	public Long getItemId() {
		return itemId;
	}

	public void setItemId(Long itemId) {
		this.itemId = itemId;
	}

	public String getItemuserlogin() {
		return itemuserlogin;
	}

	public void setItemuserlogin(String itemuserlogin) {
		this.itemuserlogin = itemuserlogin;
	}

	public String getOwnerlogin() {
		return ownerlogin;
	}

	public void setOwnerlogin(String ownerlogin) {
		this.ownerlogin = ownerlogin;
	}
}
