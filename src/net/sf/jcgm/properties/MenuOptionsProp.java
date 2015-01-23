package net.sf.jcgm.properties;

import java.util.Set;

public class MenuOptionsProp {
	public enum units{
		INCHES,
		MILLIMETERS,
	}
	
	public enum cursorUnit{
		INCHES,
		MILLIMETERS,
		VDCS
	}
	public enum profile{
		NONE,
		ATA,
		CALS,
		CGM_PLUS,
		PIP,
		WEBCGM
	}
	public enum votextRenderMethord{
		NONE,
		BOXED_CAP,
		BASIC
	}
	public enum voBackGroundColor{
		USE_WHITE,
		USE_FILE_SPECIFIED_BACKGROUND_COLOR,
		USE_SYSTEM_WINDOW_COLOR
	}
	private units opUnits;    //0 none,  1, inches   2 millimeters
	private cursorUnit opCursorUnit; //0 none , 1 inches , 2 millimeters , 3 VDCs
	private profile opProfile ; // 0 none , 1 ATA , 2 CALS , 3 CGM+ 4 , PIP , 5 WebCGM
	private voBackGroundColor opVobackgroundColor;//0 none ,1 useWhite 2,Use File-Specified Background Color 3 use System window Color
	private votextRenderMethord opVotextRenderMethord = votextRenderMethord.NONE; // 0 none , 1 BoxedCap , 2 Basic
	private boolean opVoAbstractFilesShowDialog ;//false none ,true Show Abstract Resolution Dialog on Open
	private boolean opVohideHotSpots ;
	private boolean opRulers ;
	private boolean opEnableLogging ;
	public MenuOptionsProp(){
		
	}
	
	
	public void setOpCursorUnit(cursorUnit opCursorUnit) {
		this.opCursorUnit = opCursorUnit;
	}
	
	
	public void setOpProfile(profile opProfile) {
		this.opProfile = opProfile;
	}
	
	
	public void setOpUnits(units opUnits) {
		this.opUnits = opUnits;
	}
	
	
	public void setOpVobackgroundColor(voBackGroundColor opVobackgroundColor) {
		this.opVobackgroundColor = opVobackgroundColor;
	}
	
	
	public void setOpVotextRenderMethord(
			votextRenderMethord opVotextRenderMethord) {
		this.opVotextRenderMethord = opVotextRenderMethord;
	}
	
	
	public void setOpEnableLogging(boolean opEnableLogging) {
		this.opEnableLogging = opEnableLogging;
	}
	
	public void setOpRulers(boolean opRulers) {
		this.opRulers = opRulers;
	}
	
	public void setOpVoAbstractFilesShowDialog(
			boolean opVoAbstractFilesShowDialog) {
		this.opVoAbstractFilesShowDialog = opVoAbstractFilesShowDialog;
	}
	
	public void setOpVohideHotSpots(boolean opVohideHotSpots) {
		this.opVohideHotSpots = opVohideHotSpots;
	}

	
}
