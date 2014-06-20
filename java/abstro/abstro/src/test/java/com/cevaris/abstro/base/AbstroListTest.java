package com.cevaris.abstro.base;

import java.util.List;

import junit.framework.TestCase;

public class AbstroListTest extends TestCase {
	
	
	public void testCreate() {
        List<Integer> alist = new ArrayList<Integer>();
        assertNotNull(alist);
    }
	
	public void testSize() {
        List<Integer> alist = new ArrayList<Integer>();
        assertNotNull(alist);
        assertEquals(alist.size(), 0);
        alist.add(10);
        alist.add(15);
        alist.add(16);
        assertEquals(alist.size(), 3);
    }
	
	
	public void testAdd() {
        List<Integer> alist = new ArrayList<Integer>();
        assertNotNull(alist);
        assertTrue(alist.add(10));
        assertTrue(alist.add(15));
        assertTrue(alist.add(16));
        assertTrue(10 == alist.get(0));
        assertTrue(15 == alist.get(1));
        assertTrue(16 == alist.get(2));
    }
	
	
}
