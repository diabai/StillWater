package item;

/**
 * Sale class containing the functionalities needed for a Client to sell an
 * item.
 * 
 * @author Ibrahim Diabate
 * @version 2.0
 *
 */
public class Sale extends Transaction {

	private String saleId;
	private double commissionPaid;
	private double price;
	private String clientId;
	private String itemId;

	/**
	 * Sale constructor.
	 * 
	 * @param theCcommission  the commission for selling an item
	 * @param thePrice 		  the price of an item
	 * @param theClientId     the clientId of client who made the sale
	 * @param theItemId		  the ItemId of the item sold
	 */
	public Sale(double theCommission, double thePrice, String theClientId, String theItemId) {

		setCommissionPaid(theCommission);
		setPrice(thePrice);
		clientId = theClientId;
		itemId = theItemId;

	}

	public String getId() {
		return saleId;
	}

	public double getCommissionPaid() {
		return commissionPaid;
	}

	public void setCommissionPaid(double commissionPaid) {

		if (commissionPaid < 0) {
			throw new IllegalArgumentException();
		}

		this.commissionPaid = commissionPaid;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		if (price < 0) {
			throw new IllegalArgumentException("Price cannot be negative");
		}
		this.price = price;
	}

	public String getClientId() {
		return clientId;
	}

	public String getItemId() {
		return itemId;
	}

	 
	@Override
	public boolean executeTransaction() {
		return false;
		// TODO Auto-generated method stub

	}

}
