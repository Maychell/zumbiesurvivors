package com.zumbieland.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

import com.zumbieland.model.Inventory;
import com.zumbieland.model.Item;
import com.zumbieland.model.Survivor;

public class InventoryMapper implements RowMapper<Inventory> {

	@Override
	public Inventory mapRow(ResultSet resultSet, int rowNum) throws SQLException {
		Inventory inventory = new Inventory();
		inventory.setId(resultSet.getLong(Inventory._ID));
		
		Survivor survivor = new Survivor();
		survivor.setId(resultSet.getLong(Inventory.SURVIVOR));
		inventory.setSurvivor(survivor);
		
		Item item = new Item();
		item.setId(resultSet.getLong(Inventory.ITEM));
		inventory.setItem(item);
		
		return inventory;
	}

}