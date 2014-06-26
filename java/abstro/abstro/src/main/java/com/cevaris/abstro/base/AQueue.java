package com.cevaris.abstro.base;

import java.io.Serializable;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Queue;
import java.util.logging.Logger;

import com.cevaris.abstro.Utils;

import redis.clients.jedis.Jedis;

public class AQueue<E> implements Queue<E>, Serializable {

	private static final long serialVersionUID = 7503703192028702396L;
	
	private final static Logger LOG = Logger.getLogger(AQueue.class.getName()); 
	
	private Jedis client = null;
	private String key   = null;
	
	public AQueue() {
		this.client = new Jedis("localhost", 6379);
		this.key = Utils.slug();
		
		LOG.info(String.format("Created object with key: %s", this.key));
	}

	public int size() {
		return this.client.llen(this.key).intValue();
	}

	public boolean isEmpty() {
		return this.client.llen(this.key) == 0L;
	}

	public boolean contains(Object o) {
		throw new UnsupportedOperationException("Not Implmented");
	}

	public Iterator<E> iterator() {
		throw new UnsupportedOperationException("Not Implmented");
	}

	public Object[] toArray() {
		throw new UnsupportedOperationException("Not Implmented");
	}

	public <T> T[] toArray(T[] a) {
		throw new UnsupportedOperationException("Not Implmented");
	}

	public boolean remove(Object o) {
		throw new UnsupportedOperationException("Not Implmented");
	}

	public boolean containsAll(Collection<?> c) {
		throw new UnsupportedOperationException("Not Implmented");
	}

	public boolean addAll(Collection<? extends E> c) {
		boolean result = true;
		for(E e : c){
			result = result && add(e);			
		}
		return result;
	}

	public boolean removeAll(Collection<?> c) {
		throw new UnsupportedOperationException("Not Implmented");
	}

	public boolean retainAll(Collection<?> c) {
		throw new UnsupportedOperationException("Not Implmented");
	}

	public void clear() {
		destroy();
	}

	public boolean add(E e) {
		if(this.client.rpush(this.key, Utils.encode(e)) > 0L){
			return true;
		} else {
			return false;
		}
	}

	public boolean offer(E e) {
		throw new UnsupportedOperationException("Not Implmented");
	}

	public E remove() {
		throw new UnsupportedOperationException("Not Implmented");
	}

	public E poll() {
		String result = this.client.lpop(this.key);
		if(result != null){
			System.out.println(Utils.decode(result));
			return castTo(Utils.decode(result));
		} else {
			return null;
		}
	}

	public E element() {
		if(this.isEmpty()){
			throw new NoSuchElementException();
		}else {
			return peek();
		}
	}

	public E peek() {
		List<String> results = this.client.lrange(this.key, 0, 0);
		String result = null;
		if(results.size() != 1) {
			return null;
		} else {
			result = results.get(0);
		}
		return castTo(Utils.decode(result));
	}
	
	
	
	// Custom definitions
	
	@SuppressWarnings("unchecked")
	public E castTo(final Object obj) {
		// Only call after checking type!!!
		return (E) obj;
	}
	
	private boolean destroy(){
		return this.client.del(this.key) > 0;
	}
	
	@Override
	public void finalize() throws Throwable {
		LOG.info("Deleted Object: "+this.key);
		destroy();
		super.finalize();
	}

}
