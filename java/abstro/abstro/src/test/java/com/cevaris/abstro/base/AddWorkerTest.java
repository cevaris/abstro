package com.cevaris.abstro.base;

import junit.framework.Assert;
import junit.framework.TestCase;

import org.junit.AfterClass;

import com.cevaris.abstro.Utils;
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
        Worker<Double> wrk = new AddWorker<Double>();
        assertNotNull(wrk);
    }
	
	public void testIllegalArgumentException() {
        Worker<Double> wrk = new AddWorker<Double>();
        assertNotNull(wrk);
        try{
        	wrk.validate();
        	Assert.fail();
        } catch (IllegalArgumentException e){}
    }
	
	public void testSetArgs() {
        Worker<Double> wrk = new AddWorker<Double>().
        		setArgs(new String[]{"0", "3"});
        assertNotNull(wrk);
        assertNotNull(wrk.validate());
        assertNotNull(wrk.getArgs());
        assertTrue(wrk.getArgs().size() == 2);
    }
	
	public void testExecute() {
        Worker<Double> wrk = new AddWorker<Double>().
        		setArgs(new String[]{"1", "3"});
        assertNotNull(wrk);
        assertNotNull(wrk.validate());
        assertNotNull(wrk.execute());
        assertEquals(wrk.result(), 4.0);
    }
	
	
	public void testSerializatoin() {
        Worker<Double> wrk = new AddWorker<Double>().
        		setArgs(new String[]{"1", "3"});
        assertNotNull(wrk.validate());
        
        String serializedObj = Utils.encode(wrk);
        assertNotNull(serializedObj);
        Worker<Double> wrk2 = Utils.decode(serializedObj, Worker.class);
        assertNotNull(wrk2);
        assertNotNull(wrk2.validate());
        assertEquals(wrk.result(), wrk2.result());
    }
	
}
