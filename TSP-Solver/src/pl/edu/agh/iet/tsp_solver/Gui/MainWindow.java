package pl.edu.agh.iet.tsp_solver.Gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import javax.swing.UIManager;

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
		JButton openButton = new JButton("Open");
		JButton randomButton = new JButton("Random data");
		JButton plotButton = new JButton("Draw");
		JButton quitButton = new JButton("Quit");

		openButton.setToolTipText("Ctrl + O");
		randomButton.setToolTipText("Ctrl + R");
		plotButton.setToolTipText("Ctrl + D");
		quitButton.setToolTipText("Ctrl + Q");

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
		commandPanel.add(randomButton);
		frame.getContentPane().add(commandPanel, "North");

		final GraphPanel graphpanel = new GraphPanel(frame);
		plotButton.addActionListener(graphpanel);
		commandPanel.registerKeyboardAction(graphpanel,
				KeyStroke.getKeyStroke("control D"),
				JComponent.WHEN_IN_FOCUSED_WINDOW);

		ActionListener openButtonActionListener = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				JFileChooser fileChooser = new JFileChooser(".");
				fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
				int result = fileChooser.showOpenDialog(frame);
				if (result != JFileChooser.APPROVE_OPTION) {
					JLabel msg = new JLabel("No file selected");
					frame.add(msg);
					return;
				}

				File datafile = fileChooser.getSelectedFile();
				graphpanel.getTSPDataPanel().fillWithData(datafile);
			}
		};
		ActionListener randomButtonActionListener = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				graphpanel.getTSPDataPanel().fillWithRandomData();
			}
		};
		openButton.addActionListener(openButtonActionListener);
		randomButton.addActionListener(randomButtonActionListener);
		commandPanel.registerKeyboardAction(openButtonActionListener,
				KeyStroke.getKeyStroke("control O"),
				JComponent.WHEN_IN_FOCUSED_WINDOW);
		commandPanel.registerKeyboardAction(randomButtonActionListener,
				KeyStroke.getKeyStroke("control R"),
				JComponent.WHEN_IN_FOCUSED_WINDOW);

		frame.setVisible(true);
		// frame.pack();
	}
}
