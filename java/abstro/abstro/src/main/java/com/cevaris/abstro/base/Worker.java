package com.cevaris.abstro.base;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public abstract class Worker<E> implements Serializable, Runnable {

	private static final long serialVersionUID = -9051196502774620555L;
	private static final Logger LOG = Logger.getLogger(Worker.class.getName());
	
	protected final List<String> args = new ArrayList<String>();
	protected E result = null;
	
	
	public Worker<E> validate() throws IllegalArgumentException {
		return this;
	}
	
	public List<String> getArgs() {
		return this.args;
	}
	
	public E result(){
		return this.result;
	}
	
//	public abstract Worker<E> execute();
	
	
	@SuppressWarnings("unchecked")
	protected E castTo(Object obj){
		return (E) obj;
	}

	public void run() {
		// TODO Auto-generated method stub
		
	}

}
