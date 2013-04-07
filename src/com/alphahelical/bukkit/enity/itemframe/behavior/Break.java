/**
 * 
 */
package com.alphahelical.bukkit.enity.itemframe.behavior;

import org.apache.commons.lang.NotImplementedException;
import org.bukkit.entity.ItemFrame;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;

/**
 * @author Keith Beckman
 *
 */
public class Break implements IItemFrameBehavior {
	
	/* (non-Javadoc)
	 * @see com.alphahelical.bukkit.enity.itemframe.behavior.IItemFrameBehavior#doBehaviour(org.bukkit.entity.Player, org.bukkit.entity.ItemFrame, org.bukkit.event.Cancellable)
	 */
	@Override
	public boolean doBehaviour(Player p, ItemFrame f, Cancellable e) {
		// TODO: API to manually break ItemFrame
		/* ideas:
		 * force change facing to an unsupported block if possible
		 * remove and manually drop
		 * set block type
		 * 
		 */
		throw new NotImplementedException();
	}

}
