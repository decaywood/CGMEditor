/**
 * 
 */
package net.sf.jcgm.event;

import java.awt.Color;
import java.awt.geom.Path2D;
import java.awt.geom.Point2D;
import java.util.Deque;
import java.util.LinkedList;



import net.sf.jcgm.drawer.DrawType;


/**
 * 2013-11-7
 */
public class LineEventParameter extends AttributeEventParameter{
	
	private int lineTypeLeft = 1;
	private int lineTypeRight = 1;
	private int lineTypeCenter = 1;  
	private double lineWidth = 0.0;  
	private Color lineColor = Color.BLACK;  
	private double arrowSize = 0.2;
	private double headRiatio = 2.5;
	private boolean isHalo;
	 
	public LineEventParameter(DrawType drawType) {
		super(drawType);
	}

	public int getLineTypeLeft() {
		return lineTypeLeft;
	}

	public void setLineTypeLeft(int lineTypeLeft) {
		this.lineTypeLeft = lineTypeLeft;
	}

	public int getLineTypeRight() {
		return lineTypeRight;
	}

	public void setLineTypeRight(int lineTypeRight) {
		this.lineTypeRight = lineTypeRight;
	}

	public int getLineTypeCenter() {
		return lineTypeCenter;
	}

	public void setLineTypeCenter(int lineTypeCenter) {
		this.lineTypeCenter = lineTypeCenter;
	}

	public double getLineWidth() {
		return lineWidth;
	}

	public void setLineWidth(double lineWidth) {
		this.lineWidth = lineWidth;
	}

	 
	public Color getLineColor() {
		return lineColor;
	}

	public void setLineColor(Color lineColor) {
		this.lineColor = lineColor;
	}

	public double getArrowSize() {
		return arrowSize;
	}

	public void setArrowSize(double arrowSize) {
		this.arrowSize = arrowSize;
	}

	public double getHeadRiatio() {
		return headRiatio;
	}

	public void setHeadRiatio(double headRiatio) {
		this.headRiatio = headRiatio;
	}

	public boolean isHalo() {
		return isHalo;
	}

	public void setHalo(boolean isHalo) {
		this.isHalo = isHalo;
	}

}
