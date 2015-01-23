package net.sf.jcgm.util;

import net.sf.jcgm.core.BackgroundColour;
import net.sf.jcgm.core.CGMObject;
import net.sf.jcgm.core.ColourSelectionMode;
import net.sf.jcgm.core.Command;
import net.sf.jcgm.core.DeviceViewportSpecificationMode;
import net.sf.jcgm.core.EdgeWidthSpecificationMode;
import net.sf.jcgm.core.InteriorStyleSpecificationMode;
import net.sf.jcgm.core.LineAndEdgeTypeDefinition;
import net.sf.jcgm.core.LineWidthSpecificationMode;
import net.sf.jcgm.core.MarkerSizeSpecificationMode;
import net.sf.jcgm.core.PictureDescriptorElement;
import net.sf.jcgm.core.ScalingMode;
import net.sf.jcgm.core.VDCExtent;

public class PicDescriptorTable {
	private CGMObject pictureDescCgmObject;
	private ScalingMode scalingMode; //
	private ColourSelectionMode colourSelectionMode; //
	private LineWidthSpecificationMode lineWidthSpecificationMode; //
	private MarkerSizeSpecificationMode markerSizeSpecificationMode; //
	private EdgeWidthSpecificationMode edgeWidthSpecificationMode;  //
	private VDCExtent vdcExtent; //
	private BackgroundColour backgroundColour; //
	private DeviceViewportSpecificationMode deviceViewportSpecificationMode;
	private InteriorStyleSpecificationMode interiorStyleSpecificationMode; //
	private LineAndEdgeTypeDefinition lineAndEdgeTypeDefinition;
  
	public void resetTable(){
		scalingMode = null;                               
		colourSelectionMode = null;                       
		lineWidthSpecificationMode = null;                  
		markerSizeSpecificationMode = null;                 
		edgeWidthSpecificationMode = null;
		vdcExtent = null;
		backgroundColour = null;
		deviceViewportSpecificationMode = null;
		interiorStyleSpecificationMode = null;
		lineAndEdgeTypeDefinition = null;
	}
	
	public void addPicDescriptor(Command command){
		int elementCode = command.getElementCode();
		switch (elementCode) {
		case 1: scalingMode = (ScalingMode) command; break;		
		case 2: colourSelectionMode = (ColourSelectionMode) command; break;
		case 3: lineWidthSpecificationMode = (LineWidthSpecificationMode) command; break;
		case 4: markerSizeSpecificationMode = (MarkerSizeSpecificationMode) command; break; 
		case 5: edgeWidthSpecificationMode = (EdgeWidthSpecificationMode) command; break; 
		case 6: vdcExtent = (VDCExtent) command; break; 
		case 7: backgroundColour = (BackgroundColour) command; break; 
		case 9: deviceViewportSpecificationMode = (DeviceViewportSpecificationMode) command; break; 
		case 16: interiorStyleSpecificationMode = (InteriorStyleSpecificationMode) command; break;
		case 17:lineAndEdgeTypeDefinition = (LineAndEdgeTypeDefinition) command; break;  
		default: System.out.println("Command:  "+command.getElementClass()*100
				+command.getElementCode()+"  is Not Found in PicDescriptor Table !!!"); break;
		}
	}
	
	public void addAttributeForObject(){
		pictureDescCgmObject.addAttributes(scalingMode);
		pictureDescCgmObject.addAttributes(colourSelectionMode);
		pictureDescCgmObject.addAttributes(lineWidthSpecificationMode);
		pictureDescCgmObject.addAttributes(markerSizeSpecificationMode);
		pictureDescCgmObject.addAttributes(edgeWidthSpecificationMode);
		pictureDescCgmObject.addAttributes(backgroundColour);
		pictureDescCgmObject.addAttributes(vdcExtent);
		pictureDescCgmObject.addAttributes(interiorStyleSpecificationMode);
	}
	
	public void setPictureDescCgmObject(CGMObject pictureDescCgmObject) {
		this.pictureDescCgmObject = pictureDescCgmObject;
	}
	
	public CGMObject getPictureDescCgmObject() {
		return pictureDescCgmObject;
	}
	
}
