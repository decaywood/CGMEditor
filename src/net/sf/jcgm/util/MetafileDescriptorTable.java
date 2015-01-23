package net.sf.jcgm.util;

import java.awt.font.GlyphVector;

import javax.swing.text.Segment;

import net.sf.jcgm.core.CGMObject;
import net.sf.jcgm.core.CharacterCodingAnnouncer;
import net.sf.jcgm.core.CharacterSetList;
import net.sf.jcgm.core.ColourIndexPrecision;
import net.sf.jcgm.core.ColourModel;
import net.sf.jcgm.core.ColourPrecision;
import net.sf.jcgm.core.ColourValueExtent;
import net.sf.jcgm.core.Command;
import net.sf.jcgm.core.FontList;
import net.sf.jcgm.core.IndexPrecision;
import net.sf.jcgm.core.IntegerPrecision;
import net.sf.jcgm.core.MaximumColourIndex;
import net.sf.jcgm.core.MaximumVDCExtent;
import net.sf.jcgm.core.MetafileDefaultsReplacement;
import net.sf.jcgm.core.MetafileDescription;
import net.sf.jcgm.core.MetafileElementList;
import net.sf.jcgm.core.MetafileVersion;
import net.sf.jcgm.core.NamePrecision;
import net.sf.jcgm.core.PictureDescriptorElement;
import net.sf.jcgm.core.RealPrecision;
import net.sf.jcgm.core.VDCType;



public class MetafileDescriptorTable {
	private CGMObject metafileCgmObject;
	private MetafileVersion metafileVersion;  //1
	private MetafileDescription metafileDescription; //2
	private VDCType vdcType;//3
	private IntegerPrecision integerPrecision;//4
	private RealPrecision realPrecision;//5
	private IndexPrecision indexPrecision;//6
	private ColourPrecision colourPrecision;//7
	private ColourIndexPrecision colourIndexPrecision;//8
	private MaximumColourIndex maximumColourIndex;//9
	private ColourValueExtent colourValueExtent;//10
	private MetafileElementList metafileElementList;//11
	private MetafileDefaultsReplacement metafileDefaultsReplacement;//12
	private FontList fontList;//13
	private CharacterSetList characterSetList;//14
	private CharacterCodingAnnouncer characterCodingAnnouncer;//15
	private NamePrecision namePrecision;//16
	private MaximumVDCExtent maximumVDCExtent;//17
	private ColourModel colourModel;//19

	public void resetMetafileDescriptorTable(){
		metafileVersion = null;  //1
	    metafileDescription = null; //2
		vdcType = null;//3
		integerPrecision = null;//4
		realPrecision = null;//5
		indexPrecision = null;//6
		colourPrecision = null;//7
		colourIndexPrecision = null;//8
		maximumColourIndex = null;//9
		colourValueExtent = null;//10
		metafileElementList = null;//11
		metafileDefaultsReplacement = null;//12
		fontList = null;//13
		characterSetList = null;//14
		characterCodingAnnouncer = null;//15
		namePrecision = null;//16
		maximumVDCExtent = null;//17
		colourModel = null;//19
	}
	public void addMetafileDescriptor(Command command){
		int elementCode = command.getElementCode();
		switch (elementCode) {
		case 1 : metafileVersion = (MetafileVersion) command; break;  //1
		case 2 : metafileDescription = (MetafileDescription) command; break; //2
		case 3 : vdcType = (VDCType) command; break;//3
		case 4 : integerPrecision = (IntegerPrecision) command; break;//4
		case 5 : realPrecision = (RealPrecision) command; break;//5
		case 6 : indexPrecision = (IndexPrecision) command; break;//6
		case 7 : colourPrecision = (ColourPrecision) command; break;//7
		case 8 : colourIndexPrecision = (ColourIndexPrecision) command; break;//8
		case 9 : maximumColourIndex = (MaximumColourIndex) command; break;//9
		case 10: colourValueExtent = (ColourValueExtent) command; break;//10
		case 11: metafileElementList = (MetafileElementList) command; break;//11
		case 12: metafileDefaultsReplacement = (MetafileDefaultsReplacement) command; break;//12
		case 13: fontList = (FontList) command; break;//13
		case 14: characterSetList = (CharacterSetList) command; break;//14
		case 15: characterCodingAnnouncer = (CharacterCodingAnnouncer) command; break;//15
		case 16: namePrecision = (NamePrecision) command; break;//16
		case 17: maximumVDCExtent = (MaximumVDCExtent) command; break;//17
		case 19: colourModel = (ColourModel) command; break;//19

		default:  System.out.println("Command:  "+command.getElementClass()*100
				+command.getElementCode()+"  is Not Found in MetafileDescriptor Table !!!"); break;
			
		}
	}
	
	public void addAttributeForObject(){
		metafileCgmObject.addAttributes(metafileVersion);
		metafileCgmObject.addAttributes(vdcType);
		metafileCgmObject.addAttributes(integerPrecision);
		metafileCgmObject.addAttributes(realPrecision);
		metafileCgmObject.addAttributes(indexPrecision);
		metafileCgmObject.addAttributes(colourPrecision);
		metafileCgmObject.addAttributes(colourIndexPrecision);
		metafileCgmObject.addAttributes(maximumColourIndex);
		metafileCgmObject.addAttributes(characterCodingAnnouncer);
		metafileCgmObject.addAttributes(colourValueExtent);
		metafileCgmObject.addAttributes(metafileDescription);
		metafileCgmObject.addAttributes(metafileDefaultsReplacement);
		metafileCgmObject.addAttributes(metafileElementList);
		metafileCgmObject.addAttributes(fontList);
		metafileCgmObject.addAttributes(characterCodingAnnouncer);	
	}
	
	public CGMObject getMetafileCgmObject() {
		return metafileCgmObject;
	}
	public void setMetafileCgmObject(CGMObject metafileCgmObject) {
		this.metafileCgmObject = metafileCgmObject;
	}
}
