/**
 * 
 */
package net.sf.jcgm.core;

import java.awt.Graphics;
import java.awt.geom.AffineTransform;

import javax.swing.JPanel;

/**
 * 2013-11-10
 */
public class CGMTempDisplay implements ICGMDisplay{
	private CGMTemp cgmTemp;
	public CGMTempDisplay(CGMTemp cgmTemp) {
		this.cgmTemp=cgmTemp;
	}
	
	@Override
	public void paint(Graphics g) {
		this.cgmTemp.paint(g);
	}
	
	@Override
	public void setCanvas(JPanel c) {}
	@Override
	public CGM getCGM() { return null; }
	@Override
	public AffineTransform getTransform() { return null; }
	@Override
	public void setMousePosition(double x, double y){}
	@Override
	public void reset() {}
	@Override
	public boolean isScaled() { return false; }
	@Override
	public void scale(Graphics g, int w, int h) {}
	@Override
	public void initTransform(Graphics g) {}
	
	public CGMTemp getCGMTemp()
	{
		return this.cgmTemp;
	}
	public void setCGMTemp(CGMTemp cgmTemp)
	{
		this.cgmTemp=cgmTemp;
	}
}
