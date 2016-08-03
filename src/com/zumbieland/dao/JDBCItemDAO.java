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

import com.zumbieland.model.Item;

public class JDBCItemDAO implements ItemDAO {
	private JdbcTemplate jdbcTemplateObject;

	@Override
	public void setDataSource(DataSource dataSource) {
	    this.jdbcTemplateObject = new JdbcTemplate(dataSource);
	}

	@Override
	public void create(Item item) {
		String SQL = "insert into "+Item.ITEM+" (" +
				Item.NAME + ", " +
				Item.POINTS + ") values (?,?)";
		
		KeyHolder keyHolder = new GeneratedKeyHolder();
		
		jdbcTemplateObject.update(new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
				PreparedStatement ps = connection.prepareStatement(SQL, new String[] {"id"});
				ps.setString(1, item.getName());
				ps.setInt(2, item.getPoints());
				
				return ps;
			}
		},keyHolder);
		item.setId(keyHolder.getKey().longValue());
		return;
	}

	@Override
	public Item getItemById(Long id) {
		String SQL = "select * from "+Item.ITEM+
				" where "+Item._ID+" = ?";
		try {
		    Item item = jdbcTemplateObject.queryForObject(SQL, 
		                        new Object[]{id}, new ItemMapper());
		    return item;
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public List<Item> listItems() {
		String SQL = "select * from "+Item.ITEM;
		List<Item> items = jdbcTemplateObject.query(SQL,
									new ItemMapper());
	      return items;
	}

	@Override
	public void delete(Integer id) {
		String SQL = "delete from "+Item.ITEM+
				" where "+Item._ID+" = ?";
	    jdbcTemplateObject.update(SQL, id);
	    return;
	}

	@Override
	public void update(Item item) {
		String SQL = "update "+Item.ITEM+
				" set " +
					Item.NAME + "=?," +
					Item.POINTS + "=?" +
				" where id = ?";
	    jdbcTemplateObject.update(SQL, item.getName(), item.getPoints(), item.getId());
	    return;
	}
}
