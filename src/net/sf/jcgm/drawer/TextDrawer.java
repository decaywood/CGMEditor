package net.sf.jcgm.drawer;
/**
 * 2013-10-31
 */
import java.awt.Graphics;


import net.sf.jcgm.core.Algorithm;
import net.sf.jcgm.display.PropertyPanel;

public class TextDrawer extends BaseDrawer{
	
	public TextDrawer(){
		super(DrawType.TEXT);
	}
	
	public void reset(){
		
	}
	
	
	public void drawOutLine(Graphics graphics,int mousePressedX,int mousePressedY){
		double fontSize;
		double rotation;
	/*	switch (toolPanel.toolPanelText.getFontUnits()) {
		case 1: fontSize = toolPanel.toolPanelText.getFontSize()*1; break;
		case 2: fontSize = toolPanel.toolPanelText.getFontSize()*100; break;	
		case 3: fontSize = toolPanel.toolPanelText.getFontSize()*4; break;	
		default: fontSize = toolPanel.toolPanelText.getFontSize()*1; break;
		}
		rotation = toolPanel.toolPanelText.getFontRotation();
		Algorithm.drawCursor(graphics,mousePressedX,mousePressedY,fontSize,rotation); */
	}
	 

}
