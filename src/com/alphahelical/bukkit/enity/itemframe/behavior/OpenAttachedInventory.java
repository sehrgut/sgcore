/**
 * 
 */
package com.alphahelical.bukkit.enity.itemframe.behavior;

import org.bukkit.Bukkit;
import org.bukkit.entity.ItemFrame;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;

import com.alphahelical.bukkit.enity.itemframe.ItemFrameUtil;

/**
 * @author kbeckman
 *
 */
public class OpenAttachedInventory implements IItemFrameBehavior {
//TODO: ender chests. can open, just need to find how to animate
// https://github.com/rutgerkok/BetterEnderChest/blob/master/src/nl/rutgerkok/betterenderchest/nms/NMSHandler_1_5_R2.java
// TODO: need native call wrapper
	/* (non-Javadoc)
	 * @see com.alphahelical.bukkit.labelframes.IItemFrameInteraction#doBehaviour(org.bukkit.entity.Player, org.bukkit.entity.ItemFrame)
	 */
	@Override
	public boolean doBehaviour(Player p, ItemFrame f, Cancellable e) {
		if(ItemFrameUtil.isLabel(f)) {
			e.setCancelled(true);

			PlayerInteractEvent pe = new PlayerInteractEvent(p,
					Action.RIGHT_CLICK_BLOCK,
					p.getItemInHand(),
					ItemFrameUtil.attachedBlock(f),
					f.getAttachedFace().getOppositeFace());
			Bukkit.getServer().getPluginManager().callEvent(pe);
			
			if(! pe.isCancelled()) {
				// get the wrapper InventoryHolder in case of double-chests
				// TODO: configure open only halves of chests
				Inventory i = ((InventoryHolder)pe.getClickedBlock().getState()).getInventory().getHolder().getInventory();
				pe.getPlayer().openInventory(i);
			}
			
			return true;
		}
		return false;
	}

}
