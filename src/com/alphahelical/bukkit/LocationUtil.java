/**
 * Utility methods for working with Bukkit Locations.
 * 
 */
package com.alphahelical.bukkit;

import org.bukkit.Location;

/**
 * @author kbeckman
 *
 */
public class LocationUtil {
	private LocationUtil () {}
	
	public static boolean blockEquals(Location a, Location b) {
		return a.getBlock() == b.getBlock();
	}
	
}
