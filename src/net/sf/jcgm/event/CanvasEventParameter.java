/**
 * 
 */
package net.sf.jcgm.event;

import java.awt.Graphics;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;

import net.sf.jcgm.core.ICGMDisplay;
import net.sf.jcgm.display.CGMCanvas;
import net.sf.jcgm.display.PropertyPanel;
import net.sf.jcgm.drawer.DrawType;

/**
 * 2013-11-6
 */
public class CanvasEventParameter extends EventParameter{
	private AffineTransform affineTransform;
	private Point2D.Double canvasP0,canvasP1;
	private MouseType mouseType;
	private DrawType drawType;
	private int mouseClickCount;
	private int mouseButton;
	private int mouseX;
	private int mouseY;
	private int cgmId;
	private int cgmTempId;
	
	public CanvasEventParameter() {
		super(CGM_DRAW);
	}

	public void setDrawType(DrawType drawType) {
		this.drawType = drawType;
	}
	
	public DrawType getDrawType() {
		return drawType;
	}
	public void setMouseClickCount(int mouseClickCount) {
		this.mouseClickCount = mouseClickCount;
	}
	public int getMouseClickCount() {
		return mouseClickCount;
	}
	public int getCgmId() {
		return cgmId;
	}
	public int getCgmTempId() {
		return cgmTempId;
	}
	public void setCgmId(int cgmId) {
		this.cgmId = cgmId;
	}
	public void setCgmTempId(int cgmTempId) {
		this.cgmTempId = cgmTempId;
	}
	public void setAffineTransform(AffineTransform affineTransform) {
		this.affineTransform = affineTransform;
	}
	
	public AffineTransform getAffineTransform() {
		return affineTransform;
	}
	
	public void setMouseButton(int mouseButton) {
		this.mouseButton = mouseButton;
	}
	
	public int getMouseButton() {
		return mouseButton;
	}
	
	public MouseType getMouseType() {
		return mouseType;
	}
	
	public void setMouseType(MouseType mouseType) {
		this.mouseType = mouseType;
	}
	
	public void setMousePositon(int x, int y)
	{
		this.mouseX=x;
		this.mouseY=y;
	}
	
	public int getMouseX()
	{
		return this.mouseX;
	}
	
	public int getMouseY()
	{
		return this.mouseY;
	}
	public Point2D.Double getCanvasP0() {
		return canvasP0;
	}
	public Point2D.Double getCanvasP1() {
		return canvasP1;
	}
	public void setCanvasP(Point2D.Double canvasP0, Point2D.Double canvasP1) {
		this.canvasP0 = canvasP0;
		this.canvasP1 = canvasP1;
	}
	
}
