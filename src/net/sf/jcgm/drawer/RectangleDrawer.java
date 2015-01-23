package net.sf.jcgm.drawer;

import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;


/**
 * 2013-10-31
 */
public class RectangleDrawer extends BaseDrawer{

	private int x;
	private int y;
	private int width;
	private int height;
	private int originalX;
	private int originalY;
	
	public RectangleDrawer(){
		super(DrawType.RECTANGLE);
	}

	public void setOriginalX(int originalX) {
		this.originalX = originalX;
	}
	
	public void setOriginalY(int originalY) {
		this.originalY = originalY;
	}
	
	
	public void drawOutLine(int mouseDraggedX,int mouseDraggedY){
		width = Math.abs(originalX-mouseDraggedX);
		height = Math.abs(originalY-mouseDraggedY);
		x = Math.min(mouseDraggedX, originalX);
		y = Math.min(mouseDraggedY, originalY);	
//		graphics.drawRect(x, y, width, height);
	}
	public void finishDraw(AffineTransform affineTransform){
		Point2D.Double p0 = new Point2D.Double(x, y); 
		Point2D.Double p1 = new Point2D.Double(x+width, y+height); 
		affineTransform.transform(p0, p0);
		affineTransform.transform(p1, p1);
		//fireEvent(p0,p1);//send message
	}
}
