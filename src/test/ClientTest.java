package test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import client.Client;

public class ClientTest {

	Client client;
	@Before
	public void setUp() throws Exception {
		client  = new Client ("last name", "first name", "middleIn"," streetAddress", "city", "state", "zipCode" );
	}

	@Test
	public void testConstructorWithName() {
		Client cl = new Client ("last", "first", "middleIn"," street", "city", "state", "zipCode" );
		assertNotNull("Client not null", cl);
	}

	@Test
	(expected = IllegalArgumentException.class)
	public void testConstructorWithLastNameNull() {
			client = new Client(null, "first", "middleIn"," street", "city", "state", "zipCode" );
		
	}
	@Test
	(expected = IllegalArgumentException.class)
	public void testConstructorWithFirstNameNull() {
		client = new Client("last", null, "middleIn"," street", "city", "state", "zipCode" );
		
	}
	
	@Test
	(expected = IllegalArgumentException.class)
	public void testConstructorWithStreetAddressNull() {
		client = new Client("last", "first", "middleIn", null, "city", "state", "zipCode" );
		
	}
	@Test
	(expected = IllegalArgumentException.class)
	public void testConstructorWithCityNull() {
		client = new Client("last", "first", "middleIn", "street", null, "state", "zipCode" );
		
	}
	
	@Test
	(expected = IllegalArgumentException.class)
	public void testConstructorWithStateNull() {
		client = new Client("last", "first", "middleIn", "street", "city", null, "zipCode" );
		
	}
	
	@Test
	(expected = IllegalArgumentException.class)
	public void testConstructorWithZipNull() {
		client = new Client("last", "first", "middleIn", "street", "city", "state", null);
		
	}
}
