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
import javax.swing.JCheckBox;


import net.sf.jcgm.core.DashType;
import net.sf.jcgm.drawer.DrawType;
import net.sf.jcgm.event.EventDispatcher;
import net.sf.jcgm.event.LineEventParameter;

public class PropertyLine extends BasePanel{

	
	private JComboBox comboBoxRightEndStyle;
	private JComboBox comboBoxLeftEndStyle;
	private JTextField textFieldWidth;
	private int leftStyle = 1;
	private int rightStyle = 1;
	private int lineType = 1; //default : solid
	private double widthSize = 0.0; // default :0.0
	private int lineUnits = 2;// default :inches
	private Color lineColor = Color.BLACK; //default: Black
	private double arrowSize = 0.2;
	private double headRiatio = 2.5;
	private boolean isHalo;
	private JLabel lblArrow;
	private JLabel lblHeadRatio;
	private JPanel panelColorChooser;
	private JTextField textFieldArrowSize;
	private JTextField textFieldHeadRatio;
	private final JCheckBox chckbxHalo = new JCheckBox("halo");
 
	public PropertyLine(String string, DrawType drawType){
		super(drawType);
		setBorder(new LineBorder(new Color(0, 0, 0)));
		setPreferredSize(new Dimension(899, 33));
		setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
		
		JLabel lblLine = new JLabel(string);
		add(lblLine);
		
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
					lineType = DashType.SOLID;
				}
				else if(comboBoxLineType.getSelectedIndex()==1){
					lineType = DashType.DASH;
				}
				else if(comboBoxLineType.getSelectedIndex()==2){
					lineType = DashType.DOT;
				}
				else if(comboBoxLineType.getSelectedIndex()==3){
					lineType = DashType.DASH_DOT;
				}
				else if(comboBoxLineType.getSelectedIndex()==4){
					lineType = DashType.DASH_DOT_DOT;
				}
				fireEvent();
			}
		});
		
		comboBoxLeftEndStyle = new JComboBox();
		comboBoxLeftEndStyle.setToolTipText("Left end Style");
		comboBoxLeftEndStyle.addItem(new ImageIcon(JCGMEditor.class.getResource("/images/sline.gif")));
		comboBoxLeftEndStyle.addItem(new ImageIcon(JCGMEditor.class.getResource("/images/leftArrow.gif")));
		comboBoxLeftEndStyle.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				if(comboBoxLeftEndStyle.getSelectedIndex()==0){
					leftStyle = 1;
					if(comboBoxRightEndStyle.getSelectedIndex()==0){
						chckbxHalo.setVisible(false);
						lblArrow.setVisible(false);
						lblHeadRatio.setVisible(false);
						textFieldArrowSize.setVisible(false);
						textFieldHeadRatio.setVisible(false);
					}
				}
				else if(comboBoxLeftEndStyle.getSelectedIndex()==1){
					chckbxHalo.setVisible(true);
					lblArrow.setVisible(true);
					lblHeadRatio.setVisible(true);
					textFieldArrowSize.setVisible(true);
					textFieldHeadRatio.setVisible(true);
					leftStyle = 2;
				}
				fireEvent();
			}
		});
		comboBoxLeftEndStyle.setRenderer(new AcellRenderer());
		add(comboBoxLeftEndStyle);
		add(comboBoxLineType);
		
		comboBoxRightEndStyle = new JComboBox();
		comboBoxRightEndStyle.setToolTipText("Right End Style");
		comboBoxRightEndStyle.setPreferredSize(comboBoxLeftEndStyle.getPreferredSize());
		comboBoxRightEndStyle.addItem(new ImageIcon(JCGMEditor.class.getResource("/images/sline.gif")));
		comboBoxRightEndStyle.addItem(new ImageIcon(JCGMEditor.class.getResource("/images/rightArrow.gif")));
		comboBoxRightEndStyle.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				if(comboBoxRightEndStyle.getSelectedIndex()==0){
					rightStyle = 1;
					if(comboBoxLeftEndStyle.getSelectedIndex()==0){
						chckbxHalo.setVisible(false);
						lblArrow.setVisible(false);
						lblHeadRatio.setVisible(false);
						textFieldArrowSize.setVisible(false);
						textFieldHeadRatio.setVisible(false);
					}
				}
				else if(comboBoxRightEndStyle.getSelectedIndex()==1){
					chckbxHalo.setVisible(true);
					lblArrow.setVisible(true);
					lblHeadRatio.setVisible(true);
					textFieldArrowSize.setVisible(true);
					textFieldHeadRatio.setVisible(true);
					rightStyle = 2;
				}
				fireEvent();
			}
		});
		add(comboBoxRightEndStyle);
		
		JLabel lblWidth = new JLabel("Width");
		add(lblWidth);
		
		textFieldWidth = new JTextField();
		textFieldWidth.setText("0.0");
		textFieldWidth.getDocument().addDocumentListener(new DocumentListener() {
			
			@Override
			public void removeUpdate(DocumentEvent e) {
				if(!textFieldWidth.getText().isEmpty()){
					widthSize = Double.parseDouble(textFieldWidth.getText().trim());
					fireEvent();
				}
			}
			
			@Override
			public void insertUpdate(DocumentEvent e) {
				if(!textFieldWidth.getText().isEmpty()){
					widthSize = Double.parseDouble(textFieldWidth.getText().trim());
					fireEvent();
				}
			}
			
			@Override
			public void changedUpdate(DocumentEvent e) {
				if(!textFieldWidth.getText().isEmpty()){
					widthSize = Double.parseDouble(textFieldWidth.getText().trim());
					fireEvent();
				}
			}
		});
		add(textFieldWidth);
		textFieldWidth.setColumns(10);
		
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
					if(lineUnits==2){
						widthSize*= 100; 
						textFieldWidth.setText(String.format("%.3f", widthSize));
					}
					else if(lineUnits==3){
						widthSize*= 4.0; 
						textFieldWidth.setText(String.format("%.3f", widthSize));
					}
					lineUnits = 1;
				}
				else if(((String)e.getItem()).equalsIgnoreCase("Inches")){
					if(lineUnits==1){
						widthSize*= 0.01; 
						textFieldWidth.setText(String.format("%.3f", widthSize));
					}
					else if(lineUnits==3){
						widthSize*= 0.04; 
						textFieldWidth.setText(String.format("%.3f", widthSize));
					}
					lineUnits = 2;
				}
				else if(((String)e.getItem()).equalsIgnoreCase("mm")){
					if(lineUnits==1){
						widthSize*= 0.25; 
						textFieldWidth.setText(String.format("%.3f", widthSize));
					}
					else if(lineUnits==2){
						widthSize*= 25.0; 
						textFieldWidth.setText(String.format("%.3f", widthSize));
					}
					lineUnits = 3;
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
		
		lblArrow = new JLabel("Arrow");
		lblArrow.setVisible(false);
		add(lblArrow);
		
		textFieldArrowSize = new JTextField("0.2");
		textFieldArrowSize.setToolTipText("Arrow Size");
		textFieldArrowSize.setVisible(false);
		textFieldArrowSize.getDocument().addDocumentListener(new DocumentListener() {
			
			@Override
			public void removeUpdate(DocumentEvent e) {
				if(!textFieldArrowSize.getText().isEmpty()){
					arrowSize = Double.parseDouble(textFieldArrowSize.getText().trim());
					fireEvent();
				}
			}
			
			@Override
			public void insertUpdate(DocumentEvent e) {
				if(!textFieldArrowSize.getText().isEmpty()){
					arrowSize = Double.parseDouble(textFieldArrowSize.getText().trim());
					fireEvent();
				}
			}
			
			@Override
			public void changedUpdate(DocumentEvent e) {
				if(!textFieldArrowSize.getText().isEmpty()){
					arrowSize = Double.parseDouble(textFieldArrowSize.getText().trim());
					fireEvent();
				}
			}
		});
		add(textFieldArrowSize);
		textFieldArrowSize.setColumns(5);
		
		lblHeadRatio = new JLabel("Head Ratio");
		lblHeadRatio.setVisible(false);
		add(lblHeadRatio);
		
		textFieldHeadRatio = new JTextField("2.5");
		textFieldHeadRatio.setVisible(false);
		textFieldHeadRatio.getDocument().addDocumentListener(new DocumentListener() {
			
			@Override
			public void removeUpdate(DocumentEvent e) {
				if(!textFieldHeadRatio.getText().isEmpty()){
					headRiatio = Double.parseDouble(textFieldHeadRatio.getText().trim());
					fireEvent();
				}
			}
			
			@Override
			public void insertUpdate(DocumentEvent e) {
				if(!textFieldHeadRatio.getText().isEmpty()){
					headRiatio = Double.parseDouble(textFieldHeadRatio.getText().trim());
					fireEvent();
				}
			}
			
			@Override
			public void changedUpdate(DocumentEvent e) {
				if(!textFieldHeadRatio.getText().isEmpty()){
					headRiatio = Double.parseDouble(textFieldHeadRatio.getText().trim());
					fireEvent();
				}
			}
		});
		add(textFieldHeadRatio);
		textFieldHeadRatio.setColumns(5);
		
		chckbxHalo.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				isHalo = chckbxHalo.isSelected();
				fireEvent();
			}
		});
		chckbxHalo.setVisible(false);
		add(chckbxHalo);
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
							ColorChooserPanel.this.dispose();		
							fireEvent();
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
		//super.fireEvent();
		 EventDispatcher eventDispatcher = getEventDispatcher();
		 LineEventParameter lineEventParameter = new LineEventParameter(getDrawType());
		 lineEventParameter.setArrowSize(arrowSize);
		 lineEventParameter.setHalo(isHalo);
		 lineEventParameter.setHeadRiatio(headRiatio);
		 lineEventParameter.setLineColor(lineColor);
		 lineEventParameter.setLineTypeCenter(lineType);
		 lineEventParameter.setLineTypeLeft(leftStyle);
		 lineEventParameter.setLineTypeRight(rightStyle);
		 lineEventParameter.setLineWidth(widthSize);
		 if(eventDispatcher==null){ return; }
		 eventDispatcher.fireEvent(lineEventParameter);
	}
}
