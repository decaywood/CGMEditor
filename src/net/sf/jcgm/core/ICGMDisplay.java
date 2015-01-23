/**
 * 
 */
package net.sf.jcgm.core;

import java.awt.Graphics;
import java.awt.geom.AffineTransform;

import javax.swing.JPanel;


/**
 * 2013-11-2
 */
public interface ICGMDisplay {
	
	public void setCanvas(JPanel c);
	
	public CGM getCGM();
	
	public AffineTransform getTransform();
	
	public void setMousePosition(double x,double y);
	
	public void paint(Graphics g);
	
	public void reset();
	
	public boolean isScaled();
	
	public void scale(Graphics g, int w, int h);
	
	public void initTransform(Graphics g);
	
}
