/**
 * 
 */
package net.sf.jcgm.event;

import java.awt.Color;

import net.sf.jcgm.drawer.DrawType;


/**
 * 2013-11-7
 */
public class SymbolEventParameter extends AttributeEventParameter {

	private int unit;  
	private double size;  
	
	public SymbolEventParameter() {
		super(DrawType.SYMBOL);
	}
	  
	public void setSize(double size) {
		this.size = size;
	}
	public double getSize() {
		return size;
	}
	public void setUnit(int unit) {
		this.unit = unit;
	}
	public int getUnit() {
		return unit;
	}
}
