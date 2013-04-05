/**
 * 
 */
package com.alphahelical.bukkit;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.serialization.ConfigurationSerializable;
import org.bukkit.event.Listener;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;

/**
 * @author Keith Beckman
 *
 */
public class VirtualInventory implements ConfigurationSerializable, InventoryHolder, Listener {

	private Inventory inventory;

	@Override
	public Inventory getInventory() {
		return this.inventory;
	}
	
	public VirtualInventory(int size, String title) {
		this.inventory = Bukkit.createInventory(this, size, title);
	}

	@Override
	public Map<String, Object> serialize() {
		Map<String, Object> out = new HashMap<String, Object>();
		
		out.put("size", Integer.valueOf(this.getInventory().getSize()));
		out.put("title", this.getInventory().getTitle());
		
		List<Map<String, Object>> contents = new ArrayList<Map<String, Object>>(this.getInventory().getSize());
		
		for(ItemStack item : this.inventory)
			contents.add(item.serialize());
 		
		out.put("contents", contents);
		
		return out;
	}

	public static VirtualInventory deserialize(Map<String, Object> map) throws InvalidConfigurationException {
		if(!map.containsKey("size")) throw new InvalidConfigurationException("VirtualInventory size unknown.");
		if(!map.containsKey("title")) throw new InvalidConfigurationException("VirtualInventory title unknown.");
		if(!map.containsKey("contents")) throw new InvalidConfigurationException("VirtualInventory contents unknown.");
		
		int size = ((Integer) map.get("size")).intValue();
		String title = (String) map.get("title");
		
		@SuppressWarnings("unchecked")
		List<Map<String, Object>> contents = (List<Map<String, Object>>) map.get("contents");

		VirtualInventory out = new VirtualInventory(size, title);

		for(Map<String, Object> item : contents)
			out.getInventory().addItem(ItemStack.deserialize(item));
		
		return out;
	}

	
	
	
}
