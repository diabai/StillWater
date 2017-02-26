package ui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;

import item.Item;
import item.ItemCategory;
import item.ItemCollection;

/***
 * 
 * ItemCategoryGUI class. 
 * This class contains the main functions to display a user-friendly Category interface
 * as well as the majority of the tasks available on that UI.
 * 
 * @author Ibrahim Diabate
 * 
 * @version 2.0
 *
 */
public class ItemCategoryGUI extends JPanel implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1875699406679076341L;

	
	private JButton btnEdit, btnAdd, btnList;
	private JPanel pnlButtons, pnlContent;
	private JPanel pnlAddCategory;
	private JLabel lblTitle;;
	private JTextField txfTitle;
	private JButton btnTitleAdd;

	private JTextField txfEditTitle;
	private JButton btnTitleEdit;

	private JList categoryList;

	private JScrollPane scrollPane;
	private JLabel lblSetEdit;
	private JComboBox cmbCategories;
 

	private static String selectedCategory;
	 

	public ItemCategoryGUI() {
		setLayout(new BorderLayout());

		createComponents();
	 
		setVisible(true);
		setSize(500, 500);

	}

	/**
	 * Create the two panels to add to this GUI. One to add a category, one to
	 * edit a category
	 * 
	 */
	private void createComponents() {

		// A button panel at the top for add and edit item
		pnlButtons = new JPanel();
		
		// List all categories
		btnList = new JButton("Category List");
		btnList.addActionListener(this);

		// Add category button
		btnAdd = new JButton("Add Category");
		btnAdd.addActionListener(this);
		
		// Edit category button
		btnEdit = new JButton("Edit Category");
		btnEdit.addActionListener(this);

		pnlButtons.add(btnList);
		pnlButtons.add(btnAdd);
		pnlButtons.add(btnEdit);

		//Adds Edit and Add category buttons to top of this JPanel
		add(pnlButtons, BorderLayout.NORTH);

		// List panel
		pnlContent = new JPanel();
		categoryList = new JList(ItemCollection.getCategories());
		categoryList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		categoryList.setLayoutOrientation(JList.VERTICAL);
		categoryList.setVisibleRowCount(5);

		scrollPane = new JScrollPane(categoryList);
		scrollPane.setPreferredSize(new Dimension(250, 250));
		pnlContent.add(scrollPane);

		add(pnlContent, BorderLayout.CENTER);

		// Add category panel -- When Add category clicked
		pnlAddCategory = new JPanel();
		lblTitle = new JLabel("Enter new category: ");
		txfTitle = new JTextField(25);
		btnTitleAdd = new JButton("Add");
		btnTitleAdd.addActionListener(this);
		pnlAddCategory.add(lblTitle);
		pnlAddCategory.add(txfTitle);
		pnlAddCategory.add(btnTitleAdd);


	}

	@SuppressWarnings("unchecked")
	/**
	 * Method to set up the Edit category panel 
	 * @return
	 */
	private JPanel setUpEditCategoryPanel() {
		
		// Get categories to display in the combo box
				JPanel comboPanel = new JPanel();
				comboPanel.setLayout(new GridLayout(3, 2,6,6));
				Object[] categories = ItemCollection.getCategories();
				if (categories != null) {
					cmbCategories = new JComboBox(categories);
					cmbCategories.setSelectedIndex(0);
					 
					comboPanel.add(new JLabel("Select category to edit: "));
					comboPanel.add(cmbCategories);
				
				}
				
				lblSetEdit = new JLabel("Enter replacement category: ");
				txfEditTitle = new JTextField(20);
				btnTitleEdit = new JButton("Update category");
				btnTitleEdit.addActionListener(this);
				
				comboPanel.add(lblSetEdit);
				comboPanel.add(txfEditTitle);
				comboPanel.add(new JLabel()); 
				comboPanel.add(btnTitleEdit);
				return comboPanel;
		
	}

	/***
	 * This method will handle what happens when this GUI's buttons get
	 * clicked.
	 * 
	 * @param e an actionEvent that allows certain tasks to be performed depending on what the event is
	 *            
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == btnList) { // List Category is clicked

			pnlContent.removeAll();
			//Puts all categories on a JList
			categoryList = new JList(ItemCollection.getCategories());           
			categoryList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			categoryList.setLayoutOrientation(JList.VERTICAL);
			categoryList.setVisibleRowCount(5);		
			scrollPane = new JScrollPane(categoryList);
			scrollPane.setPreferredSize(new Dimension(250, 250));
			pnlContent.add(scrollPane);
			pnlContent.revalidate();
			this.repaint();

		} else if (e.getSource() == btnAdd) {  

			pnlContent.removeAll();
			pnlContent.add(pnlAddCategory);
			pnlContent.revalidate();
			this.repaint();

		} else if (e.getSource() == btnTitleAdd) { 
			
			
			performAddCategory();

		} else if (e.getSource() == btnEdit) { 

			pnlContent.removeAll();
			pnlContent.add(setUpEditCategoryPanel());
			pnlContent.revalidate();
			this.repaint();
			
		} else if (e.getSource() == btnTitleEdit) {  

			selectedCategory = (String) cmbCategories.getSelectedItem() ;
			String newCategory = txfEditTitle.getText();
			
			List<Item> myList = ItemCollection.search(selectedCategory);
			for (Item itmn: myList) {
				
				ItemCollection.update(itmn, "category", newCategory);
			}
			
			ItemCollection.updateCategory(newCategory ,selectedCategory);
			

			txfEditTitle.setText(" ");
			cmbCategories.setSelectedIndex(-1);
		}
	}

	/**
	 * performAddCategory method.
	 * Allows a new Item category to be added to the system.
	 */
	private void performAddCategory() {

		String name = txfTitle.getText();
		if (name.length() == 0) {
			JOptionPane.showMessageDialog(null, "Enter an item category");
			txfTitle.setFocusable(true);
			return;
		}
		
		ItemCategory category = new ItemCategory (name);
		String message = "Category add failed";
		
		if (ItemCollection.addCategory(category)) {
			message = "Category added";
		}
		
		JOptionPane.showMessageDialog(null, message);
		
		// Clears the text field where category name was entered
		if (txfTitle.getText().length()!=0) {
		txfTitle.setText("");
		}
	}

}
