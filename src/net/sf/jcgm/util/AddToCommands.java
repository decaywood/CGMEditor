package net.sf.jcgm.util;

import java.awt.Color;
import java.awt.geom.Path2D;
import java.awt.geom.Point2D;
import java.awt.geom.Point2D.Double;
import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;

import net.sf.jcgm.core.Algorithm;
import net.sf.jcgm.core.CGMObject;
import net.sf.jcgm.core.Command;
import net.sf.jcgm.core.DashType;
import net.sf.jcgm.core.HatchIndex;
import net.sf.jcgm.core.InteriorStyle;

public class AddToCommands {
	
	private BasicCGMObjectGenerator basicCGMObjectGenerator;
	
	public AddToCommands(ArrayList<CGMObject> objects){
		basicCGMObjectGenerator = new BasicCGMObjectGenerator(objects);
	}
	
	public void initiateCGM(double units , Point2D.Double canvasP0,Point2D.Double canvasP1){
		basicCGMObjectGenerator.initiateCGM(units, canvasP0, canvasP1);
	}
	
	public void convertToPolygon(InteriorStyle.Style interiorStyle,HatchIndex.HatchType hatchType,
			int edgeType, double edgeWidth,
			Color fillColor, Color edgeColor, Point2D.Double canvasP0,
			Point2D.Double canvasP1,Path2D.Double polyPath,Deque pointDeque){
		boolean isVisible = false;
		if(edgeColor!=null){
			isVisible = true;
		}
		Point2D.Double point = (Double) pointDeque.pollLast();
		polyPath.moveTo(point.x, point.y);
		while(pointDeque.size()>0){
			point = (Double) pointDeque.pollLast();
			polyPath.lineTo(point.x, point.y);
		}
		polyPath.closePath();
		basicCGMObjectGenerator.generatePolygon(canvasP0, canvasP1, interiorStyle, fillColor,
				edgeWidth, edgeColor, isVisible, edgeType, hatchType, polyPath);
	}
	
	public void convertToEllipse(InteriorStyle.Style interiorStyle,HatchIndex.HatchType hatchType,
	int edgeType, double edgeWidth,Color fillColor, Color edgeColor,Point2D.Double canvasP0,
	Point2D.Double canvasP1,Point2D.Double p0,Point2D.Double p1,Point2D.Double p2){
		boolean isVisible = false;	
		if(edgeColor!=null){
			isVisible = true;
		}
		basicCGMObjectGenerator.generateEllipse(canvasP0, canvasP1, interiorStyle, fillColor, 
				edgeWidth, edgeColor, isVisible, edgeType, hatchType, p0,p1,p2);
	}
	
