/**
 * 
 */
package com.alphahelical.bukkit.sgcore;

import org.bukkit.entity.Player;
import com.alphahelical.bukkit.SGPlugin;
import com.alphahelical.bukkit.enity.itemframe.ItemFrameListener;

/**
 * @author Keith Beckman
 *
 */
public final class SGCore extends SGPlugin {

	private SGCoreCommandExecutor cmdex;
	
	public static boolean isPlayer(Object sender) {
		return sender instanceof Player;
	}

	@Override
	protected void onEnabled() {
		cmdex = new SGCoreCommandExecutor(this);
		getCommand("ping").setExecutor(cmdex);
		getCommand("anvil").setExecutor(cmdex);
		this.getServer().getPluginManager().registerEvents(new ItemFrameListener(), this);
	}
	
	@Override
	protected void onDisabled() {
	}
		
	@Override
	protected void onEnabling() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void onDisabling() {
		// TODO Auto-generated method stub
		
	}
	

}
