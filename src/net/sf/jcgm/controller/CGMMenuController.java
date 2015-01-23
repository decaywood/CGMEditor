package net.sf.jcgm.controller;

import java.awt.Color;
import java.awt.geom.Point2D;
import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.zip.GZIPInputStream;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

import net.sf.jcgm.core.CGM;
import net.sf.jcgm.core.CGMDisplay;
import net.sf.jcgm.core.CGMTemp;
import net.sf.jcgm.core.CGMTempDisplay;
import net.sf.jcgm.db.CGMManager;
import net.sf.jcgm.db.CGMTempManager;
import net.sf.jcgm.display.CGMCanvas;
import net.sf.jcgm.display.FileNew;
import net.sf.jcgm.display.JCGMEditor;
import net.sf.jcgm.display.JCGMInternalFrame;
import net.sf.jcgm.drawer.DrawType;
import net.sf.jcgm.event.EventDispatcher;
import net.sf.jcgm.processor.CGMMenuProcessor;
import net.sf.jcgm.properties.FileNewProp;
import net.sf.jcgm.util.AddToCommands;

public class CGMMenuController implements ICGMMenuController{
	private JCGMEditor jcgmEditor;
	private JCGMInternalFrame currentInternalFrame;
	private EventDispatcher dispatcher;
	private CGMManager cgmManager;
	private CGMTempManager cgmTempManager;
	public CGMMenuController()
	{
		this.dispatcher=new EventDispatcher();
		this.dispatcher.addEventHandler(new CGMMenuProcessor(this));
		this.jcgmEditor=new JCGMEditor();
		this.jcgmEditor.setEventDispatcher(this.dispatcher);
		this.cgmManager=CGMManager.getCGMManager();
		this.cgmTempManager=CGMTempManager.getCGMTempManager();
	}
	
	@Override
	public void setEventDispatcher(EventDispatcher dispatcher) {
		this.dispatcher=dispatcher;
		this.jcgmEditor.setEventDispatcher(this.dispatcher);
	}
	@Override
	public void setPropertyTab(DrawType drawType) {
		jcgmEditor.getSelectedJCGMInternalFrame().setPropertyPanelTab(drawType);
	}


	@Override
	public void fileNew(int x,int y){
		// TODO Auto-generated method stub
		FileNew fn=new FileNew(45,45,this.jcgmEditor);
		if((fn.getFileNewProp())!=null)
		{
			FileNewProp fileNewProp= fn.getFileNewProp();
			double units=fileNewProp.getImageUnitSize();
			double width=fileNewProp.getWidth();
			double height=fileNewProp.getHeight();
			Point2D.Double canvasP0 = new Point2D.Double(0.0, 0.0);
			Point2D.Double canvasP1 = new Point2D.Double(width*units,height*units);
			JCGMInternalFrame iframe=new CGMCanvasController(fileNewProp.getFileName()).getJCGMInternalFrame();
			iframe.setCanvas(canvasP0, canvasP1);
			this.currentInternalFrame=iframe;
			int cgmTempId=cgmTempManager.createCGMTemp();
			CGMTemp cgmTemp=cgmTempManager.getCGMTempByIndex(cgmTempId);
			this.currentInternalFrame.setCgmTempId(cgmTempId);
			int cgmId=cgmManager.createCGM();
			CGM cgm=cgmManager.getCGMByIndex(cgmId);
			this.currentInternalFrame.setCgmId(cgmId);
			this.currentInternalFrame.getCanvasPanel().addICGMDisplay(new CGMDisplay(cgm));
			this.currentInternalFrame.getCanvasPanel().addICGMDisplay(new CGMTempDisplay(cgmTemp));
			this.jcgmEditor.addJCGMInternalFrame(this.currentInternalFrame);
		}
		fn.dispose();
	}

	@Override
	public void fileSave() {
		// TODO Auto-generated method stub
	}

