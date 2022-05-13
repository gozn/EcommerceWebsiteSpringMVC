package ElectronicComponentsShopMVC.Entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.validator.constraints.NotEmpty;

@Indexed
@Entity
@Table(name = "PRODUCTS")
public class Product {
	
	private int product_id;

	@Field
	@NotEmpty(message = "Không được để trống tên sản phẩm !")
	private String product_name;

	@NotNull(message = "Không được để trống giá sản phẩm !")
	//@DecimalMin(value = "1000.0", message = "Giá sản phẩm không hợp lệ!")
	private int product_price;

	@NotNull(message = "Không được để trong giá sản phẩm !")
	//@DecimalMin(value = "0", message = "Dicount không hợp lệ!")
	//@DecimalMax(value = "100", message = "Dicount không hợp lệ!")
	private int product_discount;

	@Field
	@NotEmpty(message = "Không được để trống thông tin sản phẩm !")
	private String product_detail;

	@NotEmpty(message = "Không được để trống hình ảnh sản phẩm !")
	private String product_images;

	@NotNull(message = "Không được để trống sản phẩm")
	//@DecimalMin(value = "1", message = "Số lượng sả phẩm không hợp lệ!")
	private int product_quantity;

	
	private Category categoryID;
	
	private Set<orderDetails> orderDetails = new HashSet<orderDetails>();
	
	public Product() {
		super();
	}

	
	
	public Product(int product_id, String product_name, int product_price, int product_discount, String product_detail,
			String product_images, int product_quantity, Category categoryID) {
		super();
		this.product_id = product_id;
		this.product_name = product_name;
		this.product_price = product_price;
		this.product_discount = product_discount;
		this.product_detail = product_detail;
		this.product_images = product_images;
		this.product_quantity = product_quantity;
		this.categoryID = categoryID;
	}


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="product_id")
	public int getProduct_id() {
		return product_id;
	}

	public void setProduct_id(int product_id) {
		this.product_id = product_id;
	}

	@Column(name="product_name")
	public String getProduct_name() {
		return product_name;
	}

	public void setProduct_name(String product_name) {
		this.product_name = product_name;
	}


	public int getProduct_price() {
		return product_price;
	}

	public void setProduct_price(int product_price) {
		this.product_price = product_price;
	}

	public int getProduct_discount() {
		return product_discount;
	}

	public void setProduct_discount(int product_discount) {
		this.product_discount = product_discount;
	}

	@Column(name="product_detail")
	public String getProduct_detail() {
		return product_detail;
	}

	public void setProduct_detail(String product_detail) {
		this.product_detail = product_detail;
	}

	public String getProduct_images() {
		return product_images;
	}

	public void setProduct_images(String product_images) {
		this.product_images = product_images;
	}

	@ManyToOne(fetch = FetchType.EAGER)	
	@JoinColumn(name = "category_id")
	public Category getCategoryID() {
		return categoryID;
	}

	public void setCategoryID(Category categoryID) {
		this.categoryID = categoryID;
	}

	public int getProduct_quantity() {
		return product_quantity;
	}

	public void setProduct_quantity(int product_quantity) {
		this.product_quantity = product_quantity;
	}
	
	@OneToMany(mappedBy = "product")
    public Set<orderDetails> getOrderDetails() {
        return orderDetails;
    }
 
    public void setOrderDetails(Set<orderDetails> product) {
        this.orderDetails = product;
    }
     
    public void addOrderDetails(orderDetails orderDetails) {
        this.orderDetails.add(orderDetails);
    }
}



//package ElectronicComponentsShopMVC.Entity;
//
//import javax.persistence.Entity;
//import javax.persistence.FetchType;
//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
//import javax.persistence.Id;
//import javax.persistence.JoinColumn;
//import javax.persistence.ManyToOne;
//import javax.persistence.Table;
//import javax.validation.constraints.NotNull;
//
//import org.hibernate.validator.constraints.NotEmpty;
//
//@Entity
//@Table(name = "PRODUCTS")
//public class Product {
//	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
//	private int product_id;
//
//	@NotEmpty(message = "Không được để trống tên sản phẩm !")
//	private String product_name;
//
//	@NotNull(message = "Không được để trống giá sản phẩm !")
//	//@DecimalMin(value = "1000.0", message = "Giá sản phẩm không hợp lệ!")
//	private int product_price;
//
//	@NotNull(message = "Không được để trong giá sản phẩm !")
//	//@DecimalMin(value = "0", message = "Dicount không hợp lệ!")
//	//@DecimalMax(value = "100", message = "Dicount không hợp lệ!")
//	private int product_discount;
//
//	@NotEmpty(message = "Không được để trống thông tin sản phẩm !")
//	private String product_detail;
//
//	@NotEmpty(message = "Không được để trống hình ảnh sản phẩm !")
//	private String product_images;
//
//	@NotNull(message = "Không được để trống sản phẩm")
//	//@DecimalMin(value = "1", message = "Số lượng sả phẩm không hợp lệ!")
//	private int product_quantity;
//
//	@ManyToOne(fetch = FetchType.EAGER)	
//	@JoinColumn(name = "category_id")
//
//	private Category categoryID;
//
//	public Product() {
//		super();
//	}
//
//	
//
//	public Product(int product_id, String product_name, int product_price, int product_discount, String product_detail,
//			String product_images, int product_quantity, Category categoryID) {
//		super();
//		this.product_id = product_id;
//		this.product_name = product_name;
//		this.product_price = product_price;
//		this.product_discount = product_discount;
//		this.product_detail = product_detail;
//		this.product_images = product_images;
//		this.product_quantity = product_quantity;
//		this.categoryID = categoryID;
//	}
//
//
//
//	public int getProduct_id() {
//		return product_id;
//	}
//
//	public void setProduct_id(int product_id) {
//		this.product_id = product_id;
//	}
//
//	public String getProduct_name() {
//		return product_name;
//	}
//
//	public void setProduct_name(String product_name) {
//		this.product_name = product_name;
//	}
//
//
//	public int getProduct_price() {
//		return product_price;
//	}
//
//	public void setProduct_price(int product_price) {
//		this.product_price = product_price;
//	}
//
//	public int getProduct_discount() {
//		return product_discount;
//	}
//
//	public void setProduct_discount(int product_discount) {
//		this.product_discount = product_discount;
//	}
//
//	public String getProduct_detail() {
//		return product_detail;
//	}
//
//	public void setProduct_detail(String product_detail) {
//		this.product_detail = product_detail;
//	}
//
//	public String getProduct_images() {
//		return product_images;
//	}
//
//	public void setProduct_images(String product_images) {
//		this.product_images = product_images;
//	}
//
//	public Category getCategoryID() {
//		return categoryID;
//	}
//
//	public void setCategoryID(Category categoryID) {
//		this.categoryID = categoryID;
//	}
//
//	public int getProduct_quantity() {
//		return product_quantity;
//	}
//
//	public void setProduct_quantity(int product_quantity) {
//		this.product_quantity = product_quantity;
//	}
//}
