package test;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.Arc2D;
import java.awt.geom.GeneralPath;
import java.awt.geom.Line2D;
import java.awt.geom.PathIterator;
import java.awt.geom.Point2D;

import javax.swing.JFrame;


public class testArc extends JFrame{
	public testArc()
	{		
		this.setSize(400, 500);
		this.setVisible(true);
		this.repaint();
	}
	public void paint(Graphics g)
	{
		super.paint(g);
		Arc2D arc=new Arc2D.Float();
		arc.setArc(new Point2D.Float(100,100), new Dimension(200,200), 0, 90, Arc2D.OPEN);
		Graphics2D g2d=(Graphics2D)g;
		//g2d.draw(arc);
		
		Arc2D arc2=new Arc2D.Float();
		arc2.setArc(new Point2D.Float(100,95), new Dimension(200,210), 90, -90, Arc2D.OPEN);
		//g2d.draw(arc2);
		
		
		GeneralPath gp=new GeneralPath();
		gp.append(arc.getPathIterator(new AffineTransform()), false);
		//gp.append(arc2.getPathIterator(new AffineTransform()), false);
		//gp.append(line, false);
		gp.lineTo(200, 90);
		gp.lineTo(195, 98);
		gp.lineTo(200, 105);
		gp.lineTo(200,95);
		gp.append(arc2.getPathIterator(new AffineTransform()), false);
//		g2d.draw(arc2);
//		g2d.draw(arc)
		g2d.draw(gp);
		
		System.out.println(this.printShape(arc)); 
		System.out.println(this.printShape(arc2)); 
	}
	protected String printShape(Shape s) {
		StringBuilder sb = new StringBuilder();

		PathIterator pathIterator = s.getPathIterator(new AffineTransform());
		double[] coords = new double[6];
		while (!pathIterator.isDone()) {
			int currentSegment = pathIterator.currentSegment(coords);
			switch (currentSegment) {
			case PathIterator.SEG_MOVETO:
				sb.append("MOVETO=");
				sb.append(coords[0]).append(",").append(coords[1]).append(";");
				break;
			case PathIterator.SEG_LINETO:
				sb.append("LINETO=");
				sb.append(coords[0]).append(",").append(coords[1]).append(";");
				break;
			case PathIterator.SEG_QUADTO:
				sb.append("QUADTO=");
				sb.append(coords[0]).append(",").append(coords[1]).append(",");
				sb.append(coords[2]).append(",").append(coords[3]).append(";");
				break;
			case PathIterator.SEG_CUBICTO:
				sb.append("CUBICTO=");
				sb.append(coords[0]).append(",").append(coords[1]).append(",");
				sb.append(coords[2]).append(",").append(coords[3]).append(",");
				sb.append(coords[4]).append(",").append(coords[5]).append(";");
				break;
			case PathIterator.SEG_CLOSE:
				sb.append("CLOSE=");
			}
			pathIterator.next();
		}

		return sb.toString();
	}
	public static void main(String[] argv)
	{
		testArc t=new testArc();
	}
}
