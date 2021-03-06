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

import java.io.*;


/**
 * Class=5, Element=37
 * @author xphc (Philippe Cadé)
 * @author BBNT Solutions
 * @version $Id: LineCap.java 46 2011-12-14 08:26:44Z phica $
 */
public class LineCap extends CapCommand {
	
    public LineCap() {
        super(5, 37, 3);
    }

    public LineCap(int cap) {
        this(cap, 2);
    }

    public LineCap(LineCapIndicator lineCapIndicator,DashCapIndicator dashCapIndicator){
    	this();
    	this.lineIndicator = lineCapIndicator;
    	this.dashIndicator = dashCapIndicator;
    }
    
    public LineCap(int cap, int dash) {
        this();
        int lineIndic=cap;
        switch (lineIndic) {        
        case 1:
        	this.lineIndicator = LineCapIndicator.UNSPECIFIED;
        	break;
        case 2:
        	this.lineIndicator = LineCapIndicator.BUTT;
        	break;
        case 3:
        	this.lineIndicator = LineCapIndicator.ROUND;
        	break;
        case 4:
        	this.lineIndicator = LineCapIndicator.PROJECTED_SQUARE;
        	unsupported("unsupported line cap indicator "+lineIndic);
        	break;
        case 5:
        	this.lineIndicator = LineCapIndicator.TRIANGLE;
        	unsupported("unsupported line cap indicator "+lineIndic);
        	break;
        default:
        	unsupported("unsupported line cap indicator "+lineIndic);
        	this.lineIndicator = LineCapIndicator.UNSPECIFIED;
        }
        
		int dashIndic = dash;
        switch (dashIndic) {
        case 1:
        	this.dashIndicator = DashCapIndicator.UNSPECIFIED;
        	break;
        case 2:
        	this.dashIndicator = DashCapIndicator.BUTT;
        	break;
        case 3:
        	this.dashIndicator = DashCapIndicator.MATCH;
        	break;
        default:
        	unsupported("unsupported dash cap indicator "+dashIndic);
        	this.dashIndicator = DashCapIndicator.UNSPECIFIED;
        }
    }
    
	public LineCap(int ec, int eid, int l, DataInput in)
            throws IOException {
        super(ec, eid, l, in, 3);
    }

    @Override
	public String toString() {
    	StringBuilder sb = new StringBuilder();
    	sb.append("LineCap lineCapIndicator=").append(this.lineIndicator);
    	sb.append(" dashCapIndicator=").append(this.dashIndicator);
        return sb.toString();
    }

	@Override
	public void paint(CGMDisplay d) {
		d.setLineCap(this.lineIndicator);
	}
}

/*
 * vim:encoding=utf8
 */
