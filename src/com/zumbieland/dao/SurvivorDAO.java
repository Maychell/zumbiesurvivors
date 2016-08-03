package com.zumbieland.dao;

import java.util.List;
import javax.sql.DataSource;

import com.zumbieland.model.Survivor;

public interface SurvivorDAO {
	/**
	 * This is the method to be used to initialize
	 * database resources ie. connection.
	 */
	public void setDataSource(DataSource ds);
	/** 
	 * This is the method to be used to create
	 * a record in the Survivors table.
	 */
	public void create(Survivor survivor);
	/** 
	 * This is the method to be used to list down
	 * a record from the Survivors table corresponding
	 * to a passed Survivors id.
	 */
	public Survivor getSurvivorById(Long id);
	/** 
	 * This is the method to be used to list down
	 * all the records from the Survivors table.
	 */
	public List<Survivor> listSurvivors();
	/** 
	 * This is the method to be used to delete
	 * a record from the Survivors table corresponding
	 * to a passed survivors id.
	 */
	public void delete(Integer id);
	/** 
	 * This is the method to be used to update
	 * a record into the Survivors table.
	 */
	public void update(Survivor survivor);
}
