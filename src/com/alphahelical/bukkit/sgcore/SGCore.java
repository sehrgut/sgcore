/**
 * 
 */
package com.alphahelical.bukkit.sgcore;

import org.bukkit.entity.Player;
import com.alphahelical.bukkit.SGPlugin;

/**
 * @author Keith Beckman
 *
 */
public final class SGCore extends SGPlugin {

	private SGCoreCommandExecutor cmdex;
	
	@Override
	public void onEnable() {
		cmdex = new SGCoreCommandExecutor(this);
		getCommand("ping").setExecutor(cmdex);
		
	}
	
	@Override
	public void onDisable() {
	}
		
	public static boolean isPlayer(Object sender) {
		return sender instanceof Player;
	}
	

}
