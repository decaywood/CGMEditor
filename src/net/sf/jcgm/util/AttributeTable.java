package net.sf.jcgm.util;

import java.util.regex.Pattern;

import net.sf.jcgm.core.AlternateCharacterSetIndex;
import net.sf.jcgm.core.CGMObject;
import net.sf.jcgm.core.CharacterExpansionFactor;
import net.sf.jcgm.core.CharacterHeight;
import net.sf.jcgm.core.CharacterOrientation;
import net.sf.jcgm.core.CharacterSetIndex;
import net.sf.jcgm.core.CharacterSpacing;
import net.sf.jcgm.core.ColourTable;
import net.sf.jcgm.core.Command;
import net.sf.jcgm.core.EdgeCap;
import net.sf.jcgm.core.EdgeColour;
import net.sf.jcgm.core.EdgeJoin;
import net.sf.jcgm.core.EdgeType;
import net.sf.jcgm.core.EdgeVisibility;
import net.sf.jcgm.core.EdgeWidth;
import net.sf.jcgm.core.FillColour;
import net.sf.jcgm.core.HatchIndex;
import net.sf.jcgm.core.InteriorStyle;
import net.sf.jcgm.core.LineCap;
import net.sf.jcgm.core.LineColour;
import net.sf.jcgm.core.LineJoin;
import net.sf.jcgm.core.LineType;
import net.sf.jcgm.core.LineWidth;
import net.sf.jcgm.core.MarkerColour;
import net.sf.jcgm.core.MarkerSize;
import net.sf.jcgm.core.MarkerSizeSpecificationMode;
import net.sf.jcgm.core.MarkerType;
import net.sf.jcgm.core.RestrictedText;
import net.sf.jcgm.core.RestrictedTextType;
import net.sf.jcgm.core.TextAlignment;
import net.sf.jcgm.core.TextColour;
import net.sf.jcgm.core.TextFontIndex;
import net.sf.jcgm.core.TextPath;
import net.sf.jcgm.core.TextPrecision;
import net.sf.jcgm.display.DrawOptions.Symbol;

public class AttributeTable {
	
	private CGMObject attributeCgmObject;
	private LineType lineType;    
	private LineWidth lineWidth;
	private LineColour lineColour;
	private MarkerType markerType;
	private MarkerSize markerSize;
	private MarkerColour markerColour;
	private TextFontIndex textFontIndex;
	private TextPrecision textPrecision;
	private CharacterExpansionFactor characterExpansionFactor;
	private CharacterSpacing characterSpacing;
	private TextColour textColour;
	private CharacterHeight characterHeight;
	private CharacterOrientation characterOrientation;
	private TextPath textPath;
	private TextAlignment textAlignment;
	private CharacterSetIndex characterSetIndex;
	private AlternateCharacterSetIndex alternateCharacterSetIndex;
	private InteriorStyle interiorStyle;
	private FillColour fillColour;
	private HatchIndex hatchIndex;
	private EdgeType edgeType;
	private EdgeWidth edgeWidth;
	private EdgeColour edgeColour;
	private EdgeVisibility edgeVisibility;
	private ColourTable colourTable;
	private LineCap lineCap;
	private LineJoin lineJoin;
	private RestrictedTextType restrictedTextType;
	private EdgeCap edgeCap;
	private EdgeJoin edgeJoin;
	
	public void resetTable(){
		lineType = null;    
		lineWidth = null;
		lineColour = null;
		markerType = null;
		markerSize = null;
		markerColour = null;
		textFontIndex = null;
		textPrecision = null;
		characterExpansionFactor = null;
		characterSpacing = null;
		textColour = null;
		characterHeight = null;
		characterOrientation = null;
		textPath = null;
		textAlignment = null;
		characterSetIndex = null;
		alternateCharacterSetIndex = null;
		interiorStyle = null;
		fillColour = null;
		hatchIndex = null;
		edgeType = null;
		edgeWidth = null;
		edgeColour = null;
		edgeVisibility = null;
		colourTable = null;
		lineCap = null;
		lineJoin = null;
		restrictedTextType = null;
		edgeCap = null;
		edgeJoin = null;
	}
	
