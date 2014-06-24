package com.cevaris.abstro.base;

import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;


public class WorkerManager {
	
	private static final Logger LOG = Logger.getLogger(WorkerManager.class.getName());
	
	private final int nThreads;
    private final PoolWorker[] threads;
    private final LinkedList<Worker<?>> queue;

    public WorkerManager(int nThreads)
    {
        this.nThreads = nThreads;
        queue = new LinkedList<Worker<?>>();
        threads = new PoolWorker[nThreads];

        for (int i=0; i<nThreads; i++) {
            threads[i] = new PoolWorker();
            threads[i].start();
        }
    }
    
    public void enqueue(Worker<?> r) {
        synchronized(queue) {
            queue.addLast(r);
            queue.notify();
        }
    }

    public boolean isWorking() {
        synchronized(queue) {
        	return !this.queue.isEmpty();
        }
    }

    private class PoolWorker extends Thread {
        public void run() {
            Runnable r;

            while (true) {
                synchronized(queue) {
                    while (queue.isEmpty()) {
                        try {
                            queue.wait();
                        }
                        catch (InterruptedException ignored) {
                        	LOG.log(Level.SEVERE, ignored.getMessage());                        	
                        }
                    }

                    r = (Runnable) queue.removeFirst();
                }

                // If we don't catch RuntimeException, 
                // the pool could leak threads
                try {
                    r.run();
                }
                catch (RuntimeException e) {
                	LOG.log(Level.SEVERE, e.getMessage());
                }
            }
        }
    }
	
}
