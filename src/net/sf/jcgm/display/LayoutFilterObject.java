package net.sf.jcgm.display;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JFrame;
import javax.swing.JCheckBox;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.border.TitledBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.JToggleButton;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JTextField;

import net.sf.jcgm.core.CellArray;
import net.sf.jcgm.core.CircleElement;
import net.sf.jcgm.core.CircularArc3Point;
import net.sf.jcgm.core.CircularArc3PointClose;
import net.sf.jcgm.core.CircularArcCentre;
import net.sf.jcgm.core.CircularArcCentreClose;
import net.sf.jcgm.core.DisjointPolyline;
import net.sf.jcgm.core.EllipseElement;
import net.sf.jcgm.core.EllipticalArc;
import net.sf.jcgm.core.EllipticalArcClose;
import net.sf.jcgm.core.PolyBezier;
import net.sf.jcgm.core.PolyMarker;
import net.sf.jcgm.core.PolygonElement;
import net.sf.jcgm.core.PolygonSet;
import net.sf.jcgm.core.Polyline;
import net.sf.jcgm.core.RectangleElement;
import net.sf.jcgm.core.RestrictedText;
import net.sf.jcgm.core.Tile;
import net.sf.jcgm.properties.LayoutFilterObjecProp;



public class LayoutFilterObject extends JFrame {

	 private Map<Class<?>, Boolean> filterMap;
	 private JButton btnClearAll;
	 private JButton btnSetAll ;
	 private JPanel panel;
	 private JTextField textFieldStart;
	 private JTextField textFieldEnd;
	 private JCheckBox chckbxCellArray;
	 private JCheckBox chckbxCircle;
	 private JCheckBox	chckbxCircularArcpoint;
	 private JCheckBox chckbxCircularArcpointClose;
	 private JCheckBox chckbxCircularArcCenter;
	 private JCheckBox chckbxCircularArcCenterClose;
	 private JCheckBox chckbxClosedFigure;
	 private JCheckBox chckbxEllipse;
	 private JCheckBox chckbxEllipticalArc ;
	 private JCheckBox chckbxEllipticalArcClose;
	 private JCheckBox chckbxPolybezier;
	 private JCheckBox chckbxPolygon;
	 private JCheckBox chckbxPolygonSet;
	 private JCheckBox chckbxPolyline;
	 private JCheckBox chckbxPolylineDisjoint;
	 private JCheckBox chckbxPolymarker;
	 private JCheckBox chckbxRectangle;
	 private JCheckBox chckbxSeismicGdp;
	 private JCheckBox chckbxSpline;
	 private JCheckBox chckbxText;
	 private JCheckBox chckbxTile ;
	 private JLabel lblStart;
	 private JLabel lblEnd;
	 private JButton btnClose;
	 private LayoutFilterObjecProp layoutFilterObjectProp;
	 private Boolean filterIschckbxCellArray;
	 private Boolean filterIschckbxCircle;
	 private Boolean filterIschckbxCircularArcpoint;
	 private Boolean filterIschckbxCircularArcpointClose;
	 private Boolean filterIschckbxCircularArcCenter;
	 private Boolean filterIschckbxCircularArcCenterClose;
	 private Boolean filterIschckbxClosedFigure;
	 private Boolean filterIschckbxEllipse;
	 private Boolean filterIschckbxEllipticalArc ;
	 private Boolean filterIschckbxEllipticalArcClose;
	 private Boolean filterIschckbxPolybezier;
	 private Boolean filterIschckbxPolygon;
	 private Boolean filterIschckbxPolygonSet;
	 private Boolean filterIschckbxPolyline;
	 private Boolean filterIschckbxPolylineDisjoint;
	 private Boolean filterIschckbxPolymarker;
	 private Boolean filterIschckbxRectangle;
	 private Boolean filterIschckbxSeismicGdp;
	 private Boolean filterIschckbxSpline;
	 private Boolean filterIschckbxText;
	 private Boolean filterIschckbxTile ;
	 private String filterStart;
	 private String filterEnd;

