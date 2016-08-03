package com.zumbieland.model;

/**
 * Factory for Complaint
 * @author maychellfernandesdeoliveira
 *
 */
public class Complaint {
	
	/*
	CREATE TABLE Complaints(
		ID INT NOT NULL AUTO_INCREMENT,
		SURVIVOR_ID INT NOT NULL,
		PRIMARY KEY (ID),
		FOREIGN KEY (SURVIVOR_ID) REFERENCES SURVIVORS(ID)
	);
	*/
	
	private Long id;
	private Survivor survivor;
	
	/**
	 * Default constructor
	 */
	public Complaint() {}
	
	/**
	 * Constructor with survivor only
	 * @param survivor
	 */
	public Complaint(Survivor survivor) {
		this.survivor = survivor;
	}
	
	/**
	 * Constructor with id and survivor
	 * @param id
	 * @param survivor
	 */
	public Complaint(Long id, Survivor survivor) {
		this.id = id;
		this.survivor = survivor;
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
	
	public static final String COMPLAINT = "Complaints";
	public static final String _ID = "id";
	public static final String SURVIVOR = "survivor_id";

}
