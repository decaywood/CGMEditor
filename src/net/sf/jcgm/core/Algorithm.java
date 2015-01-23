package net.sf.jcgm.core;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.geom.Arc2D;
import java.awt.geom.Path2D;
import java.awt.geom.PathIterator;
import java.awt.geom.Point2D;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;

import net.sf.jcgm.display.PropertyPanel;

public class Algorithm {
	private static Point2D.Double findCenter(double x1, double y1, double x2, double y2, double x3, double y3) {
	    Point2D.Double ret = new Point2D.Double();
	    ret.x = ((((x1*x1)*(y3 - y2)) + (y2*((x3*x3) + (y1*(y2 - y1)) + (y3*(y3 - y2)))) + (y1*((y3*(y1 - y3)) + (x2*x2) - (x3*x3))) - (y3*(x2*x2)))/(2.0*(((x1 - x3)*(y3 - y2)) + ((x2 - x3)*(y1 - y3)))));
	    ret.y = (((x3*((y1*y1) - (x2*x2) - (y2*y2))) + (x2*((x3*x3) + (y3*y3) - (y1*y1))) + ((x1*x1)*(x3 - x2)) + (x1*((x2*x2) - (x3*x3) + (y2*y2) - (y3*y3))))/(2.0*((x2*(y3 - y1)) + (x1*(y2 - y3)) + (x3*(y1 - y2)))));
	    return ret;
	}
	private static double distance(Point2D.Double p1, Point2D.Double p2) {
	    return Math.sqrt((p2.x-p1.x)*(p2.x-p1.x)+(p2.y-p1.y)*(p2.y-p1.y));
	}
	private static double angle(double x, double y) {
			if (Math.atan2(y, x) < 0) {
				return Math.atan2(y, x) + 2*Math.PI;
			}
			return Math.atan2(y, x);
		}
	

	public static Arc2D.Double arcCompute(Point2D.Double p1,Point2D.Double p2,Point2D.Double p3){
		Point2D.Double center = findCenter(p1.x,p1.y,p2.x,p2.y,p3.x,p3.y) ;  
	    double radius = distance(p1, center);
	    double startDeltaX = p1.x - center.x;
	    double startDeltaY = p1.y - center.y;
	    double intermediateDeltaX = p2.x - center.x;
	    double intermediateDeltaY = p2.y - center.y;
	    double endDeltaX = p3.x - center.x;
	    double endDeltaY = p3.y - center.y;		
	    double startAngle = angle(startDeltaX, startDeltaY);
		double intermediateAngle = angle(intermediateDeltaX, intermediateDeltaY);
		double endAngle = angle(endDeltaX, endDeltaY);	
		Arc2D.Double shape = new Arc2D.Double(Arc2D.OPEN);
		shape.setFrame(center.x - radius, center.y - radius, 2*radius, 2*radius);
		
		if (endAngle > startAngle) {
			if (intermediateAngle < startAngle ||  endAngle < intermediateAngle) {
				// intermediate angle outside of start/end
				shape.setAngles(p1, p3);
			}
			else {
				shape.setAngles(p3, p1);
			}
		}
		else {
			if (intermediateAngle < endAngle || startAngle < intermediateAngle) {
				// intermediate angle outside of start/end
				shape.setAngles(p3, p1);
			}
			else {
				shape.setAngles(p1, p3);
			}
		}
		return shape;
	}
	
	private static double slopeX(Point2D.Double p1,Point2D.Double p2,Point2D.Double endPoint){
		Point2D.Double center = findCenter(p1.x,p1.y,p2.x,p2.y,endPoint.x,endPoint.y) ;
		double length = Point2D.distance(endPoint.x, endPoint.y, center.x, center.y);
		double slopeX = (-endPoint.y+center.y)/length;
		return slopeX;
	}
	private static double slopeY(Point2D.Double p1,Point2D.Double p2,Point2D.Double endPoint){
		Point2D.Double center = findCenter(p1.x,p1.y,p2.x,p2.y,endPoint.x,endPoint.y) ;
		double length = Point2D.distance(endPoint.x, endPoint.y, center.x, center.y);
		double slopeY = (endPoint.x-center.x)/length;
		return slopeY;
	}
	
	
	public static void drawCursor(Graphics g,int x,int y ,double fontHeight,double degree){
		double width = fontHeight/4;
		double factorX = Math.sin(Math.toRadians(degree)); 
		double factorY = Math.cos(Math.toRadians(degree)); 
		double x0 = x + fontHeight*factorX;
		double y0 = y + fontHeight*factorY;
		double x1 = x - fontHeight*factorX;
		double y1 = y - fontHeight*factorY;
		g.drawLine((int)x0, (int)y0, (int)x1, (int)y1);
		x0 = x-width*factorY + fontHeight*factorX;
		y0 = y+width*factorX + fontHeight*factorY;
		x1 = x+width*factorY + fontHeight*factorX;
		y1 = y-width*factorX + fontHeight*factorY;
		g.drawLine((int)x0, (int)y0, (int)x1, (int)y1);
		x0 = x-width*factorY - fontHeight*factorX;
		y0 = y+width*factorX - fontHeight*factorY;
		x1 = x+width*factorY - fontHeight*factorX;
		y1 = y-width*factorX - fontHeight*factorY;
		g.drawLine((int)x0, (int)y0, (int)x1, (int)y1);
	}
	
