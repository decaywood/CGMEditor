/**
 * 
 */
package net.sf.jcgm.display;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;
import java.awt.geom.NoninvertibleTransformException;
import java.awt.geom.Point2D;
import java.text.DecimalFormat;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;


import net.sf.jcgm.core.CGMDisplay;
import net.sf.jcgm.core.CGMTempDisplay;
import net.sf.jcgm.core.ICGMDisplay;
import net.sf.jcgm.display.JCGMEditor;
import net.sf.jcgm.event.CanvasEventParameter;
import net.sf.jcgm.event.EventDispatcher;
import net.sf.jcgm.event.MouseType;

/**CGMCanvas.java
 * @author Jiang Pingchuan
 *
 *2013-9-2 15:39:53
 */
public class CGMCanvas extends JPanel implements MouseListener,MouseMotionListener{
	
	private EventDispatcher eventDispatcher;
	private ArrayList<ICGMDisplay> icgmDisplays;
	protected int width = 0, height = 0;
	private double dpi = 75;
	private JScrollPane scroll;
	private Dimension dimension=new Dimension(400, 400);
	private AffineTransform affineTransform;
	private Point2D.Double canvasP0,canvasP1;
	private Graphics graphics;

/**********************************************************************************************************************/
	public CGMCanvas()
	{
		this.addMouseMotionListener(this);
		this.addMouseListener(this);
		this.setBackground(Color.gray);
		this.icgmDisplays = new ArrayList<ICGMDisplay>();	
	}
	public CGMCanvas(JFrame v) {
		this.addMouseMotionListener(this);
		this.addMouseListener(this);
		this.setBackground(Color.gray);
		this.icgmDisplays = new ArrayList<ICGMDisplay>();
	}

	public void mouseDragged(MouseEvent e) {
		if(eventDispatcher!=null){
			CanvasEventParameter dragParameter = new CanvasEventParameter();
			CGMTempDisplay cgmTempDisplay=(CGMTempDisplay)this.icgmDisplays.get(1);
			dragParameter.setCgmTempId(cgmTempDisplay.getCGMTemp().getCgmTempId());
			dragParameter.setCanvasP(canvasP0, canvasP1);
			dragParameter.setAffineTransform(affineTransform);
			dragParameter.setMouseButton(e.getButton());
			dragParameter.setMousePositon(e.getX(), e.getY());
			dragParameter.setMouseType(MouseType.MOUSE_DRAGGED);
			eventDispatcher.fireEvent(dragParameter);
		}
	}	
	
	public void mousePressed(MouseEvent e) {
		if(eventDispatcher!=null){
			CanvasEventParameter pressParameter = new CanvasEventParameter();
			CGMTempDisplay cgmTempDisplay=(CGMTempDisplay)this.icgmDisplays.get(1);
			pressParameter.setCgmTempId(cgmTempDisplay.getCGMTemp().getCgmTempId());
			pressParameter.setCanvasP(canvasP0, canvasP1);
			pressParameter.setAffineTransform(affineTransform);
			pressParameter.setMouseButton(e.getButton());
			pressParameter.setMousePositon(e.getX(), e.getY());
			pressParameter.setMouseType(MouseType.MOUSE_PRESSED);
			eventDispatcher.fireEvent(pressParameter);	
		}
	}	
	public void mouseReleased(MouseEvent e) {
		if(eventDispatcher!=null){
			CanvasEventParameter releaseParameter = new CanvasEventParameter();
			CGMTempDisplay cgmTempDisplay=(CGMTempDisplay)this.icgmDisplays.get(1);
			releaseParameter.setCgmTempId(cgmTempDisplay.getCGMTemp().getCgmTempId());
			releaseParameter.setCanvasP(canvasP0, canvasP1);
			releaseParameter.setAffineTransform(affineTransform);
			releaseParameter.setMouseButton(e.getButton());
			releaseParameter.setMousePositon(e.getX(), e.getY());
			releaseParameter.setMouseType(MouseType.MOUSE_RELEASED);
			eventDispatcher.fireEvent(releaseParameter);	
		}
	} 
	public void mouseMoved(MouseEvent e) {
		if(eventDispatcher!=null){
			CanvasEventParameter moveParameter = new CanvasEventParameter();
			CGMTempDisplay cgmTempDisplay=(CGMTempDisplay)this.icgmDisplays.get(1);
			moveParameter.setCanvasP(canvasP0, canvasP1);
			moveParameter.setCgmTempId(cgmTempDisplay.getCGMTemp().getCgmTempId());
			moveParameter.setAffineTransform(affineTransform);
			moveParameter.setMouseButton(e.getButton());
			moveParameter.setMousePositon(e.getX(), e.getY());
			moveParameter.setMouseType(MouseType.MOUSE_MOVED);
			
			graphics = getGraphics();
			eventDispatcher.fireEvent(moveParameter);
			cgmTempDisplay.paint(getGraphics());
			
		}
//		final Point2D.Double mousePoint=new Point2D.Double();
//		mousePoint.x=e.getX();
//		mousePoint.y=e.getY();	
//		if(icgmDisplays.isEmpty()){ return; }
//		for(ICGMDisplay icgmDisplay: icgmDisplays){
//			if(icgmDisplay.getCGM()==null){
//				//editor.coordsLabel.setText("Done.");
//				return;
//			}		
//			
//			Point2D.Double oldMousePoint=new Point2D.Double(mousePoint.x,mousePoint.y);
//			try {
//				//TODO compare and set the max one
//				affineTransform=icgmDisplay.getTransform().createInverse();
//				affineTransform.transform(mousePoint, mousePoint);
//			} catch (Exception e1) {
//				e1.printStackTrace();		
//			}	
//			finally{
//				
//			}
//			icgmDisplay.setMousePosition(mousePoint.x, mousePoint.y);
//		}
//		DecimalFormat df=new DecimalFormat(".##");
		//editor.coordsLabel.setText(df.format(mousePoint.x)+","+df.format(mousePoint.y)+" VDC(s)");
	}
	public void mouseClicked(MouseEvent e) {
		CanvasEventParameter clickParameter = new CanvasEventParameter();
		clickParameter.setAffineTransform(affineTransform);
		clickParameter.setMouseButton(e.getButton());
		clickParameter.setMousePositon(e.getX(), e.getY());
		clickParameter.setMouseType(MouseType.MOUSE_CLICKED);
		eventDispatcher.fireEvent(clickParameter);
	}
	public void mouseEntered(MouseEvent e) {}
	public void mouseExited(MouseEvent e) {} 
	

	
	
/*************************************************************************************************************
 * 	
 * @param d
 */

