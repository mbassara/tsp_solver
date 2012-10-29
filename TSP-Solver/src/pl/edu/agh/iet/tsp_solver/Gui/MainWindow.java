package pl.edu.agh.iet.tsp_solver.Gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.UIManager;

import pl.edu.agh.iet.tsp_solver.Model.TSPData;
import pl.edu.agh.iet.tsp_solver.Model.TSPDataSerialization;

public class MainWindow extends JFrame {

	private static final long serialVersionUID = -1678800712030370328L;
	private final MainWindow thisReference = this;

	private final MapPanel mapPanel;
	private final TSPDataPanel dataPanel;

	public MainWindow(String title) {
		super(title);

		this.setSize(800, 600);
		this.getContentPane().setLayout(new BorderLayout());
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);

		// LOOK AND FEEL
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e1) {
			e1.printStackTrace();
		}

		// DATA PANEL
		dataPanel = new TSPDataPanel();
		this.add(dataPanel, BorderLayout.EAST);

		// MAP PANEL
		mapPanel = new MapPanel();
		mapPanel.setDisplayPlot(false);
		this.add(mapPanel, BorderLayout.CENTER);

		// OPEN BUTTON
		JButton openButton = new JButton("Open");
		openButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				JFileChooser fileChooser = new JFileChooser(".");
				fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
				int result = fileChooser.showOpenDialog(thisReference);
				if (result != JFileChooser.APPROVE_OPTION) {
					JLabel msg = new JLabel("No file selected");
					thisReference.add(msg);
					return;
				}

				File datafile = fileChooser.getSelectedFile();
				TSPData data = TSPDataSerialization.deserialize(datafile);

				mapPanel.setData(data);
				dataPanel.fillWithData(data);
			}
		});

		// RANDOM BUTTON
		JButton randomButton = new JButton("Random data");
		randomButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dataPanel.fillWithRandomData();
			}
		});

		// PLOT BUTTON
		JButton plotButton = new JButton("Draw");
		plotButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (!dataPanel.isInitialized()) {
					return;
				}
				mapPanel.setData(dataPanel.getData());
				mapPanel.setDisplayPlot(true);
				mapPanel.update(mapPanel.getGraphics());
				thisReference.setVisible(true);
			}
		});

		// QUIT BUTTON
		JButton quitButton = new JButton("Quit");
		quitButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});

		// COMMAND PANEL
		JPanel commandPanel = new JPanel(new FlowLayout());
		commandPanel.add(openButton);
		commandPanel.add(plotButton);
		commandPanel.add(quitButton);
		commandPanel.add(randomButton);
		this.getContentPane().add(commandPanel, "North");

		this.setVisible(true);
		// this.pack();
	}

	public static void main(String[] args) {
		new MainWindow("TSP Solver");
	}
}
