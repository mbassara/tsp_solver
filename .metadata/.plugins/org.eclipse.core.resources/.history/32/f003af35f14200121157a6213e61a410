package pl.edu.agh.iet.tsp_solver.Gui;

import java.awt.BorderLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;

import pl.edu.agh.iet.tsp_solver.Model.TSPData;
import pl.edu.agh.iet.tsp_solver.Model.TSPDataSerialization;

public class MainWindow {

	private static GraphPanel graphPanel;
	private static TSPDataPanel tspDataPanel;

	public static void main(String[] args) {
		final JFrame frame = new JFrame("TSP Solver");
		Toolkit tk = Toolkit.getDefaultToolkit();
		int xSize = ((int) tk.getScreenSize().getWidth());
		int ySize = ((int) tk.getScreenSize().getHeight());
		frame.setSize(xSize, ySize);
		try {
			for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
				if ("Nimbus".equals(info.getName())) {
					UIManager.setLookAndFeel(info.getClassName());
					break;
				}
			}
		} catch (Exception e) {
			// If Nimbus is not available, you can set the GUI to another look
			// and feel.
		}
		frame.getContentPane().setLayout(new BorderLayout());
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		graphPanel = new GraphPanel(frame);
		tspDataPanel = new TSPDataPanel(frame);
		JPanel commandPanel = new JPanel();
		commandPanel
				.setLayout(new BoxLayout(commandPanel, BoxLayout.PAGE_AXIS));
		JButton openButton = new JButton("Open");
		JButton randomButton = new JButton("Random data");
		final JButton plotButton = new JButton("Draw");
		JButton quitButton = new JButton("Quit");
		quitButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		randomButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// to do - make a new window to choose name , comment ,
				// dimension and mapwidth
				TSPData data = TSPData.generateData("random data", "comment",
						10, 10000);
				tspDataPanel = new TSPDataPanel(frame);
				tspDataPanel.initialized = tspDataPanel.readData(data);
				tspDataPanel.panel.update(tspDataPanel.panel.getGraphics());
				tspDataPanel.refreshData();
				// dataPanel.frame.pack();
				tspDataPanel.frame.setVisible(true);
				graphPanel.setTSPDataPanel(tspDataPanel);
				// GraphPanel graphpanel = new GraphPanel(dataPanel.frame);
				// graphpanel.datapanel = dataPanel;
				// plotButton.addActionListener(graphpanel);
			}
		});
		commandPanel.registerKeyboardAction(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		}, KeyStroke.getKeyStroke("control Q"),
				JComponent.WHEN_IN_FOCUSED_WINDOW);
		commandPanel.add(openButton);
		commandPanel.add(plotButton);
		commandPanel.add(quitButton);
		commandPanel.add(randomButton);
		frame.getContentPane().add(commandPanel, "East");

		plotButton.addActionListener(graphPanel);

		openButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				JFrame fileFrame = new JFrame();
				JPanel filePanel = new JPanel();
				JFileChooser fileChooser = new JFileChooser();
				fileFrame.getContentPane().add(filePanel);
				filePanel.add(fileChooser);
				fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
				int result = fileChooser.showOpenDialog(filePanel);
				if (result != JFileChooser.APPROVE_OPTION) {
					tspDataPanel.msg = new JLabel("No file selected");
					tspDataPanel.panel.add(tspDataPanel.msg);
					return;
				}

				File datafile = fileChooser.getSelectedFile();
				TSPData data = TSPDataSerialization.deserialize(datafile);
				tspDataPanel.initialized = tspDataPanel.readData(data);
				tspDataPanel.panel.update(tspDataPanel.panel.getGraphics());
				// frame.pack();
				tspDataPanel.frame.setVisible(true);
			}
		});

		frame.setVisible(true);
		// frame.pack();
	}
}
