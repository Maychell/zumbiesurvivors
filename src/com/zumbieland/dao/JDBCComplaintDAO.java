package com.zumbieland.dao;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;

import com.zumbieland.model.Complaint;
import com.zumbieland.model.Survivor;

public class JDBCComplaintDAO implements ComplaintDAO {
	private JdbcTemplate jdbcTemplateObject;

	@Override
	public void setDataSource(DataSource dataSource) {
	    this.jdbcTemplateObject = new JdbcTemplate(dataSource);
	}

	@Override
	public void create(Complaint complaint) {
		String SQL = "insert into "+Complaint.COMPLAINT+" (" +
				Complaint.SURVIVOR + ") values (?)";
		
		jdbcTemplateObject.update(SQL, complaint.getSurvivor().getId());
		return;
	}

	@Override
	public int getCountComplaintsBySurvivor(Survivor survivor) {
		String SQL = "select count(*) from "+Complaint.COMPLAINT+
				" where "+Complaint.SURVIVOR+" = ?";
		try {
		    int count = jdbcTemplateObject.queryForObject(
                    SQL, Integer.class, survivor.getId());
		    return count;
		} catch (Exception e) {
			return 0;
		}
	}
	
}
