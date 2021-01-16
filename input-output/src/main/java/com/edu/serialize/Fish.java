package com.edu.serialize;

import java.io.Serializable;

public class Fish implements Serializable {

	@Override
	public String toString() {
		return "Fish [type=" + type + ", weight=" + weight + ", isSaltWater=" + isSaltWater + "]";
	}

	private static final long serialVersionUID = 4818148834344266960L;

	private transient String type;
	private int weight;
	private boolean isSaltWater;

	public Fish(String type, int weight, boolean isSaltWater) {
		super();
		this.type = type;
		this.weight = weight;
		this.isSaltWater = isSaltWater;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getWeight() {
		return weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}

	public boolean isSaltWater() {
		return isSaltWater;
	}

	public void setSaltWater(boolean isSaltWater) {
		this.isSaltWater = isSaltWater;
	}

}
