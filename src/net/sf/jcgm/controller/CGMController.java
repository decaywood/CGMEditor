/**
 * 
 */
package net.sf.jcgm.controller;

import java.awt.Color;
import java.awt.geom.Point2D;
import java.awt.geom.Point2D.Double;
import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.zip.GZIPInputStream;

import javax.swing.BorderFactory;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.WindowConstants;
import javax.swing.border.BevelBorder;
import javax.swing.filechooser.FileNameExtensionFilter;


import net.sf.jcgm.core.CGM;
import net.sf.jcgm.core.CGMDisplay;
import net.sf.jcgm.display.CGMCanvas;
import net.sf.jcgm.display.DrawOptions;
import net.sf.jcgm.display.FileNew;
import net.sf.jcgm.display.JCGMEditor;
import net.sf.jcgm.display.JCGMInternalFrame;
import net.sf.jcgm.display.LayoutEditorPattern;
import net.sf.jcgm.display.LayoutFilterObject;
import net.sf.jcgm.display.LayoutGrids;
import net.sf.jcgm.display.LayoutLayerManager;
import net.sf.jcgm.display.LayoutResize;
import net.sf.jcgm.display.PropertyPanel;
import net.sf.jcgm.display.SaveSetAsDefaults;
import net.sf.jcgm.drawer.DrawType;
import net.sf.jcgm.event.EventDispatcher;
import net.sf.jcgm.processor.CGMCanvasProcessor;
import net.sf.jcgm.processor.CGMMenuProcessor;
import net.sf.jcgm.processor.CGMPropertyProcessor;
import net.sf.jcgm.properties.LayoutFilterObjecProp;
import net.sf.jcgm.util.AddToCommands;

/**
 * 2013-11-9
 */
public class CGMController implements ICGMController{

	private ICGMMenuController menuController;
//	private double dpiStep = 30;
//	private final int zoomStep=200;
	
	public CGMController(){
		this.menuController=new CGMMenuController();
		//this.eventDispatcher.addEventHandler(new CGMCanvasProcessor());
		//this.eventDispatcher.addEventHandler(new CGMPropertyProcessor());
	}	

}
