/**
 * 
 */
package com.alphahelical.util;

/**
 * @author Keith Beckman
 *
 */
public class StringUtil {
	private StringUtil () {}
	
	public static String repeat(String in, int n) {
		StringBuilder out = new StringBuilder();
		for(int i=0; i<n; i++)
			out.append(in);
		return out.toString();
	}
}
