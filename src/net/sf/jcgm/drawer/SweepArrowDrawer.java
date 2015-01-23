package net.sf.jcgm.drawer;

import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;


/**
 * 2013-10-31
 */
public class SweepArrowDrawer extends BaseDrawer{

	private Point2D.Double arcP0; 
	private Point2D.Double arcP1;
	private Point2D.Double arcP2;
	private int pressCount;
	
	public SweepArrowDrawer(){
		super(DrawType.SWEEP_ARROW);
	}

	public void reset(){
		arcP0 = null;
		arcP1 = null;
		arcP2 = null;
		pressCount = 0;
	}
	
	 
	
	public void curveShape(AffineTransform affineTransform, int mousePressedX,int mousePressedY){
		if(pressCount==0){
			arcP0 = new Point2D.Double();
			arcP1 = new Point2D.Double();
			arcP2 = new Point2D.Double();
			pressCount++;
		}
		if(pressCount==1){
			pressCount++;
			arcP0.x = mousePressedX;
			arcP0.y = mousePressedY;
			arcP1.x = mousePressedX;
			arcP1.y = mousePressedY;
		}
		else if(pressCount==2){
			arcP1.x = mousePressedX;
			arcP1.y = mousePressedY;
			pressCount++;
		}
		else if(pressCount==3){
			arcP2.x = mousePressedX;
			arcP2.y = mousePressedY;
			affineTransform.transform(arcP0, arcP0);
			affineTransform.transform(arcP1, arcP1);
			affineTransform.transform(arcP2, arcP2);
//			fireEvent();// send message arcP0, arcP1, arcP2
			pressCount = 0;
		}
		
	}
	
	public void drawOutLine(int mouseMovedX,int mouseMovedY){
		if(pressCount==2){
//			graphics.fillOval((int)arcP0.x, (int)arcP0.y, 5, 5);
//			graphics.fillOval(mouseMovedX, mouseMovedY, 5, 5);
//			graphics.drawLine((int)arcP0.x, (int)arcP0.y,mouseMovedX, mouseMovedY);
		}
		if(pressCount==3){
			arcP2.x = mouseMovedX;
			arcP2.y = mouseMovedY;
//			graphics.draw(Algorithm.arcCompute(arcP0, arcP1, arcP2));
//			graphics.fillOval((int)arcP0.x, (int)arcP0.y, 5, 5);
//			graphics.fillOval((int)arcP2.x, (int)arcP2.y, 5, 5);
		}	
	}
}
