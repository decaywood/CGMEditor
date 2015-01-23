/**
 * 
 */
package net.sf.jcgm.event;

/**
 * 2013-11-6
 */
public enum MenuType {
	File_New,
	File_Open,
	File_Close,//
	File_Save,
	File_SaveAs,
	File_Print,
	File_Exit,
	
	Edit_Undo,
	Edit_Redo,
	Edit_Cut,
	Edit_Copy,
	Edit_Paste,
	Edit_Duplicate,
	Edit_Crop,
	Edit_SelectAll_All,
	Edit_SelectAll_Lines,
	Edit_SelectAll_Solids,
	Edit_SelectAll_Text,
	Edit_CaptureImage,
	
	EXPORT_EntireFile,
	EXPORT_ViewPort,
	
	Options_Units_Inches,
	Options_Units_Millimeters,
	Options_CursorUnits_Inches,
	Options_CursorUnits_Millimeters,
	Options_CursorUnits_VDCs,
	Options_Profile_ATA,
	Options_Profile_CALS,
	Options_Profile_CGMPlus,
	Options_Profile_PIP,
	Options_Profile_WebCGM,
	Options_Profile_None,
	Options_ViewOpt_TextRenderMethord_BoxedCap,
	Options_ViewOpt_TextRenderMethord_Basic,
	Options_ViewOpt_AbstractFiles_ShowAbstrResolDiaOnOpenSelect,
	Options_ViewOpt_AbstractFiles_ShowAbstrResolDiaOnOpenDeselect,
	Options_ViewOpt_BackGrdColor_UseWhite,
	Options_ViewOpt_BackGrdColor_UseFileSpecBackGrdColor,
	Options_ViewOpt_BackGrdColor_UseSystemWindowColor,
	Options_ViewOpt_HideHotSpotsSelect,
	Options_ViewOpt_HideHotSpotsDeselect,
	Options_DefaultDrawOptions,
	Options_EnableLoginPrintOpsSelect,
	Options_EnableLoginPrintOpsDeselect,
	Options_RulersSelect,
	Options_RulersDeselect,
	Options_SaveSettingAsDefaults,
	
	Layout_Resize,
	Layout_Grids,
	Layout_LayerManagement,
	Layout_EditorPattern,
	Layout_ViewStrctList,
	Layout_FilterObject,
	Layout_RotateDrawLeft,
	Layout_RotateDrawRight,
	
	Window_ZoomIn,
	Window_ZoomOut,
	Window_FitToWidth,
	Window_FitToHeight,
	Window_BestFit,
	Window_ZoomFactor_2X,
	Window_ZoomFactor_4X,
	Window_ZoomFactor_8X,
	Window_ZoomFactor_16X,
	
	Help_Register,
	Help_Update,
	Help_About,
	
	
	QuickSelect_DetailView,
	QuickSelect_OverView,
	QuickSelect_PanMode,
	QuickSelect_ShowHotSpot,
	QuickSelect_BackHotSpot,
	QuickSelect_ForwardHotSpot,
	QuickSelect_NoObjFilter,
	QuickSelect_Refresh,
	
	Tool_HotSpot,
	Tool_DeleteObj,
	Tool_RotateObj,
	Tool_MoveObjToFront,
	Tool_MoveObjToBack,
	Tool_MoveObjToOneLvlUp,
	Tool_MoveObjToOneLvlBack,
	Tool_InsertGraphic,
	Tool_SelectGroup,
	Tool_BEZIER,
	Tool_ARC,
	Tool_BOXED_TEXT,
	Tool_LEADER_LINE,
	Tool_LINE,
	Tool_RECTANGLE,
	Tool_ELLIPSE,
	Tool_POLYGON,
	Tool_POLYLINE,
	Tool_SWEEP_ARROW,
	Tool_SYMBOL,
	Tool_TEXT,
	Tool_DEFAULT,
}