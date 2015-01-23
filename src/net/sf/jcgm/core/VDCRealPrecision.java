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
 * Class=3, Element=2
 * @author xphc (Philippe Cadé)
 * @author BBNT Solutions
 * @version $Id: VDCRealPrecision.java 46 2011-12-14 08:26:44Z phica $
 */
public class VDCRealPrecision extends Command {
    enum Type {
		FLOATING_POINT_32BIT,  //0
		FLOATING_POINT_64BIT,  //1
		FIXED_POINT_32BIT,    //2
		FIXED_POINT_64BIT,    //3
	}

	private static Type precision;
	
	static {
		reset();
	}
	
    public VDCRealPrecision() {
        super(3, 2, 1);
    }

    public VDCRealPrecision(int precision) {
        super(3, 2, 1);
        switch(precision){
        case 0:
        	VDCRealPrecision.precision=Type.FLOATING_POINT_32BIT;
        	break;
        case 1:
        	VDCRealPrecision.precision=Type.FLOATING_POINT_64BIT;
        	break;
        case 2:
        	VDCRealPrecision.precision=Type.FIXED_POINT_32BIT;
        	break;
        case 3:
        	VDCRealPrecision.precision=Type.FIXED_POINT_64BIT;
        	break;
        default:
        	VDCRealPrecision.precision=Type.FIXED_POINT_32BIT;
        	break;
        }
    }
    
    public VDCRealPrecision(VDCRealPrecision.Type precision) {
        super(3, 2, 1);
        VDCRealPrecision.precision=precision;
    }
    
    public void write(int tagID, CGMOutputStream cgm) throws IOException {
    	switch(precision){
    	case FLOATING_POINT_32BIT:
            cgm.setVDCRealPrecision(false, true);//(fixed,floating)
            cgm.writeEnumerate(0); // floating
            cgm.writeInteger(9); // double exp
            cgm.writeInteger(23); // double fract
            break;
    	case FLOATING_POINT_64BIT:
            cgm.setVDCRealPrecision(false, true); //(fixed,floating)
            cgm.writeEnumerate(0); // floating
            cgm.writeInteger(12); // double exp
            cgm.writeInteger(52); // double fract
            break;
    	case FIXED_POINT_32BIT:
            cgm.setVDCRealPrecision(true, false);//(fixed,floating)
            cgm.writeEnumerate(0); // floating
            cgm.writeInteger(16); // double exp
            cgm.writeInteger(16); // double fract
            break;
    	case FIXED_POINT_64BIT:
            cgm.setVDCRealPrecision(true, false);//(fixed,floating)
            cgm.writeEnumerate(0); // floating
            cgm.writeInteger(32); // double exp
            cgm.writeInteger(32); // double fract
            break; 
    	default:
            cgm.setVDCRealPrecision(false, true);//(fixed,floating)
            cgm.writeEnumerate(0); // floating
            cgm.writeInteger(16); // double exp
            cgm.writeInteger(16); // double fract
            break;
    	}
    }
    
    public VDCRealPrecision(int ec, int eid, int l, DataInput in)
            throws IOException {

        super(ec, eid, l, in, 1);
        int p1 = makeInt();
        int p2 = makeInt();
        int p3 = makeInt();

        if (p1 == 0) {
        	if (p2 == 9 && p3 == 23) {
        		VDCRealPrecision.precision = Type.FLOATING_POINT_32BIT;
        	}
        	else if (p2 == 12 && p3 == 52) {
        		VDCRealPrecision.precision = Type.FLOATING_POINT_64BIT;
        	}
        	else {
        		// use default
        		unsupported("unsupported real precision");
        		VDCRealPrecision.precision = Type.FIXED_POINT_32BIT;
        	}
        }
        else if (p1 == 1) {
        	if (p2 == 16 && p3 == 16) {
        		VDCRealPrecision.precision = Type.FIXED_POINT_32BIT;
        	}
        	else if (p2 == 32 && p3 == 32) {
        		VDCRealPrecision.precision = Type.FIXED_POINT_64BIT;
        	}
        	else {
        		// use default
        		unsupported("unsupported real precision");
        		VDCRealPrecision.precision = Type.FIXED_POINT_32BIT;
        	}
        }
    }
    
    public static void reset() {
    	precision = Type.FIXED_POINT_32BIT;
	}

	public static Type getPrecision() {
    	return VDCRealPrecision.precision;
    }

    @Override
	public String toString() {
        String s = "VDCRealPrecision " + String.valueOf(VDCRealPrecision.precision);
        return s;
    }
}

/*
 * vim:encoding=utf8
 */
