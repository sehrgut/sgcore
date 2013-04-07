/**
 * 
 */
package com.alphahelical.bukkit;

import java.util.logging.Level;

import org.bukkit.event.Listener;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * @author kbeckman
 *
 */
public abstract class SGPlugin extends JavaPlugin {

	private Level loglevel = null;
	private Level defaultLoglevel = Level.SEVERE;
	
	protected Level getDefaultLoglevel() {
		return this.defaultLoglevel;
	}
	
	protected void setDefaultLoglevel(Level level) {
		this.defaultLoglevel = level;
	}
	
	public Level getLogLevel() {
		if (this.loglevel == null) {
			String level = this.getConfig().getString("loglevel", this.defaultLoglevel.getName());
			level = (level.equalsIgnoreCase("false") ? "off" : level);
			try {
				this.loglevel = Level.parse(level.toUpperCase());				
			} catch(IllegalArgumentException ex) {
				this.loglevel = this.defaultLoglevel;
			}
		}
		
		this.getLogger().info(String.format("Logging at %s", this.loglevel.getName()));
		
		return this.loglevel;
	}
	
	/* (non-Javadoc)
	 * @see org.bukkit.plugin.java.JavaPlugin#onEnable()
	 */
	@Override
	public final void onEnable() {
		this.onEnabling();
		this.initializeConfig();
		this.initializeLogger();
		this.onEnabled();
	}
	
	/**
	 * Called within {@link JavaPlugin#onEnable()}
	 */
	protected abstract void onEnabling();
	protected abstract void onEnabled();
	
	
	/* (non-Javadoc)
	 * @see org.bukkit.plugin.java.JavaPlugin#onDisable()
	 */
	@Override
	public final void onDisable() {
		this.onDisabling();
		this.onDisabled();
	}
	
	/**
	 * Called within {@link JavaPlugin#onDisable()}
	 */
	protected abstract void onDisabling();
	protected abstract void onDisabled();
	
	private Boolean hasDefaultConfig;
	public boolean getHasDefaultConfig() {
		if(this.hasDefaultConfig == null)
			this.hasDefaultConfig = ((this.getResource("config.yml")) != null);
		return this.hasDefaultConfig;
	}
	
	// TODO: robust config reload handler, and a CommandExecutor that can be used by plugins to implement it
	private void initializeConfig() {

		if(this.getHasDefaultConfig())
			this.saveDefaultConfig();

		this.getConfig().options().copyDefaults(true);
		
		this.saveConfig();
	}
	
	private void initializeLogger() {
		this.getLogger().setLevel(this.getLogLevel());
	}

	protected void registerListeners(Listener... listeners) {
		PluginManager pm = this.getServer().getPluginManager();
		
		for(Listener listener : listeners)
			pm.registerEvents(listener, this);
	}
	
}
