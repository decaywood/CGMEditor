package net.sf.jcgm.util;

/**
 * 
 */

import java.util.ArrayList;

import net.sf.jcgm.core.ApplicationData;
import net.sf.jcgm.core.BackgroundColour;
import net.sf.jcgm.core.CGM;
import net.sf.jcgm.core.CGMObject;
import net.sf.jcgm.core.Command;
import net.sf.jcgm.core.InteriorStyle;
import net.sf.jcgm.core.PictureDescriptorElement;
import net.sf.jcgm.core.RestrictedTextType;
/**
 * @author Jiang
 *
 */
public class CGMObjectConverter {
	private ArrayList<CGMObject> objects; 
	private ApplicationDataProcess applicationDataProcess;
	private AttributeTable attributeTable;
	private PicDescriptorTable picDescriptorTable;
	private MetafileDescriptorTable metafileDescriptorTable;
	
	public CGMObjectConverter(ArrayList<CGMObject> objects){
		this.objects = objects;
		attributeTable = new AttributeTable();
		picDescriptorTable = new PicDescriptorTable();
		metafileDescriptorTable = new MetafileDescriptorTable();
		applicationDataProcess = new ApplicationDataProcess();
	}
	
	private void resetVal() {
		this.objects = new ArrayList<CGMObject>();
		attributeTable.resetTable();
		picDescriptorTable.resetTable();
	}
	
	public void addAndConvert(Command command)
	{
		if(this.objects!=null){
			int elementClass = command.getElementClass();
			switch (elementClass) {
			case 0: processDelimiterElements(command); break;
			case 1:	processMetafileDescriptor(command); break;
			case 2: processPictureDescriptorElements(command); break;
			case 3: processControlElements(command); break;
			case 4: processGraphicalPrimitiveElements(command); break;
			case 5: processAttributeElements(command); break;
			case 7: processExternalElements(command); break;
			default:System.out.println("Unknow Class command : -- "+command.getElementClass());break;
			}
		}
	}
	
	public ArrayList<CGMObject> returnedCgmCommandsToObjects(ArrayList<Command> commands)
	{
		resetVal();
		
		for (Command command : commands) {
			int elementClass = command.getElementClass();
			switch (elementClass) {
			case 0: processDelimiterElements(command); break;
			case 1:	processMetafileDescriptor(command); break;
			case 2: processPictureDescriptorElements(command); break;
			case 3: processControlElements(command); break;
			case 4: processGraphicalPrimitiveElements(command); break;
			case 5: processAttributeElements(command); break;
			case 7: processExternalElements(command); break;
			default:System.out.println("Unknow Class command : -- "+command.getElementClass());break;
			}
		}
		return objects;
	}
	
	
	public void cgmCommandsToObjects(ArrayList<Command> commands)
	{
		resetVal();
		
		for (Command command : commands) {
			int elementClass = command.getElementClass();
			switch (elementClass) {
			case 0: processDelimiterElements(command); break;
			case 1:	processMetafileDescriptor(command); break;
			case 2: processPictureDescriptorElements(command); break;
			case 3: processControlElements(command); break;
			case 4: processGraphicalPrimitiveElements(command); break;
			case 5: processAttributeElements(command); break;
			case 7: processExternalElements(command); break;
			default:System.out.println("Unknow Class command : -- "+command.getElementClass());break;
			}
		}
	}


	private void processDelimiterElements(Command command) {
		CGMObject object=new CGMObject();
		int elementCode = command.getElementCode();
		switch (elementCode) {
		case 1: metafileDescriptorTable.setMetafileCgmObject(object); break;
		
		case 3:	picDescriptorTable.setPictureDescCgmObject(object); 
				metafileDescriptorTable.addAttributeForObject();
				break;
		
		case 4:	picDescriptorTable.addAttributeForObject();
				break;
		default:break;
		}
		object.addGraphics(command);
		objects.add(object);
	}
	
	private void processMetafileDescriptor(Command command) {
		metafileDescriptorTable.addMetafileDescriptor(command);
	}
	
	private void processPictureDescriptorElements(Command command) {
		picDescriptorTable.addPicDescriptor(command);
	}
	
	private void processControlElements(Command command) {
		CGMObject object=new CGMObject();
		object.addAttributes(command);
		objects.add(object);	
	}
	
	private void processGraphicalPrimitiveElements(Command command) {
		CGMObject object=new CGMObject();
		attributeTable.setAttributeCgmObject(object);
		int elementCode = command.getElementCode();
		if(applicationDataProcess.isBegined){
			int currentCommandIndex = objects.size()-1;
			int commandClass = -1;
			int applicationIndex = -1;
			do{
				CGMObject currentCgmObject = null;
				if(currentCommandIndex>0)
					currentCgmObject = objects.get(currentCommandIndex);
				else {
					break;
				}
				if(currentCgmObject.getGraphics().size()>0){
					commandClass = currentCgmObject.getGraphics().get(0).getElementClass();
					if(commandClass==4){
						currentCgmObject.setNextRefCGMObject(object);
						object.setPreviousRefCGMObject(currentCgmObject);
						break;
					}
					else if (commandClass==7) {
						applicationIndex = ((ApplicationData)currentCgmObject.getGraphics().get(0)).getIdentifier();
					}
				}
				currentCommandIndex--;
			}while(applicationIndex!=applicationDataProcess.beginApplicationDataIndex);
		}
		attributeTable.addAttributeForObject(elementCode);
		object.addGraphics(command);
		objects.add(object);
	}

	private void processAttributeElements(Command command) {
		attributeTable.addAttribute(command);
	}


	private void processExternalElements(Command command) {
		CGMObject object = new CGMObject();
		objects.add(object);
		int elementCode = command.getElementCode();
		if(elementCode==1){
			System.out.println("Command message is not used!!! ");
		}
		else if(elementCode==2){      //Application Data
			applicationDataProcess.Process(command);
			object.addGraphics(command);
		}
		
	}
	
	
	public void setObjects(ArrayList<CGMObject> objects) {
		this.objects = objects;
	}
	
	public ArrayList<CGMObject> getObjects() {
		return objects;
	}
	
	public void generateApplicationData(){
		applicationDataProcess.generateApplicationData(this);
	}
	
}
