package com.alphahelical.collections;

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
	
	
}
