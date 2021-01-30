/**
 * 
 */
package com.mse.oop.collections.lru;

import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author Petar Ivanov - petarivanovgap@gmail.com/pesho02@abv.bg
 *
 */
public class LRURunner {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Set<Integer> a = new HashSet<Integer>();
		a.add(1);
		a.add(2);
		a.add(3);

		Set<Integer> b = new HashSet<Integer>();
		b.add(2);
		b.add(3);
		b.add(4);

		a.removeAll(b);
		System.out.println(a);
		// a.

		Map<Integer, String> aMap = new LinkedHashMap<Integer, String>();
		aMap.put(10, "a");
		aMap.put(-2, "b");
		aMap.put(3, "c");
		aMap.put(0, "d");
		aMap.entrySet().stream().map(e -> e.getValue()).forEach(System.out::println);

		List<String> li = aMap.entrySet().stream().map(e -> e.getValue()).collect(Collectors.toList());
		aMap.forEach((k, v) -> System.out.println(v));

		Map<Integer, Integer> abMap = new LinkedHashMap<Integer, Integer>();
		abMap.put(10, 1);
		abMap.put(-2, 2);
		abMap.put(3, 3);
		abMap.put(0, 4);

		Integer reduce = abMap.entrySet().stream().map(e -> e.getValue()).reduce(0,
				(currentSum, currentElement) -> (currentSum + currentElement));

		System.out.println(reduce);

	}

}
