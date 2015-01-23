package net.sf.jcgm.display;

import java.awt.BorderLayout;
import java.awt.Button;
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
import java.security.Timestamp;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JColorChooser;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import net.sf.jcgm.properties.OptionDrawOptionsProp;

import org.omg.CORBA.PRIVATE_MEMBER;

public class OptionDrawOptions extends JFrame {
	 private JTabbedPane jTabbedPane ;
	 private JButton buttonOk;
	 private JButton buttonCancel;
	 private SubDrawOptions subDrawOptions;
	 private static final int screenWidth=(int)(java.awt.Toolkit.getDefaultToolkit().getScreenSize().width*65535)/1280;
	 private static final int  screenHeight = (int)(java.awt.Toolkit.getDefaultToolkit().getScreenSize().height*65535)/800;
	 
	 private String generalColor ;
	 private String generalMDS ; 
	 private String textColorChooserColor;
	 private String textbgColorChooserColor;
	 private String textCapHeight;
	 private String textFontSize;
	 private String textFont;
	 private String textAlignment;
	 private Object[] textSelectedFonts;
	 private boolean textIsBold;
	 private boolean textIsItalic;
	 private String lineSize;
	 private String lineFontSize;
	 private String lineColor;
	 private String lineHeadRatio;
	 private String lineType;
	 private String solidEdgeSize;
	 private String solidEdgeColor;
	 private String solidFillColor;
	 private String solidEdgeType;
	 private String solidIterior;
	 private boolean solidIsEdge;
	 private String symbolSize;
	
	
	 
	 public class General extends JPanel{
		 private JComboBox jComboBox;
		 private JTextArea jTextArea;
		 private JLabel jLabel1;
		 private JLabel jLabel2;
	
		 public General(){
			 setLayout(null);
			 jComboBox = new JComboBox();
			 jComboBox.addItem("Use Standard Color");
			 jComboBox.addItem("Black and White Only");
			 jComboBox.addItemListener(new ItemListener() {
				@Override
				public void itemStateChanged(ItemEvent e) {
					if(e.getStateChange()==ItemEvent.SELECTED){
						generalColor = (String)e.getItem();
					}
				}
			});
			 jTextArea = new JTextArea();
			 jTextArea.getDocument().addDocumentListener(new DocumentListener() {
					
					@Override
					public void removeUpdate(DocumentEvent e) {
						generalMDS = jTextArea.getText().trim(); // TODO Auto-generated method stub
					}
					
					@Override
					public void insertUpdate(DocumentEvent e) {
						generalMDS = jTextArea.getText().trim();
					}
					
					@Override
					public void changedUpdate(DocumentEvent e) {
						generalMDS = jTextArea.getText().trim();
					}
				});
			 jLabel1 = new JLabel("Color");
			 jLabel1.setBounds(20*screenWidth/65535, 80*screenHeight/65535, 50*screenWidth/65535, 20*screenHeight/65535);
			 jComboBox.setBounds(80*screenWidth/65535, 80*screenHeight/65535, 150*screenWidth/65535, 20*screenHeight/65535);
			 jLabel2 = new JLabel("MDS");
			 jLabel2.setBounds(20*screenWidth/65535,110*screenHeight/65535, 50*screenWidth/65535, 20*screenHeight/65535);
			 jTextArea = new JTextArea();
			 jTextArea.setBounds(80*screenWidth/65535, 110*screenHeight/65535, 150*screenWidth/65535, 20*screenHeight/65535);
			 jTextArea.setBorder(BorderFactory.createLineBorder(Color.gray));
			 this.add(jLabel1);
			 this.add(jLabel2);
			 this.add(jComboBox);
			 this.add(jTextArea);
		 }
	 }
	 public class Text extends JPanel{
		private JLabel lblCapHeight;
		private JLabel lblTextColor;
		private JLabel lblMmFontSize;
		private JLabel lblAlignment;
		private JLabel lblTextFont;
		private JTextField jTextFieldFontSize;
		private JTextField jTextFieldCapHeight;
		private JPanel panelTextColor;
		private JPanel panelBgColor;
		private JLabel lblBgColor;
		private JCheckBox checkBoxColor;
		private JComboBox comboBox;
		private JComboBox comboBox_1;
		private JCheckBox checkBoxBold;
		private JCheckBox chckbxItalic;
		
		private class ColorChooserPanel extends JFrame{
			
