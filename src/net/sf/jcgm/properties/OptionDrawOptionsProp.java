package net.sf.jcgm.properties;

import java.io.Serializable;
import java.io.ObjectInputStream.GetField;

public class OptionDrawOptionsProp implements Serializable {
	private String generalColor ;
	 private String generalMDS ; 
	 private String textColorChooserColor;
	 private String textbgColorChooserColor;
	 private String textCapHeight;
	 private String textFontSize;
	 private String textFont;
	 private String textAlignment;
	 private Object[] textSelectedFonts;
	 private boolean textIsBold;
	 private boolean textIsItalic;
	 private String lineSize;
	 private String lineFontSize;
	 private String lineColor;
	 private String lineHeadRatio;
	 private String lineType;
	 private String solidEdgeSize;
	 private String solidEdgeColor;
	 private String solidFillColor;
	 private String solidEdgeType;
	 private String solidIterior;
	 private boolean solidIsEdge;
	 private String symbolSize;
	 public boolean isSolidIsEdge() {
		return solidIsEdge;
	}
	 public boolean isTextIsBold() {
		return textIsBold;
	}
	 public boolean isTextIsItalic() {
		return textIsItalic;
	}
	 public OptionDrawOptionsProp(){
		 
	 }
	 public String getGeneralColor() {
		return generalColor;
	}
	 public String getGeneralMDS() {
		return generalMDS;
	}
	 public String getLineColor() {
		return lineColor;
	}
	 public String getLineFontSize() {
		return lineFontSize;
	}
	 public String getLineHeadRatio() {
		return lineHeadRatio;
	}
	 public String getLineSize() {
		return lineSize;
	}
	 public String getLineType() {
		return lineType;
	}
	 public String getSolidEdgeColor() {
		return solidEdgeColor;
	}
	 public String getSolidEdgeSize() {
		return solidEdgeSize;
	}
	 public String getSolidEdgeType() {
		return solidEdgeType;
	}
	 public String getSolidFillColor() {
		return solidFillColor;
	}
	 public String getSolidIterior() {
		return solidIterior;
	}
	 public String getSymbolSize() {
		return symbolSize;
	}
	 public String getTextAlignment() {
		return textAlignment;
	}
	 public String getTextbgColorChooserColor() {
		return textbgColorChooserColor;
	}
	 public String getTextCapHeight() {
		return textCapHeight;
	}
	 public String getTextColorChooserColor() {
		return textColorChooserColor;
	}
	 public String getTextFont() {
		return textFont;
	}
	 public String getTextFontSize() {
		return textFontSize;
	}
	 public Object[] getTextSelectedFonts() {
		return textSelectedFonts;
	}
	 
	 
	 
	 public void setGeneralColor(String generalColor) {
		this.generalColor = generalColor;
	}
	 public void setGeneralMDS(String generalMDS) {
		this.generalMDS = generalMDS;
	}
	 public void setLineColor(String lineColor) {
		this.lineColor = lineColor;
	}
	 public void setLineFontSize(String lineFontSize) {
		this.lineFontSize = lineFontSize;
	}
	 public void setLineHeadRatio(String lineHeadRatio) {
		this.lineHeadRatio = lineHeadRatio;
	}
	 public void setLineSize(String lineSize) {
		this.lineSize = lineSize;
	}
	 public void setLineType(String lineType) {
		this.lineType = lineType;
	}
	 public void setSolidEdgeColor(String solidEdgeColor) {
		this.solidEdgeColor = solidEdgeColor;
	}
	 public void setSolidEdgeSize(String solidEdgeSize) {
		this.solidEdgeSize = solidEdgeSize;
	}
	 public void setSolidEdgeType(String solidEdgeType) {
		this.solidEdgeType = solidEdgeType;
	}
	 public void setSolidFillColor(String solidFillColor) {
		this.solidFillColor = solidFillColor;
	}
	 public void setSolidIsEdge(boolean solidIsEdge) {
		this.solidIsEdge = solidIsEdge;
	}
	 public void setSolidIterior(String solidIterior) {
		this.solidIterior = solidIterior;
	}
	 public void setSymbolSize(String symbolSize) {
		this.symbolSize = symbolSize;
	}
	 public void setTextAlignment(String textAlignment) {
		this.textAlignment = textAlignment;
	}
	 public void setTextbgColorChooserColor(String textbgColorChooserColor) {
		this.textbgColorChooserColor = textbgColorChooserColor;
	}
	 public void setTextCapHeight(String textCapHeight) {
		this.textCapHeight = textCapHeight;
	}
	 public void setTextColorChooserColor(String textColorChooserColor) {
		this.textColorChooserColor = textColorChooserColor;
	}
	 public void setTextFont(String textFont) {
		this.textFont = textFont;
	}
	 public void setTextFontSize(String textFontSize) {
		this.textFontSize = textFontSize;
	}
	 public void setTextIsBold(boolean textIsBold) {
		this.textIsBold = textIsBold;
	}
	 public void setTextIsItalic(boolean textIsItalic) {
		this.textIsItalic = textIsItalic;
	}
	 public void setTextSelectedFonts(Object[] textSelectedFonts) {
		this.textSelectedFonts = textSelectedFonts;
	}
	 
	 
}
