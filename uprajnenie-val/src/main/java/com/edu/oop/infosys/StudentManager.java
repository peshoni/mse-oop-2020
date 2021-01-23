/**
 * 
 */
package com.edu.oop.infosys;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Petar Ivanov - pesho02@abv.bg
 *
 */
public class StudentManager {
	List<Student> students;

	public StudentManager() {
		students = new ArrayList<Student>();
	}

	/**
	 * Loads the students collection from .txt file
	 * 
	 * @param path
	 */
	public void readFileContent(String path) {
		try (FileReader fr = new FileReader(path); BufferedReader br = new BufferedReader(fr);) {
			String line;
			while ((line = br.readLine()) != null) {
				Student student = this.convertStringToStudent(line);
				this.addStudent(student);
			}
		} catch (Exception e) {
			throw new RuntimeException("File read failed.", e);
		}
	}

	/**
	 * Prints collection of students in file
	 * 
	 * @param path
	 */
	public void flushSudentsContentInFile(String path) {
		try (Writer writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(path, false), "UTF-8"))) {
			final String[] res = new String[] { "" };
			this.getStudents().forEach(student -> {
				String ress = student.toString();
				res[0] += ress + "\n";
			});
			writer.write(res[0]);
			writer.flush();
		} catch (Exception e) {
			throw new RuntimeException("File write failed.", e);
		}
	}

	private Student convertStringToStudent(String data) {
		Student student = new Student();
		System.out.println("before:" + data);
		String[] arr = data.split("\t");
		student.setFirstName(arr[0].split("\s")[0]);
		student.setLastName(arr[0].split("\s")[1]);
		student.setEGN(arr[1]);
		student.setHeight(Double.parseDouble(arr[2]));
		student.setWeight(Double.parseDouble(arr[3]));
		student.setFacultyNumber(Integer.parseInt(arr[4]));
		String[] scores = arr[5].split("\s");
		List<Double> sc = new ArrayList<Double>();
		for (String string : scores) {
			sc.add(Double.parseDouble(string));
		}
		student.setScores(sc.stream().mapToDouble(Double::doubleValue).toArray());
		student.setImageHeight(Double.parseDouble(arr[6]));
		student.setImageWidth(Double.parseDouble(arr[7]));
		System.out.println("after:" + student);
		return student;
	}

	public boolean addStudent(Student student) {
		return this.students.add(student);
	}

	public void calculateAverageScores() {
		this.students.forEach(s -> s.calculateAverageScore());
	}

	// getters and setters
	public List<Student> getStudents() {
		return students;
	}
}
