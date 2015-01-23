package net.sf.jcgm.drawer;

import java.awt.geom.AffineTransform;
import java.awt.geom.Path2D;
import java.awt.geom.Point2D;
import java.util.Deque;
import java.util.LinkedList;


/**
 * 2013-10-31
 */
public class PolyLineDrawer extends BaseDrawer{

	private Path2D.Double polyPath;
	private LinkedList drawElements ;
	private Deque pointDeque;
	private boolean isFirst = true;
	private int previousX;
	private int previousY;
	public PolyLineDrawer(){
		super(DrawType.POLYLINE);
		polyPath = new Path2D.Double();
		pointDeque = new LinkedList();
		drawElements = new LinkedList();
	}
	
	public void reset(){
		isFirst = true;
		pointDeque.clear();
		drawElements.clear();
	}
	
	public void finishDraw(){
		if(pointDeque.size()>0){
//			fireEvent();
		}
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
	public void drawOutLine(int mouseMovedX,int mouseMovedY){
//		if(!isFirst){
//			Iterator iterator = drawElements.iterator();
//			while (iterator.hasNext()) {
//				Point2D.Double[] paintElements = (Point2D.Double[]) iterator.next() ;
//				graphics.drawLine((int)paintElements[0].x, (int)paintElements[0].y,
//						(int)paintElements[1].x, (int)paintElements[1].y);
//			}
//			graphics.drawLine(previousX, previousY, mouseMovedX, mouseMovedY);	
//		}
	}
	
}
