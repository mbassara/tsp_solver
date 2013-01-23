package pl.edu.agh.iet.tsp.Gui;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;

import pl.edu.agh.iet.tsp.Model.TSPData;
import pl.edu.agh.iet.tsp.Model.TSPDataSerialization;

public class DataPanel extends JPanel {

	private static final long serialVersionUID = -1588798219506287543L;

	public DataPanel(final MapPanel mapPanel, final Data data,
			final JFrame mainFrame) {

		// super(new BorderLayout());

		JButton openButton = new JButton("Open");
		JButton randomButton = new JButton("Random data");
		JButton quitButton = new JButton("Quit");
		quitButton.setBackground(Color.decode("#D2691E"));

		quitButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);

			}
		});
		randomButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				Data newData = new Data(mainFrame);
				TSPData tspData = TSPData.generateData("random data",
						"comment", 20, 1000);
				newData.readData(tspData);
				mapPanel.setDataPanel(newData);
				mapPanel.setDisplayPlot(true);
				mainFrame.repaint();

			}
		});

		add(randomButton);
		add(openButton);
		add(quitButton);

		openButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				JFrame fileFrame = new JFrame();
				JPanel filePanel = new JPanel();
				// JFileChooser fileChooser = new
				// JFileChooser(System.getProperty("user.dir"));
				JFileChooser fileChooser = new JFileChooser(".");
				fileFrame.getContentPane().add(filePanel);
				filePanel.add(fileChooser);
				fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
				int result = fileChooser.showOpenDialog(filePanel);
				if (result != JFileChooser.APPROVE_OPTION) {
					System.out.println("No file selected");

					return;
				}

				File datafile = fileChooser.getSelectedFile();
				TSPData tdata = TSPDataSerialization.deserialize(datafile);
				Data newData = new Data(mainFrame);
				newData.readData(tdata);
				mapPanel.setDisplayPlot(true);
				mapPanel.setDataPanel(newData);
				mainFrame.repaint();
				// frame.pack();
				// tspDataPanel.mainFrame.setVisible(true);
			}
		});

		// add(commandPanel);
	}

}
