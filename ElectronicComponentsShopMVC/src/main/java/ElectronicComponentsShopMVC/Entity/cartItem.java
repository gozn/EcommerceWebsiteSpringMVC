package ElectronicComponentsShopMVC.Entity;



public class cartItem {
	private Product prod;
	private int quantity;
	private double subTotal;
	
	//khoi tao gio hang
	public cartItem(Product prod) {
		this.prod = prod;
		this.quantity = 1;
		this.subTotal = prod.getProduct_price();
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public double getSubTotal() {
		subTotal = (prod.getProduct_price() - prod.getProduct_discount()) * quantity;
		return subTotal;
	}
	

	public void setSubTotal(double subTotal) {
		this.subTotal = subTotal;
	}

	public Product getProd() {
		return prod;
	}
}
