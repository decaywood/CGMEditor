/**
 * 
 */
package net.sf.jcgm.event;

/**
 * 2013-11-6
 */
public class EventParameter {
	
	public static final int CGM_MENU = 1;
	public static final int CGM_DRAW = 2;
	public static final int CGM_ATTRIBUTE = 3;
	public int eventType;  

	public EventParameter(int eventType) {
		if(eventType!=1&&eventType!=2&&eventType!=3){
			System.out.println("error event Type");
			return;
		}
		this.eventType = eventType;
	}
}
