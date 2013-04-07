/**
 * 
 */
package com.alphahelical.bukkit.enity.itemframe.behavior;

import org.bukkit.entity.ItemFrame;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;

/**
 * @author kbeckman
 *
 */
public interface IItemFrameBehavior {
	public boolean doBehaviour(Player p, ItemFrame f, Cancellable e);
}
