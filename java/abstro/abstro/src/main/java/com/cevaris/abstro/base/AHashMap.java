package com.cevaris.abstro.base;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Logger;

import com.cevaris.abstro.Utils;

import redis.clients.jedis.Jedis;

public class AHashMap<K, V> implements Map<K, V>, Serializable{

	private static final long serialVersionUID = -5300531321920414364L;
	
	private final static Logger LOG = Logger.getLogger(AHashMap.class.getName()); 
	
	private Jedis client = null;
	private String key   = null;
	
	private Class<?> clazz;
	
	public AHashMap() {
		this.client = new Jedis("localhost", 6379);
		this.key = Utils.slug();
		
		LOG.info(String.format("Created object with key: %s", this.key));
	}


	public int size() {
		// TODO Auto-generated method stub
		return 0;
	}

	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean containsKey(Object key) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean containsValue(Object value) {
		// TODO Auto-generated method stub
		return false;
	}

	public V get(Object key) {
		String val = this.client.hget(this.key, Utils.encode(key));
		return castTo(Utils.decode(val));
	}

	public V put(K hKey, V value) {
		String enKey = Utils.encode(hKey);
		String enVal = Utils.encode(value);
		if (this.client.hset(this.key, enKey, enVal) > 0L){
			return value;
		} else {
			return null;
		}
	}

	public V remove(Object key) {
		// TODO Auto-generated method stub
		return null;
	}

	public void putAll(Map<? extends K, ? extends V> m) {
		// TODO Auto-generated method stub
		
	}

	public void clear() {
		// TODO Auto-generated method stub
		
	}

	public Set<K> keySet() {
		// TODO Auto-generated method stub
		return null;
	}

	public Collection<V> values() {
		// TODO Auto-generated method stub
		return null;
	}

	public Set<java.util.Map.Entry<K, V>> entrySet() {
		// TODO Auto-generated method stub
		return null;
	}
	
	// Custom methods
	
	@SuppressWarnings("unchecked")
	public V castTo(final Object obj) {
		// Only call after checking type!!!
		return (V) obj;
	}

//	private void detectType(E e) {
//		if(e instanceof Integer){
//			this.clazz = Integer.class;
//		} else if(e instanceof Long){
//			this.clazz = Long.class;
//		} else {
//			this.clazz = String.class;
//		}
//	}
	
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
