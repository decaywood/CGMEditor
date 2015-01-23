/**
 * 
 */
package net.sf.jcgm.display;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.beans.PropertyVetoException;
import java.util.logging.Logger;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;

import javax.swing.JDesktopPane;


import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JSeparator;
import javax.swing.JToggleButton;
import javax.swing.JToolBar;
import javax.swing.KeyStroke;
import javax.swing.border.BevelBorder;

import net.sf.jcgm.drawer.DrawType;
import net.sf.jcgm.event.CanvasEventParameter;
import net.sf.jcgm.event.EventDispatcher;
import net.sf.jcgm.event.MenuEventParameter;
import net.sf.jcgm.event.MenuType;
/**  CGM Editor   
 * @author Jiang Pingchuan
 *Since Sep 3, 2013 18:58:00
 */
public class JCGMEditor extends JFrame {
	private static Logger log = Logger.getLogger(JCGMEditor.class.toString());
	private Container container;
	private JDesktopPane desktop;
	private JPanel topPanel;
	private JMenuBar menuBar;
	private JToolBar toolBar;
	private JToolBar toolBar2;
	private JMenu mnFile;
	private JMenuItem fileNewMenu;
	private JMenuItem fileOpenMenu;
	private JMenuItem mntmClose;
	private JMenuItem fileSaveMenu;
	private JSeparator separator;
	private JMenuItem fileSaveAsMenu;
	private JMenuItem mntmPrintCtrlp;
	private  JSeparator separator_1;
	private  JMenuItem mntmExit;
	private  JSeparator separator_2;
	private  JMenu mnEdit;
	private  JMenuItem editUndoMenu;
	private  JMenuItem editRedoMenu;
	private  JSeparator separator_3;
	private  JMenuItem editCutMenu;
	private  JMenuItem editCopyMenu;
	private  JMenuItem editPasteMenu;
	private  JMenuItem editDuplicateMenu;
	private  JSeparator separator_4;
	private  JMenuItem editCaptureImageMenu;
	private  JMenu editSelectAllMenu;
	private  JMenuItem selectAllSubMenu;
	private  JMenuItem selectAllLinesSubMenu;
	private  JMenuItem selectAllSolidsMenu;
	private  JMenuItem selectAllTextSubMenu;
	private  JMenuItem editCropMenu;
	private  JMenu mnExport;
	private  JMenuItem exportEntireFileMenu;
	private  JMenuItem exportViewPortMenu;
	private  JMenu mnOptions;
	private  JMenu mnLayout;
	private  JMenu mnWindow;
	private  JMenu mnHelp;
	private  JMenuItem helpRegisterMenu;
	private  JMenuItem helpUpdateMenu;
	private  JMenuItem optionsDefDrawOptMenu;
	private  JMenuItem optionsEnLoggingMenu;
	private  JMenu optionsUnitsMenu;
	private  JMenu optionsCursorUnitsMenu;
	private  JMenu optionsProfileMenu;
	private  JSeparator separator_5;
	private  JSeparator separator_6;
	private  JMenu mnViewOptions;
	private  JMenu mnVOtextRenderMethord;
	private  JMenu mnVOabstractFiles;
	private  JMenu mnVObackGroundColor;
	private  JSeparator separator_7;
	private  JMenuItem optionsRulersMenu;
	private  JSeparator separator_8;
	private  JMenuItem mntmSaveCurrentSetting;
	private  JMenuItem layoutResizeMenu;
	private  JMenuItem layoutGridsMenu;
	private  JMenuItem layoutLayerMngMenu;
	private  JMenuItem mntmEditPattern;
	private  JMenuItem layoutViewStructureListMenu;
	private  JMenuItem layoutFilterObjectMenu;
	private  JMenuItem layoutRotateLeftMenu;
	private  JMenuItem layoutRotateDrawingRightMenu;
	private  JMenuItem boxedCap;
	private  JMenuItem basic;
	private  JMenuItem showAbstractResolutionDialogOnOpen;
	private  JMenuItem useWhite;
	private  JMenuItem useFileSpecifiedBackGroundColor;
	private  JMenuItem useSystemWindowColor;
	private  JMenuItem hotspots ;

	private  JSeparator separator_9;
	private  JSeparator separator_10;
	private  JSeparator separator_11;
	private  JMenuItem windowZoomInMenu;
	private  JMenuItem windowZoomOutMenu;
	private  JMenuItem windowFitToWidthMenu;
	private  JMenuItem windowFitToHeightMenu;
	private  JMenuItem windowBestFitMenu;
	private  JMenu windowZoomFactorMenu;
	private  JMenuItem mntmWebCgm;
	private  JMenuItem mntmATA;
	private  JMenuItem mntmCALS;
	private  JMenuItem mntmCGMplus;
	private  JMenuItem mntmPIP;
	private  JMenuItem mntmNone;
	private  JMenuItem helpAboutMenu;
	private  JMenuItem optionsUnitsInchesSubMenuR;
	private  JMenuItem optionsUnitsMMSubMenR;
	private  JMenuItem optionsCursorUnitsVDCsSubMenu;
	private  JMenuItem optionsCursorUnitsMMSubMenu;
	private  JMenuItem optionsCursorUnitsInchesSubMenu;
	private  JButton btnNew;
	private  JButton btnOpen;
	private  JButton btnSave;
	private  JButton btnPrint;
	private  JButton btnSelect;
	private  JButton btnUndo;
	private  JButton btnRedo;
	private  JButton btnZoomIn;
	private  JButton btnZoomOut;
	private  JButton btnFitToWidth;
	private  JButton btnFitToHeight;
	private  JButton btnBestFit;
	private  JButton btnDetailView;
	private  JButton btnOverView;
	private  JButton btnPanMode;
	private  JButton btnRotateLeft;
	private  JButton btnRotateRight;
	private  JButton btnShowHotSpot;
	private  JButton btnBack;
	private  JButton btnForward;
	private  JButton btnNoObjectFilter;
	private  JButton btnRefresh;
	private  JToggleButton btnText;
	private  JToggleButton btnBoxedText;
	private  JToggleButton btnLeaderLine;
	private  JToggleButton btnLine;
	private  JToggleButton btnPolyLine;
	private  JToggleButton btnArc;
	private  JToggleButton btnSweepArrow;
	private  JToggleButton btnPolygon;
	private  JToggleButton btnCurveLine;
	private  JToggleButton btnEllipse;
	private  JToggleButton btnRectangle;
	private  JToggleButton btnDefault;
	private  JToggleButton btnMoveObjectToFront;
	private  JToggleButton btnDeleteObject;
	private  JToggleButton btnRotateObject;
	private  JToggleButton btnSymbol;
	private  JToggleButton btnMveObjectOneLevelBack;
	private  JToggleButton btnMoveObjectOneLevelFront;
	private  JToggleButton btnHotspot;
	private  JToggleButton btnInsertGraphic;
	private  JToggleButton btnMoveObjectToBack;
	private  JToggleButton btnSelectGroup;
	private JRadioButtonMenuItem wndZoonFactor4xSubMenu;
	private JRadioButtonMenuItem wndZoonFactor8xSubMenu;
	private JRadioButtonMenuItem wndZoonFactor16xSubMenu;
	private JRadioButtonMenuItem wndZoonFactor2xSubMenu;

	private  JLabel label;
	private  JLabel label_1;
	private  JLabel label_2;
	private  JLabel label_3;
	private  JLabel label_4;
	private  JLabel label_5;
	private  JLabel label_6;

	private ImageIcon emptyIcon;
	private ImageIcon okIcon;
	private EventDispatcher eventDispatcher;	
	
