package net.sf.jcgm.drawer;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.util.ArrayList;

import net.sf.jcgm.core.Algorithm;
import net.sf.jcgm.core.CGM;
import net.sf.jcgm.core.CGMTemp;
import net.sf.jcgm.db.CGMManager;
import net.sf.jcgm.db.CGMTempManager;
import net.sf.jcgm.display.CGMCanvas;
import net.sf.jcgm.display.JCGMInternalFrame;
import net.sf.jcgm.event.AttributeEventParameter;
import net.sf.jcgm.event.CanvasEventParameter;
import net.sf.jcgm.event.CurveEventParameter;
import net.sf.jcgm.event.EventParameter;
import net.sf.jcgm.processor.CGMCanvasState;

/**
 * 2013-10-31
 */

public class ArcDrawer extends BaseDrawer{
	private Point2D.Double arcP0,arcP1,arcP2;
	private int pressCount=0;
	private CurveEventParameter curveEventParameter;
	private CGMManager cgmManager;
	private CGMTempManager cgmTempManager;
	
	public ArcDrawer(){
		super(DrawType.ARC);
		cgmManager = CGMManager.getCGMManager();
		cgmTempManager = CGMTempManager.getCGMTempManager();
	}
	
	@Override
	public void drawCGMTempObject(CGMCanvasState state, EventParameter eventparameter){
		switch(eventparameter.eventType){
		case EventParameter.CGM_DRAW: 
			if(state==CGMCanvasState.PAINTING){
				CanvasEventParameter parameter = (CanvasEventParameter)eventparameter;
				CGMTemp cgmTemp=this.cgmTempManager.getCGMTempByIndex(parameter.getCgmTempId());
				int mouseMovedX = parameter.getMouseX();
				int mouseMovedY = parameter.getMouseY();
				if(pressCount==2){
					ArrayList<Shape> shapes = new ArrayList<Shape>();
					shapes.add(new Ellipse2D.Double((int)arcP0.x, (int)arcP0.y, 5, 5));
					shapes.add(new Ellipse2D.Double(mouseMovedX, mouseMovedY, 5, 5));
					shapes.add(new Line2D.Double((int)arcP0.x, (int)arcP0.y,mouseMovedX, mouseMovedY));
					cgmTemp.setShapes(shapes);
				}
				if(pressCount==3){
					arcP2.x = mouseMovedX;
					arcP2.y = mouseMovedY;
					ArrayList<Shape> shapes = new ArrayList<Shape>();
					shapes.add(Algorithm.arcCompute(arcP0, arcP1, arcP2));
					shapes.add(new Ellipse2D.Double((int)arcP0.x, (int)arcP0.y, 5, 5));
					shapes.add(new Line2D.Double((int)arcP2.x, (int)arcP2.y,mouseMovedX, mouseMovedY));
					cgmTemp.setShapes(shapes);
				}
			}
			break;
		case EventParameter.CGM_ATTRIBUTE: 
			curveEventParameter = (CurveEventParameter)eventparameter;
			break;
		}			
	}
	
	@Override
	public void draw(CGMCanvasState state, EventParameter eventparameter)
	{
		
		switch(eventparameter.eventType){
		case EventParameter.CGM_DRAW: 
			CanvasEventParameter canvasEventParameter = (CanvasEventParameter)eventparameter;
			AffineTransform affineTransform = canvasEventParameter.getAffineTransform();
			if(state==CGMCanvasState.PAINTING_WAIT){
				if(pressCount==0){
					arcP0 = new Point2D.Double();
					arcP1 = new Point2D.Double();
					arcP2 = new Point2D.Double();
					pressCount++;
				}
			}
			else if(state==CGMCanvasState.PAINTING)
			{
				if(pressCount==0){
					arcP0 = new Point2D.Double();
					arcP1 = new Point2D.Double();
					arcP2 = new Point2D.Double();
					pressCount++;
				}
				if(pressCount==1){
					pressCount++;
					arcP0.x = canvasEventParameter.getMouseX();
					arcP0.y = canvasEventParameter.getMouseY();
					arcP1.x = canvasEventParameter.getMouseX();
					arcP1.y = canvasEventParameter.getMouseY();
				}
				else if(pressCount==2){
					arcP1.x = canvasEventParameter.getMouseX();
					arcP1.y = canvasEventParameter.getMouseY();	
					pressCount++;
				}
				else if(pressCount==3){
					arcP2.x = canvasEventParameter.getMouseX();
					arcP2.y = canvasEventParameter.getMouseY();
					affineTransform.transform(arcP0, arcP0);
					affineTransform.transform(arcP1, arcP1);
					affineTransform.transform(arcP2, arcP2);
					CGM cgm = cgmManager.getCGMByIndex(canvasEventParameter.getCgmId());
					System.out.println(cgm);
					CGMTemp cgmTemp=this.cgmTempManager.getCGMTempByIndex(canvasEventParameter.getCgmTempId());
					int lineType = curveEventParameter.getLineType();
					double lineWidth = curveEventParameter.getLineWidth();
					Color lineColor = curveEventParameter.getLineColor();
					Point2D.Double canvasP0 = canvasEventParameter.getCanvasP0();
					Point2D.Double canvasP1 = canvasEventParameter.getCanvasP1();
					cgm.getAddToCommands().conVertToArc(lineType, lineWidth, lineColor, canvasP0, canvasP1, arcP0, arcP1, arcP2);
					// send message arcP0 arcP1 arcP2
					cgmTemp.setShapes(null);
					pressCount = 0;
				}
			}			
			else if(state==CGMCanvasState.PAINTING_FINISHED)
			{
				return;
			}
			break;
		
		case EventParameter.CGM_ATTRIBUTE: 
			curveEventParameter = (CurveEventParameter)eventparameter;
			break;
		}		
	}
}
