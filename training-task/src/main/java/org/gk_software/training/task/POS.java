package org.gk_software.training.task;

import java.sql.Timestamp;
import java.util.List;

import org.gk_software.training.task.model.Item;

public interface POS {

	public int getCurrentMoneyAmount(); // shows all money held in POS

	public List<Item> getListOfAvailbaleSaleItems(); // Shows all items can be
														// sold.

	public void sellItem(Item item); // take item away, receive some money
										// (depending on item price)

	public void returnSoldItem(Item item); // take item back, give away some
											// money (depending on item price)

	public void encashment(); // Take away all money from POS, leaving minimal
								// amount for change

	public void storeItems(List<Item> items); // Receive a list of goods, and
												// store them for further
												// selling

	public String report(Timestamp from, Timestamp to); // Give information of
														// sold items, revenue,
														// encashment.

}
