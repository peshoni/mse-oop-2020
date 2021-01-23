package com.edu.oop.infosys;

import java.awt.BorderLayout;
import java.awt.Dialog;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.filechooser.FileSystemView;
import javax.swing.table.DefaultTableModel;

public class GUI implements ActionListener {
	private StudentManager manager = new StudentManager();
	private JFrame frame;
	private JTable table;
	private DefaultTableModel tableModel;

	public GUI() {
		buildFrame();
		buildTableAndModel();
		buildMenu();
		frame.setVisible(true);

	}

	private void buildFrame() {
		frame = new JFrame("STUDENTS -Info system frame");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(1200, 800);
	}

	private void buildTableAndModel() {
		String column[] = { "add", "lastName", "EGN", "height", "weight", "fakNum", "score 1", "score 2", "score 3",
				"score 4", "score 5", "average", "imageH", "imageW" };

		tableModel = new DefaultTableModel(column, 0);
		table = new JTable(tableModel);
		table.setBounds(80, 80, 800, 400);
		JScrollPane scrollPane = new JScrollPane(table);

		frame.getContentPane().add(BorderLayout.CENTER, scrollPane);

	}

	private void buildMenu() {
		// Creating the MenuBar
		JMenuBar menuBar = new JMenuBar();
		JMenu menuFile = new JMenu("FILE");
		JMenuItem menuOpen = new JMenuItem("Open");
		menuOpen.setActionCommand("FILE_CHOOSER");
		menuOpen.addActionListener(this);
		menuFile.add(menuOpen);
		JMenuItem manuSave = new JMenuItem("Save as");
		manuSave.setActionCommand("FILE_SAVE");
		manuSave.addActionListener(this);
		menuFile.add(manuSave);
		menuBar.add(menuFile);

		JMenu menuFileData = new JMenu("DATA");
		JMenuItem deleteMenu = new JMenuItem("Clear all");
		deleteMenu.setActionCommand("DELETE_ALL");
		deleteMenu.addActionListener(this);
		menuFileData.add(deleteMenu);

		JButton send = new JButton("Add Student");
		send.setActionCommand("ADD_STUDENT");
		send.addActionListener(this);

		menuBar.add(menuFileData);
		menuBar.add(send);

		frame.getContentPane().add(BorderLayout.NORTH, menuBar);
	}

	private void addStudentsToTable(List<Student> students, DefaultTableModel tableModel) {
		for (Student s : students) {
			Object[] data = buildTbleObjectDataForStudent(s);
			tableModel.addRow(data);
		}

	}

	private Object[] buildTbleObjectDataForStudent(Student s) {
		s.calculateAverageScore();
		double[] sc = s.getScores();
		Object[] data = { s.getFirstName(), s.getLastName(), s.getEGN(), s.getHeight(), s.getWeight(),
				s.getFacultyNumber(), sc[0], sc[1], sc[2], sc[3], sc[4], s.getAverageScore(), s.getImageHeight(),
				s.getImageWidth() };
		return data;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		System.out.println(e.getActionCommand());
		switch (e.getActionCommand()) {
		case "FILE_CHOOSER":

			final JFileChooser fileChooser = new JFileChooser();
			fileChooser.setDialogTitle("Choose a directory to save your file: ");
			int returnValue = fileChooser.showOpenDialog(null);
			if (returnValue == JFileChooser.APPROVE_OPTION) {
				File selectedFile = fileChooser.getSelectedFile();
				String path = selectedFile.getAbsolutePath();
				// String filInput = "f:\\students.txt";
				manager.readFileContent(path);
				addStudentsToTable(manager.getStudents(), tableModel);
			}
			break;
		case "FILE_SAVE":
			final JFileChooser jfc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
			jfc.setDialogTitle("Custom button");
			int returnValue1 = jfc.showDialog(null, "Create and save");
			if (returnValue1 == JFileChooser.APPROVE_OPTION) {
				String path = jfc.getSelectedFile().getPath();
				if (!path.endsWith(".txt"))
					path = path + ".txt";
				System.out.println(path);
				manager.flushSudentsContentInFile(path);
			}

			break;

		case "DELETE_ALL":
			manager.getStudents().clear();
			tableModel.setRowCount(0);
			break;
		case "ADD_STUDENT":
			showDialog(frame);
			break;

		default:
			break;
		}

	}

	private void showDialog(JFrame frame) {
		JDialog dialog = new JDialog(frame, Dialog.ModalityType.APPLICATION_MODAL);
		JPanel panel = new JPanel();

		JTextField name = new JTextField(10); // accepts upto 10 characters
		JTextField lastName = new JTextField(10);
		JTextField egn = new JTextField(10);
		JTextField height = new JTextField(10);
		JTextField weight = new JTextField(10);
		JTextField fakNum = new JTextField(10);
		JTextField ocenka1 = new JTextField(4);
		JTextField ocenka2 = new JTextField(4);
		JTextField ocenka3 = new JTextField(4);
		JTextField ocenka4 = new JTextField(4);
		JTextField ocenka5 = new JTextField(5);
		JTextField imageHeight = new JTextField(5);
		JTextField imageWidth = new JTextField(5);

		panel.add(new JLabel("firstName")); // Components Added using Flow Layout
		panel.add(name);
		panel.add(new JLabel("lastName"));
		panel.add(lastName);
		panel.add(new JLabel("egn"));
		panel.add(egn);
		panel.add(new JLabel("height"));
		panel.add(height);
		panel.add(new JLabel("weight"));
		panel.add(weight);
		panel.add(new JLabel("fakNum"));
		panel.add(fakNum);
		panel.add(new JLabel("ocenka1"));
		panel.add(ocenka1);
		panel.add(new JLabel("ocenka2"));
		panel.add(ocenka2);
		panel.add(new JLabel("ocenka3"));
		panel.add(ocenka3);
		panel.add(new JLabel("ocenka4"));
		panel.add(ocenka4);
		panel.add(new JLabel("ocenka5"));
		panel.add(ocenka5);
		panel.add(new JLabel("imageHeight"));
		panel.add(imageHeight);
		panel.add(new JLabel("imageWidth"));
		panel.add(imageWidth);
		panel.add(new JLabel(""));
		JButton save = new JButton("ok");
		panel.add(save);
		// Adding Components to the frame.
		panel.setLayout(new GridLayout(14, 2));

		dialog.add(panel);

		save.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				Student student = new Student();
				student.setFirstName(name.getText());
				student.setLastName(lastName.getText());
				student.setEGN(egn.getText().trim());

				student.setHeight(getDoubleFromTextField(height));
				student.setWeight(getDoubleFromTextField(weight));
				student.setFacultyNumber(Integer.parseInt(fakNum.getText()));

				double[] scores = new double[5];
				scores[0] = getDoubleFromTextField(ocenka1);
				scores[1] = getDoubleFromTextField(ocenka2);
				scores[2] = getDoubleFromTextField(ocenka3);
				scores[3] = getDoubleFromTextField(ocenka4);
				scores[4] = getDoubleFromTextField(ocenka5);
				student.setScores(scores);
				student.setImageHeight(getDoubleFromTextField(imageHeight));
				student.setImageWidth(getDoubleFromTextField(imageWidth));

				manager.addStudent(student);
				tableModel.addRow(buildTbleObjectDataForStudent(student));
				dialog.dispose();
			}

		});

		dialog.setBounds(50, 50, 600, 400);
		dialog.setVisible(true);
	}

	private double getDoubleFromTextField(JTextField textField) {
		return Double.parseDouble(textField.getText().trim());
	}
}
