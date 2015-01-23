package net.sf.jcgm.drawer;

import java.awt.Graphics;
import java.awt.geom.AffineTransform;
import java.awt.geom.NoninvertibleTransformException;
import java.awt.geom.Path2D;
import java.awt.geom.Point2D;
import java.awt.geom.Path2D.Double;
import java.util.Deque;
import java.util.LinkedList;


import net.sf.jcgm.core.CGMDisplay;
import net.sf.jcgm.core.ICGMDisplay;
import net.sf.jcgm.display.PropertyPanel;
import net.sf.jcgm.event.EventDispatcher;
import net.sf.jcgm.event.LeaderLineEventParameter;
import net.sf.jcgm.event.LineEventParameter;
import net.sf.jcgm.event.MouseType;
import net.sf.jcgm.processor.CGMCanvasProcessor;
import net.sf.jcgm.util.AddToCommands;
/**
 * 2013-10-31
 */
public class LineDrawer extends BaseDrawer{
	private int pressCount;
	private int originalX;
	private int originalY;
	private Path2D.Double polyPath;
	private Deque pointDeque;
	public LineDrawer(){
		super(DrawType.LINE);
		polyPath = new Path2D.Double();
		pointDeque = new LinkedList();
	}
	
	public void reset(){
		pressCount = 0;
		pointDeque.clear();
	}
	
	public void setOriginal(int originalX , int originalY) {
		this.originalX = originalX;
		this.originalY = originalY;
	}
	
	
	public void curveShape(AffineTransform affineTransform){
		if(pressCount==0)
			pressCount++;
		if(pressCount==2) {
			Point2D.Double p0 = new Point2D.Double(this.originalX, this.originalY);
			affineTransform.transform(p0, p0);
			pointDeque.offerFirst(p0);
//			fireEvent();
			pressCount = 0;
			pointDeque.clear();
			polyPath = new Path2D.Double();
		}
		if(pressCount==1){		
			Point2D.Double p0 = new Point2D.Double(this.originalX, this.originalY);
			affineTransform.transform(p0, p0);
			pointDeque.offerFirst(p0);
			pressCount++;
		}

	}
	
	public void drawOutLine(int mouseMovedX,int mouseMovedY){
//		if(pressCount==2){
//			graphics.drawLine(this.originalX, this.originalY, mouseMovedX, mouseMovedY);
//		}		
	}
}
