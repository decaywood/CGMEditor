package net.sf.jcgm.util;

import java.awt.Color;
import java.awt.List;
import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;

import org.omg.CORBA.PRIVATE_MEMBER;

import net.sf.jcgm.core.Command;


public class CommandsConverter {
	private ArrayList<Command> canvasParameter;
	private ArrayList<Command> canvasParameterData;
	
	public void addAndConvert(ArrayList<Command> commands){
		int i;
		canvasParameter = new ArrayList<Command>();
		canvasParameterData = new ArrayList<Command>();
		for(i = 0;i<commands.size();i++){
			if(commands.get(i).getElementClass()+commands.get(i).getElementCode()<<1!=8){
				canvasParameter.add(commands.get(i));
			}	
			else {
				canvasParameterData.add(commands.get(i));
			}
		}
	}
	public ArrayList<Command> getCanvasParameter() {
		return canvasParameter;
	}
	public ArrayList<Command> getCanvasParameterData() {
		return canvasParameterData;
	}
}
