package net.sf.jcgm.processor;


import net.sf.jcgm.controller.ICGMMenuController;
import net.sf.jcgm.drawer.DrawType;
import net.sf.jcgm.event.EventHandler;
import net.sf.jcgm.event.EventParameter;
import net.sf.jcgm.event.MenuEventParameter;
import net.sf.jcgm.event.MenuType;

public class CGMMenuProcessor implements EventHandler {

	private ICGMMenuController menuController;
	 
	public CGMMenuProcessor(){}
	public CGMMenuProcessor(ICGMMenuController menuController){
		this.menuController = menuController;
	}
	
	@Override
	public void process(EventParameter parameter) {
		if(parameter.eventType!=EventParameter.CGM_MENU){ return; }
		MenuEventParameter menuEventParameter = (MenuEventParameter)parameter;
		MenuType menuType = menuEventParameter.getMenuType();
		int x = menuEventParameter.getMenuPositionX();
		int y = menuEventParameter.getMenuPositionY();
		switch(menuType){
		case File_New: fileNew(x, y); break;
		case File_Open: fileOpen(); break;
		case File_Close: fileClose(); break;
		case File_Save: ; fileSave(); break;
		case File_SaveAs: fileSaveAs(); break;
		case File_Print: filePrint(); break;
		case File_Exit: fileExit(); break;
		
		case Edit_Undo: editUndo(); break;
		case Edit_Redo: editRedo(); break;
		case Edit_Cut: editCut(); break;
		case Edit_Copy: editCopy(); break;
		case Edit_Paste: editPaste(); break;
		case Edit_Duplicate: editDuplicate(); break;
		case Edit_Crop: editCrop(); break;
		case Edit_SelectAll_All: editSelectAll(); break;
		case Edit_SelectAll_Lines: editSelectAllLines(); break;
		case Edit_SelectAll_Solids: editSelectAllSolids(); break;
		case Edit_SelectAll_Text: editSelectAllText(); break;
		case Edit_CaptureImage: editCaptureImage(); break;
		
		case EXPORT_EntireFile: exportEntireFile(); break;
		case EXPORT_ViewPort: exportViewPort(); break;
		
		case Options_Units_Inches: optionsUnits(1); break;
		case Options_Units_Millimeters: optionsUnits(2); break;
		case Options_CursorUnits_Inches: optionsCursorUnits(1); break;
		case Options_CursorUnits_Millimeters: optionsCursorUnits(2); break;
		case Options_CursorUnits_VDCs: optionsCursorUnits(3); break;
		case Options_Profile_ATA: optionsProfile(1); break;
		case Options_Profile_CALS: optionsProfile(2); break;
		case Options_Profile_CGMPlus: optionsProfile(3); break;
		case Options_Profile_PIP: optionsProfile(4); break;
		case Options_Profile_WebCGM: optionsProfile(5); break;
		case Options_Profile_None: optionsProfile(6); break;
		case Options_ViewOpt_TextRenderMethord_BoxedCap: optionsViewOptTxtRendMethord(1); break;
		case Options_ViewOpt_TextRenderMethord_Basic: optionsViewOptTxtRendMethord(2); break;
		case Options_ViewOpt_AbstractFiles_ShowAbstrResolDiaOnOpenSelect: optionsViewOptAbstrctFileShowResolv(true); break;
		case Options_ViewOpt_AbstractFiles_ShowAbstrResolDiaOnOpenDeselect: optionsViewOptAbstrctFileShowResolv(false); break;
		case Options_ViewOpt_BackGrdColor_UseWhite: optionsViewOptBgrdColor(1); break;
		case Options_ViewOpt_BackGrdColor_UseFileSpecBackGrdColor: optionsViewOptBgrdColor(2); break;
		case Options_ViewOpt_BackGrdColor_UseSystemWindowColor: optionsViewOptBgrdColor(3); break;
		case Options_ViewOpt_HideHotSpotsSelect: optionsViewOptHideHotSpot(true); break;
		case Options_ViewOpt_HideHotSpotsDeselect: optionsViewOptHideHotSpot(false); break;
		case Options_DefaultDrawOptions: optionsDefaultDrawOpt(x, y); break;
		case Options_EnableLoginPrintOpsSelect: optionsEnLogPrintOpt(true); break;
		case Options_EnableLoginPrintOpsDeselect: optionsEnLogPrintOpt(false); break;
		case Options_RulersSelect: optionsRulers(true); break;
		case Options_RulersDeselect: optionsRulers(false); break;
		case Options_SaveSettingAsDefaults: optionsSaveSetAsDefault(x, y); break;
		
		case Layout_Resize: layoutReSize(x, y); break;
		case Layout_Grids: layoutGrids(x, y); break;
		case Layout_LayerManagement: layoutLayerManager(x, y); break;
		case Layout_EditorPattern: layoutEditorPattern(x, y); break;
		case Layout_ViewStrctList: layoutViewList(x, y); break;
		case Layout_FilterObject: layoutFilterObject(x, y); break;
		case Layout_RotateDrawLeft: layoutLayerRotateLeft(); break;
		case Layout_RotateDrawRight: layoutLayerRotateRight(); break;
		
		case Window_ZoomIn: windowZoomIn(); break;
		case Window_ZoomOut: windowZoomOut(); break;
		case Window_FitToWidth: windowFitToWidth(); break;
		case Window_FitToHeight: windowFitToHeight(); break;
		case Window_BestFit: windowBestTofit(); break;
		case Window_ZoomFactor_2X: windowZoomFactor(2); break;
		case Window_ZoomFactor_4X: windowZoomFactor(4); break;
		case Window_ZoomFactor_8X: windowZoomFactor(8); break;
		case Window_ZoomFactor_16X: windowZoomFactor(16); break;
		
		case Help_Register: helpRegister(); break;
		case Help_Update: helpUpdate(); break;
		case Help_About: helpAbout(); break;
		
		
		case QuickSelect_DetailView: quickSlctDetailView(); break;
		case QuickSelect_OverView: quickSlcOverView(); break;
		case QuickSelect_PanMode: quickSlcPanMode(); break;
		case QuickSelect_ShowHotSpot: quickSlcShowHotSpot(); break;
		case QuickSelect_BackHotSpot: quickSlcBackHotSpot(); break;
		case QuickSelect_ForwardHotSpot: quickSlcForwardHotSpot(); break;
		case QuickSelect_NoObjFilter: quickSlcNoObjectFilter(); break;
		case QuickSelect_Refresh: quickSlcRefresh(); break;
		
		case Tool_HotSpot: toolHotSpot(); break;
		case Tool_DeleteObj: toolDeleteObj(); break;
		case Tool_RotateObj: toolRotateObj(); break;
		case Tool_MoveObjToFront: toolMoveObjToFront(); break;
		case Tool_MoveObjToBack: toolMoveObjToBack(); break;
		case Tool_MoveObjToOneLvlUp: toolMoveObjOneLvlUp(); break;
		case Tool_MoveObjToOneLvlBack: toolMoveObjOneLvlBack(); break;
		case Tool_InsertGraphic: toolInsertGraphic(); break;
		case Tool_SelectGroup: toolSelectGroup(); break;
		case Tool_BEZIER: toolSetVisible(DrawType.BEZIER); break;
		case Tool_ARC: toolSetVisible(DrawType.ARC); break;
		case Tool_BOXED_TEXT: toolSetVisible(DrawType.BOXED_TEXT); break;
		case Tool_LEADER_LINE: toolSetVisible(DrawType.LEADER_LINE); break;
		case Tool_LINE: toolSetVisible(DrawType.LINE); break;
		case Tool_RECTANGLE: toolSetVisible(DrawType.RECTANGLE); break;
		case Tool_ELLIPSE: toolSetVisible(DrawType.ELLIPSE); break;
		case Tool_POLYGON: toolSetVisible(DrawType.POLYGON); break;
		case Tool_POLYLINE: toolSetVisible(DrawType.POLYLINE); break;
		case Tool_SWEEP_ARROW: toolSetVisible(DrawType.SWEEP_ARROW); break;
		case Tool_SYMBOL: toolSetVisible(DrawType.SYMBOL); break;
		case Tool_TEXT: toolSetVisible(DrawType.TEXT); break;
		case Tool_DEFAULT: toolSetVisible(DrawType.DEFAULT); break;
		default : break;
		}
	}
	
