package pl.edu.agh.iet.tsp_solver.Gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import javax.swing.UIManager;

import pl.edu.agh.iet.tsp_solver.Model.TSPData;

public class MainWindow {
	public static void main(String[] args) {
		final JFrame frame = new JFrame("TSP Solver");
		Toolkit tk = Toolkit.getDefaultToolkit();
		int xSize = ((int) tk.getScreenSize().getWidth());
		int ySize = ((int) tk.getScreenSize().getHeight());
		frame.setSize(xSize, ySize);
		frame.getContentPane().setLayout(new BorderLayout());
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// System LookAndFeel
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e1) {
			e1.printStackTrace();
		}

		JPanel commandPanel = new JPanel(new FlowLayout());
		JButton openButton = new JButton("Open  Ctrl-O");
		JButton randomButton = new JButton("Random data Ctrl+R");
		JButton plotButton = new JButton("Draw  Ctrl-P");
		JButton quitButton = new JButton("Quit  Ctrl-Q");
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
						10, 100);
				TSPDataPanel dataPanel = new TSPDataPanel(frame);
				dataPanel.initialized = dataPanel.readData(data);
				dataPanel.panel.update(dataPanel.panel.getGraphics());
				dataPanel.refreshData();
				// dataPanel.frame.pack();
				dataPanel.frame.setVisible(true);
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
		frame.getContentPane().add(commandPanel, "North");

		GraphPanel graphpanel = new GraphPanel(frame);
		plotButton.addActionListener(graphpanel);
		commandPanel.registerKeyboardAction(graphpanel,
				KeyStroke.getKeyStroke("control P"),
				JComponent.WHEN_IN_FOCUSED_WINDOW);
		commandPanel.registerKeyboardAction(graphpanel,
				KeyStroke.getKeyStroke("control R"),
				JComponent.WHEN_IN_FOCUSED_WINDOW);

		openButton.addActionListener(graphpanel.getTSPDataPanel());
		commandPanel.registerKeyboardAction(graphpanel.getTSPDataPanel(),
				KeyStroke.getKeyStroke("control O"),
				JComponent.WHEN_IN_FOCUSED_WINDOW);

		frame.setVisible(true);
		// frame.pack();
	}
}
