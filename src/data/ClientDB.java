package data;

import client.Client;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * 
 * Client Database. This class encapsulates all the functions available to add,
 * update or search for a client
 * 
 * @author Ibrahim Diabate
 * @version 2.0
 *
 */
public class ClientDB {

	private Connection mConnection ;
	private List<Client> mClientList;

	/**
	 * Retrieves all clients from the client table
	 * 
	 * @return
	 * @throws SQLException
	 */
	public List<Client> getClients() throws SQLException {
		if (mConnection == null) {
			mConnection = DataConnection.getConnection();
		}
		Statement stmt = null;
		String query = "select * " + "from `Client` ";

		mClientList = new ArrayList<Client>();
		try {
			stmt = mConnection.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
				int id = rs.getInt("clientId");
				String lastName = rs.getString("lastName");
				String firstName = rs.getString("firstName");
				String midInitial = rs.getString("middleInitial");
				String address = rs.getString("streetAddress");
				String city = rs.getString("city");
				String state = rs.getString("state");
				String zipCode = rs.getString("zipcode");
				Client client = null;
				if (midInitial == null) {
					client = new Client(lastName, firstName, address, city, state, zipCode);
					client.setId(new Integer(id).toString());
				} else {
					client = new Client(lastName, firstName, midInitial, address, city, state, zipCode);
					client.setId(new Integer(id).toString());
				}
				mClientList.add(client);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println(e);
		} finally {
			if (stmt != null) {
				stmt.close();
			}
		}
		return mClientList;
	}

	/**
	 * 
	 * Returns all clients that have the same last name
	 * 
	 * @param lastName
	 * @return
	 * @throws SQLException
	 */
	public List<Client> getClients(String lastName) throws SQLException {
		List<Client> filterList = new ArrayList<Client>();
		if (mClientList == null) {
			getClients();
		}
		lastName = lastName.toLowerCase();
		for (Client client : mClientList) {
			if (client.getmLastName().toLowerCase().contains(lastName)) {
				filterList.add(client);
			}
		}
		return filterList;
	}

	/**
	 * 
	 * Retrieves a client with the given id.
	 * 
	 * @param id
	 *            the id to look up in the database
	 * @return a client that has the matching id
	 * @throws SQLException
	 */
	public Client getClient(String id) throws SQLException {
		if (mConnection == null) {
			mConnection = DataConnection.getConnection();
		}
		Statement stmt = null;
		String query = "select * " + "from `Client` where clientId = " + id;

		try {
			stmt = mConnection.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			if (rs.next()) {
				 
				String lastName = rs.getString("lastName");
				String firstName = rs.getString("firstName");
				String midInitial = rs.getString("middleInitial");
				String address = rs.getString("streetAddress");
				String city = rs.getString("city");
				String state = rs.getString("state");
				String zipCode = rs.getString("zipcode");

				Client client = null;
				if (midInitial == null) {
					client = new Client(lastName, firstName, address, city, state, zipCode);
					client.setId(new Integer(id).toString());
					return client;

				} else {
					client = new Client(lastName, firstName, midInitial, address, city, state, zipCode);
					client.setId(new Integer(id).toString());
					return client;
				}

			}
		} catch (SQLException e) {
			System.out.println(e);
		} finally {
			if (stmt != null) {
				stmt.close();
			}
		}
		return null;
	}

	/**
	 * 
	 * Updates the data on a client
	 * 
	 * @param client
	 *            the client to update
	 * @param columnName
	 *            the column corresponding the the data being updated
	 * @param data
	 *            the replacement data to update the client
	 * @return Returns a message with success or failure.
	 */
	public String updateClient(Client client, String columnName, Object data) {

		String lastName = client.getmLastName();
		int id = Integer.parseInt(client.getId());
		String sql = "update `Client` set " + columnName + " = ?  where lastName = ? and clientId = ? ";

		PreparedStatement preparedStatement = null;
		try {
			preparedStatement = mConnection.prepareStatement(sql);

			preparedStatement.setString(1, (String) data);
			preparedStatement.setString(2, lastName);
			preparedStatement.setInt(3, id);
			preparedStatement.executeUpdate();

		} catch (SQLException e) {
			System.out.println(e);
			e.printStackTrace();
			return "Error updating item: " + e.getMessage();
		}
		return "Updated Client Successfully";

	}
}
