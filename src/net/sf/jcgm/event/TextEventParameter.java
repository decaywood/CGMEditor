/**
 * 
 */
package net.sf.jcgm.event;

import java.awt.Color;

import net.sf.jcgm.drawer.DrawType;


/**
 * 2013-11-7
 */
public class TextEventParameter extends AttributeEventParameter{

	private Color fontColor = Color.BLACK;
	private Color backgroundColor;
	private int fontType = 1;  
	private double fontSize = 0.2;
	private double fontRotation = 0.0;
	private int unit = 2;
	private boolean isItalic;
	private boolean isBold;
	private int textAlignment = 1;
	
	public TextEventParameter() {
		super(DrawType.TEXT);
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

	public int getFontType() {
		return fontType;
	}

	public void setFontType(int fontType) {
		this.fontType = fontType;
	}

	public double getFontSize() {
		return fontSize;
	}

	public void setFontSize(double fontSize) {
		this.fontSize = fontSize;
	}

	public double getFontRotation() {
		return fontRotation;
	}

	public void setFontRotation(double fontRotation) {
		this.fontRotation = fontRotation;
	}

	public int getUnit() {
		return unit;
	}

	public void setUnit(int unit) {
		this.unit = unit;
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

}
