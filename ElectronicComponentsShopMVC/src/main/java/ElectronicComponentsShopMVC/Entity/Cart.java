package ElectronicComponentsShopMVC.Entity;

import java.util.ArrayList;
import java.util.List;


public class Cart {
	private List<cartItem> items;
	private int total;
	private int size;
	private String itemName;
	private int tas;
	private int shipping;
	
	//Khoi tao
	public Cart() {
		items = new ArrayList<cartItem>(); //Lay cac item trong gio hang
		total = 0; //thanhtien = 0
		tas = 0;
		shipping = 0;
	}
	
	//Phuong thuc lay 1 Item
	public cartItem getItem(Product prod) {
		
		for(cartItem item : items) {
			if (item.getProd().getProduct_id() == prod.getProduct_id()) { //kiem tra da ton tai item 			
				return item;
			}
		}	
		return null; //k co item tra ve null
	}
	
	
	
	//Lay ve cac item
	public List<cartItem> getItems() {
		return items;
	}
	
	//lay ve so luong cac item
	public int getSoLuong() {
		return items.size();
	}
	
	//them 1 item
	public void addItem(cartItem item) {
		addItem(item.getProd(), item.getQuantity());
	}
	
	//them nhieu item
	public void addItem(Product prod, int quantity) {
		cartItem item = getItem(prod);
		if(item != null) {
			item.setQuantity(item.getQuantity()+quantity);
		} else {
			item = new cartItem(prod);
			item.setQuantity(quantity);
			items.add(item); //them vao list cac item	
		}
	}
	
	//update san pham
	public void updateItem(Product prod, int quantity) {
		cartItem item = getItem(prod);
		if(item != null) {
			item.setQuantity(quantity);
		}
	}
	
	//xoa 1 san pham
	public void deleteItem(Product prod) {
		cartItem item = getItem(prod);
		if(item != null) {
			items.remove(item);
		}
	}
	
	//xoa toan bo
	public void clearItem() {
		items.clear();
		total=0;
	}
	
	//kiem tra gio hang
	public boolean isEmpty( ) {
		return items.isEmpty();
	}
	
	//lay ve thanh tien
	public double getTotal(){
		total=0;
		for(cartItem item : items) {
			total += item.getSubTotal();
		}
		return total;
	}
	
	//lay ve phi ship
	public double getShipping() {
		shipping = 0;
		if(total < 500000 && total != 0) {
			shipping = shipping + 30000;
		}
		return shipping;
	}
	
	//lay ve so tien pahi tra
	public double getTas(){
		tas = 0;
		if(shipping != 0) {
			tas = total + shipping;
		} else tas = total;
		return tas;
	}
	
	//lay item name
	public String getItemName() {
		itemName = "";
		for(cartItem item : items) {
			itemName = itemName + " / " + item.getProd().getProduct_name();
		}
		return itemName;
	}
	
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	
	
	public void setTotal(double total) {
		this.total = (int) total;
	}

	public int getSize() {
		return size;
	}
	
	
	
}
	
