package com.zumbieland.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

import com.zumbieland.model.Survivor;

public class SurvivorMapper implements RowMapper<Survivor> {

	@Override
	public Survivor mapRow(ResultSet resultSet, int rowNum) throws SQLException {
		Survivor survivor = new Survivor();
		survivor.setId(resultSet.getLong(Survivor._ID));
		survivor.setName(resultSet.getString(Survivor.NAME));
		survivor.setAge(resultSet.getInt(Survivor.AGE));
		survivor.setGender(resultSet.getString(Survivor.GENDER).charAt(0));
		survivor.setLatitude(resultSet.getString(Survivor.LATITUDE));
		survivor.setLongitude(resultSet.getString(Survivor.LONGITUDE));
		return survivor;
	}

}