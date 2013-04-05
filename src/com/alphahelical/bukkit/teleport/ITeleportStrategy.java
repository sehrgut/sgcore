package com.alphahelical.bukkit.teleport;

import org.bukkit.Location;
import org.bukkit.entity.Player;

public interface ITeleportStrategy {
	public void teleport(Player p, Location l);
}
