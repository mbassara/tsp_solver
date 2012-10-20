package pl.edu.agh.iet.tsp_solver.Gui;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.font.FontRenderContext;
import java.awt.font.LineMetrics;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
//public class MainWindow {
//
//	private JFrame frame;
//
//	/**
//	 * Launch the application.
//	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			@Override
//			public void run() {
//				try {
//					MainWindow window = new MainWindow();
//					window.frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}
//
//	/**
//	 * Create the application.
//	 */
//	public MainWindow() {
//		initialize();
//	}
//
//	/**
//	 * Initialize the contents of the frame.
//	 */
//	private void initialize() {
//		frame = new JFrame();
//		frame.getContentPane().setLayout(new BorderLayout());
//		frame.setBounds(100, 100, 481, 362);
//		// Toolkit tk = Toolkit.getDefaultToolkit();
//		// int xSize = ((int) tk.getScreenSize().getWidth());
//		// int ySize = ((int) tk.getScreenSize().getHeight());
//		frame.setSize(467, 360);
//		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//
//		// Buttons
//		Box mainBox = Box.createVerticalBox();
//		JButton button = new JButton("click");
//		button.addActionListener(new ActionListener() {
//			@Override
//			public void actionPerformed(ActionEvent arg0) {
//			}
//		});
//		mainBox.add(button);
//		JButton button_1 = new JButton("click two");
//		button_1.addActionListener(new ActionListener() {
//			@Override
//			public void actionPerformed(ActionEvent e) {
//			}
//		});
//
//		mainBox.add(button_1);
//
//		// Points Panel
//		GraphicPanel panel = new GraphicPanel(TSPData.generateData("name",
//				"comment", 10, 10), 10, 10);
//		frame.add(panel, BorderLayout.WEST);
//
//		frame.getContentPane().add(mainBox, BorderLayout.EAST);
//	}
//
//}

public class MainWindow {
	public static void main(String[] args) {
		JFrame frame = new JFrame("TSP Solver");
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

class GraphPanel implements ActionListener {
	DataPanel datapanel;
	JFrame frame;
	GraphicPanel panel;

	GraphPanel(JFrame newFrame) {
		frame = newFrame;
		panel = new GraphicPanel();
		panel.setDisplayPlot(false);
		datapanel = new DataPanel(frame);
		panel.setDataPanel(datapanel);
		frame.getContentPane().add(panel, "Center");
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (!datapanel.isInitialized()) {
			return;
		}
		datapanel.refreshData();
		panel.setDisplayPlot(true);
		panel.update(panel.getGraphics());
		frame.setSize(700, 600);
		frame.setVisible(true);
		frame.pack();
	}

	ActionListener getDataPanel() {
		return datapanel;
	}
}

class GraphicPanel extends JPanel {
	boolean display_plot;
	DataPanel d;

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		g2d.setPaint(Color.black);
		g2d.setStroke(new BasicStroke());
		g2d.setFont(new Font("Century Schoolbook", Font.PLAIN, 12));
		if (d.isInitialized() && display_plot) {
			d.refreshData();
			Float xLower = d.getXLower();
			Float xUpper = d.getXUpper();
			Float xInterval = d.getXInterval();
			Float yLower = d.getYLower();
			Float yUpper = d.getYUpper();
			Float yInterval = d.getYInterval();
			Float dx = xUpper - xLower;
			Float dy = yUpper - yLower;

			drawCenteredString(g2d, d.getTitle(), 250, 25, (float) 0.);
			drawCenteredString(g2d, d.getXTitle(), 250, 475, (float) 0.);
			drawCenteredString(g2d, d.getYTitle(), 25, 250,
					(float) -Math.PI / 2);
			drawCenteredString(g2d, xLower.toString(), 50, 475, 0);
			drawCenteredString(g2d, xUpper.toString(), 450, 475, 0);
			drawCenteredString(g2d, yLower.toString(), 25, 450, 0);
			drawCenteredString(g2d, yUpper.toString(), 25, 50, 0);

			g2d.setPaint(Color.gray);
			for (Float x = new Float(50); x <= 450; x += 400 * xInterval / dx)
				g2d.draw(new Line2D.Float(x, 450, x, 50));
			for (Float y = new Float(50); y <= 450; y += 400 * yInterval / dy)
				g2d.draw(new Line2D.Float(45, y, 450, y));

			g2d.setPaint(Color.red);
			Float diam = new Float(8);
			int num_points = d.getNumberOfPoints();
			for (int i = 0; i < num_points; i++) {
				Float ex = 400 * (d.getPoint(i).x - xLower) / dx + 50;
				ex -= diam / 2;
				Float ey = -400 * (d.getPoint(i).y - yLower) / dy + 450;
				ey -= diam / 2;
				g2d.fill(new Ellipse2D.Float(ex, ey, diam, diam));
			}
		}
	}

	void setDataPanel(DataPanel new_d) {
		d = new_d;
	}

	void setDisplayPlot(boolean new_display) {
		display_plot = new_display;
	}

	void drawCenteredString(Graphics2D g2d, String string, int x0, int y0,
			float angle) {
		FontRenderContext frc = g2d.getFontRenderContext();
		Rectangle2D bounds = g2d.getFont().getStringBounds(string, frc);
		LineMetrics metrics = g2d.getFont().getLineMetrics(string, frc);
		if (angle == 0) {
			g2d.drawString(string, x0 - (float) bounds.getWidth() / 2, y0
					+ metrics.getHeight() / 2);
		} else {
			g2d.rotate(angle, x0, y0);
			g2d.drawString(string, x0 - (float) bounds.getWidth() / 2, y0
					+ metrics.getHeight() / 2);
			g2d.rotate(-angle, x0, y0);
		}
	}
}

class DataPanel implements ActionListener {
	boolean initialized;
	int titleIndex, xTitleIndex, yTitleIndex;
	int xLowerIndex, xUpperIndex, xIntervalIndex;
	int yLowerIndex, yUpperIndex, yIntervalIndex;
	JFrame frame;
	JPanel panel;
	JLabel msg;
	String title;
	String xTitle;
	String yTitle;
	float xLower, xUpper, xInterval;
	float yLower, yUpper, yInterval;
	Point2D.Float[] points;
	int numberOfPoints;
	int numberOfPointsAllocated;
	JLabel[] paramLabels;
	JTextField[] paramFields;
	JTextField[] dataFields;