	 public LayoutFilterObject(int x,int y) {
		 this.setBounds(x,y,230, 750);
		 
		 filterMap = new HashMap<Class<?>, Boolean>();
		 filterMap.put(CellArray.class, filterIschckbxCellArray);
		 filterMap.put(CircleElement.class, filterIschckbxCircle);
		 filterMap.put(CircularArc3Point.class, filterIschckbxCircularArcpoint);
		 filterMap.put(CircularArc3PointClose.class, filterIschckbxCircularArcpointClose);
		 filterMap.put(CircularArcCentre.class, filterIschckbxCircularArcCenter);
		 filterMap.put(CircularArcCentreClose.class, filterIschckbxCircularArcCenterClose);
//		 filterMap.put(.class, filterIschckbxClosedFigure);
		 filterMap.put(EllipseElement.class, filterIschckbxEllipse);
		 filterMap.put(EllipticalArc.class, filterIschckbxEllipticalArc );
		 filterMap.put(EllipticalArcClose.class, filterIschckbxEllipticalArcClose);
		 filterMap.put(PolyBezier.class, filterIschckbxPolybezier);
		 filterMap.put(PolygonElement.class, filterIschckbxPolygon);
		 filterMap.put(PolygonSet.class, filterIschckbxPolygonSet);
		 filterMap.put(Polyline.class, filterIschckbxPolyline);
		 filterMap.put(DisjointPolyline.class, filterIschckbxPolylineDisjoint);
		 filterMap.put(PolyMarker.class, filterIschckbxPolymarker);
		 filterMap.put(RectangleElement.class, filterIschckbxRectangle);
//		 filterMap.put(.class, filterIschckbxSeismicGdp);
//		 filterMap.put(.class, filterIschckbxSpline);
		 filterMap.put(RestrictedText.class, filterIschckbxText);
		 filterMap.put(Tile.class, filterIschckbxTile );
		 
		 
		 getContentPane().setLayout(null);
		 btnClearAll = new JButton("Clear All");
		 btnClearAll.addActionListener(new ActionListener() {
		 	@Override
		 	public void actionPerformed(ActionEvent e) {
		 		 chckbxCellArray.setSelected(false);
		 		 chckbxCircle.setSelected(false);
		 		 chckbxCircularArcpoint.setSelected(false);
		 		 chckbxCircularArcpointClose.setSelected(false);
		 		 chckbxCircularArcCenter.setSelected(false);
		 		 chckbxCircularArcCenterClose.setSelected(false);
		 		 chckbxClosedFigure.setSelected(false);
		 		 chckbxEllipse.setSelected(false);
		 		 chckbxEllipticalArc .setSelected(false);
		 		 chckbxEllipticalArcClose.setSelected(false);
		 		 chckbxPolybezier.setSelected(false);
		 		 chckbxPolygon.setSelected(false);
		 		 chckbxPolygonSet.setSelected(false);
		 		 chckbxPolyline.setSelected(false);
		 		 chckbxPolylineDisjoint.setSelected(false);
		 		 chckbxPolymarker.setSelected(false);
		 		 chckbxRectangle.setSelected(false);
		 		 chckbxSeismicGdp.setSelected(false);
		 		 chckbxSpline.setSelected(false);
		 		 chckbxText.setSelected(false);
		 		 chckbxTile .setSelected(false);
		 	}
		 });
		 btnClearAll.setBounds(10, 10, 87, 23);
		 getContentPane().add(btnClearAll);

		 btnSetAll = new JButton("Set All");
		 btnSetAll.addActionListener(new ActionListener() {
		 	@Override
		 	public void actionPerformed(ActionEvent e) {
		 		 chckbxCellArray.setSelected(true);
		 		 chckbxCircle.setSelected(true);
		 		 chckbxCircularArcpoint.setSelected(true);
		 		 chckbxCircularArcpointClose.setSelected(true);
		 		 chckbxCircularArcCenter.setSelected(true);
		 		 chckbxCircularArcCenterClose.setSelected(true);
		 		 chckbxClosedFigure.setSelected(true);
		 		 chckbxEllipse.setSelected(true);
		 		 chckbxEllipticalArc .setSelected(true);
		 		 chckbxEllipticalArcClose.setSelected(true);
		 		 chckbxPolybezier.setSelected(true);
		 		 chckbxPolygon.setSelected(true);
		 		 chckbxPolygonSet.setSelected(true);
		 		 chckbxPolyline.setSelected(true);
		 		 chckbxPolylineDisjoint.setSelected(true);
		 		 chckbxPolymarker.setSelected(true);
		 		 chckbxRectangle.setSelected(true);
		 		 chckbxSeismicGdp.setSelected(true);
		 		 chckbxSpline.setSelected(true);
		 		 chckbxText.setSelected(true);
		 		 chckbxTile .setSelected(true);
		 	}
		 });
		 btnSetAll.setBounds(107, 10, 93, 23);
		 getContentPane().add(btnSetAll);

		 chckbxCellArray = new JCheckBox("Cell Array");
		 chckbxCellArray.addItemListener(new ItemListener() {
		 	@Override
		 	public void itemStateChanged(ItemEvent e) {
		 		filterIschckbxCellArray = chckbxCellArray.isSelected();
		 	}
		 });
		 chckbxCellArray.setBounds(10, 39, 103, 23);
		 getContentPane().add(chckbxCellArray);

		 chckbxCircle = new JCheckBox("Circle");
		 chckbxCircle.addItemListener(new ItemListener() {
		 		@Override
		 		public void itemStateChanged(ItemEvent e) {
		 			filterIschckbxCircle = chckbxCircle.isSelected();
		 		}
		 	});
		 chckbxCircle.setBounds(10, 64, 103, 23);
		 getContentPane().add(chckbxCircle);

		 chckbxCircularArcpoint = new JCheckBox("Circular Arc 3Point");
		 chckbxCircularArcpoint.addItemListener(new ItemListener() {
		 		@Override
		 		public void itemStateChanged(ItemEvent e) {
		 			filterIschckbxCircularArcpoint = chckbxCircularArcpoint.isSelected();
		 		}
		 	});
		 chckbxCircularArcpoint.setBounds(10, 89, 148, 23);
		 getContentPane().add(chckbxCircularArcpoint);

		 chckbxCircularArcpointClose = new JCheckBox("Circular Arc 3Point Closed");
		 chckbxCircularArcpointClose.addItemListener(new ItemListener() {
		 		@Override
		 		public void itemStateChanged(ItemEvent e) {
		 			filterIschckbxCircularArcpointClose = chckbxCircularArcpointClose.isSelected();
		 		}
		 	});
		 chckbxCircularArcpointClose.setBounds(10, 114, 190, 23);
		 getContentPane().add(chckbxCircularArcpointClose);

		 chckbxCircularArcCenter = new JCheckBox("Circular Arc Center");
		 chckbxCircularArcCenter.addItemListener(new ItemListener() {
		 		@Override
		 		public void itemStateChanged(ItemEvent e) {
		 			filterIschckbxCircularArcCenter = chckbxCircularArcCenter.isSelected();
		 		}
		 	});
		 chckbxCircularArcCenter.setBounds(10, 139, 180, 23);
		 getContentPane().add(chckbxCircularArcCenter);

		 chckbxCircularArcCenterClose = new JCheckBox("Circular Arc Center Close");
		 chckbxCircularArcCenterClose.addItemListener(new ItemListener() {
		 		@Override
		 		public void itemStateChanged(ItemEvent e) {
		 			filterIschckbxCircularArcCenterClose = chckbxCircularArcCenterClose.isSelected();
		 		}
		 	});
		 chckbxCircularArcCenterClose.setBounds(10, 164, 190, 23);
		 getContentPane().add(chckbxCircularArcCenterClose);

		 chckbxClosedFigure = new JCheckBox("Closed Figure");
		 chckbxClosedFigure.addItemListener(new ItemListener() {
		 		@Override
		 		public void itemStateChanged(ItemEvent e) {
		 			filterIschckbxClosedFigure = chckbxClosedFigure.isSelected();
		 		}
		 	});
		 chckbxClosedFigure.setBounds(10, 189, 103, 23);
		 getContentPane().add(chckbxClosedFigure);

		 chckbxEllipse = new JCheckBox("Ellipse");
		 chckbxEllipse.addItemListener(new ItemListener() {
		 		@Override
		 		public void itemStateChanged(ItemEvent e) {
		 			 filterIschckbxEllipse = chckbxEllipse.isSelected();
		 		}
		 	});
		 chckbxEllipse.setBounds(10, 214, 103, 23);
		 getContentPane().add(chckbxEllipse);

		 chckbxEllipticalArc = new JCheckBox("Elliptical Arc");
		 chckbxEllipticalArc.addItemListener(new ItemListener() {
		 		@Override
		 		public void itemStateChanged(ItemEvent e) {
		 			filterIschckbxEllipticalArc = chckbxEllipticalArc.isSelected();
		 		}
		 	});
		 chckbxEllipticalArc.setBounds(10, 239, 180, 23);
		 getContentPane().add(chckbxEllipticalArc);

		 chckbxEllipticalArcClose = new JCheckBox("Elliptical Arc Close");
		 chckbxEllipticalArcClose.addItemListener(new ItemListener() {
		 		@Override
		 		public void itemStateChanged(ItemEvent e) {
		 			filterIschckbxEllipticalArcClose = chckbxEllipticalArcClose.isSelected();
		 		}
		 	});
		 chckbxEllipticalArcClose.setBounds(10, 264, 180, 23);
		 getContentPane().add(chckbxEllipticalArcClose);

		 chckbxPolybezier = new JCheckBox("PolyBezier");
		 chckbxPolybezier.addItemListener(new ItemListener() {
		 		@Override
		 		public void itemStateChanged(ItemEvent e) {
		 			filterIschckbxPolybezier = chckbxPolybezier.isSelected();
		 		}
		 	});
		 chckbxPolybezier.setBounds(10, 289, 103, 23);
		 getContentPane().add(chckbxPolybezier);

		 chckbxPolygon = new JCheckBox("Polygon");
		 chckbxPolygon.addItemListener(new ItemListener() {
		 		@Override
		 		public void itemStateChanged(ItemEvent e) {
		 			filterIschckbxPolygon  = chckbxPolygon.isSelected();
		 		}
		 	});
		 chckbxPolygon.setBounds(10, 314, 103, 23);
		 getContentPane().add(chckbxPolygon);

		 chckbxPolygonSet = new JCheckBox("Polygon Set");
		 chckbxPolygonSet.addItemListener(new ItemListener() {
		 		@Override
		 		public void itemStateChanged(ItemEvent e) {
		 			filterIschckbxPolygonSet = chckbxPolygonSet.isSelected();
		 		}
		 	});
		 chckbxPolygonSet.setBounds(10, 339, 103, 23);
		 getContentPane().add(chckbxPolygonSet);

		 chckbxPolyline = new JCheckBox("Polyline");
		 chckbxPolyline.addItemListener(new ItemListener() {
		 		@Override
		 		public void itemStateChanged(ItemEvent e) {
		 			filterIschckbxPolyline = chckbxPolyline.isSelected();
		 		}
		 	});
		 chckbxPolyline.setBounds(10, 364, 103, 23);
		 getContentPane().add(chckbxPolyline);

		 chckbxPolylineDisjoint = new JCheckBox("Polyline - Disjoint");
		 chckbxPolylineDisjoint.addItemListener(new ItemListener() {
		 		@Override
		 		public void itemStateChanged(ItemEvent e) {
		 			filterIschckbxPolylineDisjoint = chckbxPolylineDisjoint.isSelected();
		 		}
		 	});
		 chckbxPolylineDisjoint.setBounds(10, 389, 162, 23);
		 getContentPane().add(chckbxPolylineDisjoint);

		 chckbxPolymarker = new JCheckBox("Polymarker");
		 chckbxPolymarker.addItemListener(new ItemListener() {
		 		@Override
		 		public void itemStateChanged(ItemEvent e) {
		 			filterIschckbxPolymarker = chckbxPolymarker.isSelected();
		 		}
		 	});
		 chckbxPolymarker.setBounds(10, 414, 103, 23);
		 getContentPane().add(chckbxPolymarker);

		 chckbxRectangle = new JCheckBox("Rectangle");
		 chckbxRectangle.addItemListener(new ItemListener() {
		 		@Override
		 		public void itemStateChanged(ItemEvent e) {
		 			 filterIschckbxRectangle = chckbxRectangle.isSelected();
		 		}
		 	});
		 chckbxRectangle.setBounds(10, 439, 103, 23);
		 getContentPane().add(chckbxRectangle);

		 chckbxSeismicGdp = new JCheckBox("Seismic GDP");
		 chckbxSeismicGdp.addItemListener(new ItemListener() {
		 		@Override
		 		public void itemStateChanged(ItemEvent e) {
		 			filterIschckbxSeismicGdp = chckbxSeismicGdp.isSelected();
		 		}
		 	});
		 chckbxSeismicGdp.setBounds(10, 464, 103, 23);
		 getContentPane().add(chckbxSeismicGdp);

		 chckbxSpline = new JCheckBox("Spline");
		 chckbxSpline.addItemListener(new ItemListener() {
		 		@Override
		 		public void itemStateChanged(ItemEvent e) {
		 			filterIschckbxSpline = chckbxSpline.isSelected();
		 		}
		 	});
		 chckbxSpline.setBounds(10, 489, 103, 23);
		 getContentPane().add(chckbxSpline);

		 chckbxText = new JCheckBox("Text");
		 chckbxText.addItemListener(new ItemListener() {
		 		@Override
		 		public void itemStateChanged(ItemEvent e) {
		 			filterIschckbxText = chckbxText.isSelected();
		 		}
		 	});
		 chckbxText.setBounds(10, 514, 103, 23);
		 getContentPane().add(chckbxText);

		 chckbxTile = new JCheckBox("Tile");
		 chckbxTile.addItemListener(new ItemListener() {
		 		@Override
		 		public void itemStateChanged(ItemEvent e) {
		 			filterIschckbxTile = chckbxTile.isSelected();
		 		}
		 	});
		 chckbxTile.setBounds(10, 539, 103, 23);
		 getContentPane().add(chckbxTile);

		 panel = new JPanel();
		 panel.setBorder(new TitledBorder(new LineBorder(new Color(192, 192, 192)), "Byte-Offset Range", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		 panel.setBounds(10, 568, 190, 92);
		 getContentPane().add(panel);
		 panel.setLayout(null);

		 lblStart = new JLabel("Start");
		 lblStart.setBounds(10, 25, 57, 21);
		 panel.add(lblStart);

		 lblEnd = new JLabel("End");
		 lblEnd.setBounds(10, 56, 57, 21);
		 panel.add(lblEnd);

		 textFieldStart = new JTextField();
		 textFieldStart.getDocument().addDocumentListener(new DocumentListener() {
		 	
		 	@Override
		 	public void removeUpdate(DocumentEvent e) {
		 		filterStart = textFieldStart.getText();
		 	}
		 	
		 	@Override
		 	public void insertUpdate(DocumentEvent e) {
		 		filterStart = textFieldStart.getText();
		 	}
		 	
		 	@Override
		 	public void changedUpdate(DocumentEvent e) {
		 		filterStart = textFieldStart.getText();
		 	}
		 });

		 textFieldStart.setBounds(46, 25, 106, 21);
		 panel.add(textFieldStart);
		 textFieldStart.setColumns(10);

		 textFieldEnd = new JTextField();
		 textFieldEnd.getDocument().addDocumentListener(new DocumentListener() {
		 	
		 	@Override
		 	public void removeUpdate(DocumentEvent e) {
		 		if(!textFieldEnd.getText().isEmpty()){
		 			filterEnd = textFieldEnd.getText().trim();  
		 		}
		 	}
		 	
		 	@Override
		 	public void insertUpdate(DocumentEvent e) {
		 		if(!textFieldEnd.getText().isEmpty()){
		 			filterEnd = textFieldEnd.getText().trim();  
		 		}
		 	}
		 	
		 	@Override
		 	public void changedUpdate(DocumentEvent e) {
		 		if(!textFieldEnd.getText().isEmpty()){
		 			filterEnd = textFieldEnd.getText().trim();  
		 		}
		 	}
		 });
		 textFieldEnd.setText("");
		 textFieldEnd.setBounds(46, 56, 106, 21);
		 panel.add(textFieldEnd);
		 textFieldEnd.setColumns(10);

		 btnClose = new JButton("Close");
		 btnClose.addActionListener(new ActionListener() {
		 	@Override
		 	public void actionPerformed(ActionEvent e) {
		 		LayoutFilterObject.this.dispose();
		 	}
		 });
		 btnClose.setBounds(65, 670, 93, 23);
		 btnClose.addActionListener(new ActionListener() {
		 	@Override
		 	public void actionPerformed(ActionEvent e) {
		 		layoutFilterObjectProp = new LayoutFilterObjecProp();
		 		layoutFilterObjectProp.setFilterEnd(filterEnd);
		 		layoutFilterObjectProp.setFilterIschckbxCellArray(filterIschckbxCellArray);
		 		layoutFilterObjectProp.setFilterIschckbxCircle(filterIschckbxCircle);
		 		layoutFilterObjectProp.setFilterIschckbxCircularArcCenter(filterIschckbxCircularArcCenter);
		 		layoutFilterObjectProp.setFilterIschckbxCircularArcCenterClose(filterIschckbxCircularArcCenterClose);
		 		layoutFilterObjectProp.setFilterIschckbxCircularArcCenterClose(filterIschckbxCircularArcCenterClose);
		 		layoutFilterObjectProp.setFilterIschckbxCircularArcpointClose(filterIschckbxCircularArcpointClose);
		 		layoutFilterObjectProp.setFilterIschckbxClosedFigure(filterIschckbxClosedFigure);
		 		layoutFilterObjectProp.setFilterIschckbxEllipse(filterIschckbxEllipse);
		 		layoutFilterObjectProp.setFilterIschckbxEllipticalArc(filterIschckbxEllipticalArc);
		 		layoutFilterObjectProp.setFilterIschckbxEllipticalArcClose(filterIschckbxEllipticalArcClose);
		 		layoutFilterObjectProp.setFilterIschckbxPolybezier(filterIschckbxPolybezier);
		 		layoutFilterObjectProp.setFilterIschckbxPolygon(filterIschckbxPolygon);
		 		layoutFilterObjectProp.setFilterIschckbxPolygonSet(filterIschckbxPolygonSet);
		 		layoutFilterObjectProp.setFilterIschckbxPolyline(filterIschckbxPolyline);
		 		layoutFilterObjectProp.setFilterIschckbxPolylineDisjoint(filterIschckbxPolylineDisjoint);
		 		layoutFilterObjectProp.setFilterIschckbxPolymarker(filterIschckbxPolymarker);
		 		layoutFilterObjectProp.setFilterIschckbxRectangle(filterIschckbxRectangle);
		 		layoutFilterObjectProp.setFilterIschckbxSeismicGdp(filterIschckbxSeismicGdp);
		 		layoutFilterObjectProp.setFilterIschckbxSpline(filterIschckbxSpline);
		 		layoutFilterObjectProp.setFilterIschckbxText(filterIschckbxText);
		 		layoutFilterObjectProp.setFilterIschckbxTile(filterIschckbxTile);
		 		LayoutFilterObject.this.dispose();
		 	}
		 });
		 getContentPane().add(btnClose);
		 setVisible(true);

		 }
	 
	 public Map<Class<?>, Boolean> getFilterMap() {
		return filterMap;
	}

} 