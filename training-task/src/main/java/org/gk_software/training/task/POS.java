package org.gk_software.training.task;

import java.sql.Timestamp;
import java.util.List;

import org.gk_software.training.task.model.Item;

public interface POS {

	/**
	 * Shows all money held in POS
	 * 
	 * @return Integer money of this POS
	 */
	public int getCurrentMoneyAmount();

	/**
	 * Shows all items can be sold.
	 * 
	 * @return List of items
	 */
	public List<Item> getListOfAvailbaleSaleItems();

	/**
	 * take item away, receive some money (depending on item price)
	 * 
	 * @param item
	 *            - which will be sold
	 */
	public void sellItem(Item item);

	/**
	 * take item back, give away some money (depending on item price)
	 * 
	 * @param item
	 *            - which will be returned
	 */
	public void returnSoldItem(Item item);

	/**
	 * Take away all money from POS, leaving minimal amount for change
	 */
	public void encashment();

	/**
	 * Receive a list of goods, and store them for further selling
	 * 
	 * @param items
	 */
	public void storeItems(List<Item> items);

	/**
	 * Give information of sold items, revenue, encashment.
	 * 
	 * @param from
	 *            - timestamp start date;
	 * @param to
	 *            - timestamp end date;
	 * @return String report
	 */
	public String report(Timestamp from, Timestamp to);
	
}
