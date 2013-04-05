/**
 * 
 */
package com.alphahelical.util;

/**
 * @author kbeckman
 *
 */
public class EnumUtil {
	private EnumUtil () {}
	
	public static <T extends Enum<T>> T find(Class<T> e, String val, T default_value) {
		for (T c : e.getEnumConstants())
			if (c.name().equalsIgnoreCase(val)) return c;
		return default_value;
	}

}
