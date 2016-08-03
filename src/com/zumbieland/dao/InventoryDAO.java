package com.zumbieland.dao;

import java.util.List;
import javax.sql.DataSource;

import com.zumbieland.model.Inventory;
import com.zumbieland.model.Item;
import com.zumbieland.model.Survivor;

public interface InventoryDAO {
	/**
	 * This is the method to be used to initialize
	 * database resources ie. connection.
	 */
	public void setDataSource(DataSource ds);
	/** 
	 * This is the method to be used to create
	 * a record in the Inventories table.
	 */
	public void create(Inventory inventory);
	/** 
	 * This is the method to be used to list down
	 * a record from the Inventories table corresponding
	 * to a passed Inventory id.
	 */
	public Inventory getInventoryById(Long id);
	/** 
	 * This is the method to be used to list down
	 * all the records from the Inventories table that the passed survivor owns.
	 */
	public List<Inventory> listSurvivorInventory(Survivor survivor);
	/** 
	 * This is the method to be used to delete
	 * a record from the Inventories table corresponding
	 * to a passed survivors id.
	 */
	public void delete(Integer id);
	/** 
	 * This is the method to be used to update
	 * a record into the Inventories table.
	 */
	public void update(Inventory inventory);
	/** 
	 * This is the method to count the number of
	 * Items the Survivors owns
	 */
	public int countByItem(Item item);
}
