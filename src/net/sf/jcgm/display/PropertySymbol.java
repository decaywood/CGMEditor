package net.sf.jcgm.display;

import java.awt.Dimension;

import javax.swing.JPanel;
import java.awt.FlowLayout;
import javax.swing.border.LineBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import java.awt.Color;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JComboBox;

import net.sf.jcgm.drawer.DrawType;
import net.sf.jcgm.event.EventDispatcher;
import net.sf.jcgm.event.SweepArrowEventParameter;
import net.sf.jcgm.event.SymbolEventParameter;



public class PropertySymbol extends BasePanel{
	private JTextField textFieldSize;
	private int symbolUnits = 0; 
	private double symbolSize;
	private SymbolEventParameter symbolEventParameter;
	
	public PropertySymbol(DrawType drawType){
		super(drawType);
		setBorder(new LineBorder(new Color(0, 0, 0)));
		setPreferredSize(new Dimension(370, 33));
		setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
		symbolEventParameter = new SymbolEventParameter();
		JLabel lblSymbol = new JLabel("Symbol");
		add(lblSymbol);
		
		JLabel lblIconlable = new JLabel("iconLable");
		lblIconlable.setToolTipText("Display All Symbols");
		lblIconlable.addMouseListener(new MouseAdapter() {
			//TODO Icon Panel
		});
		add(lblIconlable);
		
		JLabel lblSize = new JLabel("Size");
		add(lblSize);
		
		textFieldSize = new JTextField();
		textFieldSize.setToolTipText("Size");
		textFieldSize.getDocument().addDocumentListener(new DocumentListener() {
			
			@Override
			public void removeUpdate(DocumentEvent e) {
				if(!textFieldSize.getText().isEmpty()){
					symbolSize = Double.parseDouble(textFieldSize.getText().trim());
					fireEvent();
				}
			}
			
			@Override
			public void insertUpdate(DocumentEvent e) {
				if(!textFieldSize.getText().isEmpty()){
					symbolSize = Double.parseDouble(textFieldSize.getText().trim());
					fireEvent();
				}
			}
			
			@Override
			public void changedUpdate(DocumentEvent e) {
				if(!textFieldSize.getText().isEmpty()){
					symbolSize = Double.parseDouble(textFieldSize.getText().trim());
					fireEvent();
				}
			}
		});
		add(textFieldSize);
		textFieldSize.setColumns(10);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setToolTipText("Unit");
		comboBox.addItem("Points");
		comboBox.addItem("Inches");
		comboBox.addItem("mm");
		comboBox.setSelectedIndex(1);
		comboBox.addItemListener(new ItemListener() {	
			@Override
			public void itemStateChanged(ItemEvent e) {
				if(((String)e.getItem()).equalsIgnoreCase("Points")){
					if(symbolUnits==2){
						symbolSize*= 100; 
						textFieldSize.setText(String.format("%.3f", symbolSize));
					}
					else if(symbolUnits==3){
						symbolSize*= 4.0; 
						textFieldSize.setText(String.format("%.3f", symbolSize));
					}
					symbolUnits = 1;
				}
				else if(((String)e.getItem()).equalsIgnoreCase("Inches")){
					if(symbolUnits==1){
						symbolSize*= 0.01; 
						textFieldSize.setText(String.format("%.3f", symbolSize));
					}
					else if(symbolUnits==3){
						symbolSize*= 0.04; 
						textFieldSize.setText(String.format("%.3f", symbolSize));
					}
					symbolUnits = 2;
				}
				else if(((String)e.getItem()).equalsIgnoreCase("mm")){
					if(symbolUnits==1){
						symbolSize*= 0.25; 
						textFieldSize.setText(String.format("%.3f", symbolSize));
					}
					else if(symbolUnits==2){
						symbolSize*= 25.0; 
						textFieldSize.setText(String.format("%.3f", symbolSize));
					}
					symbolUnits = 3;
				}
				fireEvent();
			}
		});
		add(comboBox);
	}
	@Override
	public void fireEvent() {
		super.fireEvent();
		EventDispatcher eventDispatcher = getEventDispatcher();
		SweepArrowEventParameter sweepArrowEventParameter = new SweepArrowEventParameter();
		symbolEventParameter.setUnit(symbolUnits);
		symbolEventParameter.setSize(symbolSize);
		if(eventDispatcher==null){ return; }
		eventDispatcher.fireEvent(symbolEventParameter);
	}
}
