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
 * Class=5, Element=18
 * @author xphc (Philippe Cadé)
 * @author BBNT Solutions
 * @version $Id: TextAlignment.java 46 2011-12-14 08:26:44Z phica $
 */
public class TextAlignment extends Command {
	public enum HorizontalAlignment { NORMAL_HORIZONTAL, LEFT, CENTRE, RIGHT, CONTINOUS_HORIZONTAL }
	public enum VerticalAlignment { NORMAL_VERTICAL, TOP, CAP, HALF, BASE, BOTTOM, CONTINOUS_VERTICAL }

	private HorizontalAlignment horizontalAlignment;
	private VerticalAlignment verticalAlignment;
	private double continuousHorizontalAlignment;
	private double continuousVerticalAlignment;
	
    public TextAlignment() {
        super(5, 18, 1);
    }
    
    public TextAlignment(HorizontalAlignment horizontalAlignment, VerticalAlignment verticalAlignment,
            double horizontal, double vertical) {
        this();
       	this.horizontalAlignment = horizontalAlignment;
       	this.verticalAlignment = verticalAlignment;
        this.continuousHorizontalAlignment = horizontal;
        this.continuousVerticalAlignment = vertical;
    }
    
    public TextAlignment(int horizontalType, int verticalType,
            double horizontal, double vertical) {
        this();
        this.init(horizontalType, verticalType);
        this.continuousHorizontalAlignment = horizontal;
        this.continuousVerticalAlignment = vertical;
    }
    
    public TextAlignment(int ec, int eid, int l, DataInput in)
            throws IOException {
        super(ec, eid, l, in, 1);
        
        int horiz = makeEnum();
        int vert = makeEnum();
        this.init(horiz, vert);
        this.continuousHorizontalAlignment = makeReal();
        this.continuousVerticalAlignment = makeReal();
        
        // make sure all the arguments were read
        assert (this.currentArg == this.args.length);
    }
    public void init(int h, int v)
    {
    	 switch (h) {
         case 0:
         	this.horizontalAlignment = HorizontalAlignment.NORMAL_HORIZONTAL;
         	break;
         case 1:
         	this.horizontalAlignment = HorizontalAlignment.LEFT;
         	break;
         case 2:
         	this.horizontalAlignment = HorizontalAlignment.CENTRE;
         	break;
         case 3:
         	this.horizontalAlignment = HorizontalAlignment.RIGHT;
         	break;
         case 4:
         	this.horizontalAlignment = HorizontalAlignment.CONTINOUS_HORIZONTAL;
         	break;
         default:
         	this.horizontalAlignment = HorizontalAlignment.NORMAL_HORIZONTAL;
         	unsupported("unsupported horizontal alignment "+h);
         }
  
         switch (v) {
         case 0:
         	this.verticalAlignment = VerticalAlignment.NORMAL_VERTICAL;
         	break;
         case 1:
         	this.verticalAlignment = VerticalAlignment.TOP;
         	break;
         case 2:
         	this.verticalAlignment = VerticalAlignment.CAP;
         	break;
         case 3:
         	this.verticalAlignment = VerticalAlignment.HALF;
         	break;
         case 4:
         	this.verticalAlignment = VerticalAlignment.BASE;
         	break;
         case 5:
         	this.verticalAlignment = VerticalAlignment.BOTTOM;
         	break;
         case 6:
         	this.verticalAlignment = VerticalAlignment.CONTINOUS_VERTICAL;
         	break;
         default:
         	this.verticalAlignment = VerticalAlignment.NORMAL_VERTICAL;
         	unsupported("unsupported vertical alignment "+v);
         }
    }
    
    public void write(int tagID, CGMOutputStream cgm) throws IOException {
        cgm.writeEnumerate(this.horizontalAlignment.ordinal());
        cgm.writeEnumerate(this.verticalAlignment.ordinal());
        cgm.writeReal(this.continuousHorizontalAlignment);
        cgm.writeReal(this.continuousVerticalAlignment);
    }
    
	@Override
	public void paint(CGMDisplay d) {
		d.setTextAlignment(this.horizontalAlignment, this.verticalAlignment,
			this.continuousHorizontalAlignment, this.continuousVerticalAlignment);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("TextAlignment");
		sb.append(" horizontal=").append(this.horizontalAlignment);
		sb.append(" vertical=").append(this.verticalAlignment);
		sb.append(" continousHorizontalAlignment=").append(this.continuousHorizontalAlignment);
		sb.append(" continuousVerticalAlignment=").append(this.continuousVerticalAlignment);
        return sb.toString();
    }
}

/*
 * vim:encoding=utf8
 */