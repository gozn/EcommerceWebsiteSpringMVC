package ElectronicComponentsShopMVC.Service.Admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ElectronicComponentsShopMVC.Dao.OrderDao;
import ElectronicComponentsShopMVC.Entity.Order;



@Service
@Transactional
public class OrderService {
	@Autowired
	OrderDao orderDao;
	
	public List<Order> getNullOrders(){
		return orderDao.getNullOrder();
	}
	
	public List<Order> getLatestNullOrder(){
		return orderDao.getLatestNullOrder();
	}
	
	public List<Order> getConfirmedOrders(){
		return orderDao.getConfirmedOrder();
	}
	
	public List<Order> getLatestConfirmedOrders(){
		return orderDao.getLatestConfirmedOrder();
	}
	
	public List<Order> getDenyOrders(){
		return orderDao.getDenyOrder();
	}
	
	public List<Order> getLatestDenyOrders(){
		return orderDao.getLatestDenyOrder();
	}
	
	public List<Order> getUserOrder(int id){
		return orderDao.getUserOrder(id);
	}
}
