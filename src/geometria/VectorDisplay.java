package geometria;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Stroke;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class VectorDisplay extends JPanel {
	private static final long serialVersionUID = 1L;

	private static final int WIDTH = 800;
	private static final int HEIGHT = 800;
	private static final double SCALE = 100.0;

	private List<VectorData> vectors = new ArrayList<>();

	public VectorDisplay() {
		setPreferredSize(new Dimension(WIDTH, HEIGHT));
	}

	public void addVector(Point2D startPoint, Point2D endPoint, Stroke stroke, Color color) {
		vectors.add(new VectorData(startPoint, endPoint, stroke, color));
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;

		for (VectorData v : vectors) {
			g2d.setColor(v.color);
			g2d.setStroke(v.stroke);
			g2d.drawLine((int) (WIDTH / 2 + v.startPoint.getX() * SCALE),
					(int) (HEIGHT / 2 - v.startPoint.getY() * SCALE), (int) (WIDTH / 2 + v.endPoint.getX() * SCALE),
					(int) (HEIGHT / 2 - v.endPoint.getY() * SCALE));
		}

	}

	public static void dibuja(List<Vector2D> vectors, String titulo) {
		Color[] colors = new Color[] { Color.RED, Color.BLUE, Color.GREEN, Color.ORANGE, Color.YELLOW};
		int colorIndex = 0;
		JFrame frame = new JFrame(titulo);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		VectorDisplay vectorDisplay = new VectorDisplay();
		for (Vector2D v : vectors) {

			Point2D startPoint = new Point2D.Double(0.0, 0.0);
			Point2D endPoint = new Point2D.Double(v.x(), v.y());
			Stroke stroke = new BasicStroke(2.0f);
			Color color = colors[colorIndex % colors.length];
			colorIndex++;

			vectorDisplay.addVector(startPoint, endPoint, stroke, color);
		}

		frame.add(vectorDisplay);
		frame.pack();
		frame.setVisible(true);
	}

	private static class VectorData {
		Point2D startPoint;
		Point2D endPoint;
		Stroke stroke;
		Color color;

		public VectorData(Point2D startPoint, Point2D endPoint, Stroke stroke, Color color) {
			this.startPoint = startPoint;
			this.endPoint = endPoint;
			this.stroke = stroke;
			this.color = color;
		}

	}
}
