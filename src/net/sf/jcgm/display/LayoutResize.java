package net.sf.jcgm.display;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Box;
import javax.swing.border.TitledBorder;
import javax.swing.UIManager;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Panel;
import java.awt.Rectangle;

import javax.swing.BoxLayout;
import java.awt.GridBagLayout;
import javax.swing.JTextField;
import java.awt.GridBagConstraints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.border.LineBorder;
import javax.swing.JLabel;
import javax.swing.JButton;

import org.omg.CORBA.PUBLIC_MEMBER;

import java.awt.SystemColor;

 

 
public class LayoutResize extends JFrame{
	private JTextField textFieldTop;
	private JTextField textFieldLeft;
	private JTextField textFieldRight;
	private JTextField textFieldButtom;
	private JTextField textFieldWidth;
	private JTextField textFieldHeight;
	private MovablePanel innerPanel;
	private static final int screenWidth=(int)java.awt.Toolkit.getDefaultToolkit().getScreenSize().width*65535/1280;
	private static final int screenHeight = (int)java.awt.Toolkit.getDefaultToolkit().getScreenSize().height*65535/800;
	
	private class MovablePanel extends JPanel implements MouseListener , MouseMotionListener{
		private Rectangle thisSpace;
		private Rectangle leftTop;
		private Rectangle leftBottom;
		private Rectangle rightTop;
		private Rectangle rightBottom;
		private Rectangle topBar;
		private Rectangle buttomBar;
		private Rectangle leftBar;
		private Rectangle rightBar;
		private Rectangle where;
		private int drawX;
		private int drawY;
		private int drawWidth;
		private int drawHeight;
		private static final int primaryX = 63;
		private static final int primaryY = 51;
		private static final int primaryWidth = 95;
		private static final int primaryHeight = 95;
		private int coreX = (primaryX+primaryWidth/2)*screenWidth/65535;
		private int coreY = (primaryY+primaryHeight/2)*screenHeight/65535;
		private static final int lineWidth =2;
		private static final int pointWidth = 6;
		private static final int pointOffset = pointWidth/2-lineWidth/2;
		public MovablePanel(){
			super();
			drawX = primaryX*screenWidth/65535;
			drawY = primaryY*screenHeight/65535;
			drawWidth = primaryWidth*screenWidth/65535;
			drawHeight = primaryHeight*screenHeight/65535;
			where = new Rectangle();;
			thisSpace = new Rectangle();
			leftTop =  new Rectangle();
			leftBottom = new Rectangle();
			rightTop =  new Rectangle();
			rightBottom =  new Rectangle();
			topBar = new Rectangle();
			buttomBar = new Rectangle();
			leftBar = new Rectangle();
			rightBar = new Rectangle();
			addMouseListener(this);
			addMouseMotionListener(this);
		}
		public void reset(){
			drawX = primaryX;
			drawY = primaryY;
			drawWidth = primaryWidth;
			drawHeight = primaryHeight;
			LayoutResize.this.repaint();
		}
		 
