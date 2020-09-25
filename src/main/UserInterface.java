package main;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

public class UserInterface implements Runnable {

	private JFrame frame;

	public UserInterface() {
	}

	@Override
	public void run() {
		frame = new JFrame("Title");
		frame.setPreferredSize(new Dimension(600, 300));

		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

		createComponents(frame.getContentPane());

		frame.pack();
		frame.setVisible(true);
	}

	private void createComponents(Container container) {
		GridLayout layout = new GridLayout(4, 1);
		container.setLayout(layout);

		JLabel msg = new JLabel("Tutorials: put your .csv file in this folder "
				+ "\\projekt_csvToXml,\n Enter file name in filed and click the button");
		JTextField input = new JTextField("Enter file name here");

		JLabel output = new JLabel();
		JButton createButton = new JButton("Create");
		MessageListener create = new MessageListener(input, output);
		createButton.addActionListener(create);

		container.add(msg);
		container.add(input);
		container.add(output);
		container.add(createButton);

	}

	public JFrame getFrame() {
		return frame;
	}
}
