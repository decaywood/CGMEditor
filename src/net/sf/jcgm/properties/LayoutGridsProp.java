package net.sf.jcgm.properties;

public class LayoutGridsProp {
	private boolean gridIsShowGrid;
	private boolean gridIsSnaptoGrid;
	private double gridHorizontal;
	private double gridVertical;
	private int gridHowShowGrid; //0 default, 1 lines, 2 Dots
	private String gridUnit;
	public LayoutGridsProp(){
		
	}
	public boolean isGridIsShowGrid() {
		return gridIsShowGrid;
	}
	public boolean isGridIsSnaptoGrid() {
		return gridIsSnaptoGrid;
	}
	public double getGridHorizontal() {
		return gridHorizontal;
	}
	public int getGridHowShowGrid() {
		return gridHowShowGrid;
	}
	public String getGridUnit() {
		return gridUnit;
	}
	public double getGridVertical() {
		return gridVertical;
	}
	public void setGridHorizontal(double gridHorizontal) {
		this.gridHorizontal = gridHorizontal;
	}
	public void setGridHowShowGrid(int gridHowShowGrid) {
		this.gridHowShowGrid = gridHowShowGrid;
	}
	public void setGridIsShowGrid(boolean gridIsShowGrid) {
		this.gridIsShowGrid = gridIsShowGrid;
	}
	public void setGridIsSnaptoGrid(boolean gridIsSnaptoGrid) {
		this.gridIsSnaptoGrid = gridIsSnaptoGrid;
	}
	public void setGridUnit(String gridUnit) {
		this.gridUnit = gridUnit;
	}
	public void setGridVertical(double gridVertical) {
		this.gridVertical = gridVertical;
	}
}
