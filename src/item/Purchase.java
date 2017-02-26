package item;

/**
 * Purchase class.
 * Contains the functionalities needed for a Client to purchase
 * an item.
 * 
 * @author Ibrahim Diabate
 * @version 2.0
 *
 */
public class Purchase extends Transaction {

	private int purchaseId;
	private double cost;
	private String condition;
	private int clientId;

	public Purchase(double theCost, String theCondition, int theClientId) {

		cost = theCost;
		condition = theCondition;
		clientId = theClientId;
	}

	public int getPurchaseId() {
		return purchaseId;
	}

	public double getCost() {
		return cost;
	}

	public void setCost(double cost) {
		if (cost < 0) {
			throw new IllegalArgumentException("Cost cannot be negative");
		}
		this.cost = cost;
	}

	public String getCondition() {
		return condition;
	}

	public void setCondition(String condition) {

		if (condition == null) {
			throw new IllegalArgumentException("The condition cannot be null!");
		}
		if (condition.length() > 100) {
			throw new IllegalArgumentException("The condition's cannot be over 100 characters long!");
		}

		this.condition = condition;
	}

	public int getClientId() {
		return clientId;
	}

	@Override
	public boolean executeTransaction() {
		return false;
		// TODO Auto-generated method stub

	}

}
