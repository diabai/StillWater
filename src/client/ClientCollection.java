package client;

import data.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * 
 * Client Collection class.
 * 
 * This class contains all the functionalities to successfully add, update or
 * search for a client in the Client database
 * 
 * @author Ibrahim Diabate
 * @version 2.0
 *
 */
public class ClientCollection {

	private static ClientDB mClientDB;
	private static Connection mConnection;

	/**
	 * Return a list of clients with the matching name.
	 * 
	 * @param name
	 * @return
	 */
	public static List<Client> search(String name) {
		List<Client> list = new ArrayList<Client>();

		if (mClientDB == null) {
			mClientDB = new ClientDB();

		}
		try {
			return mClientDB.getClients(name);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	/**
	 *
	 * Adds a new item category to the database
	 * 
	 * @param category
	 *            the category to be added
	 * @return true if category was successfully added, otherwise false
	 */
	public static boolean addClient(Client client) {

		Boolean result = true;
		String sql = "insert into `Client` (lastName, firstName, middleInitial, streetAddress, city, state, zipCode) values "
				+ "(?, ?, ?, ?, ?, ?, ?); ";

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
			preparedStatement.setString(1, client.getmLastName());
			preparedStatement.setString(2, client.getmFirstName());
			preparedStatement.setString(3, client.getmMiddleInitial());
			preparedStatement.setString(4, client.getmStreetAddress());
			preparedStatement.setString(5, client.getmCity());
			preparedStatement.setString(6, client.getmState());
			preparedStatement.setString(7, client.getmZipCode());
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			result = false;
		}
		return result;

	}

	/**
	 * Modifies the particular column of a client with the given data.
	 * 
	 * @param client
	 *            the client whose data is getting updated
	 * @param column
	 *            the column where the client's data to be updated is located
	 * @param data
	 *            the value for the field to update
	 * @return true if client's data was successfully updated otherwise false
	 */
	public static boolean update(Client client, String column, Object data) {
		if (mClientDB == null) {
			mClientDB = new ClientDB();
		}
		// Users cannot change client id
		if (!(column.equals("clientId"))) {
			String message = mClientDB.updateClient(client, column, data);
			if (message.startsWith("Error updating client: ")) {
				return false;
			}
			return true;
		}
		return false;
	}

	/**
	 * Return all clients in the list, null otherwise.
	 * 
	 * @return
	 */
	public static List<Client> getClients() {
		if (mClientDB == null) {
			mClientDB = new ClientDB();
		}
		try {
			return mClientDB.getClients();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 
	 * Returns a client with the given id, null otherwise.
	 * 
	 * @param id
	 *            the id of client to look for
	 * @return client the client with the matching id
	 */
	public static Client getClient(String id) {

		if (mClientDB == null) {
			mClientDB = new ClientDB();
		}
		try {

			return mClientDB.getClient(id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

}
