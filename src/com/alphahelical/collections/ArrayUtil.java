/**
 * 
 */
package com.alphahelical.collections;

import java.util.Arrays;

/**
 * @author kbeckman
 *
 */
public class ArrayUtil {

	private ArrayUtil() {}
	
	public static <T> T[] shiftOff(T[] a) {
		return (a.length == 0 ? a : Arrays.copyOfRange(a, 1, a.length));
	}

	
}