	public void addAttribute(Command command){
		int elementCode = command.getElementCode();
		switch (elementCode){
		case 2 : lineType = (LineType) command; break;		  				//5 2 LINE TYPE
		case 3 : lineWidth = (LineWidth) command; break;						//5 3 LINE WIDTH
		case 4 : lineColour = (LineColour) command; break;						//5 4 LINE COLOUR
		case 6 : markerType = (MarkerType) command; break;						//5 6 MARKER TYPE
		case 7 : markerSize = (MarkerSize) command; break;						//5 7 MARKER SIZE
		case 8 : markerColour = (MarkerColour) command; break;					//5 8 MARKER COLOUR
		case 10: textFontIndex = (TextFontIndex) command; break;					//5 10 TEXT FONT INDEX
		case 11: textPrecision = (TextPrecision) command; break;					//5 11 TEXT PRECISION
		case 12: characterExpansionFactor = (CharacterExpansionFactor) command; break;		//5 12 CHARACTER EXPANSION FACTOR
		case 13: characterSpacing = (CharacterSpacing) command; break;				//5 13 CHARACTER SPACING
		case 14: textColour = (TextColour) command; break;						//5 14 TEXT COLOUR
		case 15: characterHeight = (CharacterHeight) command; break;					//5 15 CHARACTER HEIGHT
		case 16: characterOrientation = (CharacterOrientation) command; break;			//5 16 CHARACTER ORIENTATION
		case 17: textPath = (TextPath) command; break;						//5 17 TEXT PATH
		case 18: textAlignment = (TextAlignment) command; break;					//5 18 TEXT ALIGNMENT
		case 19: characterSetIndex = (CharacterSetIndex) command; break;				//5 19 CHARACTER SET INDEX
		case 20: alternateCharacterSetIndex = (AlternateCharacterSetIndex) command; break;		//5 20 ALTERNATE CHARACTER SET INDEX
		case 22: interiorStyle = (InteriorStyle) command; break;						//5 22 INTERIOR STYLE
		case 23: fillColour = (FillColour) command; break;						//5 23 FILL COLOUR
		case 24: hatchIndex = (HatchIndex) command; break;						//5 24 HATCH INDEX
		case 27: edgeType = (EdgeType) command; break;						//5 27 EDGE TYPE
		case 28: edgeWidth = (EdgeWidth) command; break;						//5 28 EDGE WIDTH
		case 29: edgeColour = (EdgeColour) command; break;						//5 29 EDGE COLOUR
		case 30: edgeVisibility = (EdgeVisibility) command; break;						//5 30 EDGE VISIBILITY
		case 34: colourTable = (ColourTable) command; break;						//5 34 COLOUR TABLE
		case 37: lineCap = (LineCap) command; break;						//5 37 LINE CAP
		case 38: lineJoin = (LineJoin) command; break;						//5 38 LINE JOIN
		case 42: restrictedTextType = (RestrictedTextType) command; break;						//5 42 RESTRICTED TEXT TYPE
		case 44: edgeCap = (EdgeCap) command; break;					//5 44 EDGE CAP
		case 45: edgeJoin = (EdgeJoin) command; break;						//5 4//5 EDGE JOIN	
		default: System.out.println("Command:  "+command.getElementClass()*100+command.getElementCode()+"  is Not Found in Attribute Table !!!");
		}
	}
	
	
	public void addAttributeForObject(int elementCode){
		switch (elementCode) {
		case 1 : attributeCgmObject.addAttributes(lineWidth);       	 //4 1 POLYLINE 
				 attributeCgmObject.addAttributes(lineColour);
				 attributeCgmObject.addAttributes(lineCap);
				 attributeCgmObject.addAttributes(lineJoin);
				 attributeCgmObject.addAttributes(lineType);
				 break;
		case 5 : attributeCgmObject.addAttributes(lineWidth);         //4 5 RESTRICTED TEXT  
				 attributeCgmObject.addAttributes(textColour);
				 attributeCgmObject.addAttributes(textPrecision);
				 attributeCgmObject.addAttributes(textAlignment);
				 attributeCgmObject.addAttributes(textFontIndex);
				 attributeCgmObject.addAttributes(characterExpansionFactor);
				 attributeCgmObject.addAttributes(characterHeight);
				 attributeCgmObject.addAttributes(characterOrientation);
				 attributeCgmObject.addAttributes(characterSetIndex);
				 attributeCgmObject.addAttributes(alternateCharacterSetIndex);
				 attributeCgmObject.addAttributes(restrictedTextType);
				 break;
		case 7 : attributeCgmObject.addAttributes(interiorStyle);     //4 7 POLYGON 
				 attributeCgmObject.addAttributes(fillColour);
				 attributeCgmObject.addAttributes(edgeWidth);
				 attributeCgmObject.addAttributes(edgeColour);
				 attributeCgmObject.addAttributes(edgeCap);
				 attributeCgmObject.addAttributes(edgeJoin);
				 attributeCgmObject.addAttributes(edgeVisibility);
				 attributeCgmObject.addAttributes(edgeType);
				 attributeCgmObject.addAttributes(hatchIndex);
				 break; 
		case 11: attributeCgmObject.addAttributes(interiorStyle);     //4 11 RECTANGLE 
				 attributeCgmObject.addAttributes(fillColour);
				 attributeCgmObject.addAttributes(edgeWidth);
				 attributeCgmObject.addAttributes(edgeColour);
				 attributeCgmObject.addAttributes(edgeCap);
				 attributeCgmObject.addAttributes(edgeJoin);
				 attributeCgmObject.addAttributes(edgeVisibility);
				 attributeCgmObject.addAttributes(edgeType);
				 attributeCgmObject.addAttributes(hatchIndex);
				 break; 	
		case 12: attributeCgmObject.addAttributes(interiorStyle);     //4 12 CIRCLE  
				 attributeCgmObject.addAttributes(fillColour);
				 attributeCgmObject.addAttributes(edgeWidth);
				 attributeCgmObject.addAttributes(edgeColour);
				 attributeCgmObject.addAttributes(edgeCap);
				 attributeCgmObject.addAttributes(edgeJoin);
				 attributeCgmObject.addAttributes(edgeVisibility);
				 attributeCgmObject.addAttributes(edgeType);
				 attributeCgmObject.addAttributes(hatchIndex);
				 break;
		case 13: attributeCgmObject.addAttributes(lineWidth);   		 //4 13 CIRCULAR ARC 3 POINT  
				 attributeCgmObject.addAttributes(lineColour);
				 attributeCgmObject.addAttributes(lineCap);
				 attributeCgmObject.addAttributes(lineJoin);
				 attributeCgmObject.addAttributes(lineType);			
			
				 break;
		case 17: attributeCgmObject.addAttributes(interiorStyle);      //4 17 ELLIPSE  
			   	 attributeCgmObject.addAttributes(fillColour);
			   	 attributeCgmObject.addAttributes(edgeWidth);
			   	 attributeCgmObject.addAttributes(edgeColour);
			   	 attributeCgmObject.addAttributes(edgeCap);
			   	 attributeCgmObject.addAttributes(edgeJoin);
			   	 attributeCgmObject.addAttributes(edgeVisibility);
			   	 attributeCgmObject.addAttributes(edgeType);
				 attributeCgmObject.addAttributes(hatchIndex);
				 break; 
		case 26: attributeCgmObject.addAttributes(lineWidth);   		 //4 26 POLYBEZIER   
				 attributeCgmObject.addAttributes(lineColour);
				 attributeCgmObject.addAttributes(lineCap);
				 attributeCgmObject.addAttributes(lineJoin);
				 attributeCgmObject.addAttributes(lineType);	
				 break; 
		}
	}
	
	public void setAttributeCgmObject(CGMObject attributeCgmObject) {
		this.attributeCgmObject = attributeCgmObject;
	}
	
	public CGMObject getAttributeCgmObject() {
		return attributeCgmObject;
	}
	 
}
