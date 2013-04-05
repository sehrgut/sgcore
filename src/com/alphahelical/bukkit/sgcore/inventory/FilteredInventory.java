/**
 * 
 */
package com.alphahelical.bukkit.sgcore.inventory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.ListIterator;
import java.util.Set;

import org.bukkit.Material;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Item;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;

/**
 * @author kbeckman
 *
 */
@SuppressWarnings("unused")
public class FilteredInventory extends WrappedInventory {

	public enum Modes {
		WHITELIST, BLACKLIST;
	}
	
	// Property: Filter
	private Set<ItemStack> filter;
	private void setFilter(Set<ItemStack> filter) {
		this.filter = filter;
	}
	public Set<ItemStack> getFilter() {
		return this.filter;
	}
	
	// Property: Mode
	private Modes mode;
	public void setMode(Modes mode) {
		this.mode = mode;
	}
	public Modes getMode() {
		return this.mode;
	}
	
	
	// Constructors

	public FilteredInventory(Inventory innerInventory, Modes mode) {
		this(innerInventory, mode, new HashSet<ItemStack>());
	}
	
	public FilteredInventory(Inventory innerInventory, Modes mode, ItemStack[] filter) {
		this(innerInventory, mode, new HashSet<ItemStack>(Arrays.asList(filter)));
	}
	
	public FilteredInventory(Inventory innerInventory, Modes mode, Set<ItemStack> filter) {
		super(innerInventory);
		this.setFilter(filter);
	}
	
	public boolean canPlace(ItemStack test) {
		for(ItemStack standard : this.getFilter()) {
			boolean match = ItemStackComparer.compare(standard, test);

			switch(this.getMode()) {
				case WHITELIST:
					if (!match) return false;
				case BLACKLIST:
					if (match) return false;
			}
		}
		
		return true;
	}
	
	private void checkItems(ItemStack... items) throws IllegalArgumentException {
		ArrayList<ItemStack> illegal = new ArrayList<ItemStack>();

		for(ItemStack i : items)
			if(!(this.canPlace(i)))
				illegal.add(i);
		
		if(!illegal.isEmpty()) {
			StringBuilder msg = new StringBuilder();
			
			msg.append(String.format("FilteredInventory rejected the following %s:\n", (illegal.size() > 1 ? "items" : "item") ));
			
			for(ItemStack i : illegal) {
				msg.append(String.format("\t%s\n", i.toString()));
			}

			throw new IllegalArgumentException(msg.toString());
		}	
	}
	
	public HashMap<Integer, ItemStack> addItem(ItemStack... items) throws IllegalArgumentException {
		this.checkItems(items);
		return super.addItem(items);
	}

	public void setContents(ItemStack[] items) throws IllegalArgumentException {
		this.checkItems(items);
		super.setContents(items);
	}

	public void setItem(int index, ItemStack item) throws IllegalArgumentException {
		this.checkItems(item);
		super.setItem(index, item);
	}

}
