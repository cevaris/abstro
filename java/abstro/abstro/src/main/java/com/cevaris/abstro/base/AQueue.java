package com.cevaris.abstro.base;

import java.io.Serializable;
import java.util.Collection;
import java.util.Iterator;
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
		// TODO Auto-generated method stub
		return false;
	}

	public Iterator<E> iterator() {
		// TODO Auto-generated method stub
		return null;
	}

	public Object[] toArray() {
		// TODO Auto-generated method stub
		return null;
	}

	public <T> T[] toArray(T[] a) {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean remove(Object o) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean containsAll(Collection<?> c) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean addAll(Collection<? extends E> c) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean removeAll(Collection<?> c) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean retainAll(Collection<?> c) {
		// TODO Auto-generated method stub
		return false;
	}

	public void clear() {
		// TODO Auto-generated method stub
		
	}

	public boolean add(E e) {
		return this.client.rpush(this.key, Utils.encode(e)) > 0L;
	}

	public boolean offer(E e) {
		// TODO Auto-generated method stub
		return false;
	}

	public E remove() {
		// TODO Auto-generated method stub
		return null;
	}

	public E poll() {
		// TODO Auto-generated method stub
		return null;
	}

	public E element() {
		// TODO Auto-generated method stub
		return null;
	}

	public E peek() {
		// TODO Auto-generated method stub
		return null;
	}

}
