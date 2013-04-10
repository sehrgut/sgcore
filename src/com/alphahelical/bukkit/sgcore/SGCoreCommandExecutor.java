/**
 * 
 */
package com.alphahelical.bukkit.sgcore;

import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.Repairable;

import com.alphahelical.bukkit.MaterialInfo;
import com.alphahelical.bukkit.anvil.VirtualAnvil;


/**
 * @author Keith Beckman
 *
 */
public class SGCoreCommandExecutor implements CommandExecutor {

	private enum Commands {
		PING, ANVIL;
		
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
			case ANVIL:
				return doAnvil(sender, label, args);
		}

		return false;
	}

	private boolean doPing(CommandSender sender, String label, String[] args) {
		sender.sendMessage("PONG!");
		return true;		
	}
	
	private boolean doAnvil(CommandSender sender, String label, String[] args) {
		if(! (sender instanceof Player)) {
			sender.sendMessage("This command can only be executed by a player on their held item.");
			return false;
		} else {
			Player p = (Player) sender;
			
			if (args.length < 1) {
				sender.sendMessage("Please specify 'cost' or 'repair'.");
				return false;
			}
			
			ItemStack item = p.getItemInHand();
			MaterialInfo mi = new MaterialInfo(p.getItemInHand());
			
			if (mi.is(Material.AIR)) {
				sender.sendMessage("Please hold an item in your hand to repair.");
				return false;
			}
			
			if (! mi.hasBaseMaterial()) {
				sender.sendMessage("This item is not repairable.");
				return false;
			}
		
			Material base = mi.getBaseMaterial();
			ItemStack scrap = new ItemStack(base, 1);
			
			VirtualAnvil anvil = new VirtualAnvil(p, item, scrap, null);
			
			int durabilityPre = mi.getMaterial().getMaxDurability() - item.getDurability();
			int durabilityPost = mi.getMaterial().getMaxDurability() - anvil.getResult().getDurability();
			
			if (args[0].equalsIgnoreCase("cost")) {				
				String msg = String.format("Can repair from %d to %d durability for %d levels and %d scrap.",
						durabilityPre, durabilityPost, anvil.getLevelCost(), anvil.getScrapCost());
				sender.sendMessage(msg);
				return true;
			} else if (args[0].equalsIgnoreCase("repair")) {
				if (p.getInventory().contains(base, anvil.getScrapCost()) && p.getLevel() >= anvil.getLevelCost()) {
					float pct = p.getExp();
					p.setLevel(p.getLevel() - anvil.getLevelCost());
					p.setExp(pct);
					
					p.getInventory().remove(new ItemStack(base, anvil.getScrapCost()));
					
					p.updateInventory(); // TODO: this should be a next-tick task
					
					
					p.setItemInHand(anvil.getResult());
				} else {
					sender.sendMessage(String.format("This repair requires %d levels and %d scrap;",
							anvil.getLevelCost(), anvil.getScrapCost()));
				}
			} else {
				sender.sendMessage("Please specify 'cost' or 'repair'.");
				return false;
			}
		return true;
		}
	}
}
