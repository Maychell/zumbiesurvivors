package com.zumbieland.model;

public class Item {

	/*
	 CREATE TABLE Items(
	 	ID INT NOT NULL AUTO_INCREMENT,
	 	NAME VARCHAR(50) NOT NULL,
	 	POINTS INT NOT NULL,
	 	PRIMARY KEY (ID)
	 );
	 */
	
	private Long id;
	private String name;
	private int points;
	
	/**
	 * Default constructor
	 */
	public Item() {}
	
	/**
	 * Constructor with params for new objects
	 * @param name
	 * @param points
	 */
	public Item(String name, int points) {
		this.name = name;
		this.points = points;
	}
	
	/**
	 * Constructor with params for existing objects
	 * @param id
	 * @param name
	 * @param points
	 */
	public Item(Long id, String name, int points) {
		this.id = id;
		this.name = name;
		this.points = points;
	}
	
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
	 * @return the points
	 */
	public int getPoints() {
		return points;
	}
	/**
	 * @param points the points to set
	 */
	public void setPoints(int points) {
		this.points = points;
	}
	
	public static final String ITEM = "Items";
	public static final String _ID = "id";
	public static final String NAME = "name";
	public static final String POINTS = "points";
	
}
