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

public class MainWindow {
	public static void main(String[] args) {
		JFrame frame = new JFrame("TSP Solver");
		Toolkit tk = Toolkit.getDefaultToolkit();
		int xSize = ((int) tk.getScreenSize().getWidth());
		int ySize = ((int) tk.getScreenSize().getHeight());
		frame.setSize(xSize, ySize);
		frame.getContentPane().setLayout(new BorderLayout());
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JPanel commandPanel = new JPanel(new FlowLayout());
		JButton openButton = new JButton("Open  Ctrl-O");
		JButton plotButton = new JButton("Draw  Ctrl-P");
		JButton quitButton = new JButton("Quit  Ctrl-Q");
		quitButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
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
		frame.getContentPane().add(commandPanel, "North");

		GraphPanel graphpanel = new GraphPanel(frame);
		plotButton.addActionListener(graphpanel);
		commandPanel.registerKeyboardAction(graphpanel,
				KeyStroke.getKeyStroke("control P"),
				JComponent.WHEN_IN_FOCUSED_WINDOW);

		openButton.addActionListener(graphpanel.getDataPanel());
		commandPanel.registerKeyboardAction(graphpanel.getDataPanel(),
				KeyStroke.getKeyStroke("control O"),
				JComponent.WHEN_IN_FOCUSED_WINDOW);

		frame.setVisible(true);
		frame.pack();
	}
}
