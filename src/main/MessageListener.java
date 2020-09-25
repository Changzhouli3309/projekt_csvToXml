package main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JTextField;

public class MessageListener implements ActionListener {

	private JTextField input;
	private JLabel output;

	public MessageListener(JTextField input, JLabel output) {
		this.input = input;
		this.output = output;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String fileName = getCheckInput(this.input.getText());

		List<List<String>> records = CSV_reader.getData(fileName + ".csv");
		if (records == null) {
			this.output.setText("File not found.");
		} else {
			if (XmlCreater.create(fileName, records)) {
				this.output.setText("Create xml, successed");
			} else {
				this.output.setText("Create xml, failed");
			}
		}
	}

	/**
	 * Check input with .csv or not
	 * @param input
	 * @return file name without .csv
	 */
	private String getCheckInput(String input) {
		if (input.length() > 4) {
			int endIndex = input.length() - 4;
			String extension = input.substring(endIndex, input.length());
			if (extension.equals(".csv")) {
				return input.substring(0, endIndex);
			}
		}
		return input;
	}

}
