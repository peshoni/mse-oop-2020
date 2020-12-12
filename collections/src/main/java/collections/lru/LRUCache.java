package collections.lru;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * LRU cache
 * 
 * @author Petar Ivanov - petarivanovgap@gmail.com/pesho02@abv.bg
 *
 * @param <K>
 * @param <V>
 */
public class LRUCache<K, V> implements Map<K, V> {
	private int size;
	private Map<K, V> cache;
	private Queue<K> hits;

	public LRUCache(int size) {
		this.size = size;
		cache = new LinkedHashMap<K, V>();
		hits = new LinkedBlockingQueue<K>(size);
	}

	@Override
	public V put(K key, V value) {
		if (size <= cache.size()) {
			K keyToRemove = hits.poll();
			cache.remove(keyToRemove);
		}
		hits.add(key);
		return this.cache.put(key, value);
	}

	@Override
	public V get(Object key) {
		if (hits.remove(key) == false) {
			return null;
		}
		K keyToAdd = (K) key;
		hits.add(keyToAdd);
		return cache.get(key);
	}

	@Override
	public int size() {
		return cache.size();
	}

	@Override
	public boolean isEmpty() {
		return cache.isEmpty();
	}

	@Override
	public boolean containsKey(Object key) {
		throw new UnsupportedOperationException("Not implemented");
	}

	@Override
	public boolean containsValue(Object value) {
		throw new UnsupportedOperationException("Not implemented");
	}

	@Override
	public V remove(Object key) {
		hits.remove(key);
		return cache.remove(key);
	}

	@Override
	public void putAll(Map<? extends K, ? extends V> m) {
		throw new UnsupportedOperationException("Not implemented");
	}

	@Override
	public void clear() {
		throw new UnsupportedOperationException("Not implemented");
	}

	@Override
	public Set<K> keySet() {
		throw new UnsupportedOperationException("Not implemented");
	}

	@Override
	public Collection<V> values() {
		throw new UnsupportedOperationException("Not implemented");
	}

	@Override
	public Set<Entry<K, V>> entrySet() {
		throw new UnsupportedOperationException("Not implemented");
	}

}
