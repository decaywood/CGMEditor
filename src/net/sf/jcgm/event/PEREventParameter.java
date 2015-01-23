/**
 * 
 */
package net.sf.jcgm.event;

import java.awt.Color;
import java.awt.geom.Path2D;
import java.awt.geom.Point2D;
import java.util.Deque;
import java.util.LinkedList;

import net.sf.jcgm.core.HatchIndex;
import net.sf.jcgm.core.InteriorStyle;
import net.sf.jcgm.drawer.DrawType;

/**
 * 2013-11-7
 */
public class PEREventParameter extends AttributeEventParameter{
 
	private int edgeType = 1; //default : solid
	private InteriorStyle.Style interiorStyle = InteriorStyle.Style.SOLID;
	private double edgeWidth = 0.01; // default :0.0
	private HatchIndex.HatchType hatchIndex = HatchIndex.HatchType.HORIZONTAL_LINES;
	private Color edgeColor = null;
	private Color interiorColor = Color.BLACK;
	
	
	public PEREventParameter(DrawType drawType) {
		super(drawType);
	}
	
	public int getEdgeType() {
		return edgeType;
	}
	public void setEdgeType(int edgeType) {
		this.edgeType = edgeType;
	}

	public InteriorStyle.Style getInteriorStyle() {
		return interiorStyle;
	}
	
	public void setInteriorStyle(InteriorStyle.Style interiorStyle) {
		this.interiorStyle = interiorStyle;
	}

	public double getEdgeWidth() {
		return edgeWidth;
	}

	public void setEdgeWidth(double edgeWidth) {
		this.edgeWidth = edgeWidth;
	}

	public HatchIndex.HatchType getHatchIndex() {
		return hatchIndex;
	}
	
	public void setHatchIndex(HatchIndex.HatchType hatchIndex) {
		this.hatchIndex = hatchIndex;
	}

	public Color getEdgeColor() {
		return edgeColor;
	}

	public void setEdgeColor(Color edgeColor) {
		this.edgeColor = edgeColor;
	}

	public Color getInteriorColor() {
		return interiorColor;
	}

	public void setInteriorColor(Color interiorColor) {
		this.interiorColor = interiorColor;
	}
}
