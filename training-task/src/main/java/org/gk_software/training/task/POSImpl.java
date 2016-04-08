package org.gk_software.training.task;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import org.gk_software.training.task.model.Encashment;
import org.gk_software.training.task.model.Item;

public class POSImpl extends POSAbstract implements POS {

	public POSImpl(int i, List<Item> items) {
		super(i, items);
	}

	public POSImpl() {
	}

	public int getCurrentMoneyAmount() {
		return getAmountMoney();
	}

	public List<Item> getListOfAvailbaleSaleItems() {
		return getItems();
	}

	public void sellItem(Item item) {
		if (!items.contains(item)) {
			throw new NullPointerException();
		}
		amountMoney += item.getPrice();
		items.remove(item);
		soldItems.add(item);
		item.setSoldDate(new Timestamp(new Date().getTime()));
	}

	public void returnSoldItem(Item item) {
		if(!soldItems.contains(item) && item.getSoldDate() == null) {
			throw new NullPointerException();
		}
		amountMoney -= item.getPrice();
		soldItems.remove(item);
		items.add(item);
		item.setSoldDate(null);
	}

	public void encashment() {
		Encashment encashment = new Encashment();
		encashment.setMoney(amountMoney - MIN_AMOUNT);
		encashment.setDate(new Timestamp(new Date().getTime()));
		amountMoney = MIN_AMOUNT;
		encashments.add(encashment);
	}

	public void storeItems(List<Item> items) {
		this.items.addAll(items);
	}

	public String report(Timestamp from, Timestamp to) {
		StringBuilder builder = new StringBuilder();
		int interProfit = 0;
		builder.append("Report\nFrom: " + from + "\nTo: " + to)
				.append("\n-------------------------------------\n" + "Sold items: \n");

		for (Item item : soldItems) {
			if (item.getSoldDate().getTime() >= from.getTime() && item.getSoldDate().getTime() <= to.getTime()) {
				builder.append(item);
			}
		}

		builder.append("\n-------------------------------------\n").append("Encashments:\n");

		for (Encashment encashment : encashments) {
			if (encashment.getDate().getTime() >= from.getTime() && encashment.getDate().getTime() <= to.getTime()) {
				builder.append(encashment);
				interProfit += encashment.getMoney();
			}
		}

		builder.append("\n-------------------------------------\n").append("Profit = " + interProfit);

		return builder.toString();
	}

}