	public void convertToRectangle(InteriorStyle.Style interiorStyle,HatchIndex.HatchType hatchType,
			int edgeType, double edgeWidth,Color fillColor, Color edgeColor,Point2D.Double canvasP0,
			Point2D.Double canvasP1,Point2D.Double p0, Point2D.Double p1){
		boolean isVisible = false;
		if(edgeColor!=null){
			isVisible = true;
		}
		basicCGMObjectGenerator.generateRectangle(canvasP0, canvasP1, interiorStyle, fillColor, edgeWidth, 
				edgeColor, isVisible, edgeType, hatchType, p0, p1);
	}
	
	
	public void convertToline(int lineTypeLeft, int lineTypeCenter, int lineTypeRight,
			double arrowSize,double headRatio, double lineWidth, boolean isHalo,Color lineColor,
			Point2D.Double canvasP0,Point2D.Double canvasP1
			,Deque pointDeque,Path2D.Double polyPath){
		Deque pathDeque = (Deque) ((LinkedList)pointDeque).clone();
		if(lineTypeLeft==2||lineTypeRight==2){
			basicCGMObjectGenerator.generateApplicationData();
			if(isHalo){
				pathDeque = drawArrow(canvasP0,canvasP1,pointDeque,pathDeque,Color.WHITE,lineTypeLeft,
						  lineTypeRight, arrowSize*100+10, headRatio+10,isHalo);
				Point2D.Double point = (Double) pathDeque.pollLast();
				polyPath.moveTo(point.x, point.y);
				while(pathDeque.size()>0){
					point = (Double) pathDeque.pollLast();
					polyPath.lineTo(point.x, point.y);
				}
				basicCGMObjectGenerator.generatePolyLine(canvasP0, canvasP1, lineWidth, 
						Color.WHITE, DashType.SOLID , polyPath);
			}
		}
		pathDeque = drawArrow(canvasP0,canvasP1,pointDeque,pathDeque,lineColor,lineTypeLeft,
				  lineTypeRight, arrowSize*100, headRatio, isHalo);
		Point2D.Double point = (Double) pathDeque.pollLast();
		polyPath.moveTo(point.x, point.y);
		while(pathDeque.size()>0){
			point = (Double) pathDeque.pollLast();
			polyPath.lineTo(point.x, point.y);
		}
		basicCGMObjectGenerator.generatePolyLine(canvasP0, canvasP1, lineWidth, 
				lineColor, lineTypeCenter , polyPath);
		if(lineTypeLeft==2||lineTypeRight==2){
			basicCGMObjectGenerator.generateApplicationData();
		}
	}
	
	
	public void convertToPolyLine(int lineTypeLeft, int lineTypeCenter, int lineTypeRight,
			double arrowSize,double headRatio, double lineWidth, boolean isHalo,Color lineColor,
			Point2D.Double canvasP0,Point2D.Double canvasP1
			,Deque pointDeque,Path2D.Double polyPath){
		Deque pathDeque = (Deque) ((LinkedList)pointDeque).clone();
		if(lineTypeLeft==2||lineTypeRight==2){
			basicCGMObjectGenerator.generateApplicationData();
			if(isHalo){
				pathDeque = drawArrow(canvasP0,canvasP1,pointDeque,pathDeque,Color.WHITE,lineTypeLeft,
						  lineTypeRight, arrowSize*100+10, headRatio+10,isHalo);
				Point2D.Double point = (Double) pathDeque.pollLast();
				polyPath.moveTo(point.x, point.y);
				while(pathDeque.size()>0){
					point = (Double) pathDeque.pollLast();
					polyPath.lineTo(point.x, point.y);
				}
				basicCGMObjectGenerator.generatePolyLine(canvasP0, canvasP1, lineWidth, 
						Color.WHITE, DashType.SOLID , polyPath);
			}
		}
		pathDeque = drawArrow(canvasP0,canvasP1,pointDeque,pathDeque,lineColor,lineTypeLeft,
				  lineTypeRight, arrowSize*100, headRatio, isHalo);
		Point2D.Double point = (Double) pathDeque.pollLast();
		polyPath.moveTo(point.x, point.y);
		while(pathDeque.size()>0){
			point = (Double) pathDeque.pollLast();
			polyPath.lineTo(point.x, point.y);
		}
		basicCGMObjectGenerator.generatePolyLine(canvasP0, canvasP1, lineWidth, 
				lineColor, lineTypeCenter , polyPath);
		if(lineTypeLeft==2||lineTypeRight==2){
			basicCGMObjectGenerator.generateApplicationData();
		}
	}
	
	public void conVertToArc(int lineType,double lineWidth, Color lineColor, Point2D.Double canvasP0,Point2D.Double canvasP1,
			Point2D.Double arcP0,Point2D.Double arcP1,Point2D.Double arcP2){
		basicCGMObjectGenerator.generateArc3Point(canvasP0, canvasP1, lineWidth, lineColor,
				lineType, arcP0, arcP1, arcP2);
	}
	
	public void convertToSweepArrow(double headRatio,double arrowWidth,Color arrowColor,
			 Point2D.Double canvasP0,Point2D.Double canvasP1,
			Point2D.Double arcP0,Point2D.Double arcP1,Point2D.Double arcP2){
		double ratio;
		Algorithm.arcToArrow arcToArrow;
		ratio = headRatio*100;
		arcToArrow = new Algorithm.arcToArrow(arcP0, arcP1, arcP2, arrowWidth, ratio);
		basicCGMObjectGenerator.generateApplicationData();
		basicCGMObjectGenerator.generatePolygon(canvasP0, canvasP1, InteriorStyle.Style.SOLID,
				arrowColor, 0.0, arrowColor, true, -1, null, arcToArrow.arrow);
		basicCGMObjectGenerator.generateApplicationData();	
	}
	
//===============================================Not Processed=====================================================================	
	
	public void convertToLeaderLine(double leaderLineWidth,boolean leaderLineIshalo,Point2D.Double canvasP0,Point2D.Double canvasP1
			,Deque pointDeque,Path2D.Double polyPath){
		Deque pathDeque = (Deque) ((LinkedList)pointDeque).clone();
		
		
	}
	
/*
 * ========================================================================================================================================================================================================	
 */

