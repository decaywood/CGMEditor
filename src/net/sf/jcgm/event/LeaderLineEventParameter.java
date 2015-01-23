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
public class LeaderLineEventParameter extends AttributeEventParameter{
	private Color lineColor = Color.BLACK;
	private int lineTypeLeft = 1;
	private int lineTypeCenter = 1;
	private int lineTypeRight = 1;
	private double lineWidth = 0.0;
	private double fontSize = 0.2;
	private double arrowSize = 0.2;
	private int unit = 2;
	private boolean isHalo;
	private int textType = 1; //helvetica 1 times 2 courier 3 symbol
	private int textAlignment = 1;

	 
	
	public LeaderLineEventParameter() {
		super(DrawType.LEADER_LINE);
	}

	public Color getLineColor() {
		return lineColor;
	}
	
	public void setLineColor(Color lineColor) {
		this.lineColor = lineColor;
	}
	
	public int getLineTypeLeft() {
		return lineTypeLeft;
	}

	public void setLineTypeLeft(int lineTypeLeft) {
		this.lineTypeLeft = lineTypeLeft;
	}

	public int getLineTypeCenter() {
		return lineTypeCenter;
	}

	public void setLineTypeCenter(int lineTypeCenter) {
		this.lineTypeCenter = lineTypeCenter;
	}

	public int getLineTypeRight() {
		return lineTypeRight;
	}

	public void setLineTypeRight(int lineTypeRight) {
		this.lineTypeRight = lineTypeRight;
	}

	public double getLineWidth() {
		return lineWidth;
	}

	public void setLineWidth(double lineWidth) {
		this.lineWidth = lineWidth;
	}

	public double getFontSize() {
		return fontSize;
	}

	public void setFontSize(double fontSize) {
		this.fontSize = fontSize;
	}

	public double getArrowSize() {
		return arrowSize;
	}

	public void setArrowSize(double arrowSize) {
		this.arrowSize = arrowSize;
	}

	public int getUnit() {
		return unit;
	}

	public void setUnit(int unit) {
		this.unit = unit;
	}

	public boolean isHalo() {
		return isHalo;
	}

	public void setHalo(boolean isHalo) {
		this.isHalo = isHalo;
	}

	public int getTextType() {
		return textType;
	}

	public void setTextType(int textType) {
		this.textType = textType;
	}

	public int getTextAlignment() {
		return textAlignment;
	}

	public void setTextAlignment(int textAlignment) {
		this.textAlignment = textAlignment;
	}
}
