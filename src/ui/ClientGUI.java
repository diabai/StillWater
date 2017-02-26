package ui;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;
import client.Client;
import client.ClientCollection;

/**
 * 
 * ClientGUI class.
 * 
 * This class contains a user friendly Client GUI as well as the functionalities
 * available on it.
 * 
 * @author Ibrahim Diabate
 * @version 2.0
 *
 */
public class ClientGUI extends JPanel implements ActionListener, TableModelListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7065682892584933756L;

	private JButton btnList, btnSearch, btnAdd;
	private JPanel pnlButtons, pnlContent;
	private List<Client> mList;
	private String[] mClientColumnNames = { "clientId", "lastName", "firstName", "middleInitial", "streetAddress",
			"city", "state", "zipCode" };

	private Object[][] mData;
	private JTable table;
	private JScrollPane scrollPane;
	private JPanel pnlSearch;

	private JTextField txfTitle;
	private JButton btnTitleSearch;

	private JPanel pnlAdd;
	private JLabel[] txfLabel = new JLabel[8];
	private JTextField[] txfField = new JTextField[8];

	private JButton btnAddClient;

	private JRadioButton btnByName;
	private JRadioButton btnById;

	/**
	 * Default constructor
	 */
	public ClientGUI() {

		setLayout(new BorderLayout());

		mList = getData(null);
		createComponents();
		setVisible(true);
		setSize(500, 500);
	}

	/**
	 * Returns the data (2d) to use in the list as well as the search panels
	 * 
	 * @param searchKey
	 * @return
	 */
	private List<Client> getData(String searchKey) {

		if (searchKey != null && btnById.isSelected()) { // If user is searching
															// by Id

			mList = new ArrayList<Client>();
			mList.add(ClientCollection.getClient(searchKey));

		} else if (searchKey != null) {
			mList = ClientCollection.search(searchKey);

		} else {
			mList = ClientCollection.getClients();
		}

		if (mList != null) {
			mData = new Object[mList.size()][mClientColumnNames.length];
			for (int i = 0; i < mList.size(); i++) {

				mData[i][0] = mList.get(i).getId();
				mData[i][1] = mList.get(i).getmLastName();
				mData[i][2] = mList.get(i).getmFirstName();
				mData[i][3] = mList.get(i).getmMiddleInitial();
				mData[i][4] = mList.get(i).getmStreetAddress();
				mData[i][5] = mList.get(i).getmCity();
				mData[i][6] = mList.get(i).getmState();
				mData[i][7] = mList.get(i).getmZipCode();

			}
		}

		return mList;
	}

	private void createComponents() {

		pnlButtons = new JPanel();
		btnList = new JButton("Client List");
		btnList.addActionListener(this);

		btnSearch = new JButton("Client Search");
		btnSearch.addActionListener(this);

		btnAdd = new JButton("Add Client");
		btnAdd.addActionListener(this);

		pnlButtons.add(btnList);
		pnlButtons.add(btnSearch);

		pnlButtons.add(btnAdd);
		add(pnlButtons, BorderLayout.NORTH);

		// Set up table
		pnlContent = new JPanel();
		table = new JTable(mData, mClientColumnNames);
		scrollPane = new JScrollPane(table);
		pnlContent.add(scrollPane);
		table.getModel().addTableModelListener(this);

		// Search Panel
		pnlSearch = new JPanel(new GridLayout(2, 2, 5, 5));
		txfTitle = new JTextField(15);

		btnByName = new JRadioButton("Search by last name");
		btnByName.setSelected(true);
		btnById = new JRadioButton("Search by client Id");
		ButtonGroup bg = new ButtonGroup();
		bg.add(btnByName);
		bg.add(btnById);

		btnTitleSearch = new JButton("Search");
		btnTitleSearch.addActionListener(this);

		pnlSearch.add(txfTitle);
		pnlSearch.add(btnByName);
		pnlSearch.add(btnById);

		pnlSearch.add(new JLabel());
		pnlSearch.add(btnTitleSearch);

		// Add Panel
		pnlAdd = new JPanel();
		pnlAdd.setLayout(new GridLayout(8, 2, 5, 5));
		String labelNames[] = { "Enter last name: ", "Enter first name: ", "Enter middle initial: ",
				"Enter street address: ", "Enter city: ", "Enter state: ", "Enter zip code: " };
		for (int i = 0; i < labelNames.length; i++) {

			txfLabel[i] = new JLabel(labelNames[i]);
			txfField[i] = new JTextField(15);
			pnlAdd.add(txfLabel[i]);
			pnlAdd.add(txfField[i]);

		}

		btnAddClient = new JButton(" Add ");
		btnAddClient.addActionListener(this);
		pnlAdd.add(new JLabel()); // I used this blank label to skew
									// btnAddClient under the text fields
									// because i am using a GridLayout
									// with 8 rows and 2 columns
		pnlAdd.add(btnAddClient);

		add(pnlContent, BorderLayout.CENTER);

	}

	/**
	 * ActionPerformed method.
	 * 
	 * @param e
	 *            Keeps track of location of ActionEvent and GUI behavior
	 *            depending on what event is set.
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		
		if (e.getSource() == btnList) {
			mList = getData(null);
			pnlContent.removeAll();
			table = new JTable(mData, mClientColumnNames);
			table.getModel().addTableModelListener(this);
			scrollPane = new JScrollPane(table);
			pnlContent.add(scrollPane);
			pnlContent.revalidate();
			this.repaint();

		} else if (e.getSource() == btnSearch) {
			pnlContent.removeAll();

			pnlContent.add(pnlSearch);
			pnlContent.revalidate();
			this.repaint();
		} else if (e.getSource() == btnAdd) {
			pnlContent.removeAll();
			pnlContent.add(pnlAdd);
			pnlContent.revalidate();
			this.repaint();

		} else if (e.getSource() == btnTitleSearch) {
 
			String title = txfTitle.getText();
			if (title.length() > 0) {
				mList = getData(title);
				pnlContent.removeAll();
				btnByName.setSelected(true);
				table = new JTable(mData, mClientColumnNames);
				table.getModel().addTableModelListener(this);
				scrollPane = new JScrollPane(table);
				pnlContent.add(scrollPane);
				pnlContent.revalidate();
				this.repaint();
				txfTitle.setText("");
			}

		} else if (e.getSource() == btnAddClient) {
			performAddClient();
		}

	}

	/**
	 * performAddClient method.
	 * 
	 * Allows a client to be added to the system, only middle initial is not
	 * required
	 */
	private void performAddClient() {

		String lastName = txfField[0].getText();
		String firstName = txfField[1].getText();
		String midInitial = txfField[2].getText();
		String streetAddress = txfField[3].getText();
		String city = txfField[4].getText();
		String state = txfField[5].getText();
		String zipCode = txfField[6].getText();

		// If any of the following fiels is null, get out of this method.
		if (!checkFieldsContent(lastName, firstName, midInitial, streetAddress, city, state, zipCode)) {
			return;
		} else {

			Client client;

			if (midInitial.length() == 0) {
				client = new Client(lastName, firstName, streetAddress, city, state, zipCode);
			} else {
				client = new Client(lastName, firstName, midInitial, streetAddress, city, state, zipCode);
			}
			String message = "Client add failed";
			if (ClientCollection.addClient(client)) {
				message = "Client added";
			}
			JOptionPane.showMessageDialog(null, message);

			// Clear all text fields.
			for (int i = 0; i < txfField.length - 1; i++) {
				if (txfField[i].getText().length() != 0) {
					txfField[i].setText("");

				}
			}
		}
	}

	/**
	 * Checks for validity of client input
	 * 
	 * @param lastName
	 *            last name of client
	 * @param firstName
	 *            first name of client
	 * @param midInitial
	 *            middle initial of client
	 * @param streetAddress
	 *            Street address of client
	 * @param city
	 *            City of client
	 * @param state
	 *            State of client
	 * @param zipCode
	 *            Zip code of client
	 * @return true if none of the parameters is null otherwise false
	 */
	private boolean checkFieldsContent(String lastName, String firstName, String midInitial, String streetAddress,
			String city, String state, String zipCode) {

		Boolean isFilled = false;
		if (lastName.length() == 0) {
			JOptionPane.showMessageDialog(null, "Enter the client's last name");
			txfField[0].setFocusable(true);
			return isFilled;
		} else if (firstName.length() == 0) {
			JOptionPane.showMessageDialog(null, "Enter the client's first name");
			txfField[1].setFocusable(true);
			return isFilled;
		} else if (streetAddress.length() == 0) {
			JOptionPane.showMessageDialog(null, "Enter the client's street's address");
			txfField[2].setFocusable(true);
			return isFilled;
		} else if (city.length() == 0) {
			JOptionPane.showMessageDialog(null, "Enter the client's city");
			txfField[3].setFocusable(true);
			return isFilled;
		} else if (state.length() == 0) {
			JOptionPane.showMessageDialog(null, "Enter the client's state");
			txfField[4].setFocusable(true);
			return isFilled;
		} else if (zipCode.length() == 0) {
			JOptionPane.showMessageDialog(null, "Enter the client's zip code");
			txfField[5].setFocusable(true);
			return isFilled;
		}

		return isFilled = true;

	}

	/**
	 * Listen to the cell changes on the table.
	 */
	@Override
	public void tableChanged(TableModelEvent e) {
		int row = e.getFirstRow();
		int column = e.getColumn();
		TableModel model = (TableModel) e.getSource();
		String columnName = model.getColumnName(column);
		Object data = model.getValueAt(row, column);
		if (data != null && ((String) data).length() != 0) {
			Client client = mList.get(row);
			if (!ClientCollection.update(client, columnName, data)) {
				JOptionPane.showMessageDialog(null, "Update failed");
			}
		}

	}

}
