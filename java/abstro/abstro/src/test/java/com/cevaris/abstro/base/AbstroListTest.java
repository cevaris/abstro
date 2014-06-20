package com.cevaris.abstro.base;

import java.util.List;

import org.junit.AfterClass;

import redis.clients.jedis.Jedis;

import junit.framework.TestCase;

public class AbstroListTest extends TestCase {
	
	Jedis client = new Jedis("localhost", 6379);
	
	@AfterClass
    public void tearDown() {
		// Clears up previous entries		
		for(String key : client.keys("*")){
			client.del(key);
		}
    }
	
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
	
	public void testIsEmpty() {
        List<Integer> alist = new ArrayList<Integer>();
        assertNotNull(alist);
        assertEquals(alist.size(), 0);
        assertEquals(alist.isEmpty(), true);
        assertTrue(alist.add(10));
        assertTrue(alist.add(15));
        assertEquals(alist.isEmpty(), false);
        
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
	
	public void testInt() {
        List<Integer> alist = new ArrayList<Integer>();
        assertNotNull(alist);
        assertTrue(alist.add(10));
        assertTrue(alist.add(15));
        assertTrue(alist.add(16));
        assertTrue(10 == alist.get(0));
        assertTrue(15 == alist.get(1));
        assertTrue(16 == alist.get(2));
    }
	
	public void testLong() {
        List<Long> alist = new ArrayList<Long>();
        assertNotNull(alist);
        assertTrue(alist.add(10L));
        assertTrue(alist.add(15L));
        assertTrue(alist.add(16L));
        assertTrue(10L == alist.get(0));
        assertTrue(15L == alist.get(1));
        assertTrue(16L == alist.get(2));
    }
	
	public void testString() {
        List<String> alist = new ArrayList<String>();
        assertNotNull(alist);
        assertTrue(alist.add("a"));
        assertTrue(alist.add("b"));
        assertTrue(alist.add("c"));
        assertTrue(alist.get(0).equalsIgnoreCase("a"));
        assertTrue(alist.get(1).equalsIgnoreCase("b"));
        assertTrue(alist.get(2).equalsIgnoreCase("c"));
    }
	
	
}
