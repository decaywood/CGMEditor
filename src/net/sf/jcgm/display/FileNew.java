package net.sf.jcgm.display;


import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JToggleButton;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.filechooser.FileNameExtensionFilter;

import net.sf.jcgm.controller.CGMMenuController;
import net.sf.jcgm.processor.CGMMenuProcessor;
import net.sf.jcgm.properties.FileNewProp;

public class FileNew extends JDialog{
	private static final int screenWidth=(int)java.awt.Toolkit.getDefaultToolkit().getScreenSize().width*65535/1280;
	private static final int screenHeight = (int)java.awt.Toolkit.getDefaultToolkit().getScreenSize().height*65535/800;
	private JPanel imageJPanel;
	private JPanel cgmProfileJpanel;
	private JPanel fileNameJPanel;
	private JComboBox image_jComboBox;
	private JTextArea image_jTextArea1 ;
	private JTextArea image_jTextArea2 ;
	private JTextArea fileName_jTextArea ;
	private JList image_JList ;
	private JLabel image_jLabel1;
	private JLabel image_jLabel2;
	private JComboBox cgmProfile_jComboBox;
	private DefaultListModel listModel1;
	private DefaultListModel listModel2;
	private JCheckBox fileName_jCheckBox;
	private JButton fileName_btn;
	private JButton btn_ok;
	private JButton btn_cancel;
	private double imageUnitSize ;
	private String imageLengthHeight;
	private String cgmProfile;
	private String fileName;
	public FileNewProp fileNewProp;
	private boolean setDefaultFileName;
	public FileNew(int x,int y,JFrame frame){
		super(frame,true);
		setBounds(x*screenWidth/65535, y*screenHeight/65535, 270*screenWidth/65535, 600*screenHeight/65535);
		setLayout(null);
		setTitle("New");
		imageJPanel = new JPanel();
		imageJPanel.setBounds(5*screenWidth/65535, 5*screenHeight/65535, 240*screenWidth/65535, 350*screenHeight/65535);
		imageJPanel.setLayout(null);
		imageJPanel.setBorder(BorderFactory.createTitledBorder("Image"));
		image_jComboBox = new JComboBox();
		image_jComboBox.setBounds(10*screenWidth/65535, 20*screenHeight/65535, 130*screenWidth/65535, 20*screenHeight/65535);
		image_jTextArea1 = new JTextArea("1000");
		imageUnitSize = 1000 ;
		image_jTextArea1.getDocument().addDocumentListener(new DocumentListener() {
			
			@Override
			public void removeUpdate(DocumentEvent e) {
				if(!image_jTextArea1.getText().isEmpty()){
					imageUnitSize = Double.parseDouble(image_jTextArea1.getText().trim());
				}
			}
			
			@Override
			public void insertUpdate(DocumentEvent e) {
				if(!image_jTextArea1.getText().isEmpty()){
					imageUnitSize = Double.parseDouble(image_jTextArea1.getText().trim());
				}
			}
			
			@Override
			public void changedUpdate(DocumentEvent e) {
				if(!image_jTextArea1.getText().isEmpty()){
					imageUnitSize = Double.parseDouble(image_jTextArea1.getText().trim());
				}
			}
		});
		image_jTextArea1.setBounds(150*screenWidth/65535, 20*screenHeight/65535, 80*screenWidth/65535, 20*screenHeight/65535);
		image_jTextArea1.setBorder(BorderFactory.createLineBorder(Color.gray));
		image_jComboBox.addItem("VDCs per Inch");
		image_jComboBox.addItem("VDCs per Millimeter");
		image_jComboBox.addItemListener(new ItemListener() {	
			@Override
			public void itemStateChanged(ItemEvent e) {
				if(((String)e.getItem()).equalsIgnoreCase("VDCs per Inch"))
					image_JList.setModel(listModel2);
				else if(((String)e.getItem()).equalsIgnoreCase("VDCs per Millimeter"))
					image_JList.setModel(listModel1);
			}
		});
		image_jLabel1 = new JLabel("Image Size Choices length x height");
		image_jLabel1.setBounds(10*screenWidth/65535,45*screenHeight/65535,200*screenWidth/65535,20*screenHeight/65535);
		listModel1 = new DefaultListModel();
		listModel1.addElement("198 x 285 A4");
		listModel1.addElement("285 x 198 A4 Rotated");
		listModel1.addElement("273 x 396 A3");
		listModel1.addElement("396 x 273 A3 Rotated");
		listModel1.addElement("396 x 570 A2");
		listModel1.addElement("570 x 396 A2 Rotated");
		listModel1.addElement("570 x 817 A1");
		listModel1.addElement("817 x 570 A1 Rotated");
		listModel1.addElement("817 x 1165 A0");
		listModel1.addElement("1165 x 817 A0 Rotated");
		listModel2 = new DefaultListModel();
		listModel2.addElement("8.0 x 10.5 Letter (ANSI A)");
		listModel2.addElement("10.5 x 8.0 Letter (ANSI A) Rotated");
		listModel2.addElement("10.0 x 16.0 Tabloid (ANSI B)");
		listModel2.addElement("16.0 x 10.0 Tabloid (ANSI B) Rotated");
		listModel2.addElement("16.0 x 21.0 ANSI C");
		listModel2.addElement("21.0 x 16.0 ANSI C Rotated");
		listModel2.addElement("21.0 x 33.0 ANSI D");
		listModel2.addElement("33.0 x 21.0 ANSI D Rotated");
		listModel2.addElement("33.0 x 43.0 ANSI E");
		listModel2.addElement("43.0 x 33.0 ANSI E Rotated");
		image_JList = new JList(listModel2);
		image_JList.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				super.mouseClicked(e);
				image_jTextArea2.setText((String) image_JList.getSelectedValue());
				imageLengthHeight = image_jTextArea2.getText();
			}
		});
		image_JList.setBounds(10*screenWidth/65535, 70*screenHeight/65535, 220*screenWidth/65535, 220*screenHeight/65535);
		image_JList.setBorder(BorderFactory.createLineBorder(Color.gray));
		image_jLabel2 = new JLabel("Image Size Selected or Entered");
		image_jLabel2.setBounds(10*screenWidth/65535, 295*screenHeight/65535, 200*screenWidth/65535, 20*screenHeight/65535);
		image_jTextArea2 = new JTextArea();
		image_jTextArea2.setBounds(10*screenWidth/65535, 320*screenHeight/65535, 220*screenWidth/65535, 20*screenHeight/65535);
		image_jTextArea2.setBorder(BorderFactory.createLineBorder(Color.gray));
		imageJPanel.add(image_jComboBox);
		imageJPanel.add(image_jTextArea1);
		imageJPanel.add(image_jLabel1);
		imageJPanel.add(image_JList);
		imageJPanel.add(image_jLabel2);
		imageJPanel.add(image_jTextArea2);
		cgmProfileJpanel = new JPanel();
		cgmProfileJpanel.setBounds(5*screenWidth/65535, 355*screenHeight/65535, 240*screenWidth/65535, 50*screenHeight/65535);
		cgmProfileJpanel.setLayout(null);
		cgmProfileJpanel.setBorder(BorderFactory.createTitledBorder("CGM Profile"));
		cgmProfile_jComboBox = new JComboBox();
		cgmProfile_jComboBox.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				if(e.getStateChange()==ItemEvent.SELECTED){
					cgmProfile = (String)e.getItem();
				}
			}
		});
		cgmProfile_jComboBox.setBounds(10*screenWidth/65535, 20*screenHeight/65535, 220*screenWidth/65535, 20*screenHeight/65535);
		cgmProfile_jComboBox.addItem("ATA");
		cgmProfile_jComboBox.addItem("CALS");
		cgmProfile_jComboBox.addItem("CGM+");
		cgmProfile_jComboBox.addItem("PIP");
		cgmProfile_jComboBox.addItem("WebCGM");
		cgmProfile_jComboBox.addItem("None");
		cgmProfileJpanel.add(cgmProfile_jComboBox);
		fileNameJPanel = new JPanel();
		fileNameJPanel.setBounds(5*screenWidth/65535, 405*screenHeight/65535, 240*screenWidth/65535, 75*screenHeight/65535);
		fileNameJPanel.setLayout(null);
		fileNameJPanel.setBorder(BorderFactory.createTitledBorder("File Name"));
		fileName_jTextArea = new JTextArea();
		fileName_jTextArea.setBounds(10*screenWidth/65535, 20*screenHeight/65535, 220*screenWidth/65535, 20*screenHeight/65535);
		fileName_jTextArea.setBorder(BorderFactory.createLineBorder(Color.gray));
		fileName_jCheckBox = new JCheckBox("Set Default File Name");
		fileName_jCheckBox.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				if(fileName_jCheckBox.isSelected()){
					setDefaultFileName = true;
				}
				else{
					setDefaultFileName = false;
				}
			}
		});
		fileName_jCheckBox.setBounds(5*screenWidth/65535, 45*screenHeight/65535, 150*screenWidth/65535, 20*screenHeight/65535);
		fileName_btn = new JButton();
		fileName_btn.setBounds(160*screenWidth/65535, 45*screenHeight/65535, 70*screenWidth/65535, 20*screenHeight/65535);
		fileName_btn.setText("浏览");
		fileName_btn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JFileChooser fileChooser = new JFileChooser();
				fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
				fileChooser.setDialogTitle("New");
				int result = fileChooser.showSaveDialog(fileNameJPanel);
				 if (result == JFileChooser.APPROVE_OPTION){
					 fileName_jTextArea.setText(fileChooser.getSelectedFile().getAbsolutePath());
					 if(setDefaultFileName)
						 fileName = fileName_jTextArea.getText();
					 else 
						 fileName = null;
				 }
				 else 
					 fileName_jTextArea.setText(null);
					
			}
		});
		fileNameJPanel.add(fileName_jTextArea);
		fileNameJPanel.add(fileName_jCheckBox);
		fileNameJPanel.add(fileName_btn);
		btn_ok = new JButton("确定");
		btn_ok.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				fileNewProp = new FileNewProp();
				fileNewProp.setCgmProfile(cgmProfile);
				fileNewProp.setFileName(fileName_jTextArea.getText());
				if(fileNewProp.getFileName().equals(""))
					fileNewProp.setFileName("untitled");
				if(imageLengthHeight!=null)
					fileNewProp.setImageLengthHeight(imageLengthHeight);
				fileNewProp.setImageUnitSize(imageUnitSize);
				FileNew.this.setVisible(false);
				//cgmControler.newFile(fileNewProp.getWidth(), fileNewProp.getHeight(),fileNewProp.getImageUnitSize());
			}
		});
		btn_ok.setBounds(8*screenWidth/65535, 485*screenHeight/65535, 70*screenWidth/65535, 20*screenHeight/65535);
		btn_cancel = new JButton("取消");
		btn_cancel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				 FileNew.this.dispose();
			}
		});
		
		btn_cancel.setBounds(170*screenWidth/65535, 485*screenHeight/65535, 70*screenWidth/65535, 20*screenHeight/65535);
		add(btn_ok);
		add(btn_cancel);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.add(imageJPanel);
		this.add(cgmProfileJpanel);
		this.add(fileNameJPanel);
		this.setVisible(true);
		this.setResizable(false);
	}
	public FileNewProp getFileNewProp()
	{
		return this.fileNewProp;
	}
}
