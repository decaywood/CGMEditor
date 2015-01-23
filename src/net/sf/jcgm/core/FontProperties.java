/**
 * 
 */
package net.sf.jcgm.core;

import java.io.DataInput;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Element class 1, 21   Font Properties
 * @author  Jiang Pingchuan
 * @since Jul 24, 2013
 */

public class FontProperties extends Command {

	protected enum PropertyIndicator {FONT_INDEX(1),STANDARD_VERSION(2),DESIGN_SOURCE(3),FONT_FAMILY(4),POSTURE(5),
		WEIGHT(6),PROPORTIONATE_WIDTH(7),INCLUDE_GLYPH_COLLECTIONS(8),INCLUDE_GLYPHS(9),DESIGN_SIZE(10),MINIMUM_SIZE(11),
		MAXIMUM_SIZE(12),DESIGN_GROUP(13),STRUCTURE(14);
	private PropertyIndicator(int v)
	{
		this.value=v;
	}
	private int value;
	}
	protected List<FontParameter> fontProperties=new ArrayList<FontParameter>();
	/**
	 * @param ec
	 * @param eid
	 * @param l
	 * @param in
	 * @throws IOException
	 */
	public FontProperties(int ec, int eid, int l, DataInput in)
			throws IOException {
		super(ec, eid, l, in);		

		PropertyIndicator pi=null;
		int index;
		int pr;
		StructuredDataRecord pvr;
		while(this.currentArg < this.args.length)
		{
			index=this.makeIndex();
			switch(index){
			case 1:
				pi=PropertyIndicator.FONT_INDEX;
				break;
			case 2:
				pi=PropertyIndicator.STANDARD_VERSION;
				break;
			case 3:
				pi=PropertyIndicator.DESIGN_SOURCE;
				break;
			case 4:
				pi=PropertyIndicator.FONT_FAMILY;
				break;
			case 5:
				pi=PropertyIndicator.POSTURE;
				break;
			case 6:
				pi=PropertyIndicator.WEIGHT;
				break;
			case 7:
				pi=PropertyIndicator.PROPORTIONATE_WIDTH;
				break;
			case 8:
				pi=PropertyIndicator.INCLUDE_GLYPH_COLLECTIONS;
				break;
			case 9:
				pi=PropertyIndicator.INCLUDE_GLYPHS;
				break;
			case 10:
				pi=PropertyIndicator.DESIGN_SIZE;
				break;
			case 11:
				pi=PropertyIndicator.MINIMUM_SIZE;
				break;
			case 12:
				pi=PropertyIndicator.MAXIMUM_SIZE;
				break;
			case 13:
				pi=PropertyIndicator.DESIGN_GROUP;
				break;
			case 14:
				pi=PropertyIndicator.STRUCTURE;
				break;
			default:
				break;
			}
			pr=this.makeInt();
			pvr=this.makeSDR();
			FontParameter fp=new FontParameter(pi,pr,pvr);
			this.fontProperties.add(fp);
		}

		// TODO Auto-generated constructor stub
	}

	public class FontParameter{
		protected PropertyIndicator propertyIndicator;
		protected int priority;
		protected StructuredDataRecord propertyValueRecord;
		public FontParameter(){

		}
		public FontParameter(PropertyIndicator pi,int pr, StructuredDataRecord pvr){
			this.propertyIndicator=pi;
			this.priority=pr;
			this.propertyValueRecord=pvr;
		}
	}
	@Override
	public String toString()
	{
		return "FontProperties";
	}
}
