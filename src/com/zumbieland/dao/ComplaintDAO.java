package com.zumbieland.dao;

import javax.sql.DataSource;

import com.zumbieland.model.Complaint;
import com.zumbieland.model.Survivor;

public interface ComplaintDAO {
	/**
	 * This is the method to be used to initialize
	 * database resources ie. connection.
	 */
	public void setDataSource(DataSource ds);
	/** 
	 * This is the method to be used to create
	 * a record in the Complaints table.
	 */
	public void create(Complaint complaint);
	/** 
	 * This is the method to be used to count down
	 * the number of complaint records by passed Survivors id.
	 */
	public int getCountComplaintsBySurvivor(Survivor survivor);
}
