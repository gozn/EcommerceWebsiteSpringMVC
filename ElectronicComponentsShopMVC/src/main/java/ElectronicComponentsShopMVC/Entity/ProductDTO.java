package ElectronicComponentsShopMVC.Entity;

public class ProductDTO {
	private int product_id;
	private String product_name;
	private int product_price;
	private int product_discount;
	private String product_detail;
	private String product_images;
	private int categoryID;
	private int product_quantity;
	
	public ProductDTO() {}
	
	

	public ProductDTO(int product_id, String product_name, int product_price, int product_discount,
			String product_detail, String product_images, int categoryID, int product_quantity) {
		super();
		this.product_id = product_id;
		this.product_name = product_name;
		this.product_price = product_price;
		this.product_discount = product_discount;
		this.product_detail = product_detail;
		this.product_images = product_images;
		this.categoryID = categoryID;
		this.product_quantity = product_quantity;
	}



	public int getProduct_id() {
		return product_id;
	}
	public void setProduct_id(int product_id) {
		this.product_id = product_id;
	}
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
	public int getCategoryID() {
		return categoryID;
	}
	public void setCategoryID(int categoryID) {
		this.categoryID = categoryID;
	}

	public int getProduct_quantity() {
		return product_quantity;
	}

	public void setProduct_quantity(int product_quantity) {
		this.product_quantity = product_quantity;
	}
}
