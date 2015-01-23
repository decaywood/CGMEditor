/**
 * 
 */
package net.sf.jcgm.display;

import javax.swing.SwingUtilities;

import net.sf.jcgm.controller.CGMController;


/**MyCGMEditor.java
 * @author Jiang Pingchuan
 *
 * 2013-9-3 20:57:26
 */
public class MyCGMEditor {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
        SwingUtilities.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				new CGMController();
			}
		});
	}

}