	public static class arcToArrow{
		public Path2D.Double arrow;
		
		public arcToArrow(Point2D.Double arcP0,Point2D.Double arcP1,Point2D.Double arcP2,double width,double ratio){
			ArrayList<Point2D.Double> points = new ArrayList<Point2D.Double>();
			double slopeX;
			double slopeY;
			PathIterator pathIterator = arcCompute(arcP0, arcP1, arcP2).getPathIterator(null,0.1);
			double[] coords = new double[2];
			while (!pathIterator.isDone()) {
				int currentSegment = pathIterator.currentSegment(coords);
				points.add(new Point2D.Double(coords[0], coords[1]));			
				pathIterator.next();
			} 
			int middle = points.size()/2;
			int end = points.size()-1;
			Point2D.Double middlePoint = points.get(middle);
			Point2D.Double endPoint = points.get(end);
			Point2D.Double tempPoint = points.get(end-1);
			if(!(arcP2.x-0.5<endPoint.x&&arcP2.x+0.5>endPoint.x&&arcP2.y-0.5<endPoint.y&&arcP2.y+0.5>endPoint.y)){
				endPoint = points.get(0);
				tempPoint = points.get(1);
			}
			points.clear();
			Point2D.Double basePoint = new Point2D.Double() ;
			Point2D.Double arc13 = new Point2D.Double();
			Point2D.Double arc23 = new Point2D.Double();
			Point2D.Double arc12 = new Point2D.Double();
			Point2D.Double arc22 = new Point2D.Double();
			double arrowP1x,arrowP1y,arrowP2x,arrowP2y,temp;			
			slopeX = slopeX(arcP0, arcP1, endPoint);
			slopeY = slopeY(arcP0, arcP1, endPoint);
			basePoint.x = endPoint.x+slopeX*ratio;
			basePoint.y = endPoint.y+slopeY*ratio;
			if(((tempPoint.x-endPoint.x)*(basePoint.x-endPoint.x)+
					(tempPoint.y-endPoint.y)*(basePoint.y-endPoint.y))<0){
				basePoint.x = endPoint.x-slopeX*ratio;
				basePoint.y = endPoint.y-slopeY*ratio;
			}
			temp = slopeX;
			slopeX = -slopeY;
			slopeY = temp;
			arrowP1x = basePoint.x+width/2*slopeX;
			arrowP1y = basePoint.y+width/2*slopeY;
			arrowP2x = basePoint.x-width/2*slopeX;
			arrowP2y = basePoint.y-width/2*slopeY;
			arc13.x = basePoint.x+width/4*slopeX;
			arc13.y = basePoint.y+width/4*slopeY;
			arc23.x = basePoint.x-width/4*slopeX;
			arc23.y = basePoint.y-width/4*slopeY;
			slopeX = slopeX(arcP0, arcP1, middlePoint);
			slopeY = slopeY(arcP0, arcP1, middlePoint);
			temp = slopeX;
			slopeX = -slopeY;
			slopeY = temp;
			arc12.x = middlePoint.x+width/4*slopeX;
			arc12.y = middlePoint.y+width/4*slopeY;
			arc22.x = middlePoint.x-width/4*slopeX;
			arc22.y = middlePoint.y-width/4*slopeY;		
			arrow = addPath(arcCompute(arcP0, arc12, arc13).getPathIterator(null,0.1),arcP0,arc13);
			arrow.lineTo(arrowP1x, arrowP1y);
			arrow.lineTo(endPoint.x, endPoint.y);
			arrow.lineTo(arrowP2x, arrowP2y);
			arrow.append(addPath(arcCompute(arc23, arc22, arcP0).getPathIterator(null,0.1),arc23,arcP0), true);
			arcCompute(arcP0, arc22, arc23).getPathIterator(null,0.1);
		}
	}
	private static Path2D.Double addPath(PathIterator pathIterator,Point2D.Double arcP0,Point2D.Double arc13){
		double[] coords = new double[2];
		Path2D.Double tempPath = new Path2D.Double();
		Deque<Point2D.Double> pointDeque = new LinkedList<Point2D.Double>();
		pathIterator.currentSegment(coords);
		pathIterator.next();
		if((int)coords[0]==(int)arcP0.x&&(int)coords[1]==(int)arcP0.y){
			tempPath.moveTo(coords[0], coords[1]);
			while(!pathIterator.isDone()){
				pathIterator.currentSegment(coords);
				tempPath.lineTo(coords[0],coords[1]);
				pathIterator.next();
			}	
		}
		else if((int)coords[0]==(int)arc13.x&&(int)coords[1]==(int)arc13.y){
			pointDeque.offerFirst(new Point2D.Double(coords[0],coords[1]));
			while(!pathIterator.isDone()){
				pathIterator.currentSegment(coords);
				pointDeque.offerFirst(new Point2D.Double(coords[0],coords[1]));
				pathIterator.next();
			}	
			tempPath.moveTo(pointDeque.pollFirst().x, pointDeque.pollFirst().y);
			while (!pointDeque.isEmpty()) {
				Point2D.Double point = pointDeque.pollFirst();
				tempPath.lineTo(point.x, point.y);
			}
		}
		else {
			System.out.println("from : Algorithm--addNo1Path error: not found point");
		}
		return tempPath;
	}
}


	