package test;

 import java.awt.Cursor;
 import java.awt.Graphics;
 import java.awt.Rectangle;
 import java.awt.Dimension;
 import java.awt.event.MouseEvent;
 import java.awt.event.MouseListener;
 import java.awt.event.MouseMotionListener;
 import javax.swing.JPanel;
 import javax.swing.border.TitledBorder;

 public class MyPanel extends JPanel implements MouseListener, MouseMotionListener{

 private Rectangle outer_rect = new Rectangle();
 private Rectangle inner_rect = new Rectangle();
 private boolean resizeable = false;

 public MyPanel() {
 super();
 this.setSize(30, 30);
 this.setBorder(new TitledBorder(""));
 this.addMouseListener(this);
 this.addMouseMotionListener(this);
 }

 public void mouseEntered(MouseEvent me) {}

 public void mouseExited(MouseEvent me) {}

 public void mousePressed(MouseEvent me) {
 if ((outer_rect.contains(me.getPoint())) && (!inner_rect.contains(me.getPoint()))) 
 resizeable = true;
 else
 resizeable = false;
 }

 public void mouseReleased(MouseEvent me) {}

 public void mouseClicked(MouseEvent me) {}

 public void mouseMoved(MouseEvent me) {
 if ((outer_rect.contains(me.getPoint())) && (!inner_rect.contains(me.getPoint()))) 
 this.setCursor(new Cursor(Cursor.SW_RESIZE_CURSOR));
 else
 this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
 }

 public void mouseDragged(MouseEvent me) {
 if (resizeable)
 this.setSize(new Dimension(me.getX()-this.getX(), me.getY()-this.getY()));
 }

 protected void paintComponent(Graphics g) {
 super.paintComponent(g);
 outer_rect = this.getVisibleRect();
 inner_rect.setRect(outer_rect.x+1, outer_rect.y+1, outer_rect.width-3, outer_rect.height-3);
 }

 }
