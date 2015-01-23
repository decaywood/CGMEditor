package net.sf.jcgm.processor;


import java.awt.event.MouseEvent;
import java.awt.geom.AffineTransform;



import net.sf.jcgm.drawer.ArcDrawer;
import net.sf.jcgm.drawer.BezierDrawer;
import net.sf.jcgm.drawer.BoxedTextDrawer;
import net.sf.jcgm.drawer.DrawType;
import net.sf.jcgm.drawer.Drawer;
import net.sf.jcgm.drawer.EllipseDrawer;
import net.sf.jcgm.drawer.LeaderLineDrawer;
import net.sf.jcgm.drawer.LineDrawer;
import net.sf.jcgm.drawer.PolyLineDrawer;
import net.sf.jcgm.drawer.PolygonDrawer;
import net.sf.jcgm.drawer.RectangleDrawer;
import net.sf.jcgm.drawer.SweepArrowDrawer;
import net.sf.jcgm.drawer.SymbolDrawer;
import net.sf.jcgm.drawer.TextDrawer;
import net.sf.jcgm.event.AttributeEventParameter;
import net.sf.jcgm.event.CanvasEventParameter;
import net.sf.jcgm.event.EventHandler;
import net.sf.jcgm.event.EventParameter;
import net.sf.jcgm.event.MouseType;
import net.sf.jcgm.controller.ICGMCanvasController;
import net.sf.jcgm.controller.ICGMController;
/**
 * 2013-10-30
 */

public class CGMCanvasProcessor extends BaseProcessor implements EventHandler{
	private CGMCanvasState currState=CGMCanvasState.PAINTING_FINISHED;
	private DrawType drawType;
	private Drawer drawer;
	private ICGMCanvasController controller;
	//private CanvasEventParameter oldEventParameter;
	public CGMCanvasProcessor(ICGMController c){
		this.controller=(ICGMCanvasController)c;
		this.drawer = new Drawer();
	}
	
	@Override
	public void process(EventParameter parameter) {
		 if(parameter.eventType==EventParameter.CGM_DRAW){
			 CanvasEventParameter canvasEventParameter = (CanvasEventParameter)parameter;
			 MouseType mouseType = canvasEventParameter.getMouseType();
			 int mouseButton = canvasEventParameter.getMouseButton();	        
			 switch (mouseType) {
			 case MOUSE_PRESSED:
				 if(canvasEventParameter.getMouseButton()==MouseEvent.BUTTON3){
					 if(currState==CGMCanvasState.PAINTING_FINISHED){
						 controller.zoomOut();
					 }
					 else {
						 currState=CGMCanvasState.PAINTING_FINISHED;
					 }					 
				 }
				 else if(canvasEventParameter.getMouseButton()==MouseEvent.BUTTON1){
					 if(currState==CGMCanvasState.PAINTING_FINISHED){
						 controller.zoomIn();
						 return;
					 }
					 else if(currState==CGMCanvasState.PAINTING_WAIT){
						 drawer.getDrawer(drawType).draw(currState, canvasEventParameter);
						 currState=CGMCanvasState.PAINTING;
					 }
					 else if(currState==CGMCanvasState.PAINTING){
						 drawer.getDrawer(drawType).draw(currState, canvasEventParameter);
					 }
				 }
			 break;
			 case MOUSE_MOVED:if(currState==CGMCanvasState.PAINTING){
				 drawer.getDrawer(drawType).drawCGMTempObject(currState, canvasEventParameter);
				 controller.refreshCanvas();
			 }
			 break;
			 case MOUSE_DRAGGED:if(currState==CGMCanvasState.PAINTING){
			 }
			 break;
			 case MOUSE_RELEASED:if(currState==CGMCanvasState.PAINTING){
			 }
			 default:
					 break;
			 }
		 }
		 else{
			 AttributeEventParameter attributeEventParameter=(AttributeEventParameter)parameter;
			 this.currState=CGMCanvasState.PAINTING_WAIT;
			 this.drawType=attributeEventParameter.getDrawType();
			 drawer.getDrawer(drawType).draw(currState, attributeEventParameter);
		 }
	
		
	}
	
}
