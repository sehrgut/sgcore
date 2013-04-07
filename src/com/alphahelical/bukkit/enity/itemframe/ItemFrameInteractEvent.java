/**
 * 
 */
package com.alphahelical.bukkit.enity.itemframe;

import org.bukkit.entity.ItemFrame;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

/**
 * @author Keith Beckman
 *
 */
public class ItemFrameInteractEvent extends Event implements Cancellable {

	private Player player;
	private ItemFrame target;
	private ItemFrameActions action;
	
	/**
	 * @return the player
	 */
	public Player getPlayer() {
		return player;
	}

	/**
	 * @param player the player to set
	 */
	private void setPlayer(Player player) {
		this.player = player;
	}

	/**
	 * @return the target
	 */
	public ItemFrame getTarget() {
		return target;
	}

	/**
	 * @param target the target to set
	 */
	private void setTarget(ItemFrame target) {
		this.target = target;
	}

	/**
	 * @return the action
	 */
	public ItemFrameActions getAction() {
		return action;
	}

	/**
	 * @param action the action to set
	 */
	private void setAction(ItemFrameActions action) {
		this.action = action;
	}

	private boolean cancel;
	/* (non-Javadoc)
	 * @see org.bukkit.event.Cancellable#isCancelled()
	 */
	@Override
	public boolean isCancelled() {
		return this.cancel;
	}

	/* (non-Javadoc)
	 * @see org.bukkit.event.Cancellable#setCancelled(boolean)
	 */
	@Override
	public void setCancelled(boolean cancel) {
		this.cancel = cancel;
	}

	private static final HandlerList handlers = new HandlerList();
	
	/* (non-Javadoc)
	 * @see org.bukkit.event.Event#getHandlers()
	 */
	@Override
	public HandlerList getHandlers() {
		return handlers;
	}
	
	public static HandlerList getHandlerList() {
		return handlers;
	}

	public ItemFrameInteractEvent(Player player, ItemFrame target, ItemFrameActions action) {
		this.setPlayer(player);
		this.setTarget(target);
		this.setAction(action);
	}
	
}
