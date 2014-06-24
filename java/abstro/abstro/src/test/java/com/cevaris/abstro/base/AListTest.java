package com.cevaris.abstro.base;

import java.util.List;

import org.junit.AfterClass;

import com.cevaris.abstro.base.examples.Inventory;
import com.cevaris.abstro.base.examples.Item;

import redis.clients.jedis.Jedis;

import junit.framework.TestCase;

public class AListTest extends TestCase {
	
	Jedis client = new Jedis("localhost", 6379);
	
	@AfterClass
    public void tearDown() {
		// Clears up previous entries		
		for(String key : client.keys("*")){
			client.del(key);
		}
    }
	
	public void testCreate() {
        List<Integer> alist = new AArrayList<Integer>();
        assertNotNull(alist);
    }
	
	public void testSize() {
        List<Integer> alist = new AArrayList<Integer>();
        assertNotNull(alist);
        assertEquals(alist.size(), 0);
        alist.add(10);
        alist.add(15);
        alist.add(16);
        assertEquals(alist.size(), 3);
    }
	
	public void testIsEmpty() {
        List<Integer> alist = new AArrayList<Integer>();
        assertNotNull(alist);
        assertEquals(alist.size(), 0);
        assertEquals(alist.isEmpty(), true);
        assertTrue(alist.add(10));
        assertTrue(alist.add(15));
        assertEquals(alist.isEmpty(), false);
        
    }
	
	public void testAdd() {
        List<Integer> alist = new AArrayList<Integer>();
        assertNotNull(alist);
        assertTrue(alist.add(10));
        assertTrue(alist.add(15));
        assertTrue(alist.add(16));
        assertTrue(10 == alist.get(0));
        assertTrue(15 == alist.get(1));
        assertTrue(16 == alist.get(2));
    }
	
	public void testInt() {
        List<Integer> alist = new AArrayList<Integer>();
        assertNotNull(alist);
        assertTrue(alist.add(10));
        assertTrue(alist.add(15));
        assertTrue(alist.add(16));
        assertTrue(10 == alist.get(0));
        assertTrue(15 == alist.get(1));
        assertTrue(16 == alist.get(2));
    }
	
	public void testLong() {
        List<Long> alist = new AArrayList<Long>();
        assertNotNull(alist);
        assertTrue(alist.add(10L));
        assertTrue(alist.add(15L));
        assertTrue(alist.add(16L));
        assertTrue(10L == alist.get(0));
        assertTrue(15L == alist.get(1));
        assertTrue(16L == alist.get(2));
    }
	
	public void testString() {
        List<String> alist = new AArrayList<String>();
        assertNotNull(alist);
        assertTrue(alist.add("michio kaku"));
        assertTrue(alist.add("kanye west"));
        assertTrue(alist.add("will smith"));
        assertTrue(alist.add("james franco"));
        assertTrue(alist.get(0).equalsIgnoreCase("michio kaku"));
        assertTrue(alist.get(1).equalsIgnoreCase("kanye west"));
        assertTrue(alist.get(2).equalsIgnoreCase("will smith"));
        assertTrue(alist.get(3).equalsIgnoreCase("james franco"));
    }
	
	public void testSetItem() {
        List<String> alist = new AArrayList<String>();
        assertNotNull(alist);
        assertTrue(alist.add("a"));
        assertTrue(alist.add("b"));
        assertTrue(alist.add("c"));
        assertTrue(alist.get(1).equalsIgnoreCase("b"));
        assertNotNull(alist.set(1, "d"));
        assertTrue(alist.get(1).equalsIgnoreCase("d"));
        assertEquals(alist.size(), 3);
    }
	
	public void testRemove() {
        List<Long> alist = new AArrayList<Long>();
        assertNotNull(alist);
        assertTrue(alist.add(10L));
        assertTrue(alist.add(15L));
        assertTrue(alist.add(16L));
        assertTrue(10 == alist.get(0));
        assertTrue(15 == alist.get(1));
        assertTrue(16 == alist.get(2));
        assertTrue(alist.remove(15L));
    }
	
	
	public void testToArrayCopy() {
        List<Long> alist = new AArrayList<Long>();
        assertNotNull(alist);
        assertTrue(alist.add(10L));
        assertTrue(alist.add(15L));
        assertTrue(alist.add(16L));
        Long blist[] = new Long[alist.size()];
        assertNotNull(alist.toArray(blist));
        System.out.print("{ ");
        for(Long l : blist) { System.out.print(l +", "); }
        System.out.println("}");
    }
	
	public void testToArray() {
        List<Long> alist = new AArrayList<Long>();
        assertNotNull(alist);
        assertTrue(alist.add(10L));
        assertTrue(alist.add(15L));
        assertTrue(alist.add(16L));
        Object blist[] = alist.toArray();
        assertNotNull(blist);
        System.out.print("{ ");
        for(Object l : blist) { System.out.print(l +", "); }
        System.out.println("}");
    }
	
	public void testComplexObjectItem() {
        List<Item> alist = new AArrayList<Item>();
        assertNotNull(alist);
        assertTrue(alist.add(new Item()));
        assertTrue(alist.add(new Item()));
        assertTrue(alist.add(new Item()));
        Object blist[] = alist.toArray();
        assertNotNull(blist);
        System.out.print("{ ");
        for(Object l : blist) { System.out.print(l +", "); }
        System.out.println("}");
    }
	
	public void testComplexObjectInventory() {
        List<Inventory> alist = new AArrayList<Inventory>();
        assertNotNull(alist);
        assertTrue(alist.add(new Inventory()));
        assertTrue(alist.add(new Inventory()));
        assertTrue(alist.add(new Inventory()));
        Object blist[] = alist.toArray();
        assertNotNull(blist);
        System.out.print("{ ");
        for(Object l : blist) { System.out.print(l +", "); }
        System.out.println("}");
    }
	
	
}
