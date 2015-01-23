package net.sf.jcgm.display;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
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

import net.sf.jcgm.core.DashType;
import net.sf.jcgm.core.HatchIndex;
import net.sf.jcgm.core.InteriorStyle;
import net.sf.jcgm.drawer.DrawType;
import net.sf.jcgm.event.EventDispatcher;
import net.sf.jcgm.event.PEREventParameter;

import org.omg.CORBA.PRIVATE_MEMBER;
import org.omg.CORBA.PUBLIC_MEMBER;


public class PropertyPER extends BasePanel{

	private JComboBox comboBoxStyle;
	private JTextField textFieldwidth;
	private int lineType = 1; //default : solid
	private InteriorStyle.Style interiorStyle = InteriorStyle.Style.SOLID;
	private double widthSize = 0.01; // default :0.0
	private int units = 2;// default :points
	private HatchIndex.HatchType hatchIndex = HatchIndex.HatchType.HORIZONTAL_LINES;
	private Color edgeColor = null;
	private Color interiorColor = Color.BLACK;
	private JComboBox comboBoxHatchType;
	private JPanel panelInteriorColor;
	private JPanel panelOutLineColor;
	private JLabel lblType;
	private JLabel lblWidth;
	private JComboBox comboBoxLineType;
	private JComboBox comboBoxUnits;
	private JLabel jLabelNoEdge = new JLabel();
	private JLabel jLabelNoFill = new JLabel();

	private class ColorChooserPanel extends JFrame{
			
			private JColorChooser jColorChooser;
			private JButton buttonOk;
			private JButton buttonCancel;
			private JButton buttonNoEdge;
			private JButton buttonNoFill;
			private Object object;
			private JPanel buttonPanel;
			private int tempIndex;
			public ColorChooserPanel(int x,int y,final int i) {
				setLocation(x,y);
				setSize(500, 400);
				jColorChooser=new JColorChooser();
				add(jColorChooser,BorderLayout.CENTER);
				buttonPanel = new JPanel();
				if(i==1){
					buttonNoEdge = new JButton("No Edge");
					buttonPanel.add(buttonNoEdge);
					buttonNoEdge.addActionListener(new ActionListener() {
						@Override
						public void actionPerformed(ActionEvent e) {
							jLabelNoEdge.setVisible(true);
							jLabelNoEdge.setBackground(edgeColor);
							edgeColor = null;
							lblType.setVisible(false);
							lblWidth.setVisible(false);
							comboBoxLineType.setVisible(false);
							comboBoxUnits.setVisible(false);
							textFieldwidth.setVisible(false);
						}
					});
				}
				if(i==0){
					buttonNoFill = new JButton("No Fill");
					buttonPanel.add(buttonNoFill);
					buttonNoFill.addActionListener(new ActionListener() {
						@Override
						public void actionPerformed(ActionEvent e) {
							jLabelNoFill.setVisible(true);
							tempIndex = comboBoxStyle.getSelectedIndex();
							comboBoxStyle.setSelectedIndex(3);
							
						}
					});
				}
				buttonOk = new JButton("OK");
				buttonOk.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						if(i==0){
							jLabelNoFill.setVisible(false);
							comboBoxStyle.setSelectedIndex(tempIndex);
							interiorColor= jColorChooser.getColor();
							panelInteriorColor.setBackground(jColorChooser.getColor());
							fireEvent();
						}
						else if(i==1){
							jLabelNoEdge.setVisible(false);
							lblType.setVisible(true);
							lblWidth.setVisible(true);
							comboBoxLineType.setVisible(true);
							comboBoxUnits.setVisible(true);
							textFieldwidth.setVisible(true);
							edgeColor = jColorChooser.getColor();
							panelOutLineColor.setBorder(new LineBorder(jColorChooser.getColor(), 3));
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
	
	public PropertyPER(String name,DrawType drawType){
		super(drawType);
		setPreferredSize(new Dimension(729, 33));
		setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
		
		JLabel lblName = new JLabel(name);
		add(lblName);
		
		JLabel lblInterior = new JLabel(new ImageIcon(JCGMEditor.class.getResource("/images/interior.gif")));
		add(lblInterior);
		
		panelInteriorColor = new JPanel(new BorderLayout());
		jLabelNoFill.setIcon(new ImageIcon(JCGMEditor.class.getResource("/images/X.gif")));
		jLabelNoFill.setVisible(false);
		panelInteriorColor.add(BorderLayout.WEST,jLabelNoFill);
		panelInteriorColor.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				super.mousePressed(e);
				new ColorChooserPanel(panelInteriorColor.getX()+e.getX(),panelInteriorColor.getY()+e.getY(),0);
			}
		});
		panelInteriorColor.setToolTipText("Interior Color");
		panelInteriorColor.setBackground(Color.BLACK);
		panelInteriorColor.setPreferredSize(new Dimension(25,25));
		add(panelInteriorColor);
		
