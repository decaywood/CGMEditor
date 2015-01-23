/**
 * 
 */
package net.sf.jcgm.db;

import java.util.HashMap;

import net.sf.jcgm.core.CGMTemp;

/**
 * @author Jiang
 *
 */
public class CGMTempManager {
	private static int cgmTempSequence=0;
	private HashMap<Integer,CGMTemp> cgmTemps=new HashMap<Integer,CGMTemp>();
	private static CGMTempManager cgmTempManager;
	/**
	 * 
	 */
	private CGMTempManager() {
		// TODO Auto-generated constructor stub
	}
	public static CGMTempManager getCGMTempManager()
	{
		if(null==cgmTempManager)
		{
			cgmTempManager=new CGMTempManager();
		}
		return cgmTempManager;
	}
	public int createCGMTemp()
	{
		cgmTempSequence++;
		CGMTemp cgmTemp=new CGMTemp();
		this.cgmTemps.put(cgmTempSequence, cgmTemp);
		cgmTemp.setCgmTempId(cgmTempSequence);
		return cgmTempSequence;
	}
	public CGMTemp getCGMTempByIndex(int index)
	{
		return this.cgmTemps.get(index);
	}
	public boolean removeCGMTempByIndex(int index)
	{
		if(this.cgmTemps.isEmpty())
			return false;
		if(this.cgmTemps.get(index)!=null)
		{
			this.cgmTemps.remove(index);
			return true;
	    }
		else return false;
	}

}
