package com.zumbieland.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

import com.zumbieland.model.Item;

public class ItemMapper implements RowMapper<Item> {

	@Override
	public Item mapRow(ResultSet resultSet, int rowNum) throws SQLException {
		Item item = new Item();
		item.setId(resultSet.getLong(Item._ID));
		item.setName(resultSet.getString(Item.NAME));
		item.setPoints(resultSet.getInt(Item.POINTS));
		return item;
	}

}