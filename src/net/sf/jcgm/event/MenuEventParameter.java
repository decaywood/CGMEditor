/**
 * 
 */
package net.sf.jcgm.event;

import javax.swing.JFrame;

import net.sf.jcgm.display.JCGMEditor;

/**
 * 2013-11-6
 */
public class MenuEventParameter extends EventParameter{
	
	private MenuType menuType;
	private int menuPositionX;
	private int menuPositionY;
	
	public MenuEventParameter() {
		super(CGM_MENU);
	}
 
	
	public void setMenuType(MenuType menuType) {
		this.menuType = menuType;
	}
	
	public void setMenuPosition(int menuPositionX ,int menuPositionY) {
		this.menuPositionX = menuPositionX;
		this.menuPositionY = menuPositionY;
	}
	
	public int getMenuPositionX() {
		return menuPositionX;
	}
	
	public int getMenuPositionY() {
		return menuPositionY;
	}
	
	public MenuType getMenuType() {
		return menuType;
	}
	
}

