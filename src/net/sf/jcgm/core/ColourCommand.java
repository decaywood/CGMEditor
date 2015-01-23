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

import java.awt.*;
import java.io.*;

/**
 * @author xphc (Philippe Cadé)
 * @author BBNT Solutions
 * @version $Id: ColourCommand.java 46 2011-12-14 08:26:44Z phica $
 */
public class ColourCommand extends Command {
    protected Color color = null;
	protected int colorIndex = -1;

	public ColourCommand(int ec, int eid, int version){
		super(ec, eid, version);
	}
	public ColourCommand(int ec, int eid, int version, Color color) {
       super(ec, eid, version);
       this.color=color;
    }
	public ColourCommand(int ec, int eid, int version, int colorIndex) {
	       super(ec, eid, version);
	       this.colorIndex=colorIndex;
	}
	public ColourCommand(int ec, int eid, Color color) {
	       super(ec, eid);
	       this.color=color;
	}

    public void write(int tagID, CGMOutputStream cgm) throws IOException {
    	 if (ColourSelectionMode.getType().equals(ColourSelectionMode.Type.DIRECT)) {
         	cgm.writeColorDirect(this.color);
         }
         else if (ColourSelectionMode.getType().equals(ColourSelectionMode.Type.INDEXED)) {
         	cgm.writeColorIndex(this.colorIndex);
         }
    }
    
    public ColourCommand(int ec, int eid, int l, DataInput in, int version)
            throws IOException {
        super(ec, eid, l, in, version);
        
        if (ColourSelectionMode.getType().equals(ColourSelectionMode.Type.DIRECT)) {
        	this.color = makeDirectColor();
        }
        else if (ColourSelectionMode.getType().equals(ColourSelectionMode.Type.INDEXED)) {
        	this.colorIndex = makeColorIndex();
        }
    }
    public ColourCommand(int ec, int eid, int l, DataInput in)
            throws IOException {
        super(ec, eid, l, in);
        
        if (ColourSelectionMode.getType().equals(ColourSelectionMode.Type.DIRECT)) {
        	this.color = makeDirectColor();
        }
        else if (ColourSelectionMode.getType().equals(ColourSelectionMode.Type.INDEXED)) {
        	this.colorIndex = makeColorIndex();
        }
    }
    
    @Override
	public String toString() {
    	StringBuilder sb = new StringBuilder();
    	if (this.color != null) {
    		sb.append(" directColor=").append(this.color);
    	}
    	else {
    		sb.append(" colorIndex=").append(this.colorIndex);
    	}
        return sb.toString();
    }
}

/*
 * vim:encoding=utf8
 */
