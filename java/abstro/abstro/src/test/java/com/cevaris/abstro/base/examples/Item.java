package com.cevaris.abstro.base.examples;

import java.io.Serializable;

import com.cevaris.abstro.Utils;

public class Item implements Serializable {
	private static final long serialVersionUID = 4911335894284088312L;
	private String name;
	public Item() {
		this.name = Utils.slug();
	}
	@Override
	public String toString() {
		return this.name;
	}
}