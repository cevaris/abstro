package com.cevaris.abstro.base;

import static org.junit.Assert.*;

import java.util.Queue;

import junit.framework.TestCase;

import org.junit.AfterClass;

import com.cevaris.abstro.base.examples.Item;

import redis.clients.jedis.Jedis;

public class AQueueTest extends TestCase {

	Jedis client = new Jedis("localhost", 6379);
	
	@AfterClass
    public void tearDown() {
		// Clears up previous entries		
		for(String key : client.keys("*")){
			client.del(key);
		}
    }
	
	public void testCreate() {
        Queue<Item> qItm = new AQueue<Item>();
        assertNotNull(qItm);
    }
	
	public void testSize() {
		Queue<Item> qItm = new AQueue<Item>();
        assertNotNull(qItm);
        assertEquals(qItm.size(), 0);
        qItm.add(new Item());
        qItm.add(new Item());
        qItm.add(new Item());
        assertEquals(qItm.size(), 3);
    }

}
