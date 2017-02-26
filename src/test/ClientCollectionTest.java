package test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import client.Client;
import client.ClientCollection;

public class ClientCollectionTest {

	List<Client> clientList;
	Client client;
	@Before
	public void setUp() throws Exception {
		clientList = new ArrayList<Client>();
		client = new Client("Name", "first", "I", "Street", "city", "WA", "zip");

		
	}

	@Test
	public void testGetClients() {
		  
		clientList = ClientCollection.getClients();
		assertNotNull("ClientCollection Not Null", clientList);
	}
 
	
	@Test
	public void testGetClient() {
		  
		client = ClientCollection.getClient("1");
		assertNotNull("ClientCollection Not Null", client);
	}
	
	
	@Test
	public void testAddClient() {
		  
		Boolean check = ClientCollection.addClient(client);
		assertTrue(check);
	}
	
	@Test
	public void testSearchClient() {
		  
		clientList =  ClientCollection.search("Name");
		assertNotNull("ClientCollection contains Name", clientList);
	}

//	@Test
//	public void testUpdateClient() {
//		  
//		Boolean checker =  ClientCollection.update(client, "1", "Ibrahim");
//		assertTrue("Column name updated", checker);
//	}

}
