package com.cevaris.abstro.base.examples;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.logging.Logger;

import com.cevaris.abstro.base.Worker;

public class AddWorker extends Worker implements Serializable {
	
	private static final long serialVersionUID = 8662215365214326806L;
	private static final Logger LOG = Logger.getLogger(AddWorker.class.getName());
	
	private static final String NUMBER_REGEX = "-?\\d+(\\.\\d+)?";
	
	public List<String> getArgs(){
		return this.args;
	}
	
	public AddWorker setArgs(String[] args){
		this.args.addAll(Arrays.asList(args));
		return this;
	}
	
	@Override
	public Worker validate(){
		
		if(this.args.size() < 2){
			throw new IllegalArgumentException("Not enough args, need atleast 2.");
		}
		
		for(String val : this.args){
			if(!val.matches(NUMBER_REGEX)) throw new IllegalArgumentException("Argument "+val+" not a number.");
		}
		
		return this;		
	}
	
	@Override
	public Worker execute(){
		Integer base = 0;
		for(String val : this.args){
			base += Integer.parseInt(val);
		}
		this.result = base.toString();
		
		return this;		
	}
	
	

}