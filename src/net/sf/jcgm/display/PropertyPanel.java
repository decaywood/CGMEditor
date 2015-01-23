package net.sf.jcgm.display;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;

import javax.swing.JPanel;

import net.sf.jcgm.drawer.DrawType;
import net.sf.jcgm.event.EventDispatcher;



public class PropertyPanel extends JPanel{
	private HashMap<DrawType, BasePanel> propertyMap;
	private BasePanel emptyPanel;
	public PropertyPanel(){
		propertyMap = new HashMap<DrawType, BasePanel>();
		setLayout(new FlowLayout(FlowLayout.LEFT,0,0));
		emptyPanel = new BasePanel(DrawType.DEFAULT);
		emptyPanel.setPreferredSize(new Dimension(1, 33));
		propertyMap.put(DrawType.DEFAULT, emptyPanel);
		propertyMap.put(DrawType.SYMBOL, new PropertySymbol(DrawType.SYMBOL));
		propertyMap.put(DrawType.BEZIER, new PropertyBezier("Bezier",DrawType.BEZIER));
		propertyMap.put(DrawType.TEXT, new PropertyText(DrawType.TEXT));
		propertyMap.put(DrawType.BOXED_TEXT, new PropertyBoxedText(DrawType.BOXED_TEXT));
		propertyMap.put(DrawType.LEADER_LINE, new PropertyLeaderLine(DrawType.LEADER_LINE));
		propertyMap.put(DrawType.LINE, new PropertyLine("Line", DrawType.LINE));
		propertyMap.put(DrawType.POLYLINE, new PropertyLine("PolyLine", DrawType.POLYLINE));
		propertyMap.put(DrawType.ARC, new PropertyBezier("Arc",DrawType.ARC));
		propertyMap.put(DrawType.SWEEP_ARROW, new PropertySweepArrow(DrawType.SWEEP_ARROW));
		propertyMap.put(DrawType.POLYGON, new PropertyPER("Polygon",DrawType.POLYGON));
		propertyMap.put(DrawType.ELLIPSE, new PropertyPER("Ellipse",DrawType.ELLIPSE));
		propertyMap.put(DrawType.RECTANGLE, new PropertyPER("Rectangle",DrawType.RECTANGLE));
		Iterator iterator = (Iterator) propertyMap.entrySet().iterator();
		while(iterator.hasNext()){
			Entry<DrawType, BasePanel> entry = (Entry<DrawType, BasePanel>) iterator.next();
			entry.getValue().setBorder(null);
			add(entry.getValue());
		}
		reset();
	}
	
	private void reset(){
		setVisible(DrawType.DEFAULT);
	}
	
	public void setVisible(DrawType drawType){
		setFalse();
		propertyMap.get(drawType).setVisible(true);
		propertyMap.get(drawType).fireEvent();
	}
	
	private void setFalse(){
		Iterator iterator = (Iterator) propertyMap.entrySet().iterator();
		while(iterator.hasNext()){
			Entry<DrawType, BasePanel> entry = (Entry<DrawType, BasePanel>) iterator.next();
			entry.getValue().setVisible(false);
		}
	}
	
	public void setEventDispatcher(EventDispatcher eventDispatcher){
		Iterator iterator = (Iterator) propertyMap.entrySet().iterator();
		while(iterator.hasNext()){
			Entry<DrawType, BasePanel> entry = (Entry<DrawType, BasePanel>) iterator.next();
			entry.getValue().setEventDispatcher(eventDispatcher);
		}
	}
	
}
