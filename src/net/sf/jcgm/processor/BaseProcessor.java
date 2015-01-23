/**
 * 
 */
package net.sf.jcgm.processor;

/**
 * 2013-11-6
 */
public class BaseProcessor {
	
	private String processorName;
	private int processorID;
	
	public BaseProcessor(){
		this.processorName = "";
	}
	
	public BaseProcessor(int processorID) {
		this.processorID = processorID;
		this.processorName = "";
	}
	
	public BaseProcessor(int processorID ,String processorName) {
		this.processorID = processorID;
		this.processorName = "";
	}

}
