/**
 * 
 */
package net.sf.jcgm.event;

import java.awt.Color;
import java.awt.geom.Point2D;

import net.sf.jcgm.drawer.DrawType;


/**
 * 2013-11-7
 */
public class SweepArrowEventParameter extends AttributeEventParameter{

	private double arrowWidth = 0.2; // default :0.0
	private double headRatio = 0.33;
	private Color lineColor = Color.BLACK; //default: Black
	
	public SweepArrowEventParameter() {
		super(DrawType.SWEEP_ARROW);
	}
	
	public void setArrowWidth(double arrowWidth) {
		this.arrowWidth = arrowWidth;
	}
	public double getArrowWidth() {
		return arrowWidth;
	}
	public void setHeadRatio(double headRatio) {
		this.headRatio = headRatio;
	}
	public double getHeadRatio() {
		return headRatio;
	}
	public void setLineColor(Color lineColor) {
		this.lineColor = lineColor;
	}
	public Color getLineColor() {
		return lineColor;
	}
	 
	
}
