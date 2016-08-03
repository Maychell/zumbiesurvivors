package com.zumbieland.dao;

import java.util.List;
import javax.sql.DataSource;

import com.zumbieland.model.Item;

public interface ItemDAO {
	/**
	 * This is the method to be used to initialize
	 * database resources ie. connection.
	 */
	public void setDataSource(DataSource ds);
	/** 
	 * This is the method to be used to create
	 * a record in the Items table.
	 */
	public void create(Item item);
	/** 
	 * This is the method to be used to list down
	 * a record from the Items table corresponding
	 * to a passed Item id.
	 */
	public Item getItemById(Long id);
	/** 
	 * This is the method to be used to list down
	 * all the records from the Items table.
	 */
	public List<Item> listItems();
	/** 
	 * This is the method to be used to delete
	 * a record from the Item table corresponding
	 * to a passed item id.
	 */
	public void delete(Integer id);
	/** 
	 * This is the method to be used to update
	 * a record into the Items table.
	 */
	public void update(Item item);
}