		public void resetPosition(int primaryX,int primaryY,int primaryWidth,int primaryHeight,Graphics g){
			g.setColor(Color.WHITE);
			g.fillRect(primaryX, primaryY, primaryWidth, primaryHeight);
			g.setColor(Color.BLUE);
			g.fillRect(primaryX, primaryY, primaryWidth, lineWidth);
			g.fillRect(primaryX, primaryY, lineWidth, primaryHeight);
			g.fillRect(primaryX+primaryWidth, primaryY, lineWidth, primaryHeight+lineWidth);
			g.fillRect(primaryX, primaryY+primaryHeight, primaryWidth+lineWidth, lineWidth);
			g.setColor(Color.RED);
			g.fillRect(primaryX-pointOffset, primaryY-pointOffset, pointWidth, pointWidth);
			g.fillRect(primaryX+primaryWidth-pointOffset, primaryY-pointOffset, pointWidth, pointWidth);
			g.fillRect(primaryX-pointOffset, primaryY+primaryHeight-pointOffset, pointWidth, pointWidth);
			g.fillRect(primaryX+primaryWidth-pointOffset, primaryY+primaryHeight-pointOffset, pointWidth, pointWidth);
		}
		@Override
		public void paint(Graphics g) {
			super.paint(g);
			resetPosition(drawX, drawY, drawWidth, drawHeight, g);
			thisSpace.setRect(drawX, drawY, drawWidth, drawHeight);
			topBar.setRect(thisSpace.x+pointWidth,thisSpace.y,thisSpace.width-pointWidth*2,lineWidth);
			buttomBar.setRect(thisSpace.x+pointWidth,thisSpace.y+thisSpace.height-lineWidth,thisSpace.width-pointWidth*2,lineWidth);
			leftBar.setRect(thisSpace.x,thisSpace.y+pointWidth,lineWidth,thisSpace.height-pointWidth*2);
			rightBar.setRect(thisSpace.x+thisSpace.width-lineWidth,thisSpace.y+pointWidth,lineWidth,thisSpace.height-pointWidth*2);
			leftTop.setRect(thisSpace.x-pointOffset,thisSpace.y-pointOffset,pointWidth,pointWidth);
			leftBottom.setRect(thisSpace.x-pointOffset,thisSpace.y+thisSpace.height-pointOffset,pointWidth,pointWidth);
			rightTop.setRect(thisSpace.x+thisSpace.width-pointOffset,thisSpace.y-pointOffset,pointWidth,pointWidth);
			rightBottom.setRect(thisSpace.x+thisSpace.width-pointOffset,thisSpace.y+thisSpace.height-pointOffset,pointWidth,pointWidth);
			
		}

		@Override
		public void mouseDragged(MouseEvent e) {
			if(where.equals(leftTop)){
				drawWidth = thisSpace.width + thisSpace.x - e.getX() ;
				drawHeight =  thisSpace.height + thisSpace.y - e.getY() ;
				drawX = e.getX();
				drawY = e.getY();
				LayoutResize.this.repaint();
				
			}
			else if(where.equals(rightBottom)){
				drawX = thisSpace.x;
				drawY = thisSpace.y;
				drawWidth = e.getX()-thisSpace.x;
				drawHeight = e.getY()-thisSpace.y;
				LayoutResize.this.repaint();
			}
			else if(where.equals(leftBottom)){
				drawX = e.getX();
				drawY = thisSpace.y;
				drawWidth = thisSpace.x + thisSpace.width - e.getX();
				drawHeight = e.getY()-thisSpace.y ;  
				LayoutResize.this.repaint();
			}
			else if(where.equals(leftBar)){
				drawX = e.getX();
				drawY = thisSpace.y;
				drawWidth = thisSpace.x+thisSpace.width-e.getX() ;
				drawHeight =thisSpace.height ;  
				LayoutResize.this.repaint();
			}
			else if(where.equals(rightBar)){
				drawX = thisSpace.x;
				drawY= thisSpace.y;
				drawWidth = e.getX()-thisSpace.x;
				drawHeight = thisSpace.height;
				LayoutResize.this.repaint();
			}
			
			else if(where.equals(rightTop)){
				drawX = thisSpace.x;
				drawY = e.getY();
				drawWidth = e.getX() - thisSpace.x;
				drawHeight = thisSpace.y+thisSpace.height-e.getY();
				LayoutResize.this.repaint();
			}
			else if(where.equals(topBar)){
				drawX = thisSpace.x;
				drawY = e.getY();
				drawWidth = thisSpace.width;
				drawHeight = thisSpace.y+thisSpace.height-e.getY();
				LayoutResize.this.repaint();
			}
			else if(where.equals(buttomBar)){
				drawX = thisSpace.x;
				drawY = thisSpace.y;
				drawWidth = thisSpace.width;
				drawHeight = e.getY()-thisSpace.y;
				LayoutResize.this.repaint();
			}
		}

