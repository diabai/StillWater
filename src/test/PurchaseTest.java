package test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import client.Client;
import item.Purchase;

public class PurchaseTest {

	private Purchase purch;

	@Before
	public void setUp() throws Exception {
		purch = new Purchase(1.00, "cond", 5);

	}


	@Test
	public void testConstructor() {

		purch = new Purchase(1.00, "cond", 5);
		assertNotNull("Purchase not null", purch);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testSetCostNegative() {
		
		purch.setCost(-0.22);

	}
	@Test
	public void testSetCost() {

		purch.setCost(89.6666);
	}


	@Test(expected = IllegalArgumentException.class)
	public void testSetConditionNull() {

		purch.setCondition(null);
	}


	@Test
	public void testSetCondition() {

		purch.setCondition("New");
	}

	@Test(expected = IllegalArgumentException.class)
	public void testSetConditionOver100Char() {

		purch.setCondition("4dakfjdskjalfkdjakldfajlsdkfjaldskjflsdkjlkfjdslkfjalkfajdslfkjadslfkjad"
				+ "slfkjadslfkjadslfkdjslfdkafjldskfjaldskfjadslkjkajsdlfkjadslfkj");
	}


}
