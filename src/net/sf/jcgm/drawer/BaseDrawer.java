/**
 * 
 */
package net.sf.jcgm.drawer;

import java.util.Set;


import net.sf.jcgm.display.CGMCanvas;
import net.sf.jcgm.event.EventDispatcher;
import net.sf.jcgm.event.EventParameter;
import net.sf.jcgm.processor.CGMCanvasState;

/**
 * 2013-11-6
 */
abstract class BaseDrawer {
	 
	private DrawType drawerType;
	
	public BaseDrawer(DrawType drawerType) {
		
		this.drawerType = drawerType;
	}
	
	public DrawType getDrawerType() {
		return drawerType;
	}
	
	public void setDrawerType(DrawType drawerType) {
		this.drawerType = drawerType;
	}
	
	abstract void draw(CGMCanvasState state, EventParameter parameter);
		
	
	
	abstract void drawCGMTempObject(CGMCanvasState state, EventParameter eventparameter)
		
	
	


}