		@Override
		public void mouseMoved(MouseEvent e) {
			if(topBar.contains(e.getPoint())){
				this.setCursor(new Cursor(Cursor.N_RESIZE_CURSOR));
			}
			else if(buttomBar.contains(e.getPoint())){
				this.setCursor(new Cursor(Cursor.S_RESIZE_CURSOR));
			}
			else if(leftBar.contains(e.getPoint())){
				this.setCursor(new Cursor(Cursor.W_RESIZE_CURSOR));
			}
			else if(rightBar.contains(e.getPoint())){
				this.setCursor(new Cursor(Cursor.E_RESIZE_CURSOR));
			}
			else if (leftBottom.contains(e.getPoint())){
				this.setCursor(new Cursor(Cursor.SW_RESIZE_CURSOR));
			}
				
			else if(leftTop.contains(e.getPoint())){
				this.setCursor(new Cursor(Cursor.NW_RESIZE_CURSOR));
			}
				
			else if(rightBottom.contains(e.getPoint())){
				this.setCursor(new Cursor(Cursor.SE_RESIZE_CURSOR));
			}
				
			else if(rightTop.contains(e.getPoint())){
				this.setCursor(new Cursor(Cursor.NE_RESIZE_CURSOR));
			}
		
			else {
				this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}
				
		}

