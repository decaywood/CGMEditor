package net.sf.jcgm.properties;

import java.util.Set;

public class SaveSetAsDefaultsProp {
	private boolean saveCurSetGlobUnits;
	private boolean saveCurSetBackGroundColor;
	private boolean saveCurSetFitToWindow;
	private boolean saveCurSetOverViewWindow;
	
	public SaveSetAsDefaultsProp(){
		
	}
	public boolean isSaveCurSetBackGroundColor() {
		return saveCurSetBackGroundColor;
	}
	public boolean isSaveCurSetFitToWindow() {
		return saveCurSetFitToWindow;
	}
	public boolean isSaveCurSetGlobUnits() {
		return saveCurSetGlobUnits;
	}
	public boolean isSaveCurSetOverViewWindow() {
		return saveCurSetOverViewWindow;
	}
	public void setSaveCurSetBackGroundColor(boolean saveCurSetBackGroundColor) {
		this.saveCurSetBackGroundColor = saveCurSetBackGroundColor;
	}
	public void setSaveCurSetFitToWindow(boolean saveCurSetFitToWindow) {
		this.saveCurSetFitToWindow = saveCurSetFitToWindow;
	}
	public void setSaveCurSetGlobUnits(boolean saveCurSetGlobUnits) {
		this.saveCurSetGlobUnits = saveCurSetGlobUnits;
	}
	public void setSaveCurSetOverViewWindow(boolean saveCurSetOverViewWindow) {
		this.saveCurSetOverViewWindow = saveCurSetOverViewWindow;
	}
	
}
