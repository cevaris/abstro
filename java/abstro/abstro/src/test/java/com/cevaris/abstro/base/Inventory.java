package com.cevaris.abstro.base;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.cevaris.abstro.Utils;

class Inventory implements Serializable {
	private static final long serialVersionUID = 1943627537386162843L;
	private List<Item> items;
	private List<Integer> ints;
	private List<Double> doubles;
	private Item item = new Item();
	private String string = Utils.slug();
	
	public Inventory() {
		this.items = new ArrayList<Item>();
		this.items.add(new Item());
		
		this.ints = new ArrayList<Integer>();
		this.ints.add(Utils.rand.nextInt());
		this.ints.add(Utils.rand.nextInt());
		this.ints.add(Utils.rand.nextInt());
		
		this.doubles = new ArrayList<Double>();
		this.doubles.add(Utils.rand.nextDouble());
	}
	
	@Override
	public String toString() {
		return String.format("'%s' '%s' '%s' '%s' '%s'", 
				this.item,
				this.string,
				Arrays.toString(this.ints.toArray()),
				Arrays.toString(this.doubles.toArray()),
				Arrays.toString(this.items.toArray())
		);
	}
}