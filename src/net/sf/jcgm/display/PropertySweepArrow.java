package net.sf.jcgm.display;

import java.awt.BorderLayout;
import java.awt.Color;
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
import javax.swing.JTextField;

import net.sf.jcgm.drawer.DrawType;
import net.sf.jcgm.event.EventDispatcher;
import net.sf.jcgm.event.SweepArrowEventParameter;



public class PropertySweepArrow extends BasePanel{
 
	
	private JTextField textFieldwidth;
	private double widthSize = 0.2; // default :0.0
	private double headRatio = 0.33;
	private int units = 2;// default :points
	private Color lineColor = Color.BLACK; //default: Black
	private JPanel panelColorChooser;
	private JTextField textFieldHeadRatio;
	
	public PropertySweepArrow(DrawType drawType){
		super(drawType);
		setBorder(null);
		setPreferredSize(new Dimension(624, 33));
		setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
		
		JLabel lblSweepArrow = new JLabel("Sweep Arrow");
		add(lblSweepArrow);
		
		textFieldwidth = new JTextField("0.2");
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
		textFieldwidth.setToolTipText("Arrow Size");
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
						widthSize*= 4.0; 
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
		
		JLabel lblHeadRatio = new JLabel("Head Ratio");
		add(lblHeadRatio);
		
		
		textFieldHeadRatio = new JTextField("0.33");
		textFieldHeadRatio.getDocument().addDocumentListener(new DocumentListener() {		
			@Override
			public void removeUpdate(DocumentEvent e) {
				if(!textFieldHeadRatio.getText().isEmpty()){
					headRatio = Double.parseDouble(textFieldHeadRatio.getText().trim());
					fireEvent();
				}
			}
			
			@Override
			public void insertUpdate(DocumentEvent e) {
				if(!textFieldHeadRatio.getText().isEmpty()){
					headRatio = Double.parseDouble(textFieldHeadRatio.getText().trim());
					fireEvent();
				}
			}
			
			@Override
			public void changedUpdate(DocumentEvent e) {
				if(!textFieldHeadRatio.getText().isEmpty()){
					headRatio = Double.parseDouble(textFieldHeadRatio.getText().trim());
					fireEvent();
				}
			}
		});
		add(textFieldHeadRatio);
		textFieldHeadRatio.setColumns(10);
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
				buttonPanel.add(buttonCancel);
				add(buttonPanel,BorderLayout.SOUTH);
				setVisible(true);
			}
		}
	 @Override
	public void fireEvent() {
		super.fireEvent();
		 EventDispatcher eventDispatcher = getEventDispatcher();
		 SweepArrowEventParameter sweepArrowEventParameter = new SweepArrowEventParameter();
		 sweepArrowEventParameter.setArrowWidth(widthSize);
		 sweepArrowEventParameter.setHeadRatio(headRatio);
		 sweepArrowEventParameter.setLineColor(lineColor);
		 if(eventDispatcher==null){ return; }
		 eventDispatcher.fireEvent(sweepArrowEventParameter);
	}
}
