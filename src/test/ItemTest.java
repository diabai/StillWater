package test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import item.Item;
import item.ItemCategory;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import static org.hamcrest.CoreMatchers.is;
public class ItemTest {

	private Item myItem;
	@Before
	public void setUp()  {
		myItem = new Item ("Some item name", new ItemCategory("Some category"));
		
	}
	
	@Test
	(expected = IllegalArgumentException.class)
	public void testConstructorWithNameNull() {
		myItem = new Item(null,  new ItemCategory("some cateogry"));
		
	}
	
	@Test
	
	public void testFirstConstructor() {
			myItem = new Item("name", "desc", 1.00, "con",  new ItemCategory("some cateogry"));
		
	}
	

	@Test
	public void testConstructorWithName() {
		Item item = new Item("some item", new ItemCategory("some category"));
		assertNotNull("Item Not null", item);
	}
	
	

	@Test(expected = IllegalArgumentException.class)
	public void testConstructorWithNameLessThan3() {
		myItem = new Item("it", new ItemCategory("some category"));
		
	}
	
	@Test
	public void testSetPrice() {
		
		myItem.setPrice(5.00);
	 
		
	}


	@Test(expected = IllegalArgumentException.class)
	public void testSetPriceNegative() {
		
		myItem.setPrice(-5.00);
		
	}
	
}