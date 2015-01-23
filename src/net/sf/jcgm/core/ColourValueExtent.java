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

import java.awt.Color;
import java.io.*;

import net.sf.jcgm.core.ColourModel.Model;


/**
 * Class=1, Element=10
 * @author xphc (Philippe Cadé)
 * @author BBNT Solutions
 * @version $Id: ColourValueExtent.java 46 2011-12-14 08:26:44Z phica $
 */
public class ColourValueExtent extends Command {
    static private int[] minimumColorValueRGB;
	static private int[] maximumColorValueRGB;
	private double firstComponentScale;
	private double secondComponentScale;
	private double thirdComponentScale;
	
	static {
		reset();
	}
	
    public ColourValueExtent() {
        super(1, 10, 1);
    }

    // FIXME: only RGB ColorModel allowed
    public ColourValueExtent(Color minColor, Color maxColor) {
        this();
        Model colorModel = ColourModel.getModel();
		if (colorModel.equals(ColourModel.Model.RGB) || colorModel.equals(ColourModel.Model.CMYK)) {
        	int precision = ColourPrecision.getPrecision();
        	
        	if (colorModel.equals(Model.RGB)) {
        		ColourValueExtent.minimumColorValueRGB = new int[3];
        		ColourValueExtent.maximumColorValueRGB = new int[3];// { makeUInt(precision), makeUInt(precision), makeUInt(precision) };
        		ColourValueExtent.minimumColorValueRGB[0]=minColor.getRed();
        		ColourValueExtent.minimumColorValueRGB[1]=minColor.getGreen();
        		ColourValueExtent.minimumColorValueRGB[2]=minColor.getBlue();
        		ColourValueExtent.maximumColorValueRGB[0]=maxColor.getRed();
        		ColourValueExtent.maximumColorValueRGB[1]=maxColor.getGreen();
        		ColourValueExtent.maximumColorValueRGB[2]=maxColor.getBlue();
        	}
        	else {
        		unsupported("unsupported color model "+colorModel);
        	}
        }
        else if (colorModel.equals(ColourModel.Model.CIELAB) || 
        		colorModel.equals(ColourModel.Model.CIELUV) ||
        		colorModel.equals(ColourModel.Model.RGB_RELATED)) {
        	this.firstComponentScale = makeReal();
        	this.secondComponentScale = makeReal();
        	this.thirdComponentScale = makeReal();
        }
        else {
    		unsupported("unsupported color model "+colorModel);
        }
    }

    public void write(int tagID, CGMOutputStream cgm) throws IOException {
    	Model colorModel = ColourModel.getModel();
 		if (colorModel.equals(ColourModel.Model.RGB) || colorModel.equals(ColourModel.Model.CMYK)) {       	
         	if (colorModel.equals(Model.RGB)) {
         		cgm.writeColorDirect(new Color(ColourValueExtent.minimumColorValueRGB[0],ColourValueExtent.minimumColorValueRGB[1],ColourValueExtent.minimumColorValueRGB[2]));
         		cgm.writeColorDirect(new Color(ColourValueExtent.maximumColorValueRGB[0],ColourValueExtent.maximumColorValueRGB[1],ColourValueExtent.maximumColorValueRGB[2]));
         	}
         	else {
         		unsupported("unsupported color model "+colorModel);
         	}
         }
         else if (colorModel.equals(ColourModel.Model.CIELAB) || 
         		colorModel.equals(ColourModel.Model.CIELUV) ||
         		colorModel.equals(ColourModel.Model.RGB_RELATED)) {
        	 cgm.writeReal(this.firstComponentScale);
        	 cgm.writeReal(this.secondComponentScale);
        	 cgm.writeReal(this.thirdComponentScale);
         }
         else {
     		unsupported("unsupported color model "+colorModel);
         }
    }

	public ColourValueExtent(int ec, int eid, int l, DataInput in)
            throws IOException {
        super(ec, eid, l, in);
        
        Model colorModel = ColourModel.getModel();
		if (colorModel.equals(ColourModel.Model.RGB) || colorModel.equals(ColourModel.Model.CMYK)) {
        	int precision = ColourPrecision.getPrecision();
        	
        	if (colorModel.equals(Model.RGB)) {
        		ColourValueExtent.minimumColorValueRGB = new int[] { makeUInt(precision), makeUInt(precision), makeUInt(precision) };
        		ColourValueExtent.maximumColorValueRGB = new int[] { makeUInt(precision), makeUInt(precision), makeUInt(precision) };
        	}
        	else {
        		unsupported("unsupported color model "+colorModel);
        	}
        }
        else if (colorModel.equals(ColourModel.Model.CIELAB) || 
        		colorModel.equals(ColourModel.Model.CIELUV) ||
        		colorModel.equals(ColourModel.Model.RGB_RELATED)) {
        	this.firstComponentScale = makeReal();
        	this.secondComponentScale = makeReal();
        	this.thirdComponentScale = makeReal();
        }
        else {
    		unsupported("unsupported color model "+colorModel);
        }
        
        // make sure all the arguments were read
        assert (this.currentArg == this.args.length);
    }
	
	public static void reset() {
		minimumColorValueRGB = new int[] { 0, 0, 0 };
		maximumColorValueRGB = new int[] { 255, 255, 255 };
	}
	
	static int[] getMinimumColorValueRGB() {
		return minimumColorValueRGB;
	}
	
	static int[] getMaximumColorValueRGB() {
		return maximumColorValueRGB;
	}

    @Override
	public String toString() {
    	StringBuilder sb = new StringBuilder();
    	sb.append("ColourValueExtent");
        if (ColourModel.getModel().equals(ColourModel.Model.RGB)) {
        	sb.append(" min RGB=(").append(ColourValueExtent.minimumColorValueRGB[0]).append(",");
        	sb.append(minimumColorValueRGB[1]).append(",");
        	sb.append(minimumColorValueRGB[2]).append(")");
        	
        	sb.append(" max RGB=(").append(ColourValueExtent.maximumColorValueRGB[0]).append(",");
        	sb.append(maximumColorValueRGB[1]).append(",");
        	sb.append(maximumColorValueRGB[2]).append(")");
        }
        else if (ColourModel.getModel().equals(ColourModel.Model.CMYK)) {
        	// unsupported
        }
        else if (ColourModel.getModel().equals(ColourModel.Model.CIELAB) || 
        		ColourModel.getModel().equals(ColourModel.Model.CIELUV) ||
        		ColourModel.getModel().equals(ColourModel.Model.RGB_RELATED)) {
        	sb.append(" first=").append(this.firstComponentScale);
        	sb.append(" second=").append(this.secondComponentScale);
        	sb.append(" third=").append(this.thirdComponentScale);
        }
        return sb.toString();
    }
}

/*
 * vim:encoding=utf8
 */
