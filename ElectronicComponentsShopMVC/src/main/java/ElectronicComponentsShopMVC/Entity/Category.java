package ElectronicComponentsShopMVC.Entity;

import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotEmpty;



@Entity
@Table(name = "CATEGORY")
public class Category {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int category_id;
	@NotEmpty(message = "Không được để trống tên danh mục!")
	private String category_name;
	private int parent_id;
	

	
	@OneToMany(mappedBy="categoryID", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval=true)
	private Collection<Product> products;

	public Category() {
		super();
	}

	public Category(int category_id, String category_name, int parent_id, Collection<Product> products) {
		super();
		this.category_id = category_id;
		this.category_name = category_name;
		this.parent_id = parent_id;
		this.products = products;
	}



	public int getCategory_id() {
		return category_id;
	}

	public void setCategory_id(int category_id) {
		this.category_id = category_id;
	}

	public String getCategory_name() {
		return category_name;
	}

	public void setCategory_name(String category_name) {
		this.category_name = category_name;
	}

	public int getParent_id() {
		return parent_id;
	}

	public void setParent_id(int parent_id) {
		this.parent_id = parent_id;
	}

	public Collection<Product> getProduct() {
		return products;
	}

	public void setProduct(Collection<Product> products) {
		this.products = products;
	}

}
