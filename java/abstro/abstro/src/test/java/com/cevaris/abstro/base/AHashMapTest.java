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
			client.del(key);
		}
    }
	
	public void testCreate() {
        Map<String, Integer> aMap = new AHashMap<String, Integer>();
        assertNotNull(aMap);
    }
	
	public void testPutGet() {
        Map<String, Integer> aMap = new AHashMap<String, Integer>();
        assertNotNull(aMap);
        assertNotNull(aMap.put("a", 1));
        assertNotNull(aMap.put("b", 2));
        
        assertTrue(aMap.get("a") == 1);
        assertTrue(aMap.get("a").getClass() == Integer.class);
        
        assertTrue(aMap.get("b") == 2);
        assertTrue(aMap.get("b").getClass() == Integer.class);
    }
	
	

}
