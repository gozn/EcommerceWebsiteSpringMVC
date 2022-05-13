package ElectronicComponentsShopMVC.Service.User;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ElectronicComponentsShopMVC.Dao.ProductDao;
import ElectronicComponentsShopMVC.Entity.Product;

@Service
@Transactional
public class ProductService {
	@Autowired
	ProductDao productDao = new ProductDao();

	public Product getProductID(int product_id) {
		return productDao.getProductID(product_id);
	}

	public List<Product> getAllProduct() {
		return productDao.getAllProduct();
	}
	
	public List<Product> getLatestProd() {
		return productDao.getLatestProd();
	}

	public List<Product> getAllProductFeature() {
		return productDao.getAllProductFeature();
	}

	public List<Product> getProductByCategoryID(int category_id) {
		return productDao.getProductByCategoryID(category_id);
	}

	public List<Product> getProductLimit(int first) {
		return productDao.getProductLimit(first);
	}

	public List<Product> getProductByCategoryIDLimit(int category_id, int first) {
		return productDao.getProductByCategoryIDLimit(category_id, first);
	}
}
