package com.edu.oop.infosys;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class TestInfoSystem {

	@Test
	public void testSetterLargenessMethod() {
		Human student = new Student();
		student.setLargeness(50.0, 182.3, 35, 98.500);
		assertTrue(student.getHeight() > 0 && student.getLength() > 0 && student.getWeight() > 0
				&& student.getWidht() > 0);
	}

	@Test
	public void testStudentAverageScoreCalculator() {
		Student student = new Student();
		int[] scores = new int[] { 2, 3, 4, 5, 6 };
		student.setScores(scores);
		assertEquals(4, student.calculateAverageScore(), 0);
	}

	@Test
	public void testStudentAverageScoreCalculatorWithRounding() {
		Student student = new Student();
		int[] scores = new int[] { 3, 3, 4, 0, 0 };
		student.setScores(scores);
		assertEquals(3.33, student.calculateAverageScore(), 0);
	}

	@Test
	public void testStudentAverageScoreWithFewerScores() {
		Student student = new Student();
		int[] scores = new int[] { 2, 3, 0, 0, 0 };
		student.setScores(scores);
		assertEquals(2.5, student.calculateAverageScore(), 0);
	}

	@Test(expected = RuntimeException.class)
	public void testStudentAverageScoreWithMoreRatings() {
		Student student = new Student();
		int[] scores = new int[] { 2, 3, 0, 0, 0, 0 };
		student.setScores(scores);
	}

	@Test
	public void testStudentAverageCalculatorWithEmptyArray() {
		Student student = new Student();
		int[] scores = new int[] {};
		student.setScores(scores);
		assertEquals(0, student.calculateAverageScore(), 0);
	}

	// manager
	@Test
	public void testStudentManagerAdd() {
		assertTrue(new StudentManager().addStudent(new Student()));
	}

	@Test
	public void testStudentManagerCalculateAverage() {
		StudentManager manager = new StudentManager();
		manager.addStudent(new Student().setScores(new int[] { 3, 3, 4, 0, 0 }));
		manager.addStudent(new Student().setScores(new int[] { 2, 3, 4, 2, 0 }));
		manager.addStudent(new Student().setScores(new int[] { 5, 3, 4, 5, 6 }));
		manager.calculateAverageScores();
		assertTrue(manager.getStudents().get(2).getAverageScore() > 0);
	}

}
