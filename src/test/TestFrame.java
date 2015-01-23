package test;

 

 import java.awt.Color;

import javax.swing.JFrame;


 public class TestFrame extends JFrame {

 private MyPanel myPanel = new MyPanel();
 
 public TestFrame() {
	 myPanel.setBackground(Color.white);
 this.setSize(400, 300);
 this.setLayout(null);
 this.getContentPane().add(myPanel);
 this.setVisible(true);
 }

 public static void main(String[] args) {
 TestFrame tf = new TestFrame();
 }

 } 