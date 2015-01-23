package net.sf.jcgm.display;

import javax.net.ssl.SSLContext;
import javax.swing.Box;
import javax.swing.CellRendererPane;
import javax.swing.DefaultListModel;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListCellRenderer;
import javax.swing.ListModel;
import javax.swing.ScrollPaneLayout;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Label;
import java.awt.Panel;
import java.awt.Rectangle;

import javax.swing.border.LineBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableModel;
import javax.swing.text.LayeredHighlighter;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.HashMap;
import java.util.LinkedList;

import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;


import java.awt.GridLayout;

public class LayoutLayerManager extends JFrame{
	private JButton btnClose;
	private JButton btnNew ;
	private JPanel tablePanel;
	private JPanel buttonPanel;
	private JScrollPane jScrollPane;
	private Box panelBox;
	private ItemPanel temPanel;
	public LinkedList itemList ;
	public class ItemPanel extends JPanel {
		private JPopupMenu jPopupMenu;
		private JTextField name; 
		private JLabel label;
		protected JLabel see;
		protected JLabel write;
		protected JLabel print;
		protected JLabel save;
		private JPanel editPanel;
		private JPanel imagePanel;
		private JMenuItem reName;
		private JMenuItem delete;
		private JLabel jLabel;
		protected class Click extends MouseAdapter{
			int count;
			@Override
			public void mousePressed(MouseEvent e) {
				if(e.getButton()==MouseEvent.BUTTON1)
					count++;
			}
		}
		public ItemPanel done(){
			label.setText(name.getText());
			name.setVisible(false);
			label.setVisible(true);
			jLabel.setText("                  ");
			return this;
		}
		public ItemPanel(String nameText) {
			setLayout(new FlowLayout(FlowLayout.LEFT,0,0));
			editPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
			imagePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
			label = new JLabel("      ");
			label.setPreferredSize(new Dimension(90,20));
			label.setVisible(false);
			editPanel.add(label);
			
			name = new JTextField(nameText);
			name.setColumns(7);
			name.getDocument().addDocumentListener(new DocumentListener() {
				
				@Override
				public void removeUpdate(DocumentEvent e) {
					label.setText(name.getText());
					label.setVisible(true);
					name.setVisible(false); // TODO Auto-generated method stub
				}
				
				@Override
				public void insertUpdate(DocumentEvent e) {
					label.setText(name.getText());
					label.setVisible(true);
					name.setVisible(false);
				}
				
				@Override
				public void changedUpdate(DocumentEvent e) {
					label.setText(name.getText());
					label.setVisible(true);
					name.setVisible(false);
				}
			});
			editPanel.add(name);
			jLabel = new JLabel("        ok        ");
			add(editPanel);
			add(jLabel);
			add(imagePanel);
			see = new JLabel(new ImageIcon(JCGMEditor.class.getResource("/images/see.gif")));
			see.addMouseListener(new Click(){
				@Override
				public void mouseClicked(MouseEvent e) {
					super.mouseClicked(e);
					if(count%3==0){
						see.setIcon(new ImageIcon(JCGMEditor.class.getResource("/images/see.gif")));
					}
					else if(count%3==1){
						see.setIcon(new ImageIcon(JCGMEditor.class.getResource("/images/noSee.gif")));
					}
					else if(count%3==2){
						see.setIcon(new ImageIcon(JCGMEditor.class.getResource("/images/graySee.gif")));
					}
				}
			});
			imagePanel.add(see);
			write = new JLabel(new ImageIcon(JCGMEditor.class.getResource("/images/write.gif")));
			write.addMouseListener(new Click(){
				@Override
				public void mouseClicked(MouseEvent e) {
					super.mouseClicked(e);
					if(count%2==0){
						write.setIcon(new ImageIcon(JCGMEditor.class.getResource("/images/write.gif")));
					}
					else if(count%2==1){
						write.setIcon(new ImageIcon(JCGMEditor.class.getResource("/images/noWrite.gif")));
					}
				}
			});
			imagePanel.add(write);
			print = new JLabel(new ImageIcon(JCGMEditor.class.getResource("/images/printm.gif")));
			print.addMouseListener(new Click(){
				@Override
				public void mouseClicked(MouseEvent e) {
					super.mouseClicked(e);
					if(count%2==0){
						print.setIcon(new ImageIcon(JCGMEditor.class.getResource("/images/printm.gif")));
					}
					else if(count%2==1){
						print.setIcon(new ImageIcon(JCGMEditor.class.getResource("/images/noPrint.gif")));
					}
				}
			});
			imagePanel.add(print);
			save = new JLabel(new ImageIcon(JCGMEditor.class.getResource("/images/savem.gif")));
			save.addMouseListener(new Click(){
				@Override
				public void mouseClicked(MouseEvent e) {
					super.mouseClicked(e);
					if(count%2==0){
						save.setIcon(new ImageIcon(JCGMEditor.class.getResource("/images/savem.gif")));
					}
					else if(count%2==1){
						save.setIcon(new ImageIcon(JCGMEditor.class.getResource("/images/noSave.gif")));
					}
				}
			});
			imagePanel.add(save);
			jPopupMenu = new JPopupMenu();
			reName = new JMenuItem("Rename", new ImageIcon(JCGMEditor.class.getResource("/images/empty.gif")));
			delete = new JMenuItem("Delete", new ImageIcon(JCGMEditor.class.getResource("/images/empty.gif")));
			jPopupMenu.add(reName);
			jPopupMenu.addSeparator();
			jPopupMenu.add(delete);
			reName.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					label.setVisible(false);
					name.setVisible(true);
				}
			});
			delete.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					panelBox.remove(ItemPanel.this);
					itemList.remove(ItemPanel.this);
					tablePanel.revalidate();
				}
			});
			addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					super.mouseClicked(e);
					label.setText(name.getText());
					name.setVisible(false);
					label.setVisible(true);
					jLabel.setText("                  ");
					if(e.getButton()==MouseEvent.BUTTON3){
						jPopupMenu.show(ItemPanel.this, e.getX(), e.getY());
					}
				}
			});
			setVisible(true);
		}
	 
	}
	 
	public class LayerManager extends ItemPanel{
		public LayerManager(){
			super("  ");
			super.done();
			super.see.addMouseListener(new Click(){
				@Override
				public void mouseClicked(MouseEvent e) {
					super.mouseClicked(e);
					if(count%3==0){
						for(Object temp:itemList.toArray()){
							((ItemPanel)temp).see.setIcon(new ImageIcon(JCGMEditor.class.getResource("/images/see.gif")));
						}
					}
					else if(count%3==1){
						for(Object temp:itemList.toArray()){
							((ItemPanel)temp).see.setIcon(new ImageIcon(JCGMEditor.class.getResource("/images/noSee.gif")));
						}
					}
					else if(count%3==2){
						for(Object temp:itemList.toArray()){
							((ItemPanel)temp).see.setIcon(new ImageIcon(JCGMEditor.class.getResource("/images/graySee.gif")));
						}
					}
				}
			});
			super.write.addMouseListener(new Click(){
				@Override
				public void mouseClicked(MouseEvent e) {
					super.mouseClicked(e);
					if(count%2==0){
						for(Object temp:itemList.toArray()){
							((ItemPanel)temp).write.setIcon(new ImageIcon(JCGMEditor.class.getResource("/images/write.gif")));
						}
					}
					else if(count%2==1){
						for(Object temp:itemList.toArray()){
							((ItemPanel)temp).write.setIcon(new ImageIcon(JCGMEditor.class.getResource("/images/noWrite.gif")));
						}
					}
				}
			});
			super.print.addMouseListener(new Click(){
				@Override
				public void mouseClicked(MouseEvent e) {
					super.mouseClicked(e);
					if(count%2==0){
						for(Object temp:itemList.toArray()){
							((ItemPanel)temp).print.setIcon(new ImageIcon(JCGMEditor.class.getResource("/images/printm.gif")));
						}
					}
					else if(count%2==1){
						for(Object temp:itemList.toArray()){
							((ItemPanel)temp).print.setIcon(new ImageIcon(JCGMEditor.class.getResource("/images/noPrint.gif")));
						}
					}
				}
			});
			super.save.addMouseListener(new Click(){
				@Override
				public void mouseClicked(MouseEvent e) {
					super.mouseClicked(e);
					if(count%2==0){
						for(Object temp:itemList.toArray()){
							((ItemPanel)temp).save.setIcon(new ImageIcon(JCGMEditor.class.getResource("/images/savem.gif")));
						}
					}
					else if(count%2==1){
						for(Object temp:itemList.toArray()){
							((ItemPanel)temp).save.setIcon(new ImageIcon(JCGMEditor.class.getResource("/images/noSave.gif")));
						}
					}
				}
			});
		}
	}
	public LayoutLayerManager(int x,int y){
		setBounds(x,y,300,393);
		setLayout(new BorderLayout());
		itemList = new LinkedList();
		panelBox = Box.createVerticalBox();
		panelBox.add(new LayerManager());
		panelBox.add(new Label("------------------------------------------------------------------"));
		tablePanel = new JPanel();
		tablePanel.add(panelBox);
		
		jScrollPane = new JScrollPane(tablePanel);
		jScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		jScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER) ;
		add(jScrollPane,BorderLayout.CENTER);
		buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER,30,10));
		btnNew = new JButton("New");
		btnNew.setBounds(new Rectangle(82,23));
		buttonPanel.add(btnNew);
		temPanel = new ItemPanel("");
		btnNew.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				temPanel.done();
				temPanel = new ItemPanel("Layer"+itemList.size());
				itemList.add(temPanel);
				panelBox.add(temPanel);
				tablePanel.revalidate();
			}
		});
		
		
		btnClose = new JButton("Close");
		btnClose.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				LayoutLayerManager.this.dispose();
			}
		});
		btnClose.setBounds(new Rectangle(82,23));
		buttonPanel.add(btnClose);
		
		 
		
		add(buttonPanel, BorderLayout.SOUTH);
		
		setVisible(true);
		
	}
	public static void main(String[] args) {
		new LayoutLayerManager(100, 100);
	}
}
