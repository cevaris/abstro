package com.cevaris.abstro.base;

import junit.framework.Assert;
import junit.framework.TestCase;

import org.junit.AfterClass;

import com.cevaris.abstro.base.examples.AddWorker;

import redis.clients.jedis.Jedis;

public class AddWorkerTest extends TestCase {

	Jedis client = new Jedis("localhost", 6379);

	@AfterClass
    public void tearDown() {
		// Clears up previous entries		
		for(String key : client.keys("*")){
			client.del(key);
		}
    }
	
	public void testCreate() {
        Worker wrk = new AddWorker();
        assertNotNull(wrk);
    }
	
	public void testIllegalArgumentException() {
        Worker wrk = new AddWorker();
        assertNotNull(wrk);
        try{
        	wrk.validate();
        	Assert.fail();
        } catch (IllegalArgumentException e){}
    }
	
	public void testSetArgs() {
        Worker wrk = new AddWorker().
        		setArgs(new String[]{"0", "3"});
        assertNotNull(wrk);
        assertNotNull(wrk.validate());
        assertNotNull(wrk.getArgs());
        assertTrue(wrk.getArgs().size() == 2);
    }
	
	public void testExecute() {
        Worker wrk = new AddWorker().
        		setArgs(new String[]{"1", "3"});
        assertNotNull(wrk);
        assertNotNull(wrk.validate());
        assertNotNull(wrk.execute());
        assertEquals(wrk.result(), "4");
    }
	
}