		class AcellRenderer extends JLabel implements ListCellRenderer{
			@Override
			public Component getListCellRendererComponent(JList list, Object value,
					int index, boolean isSelected, boolean cellHasFocus) {
				setIcon((ImageIcon)value);
				return this;
			}	
		}
		comboBoxHatchType = new JComboBox();
		comboBoxHatchType.addItem(new ImageIcon(JCGMEditor.class.getResource("/images/index0.gif")));
		comboBoxHatchType.addItem(new ImageIcon(JCGMEditor.class.getResource("/images/index1.gif")));
		comboBoxHatchType.addItem(new ImageIcon(JCGMEditor.class.getResource("/images/index2.gif")));
		comboBoxHatchType.addItem(new ImageIcon(JCGMEditor.class.getResource("/images/index3.gif")));
		comboBoxHatchType.addItem(new ImageIcon(JCGMEditor.class.getResource("/images/index4.gif")));
		comboBoxHatchType.addItem(new ImageIcon(JCGMEditor.class.getResource("/images/index5.gif")));
		comboBoxHatchType.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				if(comboBoxHatchType.getSelectedIndex()==0){
					hatchIndex = HatchIndex.HatchType.HORIZONTAL_LINES;
				}
				if(comboBoxHatchType.getSelectedIndex()==1){
					hatchIndex = HatchIndex.HatchType.VERTICAL_LINES;
				}
				if(comboBoxHatchType.getSelectedIndex()==2){
					hatchIndex = HatchIndex.HatchType.POSITIVE_SLOPE_LINES;
				}
				if(comboBoxHatchType.getSelectedIndex()==3){
					hatchIndex = HatchIndex.HatchType.NEGATIVE_SLOPE_LINES;
				}
				if(comboBoxHatchType.getSelectedIndex()==4){
					hatchIndex = HatchIndex.HatchType.HORIZONTAL_VERTICAL_CROSSHATCH;
				}
				if(comboBoxHatchType.getSelectedIndex()==5){
					hatchIndex = HatchIndex.HatchType.POSITIVE_NEGATIVE_CROSSHATCH;
				}
				fireEvent();
			}
		});
		comboBoxHatchType.setPreferredSize(new Dimension(55,20));
		comboBoxHatchType.setRenderer(new AcellRenderer());
		comboBoxHatchType.setVisible(true);
		comboBoxHatchType.setVisible(false);
		
		
		comboBoxStyle = new JComboBox();
		comboBoxStyle.addItem("Solid");
		comboBoxStyle.addItem("Pattern");
		comboBoxStyle.addItem("Hatch");
		comboBoxStyle.addItem("Interior Off");
		comboBoxStyle.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				if(((String)e.getItem()).equalsIgnoreCase("Solid")){
					interiorStyle = InteriorStyle.Style.SOLID;
					comboBoxHatchType.setVisible(false);
				}else if(((String)e.getItem()).equalsIgnoreCase("Pattern")){
					interiorStyle = InteriorStyle.Style.PATTERN;
					comboBoxHatchType.setVisible(false);
				}else if(((String)e.getItem()).equalsIgnoreCase("Hatch")){
					interiorStyle = InteriorStyle.Style.HATCH;
					comboBoxHatchType.setVisible(true);
				}else if(((String)e.getItem()).equalsIgnoreCase("Interior Off")){
					interiorStyle = InteriorStyle.Style.EMPTY;
					comboBoxHatchType.setVisible(false);
				}
				fireEvent();
			}
		});
		comboBoxStyle.setToolTipText("Interior Style");
		add(comboBoxStyle);
		add(comboBoxHatchType);
		
		
		
		JLabel labelIOutLineIcon = new JLabel(new ImageIcon(JCGMEditor.class.getResource("/images/write.gif")));
		add(labelIOutLineIcon);
		
		panelOutLineColor = new JPanel(new BorderLayout());
		jLabelNoEdge.setIcon(new ImageIcon(JCGMEditor.class.getResource("/images/X.gif")));
		panelOutLineColor.add(BorderLayout.WEST,jLabelNoEdge);
		panelOutLineColor.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				super.mousePressed(e);
				new ColorChooserPanel(panelOutLineColor.getX()+e.getX(),panelOutLineColor.getY()+e.getY(),1);
			}
		});
		panelOutLineColor.setBorder(new LineBorder(Color.BLACK, 3));
		panelOutLineColor.setToolTipText("Edge Color");
		panelOutLineColor.setPreferredSize(new Dimension(25,25));
		add(panelOutLineColor);
		
		
		comboBoxLineType = new JComboBox();
		comboBoxLineType.setRenderer(new AcellRenderer());
		comboBoxLineType.setToolTipText("style");
		comboBoxLineType.addItem(new ImageIcon(JCGMEditor.class.getResource("/images/line11.gif")));
		comboBoxLineType.addItem(new ImageIcon(JCGMEditor.class.getResource("/images/line2.gif")));
		comboBoxLineType.addItem(new ImageIcon(JCGMEditor.class.getResource("/images/line3.gif")));
		comboBoxLineType.addItem(new ImageIcon(JCGMEditor.class.getResource("/images/line4.gif")));
		comboBoxLineType.addItem(new ImageIcon(JCGMEditor.class.getResource("/images/line5.gif")));
		comboBoxLineType.addItemListener(new ItemListener() {		
			@Override
			public void itemStateChanged(ItemEvent e) {
				// TODO Auto-generated method stub
				if(comboBoxLineType.getSelectedIndex()==0){
					lineType = DashType.SOLID;//SOLID
				}
				else if(comboBoxLineType.getSelectedIndex()==1){
					lineType = DashType.DASH;//DASH
				}
				else if(comboBoxLineType.getSelectedIndex()==2){
					lineType = DashType.DOT;//DOT
				}
				else if(comboBoxLineType.getSelectedIndex()==3){
					lineType = DashType.DASH_DOT;//DASH_DOT
				}
				else if(comboBoxLineType.getSelectedIndex()==4){
					lineType = DashType.DASH_DOT_DOT;//DASH_DOT_DOT
				}
				fireEvent();
			}
		});
		
		lblType = new JLabel("Type");
		add(lblType);
		add(comboBoxLineType);
		
		lblWidth = new JLabel("Width");
		add(lblWidth);
		
		textFieldwidth = new JTextField("0.010");
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
		textFieldwidth.setColumns(5);
		
		comboBoxUnits = new JComboBox();
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
		lblType.setVisible(false);
		lblWidth.setVisible(false);
		comboBoxLineType.setVisible(false);
		comboBoxUnits.setVisible(false);
		textFieldwidth.setVisible(false);
	}
	@Override
	public void fireEvent() {
		super.fireEvent();
		EventDispatcher eventDispatcher = getEventDispatcher();
		PEREventParameter perEventParameter = new PEREventParameter(getDrawType());
		perEventParameter.setEdgeColor(edgeColor);
		perEventParameter.setHatchIndex(hatchIndex);
		perEventParameter.setInteriorColor(interiorColor);
		perEventParameter.setInteriorStyle(interiorStyle);
		perEventParameter.setEdgeType(lineType);
		perEventParameter.setEdgeWidth(widthSize);
		if(eventDispatcher==null){ return; }
		eventDispatcher.fireEvent(perEventParameter);
	}
}
