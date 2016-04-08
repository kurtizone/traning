package org.gk_software.training.task.model;

import java.sql.Timestamp;

public class Encashment {
	
	private Integer money;
	private Timestamp date;
	
	public Integer getMoney() {
		return money;
	}
	public void setMoney(Integer money) {
		this.money = money;
	}
	public Timestamp getDate() {
		return date;
	}
	public void setDate(Timestamp date) {
		this.date = date;
	}
	@Override
	public String toString() {
		return "Encashment [money=" + money + ", date=" + date + "]\n";
	}
	
	
}