	public JCGMEditor()
	{
		this.initEditor();
	}
	public void initEditor(){
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setIconImage(Toolkit.getDefaultToolkit().getImage(JCGMEditor.class.getResource("/images/jcgm.png")));
		setTitle("JiangCGM Editor");
		// TODO Auto-generated constructor stub	
		
		emptyIcon = new ImageIcon(JCGMEditor.class.getResource("/images/empty.gif"));
		okIcon = new ImageIcon(JCGMEditor.class.getResource("/images/ok.gif"));
		
		emptyIcon = new ImageIcon(JCGMEditor.class.getResource("/images/empty.gif"));
		okIcon = new ImageIcon(JCGMEditor.class.getResource("/images/ok.gif"));
		getContentPane().setLayout(new BorderLayout(0, 0));
		
		this.topPanel = new JPanel();
		getContentPane().add(this.topPanel, BorderLayout.NORTH);
		
		this.topPanel.setLayout(new BorderLayout(0, 0));

		this.menuBar = new JMenuBar();
		this.topPanel.add(this.menuBar,BorderLayout.NORTH);

		this.mnFile = new JMenu("File  ");
		this.menuBar.add(this.mnFile);

		this.fileNewMenu = new JMenuItem("New              ");
		this.fileNewMenu.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				MenuEventParameter menuEventParameter=new MenuEventParameter();
				menuEventParameter.setMenuType(MenuType.File_New);
				eventDispatcher.fireEvent(menuEventParameter);	
			}
		});
		this.fileNewMenu.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, InputEvent.CTRL_MASK));
		
		this.mnFile.add(this.fileNewMenu);

		this.fileOpenMenu = new JMenuItem("Open             ");
		this.fileOpenMenu.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				MenuEventParameter menuEventParameter=new MenuEventParameter();
				menuEventParameter.setMenuType(MenuType.File_Open);
				eventDispatcher.fireEvent(menuEventParameter);		
			}
		});
		this.fileOpenMenu.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, InputEvent.CTRL_MASK));
		this.mnFile.add(this.fileOpenMenu);

		mntmClose = new JMenuItem("Close");
		this.mntmClose.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				MenuEventParameter menuEventParameter=new MenuEventParameter();
				menuEventParameter.setMenuType(MenuType.File_Close);
				eventDispatcher.fireEvent(menuEventParameter);	
			}
		});
		this.mntmClose.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_L, InputEvent.CTRL_MASK));
		this.mnFile.add(mntmClose);

		this.separator = new JSeparator();
		this.mnFile.add(this.separator);

		this.fileSaveMenu = new JMenuItem("Save");
		this.fileSaveMenu.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				MenuEventParameter menuEventParameter=new MenuEventParameter();
				menuEventParameter.setMenuType(MenuType.File_Save);
				eventDispatcher.fireEvent(menuEventParameter);	
			}
		});
		this.fileSaveMenu.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_MASK));
		this.mnFile.add(this.fileSaveMenu);

		this.fileSaveAsMenu = new JMenuItem("Save As   ");
		this.fileSaveAsMenu.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				MenuEventParameter menuEventParameter=new MenuEventParameter();
				menuEventParameter.setMenuType(MenuType.File_SaveAs);
				eventDispatcher.fireEvent(menuEventParameter);		
			}
		});
		this.mnFile.add(this.fileSaveAsMenu);
		

		this.separator_1 = new JSeparator();
		this.mnFile.add(this.separator_1);

		this.mntmPrintCtrlp = new JMenuItem("Print");
		this.mntmPrintCtrlp.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				MenuEventParameter menuEventParameter=new MenuEventParameter();
				menuEventParameter.setMenuType(MenuType.File_Print);
				eventDispatcher.fireEvent(menuEventParameter);	
			}
		});
		this.mntmPrintCtrlp.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P, InputEvent.CTRL_MASK));
		this.mnFile.add(this.mntmPrintCtrlp);

		this.separator_2 = new JSeparator();
		this.mnFile.add(this.separator_2);

		this.mntmExit = new JMenuItem("Exit");
		this.mntmExit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				MenuEventParameter menuEventParameter=new MenuEventParameter();
				menuEventParameter.setMenuType(MenuType.File_Exit);
				eventDispatcher.fireEvent(menuEventParameter);	
			}
		});
		this.mntmExit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F4, InputEvent.ALT_MASK));
		this.mnFile.add(this.mntmExit);

		this.mnEdit = new JMenu("Edit  ");
		this.menuBar.add(this.mnEdit);

		this.editUndoMenu = new JMenuItem("Undo");
		this.editUndoMenu.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				MenuEventParameter menuEventParameter=new MenuEventParameter();
				menuEventParameter.setMenuType(MenuType.Edit_Undo);
				eventDispatcher.fireEvent(menuEventParameter);	
			}
		});
		this.editUndoMenu.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Z, InputEvent.CTRL_MASK));
		this.mnEdit.add(this.editUndoMenu);

		this.editRedoMenu = new JMenuItem("Redo");
		this.editRedoMenu.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				MenuEventParameter menuEventParameter=new MenuEventParameter();
				menuEventParameter.setMenuType(MenuType.Edit_Redo);
				eventDispatcher.fireEvent(menuEventParameter);	
			}
		});
		this.editRedoMenu.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Y, InputEvent.CTRL_MASK));
		this.mnEdit.add(this.editRedoMenu);

		this.separator_3 = new JSeparator();
		this.mnEdit.add(this.separator_3);

		this.editCutMenu = new JMenuItem("Cut");
		this.editCutMenu.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				MenuEventParameter menuEventParameter=new MenuEventParameter();
				menuEventParameter.setMenuType(MenuType.Edit_Cut);
				eventDispatcher.fireEvent(menuEventParameter);	
			}
		});
		this.mnEdit.add(this.editCutMenu);

		this.editCopyMenu = new JMenuItem("Copy");
		this.editCopyMenu.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				MenuEventParameter menuEventParameter=new MenuEventParameter();
				menuEventParameter.setMenuType(MenuType.Edit_Copy);
				eventDispatcher.fireEvent(menuEventParameter);	
			}
		});
		this.editCopyMenu.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, InputEvent.CTRL_MASK));
		this.mnEdit.add(this.editCopyMenu);

		this.editPasteMenu = new JMenuItem("Paste");
		this.editPasteMenu.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				MenuEventParameter menuEventParameter=new MenuEventParameter();
				menuEventParameter.setMenuType(MenuType.Edit_Paste);
				eventDispatcher.fireEvent(menuEventParameter);	
			}
		});
		this.editPasteMenu.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V, InputEvent.CTRL_MASK));
		this.mnEdit.add(this.editPasteMenu);

		this.editDuplicateMenu = new JMenuItem("Duplicate");
		this.editDuplicateMenu.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				MenuEventParameter menuEventParameter=new MenuEventParameter();
				menuEventParameter.setMenuType(MenuType.Edit_Duplicate);
				eventDispatcher.fireEvent(menuEventParameter);	
			}
		});
		this.editDuplicateMenu.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_D, InputEvent.CTRL_MASK));
		this.mnEdit.add(this.editDuplicateMenu);

		this.editCropMenu = new JMenuItem("Crop");
		this.editCropMenu.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				MenuEventParameter menuEventParameter=new MenuEventParameter();
				menuEventParameter.setMenuType(MenuType.Edit_Crop);
				eventDispatcher.fireEvent(menuEventParameter);	
			}
		});
		this.mnEdit.add(this.editCropMenu);

		this.separator_4 = new JSeparator();
		this.mnEdit.add(this.separator_4);

		this.editSelectAllMenu = new JMenu("Select All ");
		this.mnEdit.add(this.editSelectAllMenu);

		this.selectAllSubMenu = new JMenuItem("All                   ");
		this.selectAllSubMenu.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				MenuEventParameter menuEventParameter=new MenuEventParameter();
				menuEventParameter.setMenuType(MenuType.Edit_SelectAll_All);
				eventDispatcher.fireEvent(menuEventParameter);	
			}
		});
		this.editSelectAllMenu.add(this.selectAllSubMenu);

		this.selectAllLinesSubMenu = new JMenuItem("Lines");
		this.selectAllLinesSubMenu.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				MenuEventParameter menuEventParameter=new MenuEventParameter();
				menuEventParameter.setMenuType(MenuType.Edit_SelectAll_Lines);
				eventDispatcher.fireEvent(menuEventParameter);	
			}
		});
		this.editSelectAllMenu.add(this.selectAllLinesSubMenu);

		this.selectAllSolidsMenu = new JMenuItem("Solids");
		this.selectAllSolidsMenu.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				MenuEventParameter menuEventParameter=new MenuEventParameter();
				menuEventParameter.setMenuType(MenuType.Edit_SelectAll_Solids);
				eventDispatcher.fireEvent(menuEventParameter);	
			}
		});
		this.editSelectAllMenu.add(this.selectAllSolidsMenu);

		this.selectAllTextSubMenu = new JMenuItem("Text");
		this.selectAllTextSubMenu.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				MenuEventParameter menuEventParameter=new MenuEventParameter();
				menuEventParameter.setMenuType(MenuType.Edit_SelectAll_Text);
				eventDispatcher.fireEvent(menuEventParameter);	
			}
		});
		this.editSelectAllMenu.add(this.selectAllTextSubMenu);

		this.editCaptureImageMenu = new JMenuItem("Capture Image");
		this.editCaptureImageMenu.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				MenuEventParameter menuEventParameter=new MenuEventParameter();
				menuEventParameter.setMenuType(MenuType.Edit_CaptureImage);
				eventDispatcher.fireEvent(menuEventParameter);	
			}
		});
		this.mnEdit.add(this.editCaptureImageMenu);

		this.mnExport = new JMenu("Export");
		this.menuBar.add(this.mnExport);

		this.exportEntireFileMenu = new JMenuItem("Entire File            ");
		this.exportEntireFileMenu.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				MenuEventParameter menuEventParameter=new MenuEventParameter();
				menuEventParameter.setMenuType(MenuType.EXPORT_EntireFile);
				eventDispatcher.fireEvent(menuEventParameter);	
			}
		});
		this.mnExport.add(this.exportEntireFileMenu);
		

		this.exportViewPortMenu = new JMenuItem("ViewPort");
		this.exportViewPortMenu.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				MenuEventParameter menuEventParameter=new MenuEventParameter();
				menuEventParameter.setMenuType(MenuType.EXPORT_ViewPort);
				eventDispatcher.fireEvent(menuEventParameter);	
			}
		});
		this.mnExport.add(this.exportViewPortMenu);

		this.mnOptions = new JMenu("Options");
		this.menuBar.add(this.mnOptions);

		this.optionsUnitsMenu = new JMenu("Units");
		this.mnOptions.add(this.optionsUnitsMenu);

		this.optionsUnitsInchesSubMenuR = new JMenuItem("Inches               ",new ImageIcon(JCGMEditor.class.getResource("/images/empty.gif")));
		this.optionsUnitsInchesSubMenuR.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				MenuEventParameter menuEventParameter=new MenuEventParameter();
				menuEventParameter.setMenuType(MenuType.Options_Units_Inches);
				eventDispatcher.fireEvent(menuEventParameter);	
				optionsUnitsMMSubMenR.setIcon(emptyIcon);
				JCGMEditor.this.optionsUnitsInchesSubMenuR.setIcon(okIcon);
			}
		});
		this.optionsUnitsMenu.add(this.optionsUnitsInchesSubMenuR);

		this.optionsUnitsMMSubMenR = new JMenuItem("Millimeters",null);
		this.optionsUnitsMMSubMenR.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MenuEventParameter menuEventParameter=new MenuEventParameter();
				menuEventParameter.setMenuType(MenuType.Options_Units_Millimeters);
				eventDispatcher.fireEvent(menuEventParameter);	
				optionsUnitsInchesSubMenuR.setIcon(emptyIcon);
				JCGMEditor.this.optionsUnitsMMSubMenR.setIcon(okIcon);
			}
		});
		this.optionsUnitsMenu.add(this.optionsUnitsMMSubMenR);
		

		this.optionsCursorUnitsMenu = new JMenu("Cursor Units");
		this.mnOptions.add(this.optionsCursorUnitsMenu);

		this.optionsCursorUnitsInchesSubMenu = new JMenuItem("Inches                 ",new ImageIcon(JCGMEditor.class.getResource("/images/empty.gif")));
		this.optionsCursorUnitsInchesSubMenu.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				MenuEventParameter menuEventParameter=new MenuEventParameter();
				menuEventParameter.setMenuType(MenuType.Options_CursorUnits_Inches);
				eventDispatcher.fireEvent(menuEventParameter);	
				optionsCursorUnitsVDCsSubMenu.setIcon(emptyIcon);
				optionsCursorUnitsMMSubMenu.setIcon(emptyIcon);
				JCGMEditor.this.optionsCursorUnitsInchesSubMenu.setIcon(okIcon);
			}
		});
		this.optionsCursorUnitsMenu.add(this.optionsCursorUnitsInchesSubMenu);

		this.optionsCursorUnitsMMSubMenu = new JMenuItem("Millimeters");
		this.optionsCursorUnitsMMSubMenu.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				MenuEventParameter menuEventParameter=new MenuEventParameter();
				menuEventParameter.setMenuType(MenuType.Options_CursorUnits_Millimeters);
				eventDispatcher.fireEvent(menuEventParameter);	
				optionsCursorUnitsVDCsSubMenu.setIcon(emptyIcon);
				JCGMEditor.this.optionsCursorUnitsMMSubMenu.setIcon(okIcon);
				optionsCursorUnitsInchesSubMenu.setIcon(emptyIcon);
			}
		});
		this.optionsCursorUnitsMenu.add(this.optionsCursorUnitsMMSubMenu);

		this.optionsCursorUnitsVDCsSubMenu=new JMenuItem("VDCs",null);
		this.optionsCursorUnitsVDCsSubMenu.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				MenuEventParameter menuEventParameter=new MenuEventParameter();
				menuEventParameter.setMenuType(MenuType.Options_CursorUnits_VDCs);
				eventDispatcher.fireEvent(menuEventParameter);	
				JCGMEditor.this.optionsCursorUnitsVDCsSubMenu.setIcon(okIcon);
				optionsCursorUnitsMMSubMenu.setIcon(emptyIcon);
				optionsCursorUnitsInchesSubMenu.setIcon(emptyIcon);
			}
		});
		this.optionsCursorUnitsMenu.add(this.optionsCursorUnitsVDCsSubMenu);
	

		this.optionsProfileMenu = new JMenu("Profile");
	
		this.mnOptions.add(this.optionsProfileMenu);
		this.mntmATA = new JMenuItem("ATA",emptyIcon);
		this.mntmCALS = new JMenuItem("CALS");
		this.mntmCGMplus = new JMenuItem("CGM+");
		this.mntmPIP = new JMenuItem("PIP");
		this.mntmWebCgm = new JMenuItem("Web CGM                ");
		this.mntmNone = new JMenuItem("None",okIcon);
		
		this.mntmATA.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				MenuEventParameter menuEventParameter=new MenuEventParameter();
				menuEventParameter.setMenuType(MenuType.Options_Profile_ATA);
				eventDispatcher.fireEvent(menuEventParameter);	
				JCGMEditor.this.mntmATA.setIcon(okIcon);
				mntmCALS.setIcon(emptyIcon);
				mntmCGMplus.setIcon(emptyIcon);
				mntmPIP.setIcon(emptyIcon);
				mntmWebCgm.setIcon(emptyIcon);
				mntmNone.setIcon(emptyIcon);
			}
		});
		this.mntmCALS.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				MenuEventParameter menuEventParameter=new MenuEventParameter();
				menuEventParameter.setMenuType(MenuType.Options_Profile_CALS);
				eventDispatcher.fireEvent(menuEventParameter);	
				mntmATA.setIcon(emptyIcon);
				JCGMEditor.this.mntmCALS.setIcon(okIcon);
				mntmCGMplus.setIcon(emptyIcon);
				mntmPIP.setIcon(emptyIcon);
				mntmWebCgm.setIcon(emptyIcon);
				mntmNone.setIcon(emptyIcon);
			}
		});
		this.mntmCGMplus.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				MenuEventParameter menuEventParameter=new MenuEventParameter();
				menuEventParameter.setMenuType(MenuType.Options_Profile_CGMPlus);
				eventDispatcher.fireEvent(menuEventParameter);	
				mntmATA.setIcon(emptyIcon);
				mntmCALS.setIcon(emptyIcon);
				JCGMEditor.this.mntmCGMplus.setIcon(okIcon);
				mntmPIP.setIcon(emptyIcon);
				mntmWebCgm.setIcon(emptyIcon);
				mntmNone.setIcon(emptyIcon);
			}
		});
		this.mntmPIP.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				MenuEventParameter menuEventParameter=new MenuEventParameter();
				menuEventParameter.setMenuType(MenuType.Options_Profile_PIP);
				eventDispatcher.fireEvent(menuEventParameter);	
				mntmATA.setIcon(emptyIcon);
				mntmCALS.setIcon(emptyIcon);
				mntmCGMplus.setIcon(emptyIcon);
				JCGMEditor.this.mntmPIP.setIcon(okIcon);
				mntmWebCgm.setIcon(emptyIcon);
				mntmNone.setIcon(emptyIcon);
			}
		});
		this.mntmWebCgm.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				MenuEventParameter menuEventParameter=new MenuEventParameter();
				menuEventParameter.setMenuType(MenuType.Options_Profile_WebCGM);
				eventDispatcher.fireEvent(menuEventParameter);	
				mntmATA.setIcon(emptyIcon);
				mntmCALS.setIcon(emptyIcon);
				mntmCGMplus.setIcon(emptyIcon);
				mntmPIP.setIcon(emptyIcon);
				JCGMEditor.this.mntmWebCgm.setIcon(emptyIcon);
				mntmNone.setIcon(emptyIcon);
			}
		});
		this.mntmNone.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				MenuEventParameter menuEventParameter=new MenuEventParameter();
				menuEventParameter.setMenuType(MenuType.Options_Profile_None);
				eventDispatcher.fireEvent(menuEventParameter);	
				mntmATA.setIcon(emptyIcon);
				mntmCALS.setIcon(emptyIcon);
				mntmCGMplus.setIcon(emptyIcon);
				mntmPIP.setIcon(emptyIcon);
				mntmWebCgm.setIcon(emptyIcon);
				JCGMEditor.this.mntmNone.setIcon(okIcon);
			}
		});
		
		this.optionsProfileMenu.add(this.mntmATA);
		this.optionsProfileMenu.add(this.mntmCALS);
		this.optionsProfileMenu.add(this.mntmCGMplus);
		this.optionsProfileMenu.add(this.mntmPIP);
		this.optionsProfileMenu.add(this.mntmWebCgm);
		this.optionsProfileMenu.add(this.mntmNone);
		
		this.separator_5 = new JSeparator();
		this.mnOptions.add(this.separator_5);

		this.mnViewOptions = new JMenu("View Options");
		this.mnOptions.add(this.mnViewOptions);
		
		this.mnVOtextRenderMethord = new JMenu("Text Render Methord");
		this.mnVOabstractFiles = new JMenu("Abstract Files");
		this.mnVObackGroundColor = new JMenu("Background Color");
		this.hotspots = new JMenuItem("Hide Hotspots",emptyIcon);
		this.hotspots.addActionListener(new Action_Event() {
			@Override
			public void actionPerformed(ActionEvent e) {   
				if(YesOrNo){
					MenuEventParameter menuEventParameter=new MenuEventParameter();
					menuEventParameter.setMenuType(MenuType.Options_ViewOpt_HideHotSpotsSelect);
					eventDispatcher.fireEvent(menuEventParameter);	
					JCGMEditor.this.hotspots.setIcon(okIcon);
				}
				else {
					MenuEventParameter menuEventParameter=new MenuEventParameter();
					menuEventParameter.setMenuType(MenuType.Options_ViewOpt_HideHotSpotsDeselect);
					eventDispatcher.fireEvent(menuEventParameter);	
					JCGMEditor.this.hotspots.setIcon(emptyIcon);
				}
				YesOrNo = !YesOrNo;
			}
		});

		
		this.mnViewOptions.add(this.mnVOtextRenderMethord);
		this.mnViewOptions.add(this.mnVOabstractFiles);
		this.mnViewOptions.add(this.mnVObackGroundColor);
		this.mnViewOptions.add(this.hotspots);
		
		this.boxedCap = new JMenuItem("Boxed Cap (stretch or constrain to box)",emptyIcon);
		this.boxedCap.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				MenuEventParameter menuEventParameter=new MenuEventParameter();
				menuEventParameter.setMenuType(MenuType.Options_ViewOpt_TextRenderMethord_BoxedCap);
				eventDispatcher.fireEvent(menuEventParameter);	
				basic.setIcon(emptyIcon);
				JCGMEditor.this.boxedCap.setIcon(okIcon);
			}
		});
		this.mnVOtextRenderMethord.add(this.boxedCap);
		
		this.basic = new JMenuItem("Basic (constrain to box)");
		this.basic.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				MenuEventParameter menuEventParameter=new MenuEventParameter();
				menuEventParameter.setMenuType(MenuType.Options_ViewOpt_TextRenderMethord_Basic);
				eventDispatcher.fireEvent(menuEventParameter);
				boxedCap.setIcon(emptyIcon);
				JCGMEditor.this.basic.setIcon(okIcon);
			}
		});
		this.mnVOtextRenderMethord.add(this.basic);
		
		this.showAbstractResolutionDialogOnOpen = new JMenuItem("Show Abstract Resolution Dialog on Open",emptyIcon);
		this.showAbstractResolutionDialogOnOpen.addActionListener(new Action_Event() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(YesOrNo){
					MenuEventParameter menuEventParameter=new MenuEventParameter();
					menuEventParameter.setMenuType(MenuType.Options_ViewOpt_AbstractFiles_ShowAbstrResolDiaOnOpenSelect);
					eventDispatcher.fireEvent(menuEventParameter);
					JCGMEditor.this.showAbstractResolutionDialogOnOpen.setIcon(okIcon);
				}
				else {
					MenuEventParameter menuEventParameter=new MenuEventParameter();
					menuEventParameter.setMenuType(MenuType.Options_ViewOpt_AbstractFiles_ShowAbstrResolDiaOnOpenDeselect);
					eventDispatcher.fireEvent(menuEventParameter);
					JCGMEditor.this.showAbstractResolutionDialogOnOpen.setIcon(emptyIcon);
				}
				YesOrNo = !YesOrNo;
			}
		});
		this.mnVOabstractFiles.add(this.showAbstractResolutionDialogOnOpen);
		
		this.useWhite = new JMenuItem("Use White",emptyIcon);
		this.useWhite.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				MenuEventParameter menuEventParameter=new MenuEventParameter();
				menuEventParameter.setMenuType(MenuType.Options_ViewOpt_BackGrdColor_UseWhite);
				eventDispatcher.fireEvent(menuEventParameter);
				JCGMEditor.this.useWhite.setIcon(okIcon);
				useFileSpecifiedBackGroundColor.setIcon(emptyIcon);
				useSystemWindowColor.setIcon(emptyIcon);
			}
		});
		this.mnVObackGroundColor.add(useWhite);
		
		this.useFileSpecifiedBackGroundColor = new JMenuItem("Use File-Specified Background Color");
		this.useFileSpecifiedBackGroundColor.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				MenuEventParameter menuEventParameter=new MenuEventParameter();
				menuEventParameter.setMenuType(MenuType.Options_ViewOpt_BackGrdColor_UseFileSpecBackGrdColor);
				eventDispatcher.fireEvent(menuEventParameter);
				useWhite.setIcon(emptyIcon);
				JCGMEditor.this.useFileSpecifiedBackGroundColor.setIcon(okIcon);
				useSystemWindowColor.setIcon(emptyIcon);
			}
		});
		this.mnVObackGroundColor.add(useFileSpecifiedBackGroundColor);
		
		this.useSystemWindowColor = new JMenuItem("Use System Window Color");
		this.useSystemWindowColor.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				MenuEventParameter menuEventParameter=new MenuEventParameter();
				menuEventParameter.setMenuType(MenuType.Options_ViewOpt_BackGrdColor_UseSystemWindowColor);
				eventDispatcher.fireEvent(menuEventParameter);
				useWhite.setIcon(emptyIcon);
				useFileSpecifiedBackGroundColor.setIcon(emptyIcon);
				JCGMEditor.this.useSystemWindowColor.setIcon(okIcon);
			}
		});
		this.mnVObackGroundColor.add(useSystemWindowColor);
		
		this.optionsDefDrawOptMenu = new JMenuItem("Default Draw Options   ");
		this.optionsDefDrawOptMenu.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				 
			}
		});
		this.mnOptions.add(this.optionsDefDrawOptMenu);

		this.separator_6 = new JSeparator();
		this.mnOptions.add(this.separator_6);

		this.optionsEnLoggingMenu = new JMenuItem("Enable Loging Print Ops");
		this.optionsEnLoggingMenu.addActionListener(new Action_Event() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(YesOrNo){
					MenuEventParameter menuEventParameter=new MenuEventParameter();
					menuEventParameter.setMenuType(MenuType.Options_EnableLoginPrintOpsSelect);
					eventDispatcher.fireEvent(menuEventParameter);
					JCGMEditor.this.optionsEnLoggingMenu.setIcon(okIcon);
				}
				else {
					MenuEventParameter menuEventParameter=new MenuEventParameter();
					menuEventParameter.setMenuType(MenuType.Options_EnableLoginPrintOpsDeselect);
					eventDispatcher.fireEvent(menuEventParameter);
					JCGMEditor.this.optionsEnLoggingMenu.setIcon(emptyIcon);
				}
				YesOrNo = !YesOrNo;
			}
		});
		this.mnOptions.add(this.optionsEnLoggingMenu);

		this.separator_7 = new JSeparator();
		this.mnOptions.add(this.separator_7);

		this.optionsRulersMenu = new JMenuItem("Rulers",new ImageIcon(JCGMEditor.class.getResource("/images/empty.gif")));
		this.optionsRulersMenu.addActionListener(new Action_Event() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(YesOrNo){
					MenuEventParameter menuEventParameter=new MenuEventParameter();
					menuEventParameter.setMenuType(MenuType.Options_RulersSelect);
					eventDispatcher.fireEvent(menuEventParameter);
					JCGMEditor.this.optionsRulersMenu.setIcon(okIcon);
				}
				else {
					MenuEventParameter menuEventParameter=new MenuEventParameter();
					menuEventParameter.setMenuType(MenuType.Options_RulersDeselect);
					eventDispatcher.fireEvent(menuEventParameter);
					JCGMEditor.this.optionsRulersMenu.setIcon(emptyIcon);
				}
				YesOrNo = !YesOrNo;
			}
		});
		this.mnOptions.add(this.optionsRulersMenu);

		this.separator_8 = new JSeparator();
		this.mnOptions.add(this.separator_8);

		this.mntmSaveCurrentSetting = new JMenuItem("Save Setting as Defaults");
		this.mntmSaveCurrentSetting.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				MenuEventParameter menuEventParameter=new MenuEventParameter();
				menuEventParameter.setMenuPosition(getX()+mntmSaveCurrentSetting.getX(), getY()+mntmSaveCurrentSetting.getY());
				menuEventParameter.setMenuType(MenuType.Options_SaveSettingAsDefaults);
				eventDispatcher.fireEvent(menuEventParameter);	
			}
		});
		this.mnOptions.add(this.mntmSaveCurrentSetting);

		this.mnLayout = new JMenu("Layout");
		this.menuBar.add(this.mnLayout);

		this.layoutResizeMenu = new JMenuItem("Resize           Ctrl+R");
		this.layoutResizeMenu.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				MenuEventParameter menuEventParameter=new MenuEventParameter();
				menuEventParameter.setMenuPosition(getX()+layoutResizeMenu.getX(), getY()+layoutResizeMenu.getY());
				menuEventParameter.setMenuType(MenuType.Layout_Resize);
				eventDispatcher.fireEvent(menuEventParameter);	
			}
		});
		this.mnLayout.add(this.layoutResizeMenu);

		this.layoutGridsMenu = new JMenuItem("Grids");
		this.layoutGridsMenu.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				MenuEventParameter menuEventParameter=new MenuEventParameter();
				menuEventParameter.setMenuPosition(getX()+layoutGridsMenu.getX(), getY()+layoutGridsMenu.getY());
				menuEventParameter.setMenuType(MenuType.Layout_Grids);
				eventDispatcher.fireEvent(menuEventParameter);	
			}
		});
		this.mnLayout.add(this.layoutGridsMenu);

		this.layoutLayerMngMenu = new JMenuItem("Layer Management");
		this.layoutLayerMngMenu.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				MenuEventParameter menuEventParameter=new MenuEventParameter();
				menuEventParameter.setMenuPosition(getX()+layoutLayerMngMenu.getX(), getY()+layoutLayerMngMenu.getY());
				menuEventParameter.setMenuType(MenuType.Layout_LayerManagement);
				eventDispatcher.fireEvent(menuEventParameter);	
			}
		});
		this.mnLayout.add(this.layoutLayerMngMenu);

		this.separator_9 = new JSeparator();
		this.mnLayout.add(this.separator_9);

		this.mntmEditPattern = new JMenuItem("Edit Pattern");
		this.mntmEditPattern.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				MenuEventParameter menuEventParameter=new MenuEventParameter();
				menuEventParameter.setMenuPosition(getX()+mntmEditPattern.getX(), getY()+mntmEditPattern.getY());
				menuEventParameter.setMenuType(MenuType.Layout_EditorPattern);
				eventDispatcher.fireEvent(menuEventParameter);	
			}
		});
		this.mnLayout.add(this.mntmEditPattern);

		this.separator_11 = new JSeparator();
		this.mnLayout.add(this.separator_11);

		this.layoutViewStructureListMenu = new JMenuItem("View Structure List");
		this.layoutViewStructureListMenu.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				MenuEventParameter menuEventParameter=new MenuEventParameter();
				menuEventParameter.setMenuPosition(getX()+layoutViewStructureListMenu.getX(), getY()+layoutViewStructureListMenu.getY());
				menuEventParameter.setMenuType(MenuType.Layout_ViewStrctList);
				eventDispatcher.fireEvent(menuEventParameter);	
			}
		});
		this.mnLayout.add(this.layoutViewStructureListMenu);

		this.layoutFilterObjectMenu = new JMenuItem("Filter Object");
		this.layoutFilterObjectMenu.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				MenuEventParameter menuEventParameter=new MenuEventParameter();
				menuEventParameter.setMenuPosition(getX()+layoutFilterObjectMenu.getX(), getY()+layoutFilterObjectMenu.getY());
				menuEventParameter.setMenuType(MenuType.Layout_FilterObject);
				eventDispatcher.fireEvent(menuEventParameter);	
			}
		});
		this.mnLayout.add(this.layoutFilterObjectMenu);

		this.separator_10 = new JSeparator();
		this.mnLayout.add(this.separator_10);

		this.layoutRotateLeftMenu = new JMenuItem("Ratote Drawing Left");
		this.layoutRotateLeftMenu.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				MenuEventParameter menuEventParameter=new MenuEventParameter();
				menuEventParameter.setMenuType(MenuType.Layout_RotateDrawLeft);
				eventDispatcher.fireEvent(menuEventParameter);	
			}
		});
		this.mnLayout.add(this.layoutRotateLeftMenu);

		this.layoutRotateDrawingRightMenu = new JMenuItem("Ratote Drawing Right");
		this.layoutRotateDrawingRightMenu.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				MenuEventParameter menuEventParameter=new MenuEventParameter();
				menuEventParameter.setMenuType(MenuType.Layout_RotateDrawRight);
				eventDispatcher.fireEvent(menuEventParameter);	
			}
		});
		this.mnLayout.add(this.layoutRotateDrawingRightMenu);

		this.mnWindow = new JMenu("Window");
		this.menuBar.add(this.mnWindow);

		this.windowZoomInMenu = new JMenuItem("Zoom In                 ");
		this.windowZoomInMenu.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				MenuEventParameter menuEventParameter=new MenuEventParameter();
				menuEventParameter.setMenuType(MenuType.Window_ZoomIn);
				eventDispatcher.fireEvent(menuEventParameter);	
			}
		});
		this.mnWindow.add(this.windowZoomInMenu);

		this.windowZoomOutMenu = new JMenuItem("Zoom out                ");
		this.windowZoomOutMenu.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				MenuEventParameter menuEventParameter=new MenuEventParameter();
				menuEventParameter.setMenuType(MenuType.Window_ZoomOut);
				eventDispatcher.fireEvent(menuEventParameter);		
			}
		});
		this.mnWindow.add(this.windowZoomOutMenu);

		this.windowFitToWidthMenu = new JMenuItem("Fit to Width");
		this.windowFitToWidthMenu.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				MenuEventParameter menuEventParameter=new MenuEventParameter();
				menuEventParameter.setMenuType(MenuType.Window_FitToWidth);
				eventDispatcher.fireEvent(menuEventParameter);	
			}
		});
		this.mnWindow.add(this.windowFitToWidthMenu);

		this.windowFitToHeightMenu = new JMenuItem("Fit to Height");
		this.windowFitToHeightMenu.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				MenuEventParameter menuEventParameter=new MenuEventParameter();
				menuEventParameter.setMenuType(MenuType.Window_FitToHeight);
				eventDispatcher.fireEvent(menuEventParameter);	
			}
		});
		this.mnWindow.add(this.windowFitToHeightMenu);

		this.windowBestFitMenu = new JMenuItem("Best Fit ");
		this.windowBestFitMenu.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				MenuEventParameter menuEventParameter=new MenuEventParameter();
				menuEventParameter.setMenuType(MenuType.Window_BestFit);
				eventDispatcher.fireEvent(menuEventParameter);	
			}
		});
		this.mnWindow.add(this.windowBestFitMenu);

		this.windowZoomFactorMenu = new JMenu("Zoom Factor");
		this.mnWindow.add(this.windowZoomFactorMenu);

		this.wndZoonFactor2xSubMenu = new JRadioButtonMenuItem("2x                    ");
		this.wndZoonFactor2xSubMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MenuEventParameter menuEventParameter=new MenuEventParameter();
				menuEventParameter.setMenuType(MenuType.Window_ZoomFactor_2X);
				eventDispatcher.fireEvent(menuEventParameter);	
			}
		});
		this.windowZoomFactorMenu.add(this.wndZoonFactor2xSubMenu);

		this.wndZoonFactor4xSubMenu = new JRadioButtonMenuItem("4x");
		this.wndZoonFactor4xSubMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MenuEventParameter menuEventParameter=new MenuEventParameter();
				menuEventParameter.setMenuType(MenuType.Window_ZoomFactor_4X);
				eventDispatcher.fireEvent(menuEventParameter);	
			}
		});
		this.windowZoomFactorMenu.add(this.wndZoonFactor4xSubMenu);

		this.wndZoonFactor8xSubMenu = new JRadioButtonMenuItem("8x");
		this.wndZoonFactor8xSubMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MenuEventParameter menuEventParameter=new MenuEventParameter();
				menuEventParameter.setMenuType(MenuType.Window_ZoomFactor_8X);
				eventDispatcher.fireEvent(menuEventParameter);	
			}
		});
		this.windowZoomFactorMenu.add(this.wndZoonFactor8xSubMenu);

		this.wndZoonFactor16xSubMenu = new JRadioButtonMenuItem("16x");
		this.wndZoonFactor16xSubMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MenuEventParameter menuEventParameter=new MenuEventParameter();
				menuEventParameter.setMenuType(MenuType.Window_ZoomFactor_16X);
				eventDispatcher.fireEvent(menuEventParameter);	
			}
		});
		this.windowZoomFactorMenu.add(this.wndZoonFactor16xSubMenu);
		ButtonGroup btnZoomFactorGrp=new ButtonGroup();
		btnZoomFactorGrp.add(this.wndZoonFactor2xSubMenu);
		btnZoomFactorGrp.add(this.wndZoonFactor4xSubMenu);
		btnZoomFactorGrp.add(this.wndZoonFactor8xSubMenu);
		btnZoomFactorGrp.add(this.wndZoonFactor16xSubMenu);
		this.wndZoonFactor2xSubMenu.setSelected(true);

		this.mnHelp = new JMenu("Help  ");
		this.menuBar.add(this.mnHelp);

		this.helpRegisterMenu = new JMenuItem("Register               ");
		this.helpRegisterMenu.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				MenuEventParameter menuEventParameter=new MenuEventParameter();
				menuEventParameter.setMenuType(MenuType.Help_Register);
				eventDispatcher.fireEvent(menuEventParameter);	
			}
		});
		this.mnHelp.add(this.helpRegisterMenu);

		this.helpUpdateMenu = new JMenuItem("Update        ");
		this.helpUpdateMenu.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				MenuEventParameter menuEventParameter=new MenuEventParameter();
				menuEventParameter.setMenuType(MenuType.Help_Update);
				eventDispatcher.fireEvent(menuEventParameter);	
			}
		});
		this.mnHelp.add(this.helpUpdateMenu);

		this.helpAboutMenu = new JMenuItem("About");
		this.helpAboutMenu.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				MenuEventParameter menuEventParameter=new MenuEventParameter();
				menuEventParameter.setMenuType(MenuType.Help_About);
				eventDispatcher.fireEvent(menuEventParameter);	
			}
		});
		this.mnHelp.add(this.helpAboutMenu);

		this.toolBar=new JToolBar();
		
		
		this.topPanel.add(toolBar,BorderLayout.CENTER);
		JPanel westPanel = new JPanel();
		westPanel.setVisible(false);
		JPanel eastPanel = new JPanel();
		eastPanel.setVisible(false);
		this.topPanel.add(westPanel,BorderLayout.WEST);
		this.topPanel.add(eastPanel,BorderLayout.EAST);
		
		this.btnNew = new JButton("");
		this.btnNew.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				MenuEventParameter menuEventParameter=new MenuEventParameter();
				menuEventParameter.setMenuPosition(getX()+fileNewMenu.getX(), getY()+fileNewMenu.getY());
				menuEventParameter.setMenuType(MenuType.File_New);
				eventDispatcher.fireEvent(menuEventParameter);	
			}
		});
		this.btnNew.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		this.btnNew.setIcon(new ImageIcon(JCGMEditor.class.getResource("/images/new.gif")));
		this.btnNew.setToolTipText("New");
		this.toolBar.add(this.btnNew);

		this.label = new JLabel(" ");
		this.toolBar.add(this.label);

		this.btnOpen = new JButton("");
		this.btnOpen.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				MenuEventParameter menuEventParameter=new MenuEventParameter();
				menuEventParameter.setMenuPosition(getX()+fileOpenMenu.getX(), getY()+fileOpenMenu.getY());
				menuEventParameter.setMenuType(MenuType.File_Open);
				eventDispatcher.fireEvent(menuEventParameter);		
			}
		});
		this.btnOpen.setBorder(BorderFactory.createRaisedBevelBorder());
		this.btnOpen.setIcon(new ImageIcon(JCGMEditor.class.getResource("/images/open.gif")));
		this.btnOpen.setToolTipText("Open");
		this.toolBar.add(this.btnOpen);

		this.label_1 = new JLabel(" ");
		this.toolBar.add(this.label_1);

		this.btnSave = new JButton("");
		this.btnSave.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				MenuEventParameter menuEventParameter=new MenuEventParameter();
				menuEventParameter.setMenuType(MenuType.File_Save);
				eventDispatcher.fireEvent(menuEventParameter);	
			}
		});
		this.btnSave.setBorder(BorderFactory.createRaisedBevelBorder());
		this.btnSave.setToolTipText("Save");
		this.btnSave.setIcon(new ImageIcon(JCGMEditor.class.getResource("/images/save.gif")));
		this.toolBar.add(this.btnSave);

		this.label_2 = new JLabel(" ");
		this.toolBar.add(this.label_2);

		this.btnPrint = new JButton("");
		this.btnPrint.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				MenuEventParameter menuEventParameter=new MenuEventParameter();
				menuEventParameter.setMenuType(MenuType.File_Print);
				eventDispatcher.fireEvent(menuEventParameter);	
			}
		});
		this.btnPrint.setBorder(BorderFactory.createRaisedBevelBorder());
		this.btnPrint.setToolTipText("Print");
		this.btnPrint.setIcon(new ImageIcon(JCGMEditor.class.getResource("/images/print.gif")));
		this.toolBar.add(this.btnPrint);

		this.label_3 = new JLabel("    ");
		this.toolBar.add(this.label_3);			

		this.label_6 = new JLabel("  ");
		this.label_6.setIcon(new ImageIcon(JCGMEditor.class.getResource("/images/i32_1.png")));
		this.toolBar.add(this.label_6);

		this.btnSelect = new JButton("");
		this.btnSelect.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				MenuEventParameter menuEventParameter=new MenuEventParameter();
				menuEventParameter.setMenuType(MenuType.EXPORT_ViewPort);
				eventDispatcher.fireEvent(menuEventParameter);	
			}
		});
		this.btnSelect.setBorder(BorderFactory.createRaisedBevelBorder());
		this.btnSelect.setToolTipText("Select");
		this.btnSelect.setIcon(new ImageIcon(JCGMEditor.class.getResource("/images/select.gif")));
		this.toolBar.add(this.btnSelect);

		this.label_4 = new JLabel(" ");
		this.toolBar.add(this.label_4);

		this.btnUndo = new JButton("");
		this.btnUndo.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				MenuEventParameter menuEventParameter=new MenuEventParameter();
				menuEventParameter.setMenuType(MenuType.Edit_Undo);
				eventDispatcher.fireEvent(menuEventParameter);	
			}
		});
		this.btnUndo.setBorder(BorderFactory.createRaisedBevelBorder());
		this.btnUndo.setToolTipText("Undo");
		this.btnUndo.setIcon(new ImageIcon(JCGMEditor.class.getResource("/images/undo.gif")));
		this.toolBar.add(this.btnUndo);

		this.label_5 = new JLabel(" ");
		this.toolBar.add(this.label_5);

		this.btnRedo = new JButton("");
		this.btnRedo.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				MenuEventParameter menuEventParameter=new MenuEventParameter();
				menuEventParameter.setMenuType(MenuType.Edit_Redo);
				eventDispatcher.fireEvent(menuEventParameter);	
			}
		});
		this.btnRedo.setBorder(BorderFactory.createRaisedBevelBorder());
		this.btnRedo.setToolTipText("Redo");
		this.btnRedo.setIcon(new ImageIcon(JCGMEditor.class.getResource("/images/redo.gif")));
		this.toolBar.add(this.btnRedo);
		
		
		this.btnZoomIn = new JButton("");
		this.btnZoomIn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				MenuEventParameter menuEventParameter=new MenuEventParameter();
				menuEventParameter.setMenuPosition(getX()+fileOpenMenu.getX(), getY()+fileOpenMenu.getY());
				menuEventParameter.setMenuType(MenuType.Window_ZoomIn);
				eventDispatcher.fireEvent(menuEventParameter);		
			}
		});
		this.btnZoomIn.setBorder(BorderFactory.createRaisedBevelBorder());
		this.btnZoomIn.setToolTipText("Zoom In");
		this.btnZoomIn.setIcon(new ImageIcon(JCGMEditor.class.getResource("/images/zoomIn.gif")));
		this.toolBar.add(this.btnZoomIn);
		
		this.btnZoomOut = new JButton("");
		this.btnZoomOut.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				MenuEventParameter menuEventParameter=new MenuEventParameter();
				menuEventParameter.setMenuPosition(getX()+fileOpenMenu.getX(), getY()+fileOpenMenu.getY());
				menuEventParameter.setMenuType(MenuType.Window_ZoomOut);
				eventDispatcher.fireEvent(menuEventParameter);		
			}
		});
		this.btnZoomOut.setBorder(BorderFactory.createRaisedBevelBorder());
		this.btnZoomOut.setToolTipText("Zoom Out");
		this.btnZoomOut.setIcon(new ImageIcon(JCGMEditor.class.getResource("/images/zoomOut.gif")));
		this.toolBar.add(this.btnZoomOut);
		
		this.btnFitToWidth = new JButton("");
		this.btnFitToWidth.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				MenuEventParameter menuEventParameter=new MenuEventParameter();
				menuEventParameter.setMenuType(MenuType.Window_FitToWidth);
				eventDispatcher.fireEvent(menuEventParameter);	
			}
		});
		this.btnFitToWidth.setBorder(BorderFactory.createRaisedBevelBorder());
		this.btnFitToWidth.setToolTipText("Fit To Width");
		this.btnFitToWidth.setIcon(new ImageIcon(JCGMEditor.class.getResource("/images/fitToWidth.gif")));
		this.toolBar.add(this.btnFitToWidth);
		
		this.btnFitToHeight = new JButton("");
		this.btnFitToHeight.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				MenuEventParameter menuEventParameter=new MenuEventParameter();
				menuEventParameter.setMenuType(MenuType.Window_FitToHeight);
				eventDispatcher.fireEvent(menuEventParameter);	
			}
		});
		this.btnFitToHeight.setBorder(BorderFactory.createRaisedBevelBorder());
		this.btnFitToHeight.setToolTipText("Fit To Height");
		this.btnFitToHeight.setIcon(new ImageIcon(JCGMEditor.class.getResource("/images/fitToHeight.gif")));
		this.toolBar.add(this.btnFitToHeight);
		
		this.btnBestFit = new JButton("");
		this.btnBestFit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				MenuEventParameter menuEventParameter=new MenuEventParameter();
				menuEventParameter.setMenuType(MenuType.Window_BestFit);
				eventDispatcher.fireEvent(menuEventParameter);	
			}
		});
		this.btnBestFit.setBorder(BorderFactory.createRaisedBevelBorder());
		this.btnBestFit.setToolTipText("Best Fit");
		this.btnBestFit.setIcon(new ImageIcon(JCGMEditor.class.getResource("/images/bestFit.gif")));
		this.toolBar.add(this.btnBestFit);
		
		this.btnDetailView = new JButton("");
		this.btnDetailView.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				MenuEventParameter menuEventParameter=new MenuEventParameter();
				menuEventParameter.setMenuType(MenuType.QuickSelect_DetailView);
				eventDispatcher.fireEvent(menuEventParameter);	
			}
		});
		this.btnDetailView.setBorder(BorderFactory.createRaisedBevelBorder());
		this.btnDetailView.setToolTipText("Detail View");
		this.btnDetailView.setIcon(new ImageIcon(JCGMEditor.class.getResource("/images/detailView.gif")));
		this.toolBar.add(this.btnDetailView);
		
		this.btnOverView = new JButton("");
		this.btnOverView.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				MenuEventParameter menuEventParameter=new MenuEventParameter();
				menuEventParameter.setMenuType(MenuType.QuickSelect_OverView);
				eventDispatcher.fireEvent(menuEventParameter);	
			}
		});
		this.btnOverView.setBorder(BorderFactory.createRaisedBevelBorder());
		this.btnOverView.setToolTipText("Over View");
		this.btnOverView.setIcon(new ImageIcon(JCGMEditor.class.getResource("/images/overView.gif")));
		this.toolBar.add(this.btnOverView);
		
		this.btnPanMode = new JButton("");
		this.btnPanMode.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				MenuEventParameter menuEventParameter=new MenuEventParameter();
				menuEventParameter.setMenuType(MenuType.QuickSelect_PanMode);
				eventDispatcher.fireEvent(menuEventParameter);	
			}
		});
		this.btnPanMode.setBorder(BorderFactory.createRaisedBevelBorder());
		this.btnPanMode.setToolTipText("Pan Mode");
		this.btnPanMode.setIcon(new ImageIcon(JCGMEditor.class.getResource("/images/panMode.gif")));
		this.toolBar.add(this.btnPanMode);
		
		this.btnRotateLeft = new JButton("");
		this.btnRotateLeft.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				MenuEventParameter menuEventParameter=new MenuEventParameter();
				menuEventParameter.setMenuType(MenuType.Layout_RotateDrawLeft);
				eventDispatcher.fireEvent(menuEventParameter);	
			}
		});
		this.btnRotateLeft.setBorder(BorderFactory.createRaisedBevelBorder());
		this.btnRotateLeft.setToolTipText("Rotate Left");
		this.btnRotateLeft.setIcon(new ImageIcon(JCGMEditor.class.getResource("/images/rotateLeft.gif")));
		this.toolBar.add(this.btnRotateLeft);
		
		this.btnRotateRight = new JButton("");
		this.btnRotateRight.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				MenuEventParameter menuEventParameter=new MenuEventParameter();
				menuEventParameter.setMenuType(MenuType.Layout_RotateDrawRight);
				eventDispatcher.fireEvent(menuEventParameter);	
			}
		});
		this.btnRotateRight.setBorder(BorderFactory.createRaisedBevelBorder());
		this.btnRotateRight.setToolTipText("Rotate Right");
		this.btnRotateRight.setIcon(new ImageIcon(JCGMEditor.class.getResource("/images/rotateRight.gif")));
		this.toolBar.add(this.btnRotateRight);
		
		this.btnShowHotSpot = new JButton("");
		this.btnShowHotSpot.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				MenuEventParameter menuEventParameter=new MenuEventParameter();
				menuEventParameter.setMenuType(MenuType.QuickSelect_ShowHotSpot);
				eventDispatcher.fireEvent(menuEventParameter);	
			}
		});
		this.btnShowHotSpot.setBorder(BorderFactory.createRaisedBevelBorder());
		this.btnShowHotSpot.setToolTipText("Show Hotspot");
		this.btnShowHotSpot.setIcon(new ImageIcon(JCGMEditor.class.getResource("/images/showHotSpot.gif")));
		this.toolBar.add(this.btnShowHotSpot);
		
		this.btnBack = new JButton("");
		this.btnBack.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				MenuEventParameter menuEventParameter=new MenuEventParameter();
				menuEventParameter.setMenuType(MenuType.QuickSelect_BackHotSpot);
				eventDispatcher.fireEvent(menuEventParameter);	
			}
		});
		this.btnBack.setBorder(BorderFactory.createRaisedBevelBorder());
		this.btnBack.setToolTipText("Back");
		this.btnBack.setIcon(new ImageIcon(JCGMEditor.class.getResource("/images/back.gif")));
		this.toolBar.add(this.btnBack);
		
		this.btnForward = new JButton("");
		this.btnForward.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				MenuEventParameter menuEventParameter=new MenuEventParameter();
				menuEventParameter.setMenuType(MenuType.QuickSelect_ForwardHotSpot);
				eventDispatcher.fireEvent(menuEventParameter);	
			}
		});
		this.btnForward.setBorder(BorderFactory.createRaisedBevelBorder());
		this.btnForward.setToolTipText("Forward");
		this.btnForward.setIcon(new ImageIcon(JCGMEditor.class.getResource("/images/forward.gif")));
		this.toolBar.add(this.btnForward);
		
		this.btnNoObjectFilter = new JButton("");
		this.btnNoObjectFilter.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				MenuEventParameter menuEventParameter=new MenuEventParameter();
				menuEventParameter.setMenuType(MenuType.QuickSelect_NoObjFilter);
				eventDispatcher.fireEvent(menuEventParameter);	
			}
		});
		this.btnNoObjectFilter.setBorder(BorderFactory.createRaisedBevelBorder());
		this.btnNoObjectFilter.setToolTipText("No Object Filter");
		this.btnNoObjectFilter.setIcon(new ImageIcon(JCGMEditor.class.getResource("/images/filter.gif")));
		this.toolBar.add(this.btnNoObjectFilter);
		
		this.btnRefresh = new JButton("");
		this.btnRefresh.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				MenuEventParameter menuEventParameter=new MenuEventParameter();
				menuEventParameter.setMenuType(MenuType.QuickSelect_Refresh);
				eventDispatcher.fireEvent(menuEventParameter);	
			}
		});
		this.btnRefresh.setBorder(BorderFactory.createRaisedBevelBorder());
		this.btnRefresh.setToolTipText("Refresh");
		this.btnRefresh.setIcon(new ImageIcon(JCGMEditor.class.getResource("/images/refresh.gif")));
		this.toolBar.add(this.btnRefresh);
		
		
		toolBar2 = new JToolBar();
		this.topPanel.add(toolBar2,BorderLayout.SOUTH);                                           
		toolBar2.setLayout(new FlowLayout(FlowLayout.LEFT,0,0));
		ButtonGroup group = new ButtonGroup();
		
		
		btnMoveObjectToFront = new JToggleButton(new ImageIcon(JCGMEditor.class.getResource("/images/moveObjectToFront.gif")));
		btnMoveObjectToFront.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				int state = e.getStateChange();
				if(state==ItemEvent.SELECTED){
					MenuEventParameter menuEventParameter = new MenuEventParameter();
					menuEventParameter.setMenuType(MenuType.Tool_MoveObjToFront);
					eventDispatcher.fireEvent(menuEventParameter);
				}
			}
		});
		btnMoveObjectToFront.setToolTipText("Move Object to Front");
		btnMoveObjectToFront.setPreferredSize(new Dimension(25,25));
		
		btnDeleteObject = new JToggleButton(new ImageIcon(JCGMEditor.class.getResource("/images/deleteObject.gif")));
		btnDeleteObject.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				int state = e.getStateChange();
				if(state==ItemEvent.SELECTED){
					MenuEventParameter menuEventParameter = new MenuEventParameter();
					menuEventParameter.setMenuType(MenuType.Tool_DeleteObj);
					eventDispatcher.fireEvent(menuEventParameter);
				}
			}
		});
		btnDeleteObject.setToolTipText("Delete Object");
		btnDeleteObject.setPreferredSize(new Dimension(25,25));
		
		btnRotateObject = new JToggleButton(new ImageIcon(JCGMEditor.class.getResource("/images/rotateObject.gif")));
		btnRotateObject.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				int state = e.getStateChange();
				if(state==ItemEvent.SELECTED){
					MenuEventParameter menuEventParameter = new MenuEventParameter();
					menuEventParameter.setMenuType(MenuType.Tool_RotateObj);
					eventDispatcher.fireEvent(menuEventParameter);
				}
			}
		});
		btnRotateObject.setToolTipText("Rotate Object");
		btnRotateObject.setPreferredSize(new Dimension(25,25));
		
		btnMveObjectOneLevelBack = new JToggleButton(new ImageIcon(JCGMEditor.class.getResource("/images/moveObjectOneLevelBack.gif")));
		btnMveObjectOneLevelBack.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				int state = e.getStateChange();
				if(state==ItemEvent.SELECTED){
					MenuEventParameter menuEventParameter = new MenuEventParameter();
					menuEventParameter.setMenuType(MenuType.Tool_MoveObjToOneLvlBack);
					eventDispatcher.fireEvent(menuEventParameter);
				}
			}
		});
		btnMveObjectOneLevelBack.setToolTipText("Move Object One Level Back");
		btnMveObjectOneLevelBack.setPreferredSize(new Dimension(25,25));
		
		btnMoveObjectOneLevelFront = new JToggleButton(new ImageIcon(JCGMEditor.class.getResource("/images/moveObjectOneLevelUp.gif")));
		btnMoveObjectOneLevelFront.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				int state = e.getStateChange();
				if(state==ItemEvent.SELECTED){
					MenuEventParameter menuEventParameter = new MenuEventParameter();
					menuEventParameter.setMenuType(MenuType.Tool_MoveObjToOneLvlUp);
					eventDispatcher.fireEvent(menuEventParameter);
				}
			}
		});
		btnMoveObjectOneLevelFront.setToolTipText("Move Object One Level Up");
		btnMoveObjectOneLevelFront.setPreferredSize(new Dimension(25,25));
		
		btnHotspot = new JToggleButton(new ImageIcon(JCGMEditor.class.getResource("/images/hotSpot.gif")));
		btnHotspot.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				int state = e.getStateChange();
				if(state==ItemEvent.SELECTED){
					MenuEventParameter menuEventParameter = new MenuEventParameter();
					menuEventParameter.setMenuType(MenuType.Tool_HotSpot);
					eventDispatcher.fireEvent(menuEventParameter);
				}
			}
		});
		btnHotspot.setToolTipText("Hotspot");
		btnHotspot.setPreferredSize(new Dimension(25,25));
		
		btnInsertGraphic = new JToggleButton(new ImageIcon(JCGMEditor.class.getResource("/images/insertGraphic.gif")));
		btnInsertGraphic.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				int state = e.getStateChange();
				if(state==ItemEvent.SELECTED){
					MenuEventParameter menuEventParameter = new MenuEventParameter();
					menuEventParameter.setMenuType(MenuType.Tool_InsertGraphic);
					eventDispatcher.fireEvent(menuEventParameter);
				}
			}
		});
		btnInsertGraphic.setToolTipText("Insert Graphic");
		btnInsertGraphic.setPreferredSize(new Dimension(25,25));
		
		btnMoveObjectToBack = new JToggleButton(new ImageIcon(JCGMEditor.class.getResource("/images/moveObjectToBack.gif")));
		btnMoveObjectToBack.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				int state = e.getStateChange();
				if(state==ItemEvent.SELECTED){
					MenuEventParameter menuEventParameter = new MenuEventParameter();
					menuEventParameter.setMenuType(MenuType.Tool_MoveObjToBack);
					eventDispatcher.fireEvent(menuEventParameter);
				}
			}
		});
		btnMoveObjectToBack.setToolTipText("Move Object To Back");
		btnMoveObjectToBack.setPreferredSize(new Dimension(25,25));
		
		btnSelectGroup = new JToggleButton(new ImageIcon(JCGMEditor.class.getResource("/images/selectGroup.gif")));
		btnSelectGroup.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				int state = e.getStateChange();
				if(state==ItemEvent.SELECTED){
					MenuEventParameter menuEventParameter = new MenuEventParameter();
					menuEventParameter.setMenuType(MenuType.Tool_SelectGroup);
					eventDispatcher.fireEvent(menuEventParameter);
				}
			}
		});
		btnSelectGroup.setToolTipText("Select Group");
		btnSelectGroup.setPreferredSize(new Dimension(25,25));
		
	
		btnSymbol = new JToggleButton(new ImageIcon(JCGMEditor.class.getResource("/images/symbol.gif")));
		btnSymbol.setToolTipText("Symbol");
		btnSymbol.setPreferredSize(new Dimension(25,25));
		btnSymbol.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				int state = e.getStateChange();
				if(state==ItemEvent.SELECTED){
					CanvasEventParameter canvasEventParameter = new CanvasEventParameter();
					MenuEventParameter menuEventParameter = new MenuEventParameter();
					canvasEventParameter.setDrawType(DrawType.SYMBOL);
					menuEventParameter.setMenuType(MenuType.Tool_SYMBOL);
					eventDispatcher.fireEvent(canvasEventParameter);
					eventDispatcher.fireEvent(menuEventParameter);
				}
			}
		});
		btnText = new JToggleButton(new ImageIcon(JCGMEditor.class.getResource("/images/text.gif")));
		btnText.setToolTipText("Text");
		btnText.setPreferredSize(new Dimension(25,25));
		btnText.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				int state = e.getStateChange();
				if(state==ItemEvent.SELECTED){
					CanvasEventParameter canvasEventParameter = new CanvasEventParameter();
					MenuEventParameter menuEventParameter = new MenuEventParameter();
					canvasEventParameter.setDrawType(DrawType.TEXT);
					menuEventParameter.setMenuType(MenuType.Tool_TEXT);
					eventDispatcher.fireEvent(canvasEventParameter);
					eventDispatcher.fireEvent(menuEventParameter);
				}
			}
		});
		btnBoxedText = new JToggleButton(new ImageIcon(JCGMEditor.class.getResource("/images/boxedText.gif")));
		btnBoxedText.setToolTipText("Boxed Text");
		btnBoxedText.setPreferredSize(new Dimension(25,25));
		btnBoxedText.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				int state = e.getStateChange();
				if(state==ItemEvent.SELECTED){
					CanvasEventParameter canvasEventParameter = new CanvasEventParameter();
					MenuEventParameter menuEventParameter = new MenuEventParameter();
					canvasEventParameter.setDrawType(DrawType.BOXED_TEXT);
					menuEventParameter.setMenuType(MenuType.Tool_BOXED_TEXT);
					eventDispatcher.fireEvent(canvasEventParameter);
					eventDispatcher.fireEvent(menuEventParameter);
				}
			}
		});
		btnLeaderLine = new JToggleButton(new ImageIcon(JCGMEditor.class.getResource("/images/leaderLine.gif")));
		btnLeaderLine.setPreferredSize(new Dimension(25,25));
		btnLeaderLine.setToolTipText("Leader Line");
		btnLeaderLine.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				int state = e.getStateChange();
				if(state==ItemEvent.SELECTED){
					CanvasEventParameter canvasEventParameter = new CanvasEventParameter();
					MenuEventParameter menuEventParameter = new MenuEventParameter();
					canvasEventParameter.setDrawType(DrawType.LEADER_LINE);
					menuEventParameter.setMenuType(MenuType.Tool_LEADER_LINE);
					eventDispatcher.fireEvent(canvasEventParameter);
					eventDispatcher.fireEvent(menuEventParameter);
				}
			}
		});
		btnLine = new JToggleButton(new ImageIcon(JCGMEditor.class.getResource("/images/line1.gif")));
		btnLine.setToolTipText("Line");
		btnLine.setPreferredSize(new Dimension(25,25));
		btnLine.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				int state = e.getStateChange();
				if(state==ItemEvent.SELECTED){
					CanvasEventParameter canvasEventParameter = new CanvasEventParameter();
					MenuEventParameter menuEventParameter = new MenuEventParameter();
					canvasEventParameter.setDrawType(DrawType.LINE);
					menuEventParameter.setMenuType(MenuType.Tool_LINE);
					eventDispatcher.fireEvent(canvasEventParameter);
					eventDispatcher.fireEvent(menuEventParameter);
				}
			}
		});
		btnPolyLine = new JToggleButton(new ImageIcon(JCGMEditor.class.getResource("/images/polyLine.gif")));
		btnPolyLine.setToolTipText("Poly Line");
		btnPolyLine.setPreferredSize(new Dimension(25,25));
		btnPolyLine.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				int state = e.getStateChange();
				if(state==ItemEvent.SELECTED){
					CanvasEventParameter canvasEventParameter = new CanvasEventParameter();
					MenuEventParameter menuEventParameter = new MenuEventParameter();
					canvasEventParameter.setDrawType(DrawType.POLYLINE);
					menuEventParameter.setMenuType(MenuType.Tool_POLYLINE);
					eventDispatcher.fireEvent(canvasEventParameter);
					eventDispatcher.fireEvent(menuEventParameter);
				}
			}
		});
		btnArc = new JToggleButton(new ImageIcon(JCGMEditor.class.getResource("/images/arc.gif")));
		btnArc.setToolTipText("Arc");
		btnArc.setPreferredSize(new Dimension(25,25));
		btnArc.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				int state = e.getStateChange();
				if(state==ItemEvent.SELECTED){
					CanvasEventParameter canvasEventParameter = new CanvasEventParameter();
					MenuEventParameter menuEventParameter = new MenuEventParameter();
					canvasEventParameter.setDrawType(DrawType.ARC);
					menuEventParameter.setMenuType(MenuType.Tool_ARC);
					eventDispatcher.fireEvent(canvasEventParameter);
					eventDispatcher.fireEvent(menuEventParameter);
				}
			}
		});
		btnSweepArrow = new JToggleButton(new ImageIcon(JCGMEditor.class.getResource("/images/sweepArrow.gif")));
		btnSweepArrow.setToolTipText("Sweep Arrow");
		btnSweepArrow.setPreferredSize(new Dimension(25,25));
		btnSweepArrow.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				int state = e.getStateChange();
				if(state==ItemEvent.SELECTED){
					CanvasEventParameter canvasEventParameter = new CanvasEventParameter();
					MenuEventParameter menuEventParameter = new MenuEventParameter();
					canvasEventParameter.setDrawType(DrawType.SWEEP_ARROW);
					menuEventParameter.setMenuType(MenuType.Tool_SWEEP_ARROW);
					eventDispatcher.fireEvent(canvasEventParameter);
					eventDispatcher.fireEvent(menuEventParameter);
				}
			}
		});
		btnPolygon = new JToggleButton(new ImageIcon(JCGMEditor.class.getResource("/images/polygon.gif")));
		btnPolygon.setToolTipText("Polygon");
		btnPolygon.setPreferredSize(new Dimension(25,25));
		btnPolygon.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				int state = e.getStateChange();
				if(state==ItemEvent.SELECTED){
					CanvasEventParameter canvasEventParameter = new CanvasEventParameter();
					MenuEventParameter menuEventParameter = new MenuEventParameter();
					canvasEventParameter.setDrawType(DrawType.POLYGON);
					menuEventParameter.setMenuType(MenuType.Tool_POLYGON);
					eventDispatcher.fireEvent(canvasEventParameter);
					eventDispatcher.fireEvent(menuEventParameter);
				}
			}
		});
		btnDefault = new JToggleButton(new ImageIcon(JCGMEditor.class.getResource("/images/defaultCursor.gif")));
		btnDefault.setToolTipText("Select Object");
		btnDefault.setPreferredSize(new Dimension(25,25));
		btnDefault.setSelected(true);
		btnDefault.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				int state = e.getStateChange();
				if(state==ItemEvent.SELECTED){
					CanvasEventParameter canvasEventParameter = new CanvasEventParameter();
					MenuEventParameter menuEventParameter = new MenuEventParameter();
					canvasEventParameter.setDrawType(DrawType.DEFAULT);
					menuEventParameter.setMenuType(MenuType.Tool_DEFAULT);
					eventDispatcher.fireEvent(canvasEventParameter);
					eventDispatcher.fireEvent(menuEventParameter);
				}
			}
		});
		
		btnCurveLine = new JToggleButton(new ImageIcon(JCGMEditor.class.getResource("/images/curveLine.gif")));
		btnCurveLine.setToolTipText("Curve Line");
		btnCurveLine.setPreferredSize(new Dimension(25,25));
		btnCurveLine.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				int state = e.getStateChange();
				if(state==ItemEvent.SELECTED){
					CanvasEventParameter canvasEventParameter = new CanvasEventParameter();
					MenuEventParameter menuEventParameter = new MenuEventParameter();
					canvasEventParameter.setDrawType(DrawType.BEZIER);
					menuEventParameter.setMenuType(MenuType.Tool_BEZIER);
					eventDispatcher.fireEvent(canvasEventParameter);
					eventDispatcher.fireEvent(menuEventParameter);
				}
			}
		});
		btnEllipse = new JToggleButton(new ImageIcon(JCGMEditor.class.getResource("/images/ellipse1.gif")));
		btnEllipse.setToolTipText("Ellipse");
		btnEllipse.setPreferredSize(new Dimension(25,25));
		btnEllipse.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				int state = e.getStateChange();
				if(state==ItemEvent.SELECTED){
					CanvasEventParameter canvasEventParameter = new CanvasEventParameter();
					MenuEventParameter menuEventParameter = new MenuEventParameter();
					canvasEventParameter.setDrawType(DrawType.ELLIPSE);
					menuEventParameter.setMenuType(MenuType.Tool_ELLIPSE);
					eventDispatcher.fireEvent(canvasEventParameter);
					eventDispatcher.fireEvent(menuEventParameter);
				}
			}
		});
		btnRectangle = new JToggleButton(new ImageIcon(JCGMEditor.class.getResource("/images/rectangle1.gif")));
		btnRectangle.setToolTipText("Rectangle");
		btnRectangle.setPreferredSize(new Dimension(25,25));
		btnRectangle.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				int state = e.getStateChange();
				if(state==ItemEvent.SELECTED){
					CanvasEventParameter canvasEventParameter = new CanvasEventParameter();
					MenuEventParameter menuEventParameter = new MenuEventParameter();
					canvasEventParameter.setDrawType(DrawType.RECTANGLE);
					menuEventParameter.setMenuType(MenuType.Tool_RECTANGLE);
					eventDispatcher.fireEvent(canvasEventParameter);
					eventDispatcher.fireEvent(menuEventParameter);
				}
			}
		});
		group.add(btnMoveObjectToFront);
		group.add(btnDeleteObject);
		group.add(btnRotateObject);
		group.add(btnSymbol);
		group.add(btnMveObjectOneLevelBack);
		group.add(btnMoveObjectOneLevelFront);
		group.add(btnHotspot);
		group.add(btnInsertGraphic);
		group.add(btnMoveObjectToBack);
		group.add(btnSelectGroup);
		group.add(btnSweepArrow);
		group.add(btnText);
		group.add(btnArc);
		group.add(btnBoxedText);
		group.add(btnLeaderLine);
		group.add(btnLine);
		group.add(btnPolygon);
		group.add(btnPolyLine);
		group.add(btnDefault);
		group.add(btnCurveLine);
		group.add(btnEllipse);
		group.add(btnRectangle);
		
		
		toolBar2.add(btnDefault);
		toolBar2.add(btnHotspot);
		toolBar2.add(btnSymbol);
		toolBar2.add(btnRotateObject);
		toolBar2.add(btnDeleteObject);
		toolBar2.add(btnMoveObjectToFront);
		toolBar2.add(btnMoveObjectToBack);
		toolBar2.add(btnMoveObjectOneLevelFront);
		toolBar2.add(btnMveObjectOneLevelBack);
		toolBar2.add(btnCurveLine);
		toolBar2.add(btnInsertGraphic);
		toolBar2.add(btnText);
		toolBar2.add(btnBoxedText);
		toolBar2.add(btnLeaderLine);
		toolBar2.add(btnLine);
		toolBar2.add(btnPolyLine);
		toolBar2.add(btnArc);
		toolBar2.add(btnSweepArrow);
		toolBar2.add(btnPolygon);
		toolBar2.add(btnEllipse);
		toolBar2.add(btnRectangle);
		toolBar2.add(btnSelectGroup);
		toolBar2.setBorderPainted(true);
	
	
		container=this.getContentPane();
	    desktop=new JDesktopPane();
	    desktop.setLayout(null);	    
	    desktop.setBackground(Color.DARK_GRAY);
		container.add(desktop,BorderLayout.CENTER);
		//this.getContentPane().setBackground(Color.DARK_GRAY);
		this.setSize(new Dimension(586, 352));
		this.setVisible(true);

	}
   
	public void addJCGMInternalFrame(JCGMInternalFrame canvas)
	{		
		//JCGMInternalFrame canvas=new JCGMInternalFrame(name, id);
		//canvas.setEventDispatcher(this.eventDispatcher);
		this.desktop.add(canvas);
		canvas.setVisible(true);
		try{
			canvas.setSelected(true);
		}catch (PropertyVetoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	 public abstract class Action_Event implements ActionListener{
		  protected boolean YesOrNo;
		  public Action_Event (){
			  this.YesOrNo = true ;
		  }
		@Override
		public abstract void actionPerformed(ActionEvent e);
	  }
	
	public void setEventDispatcher(EventDispatcher eventDispatcher) {
		this.eventDispatcher = eventDispatcher;
	}
	public JCGMInternalFrame[] getJCGMInternalFrames()
	{
		return (JCGMInternalFrame[]) this.desktop.getAllFrames();
	}
	public JCGMInternalFrame getSelectedJCGMInternalFrame()
	{
		return (JCGMInternalFrame) this.desktop.getSelectedFrame();
	}
}
