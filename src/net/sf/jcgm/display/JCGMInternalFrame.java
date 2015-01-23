/**
 * 
 */
package net.sf.jcgm.display;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.geom.Point2D;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

import net.sf.jcgm.core.ICGMDisplay;
import net.sf.jcgm.drawer.DrawType;
import net.sf.jcgm.event.EventDispatcher;

/**
 * @author Jiang
 *
 */
public class JCGMInternalFrame extends JInternalFrame {

	private static final long serialVersionUID = 5874091534504323890L;
	private int cgmFrameId;
	private final JPanel contentPanel;
	private JPanel statusPanel;
	private JLabel coordsLabel;
	private JScrollPane s;
	private CGMCanvas canvasPanel;
	private final JPanel cornerPanel;
	private PropertyPanel propertyPanel;
	private EventDispatcher dispatcher;
	private int cgmTempId;
	private int cgmId;
	private double dpiStep = 30;
	private final int zoomStep=200;
	public JCGMInternalFrame(String name, int cgmFrameId)
	{
		super(name,true,true,true,true);
		this.cgmFrameId=cgmFrameId;
		this.setSize(600, 300);
		
		this.getContentPane().setLayout(new BorderLayout());
		this.statusPanel=new JPanel();
		this.getContentPane().add(this.statusPanel, BorderLayout.SOUTH);
		this.statusPanel.setLayout(new BorderLayout(0, 0));
		
		this.coordsLabel = new JLabel();
		this.statusPanel.add(this.coordsLabel,BorderLayout.WEST);
		
		this.contentPanel = new JPanel();
		getContentPane().add(this.contentPanel, BorderLayout.CENTER);
		this.contentPanel.setLayout(new BorderLayout(0, 0));
		
		this.s=new JScrollPane();	
		this.canvasPanel=new CGMCanvas();
		this.canvasPanel.setBorder(null);
		this.canvasPanel.setScroll(this.s);
		
		this.propertyPanel=new PropertyPanel();
		this.contentPanel.add(propertyPanel, BorderLayout.NORTH);
		this.cornerPanel=new JPanel();
		this.cornerPanel.setOpaque(true);
		this.s.setBorder(null);
		this.s.setViewportBorder(null);
		this.s.setCorner(ScrollPaneConstants.LOWER_RIGHT_CORNER, this.cornerPanel);
		this.s.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		this.s.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		this.s.setBackground(Color.LIGHT_GRAY);
		this.s.setViewportView(this.canvasPanel);
		this.canvasPanel.setLayout(new BorderLayout(0, 0));
		this.contentPanel.add(this.s, BorderLayout.CENTER); 
		this.getContentPane().add(this.contentPanel);
		
	}
	public void setEventDispatcher(EventDispatcher dispatcher)
	{
		this.dispatcher=dispatcher;
		this.propertyPanel.setEventDispatcher(this.dispatcher);
		this.canvasPanel.setEventDispatcher(this.dispatcher);
	}
	public PropertyPanel getPropertyPanel()
	{
		return this.propertyPanel;
	}
	public void setPropertyPanelTab(DrawType type)
	{
		this.propertyPanel.setVisible(type);
	}
	public CGMCanvas getCanvasPanel()
	{
		return this.canvasPanel;
	}
	public int getFrameId()
	{
		return this.cgmFrameId;
	}
	public int getCgmTempId() {
		return cgmTempId;
	}
	public void setCgmTempId(int cgmTempId) {
		this.cgmTempId = cgmTempId;
	}
	public int getCgmId() {
		return cgmId;
	}
	public void setCgmId(int cgmId) {
		this.cgmId = cgmId;
	}
	public void zoomIn() {
		double dpi = this.canvasPanel.getDpi()+dpiStep;
		this.canvasPanel.setDpi(dpi);
		double width=this.canvasPanel.getDimension().getWidth()+zoomStep;
		double height=this.canvasPanel.getDimension().getHeight()+zoomStep;
		this.canvasPanel.getDimension().setSize(width, height);
		this.canvasPanel.repaint();
	}

	public void zoomOut() {
		double dpi =this.canvasPanel.getDpi()-dpiStep>15?this.canvasPanel.getDpi()-dpiStep:15;
		this.canvasPanel.setDpi(dpi);
		double width=this.canvasPanel.getDimension().getWidth()-zoomStep>400?
				this.canvasPanel.getDimension().getWidth()-zoomStep:200;
		double height=this.canvasPanel.getDimension().getHeight()-zoomStep>400?
				this.canvasPanel.getDimension().getHeight()-zoomStep:200;
		this.canvasPanel.getDimension().setSize(width, height);
		this.canvasPanel.repaint();
	}
	public void updateStatusBar(String str)
	{
		System.out.println(str);
		this.coordsLabel.setText(str);
	}
	public void refreshCanvas()
	{
		this.canvasPanel.refreshCanvas();
	}
	public void setCanvas(Point2D.Double canvasP0, Point2D.Double canvasP1) {
		this.canvasPanel.setCanvasP(canvasP0, canvasP1);
	}
	public Point2D.Double getCanvasP0() {
		return this.canvasPanel.getCanvasP0();
	}
	public Point2D.Double getCanvasP1() {
		return this.canvasPanel.getCanvasP1();
	}
}
