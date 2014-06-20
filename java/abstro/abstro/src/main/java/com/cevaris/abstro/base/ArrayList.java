package com.cevaris.abstro.base;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.logging.Logger;

import redis.clients.jedis.Jedis;

public class ArrayList<E> implements List<E>{
	
	private final static Logger LOG = Logger.getLogger(ArrayList.class.getName()); 
	
	private Jedis client = null;
	private String key   = null;
	
	private SecureRandom random = null;
	
	private Class<?> clazz;

	public ArrayList() {

		this.client = new Jedis("localhost", 6379);
		
		random   = new SecureRandom();
		this.key = new BigInteger(130, this.random).toString(32).substring(15);
		
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

	public boolean add(E e) {
		detectType(e);
		return this.client.rpush(this.key, e.toString()) > 0L;
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

	public boolean addAll(int index, Collection<? extends E> c) {
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

	public E get(int index) {
		
		List<String> results = this.client.lrange(this.key, index, index);
		String result = null;
		if(results.size() != 1) {
			return null;
		} else {
			result = results.get(0);
		}
		
		if (this.clazz == Integer.class){
			return castTo(Integer.parseInt(result));
		} else if(this.clazz == Long.class) {
			return castTo(Long.parseLong(result));
		} else {
			return castTo(result);
		}
		
	}

	public E set(int index, E element) {
		// TODO Auto-generated method stub
		return null;
	}

	public void add(int index, E element) {
		// TODO Auto-generated method stub
		
	}

	public E remove(int index) {
		// TODO Auto-generated method stub
		return null;
	}

	public int indexOf(Object o) {
		// TODO Auto-generated method stub
		return 0;
	}

	public int lastIndexOf(Object o) {
		// TODO Auto-generated method stub
		return 0;
	}

	public ListIterator<E> listIterator() {
		// TODO Auto-generated method stub
		return null;
	}

	public ListIterator<E> listIterator(int index) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<E> subList(int fromIndex, int toIndex) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	// Custom definitions
	
	@SuppressWarnings("unchecked")
	public E castTo(final Object obj) {
		// Only call after checking type!!!
		return (E) obj;
	}
	
	private void detectType(E e) {
		if(e instanceof Integer){
			this.clazz = Integer.class;
		} else if(e instanceof Long){
			this.clazz = Long.class;
		} else {
			this.clazz = String.class;
		}
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
