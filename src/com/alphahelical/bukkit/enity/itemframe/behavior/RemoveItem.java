package com.alphahelical.bukkit.enity.itemframe.behavior;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Rotation;
import org.bukkit.entity.ItemFrame;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import com.alphahelical.bukkit.DropUtil;
import com.alphahelical.bukkit.DropUtil.DropModes;

public class RemoveItem implements IItemFrameBehavior {

	private boolean placeInInventory;
	private boolean dropOnFailure;
	private DropModes dropMode;
	
	public RemoveItem(boolean placeInInventory, boolean dropOnFailure, DropModes dropMode) {
		this.placeInInventory = placeInInventory;
		this.dropMode = dropMode;
		this.dropOnFailure = dropOnFailure;
	}
	
	@Override
	public boolean doBehaviour(Player p, ItemFrame f, Cancellable e) {
		e.setCancelled(true);
		// TODO: config item-removal.tool defaults to AIR, but can be ANY
		ItemStack item = f.getItem();
		Inventory inv = p.getInventory();
		Location loc = f.getLocation();
		
		if(! (item.getType() == Material.AIR)) {
			if(this.placeInInventory) {
				if(this.dropOnFailure) {
					DropUtil.addToInventoryOrDrop(inv, loc, this.dropMode, item);
				} else {
					if(inv.addItem(item).values().size() == 0) return false;
				}
			} else {
				DropUtil.drop(loc, this.dropMode, item);
			}
			
			f.setItem(new ItemStack(Material.AIR, 1));
			f.setRotation(Rotation.NONE);

			return true;
		}
		
		return false;
	}
	
}