			private JColorChooser jColorChooser;
			private JButton buttonOk;
			private JButton buttonCancel;
			private Object object;
			public ColorChooserPanel(int x,int y,Object object) {
				this.object = object;
				setBounds(x*screenWidth/65535, y*screenHeight/65535, 460*screenWidth/65535, 320*screenHeight/65535);
				setLayout(null);
				jColorChooser=new JColorChooser();
				jColorChooser.setBounds(0, 0, 439*screenWidth/65535, 247*screenHeight/65535);
				add(jColorChooser);
				buttonOk = new JButton("OK");
				buttonOk.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						if(ColorChooserPanel.this.object.equals(panelTextColor)){
							textColorChooserColor = jColorChooser.getColor().toString();
							panelTextColor.setBackground(jColorChooser.getColor());
							ColorChooserPanel.this.dispose();
						}
						else  {
							textbgColorChooserColor = jColorChooser.getColor().toString();
							panelBgColor.setBackground(jColorChooser.getColor());
							ColorChooserPanel.this.dispose();
						}
					}
				});
				buttonOk.setBounds(107*screenWidth/65535, 253*screenHeight/65535, 76*screenWidth/65535, 23*screenHeight/65535);
				add(buttonOk);
				
				buttonCancel = new JButton("Cancel");
				buttonCancel.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						ColorChooserPanel.this.dispose();
					}
				});
				buttonCancel.setBounds(216*screenWidth/65535, 253*screenHeight/65535, 76*screenWidth/65535, 23*screenHeight/65535);
				add(buttonCancel);
				setVisible(true);
			}
		}
		
		 public Text(){
			
				setLayout(null);
				
				lblCapHeight = new JLabel("Cap Height");
				lblCapHeight.setBounds(10*screenWidth/65535, 21*screenHeight/65535, 80*screenWidth/65535, 20*screenHeight/65535);
				add(lblCapHeight);
				
				lblTextColor = new JLabel("Text Color");
				lblTextColor.setBounds(10*screenWidth/65535, 67*screenHeight/65535, 80*screenWidth/65535, 20*screenHeight/65535);
				add(lblTextColor);
				
				lblTextFont = new JLabel("Text Font");
				lblTextFont.setBounds(10*screenWidth/65535, 111*screenHeight/65535, 80*screenWidth/65535, 20*screenHeight/65535);
				add(lblTextFont);
				
				lblMmFontSize = new JLabel("mm Font Size");
				lblMmFontSize.setBounds(171*screenWidth/65535, 21*screenHeight/65535, 80*screenWidth/65535, 20*screenHeight/65535);
				add(lblMmFontSize);
				
				lblAlignment = new JLabel("Alignment");
				lblAlignment.setBounds(10*screenWidth/65535, 155*screenHeight/65535, 80*screenWidth/65535, 20*screenHeight/65535);
				add(lblAlignment);
				
				jTextFieldFontSize = new JTextField();
				jTextFieldFontSize.getDocument().addDocumentListener(new DocumentListener() {
					
					@Override
					public void removeUpdate(DocumentEvent e) {
						textFontSize = jTextFieldFontSize.getText().trim(); // TODO Auto-generated method stub
					}
					
					@Override
					public void insertUpdate(DocumentEvent e) {
						textFontSize = jTextFieldFontSize.getText().trim();
					}
					
					@Override
					public void changedUpdate(DocumentEvent e) {
						textFontSize = jTextFieldFontSize.getText().trim();
					}
				});
				jTextFieldFontSize.setBounds(248*screenWidth/65535, 21*screenHeight/65535, 40*screenWidth/65535, 21*screenHeight/65535);
				add(jTextFieldFontSize);
				jTextFieldFontSize.setColumns(10);
				
				jTextFieldCapHeight = new JTextField();
				jTextFieldCapHeight.getDocument().addDocumentListener(new DocumentListener() {
					
					@Override
					public void removeUpdate(DocumentEvent e) {
						textCapHeight =jTextFieldCapHeight.getText().trim(); // TODO Auto-generated method stub
					}
					
					@Override
					public void insertUpdate(DocumentEvent e) {
						textCapHeight =jTextFieldCapHeight.getText().trim();
					}
					
					@Override
					public void changedUpdate(DocumentEvent e) {
						textCapHeight =jTextFieldCapHeight.getText().trim();
					}
				});
				jTextFieldCapHeight.setColumns(10);
				jTextFieldCapHeight.setBounds(77*screenWidth/65535, 21*screenHeight/65535, 72*screenWidth/65535, 21*screenHeight/65535);
				add(jTextFieldCapHeight);
				
				panelTextColor = new JPanel();
				panelTextColor.setBackground(Color.BLACK);
				panelTextColor.addMouseListener(new MouseAdapter() {  
					@Override
					public void mouseClicked(MouseEvent e) {
						super.mouseClicked(e);
						new ColorChooserPanel(getX()+e.getX(),getY()+e.getY(),e.getSource());
					}
				});
				panelTextColor.setBounds(77*screenWidth/65535, 67*screenHeight/65535, 72*screenWidth/65535, 20*screenHeight/65535);
				add(panelTextColor);
				lblBgColor = new JLabel("bg Color");
				lblBgColor.setBounds(171*screenWidth/65535, 70*screenHeight/65535, 50*screenWidth/65535, 15*screenHeight/65535);
				add(lblBgColor);
				
				panelBgColor = new JPanel();
				panelBgColor.addMouseListener(new MouseAdapter() { 
					@Override
					public void mouseClicked(MouseEvent e) {
						super.mouseClicked(e);
						new ColorChooserPanel(getX()+e.getX(),getY()+e.getY(),e.getSource());
					}
				});
				panelBgColor.setBackground(Color.BLACK);
				panelBgColor.setBounds(248*screenWidth/65535, 67*screenHeight/65535, 40*screenWidth/65535, 20*screenHeight/65535);
				
				checkBoxColor = new JCheckBox("");
				checkBoxColor.addItemListener(new ItemListener() {
					@Override
					public void itemStateChanged(ItemEvent e) {
						if(checkBoxColor.isSelected()){
							panelBgColor.setVisible(true);
							add(panelBgColor);
							repaint();
						}
						else if(!checkBoxColor.isSelected()){
							panelBgColor.setVisible(false);
							remove(panelBgColor);
						}
							
					}
				});
				checkBoxColor.setBounds(227*screenWidth/65535, 67*screenHeight/65535, 20*screenWidth/65535, 23*screenHeight/65535);
				add(checkBoxColor);
				
				comboBox = new JComboBox();
				comboBox.addItem("Times Roman");
				comboBox.addItem("Helvetica");
				comboBox.addItem("Courier");
				comboBox.addItem("Symbol");
				textFont ="Times Roman";
				comboBox.addItemListener(new ItemListener() {
					@Override
					public void itemStateChanged(ItemEvent e) {
						if(e.getStateChange()==ItemEvent.SELECTED){
							textFont = (String)e.getItem();
						}
					}
				});
				comboBox.setBounds(77*screenWidth/65535, 111*screenHeight/65535, 148*screenWidth/65535, 21*screenHeight/65535);
				add(comboBox);
				
				comboBox_1 = new JComboBox();
				comboBox_1.addItem("Left");
				comboBox_1.addItem("Center");
				comboBox_1.addItem("Right");
				comboBox_1.addItemListener(new ItemListener() {
					@Override
					public void itemStateChanged(ItemEvent e) {
						if(e.getStateChange()==ItemEvent.SELECTED){
							textAlignment = (String)e.getItem();
						}
					}
				});
				comboBox_1.setBounds(77*screenWidth/65535, 155*screenHeight/65535, 148*screenWidth/65535, 21*screenHeight/65535);
				add(comboBox_1);
				
				checkBoxBold = new JCheckBox("Bold");
				checkBoxBold.addItemListener(new ItemListener() {
					@Override
					public void itemStateChanged(ItemEvent e) {
						if(checkBoxBold.isSelected())
							textIsBold = true;
						else
							textIsBold = false;
					}
				});
				checkBoxBold.setBounds(77*screenWidth/65535, 182*screenHeight/65535, 54*screenWidth/65535, 23*screenHeight/65535);
				add(checkBoxBold);
				
				chckbxItalic = new JCheckBox("Italic");
				chckbxItalic.addItemListener(new ItemListener() {
					@Override
					public void itemStateChanged(ItemEvent e) {
						if(chckbxItalic.isSelected())
							textIsItalic = true;
						else
							textIsItalic = false;
					}
				});
				chckbxItalic.setBounds(164*screenWidth/65535, 182*screenHeight/65535, 61*screenWidth/65535, 23*screenHeight/65535);
				add(chckbxItalic);
			
			
		 }
			
	 }
	 public class SubDrawOptions extends JPanel{
		 private JLabel jLabelAvailableFonts;
		 private JLabel jLabelSelectedFonts;
		 private JLabel jLabelSearchFont;
		 private JList jListFont;
		 private JList jListBlanker;
		 private JButton jButtonAdd;
		 private JButton jButtonRemove;
		 private JScrollPane jScrollPaneJlistFont;
		 private JScrollPane jScrollPaneJlistBlanker;
		 private DefaultListModel listModel;
		 private DefaultListModel listModelBlank;
		 private JTextField jTextField ;
		 private int size;
		 private int count;
		 public SubDrawOptions(){
			 count = 0;
			 setLayout(null);
			 jLabelAvailableFonts = new JLabel("Available Fonts");
			 jLabelAvailableFonts.setBounds(25*screenWidth/65535,10*screenHeight/65535,90*screenWidth/65535,20*screenHeight/65535);
			 listModel = new DefaultListModel();
			 
				 listModel.addElement("aestfont");
				 listModel.addElement("abstfont");
				 listModel.addElement("acstfont");
				 listModel.addElement("tecbtfont");
				 listModel.addElement("teasdasbtfont");
				 listModel.addElement("taegsfont");
				 listModel.addElement("tasegasegbtfont");
				 listModel.addElement("easegont");
				 listModel.addElement("febtfont");
				 listModel.addElement("cebtfont");
			 
			 jLabelSelectedFonts = new JLabel("Selected Fonts");
			 jListFont = new JList(listModel);
			 listModelBlank = new DefaultListModel();
			 jListBlanker = new JList(listModelBlank);
			 jScrollPaneJlistBlanker = new JScrollPane(jListBlanker,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
			 jScrollPaneJlistFont = new JScrollPane(jListFont,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
			 jScrollPaneJlistFont.setBounds(25*screenWidth/65535, 35*screenHeight/65535, 160*screenWidth/65535, 240*screenHeight/65535);
			 jScrollPaneJlistBlanker.setBounds(200*screenWidth/65535, 35, 160*screenWidth/65535, 240);
			 jLabelSelectedFonts.setBounds(220*screenWidth/65535, 10*screenHeight/65535, 90*screenWidth/65535, 20*screenHeight/65535);
			 jLabelSearchFont = new JLabel("Search Font");
			 jLabelSearchFont.setBounds(25*screenWidth/65535, 280*screenHeight/65535, 90*screenWidth/65535, 20*screenHeight/65535);
			 jTextField = new JTextField();
			 jTextField.setBounds(25*screenWidth/65535, 310*screenHeight/65535, 160*screenWidth/65535, 20*screenHeight/65535);
			 jTextField.setBorder(BorderFactory.createLineBorder(Color.gray));
			 jTextField.getDocument().addDocumentListener(new DocumentListener() {
				
				@Override
				public void removeUpdate(DocumentEvent e) {
					count = 0;
					
				}
				
				@Override
				public void insertUpdate(DocumentEvent e) {
					size = listModel.getSize();
					for(int i = 0 ; i<size ; i++){
						count = jTextField.getText().length();
						if(jTextField.getText().equalsIgnoreCase(((String)listModel.getElementAt(i)).substring(0, count))){
							jListFont.setSelectedIndex(i);
							break;
						}
					}
				}
				
				@Override
				public void changedUpdate(DocumentEvent e) {
					size = listModel.getSize();
					for(int i = 0 ; i<size ; i++){
						count = jTextField.getText().length();
						if(jTextField.getText().equalsIgnoreCase(((String)listModel.getElementAt(i)).substring(0, count+1))){
							jListFont.setSelectedIndex(i);
							break;
						}
					}
				}
			});
			 jButtonAdd = new JButton("Add");
			 jButtonAdd.addActionListener(new ActionListener() {
				@Override
				
				public void actionPerformed(ActionEvent e) {
					for(Object name: jListFont.getSelectedValues()){
						if(!listModelBlank.contains(name)){
							listModelBlank.addElement(name);
						}
					}
					textSelectedFonts = jListBlanker.getSelectedValues();
					
				}
			});
			 jButtonRemove = new JButton("Remove");
			 jButtonRemove.addActionListener(new ActionListener() {
					@Override
					
				public void actionPerformed(ActionEvent e) {
					for(Object name:jListBlanker.getSelectedValues()){
						listModelBlank.removeElement(name);
					}
					textSelectedFonts = jListBlanker.getSelectedValues();
				}
			});
			 jButtonAdd.setEnabled(false);
			 jButtonRemove.setEnabled(false);
			 jListFont.addFocusListener(new FocusAdapter() {
				 @Override
				public void focusGained(FocusEvent e) {
					super.focusGained(e);
					if(!jListFont.isSelectionEmpty())
						jButtonAdd.setEnabled(true);
				}
			});
			 jListBlanker.addFocusListener(new FocusAdapter() {
				@Override
				public void focusGained(FocusEvent e) {
					super.focusGained(e);
					if(!jListBlanker.isSelectionEmpty())
						jButtonRemove.setEnabled(true);
				}
			 });
			 jListFont.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					super.mouseClicked(e);
					if(e.getClickCount()==2)
						if(!listModelBlank.contains(jListFont.getSelectedValue()))
							listModelBlank.addElement(jListFont.getSelectedValue());
				} 
			});
			 jButtonAdd.setBounds(200*screenWidth/65535, 310*screenHeight/65535, 85*screenWidth/65535, 20*screenHeight/65535);
			 jButtonRemove.setBounds(290*screenWidth/65535, 310*screenHeight/65535, 85*screenWidth/65535, 20*screenHeight/65535);
			 this.add(jButtonAdd);
			 this.add(jButtonRemove);
			 this.add(jLabelSelectedFonts);
			 this.add(jLabelAvailableFonts);
			 this.add(jScrollPaneJlistFont);
			 this.add(jScrollPaneJlistBlanker);
			 this.add(jLabelSearchFont);
			 this.add(jTextField);
		 }
	 }
	 public class Line extends JPanel{
		private JTextField jTextFieldFontSize;
		private JTextField jTextFieldLineSize;
		private JLabel lblLineSize;
		private JTextField textFieldHeadRatio;
		private JLabel lblLineType;
		private JLabel lblLineColor;
		private JLabel lblMmFontSize;
		private JPanel panelLineColor;
		private JLabel lblHeadRatio;
		private JComboBox comboBox;
		private JLabel lblMm;
private class ColorChooserPanel extends JFrame{
			
			private JColorChooser jColorChooser;
			private JButton buttonOk;
			private JButton buttonCancel;
			public ColorChooserPanel(int x,int y) {
				setBounds(x*screenWidth/65535, y*screenHeight/65535, 460*screenWidth/65535, 320*screenHeight/65535);
				setLayout(null);
				jColorChooser=new JColorChooser();
				jColorChooser.setBounds(0, 0, 439*screenWidth/65535, 247*screenHeight/65535);
				add(jColorChooser);
				buttonOk = new JButton("OK");
				buttonOk.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
					 
							lineColor = jColorChooser.getColor().toString();
							panelLineColor.setBackground(jColorChooser.getColor());
							ColorChooserPanel.this.dispose();
					 
					}
				});
				buttonOk.setBounds(107*screenWidth/65535, 253*screenHeight/65535, 76*screenWidth/65535, 23*screenHeight/65535);
				add(buttonOk);
				
				buttonCancel = new JButton("Cancel");
				buttonCancel.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						ColorChooserPanel.this.dispose();
					}
				});
				buttonCancel.setBounds(216*screenWidth/65535, 253*screenHeight/65535, 76*screenWidth/65535, 23*screenHeight/65535);
				add(buttonCancel);
				setVisible(true);
			}
		}
		public Line() {
				setBounds(200*screenWidth/65535, 310*screenHeight/65535, 374*screenWidth/65535, 302*screenHeight/65535);
				setLayout(null);
				
				lblLineSize = new JLabel("Line Size");
				lblLineSize.setBounds(10*screenWidth/65535, 21*screenHeight/65535, 80*screenWidth/65535, 20*screenHeight/65535);
				add(lblLineSize);
				
				lblLineColor = new JLabel("Line Color");
				lblLineColor.setBounds(10*screenWidth/65535, 67*screenHeight/65535, 80*screenWidth/65535, 20*screenHeight/65535);
				add(lblLineColor);
				
				lblLineType = new JLabel("Line Type");
				lblLineType.setBounds(10*screenWidth/65535, 111*screenHeight/65535, 80*screenWidth/65535, 20*screenHeight/65535);
				add(lblLineType);
				
				lblMmFontSize = new JLabel("mm Font Size");
				lblMmFontSize.setBounds(171*screenWidth/65535, 21*screenHeight/65535, 80*screenWidth/65535, 20*screenHeight/65535);
				add(lblMmFontSize);
				
				jTextFieldFontSize = new JTextField();
				jTextFieldFontSize.getDocument().addDocumentListener(new DocumentListener() {
					
					@Override
					public void removeUpdate(DocumentEvent e) {
						lineFontSize = jTextFieldFontSize.getText().trim(); // TODO Auto-generated method stub
					}
					
					@Override
					public void insertUpdate(DocumentEvent e) {
						lineFontSize = jTextFieldFontSize.getText().trim();
					}
					
					@Override
					public void changedUpdate(DocumentEvent e) {
						lineFontSize = jTextFieldFontSize.getText().trim();
					}
				});
				jTextFieldFontSize.setBounds(248*screenWidth/65535, 21*screenHeight/65535, 40*screenWidth/65535, 21*screenHeight/65535);
				add(jTextFieldFontSize);
				jTextFieldFontSize.setColumns(10);
				
				jTextFieldLineSize = new JTextField();
				jTextFieldLineSize.getDocument().addDocumentListener(new DocumentListener() {
					
					@Override
					public void removeUpdate(DocumentEvent e) {
						lineSize = jTextFieldLineSize.getText().trim(); // TODO Auto-generated method stub
					}
					
					@Override
					public void insertUpdate(DocumentEvent e) {
						lineSize = jTextFieldLineSize.getText().trim();
					}
					
					@Override
					public void changedUpdate(DocumentEvent e) {
						lineSize = jTextFieldLineSize.getText().trim();
					}
				});
				jTextFieldLineSize.setColumns(10);
				jTextFieldLineSize.setBounds(77*screenWidth/65535, 21*screenHeight/65535, 72*screenWidth/65535, 21*screenHeight/65535);
				add(jTextFieldLineSize);
				
				panelLineColor = new JPanel();
				panelLineColor.setBackground(Color.BLACK);
				panelLineColor.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						super.mouseClicked(e);
						new ColorChooserPanel(getX()+e.getX(),getY()+e.getY());
					}
				});
				panelLineColor.setBounds(77*screenWidth/65535, 67*screenHeight/65535, 72*screenWidth/65535, 20*screenHeight/65535);
				add(panelLineColor); 
				
				lblHeadRatio = new JLabel("Head Ratio");
				lblHeadRatio.setBounds(171*screenWidth/65535, 67*screenHeight/65535, 97*screenWidth/65535, 20*screenHeight/65535);
				add(lblHeadRatio);
				
				comboBox = new JComboBox();
				comboBox.addItemListener(new ItemListener() {
					@Override
					public void itemStateChanged(ItemEvent e) {
						if(e.getStateChange()==ItemEvent.SELECTED){
							lineType = (String)e.getItem();
						}
					}
				});
				comboBox.addItem("Solid");
				comboBox.addItem("Dash");
				comboBox.addItem("Dot");
				comboBox.addItem("Dash-Dot");
				comboBox.addItem("Dash-Dot-Dot");
				comboBox.setBounds(77*screenWidth/65535, 111*screenHeight/65535, 148*screenWidth/65535, 21*screenHeight/65535);
				add(comboBox);
				
				textFieldHeadRatio = new JTextField();
				textFieldHeadRatio.getDocument().addDocumentListener(new DocumentListener() {
					
					@Override
					public void removeUpdate(DocumentEvent e) {
						lineHeadRatio = textFieldHeadRatio.getText().trim(); // TODO Auto-generated method stub
					}
					
					@Override
					public void insertUpdate(DocumentEvent e) {
						lineHeadRatio = textFieldHeadRatio.getText().trim();
					}
					
					@Override
					public void changedUpdate(DocumentEvent e) {
						lineHeadRatio = textFieldHeadRatio.getText().trim();
					}
				});
				textFieldHeadRatio.setColumns(10);
				textFieldHeadRatio.setBounds(248*screenWidth/65535, 67*screenHeight/65535, 40*screenWidth/65535, 21*screenHeight/65535);
				add(textFieldHeadRatio);
				
				lblMm = new JLabel("mm");
				lblMm.setBounds(321*screenWidth/65535, 24*screenHeight/65535, 54*screenWidth/65535, 15*screenHeight/65535);
				add(lblMm);
			
			
			}
		 
	 }
	 public class Solid extends JPanel{
		 private JTextField jTextFieldEdgeSize;
			private JLabel lblEdgeSize;
			private JLabel lblEdgeType;
			private JLabel lblEdgeColor;
			private JLabel lblInterior;
			private JPanel panelEdgeColor;
			private JLabel lblFillColor;
			private JComboBox comboBoxEdgeType;
			private JComboBox comboBoxIterior;
			private JCheckBox chckbxEdge;
			private JLabel lblIn;
			private JPanel panelFillColor;
			private class ColorChooserPanel extends JFrame{
				
				private JColorChooser jColorChooser;
				private JButton buttonOk;
				private JButton buttonCancel;
				private Object object;
				public ColorChooserPanel(int x,int y,Object object) {
					this.object = object;
					setBounds(x*screenWidth/65535, y*screenHeight/65535, 460*screenWidth/65535, 320*screenHeight/65535);
					setLayout(null);
					jColorChooser=new JColorChooser();
					jColorChooser.setBounds(0, 0, 439*screenWidth/65535, 247*screenHeight/65535);
					add(jColorChooser);
					buttonOk = new JButton("OK");
					buttonOk.addActionListener(new ActionListener() {
						@Override
						public void actionPerformed(ActionEvent e) {
							if(ColorChooserPanel.this.object.equals(panelEdgeColor)){
								solidEdgeColor = jColorChooser.getColor().toString();
								panelEdgeColor.setBackground(jColorChooser.getColor());
								ColorChooserPanel.this.dispose();
							}
							else  {
								solidFillColor = jColorChooser.getColor().toString();
								panelFillColor.setBackground(jColorChooser.getColor());
								ColorChooserPanel.this.dispose();
							}
						}
					});
					buttonOk.setBounds(107*screenWidth/65535, 253*screenHeight/65535, 76*screenWidth/65535, 23*screenHeight/65535);
					add(buttonOk);
					
					buttonCancel = new JButton("Cancel");
					buttonCancel.addActionListener(new ActionListener() {
						@Override
						public void actionPerformed(ActionEvent e) {
							ColorChooserPanel.this.dispose();
						}
					});
					buttonCancel.setBounds(216*screenWidth/65535, 253*screenHeight/65535, 76*screenWidth/65535, 23*screenHeight/65535);
					add(buttonCancel);
					setVisible(true);
				}
			}
			public Solid() {
				setBounds(200, 310, 374, 302);
				setLayout(null);
				
				lblEdgeSize = new JLabel("Edge Size");
				lblEdgeSize.setBounds(10*screenWidth/65535, 21*screenHeight/65535, 80*screenWidth/65535, 20*screenHeight/65535);
				add(lblEdgeSize);
				
				lblEdgeColor = new JLabel("Edge Color");
				lblEdgeColor.setBounds(10*screenWidth/65535, 67*screenHeight/65535, 80*screenWidth/65535, 20*screenHeight/65535);
				add(lblEdgeColor);
				
				lblEdgeType = new JLabel("Edge Type");
				lblEdgeType.setBounds(10*screenWidth/65535, 111*screenHeight/65535, 80*screenWidth/65535, 20*screenHeight/65535);
				add(lblEdgeType);
				
				jTextFieldEdgeSize = new JTextField();
				jTextFieldEdgeSize.getDocument().addDocumentListener(new DocumentListener() {
					
					@Override
					public void removeUpdate(DocumentEvent e) {
						solidEdgeSize = jTextFieldEdgeSize.getText().trim(); // TODO Auto-generated method stub
					}
					
					@Override
					public void insertUpdate(DocumentEvent e) {
						solidEdgeSize = jTextFieldEdgeSize.getText().trim();
					}
					
					@Override
					public void changedUpdate(DocumentEvent e) {
						solidEdgeSize = jTextFieldEdgeSize.getText().trim();
					}
				});
				jTextFieldEdgeSize.setColumns(10);
				jTextFieldEdgeSize.setBounds(77*screenWidth/65535, 21*screenHeight/65535, 72*screenWidth/65535, 21*screenHeight/65535);
				add(jTextFieldEdgeSize);
				
				panelEdgeColor = new JPanel();
				panelEdgeColor.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						super.mouseClicked(e);
						new ColorChooserPanel(getX()+e.getX(), getY()+e.getY(), e.getSource());
					}
				});
				panelEdgeColor.setBackground(Color.BLACK);
				panelEdgeColor.setBounds(77*screenWidth/65535, 67*screenHeight/65535, 72*screenWidth/65535, 20*screenHeight/65535);
				add(panelEdgeColor);
				
				lblFillColor = new JLabel("Fill Color");
				lblFillColor.setBounds(171*screenWidth/65535, 67*screenHeight/65535, 97*screenWidth/65535, 20*screenHeight/65535);
				add(lblFillColor);
				
				comboBoxEdgeType = new JComboBox();
				comboBoxEdgeType.addItem("Solid");
				comboBoxEdgeType.addItem("Dash");
				comboBoxEdgeType.addItem("Dot");
				comboBoxEdgeType.addItem("Dash-Dot");
				comboBoxEdgeType.addItem("Dash-Dot-Dot");
				comboBoxEdgeType.addItemListener(new ItemListener() {
					@Override
					public void itemStateChanged(ItemEvent e) {
						if(e.getStateChange()==ItemEvent.SELECTED){
							solidEdgeType = (String)e.getItem();
						}
					}
				});
				comboBoxEdgeType.setBounds(77*screenWidth/65535, 111*screenHeight/65535, 148*screenWidth/65535, 21*screenHeight/65535);
				add(comboBoxEdgeType);
				
				lblIn = new JLabel("in");
				lblIn.setBounds(171*screenWidth/65535, 24*screenHeight/65535, 54*screenWidth/65535, 15*screenHeight/65535);
				add(lblIn);
				
				panelFillColor = new JPanel();
				panelFillColor.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						super.mouseClicked(e);
						new ColorChooserPanel(getX()+e.getX(), getY()+e.getY(), e.getSource());
					}
				});
				panelFillColor.setBackground(Color.BLACK);
				panelFillColor.setBounds(240*screenWidth/65535, 67*screenHeight/65535, 40*screenWidth/65535, 20*screenHeight/65535);
				add(panelFillColor);
				
				chckbxEdge = new JCheckBox("Edge");
				
				chckbxEdge.setBounds(187*screenWidth/65535, 157*screenHeight/65535, 103*screenWidth/65535, 23*screenHeight/65535);
				chckbxEdge.addItemListener(new ItemListener() {
					@Override
					public void itemStateChanged(ItemEvent e) {
						if(chckbxEdge.isSelected())
							solidIsEdge = true;
						else
							solidIsEdge = false;
					}
				});
				add(chckbxEdge);
				
				lblInterior = new JLabel("Interior");
				lblInterior.setBounds(10*screenWidth/65535, 161*screenHeight/65535, 54*screenWidth/65535, 15*screenHeight/65535);
				add(lblInterior);
				
				comboBoxIterior = new JComboBox();
				comboBoxIterior.addItem("Solid");
				comboBoxIterior.addItem("Pattern");
				comboBoxIterior.addItem("Hatch");
				comboBoxIterior.addItem("Off");
				comboBoxIterior.addItemListener(new ItemListener() {
					@Override
					public void itemStateChanged(ItemEvent e) {
						if(e.getStateChange()==ItemEvent.SELECTED){
							solidIterior = (String)e.getItem();
						}
					}
				});
				comboBoxIterior.setBounds(77*screenWidth/65535, 158*screenHeight/65535, 94*screenWidth/65535, 21*screenHeight/65535);
				add(comboBoxIterior);
			
			}
	 }
	 public class Symbol extends JPanel{
		 	private JTextField jTextFieldSymbolSize;
			private JLabel lblSymbolSize;
			private JLabel lblIn;
			
			public Symbol() {
				setBounds(200*screenWidth/65535, 310*screenHeight/65535, 374*screenWidth/65535, 302*screenHeight/65535);
				setLayout(null);
				
				lblSymbolSize = new JLabel("Symbol Size");
				lblSymbolSize.setBounds(10*screenWidth/65535, 21*screenHeight/65535, 80*screenWidth/65535, 20*screenHeight/65535);
				add(lblSymbolSize);
				
				jTextFieldSymbolSize = new JTextField();
				jTextFieldSymbolSize.getDocument().addDocumentListener(new DocumentListener() {
					
					@Override
					public void removeUpdate(DocumentEvent e) {
						symbolSize = jTextFieldSymbolSize.getText().trim(); // TODO Auto-generated method stub
					}
					
					@Override
					public void insertUpdate(DocumentEvent e) {
						symbolSize = jTextFieldSymbolSize.getText().trim();
					}
					
					@Override
					public void changedUpdate(DocumentEvent e) {
						symbolSize = jTextFieldSymbolSize.getText().trim();
					}
				});
				jTextFieldSymbolSize.setColumns(10);
				jTextFieldSymbolSize.setBounds(88*screenWidth/65535, 21*screenHeight/65535, 105*screenWidth/65535, 21*screenHeight/65535);
				add(jTextFieldSymbolSize);
				
				lblIn = new JLabel("in");
				lblIn.setBounds(203*screenWidth/65535, 24*screenHeight/65535, 54*screenWidth/65535, 15*screenHeight/65535);
				add(lblIn);
			
			}
		 
	 }
	public OptionDrawOptions(int x,int y){
		buttonOk = new JButton("OK");
		buttonOk.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				OptionDrawOptionsProp drawOptionsProp = new OptionDrawOptionsProp();
				drawOptionsProp.setGeneralColor(generalColor);
				drawOptionsProp.setGeneralMDS(generalMDS);
				drawOptionsProp.setLineColor(lineColor);
				drawOptionsProp.setLineFontSize(lineFontSize);
				drawOptionsProp.setLineHeadRatio(lineHeadRatio);
				drawOptionsProp.setLineSize(lineSize);
				drawOptionsProp.setLineType(lineType);
				drawOptionsProp.setSolidEdgeColor(solidEdgeColor);
				drawOptionsProp.setSolidEdgeSize(solidEdgeSize);
				drawOptionsProp.setSolidEdgeType(solidEdgeType);
				drawOptionsProp.setSolidFillColor(solidFillColor);
				drawOptionsProp.setSolidIsEdge(solidIsEdge);
				drawOptionsProp.setSolidIterior(solidIterior);
				drawOptionsProp.setSymbolSize(symbolSize);
				drawOptionsProp.setTextAlignment(textAlignment);
				drawOptionsProp.setTextbgColorChooserColor(textbgColorChooserColor);
				drawOptionsProp.setTextCapHeight(textCapHeight);
				drawOptionsProp.setTextColorChooserColor(textColorChooserColor);
				drawOptionsProp.setTextFont(textFont);
				drawOptionsProp.setTextFontSize(textFontSize);
				drawOptionsProp.setTextIsBold(textIsBold);
				drawOptionsProp.setTextIsItalic(textIsItalic);
				drawOptionsProp.setTextSelectedFonts(textSelectedFonts);
				OptionDrawOptions.this.dispose();
			}
		});
		buttonCancel = new JButton("Cancel");
		buttonCancel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				OptionDrawOptions.this.dispose();
			}
		});
		buttonOk.setBounds(40*screenWidth/65535, 320*screenHeight/65535, 90*screenWidth/65535, 30*screenHeight/65535);
		buttonCancel.setBounds(170*screenWidth/65535, 320*screenHeight/65535, 90*screenWidth/65535, 30*screenHeight/65535);
		setBounds(x*screenWidth/65535, y*screenHeight/65535, 350*screenWidth/65535, 410*screenHeight/65535);
		subDrawOptions = new SubDrawOptions();
		subDrawOptions.setBounds(310*screenWidth/65535, 5*screenHeight/65535, 380*screenWidth/65535, 360*screenHeight/65535);
		jTabbedPane = new JTabbedPane();
		jTabbedPane.setBounds(5*screenWidth/65535, 5*screenHeight/65535, 300*screenWidth/65535, 300*screenHeight/65535);
		jTabbedPane.addTab("General", new General());
		jTabbedPane.addTab("Text", new Text());
		jTabbedPane.addTab("Line", new Line());
		jTabbedPane.addTab("Solid", new Solid());
		jTabbedPane.addTab("Symbol", new Symbol());
		OptionDrawOptions.this.add(subDrawOptions);
		subDrawOptions.setVisible(false);
		jTabbedPane.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				if(jTabbedPane.getSelectedIndex()==1)
				{
					OptionDrawOptions.this.setBounds(OptionDrawOptions.this.getX(), OptionDrawOptions.this.getY(), 710*screenWidth/65535, 410*screenHeight/65535);
					subDrawOptions.setVisible(true);
					
				}
					
				else {
					subDrawOptions.setVisible(false);
					OptionDrawOptions.this.setBounds(OptionDrawOptions.this.getX(), OptionDrawOptions.this.getY(), 350*screenWidth/65535, 410*screenHeight/65535);
				}
			}
		});
		add(jTabbedPane);
		add(buttonOk);
		add(buttonCancel);
		setLayout(null);
		setTitle("Draw Options");
		setResizable(false);
		setVisible(true);
	}
	
 
}
