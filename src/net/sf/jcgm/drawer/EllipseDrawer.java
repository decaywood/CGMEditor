package net.sf.jcgm.drawer;

import java.awt.Graphics;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;
import java.awt.geom.Point2D.Double;


import net.sf.jcgm.core.CGMDisplay;
import net.sf.jcgm.core.ICGMDisplay;
import net.sf.jcgm.event.EventDispatcher;
import net.sf.jcgm.event.PEREventParameter;
import net.sf.jcgm.util.AddToCommands;
/**
 * 2013-10-31
 */
public class EllipseDrawer extends BaseDrawer{
	
	private int x;
	private int y;
	private int width;
	private int height;
	private int originalX;
	private int originalY;
 
	
	public void setOriginalX(int originalX) {
		this.originalX = originalX;
	}
	
	public void setOriginalY(int originalY) {
		this.originalY = originalY;
	}
	
	public EllipseDrawer(){
		super(DrawType.ELLIPSE);
	}
	
	public void drawOutLine(int mouseDraggedX,int mouseDraggedY){
		width = Math.abs(originalX-mouseDraggedX);
		height = Math.abs(originalY-mouseDraggedY);
		x = Math.min(mouseDraggedX, originalX);
		y = Math.min(mouseDraggedY, originalY);	
	//	eventDispatcher.fireEvent(perEventParameter);
	//	graphics.drawOval(x, y, width, height);
	}
	public void finishDraw(AffineTransform affineTransform){
		Point2D.Double p0 = new Point2D.Double(x+width/2, y+height/2); 
		Point2D.Double p1 = new Point2D.Double(x, y+height/2);
		Point2D.Double p2 = new Point2D.Double(x+width/2, y+height);
		affineTransform.transform(p0, p0);
		affineTransform.transform(p1, p1);
		affineTransform.transform(p2, p2);
	}
 
}
