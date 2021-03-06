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
 * Class=1, Element=3
 * @author xphc (Philippe Cadé)
 * @author BBNT Solutions
 * @version $Id: VDCType.java 46 2011-12-14 08:26:44Z phica $
 */
public class VDCType extends Command {
	public enum Type {
		INTEGER,
		REAL
	}
	
	/** Default is INTEGER */
    private static Type type = Type.INTEGER;
    
    static {
    	reset();
    }

    public VDCType() {
        super(1, 3, 1);
    }

    public VDCType(int type) {
        this();
        if (type== 0)
        	VDCType.type = Type.INTEGER;
        else if (type == 1)
        	VDCType.type = Type.REAL;
        else{
        	VDCType.type=Type.INTEGER;
        	unsupported("VDC Type "+type);
        }
    }

    public VDCType(VDCType.Type type) {
        this();
        VDCType.type=type;
    }
    
    public VDCType(int ec, int eid, int l, DataInput in)
            throws IOException {
        super(ec, eid, l, in, 1);
        int p1 = makeInt();
        if (p1 == 0)
        	type = Type.INTEGER;
        else if (p1 == 1)
        	type = Type.REAL;
        else
        	unsupported("VDC Type "+p1);
    }

    public static void reset() {
    	// default is integer
		type = Type.INTEGER;
	}

    public void write(int tagID, CGMOutputStream cgm) throws IOException {
        cgm.setVDCReal((type ==  Type.INTEGER) ? false : true);
        cgm.writeEnumerate(type.ordinal());
    }
    
	@Override
	public String toString() {
        return "VDCType [" + String.valueOf(type) + "]";
    }
    
    public static Type getType() {
    	return type;
    }
}

/*
 * vim:encoding=utf8
 */
