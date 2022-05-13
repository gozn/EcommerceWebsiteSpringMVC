package ElectronicComponentsShopMVC.Entity;

import java.util.Date;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="ORDER_DETAILS")
public class orderDetails {
	
	private int od_id;
	
	private Order order;
	
	private Product product;
	
	@Column(name="quantity")
	private int quantity;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "create_at", length = 10)
	private Date create_at;
	
	
	public orderDetails() {}
	public orderDetails(int od_id, Order order, Product product, int quantity, Date create_at, Date update_at) {
		super();
		this.od_id = od_id;
		this.order = order;
		this.product = product;
		this.quantity = quantity;
		this.create_at = create_at;
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="od_id")
	public int getOd_id() {
		return od_id;
	}
	public void setOd_id(int od_id) {
		this.od_id = od_id;
	}
	
	@ManyToOne
    @JoinColumn(name = "order_id") 
	public Order getOrder() {
		return order;
	}
	public void setOrder(Order order) {
		this.order = order;
	}
	
	@ManyToOne
    @JoinColumn(name = "product_id")
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public Date getCreate_at() {
		return create_at;
	}
	public void setCreate_at(Date date) {
		this.create_at = date;
	}

	
}