	public void fileNew(int x, int y){ 
		menuController.fileNew(x, y);
	}
	public void fileOpen(){  
		menuController.fileOpen();
	}
	public void fileClose(){  
		menuController.fileClose();
	}
	public void fileSave(){  
		menuController.fileSave();
	}
	public void fileSaveAs(){ 
		menuController.fileSaveAs();
	}
	public void filePrint(){  
		menuController.filePrint();
	}
	public void fileExit(){  
		menuController.fileExit();
	}
	
	public void editUndo(){ 
		menuController.editUndo();
	}
	public void editRedo(){ 
		menuController.editRedo();
	}
	public void editCut(){  
		menuController.editCut();
	}
	public void editCopy(){ 
		menuController.editCopy();
	}
	public void editPaste(){ 
		menuController.editPaste();
	}
	public void editDuplicate(){  
		menuController.editDuplicate();
	}
	public void editCrop(){ 
		menuController.editCrop();
	}
	public void editSelectAll(){  
		menuController.editSelectAll();
	}
	public void editSelectAllLines(){ 
		menuController.editSelectAllLines();
	}
	public void editSelectAllSolids(){ 
		menuController.editSelectAllSolids();
	}
	public void editSelectAllText(){  
		menuController.editSelectAllText();
	}
	public void editCaptureImage(){  
		menuController.editCaptureImage();
	}
	
