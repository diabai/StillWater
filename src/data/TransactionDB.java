package data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;


import item.Sale;

public class TransactionDB {

	private Connection mConnection;

	/**
	 * Adds an item sale to the Sale table.
	 * 
	 * @param theSale
	 *            the sale object of an item
	 * @return Returns "Added Sale Successfully" or "Error Selling item: " with
	 *         the sql exception.
	 */
	public String sell(Sale theSale) {
		String sql = "insert into Sale (commissionPaid, price, clientId, itemId) values " + "(?, ?, ?, ?); ";

		if (mConnection == null) {
			try {
				mConnection = DataConnection.getConnection();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		PreparedStatement preparedStatement = null;
		try {
			preparedStatement = mConnection.prepareStatement(sql);
			preparedStatement.setDouble(1, theSale.getCommissionPaid());
			preparedStatement.setDouble(2, theSale.getPrice());
			preparedStatement.setString(3, theSale.getClientId());
			preparedStatement.setString(4, theSale.getItemId());

			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			return "Error selling item: " + e.getMessage();
		}
		return "Item sold successfully";
	}

	// public String purchase (Purchase thePurchase) {
	// String sql = "insert into Sale (commissionPaid, price, clientId, itemId)
	// values " + "(?, ?, ?, ?); ";
	//
	// if (mConnection == null) {
	// try {
	// mConnection = DataConnection.getConnection();
	// } catch (SQLException e) {
	// e.printStackTrace();
	// }
	// }
	//
	// PreparedStatement preparedStatement = null;
	// try {
	// preparedStatement = mConnection.prepareStatement(sql);
	// preparedStatement.setDouble(1, thePurchase.getCost());
	// preparedStatement.setString(2, thePurchase.getCondition());
	// preparedStatement.setInt(3, thePurchase.getClientId());
	// preparedStatement.setInt(4,thePurchase.getItemId());
	//
	// preparedStatement.executeUpdate();
	// } catch (SQLException e) {
	// e.printStackTrace();
	// return "Error selling item: " + e.getMessage();
	// }
	// return "Item sold successfully";
	// }
}
