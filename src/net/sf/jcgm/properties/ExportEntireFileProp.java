package net.sf.jcgm.properties;

import java.io.Serializable;

public class ExportEntireFileProp implements Serializable{
	private String expResolution = null;
	private String expscale = null;
	private String expQuality = null;
	private String expWidthpix = null;
	private String expHeightpix = null;
	private String expWidthInch = null;
	private String expHeightInch = null;
	private String expFileType = null;
	
	public ExportEntireFileProp(){
		
	}
	public String getExpFileType() {
		return expFileType;
	}
	public String getExpHeightInch() {
		return expHeightInch;
	}
	public String getExpHeightpix() {
		return expHeightpix;
	}
	public String getExpQuality() {
		return expQuality;
	}
	public String getExpResolution() {
		return expResolution;
	}
	public String getExpscale() {
		return expscale;
	}
	public String getExpWidthInch() {
		return expWidthInch;
	}
	public String getExpWidthpix() {
		return expWidthpix;
	}
	public void setExpFileType(String expFileType) {
		this.expFileType = expFileType;
	}
	public void setExpHeightInch(String expHeightInch) {
		this.expHeightInch = expHeightInch;
	}
	public void setExpHeightpix(String expHeightpix) {
		this.expHeightpix = expHeightpix;
	}
	public void setExpQuality(String expQuality) {
		this.expQuality = expQuality;
	}
	public void setExpResolution(String expResolution) {
		this.expResolution = expResolution;
	}
	public void setExpscale(String expscale) {
		this.expscale = expscale;
	}
	public void setExpWidthInch(String expWidthInch) {
		this.expWidthInch = expWidthInch;
	}
	public void setExpWidthpix(String expWidthpix) {
		this.expWidthpix = expWidthpix;
	}

}
