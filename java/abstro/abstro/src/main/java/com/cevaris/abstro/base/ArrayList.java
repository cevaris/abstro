package com.cevaris.abstro.base;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.logging.Logger;

import com.cevaris.abstro.Utils;

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
		List<String> results = this.client.lrange(this.key, 0, -1);
		Object os[] = new Object[size()]; 

		int i = 0;
		for(String item : results){
//			if (this.clazz == Integer.class){
//				os[i] = castTo(Integer.parseInt(item));
//			} else if(this.clazz == Long.class) {
//				os[i] = castTo(Long.parseLong(item));
//			} else {
//				os[i] = castTo(item);
//			}
			os[i] = Utils.decode(item, this.clazz);
			i++;
		}
		return os;
	}

	@SuppressWarnings("unchecked")
	public <T> T[] toArray(T[] a) {
		List<String> results = this.client.lrange(this.key, 0, -1);

		int i = 0;
		for(String item : results){
//			if (this.clazz == Integer.class){
//				a[i] = (T) castTo(Integer.parseInt(item));
//			} else if(this.clazz == Long.class) {
//				a[i] = (T) castTo(Long.parseLong(item));
//			} else {
//				a[i] = (T) castTo(item);
//			}
			a[i] = (T) Utils.decode(item, this.clazz);
			i++;
		}
		return a;
	}

	public boolean add(E e) {
		detectType(e);
		return this.client.rpush(this.key, Utils.encode(e)) > 0L;
	}

	public boolean remove(Object o) {
//		String obj = o.toString();
		return this.client.lrem(this.key, 1, Utils.encode(o)) > 0L;
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

	public boolean addAll(int index, Collection<? extends E> c) {
		throw new UnsupportedOperationException("Not Implmented");
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

	public E get(int index) {
		
		List<String> results = this.client.lrange(this.key, index, index);
		String result = null;
		if(results.size() != 1) {
			return null;
		} else {
			result = results.get(0);
		}
		return castTo(Utils.decode(result, this.clazz));
		
//		if (this.clazz == Integer.class){
//			return castTo(Integer.parseInt(result));
//		} else if(this.clazz == Long.class) {
//			return castTo(Long.parseLong(result));
//		} else {
//			return castTo(result);
//		}
		
	}

	public E set(int index, E element) {
		detectType(element);
		if (this.client.lset(this.key, (long)index, Utils.encode(element)) != null){
			return element;			
		} else {
			return null;
		}
	}

	public void add(int index, E element) {
		set(index, element);
	}

	public E remove(int index) {
		throw new UnsupportedOperationException("Not Implmented");
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
