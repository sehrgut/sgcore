/**
 * 
 */
package com.alphahelical.bukkit.enity.itemframe.behavior;

import org.apache.commons.lang.NotImplementedException;
import org.bukkit.Material;
import org.bukkit.entity.ItemFrame;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BookMeta;

/**
 * @author Keith Beckman
 *
 */
public class ReadBook implements IItemFrameBehavior {

	private boolean readUnsignedBooks;
	
	public ReadBook(Boolean readUnsignedBooks) {
		this.readUnsignedBooks = readUnsignedBooks;
	}
	
	/* (non-Javadoc)
	 * @see com.alphahelical.bukkit.enity.itemframe.behavior.IItemFrameBehavior#doBehaviour(org.bukkit.entity.Player, org.bukkit.entity.ItemFrame, org.bukkit.event.Cancellable)
	 */
	@Override
	public boolean doBehaviour(Player p, ItemFrame f, Cancellable e) {
		ItemStack item = f.getItem();
		if (item.getType().equals(Material.WRITTEN_BOOK) ||
				(this.readUnsignedBooks && item.getType().equals(Material.BOOK_AND_QUILL))) {
			
			@SuppressWarnings("unused")
			BookMeta book = item.hasItemMeta() ? ((BookMeta) item.getItemMeta()) : null;

			// TODO: API to trigger client-side book display open.
			throw new NotImplementedException();
			
		}
		return false;
	}

}
