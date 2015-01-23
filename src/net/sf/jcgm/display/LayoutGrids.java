package net.sf.jcgm.display;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;
import javax.swing.JCheckBox;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.border.TitledBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JToggleButton;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.ButtonGroup;

import net.sf.jcgm.properties.LayoutGridsProp;


public class LayoutGrids extends JFrame{
	private static final int screenWidth=(int)java.awt.Toolkit.getDefaultToolkit().getScreenSize().width*65535/1280;
	private static final int screenHeight = (int)java.awt.Toolkit.getDefaultToolkit().getScreenSize().height*65535/800;
	private boolean isLocked;
	private JSpinner spinnerVertical;
	private JToggleButton tglbtnLock;
	private JSpinner spinnerHorizon;
	private JButton btnOk;
	private JCheckBox chckbxSnapToGrid;
	private JPanel showGridpanel ;
	private JLabel lblShowGrid;
	private JPanel panel;
	private JRadioButton rdbtnLines;
	private JRadioButton rdbtnDots;
	private JLabel lblHorizontal ;
	private JLabel lblVertical;
	private JCheckBox chckbxShowGrid;
	private JComboBox comboBox;
	private LayoutGridsProp LayoutGridsProp;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	
	private boolean gridIsShowGrid;
	private boolean gridIsSnaptoGrid;
	private double gridHorizontal;
	private double gridVertical;
	private int gridHowShowGrid; //0 default, 1 lines, 2 Dots
	private String gridUnit;
	
	
	public LayoutGrids(int x,int y){
		this.setBounds(x*screenWidth/65535,y*screenHeight/65535,340*screenWidth/65535, 280*screenHeight/65535);
		isLocked = true;
		gridHowShowGrid = 0;
		LayoutGridsProp = new LayoutGridsProp();
		 getContentPane().setLayout(null);
		 chckbxShowGrid = new JCheckBox("Show grid");
		 chckbxShowGrid.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				if(chckbxShowGrid.isSelected()){
					gridIsShowGrid = true;
				}
				else {
					gridIsShowGrid = false;
				}
			}
		});
		 chckbxShowGrid.setBounds(23*screenWidth/65535, 24*screenHeight/65535, 103*screenWidth/65535, 23*screenHeight/65535);
		 getContentPane().add(chckbxShowGrid);
		 
		 chckbxSnapToGrid = new JCheckBox("Snap to grid"); 
		 chckbxSnapToGrid.addItemListener(new ItemListener() {
				@Override
				public void itemStateChanged(ItemEvent e) {
					if(chckbxSnapToGrid.isSelected()){
						gridIsSnaptoGrid = true;
					}
					else {
						gridIsSnaptoGrid = false;
					}
				}
			});
		 chckbxSnapToGrid.setBounds(23*screenWidth/65535, 51*screenHeight/65535, 103*screenWidth/65535, 23*screenHeight/65535);
		 getContentPane().add(chckbxSnapToGrid);
		 
		 showGridpanel = new JPanel();
		 showGridpanel.setBounds(129*screenWidth/65535, 10*screenHeight/65535, 185*screenWidth/65535, 78*screenHeight/65535);
		 showGridpanel.setBorder(new LineBorder(Color.LIGHT_GRAY));
		 getContentPane().add(showGridpanel);
		 showGridpanel.setLayout(null);
		 
		 lblShowGrid = new JLabel("Show grid");
		 lblShowGrid.setBounds(10*screenWidth/65535, 27*screenHeight/65535, 73*screenWidth/65535, 23*screenHeight/65535);
		 showGridpanel.add(lblShowGrid);
		 
		 rdbtnLines = new JRadioButton("Lines");
		 rdbtnLines.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				if(rdbtnLines.isSelected()){
					gridHowShowGrid = 1;
				}
			}
		});
		 buttonGroup.add(rdbtnLines);
		 rdbtnLines.setBounds(115*screenWidth/65535, 16*screenHeight/65535, 64*screenWidth/65535, 23*screenHeight/65535); 
		 showGridpanel.add(rdbtnLines);
		 
		 rdbtnDots = new JRadioButton("Dots");
		 rdbtnDots.addItemListener(new ItemListener() {
				@Override
				public void itemStateChanged(ItemEvent e) {
					if(rdbtnDots.isSelected()){
						gridHowShowGrid = 2;
					}
				}
			});
		 buttonGroup.add(rdbtnDots);
		 rdbtnDots.setBounds(115*screenWidth/65535, 41*screenHeight/65535, 73*screenWidth/65535, 23*screenHeight/65535);
		 showGridpanel.add(rdbtnDots);
		 
		 panel = new JPanel();
		 panel.setBorder(new TitledBorder(new LineBorder(new Color(192, 192, 192)), "Spacing", TitledBorder.LEFT, TitledBorder.TOP, null, null));
		 panel.setBounds(23*screenWidth/65535, 93*screenHeight/65535, 291*screenWidth/65535, 86*screenHeight/65535);
		 getContentPane().add(panel);
		 panel.setLayout(null);
		 
		 lblHorizontal = new JLabel("Horizontal");
		 lblHorizontal.setBounds(10*screenWidth/65535, 21*screenHeight/65535, 71*screenWidth/65535, 20*screenHeight/65535);
		 panel.add(lblHorizontal);
		 
		 lblVertical = new JLabel("Vertical");
		 lblVertical.setBounds(10*screenWidth/65535, 51*screenHeight/65535, 62*screenWidth/65535, 20*screenHeight/65535);
		 panel.add(lblVertical);
		 
		 spinnerHorizon = new JSpinner();
		 spinnerHorizon.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				if(!isLocked){
					spinnerVertical.setValue(((JSpinner)e.getSource()).getValue());
				}
			}
		});
		 spinnerHorizon.setModel(new SpinnerNumberModel(0.0, 0.0, 99999.0, 1.0));
		 spinnerHorizon.setBounds(82*screenWidth/65535, 21*screenHeight/65535, 81*screenWidth/65535, 22*screenHeight/65535);
		 panel.add(spinnerHorizon);
		 
		 spinnerVertical = new JSpinner();
		 spinnerVertical.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				if(isLocked){
					spinnerHorizon.setValue(((JSpinner)e.getSource()).getValue());
				}
			}
		});
		 spinnerVertical.setBounds(82*screenWidth/65535, 51*screenHeight/65535, 80*screenWidth/65535, 22*screenHeight/65535);
		 panel.add(spinnerVertical);
		 
		 
		 
		 comboBox = new JComboBox();
		 comboBox.addItem("Inches");
		 comboBox.addItem("Millimeters");
		 comboBox.setBounds(197*screenWidth/65535, 33*screenHeight/65535, 84*screenWidth/65535, 22*screenHeight/65535);
		 panel.add(comboBox);
		 
		 
		 tglbtnLock = new JToggleButton("",new ImageIcon(JCGMEditor.class.getResource("/images/lock.gif")));
		 tglbtnLock.setBackground(Color.green);
		 tglbtnLock.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				if(tglbtnLock.isSelected()){
					isLocked = false;
					tglbtnLock.setIcon(new ImageIcon(JCGMEditor.class.getResource("/images/unlock.gif")));
				}
				else {
					
					isLocked = true;
					tglbtnLock.setIcon(new ImageIcon(JCGMEditor.class.getResource("/images/lock.gif")));
					tglbtnLock.setBackground(Color.green);
				}
			}
		});
		 tglbtnLock.setBounds(168*screenWidth/65535, 33*screenHeight/65535, 24*screenWidth/65535, 23*screenHeight/65535);
		 panel.add(tglbtnLock);
		 
		 btnOk = new JButton("OK");
		 btnOk.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				LayoutGridsProp.setGridHorizontal(gridHorizontal);
				LayoutGridsProp.setGridHowShowGrid(gridHowShowGrid);
				LayoutGridsProp.setGridIsShowGrid(gridIsShowGrid);
				LayoutGridsProp.setGridIsSnaptoGrid(gridIsSnaptoGrid);
				LayoutGridsProp.setGridUnit(gridUnit);
				LayoutGridsProp.setGridVertical(gridVertical);
				LayoutGrids.this.dispose();
				// TODO Auto-generated method stub
			}
		});
		 btnOk.setBounds(112*screenWidth/65535, 189*screenHeight/65535, 93*screenWidth/65535, 23*screenHeight/65535);
		 getContentPane().add(btnOk);
		 this.setVisible(true);
		 setResizable(false);
		 }

	}
	

