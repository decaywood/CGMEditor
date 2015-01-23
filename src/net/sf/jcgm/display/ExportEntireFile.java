package net.sf.jcgm.display;

import javax.activation.MailcapCommandMap;
import javax.swing.JScrollPane;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Rectangle;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import net.sf.jcgm.properties.ExportEntireFileProp;

import org.omg.CORBA.PRIVATE_MEMBER;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JCheckBox;
import javax.swing.border.LineBorder;
import java.awt.Button;

public class ExportEntireFile extends JDialog{

	private static final int screenWidth=(int)java.awt.Toolkit.getDefaultToolkit().getScreenSize().width*65535/1280;
	private static final int screenHeight = (int)java.awt.Toolkit.getDefaultToolkit().getScreenSize().height*65535/800;
	private JTextField textFieldWidthPixels;
	private JTextField textFieldHeightPixels;
	private JTextField textFieldWidthInches;
	private JTextField textFieldHeightInches;
	private JTextField textFieldScale;
	private JLabel lblResolution;
	private JLabel lblCompression;
	private JLabel lblWidthpixels;
	private JLabel lblHeightpixels;
	private JLabel lblUncompressedFileSizebytes ;
	private JLabel lblDpi;
	private JComboBox combResolution;
	private JComboBox combCompression ;
	private JComboBox combExport;
	private JLabel lblFileSizeEmpty;
	private JLabel lblWidthinches;
	private JLabel lblHeightinches;
	private JLabel lblScale;
	private JLabel lblExport ;
	private JButton btnOk;
	private JButton btnCancel;
	private JPanel panel;
	private ExportEntireFileProp exportEntireFileProp;
	private String expResolution = null;
	private String expscale = null;
	private String expQuality = null;
	private String expWidthpix = null;
	private String expHeightpix = null;
	private String expWidthInch = null;
	private String expHeightInch = null;
	private String expFileType = null;
	public ExportEntireFile(int x ,int y, JFrame frame) {
		super(frame,true);
		setBounds(x*screenWidth/65535, y*screenHeight/65535 , 480*screenWidth/65535 , 328*screenHeight/65535 );
		getContentPane().setLayout(null);
		setTitle("Export");
		
		exportEntireFileProp = new ExportEntireFileProp();
		panel = new JPanel();
		panel.setVisible(true);
		panel.setBorder(new LineBorder(Color.GRAY));
		panel.setBounds(10*screenWidth/65535, 10*screenHeight/65535, 444*screenWidth/65535, 224*screenHeight/65535);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		lblResolution = new JLabel("Resolution");
		lblResolution.setBounds(21*screenWidth/65535, 32*screenHeight/65535, 71*screenWidth/65535, 20*screenHeight/65535);
		panel.add(lblResolution);
		
		lblCompression = new JLabel("Compression");
		lblCompression.setBounds(22*screenWidth/65535, 72*screenHeight/65535, 60*screenWidth/65535, 20*screenHeight/65535);
		panel.add(lblCompression);
		
		lblWidthpixels = new JLabel("Width:(Pixels)");
		lblWidthpixels.setBounds(23*screenWidth/65535, 112*screenHeight/65535, 105*screenWidth/65535, 20*screenHeight/65535);
		panel.add(lblWidthpixels);
		
		lblHeightpixels = new JLabel("Height:(Pixels)");
		lblHeightpixels.setBounds(23*screenWidth/65535, 150*screenHeight/65535, 105*screenWidth/65535, 20*screenHeight/65535);
		panel.add(lblHeightpixels);
		
		lblUncompressedFileSizebytes = new JLabel("Uncompressed File Size:(Bytes)");
		lblUncompressedFileSizebytes.setBounds(21*screenWidth/65535, 189*screenHeight/65535, 198*screenWidth/65535, 20*screenHeight/65535);
		panel.add(lblUncompressedFileSizebytes);
		
		combResolution = new JComboBox();
		combResolution.addItem("72");
		combResolution.addItem("96");
		combResolution.addItem("150");
		combResolution.addItem("200");
		combResolution.addItem("300");
		combResolution.addItem("600");
		combResolution.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				if(e.getStateChange()==ItemEvent.SELECTED){
					expResolution = (String)e.getItem();
				}
					
			}
		});
		combResolution.setBounds(102*screenWidth/65535, 32*screenHeight/65535, 95*screenWidth/65535, 21*screenHeight/65535);
		panel.add(combResolution);
		
		lblDpi = new JLabel("dpi");
		lblDpi.setBounds(207*screenWidth/65535, 32*screenHeight/65535, 60*screenWidth/65535, 20*screenHeight/65535);
		panel.add(lblDpi);
		
		combCompression = new JComboBox();
		combCompression.addItem("1 Bit Compressed");
		combCompression.addItem("8 Bit UnCompressed");
		combCompression.addItem("8 Bit Compressed");
		combCompression.addItem("24 Bit UnCompressed");
		combCompression.addItem("24 Bit Compressed");
		combCompression.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				if(e.getStateChange()==ItemEvent.SELECTED){
					expQuality = (String)e.getItem();
				}
			}
		});
		combCompression.setBounds(102*screenWidth/65535, 72*screenHeight/65535, 149*screenWidth/65535, 21*screenHeight/65535);
		panel.add(combCompression);
		
		textFieldWidthPixels = new JTextField();
		textFieldWidthPixels.getDocument().addDocumentListener(new DocumentListener() {
			
			@Override
			public void removeUpdate(DocumentEvent e) {
				expWidthpix = textFieldWidthPixels.getText().trim(); // TODO Auto-generated method stub
			}
			
			@Override
			public void insertUpdate(DocumentEvent e) {
				expWidthpix = textFieldWidthPixels.getText().trim(); 
			}
			
			@Override
			public void changedUpdate(DocumentEvent e) {
				expWidthpix = textFieldWidthPixels.getText().trim(); 
			}
		});
		textFieldWidthPixels.setBounds(131*screenWidth/65535, 112*screenHeight/65535, 88*screenWidth/65535, 20*screenHeight/65535);
		panel.add(textFieldWidthPixels);
		textFieldWidthPixels.setColumns(10);
		
		textFieldHeightPixels = new JTextField();
		textFieldHeightPixels.getDocument().addDocumentListener(new DocumentListener() {
			
			@Override
			public void removeUpdate(DocumentEvent e) {
				expHeightpix = textFieldHeightPixels.getText().trim(); // TODO Auto-generated method stub
			}
			
			@Override
			public void insertUpdate(DocumentEvent e) {
				expHeightpix = textFieldHeightPixels.getText().trim(); 
			}
			
			@Override
			public void changedUpdate(DocumentEvent e) {
				expHeightpix = textFieldHeightPixels.getText().trim(); 
			}
		});
		textFieldHeightPixels.setBounds(131*screenWidth/65535, 150*screenHeight/65535, 88*screenWidth/65535, 20*screenHeight/65535);
		panel.add(textFieldHeightPixels);
		textFieldHeightPixels.setColumns(10);
		
		lblFileSizeEmpty = new JLabel("empty");
		//TODO add Listener
		lblFileSizeEmpty.setBounds(220*screenWidth/65535, 189*screenHeight/65535, 139*screenWidth/65535, 20*screenHeight/65535);
		panel.add(lblFileSizeEmpty);
		
		lblWidthinches = new JLabel("Width:(inches)");
		lblWidthinches.setBounds(244*screenWidth/65535, 112*screenHeight/65535, 88*screenWidth/65535, 20*screenHeight/65535);
		panel.add(lblWidthinches);
		
		lblHeightinches = new JLabel("Height:(inches)");
		lblHeightinches.setBounds(244*screenWidth/65535, 150*screenHeight/65535, 105*screenWidth/65535, 20*screenHeight/65535);
		panel.add(lblHeightinches);
		
		textFieldWidthInches = new JTextField();
		textFieldWidthInches.getDocument().addDocumentListener(new DocumentListener() {
			
			@Override
			public void removeUpdate(DocumentEvent e) {
				expWidthInch = textFieldWidthInches.getText().trim(); // TODO Auto-generated method stub
			}
			
			@Override
			public void insertUpdate(DocumentEvent e) {
				expWidthInch = textFieldWidthInches.getText().trim(); 
			}
			
			@Override
			public void changedUpdate(DocumentEvent e) {
				expWidthInch = textFieldWidthInches.getText().trim(); 
			}
		});
		textFieldWidthInches.setBounds(336*screenWidth/65535, 112*screenHeight/65535, 88*screenWidth/65535, 20*screenHeight/65535);
		panel.add(textFieldWidthInches);
		textFieldWidthInches.setColumns(10);
		
		textFieldHeightInches = new JTextField();
		textFieldHeightInches.getDocument().addDocumentListener(new DocumentListener() {
			
			@Override
			public void removeUpdate(DocumentEvent e) {
				expHeightInch = textFieldHeightInches.getText().trim(); // TODO Auto-generated method stub
			}
			
			@Override
			public void insertUpdate(DocumentEvent e) {
				expHeightInch = textFieldHeightInches.getText().trim(); 
			}
			
			@Override
			public void changedUpdate(DocumentEvent e) {
				expHeightInch = textFieldHeightInches.getText().trim(); 
			}
		});
		textFieldHeightInches.setBounds(336*screenWidth/65535, 150*screenHeight/65535, 88*screenWidth/65535, 20*screenHeight/65535);
		panel.add(textFieldHeightInches);
		textFieldHeightInches.setColumns(10);
		
		lblScale = new JLabel("Scale:");
		lblScale.setBounds(266*screenWidth/65535, 32*screenHeight/65535, 60*screenWidth/65535, 20*screenHeight/65535);
		panel.add(lblScale);
		
		textFieldScale = new JTextField();
		textFieldScale.getDocument().addDocumentListener(new DocumentListener() {
			
			@Override
			public void removeUpdate(DocumentEvent e) {
				expscale = textFieldScale.getText().trim(); // TODO Auto-generated method stub
			}
			
			@Override
			public void insertUpdate(DocumentEvent e) {
				expscale = textFieldScale.getText().trim(); 
			}
			
			@Override
			public void changedUpdate(DocumentEvent e) {
				expscale = textFieldScale.getText().trim(); 
			}
		});
		textFieldScale.setBounds(314*screenWidth/65535, 32*screenHeight/65535, 71*screenWidth/65535, 20*screenHeight/65535);
		panel.add(textFieldScale);
		textFieldScale.setColumns(10);
		
		lblExport = new JLabel("Export");
		lblExport.setBounds(32*screenWidth/65535, 250*screenHeight/65535, 60*screenWidth/65535, 20*screenHeight/65535);
		getContentPane().add(lblExport);
		
		combExport = new JComboBox();
		combExport.addItem("Clipboard");
		combExport.addItem("TIFF File");
		combExport.addItem("JPEG File");
		combExport.addItem("PNG File");
		combExport.addItem("EMF File");
		combExport.addItem("BMP File");
		combExport.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				if(e.getStateChange()==ItemEvent.SELECTED){
					expFileType = (String)e.getItem();
				}
			}
		});
		combExport.setBounds(80*screenWidth/65535, 250*screenHeight/65535, 121*screenWidth/65535, 20*screenHeight/65535);
		getContentPane().add(combExport);
		
		btnOk = new JButton("Ok");
		btnOk.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		btnOk.setBounds(232*screenWidth/65535, 249*screenHeight/65535, 80*screenWidth/65535, 23*screenHeight/65535);
		btnOk.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				exportEntireFileProp.setExpFileType(expFileType);
				exportEntireFileProp.setExpHeightInch(expHeightInch);
				exportEntireFileProp.setExpHeightpix(expHeightpix);
				exportEntireFileProp.setExpQuality(expQuality);
				exportEntireFileProp.setExpResolution(expResolution);
				exportEntireFileProp.setExpscale(expscale);
				exportEntireFileProp.setExpWidthInch(expWidthInch);
				exportEntireFileProp.setExpWidthpix(expWidthpix);
				ExportEntireFile.this.dispose();
			}
		});
		getContentPane().add(btnOk);
		
		btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});
		btnCancel.setBounds(322*screenWidth/65535, 249*screenHeight/65535, 93*screenWidth/65535, 23*screenHeight/65535);
		getContentPane().add(btnCancel);
		setVisible(true);
		setResizable(false);
	}

}
