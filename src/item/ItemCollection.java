package item;

import data.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

/**
 * Item Collection class.
 * 
 * @author mmupa
 * @author Ibrahim Diabate
 * @version 2.0
 *
 */
public class ItemCollection {

	private static ItemDB mItemDB;
	private static Connection mConnection;

	/**
	 * Return a list of items with the matching name.
	 * 
	 * @param name
	 *            item to look for
	 * @return a list of items that match
	 */
	public static List<Item> search(String name) {
		List<Item> list = new ArrayList<Item>();
		if (mItemDB == null) {
			mItemDB = new ItemDB();
		}
		try {
			return mItemDB.getItems(name);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	// TODO: Done.
	/**
	 *
	 * Adds a new itemCategory to the database
	 * 
	 * @param category
	 *            the category to be added
	 * @return true if category was successfully added, otherwise false
	 */
	public static boolean addCategory(ItemCategory category) {

		Boolean result = true;
		String sql = "insert into ItemCategory(category) values " + "(?); ";

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
			preparedStatement.setString(1, category.getCategory());
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			result = false;
		}
		return result;

	}

	/**
	 * 
	 * Updates a category
	 * 
	 * @param newCategory
	 *            the name of the replacement category
	 * @param theCategory
	 *            the name of the category to edit
	 * @return true if category was successfully edited, otherwise false
	 */

	public static boolean updateCategory(String newCategory, String theCategory) {
		if (mItemDB == null) {
			mItemDB = new ItemDB();
		}
 
		String message = mItemDB.updateCategory(newCategory, theCategory);
		if (message.startsWith("Error updating item: ")) {
			return false;
		}
		JOptionPane.showMessageDialog(null, "Category Successfully Added!");
		return true;
	}

	/**
	 * Modify the particular column of the item with the given data.
	 * 
	 * @param item
	 *            Item to modify
	 * @param column
	 *            the field in the table to modify
	 * @param data
	 *            the value for the field
	 * @return true or false
	 */
	public static boolean update(Item item, String column, Object data) {
		if (mItemDB == null) {
			mItemDB = new ItemDB();
		}
		// Don't let the users change name. //Removed restrictions on category
		if (!(column.equals("name"))) {
			String message = mItemDB.updateItem(item, column, data);
			if (message.startsWith("Error updating item: ")) {
				return false;
			}
			return true;
		}
		return false;
	}

	/**
	 * Returns all item categories available.
	 * 
	 * @return an array of categories.
	 */
	public static Object[] getCategories() {
		if (mItemDB == null) {
			mItemDB = new ItemDB();
		}

		try {
			return mItemDB.getItemCategories();
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}

	}

	/**
	 * Adds a new item to the data.
	 * 
	 * @param item
	 * @return true or false
	 */
	public static boolean add(Item item) {
		if (mItemDB == null) {
			mItemDB = new ItemDB();
		}

		String message = mItemDB.addItem(item);
		if (message.startsWith("Error adding item:")) {
			return false;
		}
		return true;
	}

	/**
	 * 
	 * Return a item with the given id, null otherwise.
	 * 
	 * @param id
	 *            item to look for
	 * @return item
	 */
	public static Item getItem(String id) {
		if (mItemDB == null) {
			mItemDB = new ItemDB();
		}
		try {
			return mItemDB.getItem(id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 
	 * Return all items in the list, null otherwise.
	 * 
	 * @param id
	 *            item to look for
	 * @return item
	 */
	public static List<Item> getItems() {
		if (mItemDB == null) {
			mItemDB = new ItemDB();
		}
		try {
			return mItemDB.getItems();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
}
