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
 * Class=1, Element=15
 * @author xphc (Philippe Cadé)
 * @author BBNT Solutions
 * @version $Id: CharacterCodingAnnouncer.java 46 2011-12-14 08:26:44Z phica $
 */
public class CharacterCodingAnnouncer extends Command {
	public enum Type {
		BASIC_7_BIT,
		BASIC_8_BIT,
		EXTENDED_7_BIT,
		EXTENDED_8_BIT,
	}
	
	Type type;
	public CharacterCodingAnnouncer() {
	    super(1, 15, 1);
	}

	public CharacterCodingAnnouncer(CharacterCodingAnnouncer.Type type){
		this();
		this.type = type;
	}
	
	public CharacterCodingAnnouncer(int type) {
	    this();
	    switch (type) {
        case 0:
        	this.type = Type.BASIC_7_BIT;
        	break;
        case 1:
        	this.type = Type.BASIC_8_BIT;
        	break;
        case 2:
        	this.type = Type.EXTENDED_7_BIT;
        	break;
        case 3:
        	this.type = Type.EXTENDED_8_BIT;
        	break;
        default:
        	unsupported("unsupported character coding type "+type);
        	this.type = Type.BASIC_7_BIT;
        }
	}
    public CharacterCodingAnnouncer(int ec, int eid, int l, DataInput in)
            throws IOException {
        super(ec, eid, l, in);
        
        int t= makeEnum();
        switch (t) {
        case 0:
        	this.type = Type.BASIC_7_BIT;
        	break;
        case 1:
        	this.type = Type.BASIC_8_BIT;
        	break;
        case 2:
        	this.type = Type.EXTENDED_7_BIT;
        	break;
        case 3:
        	this.type = Type.EXTENDED_8_BIT;
        	break;
        default:
        	unsupported("unsupported character coding type "+t);
        	this.type = Type.BASIC_7_BIT;
        }
        
        unimplemented("CharacterCodingAnnouncer");
    }
    public void write(int tagID, CGMOutputStream cgm) throws IOException {
        switch(this.type){
        case BASIC_7_BIT:
        	cgm.writeInteger(0);
        	break;
        case BASIC_8_BIT:
        	cgm.writeInteger(1);
        	break;
        case EXTENDED_7_BIT:
        	cgm.writeInteger(2);
        	break;
        case EXTENDED_8_BIT:
        	cgm.writeInteger(3);
        	break;
        default:
        	cgm.writeInteger(-1);
        	break;        
        }
    }
    @Override
	public String toString() {
    	StringBuilder sb = new StringBuilder();
    	sb.append("CharacterCodingAnnouncer type=").append(this.type);
    	return sb.toString();
    }
}

/*
 * vim:encoding=utf8
 */
