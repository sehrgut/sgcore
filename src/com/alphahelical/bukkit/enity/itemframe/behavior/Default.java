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
public class Default implements IItemFrameBehavior {

	/* (non-Javadoc)
	 * @see com.alphahelical.bukkit.labelframes.IItemFrameInteraction#doBehaviour(org.bukkit.entity.Player, org.bukkit.entity.ItemFrame, org.bukkit.event.Cancellable)
	 */
	@Override
	public boolean doBehaviour(Player p, ItemFrame f, Cancellable e) {
		return true;
	}

}
