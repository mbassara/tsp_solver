package pl.edu.agh.iet.tsp_solver.Gui;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.font.FontRenderContext;
import java.awt.font.LineMetrics;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;

import javax.swing.JPanel;

public class MapPanel extends JPanel {

	private static final long serialVersionUID = -4655208380459028934L;
	boolean display_plot;
	TSPDataPanel data;

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		g2d.setPaint(Color.black);
		g2d.setStroke(new BasicStroke());
		g2d.setFont(new Font("Century Schoolbook", Font.PLAIN, 12));
		if (data.isInitialized() && display_plot) {
			data.refreshData();
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

			g2d.setPaint(Color.red);
			Float diam = new Float(8);
			int num_points = data.getNumberOfPoints();
			for (int i = 0; i < num_points; i++) {
				Float ex = 400 * (data.getPoint(i).x - data.getXMin())
						/ (data.xMax - data.xMin) + 50;
				ex -= diam / 2;
				Float ey = -400 * (data.getPoint(i).y - data.getYMin())
						/ (data.yMax - data.yMin) + 450;
				ey -= diam / 2;
				g2d.fill(new Ellipse2D.Float(ex, ey, diam, diam));
			}
		}
	}

	void setDataPanel(TSPDataPanel new_d) {
		data = new_d;
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