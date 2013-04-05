/**
 * 
 */
package com.alphahelical.bukkit;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import com.alphahelical.collections.Collections;

/**
 * @author Keith Beckman
 *
 */
public class MaterialInfo {

	//TODO: better way to store this: config file?
	
	public static List<Material> ToolMaterials = Arrays.<Material>asList(
			Material.WOOD, Material.COBBLESTONE, Material.IRON_INGOT, Material.GOLD_INGOT, Material.DIAMOND);

	
	public static List<Collection<Material>> ToolItems = Arrays.<Collection<Material>>asList(
			Arrays.<Material>asList(Material.WOOD_AXE, Material.WOOD_HOE, Material.WOOD_PICKAXE, Material.WOOD_SPADE, Material.WOOD_SWORD),
			Arrays.<Material>asList(Material.STONE_AXE, Material.STONE_HOE, Material.STONE_PICKAXE, Material.STONE_SPADE, Material.STONE_SWORD),
			Arrays.<Material>asList(Material.IRON_AXE, Material.IRON_HOE, Material.IRON_PICKAXE, Material.IRON_SPADE, Material.IRON_SWORD),
			Arrays.<Material>asList(Material.GOLD_AXE, Material.GOLD_HOE, Material.GOLD_PICKAXE, Material.GOLD_SPADE, Material.GOLD_SWORD),
			Arrays.<Material>asList(Material.DIAMOND_AXE, Material.DIAMOND_HOE, Material.DIAMOND_PICKAXE, Material.DIAMOND_SPADE, Material.DIAMOND_SWORD)
		);

	public static Collection<Material> OtherRepairableTools = new HashSet<Material>(
			Arrays.<Material>asList(Material.SHEARS, Material.FLINT_AND_STEEL, Material.FISHING_ROD, Material.CARROT_STICK)
		);
	
	public static List<Material> ArmorMaterials = Arrays.<Material>asList(
			Material.LEATHER, Material.FIRE, Material.IRON_INGOT, Material.GOLD_INGOT, Material.DIAMOND);

	public static List<Collection<Material>> ArmorItems = Arrays.<Collection<Material>>asList(
			Arrays.<Material>asList(Material.LEATHER_HELMET, Material.LEATHER_CHESTPLATE, Material.LEATHER_LEGGINGS, Material.LEATHER_BOOTS),
			Arrays.<Material>asList(Material.CHAINMAIL_HELMET, Material.CHAINMAIL_CHESTPLATE, Material.CHAINMAIL_LEGGINGS, Material.CHAINMAIL_BOOTS),
			Arrays.<Material>asList(Material.IRON_HELMET, Material.IRON_CHESTPLATE, Material.IRON_LEGGINGS, Material.IRON_BOOTS),
			Arrays.<Material>asList(Material.GOLD_HELMET, Material.GOLD_CHESTPLATE, Material.GOLD_LEGGINGS, Material.GOLD_BOOTS),
			Arrays.<Material>asList(Material.DIAMOND_HELMET, Material.DIAMOND_CHESTPLATE, Material.DIAMOND_LEGGINGS, Material.DIAMOND_BOOTS)
		);
	
	public static Map<Material, Collection<Material>> ArmorTiers = Collections.<Material, Collection<Material>>mapLists(ArmorMaterials, ArmorItems);

	public static Map<Material, Collection<Material>> ToolTiers = Collections.<Material, Collection<Material>>mapLists(ToolMaterials, ToolItems);

	// Property - Material
	private Material material;
	public Material getMaterial() { return this.material; }
	public void setMaterial(Material material) { this.material = material; }

	
	// Property - RepairMap
	private static Map<Material, Material> repairMap;

	public static Map<Material, Material> getRepairMap() {
		
		if(repairMap == null) {
			repairMap = new HashMap<Material, Material>();
			
			List<Map<Material, Collection<Material>>> tierlist = Arrays.<Map<Material, Collection<Material>>>asList(
					MaterialInfo.ArmorTiers, MaterialInfo.ToolTiers);
			
			for(Map<Material, Collection<Material>> tiers : tierlist) {
				for(Material base : tiers.keySet()) {
					for(Material item : tiers.get(base)) {
						repairMap.put(item, base);
					}
				}
			}
		}

		return repairMap;
	}

	public MaterialInfo(Material material) {
		this.setMaterial(material);
	}
	
	public MaterialInfo(ItemStack item) {
		this.setMaterial(item.getType());
	}
	
	public boolean hasBaseMaterial() {
		return (this.getBaseMaterial() != null);
	}
	
	public Material getBaseMaterial() {
		if (getRepairMap().containsKey(this.getMaterial()))
			return getRepairMap().get(this.getMaterial());
		return null;
	}
	
	public boolean baseMaterialIs(Material base) {
		return (base != null && this.getBaseMaterial().equals(base));
	}

	public boolean baseMaterialIs(ItemStack base) {
		return (base != null && this.baseMaterialIs(base.getType()));
	}
	
	public boolean hasDurability() {
		return this.getMaterial().getMaxDurability() > 0;
	}
	
	public boolean is(Material mat) {
		return this.getMaterial().equals(mat);
	}
	
}
