package net.sf.jcgm.display;

import java.awt.Color;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
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
import javax.swing.JToggleButton;
import javax.swing.ButtonGroup;
import javax.swing.ListCellRenderer;

import net.sf.jcgm.drawer.DrawType;
import net.sf.jcgm.event.EventDispatcher;
import net.sf.jcgm.event.TextEventParameter;



public class PropertyText extends BasePanel{
	private JTextField textFieldFontSize;
	private JTextField textFieldRotation;
	private Color fontColor = Color.BLACK;
	private Color backgroundColor;
	private int textType = 1; //default :0 helvetica 1 times 2 courier 3 symbol
	private double fontSize = 0.2;
	private double fontRotation = 0.0;
	private int fontUnits = 2;
	private boolean isItalic;
	private boolean isBold;
	private int textAlignment = 1;
	private JPanel panelFontColor;
	
	
	private class ColorChooserPanel extends JFrame{
			
		private JColorChooser jColorChooser;
		private JButton buttonOk;
		private JButton buttonCancel;
		private Object object;
		public ColorChooserPanel(int x,int y,final int i) {
			setLocation(x,y);
			setSize(500, 400);
			jColorChooser=new JColorChooser();
			add(jColorChooser,BorderLayout.CENTER);
			JPanel buttonPanel = new JPanel();
			buttonOk = new JButton("OK");
			buttonOk.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					if(i==0){
						 fontColor = jColorChooser.getColor();
						 panelFontColor.setBackground(jColorChooser.getColor());
						 fireEvent();
					}
					else if(i==1){
						backgroundColor = jColorChooser.getColor();
						fireEvent();
					}
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
			buttonPanel.add(buttonCancel);
			add(buttonPanel,BorderLayout.SOUTH);
			setVisible(true);
		}		
	}