	public void exportEntireFile(){ 
		menuController.exportEntireFile();
	}
	public void exportViewPort(){  
		menuController.exportViewPort();
	}
	
	public void optionsUnits(int index){  
		menuController.optionsUnits(index);
	}
	public void optionsCursorUnits(int index){ 
		menuController.optionsCursorUnits(index);
	}
	public void optionsProfile(int index){ 
		menuController.optionsProfile(index);
	}
	
	public void optionsViewOptTxtRendMethord(int index){   
		menuController.optionsViewOptTxtRendMethord(index);
	}
	
	public void optionsViewOptAbstrctFileShowResolv(boolean bool){
		menuController.optionsViewOptAbstrctFileShowResolv(bool);
	}
	
	public void optionsViewOptBgrdColor(int index){  
		menuController.optionsViewOptBgrdColor(index);
	}
	
	public void optionsViewOptHideHotSpot(boolean bool){  
		menuController.optionsViewOptHideHotSpot(bool);
	}
	public void optionsDefaultDrawOpt(int x, int y){  
		menuController.optionsDefaultDrawOpt(x, y);
	}
	public void optionsEnLogPrintOpt(boolean bool){  
		menuController.optionsEnLogPrintOpt(bool);
	}
	public void optionsRulers(boolean bool){ 
		menuController.optionsRulers(bool);
	}
	
	public void optionsSaveSetAsDefault(int x, int y){  
		menuController.optionsSaveSetAsDefault(x, y);
	}
	public void layoutReSize(int x, int y){  
		menuController.layoutReSize(x, y);
	}
	public void layoutGrids(int x, int y){ 
		menuController.layoutGrids(x, y);
	}
	public void layoutLayerManager(int x, int y){ 
		menuController.layoutLayerManager(x, y);
	}
	public void layoutEditorPattern(int x, int y){  
		menuController.layoutEditorPattern(x, y);
	}
	public void layoutViewList(int x, int y){ 
		menuController.layoutViewList(x, y);
	}
	public void layoutFilterObject(int x, int y){  
		menuController.layoutFilterObject(x, y);
	}
	public void layoutLayerRotateLeft(){  
		menuController.layoutLayerRotateLeft();
	}
	public void layoutLayerRotateRight(){  
		menuController.layoutLayerRotateRight();
	}
	
	public void windowZoomIn(){ 
		menuController.windowZoomIn();
	}
	public void windowZoomOut(){   
		menuController.windowZoomOut();
	}
	public void windowFitToWidth(){
		menuController.windowFitToWidth();
	}
	public void windowFitToHeight(){  
		menuController.windowFitToHeight();
	}
	public void windowBestTofit(){   
		menuController.windowBestTofit();
	}
	public void windowZoomFactor(int factor){ 
		menuController.windowZoomFactor(factor);
	}
	
	public void helpRegister(){ 
		menuController.helpRegister();
	}
	public void helpUpdate(){  
		menuController.helpUpdate();
	}
	public void helpAbout(){  
		menuController.helpAbout();
	}
	
	public void quickSlctDetailView(){  
		menuController.quickSlctDetailView();
	}
	public void quickSlcOverView(){  
		menuController.quickSlcOverView();
	}
	public void quickSlcPanMode(){   
		menuController.quickSlcPanMode();
	}
	public void quickSlcShowHotSpot(){ 
		menuController.quickSlcShowHotSpot();
	}
	public void quickSlcBackHotSpot(){  
		menuController.quickSlcBackHotSpot();
	}
	public void quickSlcForwardHotSpot(){  
		menuController.quickSlcForwardHotSpot();
	}
	public void quickSlcNoObjectFilter(){  
		menuController.quickSlcNoObjectFilter();
	}
	public void quickSlcRefresh(){  
		menuController.quickSlcRefresh();
	}
	
	public void toolHotSpot(){   
		menuController.toolHotSpot();
	}
	public void toolDeleteObj(){ 
		menuController.toolDeleteObj();
	}
	public void toolRotateObj(){  
		menuController.toolRotateObj();
	}
	public void toolMoveObjToFront(){  
		menuController.toolMoveObjToFront();
	}
	public void toolMoveObjToBack(){   
		menuController.toolMoveObjToBack();
	}
	public void toolMoveObjOneLvlUp(){ 
		menuController.toolMoveObjOneLvlUp();
	}
	public void toolMoveObjOneLvlBack(){   
		menuController.toolMoveObjOneLvlBack();
	}
	public void toolInsertGraphic(){  
		menuController.toolInsertGraphic();
	}
	public void toolSelectGroup(){   
		menuController.toolSelectGroup();
	}
	public void toolSetVisible(DrawType drawType){  
		menuController.setPropertyTab(drawType);
	}
 
}
