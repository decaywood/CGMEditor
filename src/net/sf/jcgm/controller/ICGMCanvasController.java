/**
 * 
 */
package net.sf.jcgm.controller;

import java.awt.geom.Point2D;

/**
 * 2013-11-10
 */
public interface ICGMCanvasController extends ICGMController{
	public void zoomIn();
	public void zoomOut();
	public void updateStatusBar(String str);
	public void refreshCanvas();
}
