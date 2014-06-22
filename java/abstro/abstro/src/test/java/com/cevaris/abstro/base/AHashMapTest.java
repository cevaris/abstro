package com.cevaris.abstro.base;

import static org.junit.Assert.*;

import java.util.Map;

import junit.framework.TestCase;

import org.junit.AfterClass;

import redis.clients.jedis.Jedis;

public class AHashMapTest extends TestCase {

Jedis client = new Jedis("localhost", 6379);
	
	@AfterClass
    public void tearDown() {
		// Clears up previous entries		
		for(String key : client.keys("*")){
//			client.del(key);
		}
    }
	
	public void testCreate() {
        Map<String, Integer> aMap = new AHashMap<String, Integer>();
        assertNotNull(aMap);
    }
	
	public void testPutGetInt() {
        Map<String, Integer> aMap = new AHashMap<String, Integer>();
        assertNotNull(aMap);
        assertNotNull(aMap.put("a", 1));
        assertNotNull(aMap.put("b", 2));
        
        assertTrue(aMap.get("a") == 1);
        assertTrue(aMap.get("a").getClass() == Integer.class);
        
        assertTrue(aMap.get("b") == 2);
        assertTrue(aMap.get("b").getClass() == Integer.class);
    }
	
	
	public void testPutGetInventory() {
        Map<String, Inventory> aMap = new AHashMap<String, Inventory>();
        assertNotNull(aMap);
        Inventory aInv = new Inventory();
        Inventory bInv = new Inventory();
        assertNotNull(aMap.put("a", aInv));
        assertNotNull(aMap.put("b", bInv));
        
        assertTrue(aMap.get("a").getClass() == Inventory.class);
        assertTrue(aMap.get("b").getClass() == Inventory.class);
        
        Inventory aaInv = aMap.get("a");
        Inventory bbInv = aMap.get("b");
        
        assertTrue(aInv.toString().equalsIgnoreCase(aaInv.toString()));
        assertTrue(bInv.toString().equalsIgnoreCase(bbInv.toString()));
    }
	
	public void testPutSize() {
        Map<String, Integer> aMap = new AHashMap<String, Integer>();
        assertNotNull(aMap);
        assertTrue(aMap.size() == 0);
        assertTrue(aMap.isEmpty());
        assertNotNull(aMap.put("a", 1));
        assertNotNull(aMap.put("b", 2));
        
        assertTrue(aMap.size() == 2);
        assertTrue(!aMap.isEmpty());
    }
	
	public void testContainsKey() {
        Map<String, Integer> aMap = new AHashMap<String, Integer>();
        assertNotNull(aMap);
        assertNotNull(aMap.put("a", 1));
        assertNotNull(aMap.put("b", 2));
        
        assertTrue(aMap.containsKey("a"));
        assertTrue(aMap.containsKey("b"));
    }
	
	public void testRemove() {
        Map<String, Integer> aMap = new AHashMap<String, Integer>();
        assertNotNull(aMap);
        assertNotNull(aMap.put("a", 1));
        assertNotNull(aMap.put("b", 2));
        
        assertTrue(aMap.get("a") == 1);
        assertEquals(1, aMap.remove("a").intValue());
        assertNull(aMap.get("a"));
        
    }

}
