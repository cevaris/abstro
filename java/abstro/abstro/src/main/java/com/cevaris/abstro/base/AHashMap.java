package com.cevaris.abstro.base;

import java.io.Serializable;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.logging.Logger;

import com.cevaris.abstro.Utils;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.ScanParams;
import redis.clients.jedis.ScanResult;

public class AHashMap<K, V> implements Map<K, V>, Serializable{

	private static final long serialVersionUID = -5300531321920414364L;
	
	private final static Logger LOG = Logger.getLogger(AHashMap.class.getName()); 
	
	private Jedis client = null;
	private String key   = null;
	
	public AHashMap() {
		this.client = new Jedis("localhost", 6379);
		this.key = Utils.slug();
		
		LOG.info(String.format("Created object with key: %s", this.key));
	}


	public int size() {
		return this.client.hlen(this.key).intValue();
	}

	public boolean isEmpty() {
		return this.client.hlen(this.key) == 0L;
	}

	public boolean containsKey(Object hKey) {
		return this.client.hexists(this.key, Utils.encode(hKey));
	}

	public boolean containsValue(Object value) {
		ScanParams params = new ScanParams();
        params.match(Utils.encode(value));
        boolean scanningDone = false;
        String start = ScanParams.SCAN_POINTER_START;
        while (!scanningDone) {
        	System.out.println(Utils.encode(value));
        	System.out.println(this.client.hvals(this.key));
            ScanResult<Entry<String, String>> scanResults = this.client.hscan(this.key, start, params);
            System.out.println(scanResults.getResult().size());
            for (Entry<String, String> scanResult : scanResults.getResult()) {
            	System.out.println(value + " " + Utils.decode(scanResult.getValue()));
            	if(Utils.decode(scanResult.getValue()) == value) return true;
            }
            start = scanResults.getStringCursor();
            if (start.equalsIgnoreCase("0")) {
                scanningDone = true;
            }
//            System.out.println(scanResults.getStringCursor());
        }
        return false;
	}

	public V get(Object key) {
		String val = this.client.hget(this.key, Utils.encode(key));
		if(val != null){
			return castToV(Utils.decode(val));
		} else {
			return null;
		}
		
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
		V val = get(key);
		if(this.client.hdel(this.key, Utils.encode(key)) > 0L){
			return val; 
		} else {
			return null;
		}
	}

	public void putAll(Map<? extends K, ? extends V> m) {
		// TODO Auto-generated method stub
		
	}

	public void clear() {
		destroy();
	}

	public Set<K> keySet() {
		Set<K> kSet = new HashSet<K>();
		
		for(String str : this.client.hkeys(this.key))
			kSet.add(castToK(Utils.decode(str)));
	
		return kSet;
	}

	public Collection<V> values() {
		Collection<V> vList = new ArrayList<V>();
		
		for(String str : this.client.hvals(this.key))
			vList.add(castToV(Utils.decode(str)));
		
		return vList;
	}

	public Set<java.util.Map.Entry<K, V>> entrySet() {
		Set<java.util.Map.Entry<K, V>> kvSet = new HashSet<java.util.Map.Entry<K,V>>();
		for(Entry<String,String> tmp : this.client.hgetAll(this.key).entrySet()) {
			kvSet.add(new AbstractMap.SimpleEntry<K, V>(
					castToK(Utils.decode(tmp.getKey())), 
					castToV(Utils.decode(tmp.getValue())) ));
		}
	    return kvSet;
	}
	
	// Custom methods
	
	@SuppressWarnings("unchecked")
	public V castToV(final Object obj) {
		// Only call after checking type!!!
		return (V) obj;
	}
	
	@SuppressWarnings("unchecked")
	public K castToK(final Object obj) {
		// Only call after checking type!!!
		return (K) obj;
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
