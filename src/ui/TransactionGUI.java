package ui;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

import client.Client;
import client.ClientCollection;
import item.Item;
import item.ItemCategory;
import item.ItemCollection;
import item.Purchase;
import item.Sale;
import item.TransactionCollection;

/**
 * TransactionGUI class. This class contains the main functionalities to display
 * a user-friendly Transaction interface as well as the majority of the tasks
 * available on that UI.
 * 
 * @author Ibrahim Diabate
 * @version 2.0
 *
 */

public class TransactionGUI extends JPanel implements ActionListener, TableModelListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4126613541180627528L;

	private JButton btnSale, btnPurchase, btnSellIt, btnPurchaseIt;
	private JButton btnAvail;
	private JTextField txfCmssion, txfCond, txfName, txfDesc, txfCst;
	private JLabel lblCmssion;
	private JPanel pnlButtons, pnlContent;
	private List<Item> itemList;
	private String[] mItemColumnNames = { "name", "description", "price", "condition", "category" };

	private Object[][] mData;
	private JTable table;
	private JScrollPane scrollPane;

	private JComboBox cmbCategories;
	private JComboBox cmbClientNames;
	private JComboBox cmbItemNames;
	private List<Client> clients;
	private Object[] itemNames;
	private List<Item> items;

	/**
	 * Constructor for a transaction GUI.
	 */
	public TransactionGUI() {

		setLayout(new BorderLayout());
		itemList = getData(null);
		pnlContent = new JPanel();
		createComponents();
		setVisible(true);
		setSize(500, 500);
	}

	/**
	 * Returns the data (2d) to use in the list as well as the search panels
	 * 
	 * @param searchKey
	 * @return a List of Items
	 */
	private List<Item> getData(String searchKey) {

		if (searchKey != null)
			itemList = ItemCollection.search(searchKey);
		else
			itemList = ItemCollection.getItems();

		if (itemList != null) {
			mData = new Object[itemList.size()][mItemColumnNames.length];
			for (int i = 0; i < itemList.size(); i++) {
				mData[i][0] = itemList.get(i).getName();
				mData[i][1] = itemList.get(i).getDescription();
				mData[i][2] = itemList.get(i).getPrice();
				mData[i][3] = itemList.get(i).getCondition();
				mData[i][4] = itemList.get(i).getItemCategory().getCategory();

			}
		}

		return itemList;
	}

	private void createComponents() {

		pnlButtons = new JPanel();

		// Button to for available items
		btnAvail = new JButton("Available items");
		btnAvail.addActionListener(this);

		// List Panel

		table = new JTable(mData, mItemColumnNames);
		scrollPane = new JScrollPane(table);
		pnlContent.add(scrollPane);
		table.getModel().addTableModelListener(this);

		add(pnlContent, BorderLayout.CENTER);

		btnSale = new JButton("Sell an item");
		btnSale.addActionListener(this);

		btnPurchase = new JButton("Purchase an item");
		btnPurchase.addActionListener(this);

		pnlButtons.add(btnAvail);
		pnlButtons.add(btnSale);
		pnlButtons.add(btnPurchase);

		add(pnlButtons, BorderLayout.NORTH);

	}

	/**
	 * 
	 * Handles button action events on this Panel
	 * 
	 * @param e
	 *            the location of the event
	 * 
	 */
	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == btnAvail) {

			table = new JTable(mData, mItemColumnNames);
			table.getModel().addTableModelListener(this);
			scrollPane = new JScrollPane(table);
			pnlContent.removeAll();
			pnlContent.add(scrollPane);
			pnlContent.revalidate();
			this.repaint();

		} else if (e.getSource() == btnSale) {

			pnlContent.removeAll();
			pnlContent.add(setUpSalePanel());
			pnlContent.revalidate();
			this.repaint();
		} else if (e.getSource() == btnSellIt) {

			performSellItem();

		} else if (e.getSource() == btnPurchase) {

			pnlContent.removeAll();
			pnlContent.add(setUpPurchasePanel());
			pnlContent.revalidate();
			this.repaint();
		} else if (e.getSource() == btnPurchaseIt) {

			purchaseItem();
		}

	}

	@SuppressWarnings("rawtypes")
	/**
	 * Panel to display when user chooses to make a sale.
	 * 
	 * @return a Jpanel containing the functionalities to make a sell
	 */
	private JPanel setUpSalePanel() {

		JPanel comboPanel = new JPanel();
		comboPanel.setLayout(new GridLayout(4, 2, 6, 6));

		// Sets up combo box containing name of potential sellers
		clients = ClientCollection.getClients();
		Object[] clientLastNames = new Object[clients.size()];

		for (int i = 0; i < clients.size(); i++) {
			clientLastNames[i] = clients.get(i).getId() + "  --  " + clients.get(i).getmLastName();
		}

		if (clientLastNames != null) {
			cmbClientNames = new JComboBox(clientLastNames);
			cmbClientNames.setSelectedIndex(-1);

			comboPanel.add(new JLabel("Select a seller: "));
			comboPanel.add(cmbClientNames);
		}

		// Sets up combo box containing name of items available for sell
		items = ItemCollection.getItems();
		itemNames = new Object[items.size()];

		for (int j = 0; j < items.size(); j++) {
			itemNames[j] = items.get(j).getId() + "  --" + items.get(j).getName();
		}

		if (itemNames != null) {
			cmbItemNames = new JComboBox(itemNames);
			cmbItemNames.setSelectedIndex(-1);

			comboPanel.add(new JLabel("Select item to sell : "));
			comboPanel.add(cmbItemNames);
		}

		lblCmssion = new JLabel("Enter commission: ");
		txfCmssion = new JTextField(20);
		btnSellIt = new JButton("Sell item");
		btnSellIt.addActionListener(this);

		comboPanel.add(lblCmssion);
		comboPanel.add(txfCmssion);
		comboPanel.add(new JLabel());
		comboPanel.add(btnSellIt);
		return comboPanel;

	}

	/**
	 * Allows an item to be purchased and added to the System.
	 * 
	 * @return
	 */
	private Component setUpPurchasePanel() {

		JPanel comboPanel = new JPanel();
		comboPanel.setLayout(new GridLayout(7, 2, 6, 6));

		// Sets up combo box containing name of potential sellers
		clients = ClientCollection.getClients();
		Object[] clientLastNames = new Object[clients.size()];

		for (int i = 0; i < clients.size(); i++) {
			clientLastNames[i] = clients.get(i).getId() + "  --  " + clients.get(i).getmLastName();
		}

		if (clientLastNames != null) {
			cmbClientNames = new JComboBox(clientLastNames);
			cmbClientNames.setSelectedIndex(-1);

			comboPanel.add(new JLabel("Select a buyer: "));
			comboPanel.add(cmbClientNames);

		}

		//Combo box for categories
		Object[] categories = ItemCollection.getCategories();
		if (categories != null) {
			cmbCategories = new JComboBox(categories);
			cmbCategories.setSelectedIndex(-1);
			comboPanel.add(new JLabel("Select category:"));
			comboPanel.add(cmbCategories);

		}

		JLabel lblName = new JLabel("Enter item name: ");
		txfName = new JTextField(20);

		JLabel lblDes = new JLabel("Enter description: ");
		txfDesc = new JTextField(20);

		JLabel lblCst = new JLabel("Enter cost: ");
		txfCst = new JTextField(20);

		JLabel lblCond = new JLabel("Enter condition: ");
		txfCond = new JTextField(20);

		btnPurchaseIt = new JButton("Purchase item");
		btnPurchaseIt.addActionListener(this);

		comboPanel.add(lblName);
		comboPanel.add(txfName);
		comboPanel.add(lblDes);
		comboPanel.add(txfDesc);
		comboPanel.add(lblCst);
		comboPanel.add(txfCst);
		comboPanel.add(lblCond);
		comboPanel.add(txfCond);
		comboPanel.add(new JLabel());

		comboPanel.add(btnPurchaseIt);
		return comboPanel;
	}

	/**
	 * 
	 * Allows an item to be sold.
	 */
	private void performSellItem() {

		String commission = txfCmssion.getText();

		if (commission.length() != 0) {
			String clientId = (String) cmbClientNames.getSelectedItem();
			clientId = clientId.substring(0, 2).replaceAll("\\s", "");

			String itemId = (String) cmbItemNames.getSelectedItem();
			
			// Get the item's name from item's combo box
			String itemName = itemId.substring(5, itemId.length());
			double price = 0.0;

			for (int i = 0; i < items.size(); i++) {
				if (items.get(i).getName().equals(itemName)) {
					price = items.get(i).getPrice();
				}
			}

			// Getting the item's id from the item to sell's combo box
			itemId = itemId.substring(0, 2).replaceAll("\\s", "");

			double commissionPaid = 0.0;

			try {
				commissionPaid = Double.parseDouble(commission);
			} catch (NumberFormatException e) {
				JOptionPane.showMessageDialog(null, "Enter commission made of dollars and cents");
				txfCmssion.setFocusable(true);
				return;

			}
			Sale sale = new Sale(commissionPaid, price, clientId, itemId);

			String message = "Item sale failed";
			if (TransactionCollection.sellItem(sale)) {
				message = "Item sold";
			}
			JOptionPane.showMessageDialog(null, message);

		} else {
			JOptionPane.showMessageDialog(null, "You must enter a commission !");
			return;
		}

		txfCmssion.setText(" ");
		cmbClientNames.setSelectedIndex(-1);
		cmbItemNames.setSelectedIndex(-1);

	}

	/**
	 * purchaseItem method. Handles functionalities related to the purchase of
	 * an Item.
	 * 
	 */
	private void purchaseItem() {

		ItemCategory theCategory = new ItemCategory((String)cmbCategories.getSelectedItem());
		String cond = txfCond.getText();

//		if (cond.length() <= 100) {
//			JOptionPane.showMessageDialog(null, "You must enter a condition (0 to 100) characters long");
//			txfDesc.setFocusable(true);
//			return;

//		} else 
			if (cond.length() != 0) {
			String clientId = (String) cmbClientNames.getSelectedItem();
			clientId = clientId.substring(0, 2).replaceAll("\\s", "");

			double theCost = 0.0;
			int theClientId = Integer.parseInt(clientId);
			
			// Will go into purchase table
			Purchase purchase = new Purchase(theCost, cond, theClientId);

			String message = "Item purchase process failed";
//			if (TransactionCollection.purchaseItem(purchase)) {
//				message = "Item purchased";
//			} 
			
			//Getting data necessary to construct an Item object from the purchased Item
			String iName = txfName.getText();
			String iDesc = txfDesc.getText();
			String thePrice = txfCst.getText();
			double iPrice = Double.parseDouble(thePrice);
			String iCond = txfCond.getText();
			
			// Will go into Item table
			Item purchasedItem = new Item (iName, iDesc, iPrice, iCond, theCategory);
			
			if (ItemCollection.add(purchasedItem)) {
				message = "Purchased item added to Item database";
			}
			JOptionPane.showMessageDialog(null, message);

		}
		
		
		txfName.setText("");
		txfDesc.setText("");
		txfCst.setText("");
		txfCond.setText("");

		cmbClientNames.setSelectedIndex(-1);
	    cmbCategories.setSelectedIndex(-1);

	}

	/**
	 * 
	 * Listens to the cell changes on the table.
	 * 
	 * @param e
	 *            the event listener of the table
	 */
	@Override
	public void tableChanged(TableModelEvent e) {

		int row = e.getFirstRow();
		int column = e.getColumn();
		TableModel model = (TableModel) e.getSource();
		String columnName = model.getColumnName(column);
		Object data = model.getValueAt(row, column);
		if (data != null && ((String) data).length() != 0) {
			Item item = itemList.get(row);
			if (!ItemCollection.update(item, columnName, data)) {
				JOptionPane.showMessageDialog(null, "Update failed");
			}
		}

	}

}
