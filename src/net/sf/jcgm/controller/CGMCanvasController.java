package net.sf.jcgm.controller;

import java.awt.geom.Point2D;
import java.awt.geom.Point2D.Double;

import net.sf.jcgm.display.JCGMInternalFrame;
import net.sf.jcgm.event.EventDispatcher;
import net.sf.jcgm.processor.CGMCanvasProcessor;
import net.sf.jcgm.processor.CGMPropertyProcessor;

public class CGMCanvasController implements ICGMCanvasController {
	private JCGMInternalFrame internalFrame;
	private EventDispatcher dispatcher;
	private static int internalFrameSeq=0;
	
	public CGMCanvasController(String name){
		internalFrame=new JCGMInternalFrame(name,internalFrameSeq+1);
		this.dispatcher=new EventDispatcher();
		this.dispatcher.addEventHandler(new CGMCanvasProcessor(this));
		this.dispatcher.addEventHandler(new CGMPropertyProcessor(this));
		this.internalFrame.setEventDispatcher(this.dispatcher);
	}
	public void setInterFrameCanvasSize(int width,int height)
	{
		
	}
	public JCGMInternalFrame getJCGMInternalFrame()
	{
		return this.internalFrame;
	}
	 
	@Override
	public void zoomIn() {
		this.internalFrame.zoomIn();
	}
	@Override
	public void zoomOut() {
		this.internalFrame.zoomOut();
	}

	@Override
	public void updateStatusBar(String str) {
		this.internalFrame.updateStatusBar(str);
	}
	@Override
	public void refreshCanvas() {
		this.internalFrame.refreshCanvas();
	}
}
