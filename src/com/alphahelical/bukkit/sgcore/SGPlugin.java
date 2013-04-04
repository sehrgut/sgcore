/**
 * 
 */
package com.alphahelical.bukkit.sgcore;

import org.bukkit.plugin.java.JavaPlugin;

/**
 * @author kbeckman
 *
 */
public abstract class SGPlugin extends JavaPlugin {

	/* (non-Javadoc)
	 * @see org.bukkit.plugin.java.JavaPlugin#onEnable()
	 */
	@Override
	public abstract void onEnable();
	
	/* (non-Javadoc)
	 * @see org.bukkit.plugin.java.JavaPlugin#onDisable()
	 */
	@Override
	public abstract void onDisable();
}
