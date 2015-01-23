package net.sf.jcgm.display;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JPanel;

import net.sf.jcgm.properties.SaveSetAsDefaultsProp;

public class SaveSetAsDefaults extends JFrame{
	private JCheckBox chckbxGlobalUnits;
	private JCheckBox chckbxBackGroundColor;
	private JCheckBox chckbxFitToWindow;
	private JCheckBox chckbxOverViewWindow;
	private JButton btnSave;
	private JButton btnCancel;
	private SaveSetAsDefaultsProp properties;
	private boolean saveCurSetGlobUnits;
	private boolean saveCurSetBackGroundColor;
	private boolean saveCurSetFitToWindow;
	private boolean saveCurSetOverViewWindow;
	private static final int screenWidth=(int)java.awt.Toolkit.getDefaultToolkit().getScreenSize().width*65535/1280;
	private static final int screenHeight = (int)java.awt.Toolkit.getDefaultToolkit().getScreenSize().height*65535/800;
	
	public SaveSetAsDefaults(int x,int y) {
		setBounds(x*screenWidth/65535 , y*screenHeight/65535 , 189*screenWidth/65535 , 200*screenHeight/65535  );
		getContentPane().setLayout(null);
		setVisible(true);
		setResizable(false);
		setTitle("Save as Default");
		properties = new SaveSetAsDefaultsProp();
		chckbxGlobalUnits = new JCheckBox("Global Units");
		chckbxGlobalUnits.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				if(chckbxGlobalUnits.isSelected()){
					saveCurSetGlobUnits = true;
				}
				else {
					saveCurSetGlobUnits = false;
				}
					
			}
		});
		chckbxGlobalUnits.setBounds(24*screenWidth/65535, 17*screenHeight/65535, 103*screenWidth/65535, 23*screenHeight/65535);
		getContentPane().add(chckbxGlobalUnits);
		
		chckbxBackGroundColor = new JCheckBox("Background Color");
		chckbxBackGroundColor.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				if(chckbxBackGroundColor.isSelected()){
					saveCurSetBackGroundColor = true;
				}
				else {
					saveCurSetBackGroundColor = false;
				}
			}
		});
		chckbxBackGroundColor.setBounds(24*screenWidth/65535, 42*screenHeight/65535, 137*screenWidth/65535, 23*screenHeight/65535);
		getContentPane().add(chckbxBackGroundColor);
		
		chckbxFitToWindow = new JCheckBox("Fit to Window");
		chckbxFitToWindow.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				if(chckbxFitToWindow.isSelected()){
					saveCurSetFitToWindow = true;
				}
				else {
					saveCurSetFitToWindow = false;
				}
			}
		});
		chckbxFitToWindow.setBounds(24*screenWidth/65535, 67*screenHeight/65535, 103*screenWidth/65535, 23*screenHeight/65535);
		getContentPane().add(chckbxFitToWindow);
		
		chckbxOverViewWindow = new JCheckBox("OverView Window");
		chckbxOverViewWindow.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				if(chckbxOverViewWindow.isSelected()){
					saveCurSetOverViewWindow = true;
				}
				else {
					saveCurSetOverViewWindow = false;
				}
			}
		});
		chckbxOverViewWindow.setBounds(24*screenWidth/65535, 92*screenHeight/65535, 137*screenWidth/65535, 23*screenHeight/65535);
		getContentPane().add(chckbxOverViewWindow);
		
		btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				properties.setSaveCurSetBackGroundColor(saveCurSetBackGroundColor);
				properties.setSaveCurSetFitToWindow(saveCurSetFitToWindow);
				properties.setSaveCurSetGlobUnits(saveCurSetGlobUnits);
				properties.setSaveCurSetOverViewWindow(saveCurSetOverViewWindow);
				SaveSetAsDefaults.this.dispose();
			}
		});
		btnSave.setBounds(10, 138, 73, 23);
		getContentPane().add(btnSave);
		
		btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				SaveSetAsDefaults.this.dispose();
			}
		});
		btnCancel.setBounds(89, 138, 83, 23);
		getContentPane().add(btnCancel);
	
	}	
}
