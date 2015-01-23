package net.sf.jcgm.display;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JList;
import javax.swing.JTextField;
import javax.swing.ListCellRenderer;


import net.sf.jcgm.core.DashType;
import net.sf.jcgm.drawer.DrawType;
import net.sf.jcgm.event.CurveEventParameter;
import net.sf.jcgm.event.EventDispatcher;

public class PropertyBezier extends BasePanel{
	private JTextField textFieldwidth;
	private int lineType = DashType.SOLID; //default : solid
	private double widthSize = 0.0; // default :0.0
	private int units=2;// default :inches
	private Color lineColor = Color.BLACK; //default: Black
	private JPanel panelColorChooser;
	
	
	public PropertyBezier(String name,DrawType drawType){
		super(drawType);
		setBorder(new LineBorder(new Color(0, 0, 0)));
		setPreferredSize(new Dimension(633, 33));
		setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
		JLabel lblBezier = new JLabel(name);
		add(lblBezier);
		class AcellRenderer extends JLabel implements ListCellRenderer{
			@Override
			public Component getListCellRendererComponent(JList list, Object value,
					int index, boolean isSelected, boolean cellHasFocus) {
				setIcon((ImageIcon)value);
				return this;
			}	
		}
		final JComboBox comboBoxLineType = new JComboBox();
		comboBoxLineType.setToolTipText("style");
		comboBoxLineType.setRenderer(new AcellRenderer());
		comboBoxLineType.addItem(new ImageIcon(JCGMEditor.class.getResource("/images/line11.gif")));
		comboBoxLineType.addItem(new ImageIcon(JCGMEditor.class.getResource("/images/line2.gif")));
		comboBoxLineType.addItem(new ImageIcon(JCGMEditor.class.getResource("/images/line3.gif")));
		comboBoxLineType.addItem(new ImageIcon(JCGMEditor.class.getResource("/images/line4.gif")));
		comboBoxLineType.addItem(new ImageIcon(JCGMEditor.class.getResource("/images/line5.gif")));
		comboBoxLineType.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				if(comboBoxLineType.getSelectedIndex()==0){
					lineType = DashType.SOLID; // SOLID
					fireEvent();
				}
				else if(comboBoxLineType.getSelectedIndex()==1){
					lineType = DashType.DASH; //DASH
					fireEvent();
				}
				else if(comboBoxLineType.getSelectedIndex()==2){
					lineType = DashType.DOT; //DOT
					fireEvent();
				}
				else if(comboBoxLineType.getSelectedIndex()==3){
					lineType = DashType.DASH_DOT; //DASH_DOT
					fireEvent();
				}
				else if(comboBoxLineType.getSelectedIndex()==4){
					lineType = DashType.DASH_DOT_DOT; //DASH_DOT_DOT
					fireEvent();
				}
			}
		 
		});
		add(comboBoxLineType);
		
		JLabel lblWidth = new JLabel("Width");
		add(lblWidth);
		
		textFieldwidth = new JTextField("0.0");
		textFieldwidth.setToolTipText("Size");
		textFieldwidth.getDocument().addDocumentListener(new DocumentListener() {
			
			@Override
			public void removeUpdate(DocumentEvent e) {
				if(!textFieldwidth.getText().isEmpty()){
					widthSize = Double.parseDouble(textFieldwidth.getText().trim());
					fireEvent();
				}
			}
			
			@Override
			public void insertUpdate(DocumentEvent e) {
				if(!textFieldwidth.getText().isEmpty()){
					widthSize = Double.parseDouble(textFieldwidth.getText().trim());
					fireEvent();
				}
			}
			
			@Override
			public void changedUpdate(DocumentEvent e) {
				if(!textFieldwidth.getText().isEmpty()){
					widthSize = Double.parseDouble(textFieldwidth.getText().trim());
					fireEvent();
				}
			}
		});
		add(textFieldwidth);
		textFieldwidth.setColumns(10);
		
		JComboBox comboBoxUnits = new JComboBox();
		comboBoxUnits.setToolTipText("Unit");
		comboBoxUnits.addItem("Points");
		comboBoxUnits.addItem("Inches");
		comboBoxUnits.addItem("mm");
		comboBoxUnits.setSelectedIndex(1);
		comboBoxUnits.addItemListener(new ItemListener() {	
			@Override
			public void itemStateChanged(ItemEvent e) {
				if(((String)e.getItem()).equalsIgnoreCase("Points")){
					if(units==2){
						widthSize*= 100; 
						textFieldwidth.setText(String.format("%.3f", widthSize));
					}
					else if(units==3){
						widthSize*= 4; 
						textFieldwidth.setText(String.format("%.3f", widthSize));
					}
					units = 1;
				}
				else if(((String)e.getItem()).equalsIgnoreCase("Inches")){
					if(units==1){
						widthSize*= 0.01; 
						textFieldwidth.setText(String.format("%.3f", widthSize));
					}
					else if(units==3){
						widthSize*= 0.04; 
						textFieldwidth.setText(String.format("%.3f", widthSize));
					}
					units = 2;
				}
				else if(((String)e.getItem()).equalsIgnoreCase("mm")){
					if(units==1){
						widthSize*= 0.25; 
						textFieldwidth.setText(String.format("%.3f", widthSize));
					}
					else if(units==2){
						widthSize*= 25.0; 
						textFieldwidth.setText(String.format("%.3f", widthSize));
					}
					units = 3;
				}
				fireEvent();
			}
		});
		add(comboBoxUnits);
		
		JLabel lblColor = new JLabel(new ImageIcon(JCGMEditor.class.getResource("/images/write.gif")));
		add(lblColor);
		
		panelColorChooser = new JPanel();
		panelColorChooser.setToolTipText("line color");
		panelColorChooser.setPreferredSize(new Dimension(20,20));
		panelColorChooser.addMouseListener(new MouseAdapter() {
			public void mousePressed(java.awt.event.MouseEvent e) {
				if(e.getButton()==MouseEvent.BUTTON1){
					new ColorChooserPanel(panelColorChooser.getX()+e.getX(), panelColorChooser.getY()+e.getY());
				}
			};
		});
		panelColorChooser.setBackground(Color.BLACK);
		add(panelColorChooser);
	}
	 private class ColorChooserPanel extends JFrame{
			
			private JColorChooser jColorChooser;
			private JButton buttonOk;
			private JButton buttonCancel;
			private Object object;
			public ColorChooserPanel(int x,int y) {
				setLocation(x,y);
				setSize(500, 400);
				jColorChooser=new JColorChooser();
				add(jColorChooser,BorderLayout.CENTER);
				JPanel buttonPanel = new JPanel();
				buttonOk = new JButton("OK");
				buttonOk.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						 lineColor = jColorChooser.getColor();
						 panelColorChooser.setBackground(jColorChooser.getColor());
						 fireEvent();
							ColorChooserPanel.this.dispose();		
					}
				});
				buttonPanel.add(buttonOk);	
				buttonCancel = new JButton("Cancel");
				buttonCancel.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						ColorChooserPanel.this.dispose();
					}
				});
				buttonCancel.setBounds(216, 253, 76, 23);
				buttonPanel.add(buttonCancel);
				add(buttonPanel,BorderLayout.SOUTH);
				setVisible(true);
			}
		}
	 @Override
	public void fireEvent() {
		super.fireEvent();
		 EventDispatcher eventDispatcher = getEventDispatcher();
		 CurveEventParameter curveEventParameter = new CurveEventParameter(getDrawType());
		 curveEventParameter.setLineType(lineType);
		 curveEventParameter.setLineColor(lineColor);
		 curveEventParameter.setLineWidth(widthSize);
		 if(eventDispatcher==null){ return; }
		 eventDispatcher.fireEvent(curveEventParameter);
	}
}
