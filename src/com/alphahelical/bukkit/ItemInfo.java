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
		return (this.item.getItemMeta() instanceof Repairable ? ((Repairable)this.item.getItemMeta()).getRepairCost() : 0);
	}

	public boolean hasRepairPenalty() {
		return (this.item.getItemMeta() instanceof Repairable ? ((Repairable)this.item.getItemMeta()).hasRepairCost() : false);
	}
	
	public boolean isRepairable() {
		return (this.item.getItemMeta() instanceof Repairable);
	}

	public void setRepairPenalty(int penalty) {
		if(this.item.getItemMeta() instanceof Repairable) {
			Repairable meta = (Repairable) this.item.getItemMeta();
			meta.setRepairCost(penalty);
			this.item.setItemMeta((ItemMeta) meta);
		}
	}

	public boolean hasBookMeta() {
		return this.item.hasItemMeta() && this.item.getItemMeta() instanceof BookMeta;
	}

	public boolean isWriteableBook() {
		return this.item.getType().equals(Material.BOOK_AND_QUILL);
	}
	
}
