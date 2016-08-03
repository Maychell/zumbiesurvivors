package com.zumbieland.model;

public class Inventory {
	
	/*
	 CREATE TABLE Inventories(
	 	ID INT NOT NULL AUTO_INCREMENT,
	 	SURVIVOR_ID INT NOT NULL,
	 	ITEM_ID INT NOT NULL,
	 	PRIMARY KEY (ID),
	 	FOREIGN KEY (SURVIVOR_ID) REFERENCES SURVIVORS(ID),
	 	FOREIGN KEY (ITEM_ID) REFERENCES ITEMS(ID)
	 );
	 */
	
	private Long id;
	private Survivor survivor;
	private Item item;
	
	/**
	 * Default constructor
	 */
	public Inventory() {}
	
	/**
	 * Constructor with params for new objects
	 * @param survivor
	 * @param item
	 */
	public Inventory(Survivor survivor, Item item) {
		this.survivor = survivor;
		this.item = item;
	}
	
	/**
	 * Constructor with params for existing objects
	 * @param id
	 * @param survivor
	 * @param item
	 */
	public Inventory(Long id, Survivor survivor, Item item) {
		this.id = id;
		this.survivor = survivor;
		this.item = item;
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
	 * @return the survivor
	 */
	public Survivor getSurvivor() {
		return survivor;
	}
	/**
	 * @param survivor the survivor to set
	 */
	public void setSurvivor(Survivor survivor) {
		this.survivor = survivor;
	}
	/**
	 * @return the item
	 */
	public Item getItem() {
		return item;
	}
	/**
	 * @param item the item to set
	 */
	public void setItem(Item item) {
		this.item = item;
	}
	
	public static final String INVENTORY = "Inventories";
	public static final String _ID = "id";
	public static final String SURVIVOR = "survivor_id";
	public static final String ITEM = "item_id";

}
