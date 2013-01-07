package pl.edu.agh.iet.tsp.Gui;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.font.FontRenderContext;
import java.awt.font.LineMetrics;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class MapPanel extends JPanel {

	private static final long serialVersionUID = -4655208380459028934L;
	boolean display_plot;
	boolean solution;

	Data data;

	MapPanel(JFrame mainFrame) {

		data = new Data(mainFrame);
		setDisplayPlot(false);
		setSolution(false);
		mainFrame.setVisible(true);
		mainFrame.getContentPane().add(this);
		mainFrame.repaint();

	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		g2d.setPaint(Color.black);
		g2d.setStroke(new BasicStroke());
		g2d.setFont(new Font("Century Schoolbook", Font.PLAIN, 12));
		// if (data.isInitialized() && display_plot) {
		// data.refreshData();
		g2d.setPaint(Color.gray);
		// for (Float x = new Float(50); x <= 450; x += 400 * 50 /
		// (data.xMax - data.xMin))
		// g2d.draw(new Line2D.Float(x, 450, x, 50));
		// for (Float y = new Float(50); y <= 450; y += 400 * 50 /
		// (data.yMax - data.yMin))
		// g2d.draw(new Line2D.Float(45, y, 450, y));
		for (Float x = new Float(50); x <= 450; x += 400 * 50 / 1000)
			g2d.draw(new Line2D.Float(x, 450, x, 50));
		for (Float y = new Float(50); y <= 450; y += 400 * 50 / 1000)
			g2d.draw(new Line2D.Float(50, y, 450, y));

		drawCenteredString(g2d,
				"Travelling salesman problem using bee algorithm", 200, 20, 0);

		if (display_plot) {
			Float diam = new Float(8);
			int num_points = data.getNumberOfPoints();

			if (solution) {
				g2d.setPaint(Color.orange);
				diam = new Float(8);
				num_points = data.getNumberOfPoints();
				for (int i = 0; i < num_points - 1; i++) {

					Float ex = 400 * (data.getPoint(i).x - data.getXMin())
							/ (data.xMax - data.xMin) + 50;
					ex -= diam / 2;
					Float ey = -400 * (data.getPoint(i).y - data.getYMin())
							/ (data.yMax - data.yMin) + 450;
					ey -= diam / 2;
					Float fx = 400 * (data.getPoint(i + 1).x - data.getXMin())
							/ (data.xMax - data.xMin) + 50;
					ex -= diam / 2;
					Float fy = -400 * (data.getPoint(i + 1).y - data.getYMin())
							/ (data.yMax - data.yMin) + 450;
					ey -= diam / 2;

					g2d.setStroke(new BasicStroke(2));
					g2d.draw(new Line2D.Float(ex + 8, ey + 8, fx + 1, fy + 1));
					solution = false;
				}
			}
			try {
				// Image image = ImageIO
				// .read(new File(
				// "C:\\Users\\Madzia\\workspace\\T-Solver\\resources\\icon.png"));
				Image image = ImageIO.read(new File("resources\\icon.png"));
				g2d.setPaint(Color.red);

				for (int i = 0; i < num_points; i++) {
					Float ex = 400 * (data.getPoint(i).x - data.getXMin())
							/ (data.xMax - data.xMin) + 50;
					ex -= diam / 2;
					Float ey = -400 * (data.getPoint(i).y - data.getYMin())
							/ (data.yMax - data.yMin) + 450;
					ey -= diam / 2;
					// g2d.fill(new Ellipse2D.Float(ex, ey, diam, diam));
					int x = (int) (float) ex;
					int y = (int) (float) ey;
					g2d.drawImage(image, x - 7, y - 15, null);
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
	}

	void setDataPanel(Data new_d) {
		data = new_d;
	}

	void setDisplayPlot(boolean new_display) {
		display_plot = new_display;
	}

	void drawCenteredString(Graphics2D g2d, String string, int x0, int y0,
			float angle) {
		FontRenderContext frc = g2d.getFontRenderContext();
		Rectangle2D bounds = g2d.getFont().getStringBounds(string, frc);
		Font font = new Font("Arial", Font.PLAIN, 18);
		g2d.setFont(font);

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

	public boolean isSolution() {
		return solution;
	}

	public void setSolution(boolean solution) {
		this.solution = solution;
	}
}