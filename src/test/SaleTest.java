package test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import client.Client;
import item.Sale;

public class SaleTest {

	Sale sale;

	@Before
	public void setUp() throws Exception {
		sale = new Sale(0.06, 1.22, "clientId", "itemId");
	}

	@Test
	public void testConstructor() {
		Sale sl = new Sale(0.06, 1.22, "clientId", "itemId");
		assertNotNull("Sale not null", sl);

	}

	@Test(expected = IllegalArgumentException.class)
	public void testNegativeCommission() {
		sale.setCommissionPaid(-5.00);

	}

	@Test
	public void testPositiveCommission() {
		sale.setCommissionPaid(5.00);

	}

	@Test(expected = IllegalArgumentException.class)
	public void testNegativePrice() {
		sale.setPrice(-99.00);

	}

	@Test
	public void testPositivePrice() {
		sale.setPrice(99.00);

	}

}