	public Deque<Point2D.Double> drawArrow(Point2D.Double canvasP0,Point2D.Double canvasP1,Deque pointDeque,Deque pathDeque,Color color,int hasLeftArrow,int hasRightArrow,double arrowSize,double headRatio,boolean isHalo){
		Point2D.Double p5 = null ;
		if(hasLeftArrow==2||hasRightArrow==2||hasLeftArrow==4){
			Point2D.Double p1 = (Point2D.Double) pointDeque.pollLast();//TODO 2 polygons
			Point2D.Double p4 = (Point2D.Double) pointDeque.pollFirst();
			Point2D.Double p2;
			Point2D.Double p3;
			p5 = (Point2D.Double) p1.clone();;
			if(pointDeque.isEmpty()){
				p3 = p1;
				p2 = p4;
			}
			else {
				p2 = (Point2D.Double) pointDeque.pollLast();
				if(pointDeque.isEmpty()){
					p3 = p2;
				}
				else {
					p3 = (Point2D.Double) pointDeque.pollFirst();
				}
			}
			if(isHalo){
				pointDeque.clear();
				pointDeque.offerFirst(p1);
				pointDeque.offerFirst(p2);
				pointDeque.offerFirst(p3);
				pointDeque.offerFirst(p4);
			}
			if(hasLeftArrow==2){
				double lenth = Point2D.distance(p1.x, p1.y, p2.x, p2.y);
				double startSlopeY = (p1.y-p2.y)/lenth;
				double startSlopeX = (p1.x-p2.x)/lenth; 
				Point2D.Double ps0 = new Point2D.Double(p1.x-headRatio*startSlopeX,p1.y-headRatio*startSlopeY);
				pathDeque.pollLast();
				pathDeque.offerLast(ps0);
				startSlopeY = (p1.x-p2.x)/lenth;
				startSlopeX = (p2.y-p1.y)/lenth; 
				Point2D.Double ps1 = new Point2D.Double(ps0.x+arrowSize*startSlopeX/2,ps0.y+arrowSize*startSlopeY/2);
				startSlopeY = (p2.x-p1.x)/lenth;
				startSlopeX = (p1.y-p2.y)/lenth; 
				Point2D.Double ps2 = new Point2D.Double(ps0.x+arrowSize*startSlopeX/2,ps0.y+arrowSize*startSlopeY/2);
				Path2D.Double polygon = new Path2D.Double();
				polygon.moveTo(p1.x, p1.y);
				polygon.lineTo(ps1.x,ps1.y);
				polygon.lineTo(ps2.x, ps2.y);
				polygon.lineTo(p1.x, p1.y);
				polygon.closePath();
				basicCGMObjectGenerator.generatePolygon(canvasP0, canvasP1, InteriorStyle.Style.SOLID,
						color, 1.0, Color.BLACK, false, -1, null, polygon);
			}
			if(hasRightArrow==2){
				double lenth = Point2D.distance(p4.x, p4.y, p3.x, p3.y);
				double startSlopeY = (p4.y-p3.y)/lenth;
				double startSlopeX = (p4.x-p3.x)/lenth; 
				Point2D.Double ps0 = new Point2D.Double(p4.x-headRatio*startSlopeX,p4.y-headRatio*startSlopeY);
				pathDeque.pollFirst();
				pathDeque.offerFirst(ps0);
				startSlopeY = (p4.x-p3.x)/lenth;
				startSlopeX = (p3.y-p4.y)/lenth; 
				Point2D.Double ps1 = new Point2D.Double(ps0.x+arrowSize*startSlopeX/2,ps0.y+arrowSize*startSlopeY/2);
				startSlopeY = (p3.x-p4.x)/lenth;
				startSlopeX = (p4.y-p3.y)/lenth; 
				Point2D.Double ps2 = new Point2D.Double(ps0.x+arrowSize*startSlopeX/2,ps0.y+arrowSize*startSlopeY/2);
				Path2D.Double polygon = new Path2D.Double();
				polygon.moveTo(p4.x, p4.y);
				polygon.lineTo(ps1.x,ps1.y);
				polygon.lineTo(ps2.x, ps2.y);
				polygon.lineTo(p4.x, p4.y);
				polygon.closePath();
				basicCGMObjectGenerator.generatePolygon(canvasP0, canvasP1, InteriorStyle.Style.SOLID,
						color, 1.0, Color.BLACK, false, -1, null, polygon);
			}
		}	
		if(hasLeftArrow==4){
		//	basicCGMObjectGenerator.generateCircle(canvasP0, canvasP1, interiorStyle, fillColor, edgeWidth, edgeColor,
		//			isVisible, edgeType, hatchType, center, radius);
		}
		return pathDeque;
	}
	
	
	public ArrayList<CGMObject> getObjects(){
		return basicCGMObjectGenerator.getObjects();
	}
	
	public void setObjects(ArrayList<CGMObject> objects){
		basicCGMObjectGenerator.setObjects(objects);
	}
	
	public void addAndConvert(Command command){
		basicCGMObjectGenerator.addAndConvert(command);
	}
	
}
