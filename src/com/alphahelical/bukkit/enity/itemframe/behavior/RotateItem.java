/**
 * 
 */
package com.alphahelical.bukkit.enity.itemframe.behavior;

import org.bukkit.Rotation;
import org.bukkit.entity.ItemFrame;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;

/**
 * @author Keith Beckman
 *
 */
public class RotateItem implements IItemFrameBehavior {

	private Rotation rotation;
	
	public RotateItem (Rotation rotation) {
		this.rotation = rotation;
	}
	
	/* (non-Javadoc)
	 * @see com.alphahelical.bukkit.enity.itemframe.behavior.IItemFrameBehavior#doBehaviour(org.bukkit.entity.Player, org.bukkit.entity.ItemFrame, org.bukkit.event.Cancellable)
	 */
	@Override
	public boolean doBehaviour(Player p, ItemFrame f, Cancellable e) {
		e.setCancelled(true);
		
		Rotation rot = f.getRotation();
		
		switch(this.rotation) {
			case FLIPPED:
				rot = rot.rotateClockwise();
				// fall through
			case CLOCKWISE:
				rot = rot.rotateClockwise();
				break;
			case COUNTER_CLOCKWISE:
				rot = rot.rotateCounterClockwise();
			case NONE:
		}

		f.setRotation(rot);
		
		return true;
	}

}
