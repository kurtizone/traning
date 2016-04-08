package org.gk_software.training.task;

import java.util.LinkedList;
import java.util.List;

import org.gk_software.training.task.model.Encashment;
import org.gk_software.training.task.model.Item;

public abstract class POSAbstract {
	
	protected final static Integer MIN_AMOUNT = 600;
	
	protected Integer amountMoney;
	protected List<Item> items;
	protected List<Item> soldItems;
	protected List<Encashment> encashments;
	
	public POSAbstract() {
		amountMoney = MIN_AMOUNT;
		items = new LinkedList<Item>();
		soldItems = new LinkedList<Item>();
		encashments = new LinkedList<Encashment>();
	}
	
	public POSAbstract(Integer amoumtMoney, List<Item> items) {
		this.amountMoney = amoumtMoney;
		this.items = items;
		soldItems = new LinkedList<Item>();
		encashments = new LinkedList<Encashment>();
	}
	
	public Integer getAmountMoney() {
		return amountMoney;
	}

	public void setAmountMoney(Integer amountMoney) {
		this.amountMoney = amountMoney;
	}

	public List<Item> getItems() {
		return items;
	}

	public void setItems(List<Item> items) {
		this.items = items;
	}
	
	public List<Item> getSoldItems() {
		return soldItems;
	}

	public List<Encashment> getEncashments() {
		return encashments;
	}

	
	
	
}
