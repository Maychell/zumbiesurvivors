package com.zumbieland.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import com.zumbieland.model.Inventory;
import com.zumbieland.model.Survivor;

public class JDBCInventoryDAO implements InventoryDAO {
	private JdbcTemplate jdbcTemplateObject;

	@Override
	public void setDataSource(DataSource dataSource) {
	    this.jdbcTemplateObject = new JdbcTemplate(dataSource);
	}

	@Override
	public void create(Inventory inventory) {
		String SQL = "insert into "+Inventory.INVENTORY+" (" +
				Inventory.SURVIVOR + ", " +
				Inventory.ITEM + ") values (?,?)";
		
		KeyHolder keyHolder = new GeneratedKeyHolder();
		
		jdbcTemplateObject.update(new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
				PreparedStatement ps = connection.prepareStatement(SQL, new String[] {"id"});
				ps.setLong(1, inventory.getSurvivor().getId());
				ps.setLong(2, inventory.getItem().getId());
				
				return ps;
			}
		},keyHolder);
		inventory.setId(keyHolder.getKey().longValue());
		return;
	}

	@Override
	public Inventory getInventoryById(Long id) {
		String SQL = "select * from "+Inventory.INVENTORY+
				" where "+Inventory._ID+" = ?";
		try {
		    Inventory inventory = jdbcTemplateObject.queryForObject(SQL, 
		                        new Object[]{id}, new InventoryMapper());
		    return inventory;
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public List<Inventory> listSurvivorInventory(Survivor survivor) {
		String SQL = "select * from "+Inventory.SURVIVOR;
		List<Inventory> inventories = jdbcTemplateObject.query(SQL,
									new InventoryMapper());
	      return inventories;
	}

	@Override
	public void delete(Integer id) {
		String SQL = "delete from "+Inventory.INVENTORY+
				" where "+Inventory._ID+" = ?";
	    jdbcTemplateObject.update(SQL, id);
	    return;
	}

	@Override
	public void update(Inventory inventory) {
		String SQL = "update "+Inventory.INVENTORY+
				" set " +
					Inventory.SURVIVOR + "=?," +
					Inventory.ITEM + "=?" +
				" where id = ?";
	    jdbcTemplateObject.update(SQL, inventory.getSurvivor().getId(),
	    		inventory.getItem().getId(), inventory.getId());
	    return;
	}
}
