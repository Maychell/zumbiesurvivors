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

import com.zumbieland.model.Survivor;

public class JDBCSurvivorDAO implements SurvivorDAO {
	private JdbcTemplate jdbcTemplateObject;

	@Override
	public void setDataSource(DataSource dataSource) {
	    this.jdbcTemplateObject = new JdbcTemplate(dataSource);
	}

	@Override
	public void create(Survivor survivor) {
		String SQL = "insert into "+Survivor.SURVIVOR+" (" +
				Survivor.NAME + ", " +
				Survivor.AGE + ", " +
				Survivor.GENDER + ", " +
				Survivor.LATITUDE + "," +
				Survivor.LONGITUDE + ") values (?, ?, ?, ?, ?)";
		
		KeyHolder keyHolder = new GeneratedKeyHolder();
		
		jdbcTemplateObject.update(new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
				PreparedStatement ps = connection.prepareStatement(SQL, new String[] {"id"});
				ps.setString(1, survivor.getName());
				ps.setInt(2, survivor.getAge());
				ps.setString(3, String.valueOf(survivor.getGender()));
				ps.setString(4, survivor.getLatitude());
				ps.setString(5, survivor.getLongitude());
				return ps;
			}
		},keyHolder);
		survivor.setId(keyHolder.getKey().longValue());
		return;
	}

	@Override
	public Survivor getSurvivorById(Long id) {
		String SQL = "select * from "+Survivor.SURVIVOR+
				" where "+Survivor._ID+" = ?";
		try {
		    Survivor survivor = jdbcTemplateObject.queryForObject(SQL, 
		                        new Object[]{id}, new SurvivorMapper());
		    return survivor;
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public List<Survivor> listSurvivors() {
		String SQL = "select * from "+Survivor.SURVIVOR;
		List<Survivor> survivors = jdbcTemplateObject.query(SQL,
									new SurvivorMapper());
	      return survivors;
	}

	@Override
	public void delete(Integer id) {
		String SQL = "delete from "+Survivor.SURVIVOR+
				" where "+Survivor._ID+" = ?";
	    jdbcTemplateObject.update(SQL, id);
	    return;
	}

	@Override
	public void update(Survivor survivor) {
		String SQL = "update "+Survivor.SURVIVOR+
				" set " +
					Survivor.LATITUDE + "=?," +
					Survivor.LONGITUDE + "=?" +
				" where id = ?";
	    jdbcTemplateObject.update(SQL, survivor.getLatitude(), survivor.getLongitude(), survivor.getId());
	    return;
	}

}
