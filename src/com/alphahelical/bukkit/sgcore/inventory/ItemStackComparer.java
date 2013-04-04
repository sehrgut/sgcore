/**
 * 
 */
package com.alphahelical.bukkit.sgcore.inventory;

import org.bukkit.inventory.ItemStack;

/**
 * @author kbeckman
 *
 */
public class ItemStackComparer {

	private ItemStackComparer() {}
	
	private static boolean compareType(ItemStack standard, ItemStack test) {
		return standard.getType().equals(test.getType());
	}

	public static boolean compare(ItemStack key, ItemStack test) {
		return compareType(key, test);
	}

}
