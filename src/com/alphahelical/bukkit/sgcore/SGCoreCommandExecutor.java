/**
 * 
 */
package com.alphahelical.bukkit.sgcore;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;


/**
 * @author Keith Beckman
 *
 */
public class SGCoreCommandExecutor implements CommandExecutor {

	private enum Commands {
		PING;
		
		public static Commands find(String test) {
			for (Commands c : Commands.values())
				if (c.name().equalsIgnoreCase(test))
					return c;
			return null;
		}
		
	}
	
	@SuppressWarnings("unused")
	private SGCore plugin;
	
	public SGCoreCommandExecutor(SGCore plugin) {
		this.plugin = plugin;
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		Commands cmdname = Commands.find(cmd.getName());

		switch(cmdname) {
			case PING:
				return doPing(sender, label, args);
		}

		return false;
	}

	private boolean doPing(CommandSender sender, String label, String[] args) {
		sender.sendMessage("PONG!");
		return true;		
	}
	
}
