*****************************************************************************************
*	Ibrahim Diabate		Features implemented                                    *
*****************************************************************************************

I implemented these classes: 

1) Clients: 

	ClientDB
	Client
	ClientGUI

2) Transactions:
	Abstract Transaction
	TransactionCollection
	TransactionGUI
	Sale
	Purchase 

3) Item categories:
	ItemCategoryGUI
	addCategory(ItemCategory category) method in ItemCollection class


*****************************************************************************************
*				Tests written                                           *
*****************************************************************************************

ItemCollectionTest
ItemCategoryTest
ClientCollectionTest
TransactionCollectionTest
PurchaseTest
SaleTest
ItemTest
ClientTest


*****************************************************************************************
*				Not implemented                                         *
*****************************************************************************************
 

- executeTransaction() inside of Purchase and Sale class - program still does what is expected 

- sales tax on items being sold

- On Transaction tab -- The "Available Items" tab does not update the table right away when you sell an item
		it takes a while but if you go to the Item tab's "List Item" after selling an item, you wil be able to see the 
		table updated right away.

- Max length of 8 decimals on a commission and price

- The categories of items do not  get updated to the new category once you edit a category. - I wrote code for it but did not have time to
get to the bottom of it before submission.

	