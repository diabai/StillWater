package test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import item.Sale;
import item.TransactionCollection;

/**
 * Test class for TransactionCollectionTest
 * @author Ibrahim Diabate
 *
 */
public class TransactionCollectionTest {

	Sale theSale;
	@Before
	public void setUp() throws Exception {

		theSale = new Sale(2.0,3.0, "2", "2");
	}

	@Test
	public void testSellItem() {
		Boolean check = TransactionCollection.sellItem(theSale);
		assertTrue(check);
	}

}
