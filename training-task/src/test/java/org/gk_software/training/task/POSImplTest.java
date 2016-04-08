package org.gk_software.training.task;

import java.sql.Timestamp;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import org.gk_software.training.task.model.Item;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

public class POSImplTest {

	private POSAbstract posAbst;
	private POSAbstract posAbstWithFields;
	private POS pos;
	private POS posWithFields;
	private List<Item> items;

	@Before
	public void init() {
		posAbst = new POSImpl();
		items = new LinkedList<Item>();
		items.add(new Item("coat", 350));
		items.add(new Item("t-shirt", 150));
		items.add(new Item("shirt", 200));
		posAbstWithFields = new POSImpl(600, items);
		posWithFields = new POSImpl(600, items);
	}

	
	@Test
	public void posGetCurrentAmoutMoneyNotNull() {
		Assert.assertNotNull(posWithFields.getCurrentMoneyAmount());
	}

	@Test
	public void posGetCurrentAmoutMoneyEquals() {
		Assert.assertEquals(POSAbstract.MIN_AMOUNT.intValue(), posWithFields.getCurrentMoneyAmount());
	}

	@Test
	public void posGetListOfAvaibleSaleItemsNotNull() {
		Assert.assertNotNull(posWithFields.getListOfAvailbaleSaleItems());
	}

	@Test
	public void posGetListOfAvaibleSaleItemsEquals() {
		Assert.assertEquals(items, posWithFields.getListOfAvailbaleSaleItems());
	}

	@Test
	public void posSellItemAmountOfMoney() {
		Item item = new Item("shirt", 200);
		Integer money = posWithFields.getCurrentMoneyAmount();
		posWithFields.sellItem(item);
		Assert.assertEquals(item.getPrice().intValue(), (posWithFields.getCurrentMoneyAmount() - money));
	}

	@Test(expected = NullPointerException.class)
	public void posSellItemNotExists() {
		Item item = new Item("shorts", 220);
		posWithFields.sellItem(item);
	}

	@Test
	public void posSellItemContains() {
		Item item = new Item("shirt", 200);
		posWithFields.sellItem(item);
		posAbst = (POSAbstract) posWithFields;
		Assert.assertTrue(posAbst.getSoldItems().contains(item));
	}

	@Test
	public void posSellItemNotContains() {
		Item item = new Item("shirt", 200);
		posWithFields.sellItem(item);
		Assert.assertFalse(posWithFields.getListOfAvailbaleSaleItems().contains(item));
	}

	@Test
	public void posReturnSoldItemAmountOfMoney() {
		Item item = new Item("shirt", 200);
		posWithFields.sellItem(item);
		Integer money = posWithFields.getCurrentMoneyAmount();
		posWithFields.returnSoldItem(item);
		Assert.assertEquals(item.getPrice().intValue(), money - posWithFields.getCurrentMoneyAmount());
	}

	@Test(expected = NullPointerException.class)
	public void posReturnSoldItemNotExist() {
		Item item = new Item("shirt", 200);
		posWithFields.sellItem(item);
		posWithFields.returnSoldItem(new Item("shorts", 220));
	}

	@Test
	public void posReturnSoldItemContains() {
		Item item = new Item("shirt", 200);
		posWithFields.sellItem(item);
		posWithFields.returnSoldItem(item);
		Assert.assertTrue(posWithFields.getListOfAvailbaleSaleItems().contains(item));
	}

	@Test
	public void posReturnSoldItemNotContains() {
		Item item = new Item("shirt", 200);
		posWithFields.sellItem(item);
		posWithFields.returnSoldItem(item);
		posAbst = (POSAbstract) posWithFields;
		Assert.assertFalse(posAbst.getSoldItems().contains(item));
	}

	@Test
	public void posEncashmentAmountNotNullAndNotTheSame() {
		Item item = new Item("shirt", 200);
		posWithFields.sellItem(item);
		int money = posWithFields.getCurrentMoneyAmount();
		posWithFields.encashment();
		Assert.assertNotEquals(money, posWithFields.getCurrentMoneyAmount());
		Assert.assertTrue(posWithFields.getCurrentMoneyAmount() > 0);
	}

	@Test
	public void posEncashmentEqualsMinAmount() {
		Item item = new Item("shirt", 200);
		posWithFields.sellItem(item);
		posWithFields.encashment();
		Assert.assertEquals(POSAbstract.MIN_AMOUNT.intValue(), posWithFields.getCurrentMoneyAmount());
	}

	@Test
	public void posStoreItemsContainsAll() {
		List<Item> newItems = new LinkedList<Item>();
		newItems.add(new Item("ball", 200));
		newItems.add(new Item("baseball", 300));
		posWithFields.storeItems(newItems);
		Assert.assertTrue(posWithFields.getListOfAvailbaleSaleItems().containsAll(newItems));
	}

	/*
	 * Test for reporting of sold items, revenue, encashment. Added Date param
	 * to date and created Encashments class
	 */
	@Test
	public void posSoldItemDateNotNull() {
		Item item = new Item("shirt", 200);
		posWithFields.sellItem(item);
		Assert.assertNotNull(item.getSoldDate());
	}

	@Test
	public void posEncashmentListNotNull() {
		Item item = new Item("shirt", 200);

		posWithFields.sellItem(item);
		posWithFields.encashment();
		posAbst = (POSAbstract) posWithFields;
		Assert.assertNotEquals(0, posAbst.getEncashments().size());
	}

	@Test
	public void posReportExpectedValue() {
		int expectedCash = 700;

		posWithFields.sellItem(new Item("coat", 350));
		posWithFields.sellItem(new Item("t-shirt", 150));
		posWithFields.encashment();

		posWithFields.sellItem(new Item("shirt", 200));
		posWithFields.encashment();
		String report = posWithFields.report((new Timestamp(new Date().getTime() - 2000)),
				new Timestamp(new Date().getTime()));

		Assert.assertEquals(expectedCash, Integer.parseInt(report.substring(report.length() - 3, report.length())));

		System.out.println(report);
	}

}
