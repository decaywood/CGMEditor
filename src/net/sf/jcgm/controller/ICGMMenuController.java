/**
 * 
 */
package net.sf.jcgm.controller;

import net.sf.jcgm.display.JCGMEditor;
import net.sf.jcgm.drawer.DrawType;
import net.sf.jcgm.event.EventDispatcher;

/**
 * 2013-11-10
 */
public interface ICGMMenuController extends ICGMController{

	public void setEventDispatcher(EventDispatcher dispatcher);
	public void setPropertyTab(DrawType drawType);
	
	public void fileNew(int x, int y);
	public void fileOpen();
	public void fileClose();
	public void fileSave();
	public void fileSaveAs();
	public void filePrint();
	public void fileExit();
	
	public void editUndo();
	public void editRedo();
	public void editCut();
	public void editCopy();
	public void editPaste();
	public void editDuplicate();
	public void editCrop();
	public void editSelectAll();
	public void editSelectAllLines();
	public void editSelectAllSolids();
	public void editSelectAllText();
	public void editCaptureImage();
	
	public void exportEntireFile();
	public void exportViewPort();
	
	public void optionsUnits(int index);
	public void optionsCursorUnits(int index);
	public void optionsProfile(int index);
	public void optionsViewOptTxtRendMethord(int index);
	public void optionsViewOptAbstrctFileShowResolv(boolean bool);
	public void optionsViewOptBgrdColor(int index);
	public void optionsViewOptHideHotSpot(boolean bool);
	public void optionsDefaultDrawOpt(int x, int y);
	public void optionsEnLogPrintOpt(boolean bool);
	public void optionsRulers(boolean bool);
	public void optionsSaveSetAsDefault(int x, int y);
	public void layoutReSize(int x, int y);
	public void layoutGrids(int x, int y);
	public void layoutLayerManager(int x, int y);
	public void layoutEditorPattern(int x, int y);
	public void layoutViewList(int x, int y);
	public void layoutFilterObject(int x, int y);
	public void layoutLayerRotateLeft();
	public void layoutLayerRotateRight();
	
	public void windowZoomIn();
	public void windowZoomOut();
	public void windowFitToWidth();
	public void windowFitToHeight();
	public void windowBestTofit();
	public void windowZoomFactor(int factor);
	
	public void helpRegister();
	public void helpUpdate();
	public void helpAbout();
	
	public void quickSlctDetailView();
	public void quickSlcOverView();
	public void quickSlcPanMode();
	public void quickSlcShowHotSpot();
	public void quickSlcBackHotSpot();
	public void quickSlcForwardHotSpot();
	public void quickSlcNoObjectFilter();
	public void quickSlcRefresh();
	
	public void toolHotSpot();
	public void toolDeleteObj();
	public void toolRotateObj();
	public void toolMoveObjToFront();
	public void toolMoveObjToBack();
	public void toolMoveObjOneLvlUp();
	public void toolMoveObjOneLvlBack();
	public void toolInsertGraphic();
	public void toolSelectGroup();
}
