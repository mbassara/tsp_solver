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

import pl.edu.agh.iet.tsp_solver.Model.TSPData;

public class MapPanel extends JPanel {

	private static final long serialVersionUID = -4655208380459028934L;
	boolean display_plot;
	TSPData data = null;

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		if (data == null)
			return;

		Graphics2D g2d = (Graphics2D) g;
		g2d.setPaint(Color.black);
		g2d.setStroke(new BasicStroke());
		g2d.setFont(new Font("Century Schoolbook", Font.PLAIN, 12));
		if (display_plot) {
			g2d.setPaint(Color.gray);
			for (double x = 50.0; x <= 450.0; x += 400 * 50 / 1000)
				g2d.draw(new Line2D.Double(x, 450, x, 50));
			for (double y = 50.0; y <= 450.0; y += 400 * 50 / 1000)
				g2d.draw(new Line2D.Double(45, y, 450, y));

			g2d.setPaint(Color.red);
			Float diam = new Float(8);
			int num_points = data.getDimension();
			for (int i = 0; i < num_points; i++) {
				double ex = 400 * (data.getNode(i).getX() - data.getMinX())
						/ (data.getMaxX() - data.getMinX()) + 50;
				ex -= diam / 2;
				double ey = -400 * (data.getNode(i).getY() - data.getMinY())
						/ (data.getMaxY() - data.getMinY()) + 450;
				ey -= diam / 2;
				g2d.fill(new Ellipse2D.Double(ex, ey, diam, diam));
			}
		}
	}

	void setData(TSPData tspData) {
		this.data = tspData;
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