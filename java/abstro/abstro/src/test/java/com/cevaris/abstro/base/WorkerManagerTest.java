package com.cevaris.abstro.base;

import static org.junit.Assert.*;

import java.util.List;

import junit.framework.TestCase;

import org.junit.AfterClass;

import com.cevaris.abstro.base.examples.AddWorker;

import redis.clients.jedis.Jedis;

public class WorkerManagerTest extends TestCase {

Jedis client = new Jedis("localhost", 6379);
	
	@AfterClass
    public void tearDown() {
		// Clears up previous entries		
		for(String key : client.keys("*")){
			client.del(key);
		}
    }
	
	public void testCreate() {
		WorkerManager wrkm = new WorkerManager(5);
        assertNotNull(wrkm);
    }
	
	public void testWork() throws InterruptedException {
		WorkerManager wrkm = new WorkerManager(5);
        assertNotNull(wrkm);
        
        Worker<Double> wrk  = new AddWorker<Double>().
        		setArgs(new String[]{"1", "3"});
        assertNotNull(wrk);
        Worker<Double> wrk2 = new AddWorker<Double>().
        		setArgs(new String[]{"5", "6"});
        
        wrkm.enqueue(wrk);
        wrkm.enqueue(wrk2);
        while(wrkm.isWorking()){Thread.sleep(500L);}
        
        assertEquals(wrk.result(), 4.0);        
        assertEquals(wrk2.result(), 11.0);
    }
	
}