	@Override
	public void fileSaveAs() {
		// TODO Auto-generated method stub
		FileOutputStream fileOutputStream = null;
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setAcceptAllFileFilterUsed(false);
		fileChooser.addChoosableFileFilter(new FileNameExtensionFilter("CGMÎÄ¼þ", "CGM"));
		fileChooser.setDialogTitle("Save File");
		fileChooser.setDialogType(JFileChooser.SAVE_DIALOG);
		int result = fileChooser.showSaveDialog(jcgmEditor);
		 if (result == JFileChooser.APPROVE_OPTION) {
			 File file = fileChooser.getSelectedFile();  
			 try {
				fileOutputStream = new FileOutputStream(file);
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			 finally{
				 try {
					fileOutputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			 }
		 }
	}

	@Override
	public void fileOpen() {
		// TODO Auto-generated method stub
		JFileChooser fileChooser=new JFileChooser();
		fileChooser.setDialogTitle("Choose File");
		fileChooser.showOpenDialog(this.jcgmEditor);
		fileChooser.setDialogType(JFileChooser.OPEN_DIALOG);			
		File cgmFile=fileChooser.getSelectedFile();
		try {
			if (cgmFile == null)
				throw new NullPointerException("unexpected null parameter");
			InputStream inputStream;
			String cgmFileName = cgmFile.getName();
			if (cgmFileName.endsWith(".cgm.gz") || cgmFileName.endsWith(".cgmz")) {
				inputStream = new GZIPInputStream(new FileInputStream(cgmFile));
			}
			else {
				inputStream = new FileInputStream(cgmFile);
			}
			DataInputStream in = new DataInputStream(new BufferedInputStream(inputStream));
			CGM cgm = new CGM();
			cgm.read(in);
			in.close();
			if(this.currentInternalFrame==null)
			{
				JCGMInternalFrame iframe=new CGMCanvasController(cgmFileName).getJCGMInternalFrame();
				this.currentInternalFrame=iframe;
				this.jcgmEditor.addJCGMInternalFrame(this.currentInternalFrame);
			}
			this.currentInternalFrame.getCanvasPanel().addICGMDisplay(new CGMDisplay(cgm));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void fileClose() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void filePrint() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void fileExit() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void editUndo() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void editRedo() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void editCut() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void editCopy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void editPaste() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void editDuplicate() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void editCrop() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void editSelectAll() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void editSelectAllLines() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void editSelectAllSolids() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void editSelectAllText() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void editCaptureImage() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void exportEntireFile() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void exportViewPort() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void optionsUnits(int index) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void optionsCursorUnits(int index) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void optionsProfile(int index) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void optionsViewOptTxtRendMethord(int index) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void optionsViewOptAbstrctFileShowResolv(boolean bool) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void optionsViewOptBgrdColor(int index) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void optionsViewOptHideHotSpot(boolean bool) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void optionsDefaultDrawOpt(int x, int y) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void optionsEnLogPrintOpt(boolean bool) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void optionsRulers(boolean bool) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void optionsSaveSetAsDefault(int x, int y) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void layoutReSize(int x, int y) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void layoutGrids(int x, int y) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void layoutLayerManager(int x, int y) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void layoutEditorPattern(int x, int y) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void layoutViewList(int x, int y) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void layoutFilterObject(int x, int y) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void layoutLayerRotateLeft() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void layoutLayerRotateRight() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowZoomIn() {
		this.currentInternalFrame.zoomIn();
	}

	@Override
	public void windowZoomOut() {
		this.currentInternalFrame.zoomOut();
	}

	@Override
	public void windowFitToWidth() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowFitToHeight() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowBestTofit() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowZoomFactor(int factor) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void helpRegister() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void helpUpdate() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void helpAbout() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void quickSlctDetailView() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void quickSlcOverView() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void quickSlcPanMode() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void quickSlcShowHotSpot() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void quickSlcBackHotSpot() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void quickSlcForwardHotSpot() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void quickSlcNoObjectFilter() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void quickSlcRefresh() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void toolHotSpot() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void toolDeleteObj() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void toolRotateObj() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void toolMoveObjToFront() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void toolMoveObjToBack() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void toolMoveObjOneLvlUp() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void toolMoveObjOneLvlBack() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void toolInsertGraphic() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void toolSelectGroup() {
		// TODO Auto-generated method stub
		
	}

	

	

}
