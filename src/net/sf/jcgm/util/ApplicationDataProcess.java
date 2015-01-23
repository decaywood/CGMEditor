package net.sf.jcgm.util;

import java.util.Random;

import net.sf.jcgm.core.ApplicationData;
import net.sf.jcgm.core.Command;

public class ApplicationDataProcess {
//============================================ generator ==================	
	private int packageIndex;
	private boolean isBegin = true;
	
	public void generateApplicationData(CGMObjectConverter cgmObjectConverter){
		if(isBegin){
			packageIndex = (int)Math.random()*100+17400;
		}
		else {
			packageIndex+=1;
			isBegin = !isBegin;
		}
		cgmObjectConverter.addAndConvert(new ApplicationData(packageIndex,""));
	}
//============================================ processor =============================
	protected int beginApplicationDataIndex = -1;
	protected int previousApplicationDataIndex = -1;
	protected boolean isBegined;
	
	private void reset(){
		beginApplicationDataIndex = -1;
		previousApplicationDataIndex = -1;
		isBegined = false;
	}
	
	
	public void Process(Command command){
		ApplicationData applicationDataCommand = (ApplicationData)command;
		if(!isBegined&&applicationDataCommand.getIdentifier()!=17506){
			beginApplicationDataIndex = applicationDataCommand.getIdentifier();
			isBegined = true;
		}
		else{
			previousApplicationDataIndex = applicationDataCommand.getIdentifier();
			if(beginApplicationDataIndex+1==previousApplicationDataIndex){
				reset();
			}
		}
	}
}
