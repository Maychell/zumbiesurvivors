package com.zumbieland.dao;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.HashMap;

import org.springframework.cglib.beans.BeanMap;
import org.springframework.jdbc.core.RowMapper;

import com.zumbieland.model.Inventory;
import com.zumbieland.model.Item;
import com.zumbieland.model.Survivor;

public class InventoryMapper implements RowMapper<Inventory> {

//	@Override
//	public Inventory mapRow(ResultSet resultSet, int rowNum) throws SQLException {
//		Inventory inventory = new Inventory();
//		inventory.setId(resultSet.getLong(Inventory._ID));
//		
//		Survivor survivor = new Survivor();
//		survivor.setId(resultSet.getLong(Inventory.SURVIVOR));
//		inventory.setSurvivor(survivor);
//		
//		Item item = new Item();
//		item.setId(resultSet.getLong(Inventory.ITEM));
//		inventory.setItem(item);
//		
//		return inventory;
//	}
	
	@Override
	public Inventory mapRow(ResultSet resultSet, int rowNum) throws SQLException {
		HashMap<String, BeanMap> beans_by_name = new HashMap<String, BeanMap>();

		//Separate the beans in each table to get all the nested attributes for every table
		beans_by_name.put(Inventory.INVENTORY, BeanMap.create(new Inventory()));
	    beans_by_name.put(Survivor.SURVIVOR, BeanMap.create(new Survivor()));
	    beans_by_name.put(Item.ITEM, BeanMap.create(new Item()));

	    ResultSetMetaData resultSetMetaData = resultSet.getMetaData();

	    //go through every select
	    for (int colnum = 1; colnum <= resultSetMetaData.getColumnCount(); colnum++) {
	    	//the attributes are separated in the following way "table.column". That's the reason to split it
	        String table = resultSetMetaData.getColumnName(colnum).split("\\.")[0];
	        String field = resultSetMetaData.getColumnName(colnum).split("\\.")[1];

	        //get the corresponding table to set the values to the attributes
	        BeanMap beanMap = beans_by_name.get(table);

	        //set values to each attribute
	        if (resultSet.getObject(colnum) != null) {
	        	switch (field) {
				case Inventory._ID:
					beanMap.put(field, resultSet.getLong(colnum));
					break;
				case Survivor.GENDER:
					beanMap.put(field, resultSet.getString(colnum).charAt(0));
					break;
				default:
					beanMap.put(field, resultSet.getObject(colnum));
					break;
				}
	        }

	    }

	    //build the object with the nested attributes
	    Inventory m = (Inventory) beans_by_name.get(Inventory.INVENTORY).getBean();
	    m.setSurvivor((Survivor)beans_by_name.get(Survivor.SURVIVOR).getBean());
	    m.setItem((Item)beans_by_name.get(Item.ITEM).getBean());

	    return m;
	}

}