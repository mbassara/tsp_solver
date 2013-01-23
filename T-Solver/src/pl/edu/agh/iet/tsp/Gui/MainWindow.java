package pl.edu.agh.iet.tsp.Gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;

public class MainWindow {

	private static MapPanel mapPanel;
	private static Data data;
	private static final JLabel solutionLabel = new JLabel();

	public static void main(String[] args) {

		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				final JFrame mainFrame = new JFrame("TSP Solver");
				initialize(mainFrame);
				mapPanel = new MapPanel(mainFrame);
				data = new Data(mainFrame);

				Toolkit tk = Toolkit.getDefaultToolkit();
				int xSize = ((int) tk.getScreenSize().getWidth());
				int ySize = ((int) tk.getScreenSize().getHeight());

				JPanel dataTab = new DataPanel(mapPanel, data, mainFrame);
				dataTab.setPreferredSize(new Dimension(xSize, 32));
				mainFrame.getContentPane().add(BorderLayout.NORTH, dataTab);

				OptionPanel optionsTab = new OptionPanel(mapPanel);

				optionsTab.setPreferredSize(new Dimension(2 * xSize / 10,
						8 * ySize / 10));

				mainFrame.getContentPane().add(BorderLayout.EAST, optionsTab);

				mainFrame.getContentPane().add(solutionLabel,
						BorderLayout.SOUTH);

				mainFrame.setVisible(true);
			}
		});

	}

	public static void setSolutionLabelText(String text) {
		solutionLabel.setText(text);
	}

	static void initialize(JFrame mainFrame) {
		Toolkit tk = Toolkit.getDefaultToolkit();
		int xSize = ((int) tk.getScreenSize().getWidth());
		int ySize = ((int) tk.getScreenSize().getHeight());
		mainFrame.setSize(6 * xSize / 10, 8 * ySize / 10);
		try {
			for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
				if ("Nimbus".equals(info.getName())) {
					UIManager.setLookAndFeel(info.getClassName());
					break;
				}
			}
		} catch (Exception e) {
		}

		mainFrame.getContentPane().setLayout(new BorderLayout());
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
