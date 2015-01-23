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
import javax.swing.JCheckBox;
import javax.swing.ListCellRenderer;

import net.sf.jcgm.drawer.DrawType;
import net.sf.jcgm.event.EventDispatcher;
import net.sf.jcgm.event.LeaderLineEventParameter;




public class PropertyLeaderLine extends BasePanel{
	private JTextField textFieldTextSize;
	private JTextField textFieldArrow;
	private Color lineColor = Color.BLACK;
	private int lineLeft = 1;
	private int lineCenter = 1;
	private int lineRight = 1;
	private double lineWidth = 0.0;
	private double fontSize = 0.2;
	private double arrowSize = 0.2;
	private int fontUnits = 2;
	private boolean isHalo;
	private int textType = 1; //  helvetica 1 times 2 courier 3 symbol 4
	private int textAlignment = 1;
	private JPanel panelLineColor;

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
					 panelLineColor.setBackground(jColorChooser.getColor());	
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
			buttonPanel.add(buttonCancel);
			add(buttonPanel,BorderLayout.SOUTH);
			setVisible(true);
		}		
	}
	
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JTextField textFieldWidth;
	public PropertyLeaderLine(DrawType drawType){
		super(drawType);
		setBorder(null);
		setPreferredSize(new Dimension(1100, 33));
		setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
		
		textFieldTextSize = new JTextField();
		textFieldTextSize.setText("0.2");
		textFieldTextSize.getDocument().addDocumentListener(new DocumentListener() {
			
			@Override
			public void removeUpdate(DocumentEvent e) {
				if(!textFieldTextSize.getText().isEmpty()){
					fontSize = Double.parseDouble(textFieldTextSize.getText().trim());
					fireEvent();
				}
			}
			
			@Override
			public void insertUpdate(DocumentEvent e) {
				if(!textFieldTextSize.getText().isEmpty()){
					fontSize = Double.parseDouble(textFieldTextSize.getText().trim());
					fireEvent();
				}
			}
			
			@Override
			public void changedUpdate(DocumentEvent e) {
				if(!textFieldTextSize.getText().isEmpty()){
					fontSize = Double.parseDouble(textFieldTextSize.getText().trim());
					fireEvent();
				}
			}
		});
		
		textFieldArrow = new JTextField("0.2");
		textFieldArrow.getDocument().addDocumentListener(new DocumentListener() {
			
			@Override
			public void removeUpdate(DocumentEvent e) {
				if(!textFieldArrow.getText().isEmpty()){
					arrowSize = Double.parseDouble(textFieldArrow.getText().trim());
					fireEvent();
				}
			}
			
			@Override
			public void insertUpdate(DocumentEvent e) {
				if(!textFieldArrow.getText().isEmpty()){
					arrowSize = Double.parseDouble(textFieldArrow.getText().trim());
					fireEvent();
				}
			}
			
			@Override
			public void changedUpdate(DocumentEvent e) {
				if(!textFieldArrow.getText().isEmpty()){
					arrowSize = Double.parseDouble(textFieldArrow.getText().trim());
					fireEvent();
				}
			}
		});
		panelLineColor = new JPanel();
		panelLineColor.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				super.mousePressed(e);
				if(e.getButton()==MouseEvent.BUTTON1){
					new ColorChooserPanel(panelLineColor.getX()+e.getX(),panelLineColor.getY()+e.getY());
				}
			}
		});
		
		JLabel lblLeaderline = new JLabel("LeaderLine");
		add(lblLeaderline);
		
		class AcellRenderer extends JLabel implements ListCellRenderer{
			@Override
			public Component getListCellRendererComponent(JList list, Object value,
					int index, boolean isSelected, boolean cellHasFocus) {
				setIcon((ImageIcon)value);
				return this;
			}	
		}
		
		final JComboBox comboBoxLeftEndStyle = new JComboBox();
		comboBoxLeftEndStyle.setRenderer(new AcellRenderer());
		comboBoxLeftEndStyle.addItem(new ImageIcon(JCGMEditor.class.getResource("/images/sline.gif")));
		comboBoxLeftEndStyle.addItem(new ImageIcon(JCGMEditor.class.getResource("/images/leftArrow.gif")));
		comboBoxLeftEndStyle.addItem(new ImageIcon(JCGMEditor.class.getResource("/images/leaderleft1.gif")));
		comboBoxLeftEndStyle.addItem(new ImageIcon(JCGMEditor.class.getResource("/images/leaderleft2.gif")));
		comboBoxLeftEndStyle.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				if(comboBoxLeftEndStyle.getSelectedIndex()==0){
					lineLeft = 1;
				}
				else if(comboBoxLeftEndStyle.getSelectedIndex()==1){
					lineLeft = 2;
				}
				else if(comboBoxLeftEndStyle.getSelectedIndex()==2){
					lineLeft = 3;
				}
				else if(comboBoxLeftEndStyle.getSelectedIndex()==3){
					lineLeft = 4;
				}
				fireEvent();
			}
		});
		comboBoxLeftEndStyle.setToolTipText("Left end Style");
		add(comboBoxLeftEndStyle);
		
		final JComboBox comboBoxStyle = new JComboBox();
		comboBoxStyle.setRenderer(new AcellRenderer());
		comboBoxStyle.setToolTipText("style");
		comboBoxStyle.addItem(new ImageIcon(JCGMEditor.class.getResource("/images/leaderCenter1.gif")));
		comboBoxStyle.addItem(new ImageIcon(JCGMEditor.class.getResource("/images/leaderCenter2.gif")));
		comboBoxStyle.addItem(new ImageIcon(JCGMEditor.class.getResource("/images/leaderCenter3.gif")));
		comboBoxStyle.addItem(new ImageIcon(JCGMEditor.class.getResource("/images/leaderCenter4.gif")));
		comboBoxStyle.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				if(comboBoxStyle.getSelectedIndex()==0){
					lineCenter = 1;
				}
				else if(comboBoxStyle.getSelectedIndex()==1){
					lineCenter = 2;
				}
				else if(comboBoxStyle.getSelectedIndex()==2){
					lineCenter = 3;
				}
				else if(comboBoxStyle.getSelectedIndex()==3){
					lineCenter = 4;
				}
				fireEvent();
			}
		});
		add(comboBoxStyle);
		
		final JComboBox comboBoxRightEndStyle = new JComboBox();
		comboBoxRightEndStyle.addItem(new ImageIcon(JCGMEditor.class.getResource("/images/sline.gif")));
		comboBoxRightEndStyle.addItem(new ImageIcon(JCGMEditor.class.getResource("/images/rightArrow.gif")));
		comboBoxRightEndStyle.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				if(comboBoxRightEndStyle.getSelectedIndex()==0){
					lineRight = 1;
				}
				else if(comboBoxRightEndStyle.getSelectedIndex()==1){
					lineRight = 2;
				}
				fireEvent();
			}
		});
		comboBoxRightEndStyle.setRenderer(new AcellRenderer());
		comboBoxRightEndStyle.setToolTipText("Right end Style");
		add(comboBoxRightEndStyle);
		
		
		panelLineColor.setPreferredSize(new Dimension(20, 20));
		panelLineColor.setToolTipText("Set Line Color");
		panelLineColor.setBackground(Color.BLACK);
		add(panelLineColor);
		
		JLabel lblWidth = new JLabel("Width");
		add(lblWidth);
		
		textFieldWidth = new JTextField();
		textFieldWidth.getDocument().addDocumentListener(new DocumentListener() {
			
			@Override
			public void removeUpdate(DocumentEvent e) {
				if(!textFieldWidth.getText().isEmpty()){
					lineWidth = Double.parseDouble(textFieldWidth.getText().trim());
					fireEvent();
				}
			}
			
			@Override
			public void insertUpdate(DocumentEvent e) {
				if(!textFieldWidth.getText().isEmpty()){
					lineWidth = Double.parseDouble(textFieldWidth.getText().trim());
					fireEvent();
				}
			}
			
			@Override
			public void changedUpdate(DocumentEvent e) {
				if(!textFieldWidth.getText().isEmpty()){
					lineWidth = Double.parseDouble(textFieldWidth.getText().trim());
					fireEvent();
				}
			}
		});
		textFieldWidth.setToolTipText("Size");
		textFieldWidth.setText("0.0");
		add(textFieldWidth);
		textFieldWidth.setColumns(10);
		
		JLabel lblArrow = new JLabel("Arrow");
		add(lblArrow);
		textFieldArrow.setToolTipText("Arrow Size");
		textFieldArrow.setText("0.0");
		add(textFieldArrow);
		textFieldArrow.setColumns(10);
		
		JLabel lblTextSize = new JLabel("Text");
		add(lblTextSize);
		textFieldTextSize.setToolTipText("Text Size");
		add(textFieldTextSize);
		textFieldTextSize.setColumns(10);
		
		JComboBox comboBoxFontType = new JComboBox();
		comboBoxFontType.addItem("Helvetica");
		comboBoxFontType.addItem("Times");
		comboBoxFontType.addItem("Courier");
		comboBoxFontType.addItem("Symbol");
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
				fireEvent();
			}
		});
		
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
						arrowSize*=100;
						lineWidth*=100;
						textFieldTextSize.setText(String.format("%.3f", fontSize));
						textFieldWidth.setText(String.format("%.3f", lineWidth));
						textFieldArrow.setText(String.format("%.3f", arrowSize));
					}
					else if(fontUnits==3){
						fontSize*= 4; 
						arrowSize*= 4;
						lineWidth*= 4;
						textFieldTextSize.setText(String.format("%.3f", fontSize));
						textFieldWidth.setText(String.format("%.3f", lineWidth));
						textFieldArrow.setText(String.format("%.3f", arrowSize));
					}
					fontUnits = 1;
				}
				else if(((String)e.getItem()).equalsIgnoreCase("Inches")){
					if(fontUnits==1){
						fontSize*= 0.01; 
						arrowSize*=0.01;
						lineWidth*=0.01;
						textFieldTextSize.setText(String.format("%.3f", fontSize));
						textFieldWidth.setText(String.format("%.3f", lineWidth));
						textFieldArrow.setText(String.format("%.3f", arrowSize));
					}
					else if(fontUnits==3){
						fontSize*= 0.04; 
						arrowSize*=0.04;
						lineWidth*=0.04;
						textFieldTextSize.setText(String.format("%.3f", fontSize));
						textFieldWidth.setText(String.format("%.3f", lineWidth));
						textFieldArrow.setText(String.format("%.3f", arrowSize));
					}
					fontUnits = 2;
				}
				else if(((String)e.getItem()).equalsIgnoreCase("mm")){
					if(fontUnits==1){
						fontSize*= 0.25; 
						arrowSize*=0.25;
						lineWidth*=0.25;
						textFieldTextSize.setText(String.format("%.3f", fontSize));
						textFieldWidth.setText(String.format("%.3f", lineWidth));
						textFieldArrow.setText(String.format("%.3f", arrowSize));
					}
					else if(fontUnits==2){
						fontSize*= 25.0; 
						arrowSize*=25.0;
						lineWidth*=25.0;
						textFieldTextSize.setText(String.format("%.3f", fontSize));
						textFieldWidth.setText(String.format("%.3f", lineWidth));
						textFieldArrow.setText(String.format("%.3f", arrowSize));
					}
					fontUnits = 3;
				}
				fireEvent();
			}
		});
		comboBoxFontUnits.setToolTipText("Unit");
		add(comboBoxFontUnits);
		comboBoxFontType.setToolTipText("Font");
		add(comboBoxFontType);
		
	 
		
		final JCheckBox chckbxHalo = new JCheckBox("Halo");
		chckbxHalo.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				isHalo = chckbxHalo.isSelected();
				fireEvent();
			}
		});
		add(chckbxHalo);
		
	 
		
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
 
	}
	@Override
	public void fireEvent() {
		super.fireEvent();
		EventDispatcher eventDispatcher = getEventDispatcher();
		LeaderLineEventParameter leaderLineEventParameter = new LeaderLineEventParameter();
		leaderLineEventParameter.setArrowSize(arrowSize);
		leaderLineEventParameter.setFontSize(fontSize);
		leaderLineEventParameter.setHalo(isHalo);
		leaderLineEventParameter.setLineColor(lineColor);
		leaderLineEventParameter.setLineTypeCenter(lineCenter);
		leaderLineEventParameter.setLineTypeLeft(lineLeft);
		leaderLineEventParameter.setLineTypeRight(lineRight);
		leaderLineEventParameter.setLineWidth(lineWidth);
		leaderLineEventParameter.setTextAlignment(textAlignment);
		leaderLineEventParameter.setTextType(textType);
		leaderLineEventParameter.setUnit(fontUnits);
		if(eventDispatcher==null){ return; }
		eventDispatcher.fireEvent(leaderLineEventParameter);
	}
}
