package ElectronicComponentsShopMVC.Entity;


import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="ORDERS") 
public class Order {
	 
	private int order_id;
	
	@Column(name="order_ownername") 
	private String order_ownername;
	  
	@Column(name="order_owner") 
	private int order_owner;
	 
	@Column(name="order_address") 
	private String order_address;
	  
	@Column(name="order_phone") 
	private String order_phone;
	  
	@Column(name="order_email") 
	private String order_email;
	  
	@Column(name="order_note") 
	private String order_note;
	  
	@Column(name="order_status") 
	private String order_status;
	  
//	@Column(name="order_approver")
//	private int order_approver;
	
	@Column(name="order_items") 
	private String order_items;
  
	@Column(name="total")
	private int total;
	
	@Column(name="deny_reason")
	private String  deny_reason;
	

	//@ManyToMany(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
	//@JoinTable(name = "ORDER_DETAILS", joinColumns =
	// {@JoinColumn(name="order_id", referencedColumnName = "order_id")}, //
	// inverseJoinColumns = {@JoinColumn(name="product_id", referencedColumnName =
	// "product_id")}  ) 
	//private List<Product> prods;
  
	private Set<orderDetails> orderDetails = new HashSet<orderDetails>();
  
	public Order() {}
	

	public Order(int order_id, String order_ownername, int order_owner, String order_address, String order_phone,
			String order_email, String order_note, String order_status, String order_items, int total,
			int order_approver, Set<ElectronicComponentsShopMVC.Entity.orderDetails> orderDetails, String deny_reason) {
		super();
		this.order_id = order_id;
		this.order_ownername = order_ownername;
		this.order_owner = order_owner;
		this.order_address = order_address;
		this.order_phone = order_phone;
		this.order_email = order_email;
		this.order_note = order_note;
		this.order_status = order_status;
		this.order_items = order_items;
		this.total = total;
		//this.order_approver = order_approver;
		this.orderDetails = orderDetails;
		this.deny_reason = deny_reason;
		
	}

//	public int getOrder_approver() {
//		return order_approver;
//	}
//
//
//	public void setOrder_approver(int order_approver) {
//		this.order_approver = order_approver;
//	}
	
	
	public String getDeny_reason() {
		return deny_reason;
	}


	public void setDeny_reason(String deny_reason) {
		this.deny_reason = deny_reason;
	}

	public int getTotal() {
		return total;
	}


	public void setTotal(int total) {
		this.total = total;
	}



	public String getOrder_ownername() { 
		return order_ownername; 
		}
	  
	  
	  
	public void setOrder_ownername(String order_ownername) { 
		this.order_ownername = order_ownername; 
		}
	  
	  
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="order_id")  
	public int getOrder_id() { 
		return order_id; 
	}
	  
	  
	  
	public void setOrder_id(int order_id) { 
		this.order_id = order_id; 
		}
	  
	  
	  
	public int getOrder_owner() { 
		return order_owner; 
		}

	  
	public void setOrder_owner(int order_owner) { 
		this.order_owner = order_owner;
	}
	  
	  
	  
	public String getOrder_address() { 
		return order_address; 
		}
	  
	  
	  
	public void setOrder_address(String order_address) {
		this.order_address = order_address; 
		}
  
  
  
	public String getOrder_phone() { 
		return order_phone; 
		}
	  
	  
	  
	public void setOrder_phone(String order_phone) { 
		this.order_phone =order_phone; 
		}
	  
	  
	  
	public String getOrder_email() { 
		return order_email; 
		}
	  
	  
	  
	public void setOrder_email(String order_email) { 
		this.order_email =order_email; 
		}
	  
	  
	  
	public String getOrder_note() { 
		return order_note; 
		}
	  
	  
	  
	public void setOrder_note(String order_note) { 
		this.order_note = order_note;
		}
	  
	  
	public String getOrder_status() { 
		return order_status; 
		}
	  
	  
	  
	public void setOrder_status(String order_status) { 
		this.order_status =
	order_status; 
		}
	  
	  
	public String getOrder_items() { 
		return order_items; 
		}
	  
	public void setOrder_items(String order_items) { 
		this.order_items =order_items; 
		}
  
	// public List<Product> getProds() { 
	// return prods;
	//	}  
	//public void setProds(List<Product> prods) { 
	// this.prods = prods; 
	// }
  
	@OneToMany(mappedBy = "order") 
	public Set<orderDetails> getOrderDetails() {
	return orderDetails; 
	}
	  
	public void setOrderDetails(Set<orderDetails> order) { 
		this.orderDetails =order; 
	}
	 
	public void addOrderDetails(orderDetails orderDetails) {
		this.orderDetails.add(orderDetails); 
	}
}
 


