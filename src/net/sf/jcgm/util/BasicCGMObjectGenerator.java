package net.sf.jcgm.util;

import java.awt.Color;
import java.awt.geom.CubicCurve2D;
import java.awt.geom.Path2D;
import java.awt.geom.Point2D;
import java.util.ArrayList;

import net.sf.jcgm.core.AlternateCharacterSetIndex;
import net.sf.jcgm.core.ApplicationData;
import net.sf.jcgm.core.BackgroundColour;
import net.sf.jcgm.core.BeginMetafile;
import net.sf.jcgm.core.BeginPicture;
import net.sf.jcgm.core.BeginPictureBody;
import net.sf.jcgm.core.CGM;
import net.sf.jcgm.core.CGMObject;
import net.sf.jcgm.core.CharacterCodingAnnouncer;
import net.sf.jcgm.core.CharacterExpansionFactor;
import net.sf.jcgm.core.CharacterHeight;
import net.sf.jcgm.core.CharacterOrientation;
import net.sf.jcgm.core.CharacterSetIndex;
import net.sf.jcgm.core.CircleElement;
import net.sf.jcgm.core.CircularArc3Point;
import net.sf.jcgm.core.ClipIndicator;
import net.sf.jcgm.core.ClipRectangle;
import net.sf.jcgm.core.ColourIndexPrecision;
import net.sf.jcgm.core.ColourPrecision;
import net.sf.jcgm.core.ColourSelectionMode;
import net.sf.jcgm.core.ColourValueExtent;
import net.sf.jcgm.core.Command;
import net.sf.jcgm.core.DashCapIndicator;
import net.sf.jcgm.core.EdgeCap;
import net.sf.jcgm.core.EdgeColour;
import net.sf.jcgm.core.EdgeJoin;
import net.sf.jcgm.core.EdgeType;
import net.sf.jcgm.core.EdgeVisibility;
import net.sf.jcgm.core.EdgeWidth;
import net.sf.jcgm.core.EdgeWidthSpecificationMode;
import net.sf.jcgm.core.EllipseElement;
import net.sf.jcgm.core.FillColour;
import net.sf.jcgm.core.FontList;
import net.sf.jcgm.core.HatchIndex;
import net.sf.jcgm.core.PolyBezier;
import net.sf.jcgm.core.HatchIndex.HatchType;
import net.sf.jcgm.core.IndexPrecision;
import net.sf.jcgm.core.IntegerPrecision;
import net.sf.jcgm.core.InteriorStyle;
import net.sf.jcgm.core.InteriorStyleSpecificationMode;
import net.sf.jcgm.core.JoinIndicator;
import net.sf.jcgm.core.LineCap;
import net.sf.jcgm.core.LineCapIndicator;
import net.sf.jcgm.core.LineColour;
import net.sf.jcgm.core.LineJoin;
import net.sf.jcgm.core.LineType;
import net.sf.jcgm.core.LineWidth;
import net.sf.jcgm.core.LineWidthSpecificationMode;
import net.sf.jcgm.core.MarkerSizeSpecificationMode;
import net.sf.jcgm.core.MaximumColourIndex;
import net.sf.jcgm.core.MetafileDefaultsReplacement;
import net.sf.jcgm.core.MetafileDescription;
import net.sf.jcgm.core.MetafileElementList;
import net.sf.jcgm.core.MetafileVersion;
import net.sf.jcgm.core.PolygonElement;
import net.sf.jcgm.core.Polyline;
import net.sf.jcgm.core.RealPrecision;
import net.sf.jcgm.core.RectangleElement;
import net.sf.jcgm.core.RestrictedText;
import net.sf.jcgm.core.RestrictedTextType;
import net.sf.jcgm.core.ScalingMode;
import net.sf.jcgm.core.SpecificationMode;
import net.sf.jcgm.core.TextAlignment;
import net.sf.jcgm.core.TextColour;
import net.sf.jcgm.core.TextFontIndex;
import net.sf.jcgm.core.TextPrecision;
import net.sf.jcgm.core.VDCExtent;
import net.sf.jcgm.core.VDCIntegerPrecision;
import net.sf.jcgm.core.VDCType;


