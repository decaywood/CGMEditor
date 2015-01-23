package net.sf.jcgm.display;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JToggleButton;
import javax.swing.JList;

public class LayoutViewList extends JFrame{
	private JPanel topPanel;
	private JButton saveButton;  
	private JButton copyButton;
	private JLabel searchLabel;
	private JComboBox searchBox;
	private JButton findNextButton;
	private JButton findPreviousButton;
	private JToggleButton caseSense;
	private JToggleButton summarySearch;
	private JToggleButton zoomToEle;
	private JList list;
	public LayoutViewList(int x,int y){
		setBounds(x, y, 400, 500);
		topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT,4,2));
		saveButton = new JButton(new ImageIcon(JCGMEditor.class.getResource("/images/savem.gif")));
		saveButton.setToolTipText("Save Entire List");
		saveButton.setPreferredSize(new Dimension(25,25));
		copyButton = new JButton(new ImageIcon(JCGMEditor.class.getResource("/images/copy.gif")));
		copyButton.setToolTipText("Copy Element");
		copyButton.setPreferredSize(new Dimension(25,25));
		searchLabel = new JLabel("Search:");
		searchBox = new JComboBox();
		searchBox.setPreferredSize(new Dimension(120,25));
		findNextButton = new JButton(new ImageIcon(JCGMEditor.class.getResource("/images/findNext.gif")));
		findNextButton.setToolTipText("Find Next");
		findNextButton.setPreferredSize(new Dimension(25,25));
		findPreviousButton = new JButton(new ImageIcon(JCGMEditor.class.getResource("/images/findPrevious.gif")));
		findPreviousButton.setToolTipText("Find Previous");
		findPreviousButton.setPreferredSize(new Dimension(25,25));
		caseSense = new JToggleButton(new ImageIcon(JCGMEditor.class.getResource("/images/case.gif")));
		caseSense.setToolTipText("Toggle Case Sensitivity");
		caseSense.setPreferredSize(new Dimension(25,25));
		summarySearch = new JToggleButton(new ImageIcon(JCGMEditor.class.getResource("/images/sigma.gif")));
		summarySearch.setToolTipText("Toggle Detailed\\Summary Search");
		summarySearch.setPreferredSize(new Dimension(25,25));
		zoomToEle = new JToggleButton(new ImageIcon(JCGMEditor.class.getResource("/images/findElement.gif")));
		zoomToEle.setToolTipText("Toggle Zoom to Element");
		zoomToEle.setPreferredSize(new Dimension(25,25));
		topPanel.add(saveButton);
		topPanel.add(copyButton);
		topPanel.add(searchLabel);
		topPanel.add(searchBox);
		topPanel.add(findNextButton);
		topPanel.add(findPreviousButton);
		topPanel.add(caseSense);
		topPanel.add(summarySearch);
		topPanel.add(zoomToEle);
		getContentPane().add(topPanel,BorderLayout.NORTH);
		list = new JList();
		getContentPane().add(list, BorderLayout.CENTER);
		setVisible(true);
	}
	public static void main(String[] args) {
		new LayoutViewList(100,100);
	}

}
