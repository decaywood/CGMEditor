/**
 * 
 */
package net.sf.jcgm.display;

import javax.swing.JPanel;

import net.sf.jcgm.drawer.DrawType;
import net.sf.jcgm.event.EventDispatcher;



/**
 * 2013-11-7
 */
public class BasePanel extends JPanel{ 
	
	private static EventDispatcher eventDispatcher;
	private DrawType drawType;
	
	public BasePanel(DrawType drawType){
		this.drawType = drawType;
	}
	public void setEventDispatcher(EventDispatcher eventDispatcher) {
		BasePanel.eventDispatcher = eventDispatcher;
	}
	
	public EventDispatcher getEventDispatcher() {
		return eventDispatcher;
	}
	
	public DrawType getDrawType() {
		return drawType;
	}
	
	public void fireEvent(){
		
	}
	
}
