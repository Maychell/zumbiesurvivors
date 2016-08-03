package com.zumbieland.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

import com.zumbieland.model.Complaint;
import com.zumbieland.model.Survivor;

public class ComplaintMapper implements RowMapper<Complaint> {

	@Override
	public Complaint mapRow(ResultSet resultSet, int rowNum) throws SQLException {
		Complaint complaint = new Complaint();
		complaint.setId(resultSet.getLong(Complaint._ID));
		JDBCSurvivorDAO dao = new JDBCSurvivorDAO();
		Survivor survivor = dao.getSurvivorById(resultSet.getLong(Survivor._ID));
		complaint.setSurvivor(survivor);
		return complaint;
	}

}