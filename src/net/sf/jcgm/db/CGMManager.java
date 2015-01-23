/**
 * 
 */
package net.sf.jcgm.db;

import java.util.HashMap;

import net.sf.jcgm.core.CGM;

/**
 * @author Jiang
 *
 */
public class CGMManager {
	private static int cgmSequence=0;
	private HashMap<Integer,CGM> cgms=new HashMap<Integer,CGM>();
	private static CGMManager cgmManager;
	/**
	 * 
	 */
	private CGMManager() {
		// TODO Auto-generated constructor stub
	}
	public static CGMManager getCGMManager()
	{
		if(null==cgmManager)
		{
			cgmManager=new CGMManager();
		}
		return cgmManager;
	}
	public int createCGM()
	{
		CGM cgm=new CGM();
		this.cgms.put(++cgmSequence, cgm);
		return cgmSequence;
	}
	public CGM getCGMByIndex(int index)
	{
		return this.cgms.get(index);
	}
	public boolean removeCGMByIndex(int index)
	{
		if(this.cgms.isEmpty())
			return false;
		if(this.cgms.get(index)!=null)
		{
			this.cgms.remove(index);
			return true;
	    }
		else return false;
	}
}
