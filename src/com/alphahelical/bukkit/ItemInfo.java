/**
 * 
 */
package com.alphahelical.bukkit;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BookMeta;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.Repairable;

/**
 * @author Keith Beckman
 *
 */
public class ItemInfo {

	private ItemStack item;
	
	public ItemInfo(ItemStack item) {
		this.item = item;
	}
	
	public String getName() {
		ItemMeta meta = this.item.hasItemMeta() ? this.item.getItemMeta() : null;
		return (meta != null && meta.hasDisplayName() ? meta.getDisplayName() : null); 
	}
	
	public int getRepairPenalty() {
		return (this.item instanceof Repairable ? ((Repairable)this.item).getRepairCost() : 0);
	}

	public boolean hasRepairPenalty() {
		return (this.item instanceof Repairable ? ((Repairable)this.item).hasRepairCost() : false);
	}
	
	public boolean isRepairable() {
		return (this.item instanceof Repairable);
	}

	public void setRepairPenalty(int penalty) {
		if(this.item instanceof Repairable)
			((Repairable) this.item).setRepairCost(penalty);
	}

	public boolean hasBookMeta() {
		return this.item.hasItemMeta() && this.item.getItemMeta() instanceof BookMeta;
	}

	public boolean isWriteableBook() {
		return this.item.getType().equals(Material.BOOK_AND_QUILL);
	}
	
}
