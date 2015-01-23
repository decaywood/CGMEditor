/**
 * 
 */
package net.sf.jcgm.core;

import java.util.ArrayList;
import java.util.Deque;

/**
 * @author Jiang
 *
 */
public class CGMObject {
	
	private ArrayList<Command> attributes;
	private ArrayList<Command> graphics;
	private CGMObject nextRefCGMObject;
	private CGMObject previousRefCGMObject;

	public void paintCGMObject(CGMDisplay d){
		if(!this.graphics.isEmpty()){
			if(graphics.get(0).getElementClass()==0){
				paintGraphics(d);
				paintAttributes(d);
			}
			else {
				paintAttributes(d);
				paintGraphics(d);	
			}	
		}
		else {
			paintAttributes(d);
		}
	}
	
	private void paintAttributes(CGMDisplay d){
		if(!this.attributes.isEmpty()){
			for(Command c:this.getAttributes()){
				c.paint(d);
			}
		}
	}
	
	private void paintGraphics(CGMDisplay d){
		if(!this.graphics.isEmpty()){
			for(Command c:this.getGraphics()){	
				c.paint(d);
			}
		}
	}
	
	public CGMObject() {
		this.attributes = new ArrayList<Command>();
		this.graphics = new ArrayList<Command>();
	}
	
	public void addGraphics(Command command){
		if(command!=null)
			this.graphics.add(command);
	}
	
	public void addAttributes(Command command){
		if(command!=null)
			this.attributes.add(command);
	}
	
	public ArrayList<Command> getAttributes() {
		return attributes;
	}
	
	public ArrayList<Command> getGraphics() {
		return graphics;
	}
	
	public void setNextRefCGMObject(CGMObject nextRefCGMObject) {
		this.nextRefCGMObject = nextRefCGMObject;
	}
	
	public void setPreviousRefCGMObject(CGMObject previousRefCGMObject) {
		this.previousRefCGMObject = previousRefCGMObject;
	}
	
	public CGMObject getNextRefCGMObject() {
		return nextRefCGMObject;
	}
	
	public CGMObject getPreviousRefCGMObject() {
		return previousRefCGMObject;
	}
	
	public boolean hasNextRefCGMObject(){
		return nextRefCGMObject!=null;
	}
	
	public boolean hasPreviousRefCGMObject(){
		return previousRefCGMObject!=null;
	}
}
