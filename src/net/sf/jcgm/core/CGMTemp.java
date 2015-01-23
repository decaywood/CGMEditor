package net.sf.jcgm.core;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.util.ArrayList;

public class CGMTemp {

	private int cgmTempId;
	private ArrayList<Shape> shapes;
	private BasicStroke drawStroke = new BasicStroke(2.0f, BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER);
	/**
	 * 
	 */
	public CGMTemp() {
	}
	public ArrayList<Shape> getShapes() {
		return shapes;
	}
	public void setShapes(ArrayList<Shape> shapes) {
		this.shapes = shapes;
	}
	public int getCgmTempId() {
		return cgmTempId;
	}
	public void setCgmTempId(int cgmTempId) {
		this.cgmTempId = cgmTempId;
	}
	public void paint(Graphics g)
	{
		if(g==null){ return; }
		Graphics2D g2d=(Graphics2D)g;
		Color c=g.getColor();
		BasicStroke bs=(BasicStroke) g2d.getStroke();
		g2d.setStroke(this.drawStroke);
		g2d.setColor(Color.BLACK);
		if(shapes!=null){
			for(int i=0;i<shapes.size();i++){
				g2d.draw(shapes.get(i));
			}
		}
		g.setColor(c);
		g2d.setStroke(bs);
	}
}
