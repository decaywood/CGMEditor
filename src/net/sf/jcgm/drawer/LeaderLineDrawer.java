package net.sf.jcgm.drawer;

import java.awt.Graphics;
import java.awt.geom.AffineTransform;
import java.awt.geom.NoninvertibleTransformException;
import java.awt.geom.Path2D;
import java.awt.geom.Point2D;
import java.awt.geom.Point2D.Double;
import java.util.Deque;
import java.util.Iterator;
import java.util.LinkedList;


import net.sf.jcgm.core.CGMDisplay;
import net.sf.jcgm.display.PropertyPanel;
import net.sf.jcgm.event.EventDispatcher;
import net.sf.jcgm.event.LeaderLineEventParameter;
import net.sf.jcgm.util.AddToCommands;
/**
 * 2013-10-31
 */
public class LeaderLineDrawer extends BaseDrawer{

	private Path2D.Double polyPath;
	private LinkedList drawElements ;
	private Deque pointDeque;
	private boolean isFirst = true;
	private int previousX;
	private int previousY;
	public LeaderLineDrawer(){
		super(DrawType.LEADER_LINE);
		polyPath = new Path2D.Double();
		pointDeque = new LinkedList();
		drawElements = new LinkedList();
	}
	public void finishDraw(PropertyPanel toolPanel,
			Point2D.Double canvasP0,Point2D.Double canvasP1){
		//fireEvent();
		isFirst = true;
		pointDeque.clear();
		drawElements.clear();
	}
	
	public void curveShape(AffineTransform affineTransform,int mousePressedX,int mousePressedY){	
		
		if(isFirst){
			previousX = mousePressedX;
			previousY = mousePressedY;
			pointDeque = new LinkedList();
			polyPath = new Path2D.Double();
			Point2D.Double p0 = new Point2D.Double(mousePressedX, mousePressedY);
			affineTransform.transform(p0, p0);
			pointDeque.offerFirst(p0);
			isFirst = false;
		}
		else {
			drawElements.offerLast(new Point2D.Double[]{new Point2D.Double(previousX,previousY),
					new Point2D.Double(mousePressedX,mousePressedY)});
			previousX = mousePressedX;
			previousY = mousePressedY;
			Point2D.Double p0 = new Point2D.Double(mousePressedX, mousePressedY);
			affineTransform.transform(p0, p0);
			pointDeque.offerFirst(p0);
		}
	}
	
	public void drawOutLine(int mousePressedX,int mousePressedY,int mouseMovedX,int mouseMovedY){
//		if(!isFirst){
//			Iterator iterator = drawElements.iterator();
//			while (iterator.hasNext()) {
//				Point2D.Double[] paintElements = (Point2D.Double[]) iterator.next() ;
//				graphics.drawLine((int)paintElements[0].x, (int)paintElements[0].y,
//						(int)paintElements[1].x, (int)paintElements[1].y);
//			}
//			graphics.drawLine(mousePressedX, mousePressedY, mouseMovedX, mouseMovedY);	
//		}
	}

}
