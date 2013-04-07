/**
 * 
 */
package com.alphahelical.bukkit.enity.itemframe;

import org.bukkit.Bukkit;
import org.bukkit.entity.ItemFrame;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.hanging.HangingBreakByEntityEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import static com.alphahelical.bukkit.enity.itemframe.ItemFrameActions.*;

/**
 * @author Keith Beckman
 *
 */
public class ItemFrameListener implements Listener {
// TODO: should this pass through cancelled events?
	@EventHandler
	public void onPlayerInteractEntity(PlayerInteractEntityEvent e) {
		if(! e.isCancelled() && e.getRightClicked() instanceof ItemFrame) {
			
			Player p = e.getPlayer();
			ItemFrame f = (ItemFrame) e.getRightClicked();
			ItemFrameActions a = (p.isSneaking() ? SNEAK_USE : USE);
			ItemFrameInteractEvent ev = new ItemFrameInteractEvent(p, f, a);
			Bukkit.getServer().getPluginManager().callEvent(ev);
			
			e.setCancelled(ev.isCancelled());
		}
	}

	@EventHandler
	public void onHangingBreakByEntity(HangingBreakByEntityEvent e) {
		if(! e.isCancelled() &&
				e.getRemover() instanceof Player && 
				e.getEntity() instanceof ItemFrame) {
			
			Player p = (Player) e.getRemover();
			ItemFrame f = (ItemFrame) e.getEntity();
			ItemFrameActions a = (p.isSneaking() ? SNEAK_HIT : HIT);
			ItemFrameInteractEvent ev = new ItemFrameInteractEvent(p, f, a);
			Bukkit.getServer().getPluginManager().callEvent(ev);
			
			e.setCancelled(ev.isCancelled());
		}
	}
	
}