public class BasicCGMObjectGenerator {
	private CGMObjectConverter cGMObjectConverter;
	
	public BasicCGMObjectGenerator(ArrayList<CGMObject> objects){
		cGMObjectConverter = new CGMObjectConverter(objects);
	}
	
	private void generatorControlElements(Point2D.Double canvasP0,
												 Point2D.Double canvasP1){
		cGMObjectConverter.addAndConvert(new ClipRectangle(canvasP0, canvasP1));
		cGMObjectConverter.addAndConvert(new ClipIndicator(true));
	}
	
	private void generatorFillAndEdgeElements(InteriorStyle.Style interiorStyle,
											  Color fillColor,
											  double edgeWidth,
											  Color edgeColor,
											  LineCapIndicator lineCapIndicator,
											  DashCapIndicator dashCapIndicator,
											  JoinIndicator joinIndicator
			,boolean isVisible,int edgeType,HatchType hatchType){
		if(interiorStyle!=null){
			cGMObjectConverter.addAndConvert(new InteriorStyle(interiorStyle));
		}
		if(fillColor!=null){
			cGMObjectConverter.addAndConvert(new FillColour(fillColor));
		}
		cGMObjectConverter.addAndConvert(new EdgeWidth(edgeWidth));
		if(edgeColor!=null){
			cGMObjectConverter.addAndConvert(new EdgeColour(edgeColor));
		}
		if(hatchType!=null){
			cGMObjectConverter.addAndConvert(new HatchIndex(hatchType));
		}
		if(edgeType>0){
			cGMObjectConverter.addAndConvert(new EdgeType(edgeType));
		}
		cGMObjectConverter.addAndConvert(new EdgeCap(lineCapIndicator, dashCapIndicator));
		cGMObjectConverter.addAndConvert(new EdgeJoin(joinIndicator));
		cGMObjectConverter.addAndConvert(new EdgeVisibility(isVisible));
	}
	
	
	private void generatorLineElements(	double lineWidth,
												Color lineColor,
												int lineType){
		cGMObjectConverter.addAndConvert(new LineWidth(lineWidth));
		if(lineColor!=null){
			cGMObjectConverter.addAndConvert(new LineColour(lineColor));
		}
		if(lineType>0){
			cGMObjectConverter.addAndConvert(new LineType(lineType));
		}
		cGMObjectConverter.addAndConvert(new LineCap(LineCapIndicator.BUTT,DashCapIndicator.UNSPECIFIED));
		cGMObjectConverter.addAndConvert(new LineJoin(JoinIndicator.UNSPECIFIED));
	}
	
	
	
	
	public void initiateCGM(double units , 
								   Point2D.Double canvasP0,
								   Point2D.Double canvasP1){
		cGMObjectConverter.addAndConvert(new BeginMetafile(""));
		cGMObjectConverter.addAndConvert(new MetafileVersion(4));
		cGMObjectConverter.addAndConvert(new VDCType(VDCType.Type.INTEGER));
		cGMObjectConverter.addAndConvert(new IntegerPrecision(16));
		cGMObjectConverter.addAndConvert(new RealPrecision(RealPrecision.Precision.FLOATING_32));
		cGMObjectConverter.addAndConvert(new IndexPrecision(16));
		cGMObjectConverter.addAndConvert(new ColourPrecision(8));
		cGMObjectConverter.addAndConvert(new ColourIndexPrecision(16));
		cGMObjectConverter.addAndConvert(new MaximumColourIndex(255));
		cGMObjectConverter.addAndConvert(new CharacterCodingAnnouncer(CharacterCodingAnnouncer.Type.BASIC_8_BIT));
		cGMObjectConverter.addAndConvert(new ColourValueExtent());
		cGMObjectConverter.addAndConvert(new MetafileDescription(""));
		cGMObjectConverter.addAndConvert(new MetafileDefaultsReplacement(new VDCIntegerPrecision(16)));
		cGMObjectConverter.addAndConvert(new MetafileElementList(new String[]{"DRAWING SET","VERSION 4 SET"}));
		cGMObjectConverter.addAndConvert(new FontList(new String[]{"HELVETICA"}));
		cGMObjectConverter.addAndConvert(new CharacterCodingAnnouncer(CharacterCodingAnnouncer.Type.BASIC_8_BIT));
		cGMObjectConverter.addAndConvert(new BeginPicture());
		cGMObjectConverter.addAndConvert(new ScalingMode(ScalingMode.Mode.METRIC,25.40000081062317/units));
		cGMObjectConverter.addAndConvert(new ColourSelectionMode(ColourSelectionMode.Type.DIRECT));
		cGMObjectConverter.addAndConvert(new LineWidthSpecificationMode(SpecificationMode.ABSOLUTE));
		cGMObjectConverter.addAndConvert(new MarkerSizeSpecificationMode(SpecificationMode.ABSOLUTE));
		cGMObjectConverter.addAndConvert(new EdgeWidthSpecificationMode(SpecificationMode.ABSOLUTE));
		cGMObjectConverter.addAndConvert(new BackgroundColour(Color.WHITE));
		cGMObjectConverter.addAndConvert(new VDCExtent(canvasP0,canvasP1));
		cGMObjectConverter.addAndConvert(new InteriorStyleSpecificationMode(SpecificationMode.ABSOLUTE));
		cGMObjectConverter.addAndConvert(new BeginPictureBody());
	}

	
	public void generatePolyLine(Point2D.Double canvasP0,
										Point2D.Double canvasP1,
										double lineWidth,
										Color lineColor,
										int lineType,
										Path2D.Double polyPath){
		generatorControlElements(canvasP0, canvasP1);
		generatorLineElements(lineWidth, lineColor, lineType);
		cGMObjectConverter.addAndConvert(new Polyline(polyPath));
	}
	