	DataPanel(JFrame newFrame) {
		initialized = false;
		numberOfPoints = numberOfPointsAllocated = 0;
		titleIndex = 0;
		xTitleIndex = 1;
		xLowerIndex = 2;
		xUpperIndex = 3;
		xIntervalIndex = 4;
		yTitleIndex = 5;
		yLowerIndex = 6;
		yUpperIndex = 7;
		yIntervalIndex = 8;
		paramLabels = new JLabel[9];
		paramLabels[titleIndex] = new JLabel("Title");
		paramLabels[xTitleIndex] = new JLabel("X Axis Title");
		paramLabels[yTitleIndex] = new JLabel("Y Axis Title");
		paramLabels[xLowerIndex] = new JLabel("X lower bound");
		paramLabels[xUpperIndex] = new JLabel("X upper bound");
		paramLabels[xIntervalIndex] = new JLabel("X tick interval");
		paramLabels[yLowerIndex] = new JLabel("Y lower bound");
		paramLabels[yUpperIndex] = new JLabel("Y upper bound");
		paramLabels[yIntervalIndex] = new JLabel("Y tick interval");
		paramFields = new JTextField[9];
		paramFields[titleIndex] = new JTextField("Test Title");
		paramFields[xTitleIndex] = new JTextField("X");
		paramFields[yTitleIndex] = new JTextField("Y");
		paramFields[xLowerIndex] = new JTextField("0");
		paramFields[xUpperIndex] = new JTextField("10");
		paramFields[xIntervalIndex] = new JTextField("1");
		paramFields[yLowerIndex] = new JTextField("0");
		paramFields[yUpperIndex] = new JTextField("10");
		paramFields[yIntervalIndex] = new JTextField("1");
		frame = newFrame;
		panel = new JPanel(new FlowLayout());
		frame.getContentPane().add(panel, "West");
	}

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
			msg = new JLabel("No file selected");
			panel.add(msg);
			return;
		}

