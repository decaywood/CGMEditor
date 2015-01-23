/**
 * 
 */
package net.sf.jcgm.event;

import net.sf.jcgm.drawer.DrawType;


/**
 * 2013-11-7
 */
public class AttributeEventParameter extends EventParameter{
	private DrawType drawType;
	private int cgmid;
	
	public AttributeEventParameter(DrawType drawType) {
		super(CGM_ATTRIBUTE);
		this.drawType = drawType;
	}
	
	public DrawType getDrawType() {
		return drawType;
	}
	
}