	public void generateRestrictedText(Point2D.Double canvasP0,
											  Point2D.Double canvasP1,
											  Color fontColor,
											  TextAlignment.HorizontalAlignment horizontalAlignment,
											  TextAlignment.VerticalAlignment verticalAlignment,
											  double continuousHorizontalAlignment,
											  double continuousVerticalAlignment,
											  int fontIndex,
											  double expansionFactor,
											  double characterHeight,
											  double orientationXup,
											  double orientationYup,
											  double orientationXbase,
											  double orientationYbase,
											  String string,
											  double deltaWidth,
											  double deltaHeight,
											  Point2D.Double position){
		cGMObjectConverter.addAndConvert(new ApplicationData(12506, ""));
		generatorControlElements(canvasP0, canvasP1);
		cGMObjectConverter.addAndConvert(new LineWidth(1.0));
		cGMObjectConverter.addAndConvert(new TextColour(fontColor));
		cGMObjectConverter.addAndConvert(new TextPrecision());
		cGMObjectConverter.addAndConvert(new TextAlignment(horizontalAlignment, verticalAlignment, continuousHorizontalAlignment, continuousVerticalAlignment));
		cGMObjectConverter.addAndConvert(new TextFontIndex(fontIndex));
		cGMObjectConverter.addAndConvert(new CharacterExpansionFactor(expansionFactor));
		cGMObjectConverter.addAndConvert(new CharacterHeight(characterHeight));
		cGMObjectConverter.addAndConvert(new CharacterOrientation(orientationXup, orientationYup, orientationXbase, orientationYbase));
		cGMObjectConverter.addAndConvert(new CharacterSetIndex(1));
		cGMObjectConverter.addAndConvert(new AlternateCharacterSetIndex(1));
		cGMObjectConverter.addAndConvert(new RestrictedTextType(RestrictedTextType.Type.BOXED_CAP));
		cGMObjectConverter.addAndConvert(new RestrictedText(string, deltaWidth, deltaHeight, position));
	}
	
	public void generatePolygon(Point2D.Double canvasP0,
			Point2D.Double canvasP1,
			InteriorStyle.Style interiorStyle,
			Color fillColor,
			double edgeWidth,
			Color edgeColor,
			boolean isVisible,
			int edgeType,
			HatchType hatchType,
			Path2D.Double polygon){
		generatorControlElements(canvasP0, canvasP1);
		generatorFillAndEdgeElements(interiorStyle, fillColor, edgeWidth,
							  edgeColor, LineCapIndicator.UNSPECIFIED,
							  DashCapIndicator.UNSPECIFIED, JoinIndicator.
							  UNSPECIFIED, isVisible, edgeType, hatchType);
		cGMObjectConverter.addAndConvert(new PolygonElement(polygon));
	}
	
