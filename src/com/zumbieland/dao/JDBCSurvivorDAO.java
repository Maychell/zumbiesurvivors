package com.zumbieland.dao;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;

import com.zumbieland.model.Survivor;

public class JDBCSurvivorDAO implements SurvivorDAO {
//	private DataSource dataSource;
	private JdbcTemplate jdbcTemplateObject;

	@Override
	public void setDataSource(DataSource dataSource) {
//		this.dataSource = dataSource;
	    this.jdbcTemplateObject = new JdbcTemplate(dataSource);
	}

	@Override
	public void create(Survivor survivor) {
		String SQL = "insert into "+Survivor.SURVIVOR+" ("
				+ Survivor.NAME + ", "
				+ Survivor.AGE + ", "
				+ Survivor.GENDER + ", "
				+ Survivor.LATITUDE + ","
				+ Survivor.LONGITUDE + ") values (?, ?, ?, ?, ?)";
		
		jdbcTemplateObject.update( SQL, survivor.getName(), survivor.getAge(),
				String.valueOf(survivor.getGender()), survivor.getLatitude(), survivor.getLongitude());
		return;
	}

	@Override
	public Survivor getSurvivorById(Integer id) {
		String SQL = "select * from "+Survivor.SURVIVOR+
				" where "+Survivor._ID+" = ?";
	    Survivor survivor = jdbcTemplateObject.queryForObject(SQL, 
	                        new Object[]{id}, new SurvivorMapper());
	    return survivor;
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
				" set "+ Survivor.NAME + "=?, "
					+ Survivor.AGE + "=?, "
					+ Survivor.GENDER + "=?, "
					+ Survivor.LATITUDE + "=?,"
					+ Survivor.LONGITUDE +
				" where id = ?";
	    jdbcTemplateObject.update(SQL, survivor.getName(), survivor.getAge(),
	    		survivor.getGender(), survivor.getLatitude(), survivor.getLongitude(), survivor.getId());
	    return;
	}

}
