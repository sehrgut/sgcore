package com.alphahelical.collections;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Collections {
	private Collections () { }
	
	public static <K, V> Map<K, V> mapLists(List<K> keys, List<V> values) {
		if (keys.size() != values.size())
			throw new IndexOutOfBoundsException("Must provide same number of keys and values.");
		
		Map<K, V> out = new HashMap<K, V>(keys.size());
		for(int i = 0, n = keys.size(); i < n; i++)
			out.put(keys.get(i), values.get(i));

		return out;
	}
	
	public static <T> Collection<T> pick(Collection<T> coll, Integer size) {
		@SuppressWarnings("unchecked")
		List<T> items = (List<T>) Arrays.asList(coll.toArray());
		java.util.Collections.shuffle(items);
		items = items.subList(0, size - 1);
		return items;
	}
	
}
