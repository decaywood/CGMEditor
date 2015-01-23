/**
 * 
 */
package net.sf.jcgm.event;

import java.util.ArrayList;


/**
 * 2013-11-6
 */
public class EventDispatcher {
	
	private ArrayList<EventHandler> handers=new ArrayList<EventHandler>();
	public EventDispatcher(){

	}
	
	public void fireEvent(EventParameter eventParameter){
		if(this.handers.size()==0)
			return;
		for(EventHandler hander:handers)
		{
			hander.process(eventParameter);
		}
	}
	
    public void addEventHandler(EventHandler handler)
    {
    	this.handers.add(handler);
    }
    public void removeEventHandler(int i){
    	
    }

	
}
