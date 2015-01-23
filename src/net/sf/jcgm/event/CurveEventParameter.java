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
public class CurveEventParameter extends AttributeEventParameter{

	private int lineType = 1;
	private double lineWidth = 0.0;
	private Color lineColor = Color.BLACK;
	 
	 
	 
	public CurveEventParameter(DrawType drawType){
		super(drawType);
	}

	public void setLineColor(Color lineColor) {
		this.lineColor = lineColor;
	}
	public void setLineType(int lineType) {
		this.lineType = lineType;
	}
	public void setLineWidth(double lineWidth) {
		this.lineWidth = lineWidth;
	}
	
	public Color getLineColor() {
		return lineColor;
	}
	public int getLineType() {
		return lineType;
	}
	public double getLineWidth() {
		return lineWidth;
	}
	
	
}
