/**
 * 
 */
package com.alphahelical.bukkit.enity.itemframe;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Hanging;
import org.bukkit.entity.ItemFrame;
import org.bukkit.inventory.InventoryHolder;

/**
 * @author kbeckman
 *
 */
public class ItemFrameUtil {
	private ItemFrameUtil() {}
	
	public static Block attachedBlock(Hanging f) {
		return f.getLocation().getBlock().getRelative(f.getAttachedFace());
	}
	
	public static boolean isLabel(Hanging f) {
		return (attachedBlock(f).getState() instanceof InventoryHolder); 
	}
	
	public static boolean isEmpty(ItemFrame f) {
		return f.getItem().getType().equals(Material.AIR);
	}
}
