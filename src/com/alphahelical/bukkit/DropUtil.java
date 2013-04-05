/**
 * 
 */
package com.alphahelical.bukkit;

import java.util.HashMap;

import org.bukkit.Location;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

/**
 * @author kbeckman
 *
 */
public class DropUtil {

	public enum DropModes {
		PLACE, SCATTER;
	}
	
	private DropUtil() {}
	
	public static void drop(Location l, DropModes mode, ItemStack...items) {
		for(ItemStack item : items)
			switch(mode) {
				case PLACE:
					l.getWorld().dropItem(l, item);
				case SCATTER:
					l.getWorld().dropItemNaturally(l, item);
			}		
	}
	
	public static void addToInventoryOrDrop(Inventory i, Location l, DropModes mode, ItemStack... items) {
		HashMap<Integer, ItemStack> left = i.addItem(items);
		if (left.size() > 0) {
			ItemStack[] a = new ItemStack[left.size()];
			drop(l, mode, left.values().toArray(a));
		}
	}

	
	
}
