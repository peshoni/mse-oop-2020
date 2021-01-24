package com.edu.oop.patterns.builder;

public class Fish {
	private int length;
	private int weight;
	private boolean isSaltWater;
	private String species;

	public void setLength(int length) {
		this.length = length;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}

	public void setIsSaltWater(boolean isSaltWater) {
		this.isSaltWater = isSaltWater;
	}

	public void setSpecies(String species) {
		this.species = species;
	}

	public static class Builder {
		private int length;
		private int weight;
		private boolean isSaltWater;
		private String species;

		public Builder setLength(int length) {
			this.length = length;
			return this;
		}

		public Builder setWeight(int weight) {
			this.weight = weight;
			return this;
		}

		public Builder setIsSaltWater(boolean isSaltWater) {
			this.isSaltWater = isSaltWater;
			return this;
		}

		public Builder setSpecies(String species) {
			this.species = species;
			return this;
		}

		public Fish build() {
			Fish fish = new Fish();
			fish.setLength(length);
			fish.setLength(length);
			fish.setSpecies(species);
			fish.setIsSaltWater(isSaltWater);
			return fish;
		}
	}

}