		@Override
		public void mouseClicked(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mousePressed(MouseEvent e) {
			if(topBar.contains(e.getPoint())){
				where = topBar;
			}
			else if(buttomBar.contains(e.getPoint())){
				where = buttomBar;
			}
			else if(leftBar.contains(e.getPoint())){
				where = leftBar;
			}
			else if(rightBar.contains(e.getPoint())){
				where = rightBar;
			}
			else if (leftBottom.contains(e.getPoint())){
				where = leftBottom;
			}
				
			else if(leftTop.contains(e.getPoint())){
				where = leftTop;
			}
				
			else if(rightBottom.contains(e.getPoint())){
				where = rightBottom;
			}
				
			else if(rightTop.contains(e.getPoint())){
				where = rightTop;
			}
			else
				where = new Rectangle();
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			if(drawWidth>drawHeight){
				drawHeight = drawHeight*65535/(drawWidth*65535/primaryWidth);
				drawWidth = primaryWidth;
				drawX = coreX-drawWidth/2;
				drawY = coreY-drawHeight/2;
				LayoutResize.this.repaint();
			}
			else {
				drawWidth = drawWidth*65535/(drawHeight*65535/primaryHeight);
				drawHeight = primaryHeight;
				drawX = coreX-drawWidth/2;
				drawY = coreY-drawHeight/2;
				LayoutResize.this.repaint();
			}
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}
		
		
	}
 
 
	public LayoutResize(int x,int y) {
		getContentPane().setLayout(null);
		
		setBounds(x*screenWidth/65535, y*screenHeight/65535, 390*screenWidth/65535, 500*screenHeight/65535);
		JPanel linePanel = new JPanel();
		linePanel.setBorder(new TitledBorder(new LineBorder(new Color(192, 192, 192)), "Resize", TitledBorder.LEFT, TitledBorder.TOP, null, null));
		linePanel.setBounds(10*screenWidth/65535, 10*screenHeight/65535, 360*screenWidth/65535, 345*screenHeight/65535);
		getContentPane().add(linePanel);
		linePanel.setLayout(null);
		
		innerPanel = new MovablePanel();
		innerPanel.setBounds(73*screenWidth/65535, 75*screenHeight/65535, 218*screenWidth/65535, 200*screenHeight/65535);
		innerPanel.setBorder(new LineBorder(Color.GRAY));
		linePanel.add(innerPanel);
		innerPanel.setLayout(null);
		
		
		textFieldTop = new JTextField();
		textFieldTop.setBounds(151*screenWidth/65535, 44*screenHeight/65535, 63*screenWidth/65535, 21*screenHeight/65535);
		textFieldTop.setText("");
		linePanel.add(textFieldTop);
		textFieldTop.setColumns(10);
		
		JLabel lblAddTop = new JLabel("Add Top");
		lblAddTop.setBounds(151*screenWidth/65535, 26*screenHeight/65535, 73*screenWidth/65535, 20*screenHeight/65535);
		linePanel.add(lblAddTop);
		
		textFieldLeft = new JTextField();
		textFieldLeft.setBounds(10*screenWidth/65535, 160*screenHeight/65535, 59*screenWidth/65535, 21*screenHeight/65535);
		linePanel.add(textFieldLeft);
		textFieldLeft.setColumns(10);
		
		textFieldRight = new JTextField();
		textFieldRight.setBounds(295*screenWidth/65535, 160*screenHeight/65535, 59*screenWidth/65535, 21*screenHeight/65535);
		textFieldRight.setText("");
		linePanel.add(textFieldRight);
		textFieldRight.setColumns(10);
		
		textFieldButtom = new JTextField();
		textFieldButtom.setBounds(151*screenWidth/65535, 293*screenHeight/65535, 63*screenWidth/65535, 21*screenHeight/65535);
		linePanel.add(textFieldButtom);
		textFieldButtom.setColumns(10);
		
		JLabel lblAddLeft = new JLabel("Add Left");
		lblAddLeft.setBounds(10*screenWidth/65535, 129*screenHeight/65535, 66*screenWidth/65535, 21*screenHeight/65535);
		linePanel.add(lblAddLeft);
		
		JLabel lblAddRight = new JLabel("Add Right");
		lblAddRight.setBounds(296*screenWidth/65535, 129*screenHeight/65535, 64*screenWidth/65535, 21*screenHeight/65535);
		linePanel.add(lblAddRight);
		
		JLabel lblAddButtom = new JLabel("Add Buttom");
		lblAddButtom.setBounds(151*screenWidth/65535, 272*screenHeight/65535, 73*screenWidth/65535, 21*screenHeight/65535);
		linePanel.add(lblAddButtom);
		
		JLabel lblWidth = new JLabel("Width");
		lblWidth.setBounds(83*screenWidth/65535, 365*screenHeight/65535, 56*screenWidth/65535, 21*screenHeight/65535);
		getContentPane().add(lblWidth);
		
		JLabel lblHeight = new JLabel("Height");
		lblHeight.setBounds(83*screenWidth/65535, 396*screenHeight/65535, 56*screenWidth/65535, 21*screenHeight/65535);
		getContentPane().add(lblHeight);
		
		textFieldWidth = new JTextField();
		textFieldWidth.setBounds(149*screenWidth/65535, 365*screenHeight/65535, 79*screenWidth/65535, 21*screenHeight/65535);
		getContentPane().add(textFieldWidth);
		textFieldWidth.setColumns(10);
		
		textFieldHeight = new JTextField();
		textFieldHeight.setBounds(149*screenWidth/65535, 396*screenHeight/65535, 79*screenWidth/65535, 21*screenHeight/65535);
		getContentPane().add(textFieldHeight);
		textFieldHeight.setColumns(10);
		
		JLabel lblInches = new JLabel("Inches");
		lblInches.setBounds(238*screenWidth/65535, 365*screenHeight/65535, 56*screenWidth/65535, 21*screenHeight/65535);
		getContentPane().add(lblInches);
		
		JLabel lblInches_1 = new JLabel("Inches");
		lblInches_1.setBounds(238*screenWidth/65535, 399*screenHeight/65535, 54*screenWidth/65535, 15*screenHeight/65535);
		getContentPane().add(lblInches_1);
		
		JButton btnOk = new JButton("OK");
		btnOk.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
				LayoutResize.this.dispose();
			}
		});
		btnOk.setBounds(10*screenWidth/65535, 427*screenHeight/65535, 93*screenWidth/65535, 23*screenHeight/65535);
		getContentPane().add(btnOk);
		
		JButton btnRestore = new JButton("Restore");
		btnRestore.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				innerPanel.reset();
			}
		});
		btnRestore.setBounds(138*screenWidth/65535, 427*screenHeight/65535, 93*screenWidth/65535, 23*screenHeight/65535);
		getContentPane().add(btnRestore);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				LayoutResize.this.dispose();
			}
		});
		btnCancel.setBounds(262*screenWidth/65535, 427*screenHeight/65535, 93*screenWidth/65535, 23*screenHeight/65535);
		getContentPane().add(btnCancel);
	 
		setResizable(false);
		setVisible(true);
	}
	
}