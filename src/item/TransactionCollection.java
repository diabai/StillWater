package item;

import data.TransactionDB;

/**
 * 
 * Transaction collection class.
 * 
 * This call contains the method that will be used to process sales to the
 * system.
 * 
 * @author Ibrahim Diabate
 * @version 2.0
 *
 */
public class TransactionCollection {

	private static TransactionDB mTranDB;

	/**
	 * Adds a sale to Sale the database.
	 * 
	 * @param sale
	 *            and item to be sold
	 * @return true if the item was successfully sold otherwise false
	 */
	public static boolean sellItem(Sale theSale) {
		if (mTranDB == null) {
			mTranDB = new TransactionDB();
		}

		String message = mTranDB.sell(theSale);
		if (message.startsWith("Error selling item:")) {
			return false;
		}
		return true;
	}

	/**
	 * Purchase item method. Adds a purchase to the Item database
	 * 
	 * @param thePurchase
	 *            the object containing details about an Item purchase
	 * @return true if the purchase was successfully added to the database
	 */
	// public static boolean purchaseItem(Purchase thePurchase) {
	//
	// if (mTranDB == null) {
	// mTranDB = new TransactionDB();
	// }
	//
	// String message = mTranDB.purchase(thePurchase);
	// if (message.startsWith("Error selling item:")) {
	// return false;
	// }
	// return true;
	// }

}