		File datafile = fileChooser.getSelectedFile();
		initialized = readFile(datafile);
		panel.update(panel.getGraphics());
		frame.pack();
		frame.setVisible(true);
	}

	// The data file contains two sections.
	// The first section contains parameters used for configuring the graph.
	// Each line starts with a single word identifier followed by a space.
	// The rest of the line is the value.
	// It is terminated by a line containing the word "Data".
	// The second section contains the data pairs that will be graphed.
	boolean readFile(File datafile) {
		int numAllocated = 10;
		int numRead = 0;
		int numDataPoints = 0;
		String[] dataStrings = new String[numAllocated];
		BufferedReader reader = null;

		try {
			reader = new BufferedReader(new FileReader(datafile));
			String text = null;
			while ((text = reader.readLine()) != null) {
				if (numRead >= numAllocated) {
					numAllocated = 2 * numAllocated;
					String[] temp = dataStrings;
					dataStrings = new String[numAllocated];
					System.arraycopy(temp, 0, dataStrings, 0, numRead);
				}

				dataStrings[numRead] = text;
				numRead = numRead + 1;
			}
		} catch (FileNotFoundException e) {
			System.out.println("File not found");
		} catch (IOException e) {
			System.out.println("IO Exception");
		}
		try {
			if (reader != null)
				reader.close();
		} catch (IOException e) {
			System.out.println("IO Exception on close");
		}

		int thisCase = -2;
		int thisDataPoint = 0;
		for (int i = 0; i < numRead; i++) {
			String[] segments = dataStrings[i].split(" ");
			if (segments[0].equals("Title")) {
				thisCase = titleIndex;
			} else if (segments[0].equals("xTitle")) {
				thisCase = xTitleIndex;
			} else if (segments[0].equals("yTitle")) {
				thisCase = yTitleIndex;
			} else if (segments[0].equals("xLower")) {
				thisCase = xLowerIndex;
			} else if (segments[0].equals("xUpper")) {
				thisCase = xUpperIndex;
			} else if (segments[0].equals("xInterval")) {
				thisCase = xIntervalIndex;
			} else if (segments[0].equals("yLower")) {
				thisCase = yLowerIndex;
			} else if (segments[0].equals("yUpper")) {
				thisCase = yUpperIndex;
			} else if (segments[0].equals("yInterval")) {
				thisCase = yIntervalIndex;
			} else if (segments[0].equals("Data")) {
				thisCase = -1;
				numDataPoints = numRead - i - 1;
				dataFields = new JTextField[2 * numDataPoints];
				points = new Point2D.Float[numDataPoints];
			} else if (thisCase != -1) {
				thisCase = -2;
			}

			if (thisCase >= 0 && segments.length > 1) {
				String temp = segments[1];
				for (int j = 2; j < segments.length; j++)
					temp += " " + segments[j];
				paramFields[thisCase].setText(temp);
				thisCase = -2;
			} else if (thisCase == -1 && !segments[0].equals("Data")
					&& thisDataPoint < numDataPoints) {
				dataFields[2 * thisDataPoint] = new JTextField(segments[0]);
				dataFields[2 * thisDataPoint + 1] = new JTextField(segments[1]);
				thisDataPoint++;
			}
		}

		frame.getContentPane().remove(panel);
		panel = new JPanel(new GridLayout(9 + numDataPoints, 2));
		for (int i = 0; i < 9; i++) {
			panel.add(paramLabels[i]);
			panel.add(paramFields[i]);
		}
		for (int i = 0; i < numDataPoints; i++) {
			panel.add(dataFields[2 * i]);
			panel.add(dataFields[2 * i + 1]);
		}
		frame.getContentPane().add(panel, "West");

		return true;
	}

	// Read data from panel in case user made any changes
	void refreshData() {
		if (!initialized) {
			return;
		}
		title = paramFields[titleIndex].getText();
		xTitle = paramFields[xTitleIndex].getText();
		yTitle = paramFields[yTitleIndex].getText();
		xLower = Float.parseFloat(paramFields[xLowerIndex].getText());
		xUpper = Float.parseFloat(paramFields[xUpperIndex].getText());
		xInterval = Float.parseFloat(paramFields[xIntervalIndex].getText());
		yLower = Float.parseFloat(paramFields[yLowerIndex].getText());
		yUpper = Float.parseFloat(paramFields[yUpperIndex].getText());
		yInterval = Float.parseFloat(paramFields[yIntervalIndex].getText());
		for (int i = 0; i < points.length; i++) {
			Float x = Float.parseFloat(dataFields[2 * i].getText());
			Float y = Float.parseFloat(dataFields[2 * i + 1].getText());
			points[i] = new Point2D.Float(x, y);
		}
	}

	boolean isInitialized() {
		return initialized;
	}

	String getTitle() {
		return title;
	}

	String getXTitle() {
		return xTitle;
	}

	String getYTitle() {
		return yTitle;
	}

	float getXLower() {
		return xLower;
	}

	float getXUpper() {
		return xUpper;
	}

	float getXInterval() {
		return xInterval;
	}

	float getYLower() {
		return yLower;
	}

	float getYUpper() {
		return yUpper;
	}

	float getYInterval() {
		return yInterval;
	}

	int getNumberOfPoints() {
		return points.length;
	}

	Point2D.Float getPoint(int i) {
		if (i < 0) {
			i = 0;
		} else if (i >= points.length) {
			i = points.length - 1;
		}
		return points[i];
	}
}