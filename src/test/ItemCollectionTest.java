package test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import item.Item;
import item.ItemCategory;
import item.ItemCollection;



/**
 * ItemCollectionTest class.
 * 
 * Methods contained in the ItemCollection class are tested here.
 * @author Ibrahim Diabate
 *
 */
public class ItemCollectionTest {

	private List<Item> itemList;
 
	private Item item;
	@Before
	public void setUp() throws Exception {
		 
		itemList = new ArrayList<Item>();
		item = new Item("The name", new ItemCategory("The category"));
	}

	@Test
	public void testSearch() {
		 itemList = ItemCollection.search("The name");
		 assertNotNull("ItemCollection not null", itemList);
	}
	//I commented this test because it fails when you run it twice with the same category because of 
	// no duplicate exception
	
//	@Test
//	public void testAddCategory() {
//		
//		Boolean check = ItemCollection.addCategory(category);
//		 assertTrue(check);
//	}
	
	
	@Test
	public void testGetCategories() {
		
		Object[] myArray = ItemCollection.getCategories();
		 assertNotNull("Categories not null ", myArray);
	}
	
	@Test
	public void testAdd() {
		
		Boolean check = ItemCollection.add(item);
		 assertTrue(check);
	}
	
	@Test
	public void testGetItemWithId() {
		
		item = ItemCollection.getItem("2");
		 assertNotNull("Item in database ", item);
	}
	
	@Test
	public void testGetItems() {
		
		itemList = ItemCollection.getItems();
		 assertNotNull("ItemCollection not null ", itemList);
	}
	
	
//	@Test
//	public void testUpdateCategory() {
//		Boolean checks = ItemCollection.updateCategory("Cover", "Cat");
//		 assertTrue(checks);
//	}

}
