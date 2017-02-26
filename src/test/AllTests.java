package test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import client.Client;
import item.Purchase;
import item.Sale;

@RunWith(Suite.class)
@SuiteClasses({ DataConnectionTest.class, ItemTest.class, ClientTest.class, PurchaseTest.class, SaleTest.class,
		ItemCategoryTest.class, ClientCollectionTest.class, ItemCollectionTest.class, TransactionCollectionTest.class })
public class AllTests {

}
