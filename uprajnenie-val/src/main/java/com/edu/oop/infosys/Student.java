/**
 * 
 */
package com.edu.oop.infosys;

import com.edu.oop.infosys.utils.InfoSysUtil;

/**
 * @author Petar Ivanov - pesho02@abv.bg
 *
 */
public class Student extends Human {
	private int facultyNumber;
	private double[] scores = new double[5];
	private double averageScore;
	private final String space = "\s";
	private final String tab = "\t";

	/**
	 * 
	 * A method for calculate average scores of student
	 * 
	 * @return
	 */
	public double calculateAverageScore() {
		int counter = 0;
		double sum = 0;
		for (int i = 0; i < scores.length; i++) {
			if (scores[i] > 0) {
				counter++;
				sum += scores[i];
			}
		}
		averageScore = sum / (counter > 0 ? counter : 1);// avoiding zero devision
		return InfoSysUtil.round(averageScore, 2);
	}

	// Getters and setters
	public int getFacultyNumber() {
		return facultyNumber;
	}

	public void setFacultyNumber(int facultyNumber) {
		this.facultyNumber = facultyNumber;
	}

	public double[] getScores() {
		return scores;
	}

	public Student setScores(double[] scores) {
		if (scores.length > 5)
			throw new RuntimeException("the student can have only 5 ratings");
		this.scores = scores;
		return this;
	}

	public double getAverageScore() {
		return averageScore;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append(this.getFirstName()).append(space);
		builder.append(this.getLastName()).append(tab);
		builder.append(this.getEGN()).append(tab);
		builder.append(this.getHeight()).append(tab);
		builder.append(this.getWeight()).append(tab);
		builder.append(this.getFacultyNumber()).append(tab);
		String scores = "";
		for (double d : this.scores) {
			scores += d + space;
		}
		// String scores = "1 2 3 4";
		builder.append(scores).append(tab);
		builder.append(this.getImageHeight()).append(tab);
		builder.append(this.getImageWidth()).append(tab);

		return builder.toString();
	}

}
