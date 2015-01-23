// Copyright 2001-2003 FreeHEP.
package net.sf.jcgm.core;

import org.freehep.util.io.TagSet;

/**
 * CGM specific tagset.
 * 
 * @author Mark Donszelmann
 * @author Charles Loomis
 * @version $Id: CGMTagSet.java 8584 2006-08-10 23:06:37Z duns $
 */
public class CGMTagSet extends TagSet {

    public CGMTagSet(int version) {
        // FIXME register all tags here
        if (version >= 1) {
            addTag(new BeginMetafile());
            addTag(new MetafileVersion());
            addTag(new MetafileDefaultsReplacement());
            addTag(new MetafileDescription());
            addTag(new MetafileElementList());
            addTag(new BeginPicture());
            addTag(new BeginPictureBody());
            addTag(new EndPicture());
            addTag(new EndMetafile());

            addTag(new AlternateCharacterSetIndex());
//            addTag(new AppendText());
            addTag(new ApplicationData());
//            addTag(new AspectSourceFlags());
//            addTag(new AuxiliaryColour());
            addTag(new BackgroundColour());
            addTag(new CellArray());
            addTag(new CharacterCodingAnnouncer());
            addTag(new CharacterExpansionFactor());
            addTag(new CharacterHeight());
            addTag(new CharacterOrientation());
            addTag(new CharacterSetIndex());
            addTag(new CharacterSetList());
            addTag(new CharacterSpacing());
            addTag(new CircleElement());
            addTag(new CircularArc3Point());
            addTag(new CircularArc3PointClose());
            addTag(new CircularArcCentre());
            addTag(new CircularArcCentreClose());
            addTag(new ClipIndicator());
            addTag(new ClipRectangle());
            addTag(new ColourIndexPrecision());
            addTag(new ColourPrecision());
            addTag(new ColourSelectionMode());
            addTag(new ColourTable());
            addTag(new ColourValueExtent());
            addTag(new DisjointPolyline());
//            addTag(new EdgeBundleIndex());
            addTag(new EdgeColour());
            addTag(new EdgeType());
            addTag(new EdgeVisibility());
            addTag(new EdgeWidth());
            addTag(new EdgeWidthSpecificationMode());
            addTag(new EllipseElement());
            addTag(new EllipticalArc());
            addTag(new EllipticalArcClose());
            addTag(new Escape());
//            addTag(new FillBundleIndex());
            addTag(new FillColour());
//            addTag(new FillReferencePoint());
            addTag(new FontList());
//            addTag(new GeneralizedDrawingPrimitive());
            addTag(new HatchIndex());
            addTag(new IndexPrecision());
            addTag(new IntegerPrecision());
            addTag(new InteriorStyle());
//            addTag(new LineBundleIndex());
            addTag(new LineColour());
            addTag(new LineType());
            addTag(new LineWidth());
            addTag(new LineWidthSpecificationMode());
//            addTag(new MarkerBundleIndex());
            addTag(new MarkerColour());
            addTag(new MarkerSize());
            addTag(new MarkerSizeSpecificationMode());
            addTag(new MarkerType());
            addTag(new MaximumColourIndex());
            addTag(new MessageCommand());
            addTag(new NoOp());
//            addTag(new PatternIndex());
//            addTag(new PatternSize());
//            addTag(new PatternTable());
            addTag(new PolygonElement());
            addTag(new PolygonSet());
            addTag(new Polyline());
            addTag(new PolyMarker());
            addTag(new RealPrecision());
            addTag(new RectangleElement());
            addTag(new RestrictedText());
            addTag(new ScalingMode());
            addTag(new Text());
            addTag(new TextAlignment());
//            addTag(new TextBundleIndex());
            addTag(new TextColour());
            addTag(new TextFontIndex());
            addTag(new TextPath());
            addTag(new TextPrecision());
//            addTag(new Transparency());
            addTag(new VDCExtent());
            addTag(new VDCIntegerPrecision());
            addTag(new VDCType());
            addTag(new VDCRealPrecision());
        }

        if (version >= 2) {
//            addTag(new BeginFigure());
//            addTag(new BeginSegment());
//            addTag(new EndFigure());
//            addTag(new EndSegment());

//            addTag(new ConnectingEdge());
            addTag(new MaximumVDCExtent());
            addTag(new NamePrecision());
//            addTag(new SegmentPriorityExtent());
        }

        if (version >= 3) {
            addTag(new EdgeCap());
            addTag(new EdgeJoin());
            addTag(new InteriorStyleSpecificationMode());
            addTag(new LineCap());
            addTag(new LineJoin());
//            addTag(new MitreLimit());
//            addTag(new TransparentCellColour());
        }
    }
}
