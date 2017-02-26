package test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
 
import item.ItemCategory;

public class ItemCategoryTest {

	@Before
	public void setUp() throws Exception {
	}


	@Test
	public void testConstructor() {
		ItemCategory category = new ItemCategory("Item category");
		assertNotNull("ItemCategory Not null", category);
	}
}