	public void generateRectangle(Point2D.Double canvasP0,
			Point2D.Double canvasP1,
			InteriorStyle.Style interiorStyle,
			Color fillColor,
			double edgeWidth,
			Color edgeColor,
			boolean isVisible,
			int edgeType,HatchType hatchType,
			Point2D.Double firstCorner,
			Point2D.Double secondCorner){
		generatorControlElements(canvasP0, canvasP1);
		generatorFillAndEdgeElements(interiorStyle, fillColor, edgeWidth,
							  edgeColor, LineCapIndicator.UNSPECIFIED,
							  DashCapIndicator.UNSPECIFIED, JoinIndicator.
							  UNSPECIFIED, isVisible, edgeType, hatchType);
		cGMObjectConverter.addAndConvert(new RectangleElement(firstCorner, secondCorner));
	}
	
	public void generateCircle(Point2D.Double canvasP0,
			Point2D.Double canvasP1,
			InteriorStyle.Style interiorStyle,
			Color fillColor,
			double edgeWidth,
			Color edgeColor,
			boolean isVisible,
			int edgeType,HatchType hatchType,
			Point2D.Double center,
			double radius){
		generatorControlElements(canvasP0, canvasP1);
		generatorFillAndEdgeElements(interiorStyle, fillColor, edgeWidth,
							  edgeColor, LineCapIndicator.UNSPECIFIED,
							  DashCapIndicator.UNSPECIFIED, JoinIndicator.
							  UNSPECIFIED, isVisible, edgeType, hatchType);
		cGMObjectConverter.addAndConvert(new CircleElement(center, radius));
	}
	
	public void generateArc3Point(Point2D.Double canvasP0,
										 Point2D.Double canvasP1,
										 double lineWidth,
										 Color lineColor,
										 int lineType,
										 Point2D.Double p1,
										 Point2D.Double p2,
										 Point2D.Double p3){
		generatorControlElements(canvasP0, canvasP1);
		generatorLineElements(lineWidth, lineColor, lineType);
		cGMObjectConverter.addAndConvert(new CircularArc3Point(p1, p2, p3));
	}
	
	public void generateEllipse(Point2D.Double canvasP0,
									   Point2D.Double canvasP1,
									   InteriorStyle.Style interiorStyle,
									   Color fillColor,
									   double edgeWidth,
									   Color edgeColor,
									   boolean isVisible,
									   int edgeType,HatchType hatchType,
									   Point2D.Double center,
									   Point2D.Double firstConjugateDiameterEndPoint,
									   Point2D.Double secondConjugateEndpoint){
		generatorControlElements(canvasP0, canvasP1);
		generatorFillAndEdgeElements(interiorStyle, fillColor, edgeWidth,
				              edgeColor,LineCapIndicator.UNSPECIFIED,DashCapIndicator.UNSPECIFIED, 
				              JoinIndicator.UNSPECIFIED, isVisible, edgeType, hatchType);
		cGMObjectConverter.addAndConvert(new EllipseElement(center, firstConjugateDiameterEndPoint, secondConjugateEndpoint));
		
	}
	
	public void generatePolybezier(Point2D.Double canvasP0,
										  Point2D.Double canvasP1,
										  double lineWidth,
										  Color lineColor,
										  int lineType,
										  CubicCurve2D.Double[] curves){
		generatorControlElements(canvasP0, canvasP1);
		generatorLineElements(lineWidth, lineColor, lineType);
		cGMObjectConverter.addAndConvert(new PolyBezier(curves));
	}
	
	public void generateApplicationData(){
		cGMObjectConverter.generateApplicationData();
	}
	
	public ArrayList<CGMObject> getObjects(){
		return cGMObjectConverter.getObjects();
	}
	
	public void setObjects(ArrayList<CGMObject> objects){
		cGMObjectConverter.setObjects(objects);
	}
	
	public void addAndConvert(Command command){
		cGMObjectConverter.addAndConvert(command);
	}
	
}
