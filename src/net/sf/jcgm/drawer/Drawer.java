/**
 * 
 */
package net.sf.jcgm.drawer;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;


import net.sf.jcgm.display.BasePanel;
import net.sf.jcgm.event.EventDispatcher;

/**
 * 2013-11-9
 */
public class Drawer {
	
	private HashMap<DrawType, BaseDrawer>drawerMap;
	 
	public Drawer() {
		drawerMap = new HashMap<DrawType, BaseDrawer>();
		drawerMap.put(DrawType.ARC, new ArcDrawer());
    	drawerMap.put(DrawType.BEZIER, new BezierDrawer());
    	drawerMap.put(DrawType.BOXED_TEXT, new BoxedTextDrawer());
    	drawerMap.put(DrawType.ELLIPSE, new EllipseDrawer());
    	drawerMap.put(DrawType.LEADER_LINE, new LeaderLineDrawer());
    	drawerMap.put(DrawType.LINE, new LineDrawer());
    	drawerMap.put(DrawType.POLYGON, new PolygonDrawer());
    	drawerMap.put(DrawType.POLYLINE, new PolyLineDrawer());
    	drawerMap.put(DrawType.RECTANGLE, new RectangleDrawer());
    	drawerMap.put(DrawType.SWEEP_ARROW, new SweepArrowDrawer());
    	drawerMap.put(DrawType.SYMBOL, new SymbolDrawer());
    	drawerMap.put(DrawType.TEXT, new TextDrawer());	
	}
	
	public BaseDrawer getDrawer(DrawType drawType){
		return drawerMap.get(drawType);
	}
	 
	
}
