package collections.map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.Test;

import collections.lru.LRUCache;
import collections.lru.Person;

public class CacheTest {

	@Test
	public void testCacheInsertion() {
		Person personA = new Person("Ivan", 185, 80);
		Person personB = new Person("Dragan", 175, 70);
		Person personC = new Person("Petkan", 190, 80);

		LRUCache<String, Person> cache = new LRUCache<String, Person>(2);
		cache.put(personA.getName(), personA);
		cache.put(personB.getName(), personB);
		cache.put(personC.getName(), personC);

		Person actual = cache.get(personB.getName());
		assertEquals(personB, actual);

		actual = cache.get(personC.getName());
		assertEquals(personC, actual);
	}

	@Test
	public void testCacheGet() {
		Person personA = new Person("Ivan", 185, 80);
		Person personB = new Person("Dragan", 175, 70);
		Person personC = new Person("Petkan", 190, 80);

		LRUCache<String, Person> cache = new LRUCache<String, Person>(2);
		cache.put(personA.getName(), personA);
		cache.put(personB.getName(), personB);
		cache.put(personC.getName(), personC);

		assertNull(cache.get(personA));

		Person actual = cache.get(personB.getName());
		assertEquals(personB, actual);

		actual = cache.get(personC.getName());
		assertEquals(personC, actual);

		Person personD = new Person("Override", 190, 80);
		cache.put(personD.getName(), personD);

		assertNull(cache.get(personC));
	}
}
