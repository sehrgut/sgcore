/**
 * 
 */
package com.alphahelical.bukkit.enity.itemframe.behavior;

import org.bukkit.Material;
import org.bukkit.entity.ItemFrame;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BookMeta;

import com.alphahelical.util.StringUtil;

/**
 * @author Keith Beckman
 *
 */
public class ChatBook implements IItemFrameBehavior {
// TODO: Test book chat
// TODO: should book chat be inside ReadBook, as a subbehaviour?
// TODO: should this be parameterized for timing, max length, etc?
	private boolean readUnsignedBooks;
	private String emptyBookMessage;
	
	public ChatBook(Boolean readUnsignedBooks, String emptyBookMessage) {
		this.readUnsignedBooks = readUnsignedBooks;
		this.emptyBookMessage = emptyBookMessage;
	}

	/* (non-Javadoc)
	 * @see com.alphahelical.bukkit.enity.itemframe.behavior.IItemFrameBehavior#doBehaviour(org.bukkit.entity.Player, org.bukkit.entity.ItemFrame, org.bukkit.event.Cancellable)
	 */
	@Override
	public boolean doBehaviour(Player p, ItemFrame f, Cancellable e) {
		e.setCancelled(true);
		
		ItemStack item = f.getItem();
		if (item.getType().equals(Material.WRITTEN_BOOK) ||
				(this.readUnsignedBooks && item.getType().equals(Material.BOOK_AND_QUILL))) {
			
			BookMeta book = item.hasItemMeta() ? ((BookMeta) item.getItemMeta()) : null;

			StringBuilder msg = new StringBuilder();
			
			if (book == null && this.emptyBookMessage != null) {
				msg.append(this.emptyBookMessage);
			} else { // TODO: Better handling of null authors and titles in writable books
				String title = String.format("# %s #\n", book.getTitle());
				String sep = StringUtil.repeat("-", (title.length() - 1));
				
				msg.append(String.format("%s\nby %s\n", title, book.getAuthor()));
				
				for(String page : book.getPages())
					msg.append(String.format("%s\n%s\n", sep, page));
				
				msg.append(String.format("%s\n", sep));
			}
			
			p.sendMessage(msg.toString());
			
			return true;
		}
		return false;
	}

}
