/**
 * 
 */
package com.alphahelical.bukkit;

import java.util.Arrays;

import java.util.List;
import java.util.Map;
import com.alphahelical.collections.Collections;

import org.bukkit.enchantments.Enchantment;


/**
 * @author Keith Beckman
 *
 */
public class EnchantmentInfo {

	private static List<Enchantment> Enchantments = Arrays.<Enchantment>asList(
			Enchantment.ARROW_DAMAGE, Enchantment.ARROW_FIRE, Enchantment.ARROW_INFINITE, Enchantment.ARROW_KNOCKBACK,
			Enchantment.DAMAGE_ALL, Enchantment.DAMAGE_ARTHROPODS, Enchantment.DAMAGE_UNDEAD,
			Enchantment.DIG_SPEED, Enchantment.DURABILITY, Enchantment.FIRE_ASPECT, Enchantment.KNOCKBACK,
			Enchantment.LOOT_BONUS_BLOCKS, Enchantment.LOOT_BONUS_MOBS, Enchantment.OXYGEN,
			Enchantment.PROTECTION_ENVIRONMENTAL, Enchantment.PROTECTION_EXPLOSIONS, Enchantment.PROTECTION_FALL,
			Enchantment.PROTECTION_FIRE, Enchantment.PROTECTION_PROJECTILE, Enchantment.SILK_TOUCH, Enchantment.THORNS, Enchantment.WATER_WORKER);
	private static List<Integer> Weights = Arrays.<Integer>asList(
			10, 2, 1, 2,   10, 5, 5,   10, 5, 2, 6,   2, 2, 2,   10, 2, 5,   5, 5, 1, 1, 2);
	
	public static Map<Enchantment, Integer> EnchantmentWeights = Collections.<Enchantment, Integer>mapLists(Enchantments, Weights);
	
	private Enchantment enchantment;
	public Enchantment getEnchantment() { return this.enchantment;}
	
	public EnchantmentInfo(Enchantment enchantment) {
		this.enchantment = enchantment;
	}
	
	public int getRandomWeight() {
		return EnchantmentWeights.get(this.getEnchantment());
	}

	
	
	
}
