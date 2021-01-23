/**
 * 
 */
package com.edu.oop.infosys;

/**
 * 
 * @author Petar Ivanov - pesho02@abv.bg
 *
 */
public class Human implements Largeness {
	private String firstName;
	private String lastName;
	private String EGN;
	private double height;
	private double weight;
	private double imageHeight;
	private double imageWidth;

	public void setLargeness(double height, double weight, double imageHeight, double imageWidth) {
		this.height = height;
		this.weight = weight;
		this.imageHeight = imageHeight;
		this.imageWidth = imageWidth;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEGN() {
		return EGN;
	}

	public void setEGN(String eGN) {
		EGN = eGN;
	}

	public double getWidht() {
		return imageWidth;
	}

	public double getHeight() {
		return height;
	}

	public double getLength() {
		return imageHeight;
	}

	public double getWeight() {
		return weight;
	}

	public double getImageHeight() {
		return imageHeight;
	}

	public void setImageHeight(double imageHeight) {
		this.imageHeight = imageHeight;
	}

	public double getImageWidth() {
		return imageWidth;
	}

	public void setImageWidth(double imageWidth) {
		this.imageWidth = imageWidth;
	}

	public void setHeight(double height) {
		this.height = height;
	}

	public void setWeight(double weight) {
		this.weight = weight;
	}

}
