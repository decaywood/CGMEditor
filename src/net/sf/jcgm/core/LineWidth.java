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

import java.io.DataInput;
import java.io.IOException;



/**
 * Class=5, Element=3
 * @author xphc (Philippe Cadé)
 * @author BBNT Solutions
 * @version $Id: LineWidth.java 46 2011-12-14 08:26:44Z phica $
 */
public class LineWidth extends Command {
	protected double width;

    public LineWidth() {
        super(5, 3, 1);
    }

    public LineWidth(double width) {
        this();
        this.width = width;
    }
	public LineWidth(int ec, int eid, int l, DataInput in)
			throws IOException {
		super(ec, eid, l, in, 1);
		SpecificationMode mode = LineWidthSpecificationMode.getMode();
		this.width = makeSizeSpecification(mode);

		if (!SpecificationMode.ABSOLUTE.equals(mode) && !SpecificationMode.SCALED.equals(mode)) {
			unimplemented("LineWidth specification mode "+mode+" not implemented");
		}
	}

    public void write(int tagID, CGMOutputStream cgm) throws IOException {
        if (cgm.getLineWidthSpecificationMode() == LineWidthSpecificationMode.ABSOLUTE) {
            cgm.writeVDC(width);
        } else {
            cgm.writeReal(width);
        }
    }
	@Override
	public void paint(CGMDisplay d) {
		d.setLineWidth(this.width);
	}

	@Override
	public String toString() {
		return "LineWidth " + this.width;
	}
}

/*
 * vim:encoding=utf8
 */
