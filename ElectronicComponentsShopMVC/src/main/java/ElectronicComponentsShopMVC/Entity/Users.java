package ElectronicComponentsShopMVC.Entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.Valid;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name = "USERS")
public class Users {
	@Id
	@GeneratedValue()
	@Column(name = "users_id")
	private int users_id;
	@NotEmpty(message = "Không được để trống username!")
	@Email(message = "Email không hợp lệ!")
	@Column(name = "users_username", unique = true)
	private String users_username;
	@NotEmpty(message = "Không được để trống password!")
	@Column(name = "users_password")
	private String users_password;
	
	@Valid
	@OneToOne(mappedBy = "user")
	private Customer customer;

	@Valid
	@OneToOne(mappedBy = "usernv")
	private Emloyee emloyee;

	@ManyToOne
	@JoinColumn(name = "role_id")
	private Role role;


	public Users() {
		super();
	}

	public Users(int users_id, String users_username, String users_password, Customer customer, Emloyee emloyee,
			Role role) {
		super();
		this.users_id = users_id;
		this.users_username = users_username;
		this.users_password = users_password;
		this.customer = customer;
		this.emloyee = emloyee;
		this.role = role;
	}



	public int getUsers_id() {
		return users_id;
	}

	public void setUsers_id(int users_id) {
		this.users_id = users_id;
	}

	public String getUsers_username() {
		return users_username;
	}

	public void setUsers_username(String users_username) {
		this.users_username = users_username;
	}

	public String getUsers_password() {
		return users_password;
	}

	public void setUsers_password(String users_password) {
		this.users_password = users_password;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Emloyee getEmloyee() {
		return emloyee;
	}

	public void setEmloyee(Emloyee emloyee) {
		this.emloyee = emloyee;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

}
