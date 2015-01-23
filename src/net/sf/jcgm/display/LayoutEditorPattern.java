package net.sf.jcgm.display;


 

 import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
 

import javax.imageio.ImageIO;
import javax.print.attribute.Size2DSyntax;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import javax.swing.text.html.HTMLDocument.Iterator;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.regex.Pattern;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.ButtonGroup;

import net.sf.jcgm.display.JCGMEditor;

 


 public class LayoutEditorPattern extends JDialog {
	 private static final int screenWidth=(int)java.awt.Toolkit.getDefaultToolkit().getScreenSize().width*65535/1280;
	 private static final int screenHeight = (int)java.awt.Toolkit.getDefaultToolkit().getScreenSize().height*65535/800;
	 private GridPanel panelGrid;
	 private JPanel panelChooseColor;
	 private JPanel panelCurrentColor;
	 private JPanel panelBlack;
	 private JPanel panelWhite;
	 private JPanel panelRed;
	 private JPanel panelGreen;
	 private JPanel panelBlue;
	 private JPanel panelYellow;
	 private JPanel panelMagenta;
	 private JPanel panelCyan;
	 private JPanel panelGray;
	 private JComboBox comboBox;
	 private JButton  btnPoint;
	 private JButton  btnLine;
	 private JButton  btnRectangle;
	 private JButton  btnFillRectangle;
	 private JButton  btnEllipse;
	 private JButton  btnFillEllipse;
	 private JButton  btnErase;
	 private boolean isPoint;
	 private boolean isLine;
	 private boolean isRectangle;
	 private boolean isFillRectangle;
	 private boolean isEllipse;
	 private boolean isFillEllipse;
	 private boolean isErase;
	 private JButton  btnCurrentColor;
	 private JButton  btnOpen;
	 private JButton  btnSave;
	 private JButton  btnClear;
	 private JButton  btnClose;
	 private Color currentColor;
	 private int ellipseStartX;
	 private int ellipseStartY;
	 private final ButtonGroup buttonGroup ;
	 
	 private class GridPanel extends JPanel implements MouseMotionListener,MouseListener{
		 private int lineLength = 320;
		 private BufferedImage gridImagewrite;
		 private BufferedImage gridImageread;
		 private boolean canRead;
		 private boolean canWrite;
		 private Graphics gridGraphics;
		 private int gridCount;
		 private int distanceEachLine;
		 private int pointDrawX;
		 private int pointDrawY;
		 private int pointDrawXtemp;
		 private int pointDrawYtemp;
		 private int floatStartX;
		 private int floatStartY;
		 private int floatEndX;
		 private int floatEndY;
		 private int dragX;
		 private int dragY;
		 private int height;
		 private int width;
		 private LinkedList paintThings;
		 public void clearGraphic(){
			 gridGraphics.setColor(Color.WHITE);
			 gridGraphics.fillRect(0, 0, lineLength, lineLength);
		 }
		 public GridPanel(int gridCount){
			 currentColor = Color.black;
			 this.gridCount = gridCount;
			 this.gridImagewrite = new BufferedImage(lineLength, lineLength, BufferedImage.TYPE_INT_RGB);
			 this.gridGraphics = gridImagewrite.createGraphics();
			 gridGraphics.setColor(Color.WHITE);
			 gridGraphics.fillRect(0, 0, lineLength, lineLength);
			 this.gridImageread = new BufferedImage(lineLength, lineLength, BufferedImage.TYPE_INT_RGB);
			 this.gridGraphics.setColor(Color.BLACK);
			 this.paintThings = new LinkedList();
			 this.distanceEachLine = lineLength/gridCount;
			 addMouseListener(this);
			 addMouseMotionListener(this);
		 }
		 public void setGridCount(int gridCount) {
			 this.gridCount = gridCount;
			 this.distanceEachLine = lineLength/gridCount;
		} 
			@Override
			public void mouseDragged(MouseEvent e) {
				pointDrawX = e.getX()*100/distanceEachLine;
				pointDrawY = e.getY()*100/distanceEachLine;
				if(pointDrawX>(pointDrawX/100)&&pointDrawX<((pointDrawX/100)+1)*100)
					pointDrawX = pointDrawX/100;
				if(pointDrawY>(pointDrawY/100)&&pointDrawY<((pointDrawY/100)+1)*100)
					pointDrawY = pointDrawY/100;
				if(pointDrawX!=pointDrawXtemp||pointDrawY!=pointDrawYtemp){
					pointDrawXtemp = pointDrawX;
					pointDrawYtemp = pointDrawY;
					if(isPoint){
						paintThings.offerLast(new Object[]{"point",pointDrawX,pointDrawY,currentColor});
					}
					if(isErase){
						paintThings.offerLast(new Object[]{"erase",pointDrawX,pointDrawY});
					}
					floatEndX = e.getX();
					floatEndY = e.getY();
					width = Math.abs(floatStartX-floatEndX);
					height = Math.abs(floatStartY-floatEndY);
					dragX = Math.min(e.getX(), floatStartX);
					dragY = Math.min(e.getY(), floatStartY);	
					LayoutEditorPattern.this.repaint();
				}
			}
			public void setGridImage(BufferedImage gridImage) {
				this.gridImageread = gridImage;
			}
			public void setCanRead(boolean canRead) {
				this.canRead = canRead;
			}
			public BufferedImage getGridImage() {
				return gridImagewrite;
			}
			public void setCanWrite(boolean canWrite) {
				this.canWrite = canWrite;
			};
			@Override
			public void mouseMoved(MouseEvent e) {
				// TODO Auto-generated method stub
				 
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				pointDrawX = e.getX()*100/distanceEachLine;
				pointDrawY = e.getY()*100/distanceEachLine;
				if(pointDrawX>(pointDrawX/100)&&pointDrawX<((pointDrawX/100)+1)*100)
					pointDrawX = pointDrawX/100;
				if(pointDrawY>(pointDrawY/100)&&pointDrawY<((pointDrawY/100)+1)*100)
					pointDrawY = pointDrawY/100;
				if(pointDrawX!=pointDrawXtemp||pointDrawY!=pointDrawYtemp){
					pointDrawXtemp = pointDrawX;
					pointDrawYtemp = pointDrawY;
					if(isPoint){
						paintThings.offerLast(new Object[]{"point",pointDrawX,pointDrawY,currentColor});
						LayoutEditorPattern.this.repaint();
					}
					if(isErase){
						paintThings.offerLast(new Object[]{"erase",pointDrawX,pointDrawY});
					}
				}
				
			}
			@Override
			public void mousePressed(MouseEvent e) {
				floatStartX = e.getX();
				floatStartY = e.getY();
				ellipseStartX = e.getX();
				ellipseStartY = e.getY();
			}
			@SuppressWarnings("unchecked")
			@Override
			public void mouseReleased(MouseEvent e) {
				floatStartX = (floatStartX)*100/distanceEachLine;
				floatStartY = (floatStartY)*100/distanceEachLine;
				floatEndX = (floatEndX)*100/distanceEachLine;
				floatEndY = (floatEndY)*100/distanceEachLine;
				floatStartX = floatStartX/100;
				floatStartY = floatStartY/100;
				floatEndX = floatEndX/100;
				floatEndY = floatEndY/100;
				int width =  Math.abs((floatEndX-floatStartX));
				int height = Math.abs((floatEndY-floatStartY));
				boolean xlong = width>height;
				int cursorStart; 
				if(isLine){
					int count = width>height?width:height;
					boolean xPositive = (floatEndX-floatStartX)>0;
					boolean yPositive = (floatEndY-floatStartY)>0;
					double slope ;
					if(xlong){
						slope = (double)(floatEndY-floatStartY)/(floatEndX-floatStartX);
						if(xPositive){
							cursorStart = floatStartX;
							while(count>-1){
								paintThings.offerLast(new Object[]{"line",cursorStart,
										floatStartY+(int)((cursorStart-floatStartX)*slope),currentColor});
								cursorStart++;
								count--;
							}
						}
						else {
							cursorStart = floatStartX;
							while(count>0){
								paintThings.offerLast(new Object[]{"line",cursorStart,
										floatStartY+(int)((cursorStart-floatStartX)*slope),currentColor});
								cursorStart--;
								count--;
							}
						}
					}
						
					else {
						slope = (double)(floatEndX-floatStartX)/(floatEndY-floatStartY);
						if(yPositive){
							cursorStart = floatStartY;
							while(count>-1){
								paintThings.offerLast(new Object[]{"line",floatStartX+(int)((cursorStart-floatStartY)*slope),
										cursorStart,currentColor});
								cursorStart++;
								count--;
							}
						}
						else {
							cursorStart = floatStartY;
							while(count>0){
								paintThings.offerLast(new Object[]{"line",floatStartX+(int)((cursorStart-floatStartY)*slope),
										cursorStart,currentColor});
								cursorStart--;
								count--;
							}
						}
					}
				}
				if(isEllipse){
					double ellipseTempY = -1;
					double ellipseTempX = -1;
					int coreX = (ellipseStartX+e.getX())/2;
					int corex = coreX;
					int coreY = (ellipseStartY+e.getY())/2;
					int corey = coreY;
					width =  Math.abs((e.getX()-ellipseStartX));
					height = Math.abs((e.getY()-ellipseStartY));
					double a = width>height?width/2:height/2;
					double b =  width<height?width/2:height/2;
					double ellipseCursorStart;
					double cursorstartminus;
					int count = 100;
					if(Math.abs((ellipseStartX-e.getX()))>Math.abs(ellipseStartY-e.getY())){
						
						ellipseCursorStart = coreX;
						cursorstartminus = coreX;
						coreY = ((coreY)*100/distanceEachLine);
						coreY = coreY/100;
						corex = ((corex)*100/distanceEachLine);
						corex = corex/100;
						while(ellipseCursorStart<coreX+a+10){   
							double drawX = ellipseCursorStart;
							double drawY = Math.sqrt(((b*b-b*b*(ellipseCursorStart-coreX)*(ellipseCursorStart-coreX)/(a*a))));
							drawX = ((drawX)*100/distanceEachLine);
							drawX = drawX/100;
							drawY = ((drawY)*100/distanceEachLine);
							drawY = drawY/100;
							if(!(ellipseTempX==(int)drawX&&ellipseTempY==(int)drawY)){
								for(;ellipseTempY>drawY;ellipseTempY--){
									paintThings.offerLast(new Object[]{"ellipse",(int)drawX, 
											coreY+(int)ellipseTempY,currentColor});
									paintThings.offerLast(new Object[]{"ellipse",(int)drawX, 
											coreY-(int)ellipseTempY,currentColor});
									paintThings.offerLast(new Object[]{"ellipse",2*corex-(int)drawX, 
											coreY+(int)ellipseTempY,currentColor});
									paintThings.offerLast(new Object[]{"ellipse",2*corex-(int)drawX, 
											coreY-(int)ellipseTempY,currentColor});	
								}
								if(drawY>0){
									paintThings.offerLast(new Object[]{"ellipse",(int)drawX, 
											coreY+(int)drawY,currentColor});
									paintThings.offerLast(new Object[]{"ellipse",(int)drawX, 
											coreY-(int)drawY,currentColor});
									paintThings.offerLast(new Object[]{"ellipse",2*corex-(int)drawX, 
											coreY+(int)drawY,currentColor});
									paintThings.offerLast(new Object[]{"ellipse",2*corex-(int)drawX, 
											coreY-(int)drawY,currentColor});
								}
								else {
									for(;ellipseTempY>=0;ellipseTempY--){
										paintThings.offerLast(new Object[]{"ellipse",(int)ellipseTempX+1, 
												coreY+(int)ellipseTempY,currentColor});
										paintThings.offerLast(new Object[]{"ellipse",(int)ellipseTempX+1, 
												coreY-(int)ellipseTempY,currentColor});
										paintThings.offerLast(new Object[]{"ellipse",2*corex-(int)ellipseTempX-1, 
												coreY+(int)ellipseTempY,currentColor});
										paintThings.offerLast(new Object[]{"ellipse",2*corex-(int)ellipseTempX-1, 
												coreY-(int)ellipseTempY,currentColor});	
									}
								}
							}
							ellipseTempY = (int)drawY;
							ellipseTempX = (int)drawX;
							ellipseCursorStart = ellipseCursorStart +5;
							count--;
						}
					}
					else{
						ellipseCursorStart = coreY;
						cursorstartminus = coreY;
						coreX = ((coreX)*100/distanceEachLine);
						coreX = coreX/100;
						corey = ((corey)*100/distanceEachLine);
						corey = corey/100;
						while(ellipseCursorStart<coreY+a+5){   
							double drawY = ellipseCursorStart;
							double drawX = Math.sqrt(((b*b-b*b*(ellipseCursorStart-coreY)*(ellipseCursorStart-coreY)/(a*a))));
							drawX = ((drawX)*100/distanceEachLine);
							drawX = drawX/100;
							drawY = ((drawY)*100/distanceEachLine);
							drawY = drawY/100;
							if(!(ellipseTempX==(int)drawX&&ellipseTempY==(int)drawY)){
								for(;ellipseTempX>drawX;ellipseTempX--){
									paintThings.offerLast(new Object[]{"ellipse",(int)ellipseTempX+coreX, 
											(int)ellipseTempY,currentColor});
									paintThings.offerLast(new Object[]{"ellipse",coreX-(int)ellipseTempX, 
											(int)ellipseTempY,currentColor});
									paintThings.offerLast(new Object[]{"ellipse",(int)ellipseTempX+coreX, 
											2*corey-(int)ellipseTempY,currentColor});
									paintThings.offerLast(new Object[]{"ellipse",coreX-(int)ellipseTempX, 
											2*corey-(int)ellipseTempY,currentColor});	
								}
								if(drawX>0){
									paintThings.offerLast(new Object[]{"ellipse",(int)drawX+coreX, 
											(int)drawY,currentColor});
									paintThings.offerLast(new Object[]{"ellipse",coreX-(int)drawX, 
											(int)drawY,currentColor});
									paintThings.offerLast(new Object[]{"ellipse",(int)drawX+coreX, 
											2*corey-(int)drawY,currentColor});
									paintThings.offerLast(new Object[]{"ellipse",coreX-(int)drawX, 
											2*corey-(int)drawY,currentColor});	
								}
								else {
									for(;ellipseTempX>=0;ellipseTempX--){
										paintThings.offerLast(new Object[]{"ellipse",(int)ellipseTempX+coreX, 
												(int)ellipseTempY,currentColor});
										paintThings.offerLast(new Object[]{"ellipse",coreX-(int)ellipseTempX, 
												(int)ellipseTempY,currentColor});
										paintThings.offerLast(new Object[]{"ellipse",(int)ellipseTempX+coreX, 
												2*corey-(int)ellipseTempY,currentColor});
										paintThings.offerLast(new Object[]{"ellipse",coreX-(int)ellipseTempX, 
												2*corey-(int)ellipseTempY,currentColor});	
									}
								}
								
								
								
							}
							ellipseTempX = (int)drawX;
							ellipseTempY = (int)drawY;
							ellipseCursorStart = ellipseCursorStart +2;
							count--;
						}
					}
				}
				if(isFillEllipse){
					int coreX = (ellipseStartX+e.getX())/2;
					int corex = coreX;
					int coreY = (ellipseStartY+e.getY())/2;
					int corey = coreY;
					double tempY = -1;
					width =  Math.abs((e.getX()-ellipseStartX));
					height = Math.abs((e.getY()-ellipseStartY));
					double a = width>height?width/2:height/2;
					double b =  width<height?width/2:height/2;
					double ellipseCursorStart;
					double cursorstartminus;
					int count = 100;
					if(Math.abs((ellipseStartX-e.getX()))>Math.abs(ellipseStartY-e.getY())){
						ellipseCursorStart = coreX;
						cursorstartminus = coreX;
						coreY = ((coreY)*100/distanceEachLine);
						coreY = coreY/100;
						
						corex = ((corex)*100/distanceEachLine);
						corex = corex/100;
						
						while(ellipseCursorStart<coreX+a+10){   
							double drawX = ellipseCursorStart;
							double drawY = Math.sqrt(((b*b-b*b*(ellipseCursorStart-coreX)*(ellipseCursorStart-coreX)/(a*a))));
							drawX = ((drawX)*100/distanceEachLine);
							drawX = drawX/100;
							drawY = ((drawY)*100/distanceEachLine);
							drawY = drawY/100;
							if(tempY!=drawY){
								paintThings.offerLast(new Object[]{"fillEllipse",(int)drawX, 
										coreY-(int)drawY,2*(int)drawY*distanceEachLine,currentColor});
								paintThings.offerLast(new Object[]{"fillEllipse",2*corex-(int)drawX, 
										coreY-(int)drawY,2*(int)drawY*distanceEachLine,currentColor});
							}
							tempY = drawY;
							ellipseCursorStart = ellipseCursorStart + 3;
							count--;
						}
					}
					else{
						ellipseCursorStart = coreY;
						cursorstartminus = coreY;
						coreX = ((coreX)*100/distanceEachLine);
						coreX = coreX/100;
						corey = ((corey)*100/distanceEachLine);
						corey = corey/100;
						while(ellipseCursorStart<coreY+a+5){   
							double drawY = ellipseCursorStart;
							double drawX = Math.sqrt(((b*b-b*b*(ellipseCursorStart-coreY)*(ellipseCursorStart-coreY)/(a*a))));
							drawX = ((drawX)*100/distanceEachLine);
							drawX = drawX/100;
							drawY = ((drawY)*100/distanceEachLine);
							drawY = drawY/100;
							if(tempY!=drawY){
								paintThings.offerLast(new Object[]{"fillEllipse",(int)drawX+coreX, 
										2*corey-(int)drawY,(int)(2*(drawY-corey))*distanceEachLine,currentColor});
								paintThings.offerLast(new Object[]{"fillEllipse",coreX-(int)drawX, 
										2*corey-(int)drawY,(int)(2*(drawY-corey))*distanceEachLine,currentColor});
							}
							tempY = drawY;
							ellipseCursorStart = ellipseCursorStart + 1;
							count--;
						}
					}
				}
				if(isFillRectangle&&floatEndY>0&&floatEndX>0){
					paintThings.offerLast(new Object[]{"fillrectangle",floatStartX<floatEndX?floatStartX:floatEndX
																  ,floatStartY<floatEndY?floatStartY:floatEndY
																  ,Math.abs(floatEndX-floatStartX)
																  ,Math.abs(floatEndY-floatStartY),currentColor});
				}
				if(isRectangle&&floatEndY>0&&floatEndX>0){
					paintThings.offerLast(new Object[]{"rectangle",floatStartX<floatEndX?floatStartX:floatEndX
							  ,floatStartY<floatEndY?floatStartY:floatEndY
							  ,Math.abs(floatEndX-floatStartX)
							  ,Math.abs(floatEndY-floatStartY),currentColor});
				}
				floatStartX = 0;
				floatStartY = 0;
				floatEndX = 0;
				floatEndY = 0;
				LayoutEditorPattern.this.repaint();
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			public LinkedList getPaintThings() {
				return paintThings;
			}
		 @Override
		public void paint(Graphics g) {
			super.paint(g);
		
			if(canRead){
				g.drawImage(gridImageread,0,0,lineLength,lineLength,null);
				gridGraphics.drawImage(gridImageread,0,0,lineLength,lineLength,null);
			}
			g.setColor(currentColor);
			gridGraphics.setColor(currentColor);
			if(isLine){
				g.drawLine(floatStartX, floatStartY, floatEndX, floatEndY);
			}
			else if(isRectangle||isFillRectangle){
				g.drawRect(dragX, dragY, Math.abs(floatEndX-floatStartX), Math.abs(floatEndY-floatStartY));
			}
			else if(isEllipse||isFillEllipse){
				g.drawOval(dragX, dragY,Math.abs(floatEndX-floatStartX), Math.abs(floatEndY-floatStartY));
			}
			
			java.util.Iterator iterator = paintThings.iterator();
			while(iterator.hasNext()){
				Object[] paintElements = (Object[]) iterator.next() ;
				if(((String)paintElements[0]).equalsIgnoreCase("point")||((String)paintElements[0]).equalsIgnoreCase("line")
						||((String)paintElements[0]).equalsIgnoreCase("ellipse")){
					g.setColor((Color) paintElements[3]);
					gridGraphics.setColor((Color) paintElements[3]);
					g.fillRect((Integer)paintElements[1]*distanceEachLine, 
							(Integer)paintElements[2]*distanceEachLine, 
							distanceEachLine, distanceEachLine);
					gridGraphics.fillRect((Integer)paintElements[1]*distanceEachLine, 
							(Integer)paintElements[2]*distanceEachLine, 
							distanceEachLine, distanceEachLine);
				}
				else if(((String)paintElements[0]).equalsIgnoreCase("fillEllipse")){
					g.setColor((Color) paintElements[4]);
					gridGraphics.setColor((Color) paintElements[4]);
					g.fillRect((Integer)paintElements[1]*distanceEachLine, 
							(Integer)paintElements[2]*distanceEachLine, 
							distanceEachLine, (Integer)paintElements[3]);
					gridGraphics.fillRect((Integer)paintElements[1]*distanceEachLine, 
							(Integer)paintElements[2]*distanceEachLine, 
							distanceEachLine, (Integer)paintElements[3]);
				}
				else if(((String)paintElements[0]).equalsIgnoreCase("erase")){
					g.setColor(Color.WHITE);
					gridGraphics.setColor(Color.WHITE);
					g.fillRect((Integer)paintElements[1]*distanceEachLine, 
							(Integer)paintElements[2]*distanceEachLine, 
							distanceEachLine*gridCount/16, distanceEachLine*gridCount/16);
					gridGraphics.fillRect((Integer)paintElements[1]*distanceEachLine, 
							(Integer)paintElements[2]*distanceEachLine, 
							20, 20);
				}
				else if(((String)paintElements[0]).equalsIgnoreCase("fillrectangle")){
					g.setColor((Color) paintElements[5]);
					g.fillRect((Integer)paintElements[1]*distanceEachLine
								,(Integer)paintElements[2]*distanceEachLine
								,(Integer)paintElements[3]*distanceEachLine
								,(Integer)paintElements[4]*distanceEachLine);
					g.setColor(Color.BLACK);
					gridGraphics.setColor((Color) paintElements[5]);
					gridGraphics.fillRect((Integer)paintElements[1]*distanceEachLine
								,(Integer)paintElements[2]*distanceEachLine
								,(Integer)paintElements[3]*distanceEachLine
								,(Integer)paintElements[4]*distanceEachLine);
					gridGraphics.setColor(Color.BLACK);
					
				}
				else if(((String)paintElements[0]).equalsIgnoreCase("rectangle")){
					g.setColor((Color) paintElements[5]);
					g.fillRect((Integer)paintElements[1]*distanceEachLine,
							 (Integer)paintElements[2]*distanceEachLine, 
							 distanceEachLine, 
							 (Integer)paintElements[4]*distanceEachLine);
					g.fillRect((Integer)paintElements[1]*distanceEachLine,
							 (Integer)paintElements[2]*distanceEachLine, 
							 (Integer)paintElements[3]*distanceEachLine, 
							 distanceEachLine);
					g.fillRect(((Integer)paintElements[1]+(Integer)paintElements[3])*distanceEachLine,
							 (Integer)paintElements[2]*distanceEachLine, 
							 distanceEachLine, 
							 (Integer)paintElements[4]*distanceEachLine);
					g.fillRect((Integer)paintElements[1]*distanceEachLine,
							 ((Integer)paintElements[2]+(Integer)paintElements[4])*distanceEachLine, 
							 ((Integer)paintElements[3]+1)*distanceEachLine, 
							 distanceEachLine);
					gridGraphics.setColor((Color) paintElements[5]);
					gridGraphics.fillRect((Integer)paintElements[1]*distanceEachLine,
							 (Integer)paintElements[2]*distanceEachLine, 
							 distanceEachLine, 
							 (Integer)paintElements[4]*distanceEachLine);
					gridGraphics.fillRect((Integer)paintElements[1]*distanceEachLine,
							 (Integer)paintElements[2]*distanceEachLine, 
							 (Integer)paintElements[3]*distanceEachLine, 
							 distanceEachLine);
					gridGraphics.fillRect(((Integer)paintElements[1]+(Integer)paintElements[3])*distanceEachLine,
							 (Integer)paintElements[2]*distanceEachLine, 
							 distanceEachLine, 
							 (Integer)paintElements[4]*distanceEachLine);
					gridGraphics.fillRect((Integer)paintElements[1]*distanceEachLine,
							 ((Integer)paintElements[2]+(Integer)paintElements[4])*distanceEachLine, 
							 ((Integer)paintElements[3]+1)*distanceEachLine, 
							 distanceEachLine);
				}
			}
			g.setColor(Color.BLACK);
			for(int i=0;i<=gridCount;i++){
				g.drawLine(0, i*distanceEachLine , 320, i*distanceEachLine );
				g.drawLine(i*distanceEachLine ,0 , i*distanceEachLine , 320 );
			}
		}
	 }
 
	 private class ColorChooserPanel extends JFrame{
			
			private JColorChooser jColorChooser;
			private JButton buttonOk;
			private JButton buttonCancel;
			private Object object;
			public ColorChooserPanel(int x,int y) {
				setBounds(x, y, 460, 320);
				setLayout(null);
				jColorChooser=new JColorChooser();
				jColorChooser.setBounds(0, 0, 439, 247);
				add(jColorChooser);
				buttonOk = new JButton("OK");
				buttonOk.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						 currentColor = jColorChooser.getColor();
						 panelCurrentColor.setBackground(jColorChooser.getColor());
							ColorChooserPanel.this.dispose();
						
					}
				});
				buttonOk.setBounds(107, 253, 76, 23);
				add(buttonOk);
				
				buttonCancel = new JButton("Cancel");
				buttonCancel.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						ColorChooserPanel.this.dispose();
					}
				});
				buttonCancel.setBounds(216, 253, 76, 23);
				add(buttonCancel);
				setVisible(true);
			}
		}
	
	 public LayoutEditorPattern(int x,int y, JFrame jFrame) {
		 super(jFrame,true);
		 this.setBounds(x,y,380, 510);
		 buttonGroup = new ButtonGroup();
		 getContentPane().setLayout(null);
		 setTitle("Pattern Editor");
		 panelChooseColor = new JPanel();
		 panelChooseColor.setBorder(new LineBorder(Color.LIGHT_GRAY));
		 panelChooseColor.setBounds(10, 10, 354, 70);
		 getContentPane().add(panelChooseColor);
		 panelChooseColor.setLayout(null);
		 
		 panelCurrentColor = new JPanel();
		 panelCurrentColor.setToolTipText("Current Color");
		 panelCurrentColor.setBorder(new LineBorder(Color.BLACK));
		 panelCurrentColor.addMouseListener(new MouseAdapter() {
			 @Override
			public void mouseClicked(MouseEvent e) {
				super.mouseClicked(e);
				new ColorChooserPanel(getX()+e.getX(), getY()+e.getY());
			}
		});
		 panelCurrentColor.setBackground(Color.BLACK);
		 panelCurrentColor.setBounds(10, 10, 24, 24);
		 panelChooseColor.add(panelCurrentColor);
		 
		 panelBlack = new JPanel();
		 panelBlack.setToolTipText("Black");
		 panelBlack.addMouseListener(new MouseAdapter() {
			 @Override
			public void mouseClicked(MouseEvent e) {
				super.mouseClicked(e);
				panelCurrentColor.setBackground(Color.BLACK);
				currentColor = Color.BLACK;
			}
		});
		 panelBlack.setBorder(new LineBorder(Color.BLACK));
		 panelBlack.setBackground(Color.BLACK);
		 panelBlack.setBounds(44, 10, 24, 24);
		 panelChooseColor.add(panelBlack);
		 
		 panelWhite = new JPanel();
		 panelWhite.setToolTipText("White");
		 panelWhite.addMouseListener(new MouseAdapter() {
			 @Override
			public void mouseClicked(MouseEvent e) {
				super.mouseClicked(e);
				panelCurrentColor.setBackground(Color.WHITE);
				currentColor = Color.WHITE;
			}
		});
		 panelWhite.setBorder(new LineBorder(Color.BLACK));
		 panelWhite.setBackground(Color.WHITE);
		 panelWhite.setBounds(78, 10, 24, 24);
		 panelChooseColor.add(panelWhite);
		 
		 panelRed = new JPanel();
		 panelRed.setToolTipText("Red");
		 panelRed.addMouseListener(new MouseAdapter() {
			 @Override
			public void mouseClicked(MouseEvent e) {
				super.mouseClicked(e);
				panelCurrentColor.setBackground(Color.RED);
				currentColor = Color.RED;
			}
		});
		 panelRed.setBorder(new LineBorder(Color.BLACK));
		 panelRed.setBackground(Color.RED);
		 panelRed.setBounds(112, 10, 24, 24);
		 panelChooseColor.add(panelRed);
		 
		 panelGreen = new JPanel();
		 panelGreen.setToolTipText("Green");
		 panelGreen.addMouseListener(new MouseAdapter() {
			 @Override
			public void mouseClicked(MouseEvent e) {
				super.mouseClicked(e);
				panelCurrentColor.setBackground(Color.GREEN);
				currentColor = Color.GREEN;
			}
		});
		 panelGreen.setBorder(new LineBorder(Color.BLACK));
		 panelGreen.setBackground(Color.GREEN);
		 panelGreen.setBounds(146, 10, 24, 24);
		 panelChooseColor.add(panelGreen);
		 
		 panelBlue = new JPanel();
		 panelBlue.setToolTipText("Blue");
		 panelBlue.addMouseListener(new MouseAdapter() {
			 @Override
			public void mouseClicked(MouseEvent e) {
				super.mouseClicked(e);
				panelCurrentColor.setBackground(Color.BLUE);
				currentColor = Color.BLUE;
			}
		});
		 panelBlue.setBorder(new LineBorder(Color.BLACK));
		 panelBlue.setBackground(Color.BLUE);
		 panelBlue.setBounds(180, 10, 24, 24);
		 panelChooseColor.add(panelBlue);
		 
		 panelYellow = new JPanel();
		 panelYellow.setToolTipText("Yellow");
		 panelYellow.addMouseListener(new MouseAdapter() {
			 @Override
			public void mouseClicked(MouseEvent e) {
				super.mouseClicked(e);
				panelCurrentColor.setBackground(Color.YELLOW);
				currentColor = Color.YELLOW;
			}
		});
		 panelYellow.setBorder(new LineBorder(Color.BLACK));
		 panelYellow.setBackground(Color.YELLOW);
		 panelYellow.setBounds(214, 10, 24, 24);
		 panelChooseColor.add(panelYellow);
		 
		 panelMagenta = new JPanel();
		 panelMagenta.setToolTipText("Magenta");
		 panelMagenta.addMouseListener(new MouseAdapter() {
			 @Override
			public void mouseClicked(MouseEvent e) {
				super.mouseClicked(e);
				panelCurrentColor.setBackground(Color.MAGENTA);
				currentColor = Color.MAGENTA;
			}
		});
		 panelMagenta.setBorder(new LineBorder(Color.BLACK));
		 panelMagenta.setBackground(Color.MAGENTA);
		 panelMagenta.setBounds(248, 10, 24, 24);
		 panelChooseColor.add(panelMagenta);
		 
		 panelCyan = new JPanel();
		 panelCyan.setToolTipText("Cyan");
		 panelCyan.addMouseListener(new MouseAdapter() {
			 @Override
			public void mouseClicked(MouseEvent e) {
				super.mouseClicked(e);
				panelCurrentColor.setBackground(Color.CYAN);
				currentColor = Color.CYAN;
			}
		});
		 panelCyan.setBorder(new LineBorder(Color.BLACK));
		 panelCyan.setBackground(Color.CYAN);
		 panelCyan.setBounds(282, 10, 24, 24);
		 panelChooseColor.add(panelCyan);
		 
		 panelGray = new JPanel();
		 panelGray.setToolTipText("Gray");
		 panelGray.addMouseListener(new MouseAdapter() {
			 @Override
			public void mouseClicked(MouseEvent e) {
				super.mouseClicked(e);
				panelCurrentColor.setBackground(Color.GRAY);
				currentColor = Color.GRAY;
			}
		});
		 panelGray.setBorder(new LineBorder(Color.BLACK));
		 panelGray.setBackground(Color.GRAY);
		 panelGray.setBounds(316, 10, 24, 24);
		 panelChooseColor.add(panelGray);
		 
		 JLabel lblSize = new JLabel("Size");
		 lblSize.setBounds(10, 36, 31, 27);
		 panelChooseColor.add(lblSize);
		 
		 comboBox = new JComboBox();
		 comboBox.addItem("16x16");
		 comboBox.addItem("32x32");
		 comboBox.addItem("64x64");
		 comboBox.setSelectedItem("32x32");
		 comboBox.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				if(e.getStateChange()==ItemEvent.SELECTED){//TODO
					panelGrid.getPaintThings().clear();
					panelGrid.repaint();
					panelGrid.setGridCount(Integer.parseInt(((String)e.getItem()).substring(0, 2)));
				}
			}
		});
		 comboBox.setBounds(40, 39, 62, 21);
		 panelChooseColor.add(comboBox);
		 
		 btnPoint = new JButton("",new ImageIcon(JCGMEditor.class.getResource("/images/pointOK.gif")));
		 btnPoint.setToolTipText("Point");
		 isPoint = true;
		 btnPoint.setBackground(Color.WHITE);
		 btnPoint.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				isPoint = true;
				isRectangle = false;
				isLine = false;
				isEllipse = false;
				isErase = false;
				isFillEllipse = false;
				isFillRectangle = false;
				btnPoint.setIcon(new ImageIcon(JCGMEditor.class.getResource("/images/pointOK.gif")));
				btnLine.setIcon(new ImageIcon(JCGMEditor.class.getResource("/images/Line.gif")));
				btnRectangle.setIcon(new ImageIcon(JCGMEditor.class.getResource("/images/rectangle.gif")));
				btnFillRectangle.setIcon(new ImageIcon(JCGMEditor.class.getResource("/images/fillRectangle.gif")));
				btnEllipse.setIcon(new ImageIcon(JCGMEditor.class.getResource("/images/ellipse.gif")));
				btnFillEllipse.setIcon(new ImageIcon(JCGMEditor.class.getResource("/images/fillEllipse.gif")));
				btnErase.setIcon(new ImageIcon(JCGMEditor.class.getResource("/images/erase.gif")));
			}
		});
		 buttonGroup.add(btnPoint);
		 btnPoint.setBounds(112, 36, 24, 24);
		 panelChooseColor.add(btnPoint);
		 
		 btnLine = new JButton("",new ImageIcon(JCGMEditor.class.getResource("/images/Line.gif")));
		 btnLine.setToolTipText("Line");
		 btnLine.setBackground(Color.WHITE);
		 btnLine.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				isPoint = false;
				isRectangle = false;
				isLine = true;
				isEllipse = false;
				isErase = false;
				isFillEllipse = false;
				isFillRectangle = false;
				btnPoint.setIcon(new ImageIcon(JCGMEditor.class.getResource("/images/point.gif")));
				btnLine.setIcon(new ImageIcon(JCGMEditor.class.getResource("/images/LineOK.gif")));
				btnRectangle.setIcon(new ImageIcon(JCGMEditor.class.getResource("/images/rectangle.gif")));
				btnFillRectangle.setIcon(new ImageIcon(JCGMEditor.class.getResource("/images/fillRectangle.gif")));
				btnEllipse.setIcon(new ImageIcon(JCGMEditor.class.getResource("/images/ellipse.gif")));
				btnFillEllipse.setIcon(new ImageIcon(JCGMEditor.class.getResource("/images/fillEllipse.gif")));
				btnErase.setIcon(new ImageIcon(JCGMEditor.class.getResource("/images/erase.gif")));
			}
		});
		 buttonGroup.add(btnLine);
		 btnLine.setBounds(146, 36, 24, 24);
		 panelChooseColor.add(btnLine);
		 
		 btnRectangle = new JButton("",new ImageIcon(JCGMEditor.class.getResource("/images/rectangle.gif")));
		 btnRectangle.setToolTipText("Rectangle");
		 btnRectangle.setBackground(Color.WHITE);
		 btnRectangle.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					isPoint = false;
					isRectangle = true;
					isLine = false;
					isEllipse = false;
					isErase = false;
					isFillEllipse = false;
					isFillRectangle = false;
					btnPoint.setIcon(new ImageIcon(JCGMEditor.class.getResource("/images/point.gif")));
					btnLine.setIcon(new ImageIcon(JCGMEditor.class.getResource("/images/Line.gif")));
					btnRectangle.setIcon(new ImageIcon(JCGMEditor.class.getResource("/images/rectangleOK.gif")));
					btnFillRectangle.setIcon(new ImageIcon(JCGMEditor.class.getResource("/images/fillRectangle.gif")));
					btnEllipse.setIcon(new ImageIcon(JCGMEditor.class.getResource("/images/ellipse.gif")));
					btnFillEllipse.setIcon(new ImageIcon(JCGMEditor.class.getResource("/images/fillEllipse.gif")));
					btnErase.setIcon(new ImageIcon(JCGMEditor.class.getResource("/images/erase.gif")));
				}
			});
		 buttonGroup.add(btnRectangle);
		 btnRectangle.setBounds(178, 36, 24, 24);
		 panelChooseColor.add(btnRectangle);
		 
		 btnFillRectangle = new JButton("",new ImageIcon(JCGMEditor.class.getResource("/images/fillRectangle.gif")));
		 btnFillRectangle.setToolTipText("Fill Rectangle");
		 btnFillRectangle.setBackground(Color.WHITE);
		 btnFillRectangle.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					isPoint = false;
					isRectangle = false;
					isLine = false;
					isEllipse = false;
					isErase = false;
					isFillEllipse = false;
					isFillRectangle = true;
					btnPoint.setIcon(new ImageIcon(JCGMEditor.class.getResource("/images/point.gif")));
					btnLine.setIcon(new ImageIcon(JCGMEditor.class.getResource("/images/Line.gif")));
					btnRectangle.setIcon(new ImageIcon(JCGMEditor.class.getResource("/images/rectangle.gif")));
					btnFillRectangle.setIcon(new ImageIcon(JCGMEditor.class.getResource("/images/fillRectangleOK.gif")));
					btnEllipse.setIcon(new ImageIcon(JCGMEditor.class.getResource("/images/ellipse.gif")));
					btnFillEllipse.setIcon(new ImageIcon(JCGMEditor.class.getResource("/images/fillEllipse.gif")));
					btnErase.setIcon(new ImageIcon(JCGMEditor.class.getResource("/images/erase.gif")));
				}
			});
		 buttonGroup.add(btnFillRectangle);
		 btnFillRectangle.setBounds(214, 36, 24, 24);
		 panelChooseColor.add(btnFillRectangle);
		 
		 btnEllipse = new JButton("",new ImageIcon(JCGMEditor.class.getResource("/images/ellipse.gif")));
		 btnEllipse.setToolTipText("Ellipse");
		 btnEllipse.setBackground(Color.WHITE);
		 btnEllipse.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					isPoint = false;
					isRectangle = false;
					isLine = false;
					isEllipse = true;
					isErase = false;
					isFillEllipse = false;
					isFillRectangle = false;
					btnPoint.setIcon(new ImageIcon(JCGMEditor.class.getResource("/images/point.gif")));
					btnLine.setIcon(new ImageIcon(JCGMEditor.class.getResource("/images/Line.gif")));
					btnRectangle.setIcon(new ImageIcon(JCGMEditor.class.getResource("/images/rectangle.gif")));
					btnFillRectangle.setIcon(new ImageIcon(JCGMEditor.class.getResource("/images/fillRectangle.gif")));
					btnEllipse.setIcon(new ImageIcon(JCGMEditor.class.getResource("/images/ellipseOK.gif")));
					btnFillEllipse.setIcon(new ImageIcon(JCGMEditor.class.getResource("/images/fillEllipse.gif")));
					btnErase.setIcon(new ImageIcon(JCGMEditor.class.getResource("/images/erase.gif")));
				}
			});
		 buttonGroup.add(btnEllipse);
		 btnEllipse.setBounds(248, 36, 24, 24);
		 panelChooseColor.add(btnEllipse);
		 
		 btnFillEllipse = new JButton("",new ImageIcon(JCGMEditor.class.getResource("/images/fillEllipse.gif")));
		 btnFillEllipse.setToolTipText("Fill Ellipse");
		 btnFillEllipse.setBackground(Color.WHITE);
		 btnFillEllipse.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					isPoint = false;
					isRectangle = false;
					isLine = false;
					isEllipse = false;
					isErase = false;
					isFillEllipse = true;
					isFillRectangle = false;
					btnPoint.setIcon(new ImageIcon(JCGMEditor.class.getResource("/images/point.gif")));
					btnLine.setIcon(new ImageIcon(JCGMEditor.class.getResource("/images/Line.gif")));
					btnRectangle.setIcon(new ImageIcon(JCGMEditor.class.getResource("/images/rectangle.gif")));
					btnFillRectangle.setIcon(new ImageIcon(JCGMEditor.class.getResource("/images/fillRectangle.gif")));
					btnEllipse.setIcon(new ImageIcon(JCGMEditor.class.getResource("/images/ellipse.gif")));
					btnFillEllipse.setIcon(new ImageIcon(JCGMEditor.class.getResource("/images/fillEllipseOK.gif")));
					btnErase.setIcon(new ImageIcon(JCGMEditor.class.getResource("/images/erase.gif")));
				}
			});
		 buttonGroup.add(btnFillEllipse);
		 btnFillEllipse.setBounds(282, 36, 24, 24);
		 panelChooseColor.add(btnFillEllipse);
		 
		 btnErase = new JButton("",new ImageIcon(JCGMEditor.class.getResource("/images/erase.gif")));
		 btnErase.setToolTipText("Eraser");
		 btnErase.setBackground(Color.WHITE);
		 btnErase.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					isPoint = false;
					isRectangle = false;
					isLine = false;
					isEllipse = false;
					isErase = true;
					isFillEllipse = false;
					isFillRectangle = false;
					btnPoint.setIcon(new ImageIcon(JCGMEditor.class.getResource("/images/point.gif")));
					btnLine.setIcon(new ImageIcon(JCGMEditor.class.getResource("/images/Line.gif")));
					btnRectangle.setIcon(new ImageIcon(JCGMEditor.class.getResource("/images/rectangle.gif")));
					btnFillRectangle.setIcon(new ImageIcon(JCGMEditor.class.getResource("/images/fillRectangle.gif")));
					btnEllipse.setIcon(new ImageIcon(JCGMEditor.class.getResource("/images/ellipse.gif")));
					btnFillEllipse.setIcon(new ImageIcon(JCGMEditor.class.getResource("/images/fillEllipse.gif")));
					btnErase.setIcon(new ImageIcon(JCGMEditor.class.getResource("/images/eraseOK.gif")));
				}
			});
		 buttonGroup.add(btnErase);
		 btnErase.setBounds(316, 36, 24, 24);
		 panelChooseColor.add(btnErase);
		 
		 panelGrid = new GridPanel(32);
		 panelGrid.setBackground(Color.WHITE);
		 panelGrid.setBounds(20, 90, 321, 321);
		 getContentPane().add(panelGrid);
		 
		 btnOpen = new JButton("Open");
		 btnOpen.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					JFileChooser jFileChooser = new JFileChooser();
					int retval = jFileChooser.showOpenDialog(LayoutEditorPattern.this);
					if( retval == JFileChooser.APPROVE_OPTION){ 
					      File filePath = jFileChooser.getSelectedFile();
					      panelGrid.getPaintThings().clear();
					      panelGrid.setGridImage(ImageIO.read(filePath));
					      panelGrid.setCanRead(true);
					}  
				} catch (Exception e1) {
					e1.printStackTrace();// TODO: handle exception
				}
			}
		});
		 btnOpen.setBounds(22, 432, 75, 23);
		 getContentPane().add(btnOpen);
		 
		 btnSave = new JButton("Save");
		 btnSave.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					panelGrid.repaint();
					JFileChooser jFileChooser = new JFileChooser();
					int value = jFileChooser.showSaveDialog(LayoutEditorPattern.this);
				    if( value == JFileChooser.APPROVE_OPTION){
				    	File filePath = jFileChooser.getSelectedFile();
				    	ImageIO.write(panelGrid.getGridImage(), "jpeg",new File(filePath.toString()+".jpg"));
				    }
				} catch (Exception e1) {
					e1.printStackTrace();// TODO: handle exception
				}
				
			}
		});
		 btnSave.setBounds(107, 432, 75, 23);
		 getContentPane().add(btnSave);
		 
		 btnClear = new JButton("Clear");
		 btnClear.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				panelGrid.setCanRead(false);
				panelGrid.getPaintThings().clear();
				panelGrid.clearGraphic();
				panelGrid.repaint();
			}
		});
		 btnClear.setBounds(192, 432, 75, 23);
		 getContentPane().add(btnClear);
		 
		 btnClose = new JButton("Close");
		 btnClose.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				LayoutEditorPattern.this.dispose();
			}
		});
		 btnClose.setBounds(277, 432, 75, 23);
		 getContentPane().add(btnClose);
		 setVisible(true);
	 }
	
 }
	  