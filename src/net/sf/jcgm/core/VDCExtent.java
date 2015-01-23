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

import java.awt.geom.Point2D;
import java.io.*;



/**
 * Class=2, Element=6
 * @author xphc (Philippe Cadé)
 * @author BBNT Solutions
 * @version $Id: VDCExtent.java 46 2011-12-14 08:26:44Z phica $
 */
public class VDCExtent extends Command {
    Point2D.Double firstCorner;
	Point2D.Double secondCorner;

    public VDCExtent() {
        super(2, 6, 1);
    }

    public VDCExtent(Point2D p0, Point2D p1) {
        this();
        this.firstCorner =(Point2D.Double) p0;
        this.secondCorner =(Point2D.Double) p1;
    }

    public void write(int tagID, CGMOutputStream cgm) throws IOException {
        cgm.writePoint(firstCorner);
        cgm.writePoint(secondCorner);
    }
    
    public VDCExtent(int ec, int eid, int l, DataInput in)
            throws IOException {
        super(ec, eid, l, in);
        this.firstCorner = makePoint();
        this.secondCorner = makePoint();
    }

    @Override
	public String toString() {
        return "VDCExtent [" + this.firstCorner + "] [" + this.secondCorner + "]";
    }

    public Point2D.Double[] extent() {
        Point2D.Double points[] = new Point2D.Double[2];
        points[0] = this.firstCorner;
        points[1] = this.secondCorner;
        return points;
    }
}

/*
 * vim:encoding=utf8
 */
