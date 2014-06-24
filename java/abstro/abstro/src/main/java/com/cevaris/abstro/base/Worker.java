package com.cevaris.abstro.base;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public abstract class Worker implements Serializable {

	private static final long serialVersionUID = -9051196502774620555L;
	private static final Logger LOG = Logger.getLogger(Worker.class.getName());
	
	
	protected final List<String> args = new ArrayList<String>();
	protected String result = "";
	
	
	public Worker validate() throws IllegalArgumentException {
		return this;
	}
	
	public List<String> getArgs() {
		return this.args;
	}
	
	public String result(){
		return this.result;
	}
	
	public abstract Worker execute();

}
