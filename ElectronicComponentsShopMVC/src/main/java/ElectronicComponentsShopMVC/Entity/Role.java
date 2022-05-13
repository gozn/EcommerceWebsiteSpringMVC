package ElectronicComponentsShopMVC.Entity;

import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "ROLES")
public class Role {
	@Id
	private int role_id;
	private String role_name;
	private String role_code;
	@OneToMany(mappedBy = "role", fetch = FetchType.EAGER)
	private Collection<Users> users;

	public Role(int role_id, String role_name, String role_code, Collection<Users> users) {
		super();
		this.role_id = role_id;
		this.role_name = role_name;
		this.role_code = role_code;
		this.users = users;
	}

	public Role() {
		super();
	}

	public int getRole_id() {
		return role_id;
	}

	public void setRole_id(int role_id) {
		this.role_id = role_id;
	}

	public String getRole_name() {
		return role_name;
	}

	public void setRole_name(String role_name) {
		this.role_name = role_name;
	}

	public String getRole_code() {
		return role_code;
	}

	public void setRole_code(String role_code) {
		this.role_code = role_code;
	}

	public Collection<Users> getUsers() {
		return users;
	}

	public void setUsers(Collection<Users> users) {
		this.users = users;
	}

}
