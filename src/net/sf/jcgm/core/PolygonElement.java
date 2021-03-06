/*
 * <copyright> Copyright 1997-2003 BBNT Solutions, LLC under sponsorship of the
 * Defense Advanced Research Projects Agency (DARPA).
 * Copyright 2009 Swiss AviationSoftware Ltd.
 *
 * This program is free software; you can redistribute it and/or modify it
 * under the terms of the Cougaar Open Source License as published by DARPA on
 * the Cougaar Open Source Website (www.cougaar.org).
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
 * POSSIBILITY OF SUCH DAMAGE.
 */
package net.sf.jcgm.core;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.geom.Path2D;
import java.awt.geom.PathIterator;
import java.awt.geom.Point2D;
import java.io.DataInput;
import java.io.IOException;



/**
 * Class=4, Element=7
 * @author xphc (Philippe Cadé)
 * @author BBNT Solutions
 * @version $Id: PolygonElement.java 46 2011-12-14 08:26:44Z phica $
 */
public class PolygonElement extends Command {
	protected  Path2D.Double polygon;

    public PolygonElement() {
        super(4, 7, 1);
    }
    
    public PolygonElement(Path2D.Double polygon){
    	this();
    	this.polygon = polygon;
    }

    public PolygonElement(Point2D[] p) {
        this();
        this.polygon = new Path2D.Double(Path2D.WIND_EVEN_ODD);
        for(int i=0;i<p.length;i++){
        	if(i==0)
        	{
        		this.polygon.moveTo(p[i].getX(), p[i].getY());
        	}
        	else{
        		this.polygon.lineTo(p[i].getX(), p[i].getY());
        	}
        }
        this.polygon.closePath();
    }
    
	public PolygonElement(int ec, int eid, int l, DataInput in)
			throws IOException {

		super(ec, eid, l, in, 1);

		assert (this.args.length - this.currentArg) % sizeOfPoint() == 0;
		int n = (this.args.length - this.currentArg) / sizeOfPoint();

		this.polygon = new Path2D.Double(Path2D.WIND_EVEN_ODD);

		Point2D.Double p = makePoint();
		this.polygon.moveTo(p.x, p.y);

		for (int i = 1; i < n; i++) {
			p = makePoint();
			this.polygon.lineTo(p.x, p.y);
		}

		this.polygon.closePath();

		// make sure all the arguments were read
		assert (this.currentArg == this.args.length);
	}
	
    public void write(int tagID, CGMOutputStream cgm) throws IOException {
    	
    	PathIterator pathIterator = this.polygon.getPathIterator(new AffineTransform());
		double[] coords = new double[6];
		while (!pathIterator.isDone()) {
			int currentSegment = pathIterator.currentSegment(coords);
			switch (currentSegment) {
			case PathIterator.SEG_MOVETO:
				cgm.writePoint(new Point2D.Double(coords[0],coords[1]));
				break;
			case PathIterator.SEG_LINETO:				
				cgm.writePoint(new Point2D.Double(coords[0],coords[1]));
				break;
			default:
					break;
//			case PathIterator.SEG_QUADTO:				
//				sb.append(coords[0]).append(",").append(coords[1]).append(",");
//				sb.append(coords[2]).append(",").append(coords[3]).append(";");
//				break;
//			case PathIterator.SEG_CUBICTO:
//				
//				sb.append(coords[0]).append(",").append(coords[1]).append(",");
//				sb.append(coords[2]).append(",").append(coords[3]).append(",");
//				sb.append(coords[4]).append(",").append(coords[5]).append(";");
//				break;
//			case PathIterator.SEG_CLOSE:
//				sb.append("CLOSE=");
			}
			pathIterator.next();
		}       
    }
	
	public Path2D.Double getPolyGon()
	{
		return this.polygon;
	}
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Polygon ");
		sb.append(printShape(this.polygon));
		return sb.toString();
	}

	@Override
	public void paint(CGMDisplay d) {
		if(Command.applicationFlag==true)
			return;
		Graphics2D g2d = d.getGraphics2D();

		d.fill(this.polygon);

		if (d.drawEdge()) {
			g2d.setColor(d.getEdgeColor());
			g2d.setStroke(d.getEdgeStroke());
			g2d.draw(this.polygon);
		}
	}
}

/*
 * vim:encoding=utf8
 */