	private final ButtonGroup buttonGroup = new ButtonGroup();
	public PropertyText(DrawType drawType){
		super(drawType);
		setBorder(new LineBorder(new Color(0, 0, 0)));
		setPreferredSize(new Dimension(691, 33));
		setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
		JLabel lblText = new JLabel("Text");
		add(lblText);
		
		JComboBox comboBoxFontType = new JComboBox();
		comboBoxFontType.addItem("Helvetica");
		comboBoxFontType.addItem("Times");
		comboBoxFontType.addItem("Courier");
		comboBoxFontType.addItem("Symbol");
		comboBoxFontType.addItem("Avant Garde Book");
		comboBoxFontType.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				if(((String)e.getItem()).equalsIgnoreCase("Helvetica")){
					textType = 1;
				}
				else if(((String)e.getItem()).equalsIgnoreCase("Times")){
					textType = 2;
				}
				else if(((String)e.getItem()).equalsIgnoreCase("Courier")){
					textType = 3;
				}
				else if(((String)e.getItem()).equalsIgnoreCase("Symbol")){
					textType = 4;
				}
				else if(((String)e.getItem()).equalsIgnoreCase("Avant Garde Book")){
					textType = 5;
				}
				fireEvent();
			}
		});
		comboBoxFontType.setToolTipText("Font");
		add(comboBoxFontType);
		
		JLabel lblFontHeight = new JLabel(new ImageIcon(JCGMEditor.class.getResource("/images/fontSize.gif")));
		add(lblFontHeight);
		
		textFieldFontSize = new JTextField("0.2");
		textFieldFontSize.getDocument().addDocumentListener(new DocumentListener() {
			
			@Override
			public void removeUpdate(DocumentEvent e) {
				if(!textFieldFontSize.getText().isEmpty()){
					fontSize = Double.parseDouble(textFieldFontSize.getText().trim());
					fireEvent();
				}
			}
			
			@Override
			public void insertUpdate(DocumentEvent e) {
				if(!textFieldFontSize.getText().isEmpty()){
					fontSize = Double.parseDouble(textFieldFontSize.getText().trim());
					fireEvent();
				}
			}
			
			@Override
			public void changedUpdate(DocumentEvent e) {
				if(!textFieldFontSize.getText().isEmpty()){
					fontSize = Double.parseDouble(textFieldFontSize.getText().trim());
					fireEvent();
				}
			}
		});
		textFieldFontSize.setToolTipText("Size");
		add(textFieldFontSize);
		textFieldFontSize.setColumns(10);
		
		JComboBox comboBoxFontUnits = new JComboBox();
		comboBoxFontUnits.setToolTipText("Unit");
		comboBoxFontUnits.addItem("Points");
		comboBoxFontUnits.addItem("Inches");
		comboBoxFontUnits.addItem("mm");
		comboBoxFontUnits.setSelectedIndex(1);
		comboBoxFontUnits.addItemListener(new ItemListener() {	
			@Override
			public void itemStateChanged(ItemEvent e) {
				if(((String)e.getItem()).equalsIgnoreCase("Points")){
					if(fontUnits==2){
						fontSize*= 100; 
						textFieldFontSize.setText(String.format("%.3f", fontSize));
					}
					else if(fontUnits==3){
						fontSize*= 4.0; 
						textFieldFontSize.setText(String.format("%.3f", fontSize));
					}
					fontUnits = 1;
				}
				else if(((String)e.getItem()).equalsIgnoreCase("Inches")){
					if(fontUnits==1){
						fontSize*= 0.01; 
						textFieldFontSize.setText(String.format("%.3f", fontSize));
					}
					else if(fontUnits==3){
						fontSize*= 0.04; 
						textFieldFontSize.setText(String.format("%.3f", fontSize));
					}
					fontUnits = 2;
				}
				else if(((String)e.getItem()).equalsIgnoreCase("mm")){
					if(fontUnits==1){
						fontSize*= 0.25; 
						textFieldFontSize.setText(String.format("%.3f", fontSize));
					}
					else if(fontUnits==2){
						fontSize*= 25.0; 
						textFieldFontSize.setText(String.format("%.3f", fontSize));
					}
					fontUnits = 3;
				}
				fireEvent();
			}
		});
		comboBoxFontUnits.setToolTipText("Unit");
		add(comboBoxFontUnits);
		
		panelFontColor = new JPanel();
		panelFontColor.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				super.mousePressed(e);
				if(e.getButton()==MouseEvent.BUTTON1){
					new ColorChooserPanel(panelFontColor.getX()+e.getX(),panelFontColor.getY()+e.getY(),0);
				}
				else if(e.getButton()==MouseEvent.BUTTON3){
					new ColorChooserPanel(panelFontColor.getX()+e.getX(),panelFontColor.getY()+e.getY(),1);
				}
			}
		});
		panelFontColor.setPreferredSize(new Dimension(20, 20));
		panelFontColor.setToolTipText("Left Click : Font Color Right Click: Backgrount Color");
		panelFontColor.setBackground(Color.BLACK);
		add(panelFontColor);
		
		JLabel lblRotation = new JLabel(new ImageIcon(JCGMEditor.class.getResource("/images/fontRotate.gif")));
		add(lblRotation);
		
		textFieldRotation = new JTextField("0.0");
		textFieldRotation.getDocument().addDocumentListener(new DocumentListener() {
			
			@Override
			public void removeUpdate(DocumentEvent e) {
				if(!textFieldRotation.getText().isEmpty()){
					fontRotation = Double.parseDouble(textFieldRotation.getText().trim());
					fireEvent();
				}
			}
			
			@Override
			public void insertUpdate(DocumentEvent e) {
				if(!textFieldRotation.getText().isEmpty()){
					fontRotation = Double.parseDouble(textFieldRotation.getText().trim());
					fireEvent();
				}
			}
			
			@Override
			public void changedUpdate(DocumentEvent e) {
				if(!textFieldRotation.getText().isEmpty()){
					fontRotation = Double.parseDouble(textFieldRotation.getText().trim());
					fireEvent();
				}
			}
		});
		textFieldRotation.setToolTipText("Rotation");
		add(textFieldRotation);
		textFieldRotation.setColumns(10);
		
		class AcellRenderer extends JLabel implements ListCellRenderer{
			@Override
			public Component getListCellRendererComponent(JList list, Object value,
					int index, boolean isSelected, boolean cellHasFocus) {
				setIcon((ImageIcon)value);
				return this;
			}	
		}
		
		final JComboBox comboBoxTextAlignment = new JComboBox();
		comboBoxTextAlignment.addItem(new ImageIcon(JCGMEditor.class.getResource("/images/leftText.gif")));
		comboBoxTextAlignment.addItem(new ImageIcon(JCGMEditor.class.getResource("/images/centerText.gif")));
		comboBoxTextAlignment.addItem(new ImageIcon(JCGMEditor.class.getResource("/images/rightText.gif")));
		comboBoxTextAlignment.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				if(comboBoxTextAlignment.getSelectedIndex()==0){
					textAlignment = 1;
				}
				else if(comboBoxTextAlignment.getSelectedIndex()==1){
					textAlignment = 2;
				}
				else if(comboBoxTextAlignment.getSelectedIndex()==2){
					textAlignment = 3;
				}
				fireEvent();
			}
		});
		comboBoxTextAlignment.setRenderer(new AcellRenderer());
		comboBoxTextAlignment.setToolTipText("Text Alignment");
		add(comboBoxTextAlignment);
		
		final JToggleButton tglbtnBold = new JToggleButton(new ImageIcon(JCGMEditor.class.getResource("/images/boldText.gif")));
		tglbtnBold.setPreferredSize(new Dimension(25, 25));
		tglbtnBold.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				isBold = tglbtnBold.isSelected();
				fireEvent();
			}
		});
		tglbtnBold.setToolTipText("Set Bold Text");
		add(tglbtnBold);
		
		final JToggleButton tglbtnItalic = new JToggleButton(new ImageIcon(JCGMEditor.class.getResource("/images/italic.gif")));
		tglbtnItalic.setPreferredSize(new Dimension(25, 25));
		tglbtnItalic.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				isItalic = tglbtnItalic.isSelected();
				fireEvent();
			}
		});
		tglbtnItalic.setToolTipText("Set Italic Text");
		add(tglbtnItalic);
	}
	
	@Override
	public void fireEvent() {
		super.fireEvent();
		EventDispatcher eventDispatcher = getEventDispatcher();
		TextEventParameter textEventParameter = new TextEventParameter();
		textEventParameter.setBackgroundColor(backgroundColor);
		textEventParameter.setBold(isBold);
		textEventParameter.setFontColor(fontColor);
		textEventParameter.setFontRotation(fontRotation);
		textEventParameter.setFontSize(fontSize);
		textEventParameter.setFontType(textType);
		textEventParameter.setItalic(isItalic);
		textEventParameter.setTextAlignment(textAlignment);
		textEventParameter.setUnit(fontUnits);
		if(eventDispatcher==null){ return; }
		eventDispatcher.fireEvent(textEventParameter);
	}
	
}
