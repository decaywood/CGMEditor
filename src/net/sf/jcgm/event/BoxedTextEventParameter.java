/**
 * 
 */
package net.sf.jcgm.event;

import java.awt.Color;

import net.sf.jcgm.drawer.DrawType;


/**
 * 2013-11-7
 */
public class BoxedTextEventParameter extends AttributeEventParameter{
 
	private Color fontColor;
	private Color backgroundColor;
	private boolean isItalic;
	private boolean isBold;
	private int textAlignment;
	private int fontType;
	private int unit;
	private double fontSize;
	
	public BoxedTextEventParameter() {
		super(DrawType.BOXED_TEXT);
	}

	public Color getFontColor() {
		return fontColor;
	}

	public void setFontColor(Color fontColor) {
		this.fontColor = fontColor;
	}

	public Color getBackgroundColor() {
		return backgroundColor;
	}

	public void setBackgroundColor(Color backgroundColor) {
		this.backgroundColor = backgroundColor;
	}

	public boolean isItalic() {
		return isItalic;
	}

	public void setItalic(boolean isItalic) {
		this.isItalic = isItalic;
	}

	public boolean isBold() {
		return isBold;
	}

	public void setBold(boolean isBold) {
		this.isBold = isBold;
	}

	public int getTextAlignment() {
		return textAlignment;
	}

	public void setTextAlignment(int textAlignment) {
		this.textAlignment = textAlignment;
	}

	public int getFontType() {
		return fontType;
	}

	public void setFontType(int fontType) {
		this.fontType = fontType;
	}

	public int getUnit() {
		return unit;
	}

	public void setUnit(int unit) {
		this.unit = unit;
	}

	public double getFontSize() {
		return fontSize;
	}

	public void setFontSize(double fontSize) {
		this.fontSize = fontSize;
	}

	
}