	public void refreshCanvas(){
		paint(graphics);
	}

	@Override
	public Dimension getPreferredSize() {
		for(ICGMDisplay icgmDisplay: icgmDisplays){
			if(icgmDisplay!=null&&icgmDisplay.getCGM().getScalingMode()!=null)
			{
				this.dimension= icgmDisplay.getCGM().getSize(this.dpi);
			}
			return this.dimension;
		}
		return new Dimension(200, 200);
	}

	
	 
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		for(ICGMDisplay icgmDisplay: icgmDisplays){
			if (icgmDisplay == null){
				return;
			}
			this.initDraw(g);		
			if(icgmDisplay.getClass()==CGMDisplay.class){
				icgmDisplay.paint(g);
			}
		}
		this.scroll.getHorizontalScrollBar().repaint();
		this.scroll.getVerticalScrollBar().repaint();
		this.scroll.getCorner(ScrollPaneConstants.LOWER_RIGHT_CORNER).repaint();
	}	
	public void initDraw(Graphics g)
	{
		for(ICGMDisplay icgmDisplay: icgmDisplays){
			icgmDisplay.reset();
			int W0 = getSize().width, H0 = getSize().height;			
			if (W0 != this.width || H0 != this.height || !icgmDisplay.isScaled()) {
				this.width = W0;
				this.height = H0;
				icgmDisplay.scale(g, this.width, this.height);
			}
			icgmDisplay.initTransform(g);
		}
	}
 
	public void setCanvasP(Point2D.Double canvasP0, Point2D.Double canvasP1) {
		this.canvasP0 = canvasP0;
		this.canvasP1 = canvasP1;
	}
	
	public Point2D.Double getCanvasP0() {
		return canvasP0;
	}
	
	public Point2D.Double getCanvasP1() {
		return canvasP1;
	}
	
	
	public void setDpi(double dpi) {
		this.dpi = dpi;
	}
	
	public double getDpi() {
		return dpi;
	}
	
	
	public Dimension getDimension() {
		return dimension;
	}
	
	public ICGMDisplay getICGMDisplay(int index) {
		if(icgmDisplays.size()>0){
			return icgmDisplays.get(index);
		}
		else {
			return null;
		}
			
	}
	public void addICGMDisplay(ICGMDisplay icgmDisplay) {
		try {
			if(icgmDisplay.getTransform()!=null&&icgmDisplay.getCGM()!=null){
				setAffineTransform(icgmDisplay.getTransform().createInverse());
				icgmDisplay.getCGM().showCGMCommands();
			}
			icgmDisplay.setCanvas(this);
			this.icgmDisplays.add(icgmDisplay); 
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void setEventDispatcher(EventDispatcher eventDispatcher) {
		this.eventDispatcher = eventDispatcher;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public void setHeight(int height) {
		this.height = height;
	}
 

	public void setScroll(JScrollPane scroll) {
		this.scroll = scroll;
	}

	public void setDimension(Dimension dimension) {
		this.dimension = dimension;
	}

	public void setAffineTransform(AffineTransform affineTransform) {
		this.affineTransform = affineTransform;
	}
 
	
}

 
 
  
 
