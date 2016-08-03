package com.zumbieland.model;

/**
 * Factory for survivors
 * @author maychellfernandesdeoliveira
 *
 */
public class Survivor {
	
	/*
	 CREATE TABLE Zumbies(
	 	ID INT NOT NULL AUTO_INCREMENT,
	 	NAME VARCHAR(20) NOT NULL,
	 	AGE INT NOT NULL,
	 	LATITUDE VARCHAR(50),
	 	LONGITUDE VARCHAR(50),
	 	GENDER VARCHAR(1),
	 	INFECTED BOOLEAN,
	 	PRIMARY KEY (ID)
	 );
	 */
	
	private Long id;
	private String name;
	private int age;
	private char gender;
	private String latitude;
	private String longitude;
	private boolean infected;
	
	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the age
	 */
	public int getAge() {
		return age;
	}
	/**
	 * @param age the age to set
	 */
	public void setAge(int age) {
		this.age = age;
	}
	/**
	 * @return the gender
	 */
	public char getGender() {
		return gender;
	}
	/**
	 * @param gender the gender to set
	 */
	public void setGender(char gender) {
		this.gender = gender;
	}
	/**
	 * @return the latitude
	 */
	public String getLatitude() {
		return latitude;
	}
	/**
	 * @param latitude the latitude to set
	 */
	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}
	/**
	 * @return the longitude
	 */
	public String getLongitude() {
		return longitude;
	}
	/**
	 * @param longitude the longitude to set
	 */
	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}
	
	@Override
	public String toString() {
		return id + " - " + name + " - " + gender;
	}

	/**
	 * @return the infected
	 */
	public boolean isInfected() {
		return infected;
	}
	/**
	 * @param infected the infected to set
	 */
	public void setInfected(boolean infected) {
		this.infected = infected;
	}

	public static final String SURVIVOR = "survivors";
	public static final String _ID = "id";
	public static final String NAME = "name";
	public static final String AGE = "age";
	public static final String GENDER = "gender";
	public static final String LATITUDE = "latitude";
	public static final String LONGITUDE = "longitude";
	public static final String INFECTED = "infected";
}
