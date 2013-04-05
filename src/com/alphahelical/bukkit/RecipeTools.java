/**
 * 
 */
package com.alphahelical.bukkit;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.FurnaceRecipe;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.Recipe;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.ShapelessRecipe;

/**
 * @author Keith Beckman
 *
 */
public final class RecipeTools {
	private RecipeTools() {}

	public static Collection<ItemStack> getIngredients(ShapedRecipe recipe) {
		Map<Character, ItemStack> ingredients = recipe.getIngredientMap();
		String[] shape = recipe.getShape();
		
		for(String row : shape) {
			for(Character cell : row.toCharArray()) {
				if (ingredients.containsKey(cell)) {
					ItemStack i = ingredients.get(cell);
					i.setAmount(i.getAmount() + 1);
				}
			}
		}
		
		return ingredients.values();
	}
	
	public static Collection<ItemStack> getIngredients(ShapelessRecipe recipe) {
		List<ItemStack> ingredients = recipe.getIngredientList();
		Map<Material, Integer> counts = new HashMap<Material, Integer>();
		
		for (ItemStack i : ingredients) {
			Material mat = i.getType();
			if(counts.containsKey(mat)) {
				counts.put(mat, counts.get(mat) + 1);
			} else {
				counts.put(mat, 1);
			}
		}
		
		Set<ItemStack> out = new HashSet<ItemStack>();
		
		for (Material mat : counts.keySet()) {
			out.add(new ItemStack(mat, counts.get(mat)));
		}
		
		return out;
	}
	
	public static Collection<ItemStack> getIngredients(FurnaceRecipe recipe) {
		Set<ItemStack> out = new HashSet<ItemStack>();
		out.add(recipe.getInput());
		return out;
	}
	
	public static Collection<Collection<ItemStack>> getIngredients(Material material) {
		Collection<Collection<ItemStack>> out = new HashSet<Collection <ItemStack>>();
		
		for (Recipe r : Bukkit.getRecipesFor(new ItemStack(material))) {
			if (r instanceof ShapedRecipe) {
				out.add(getIngredients((ShapedRecipe) r));
			} else if(r instanceof ShapelessRecipe) {
				out.add(getIngredients((ShapelessRecipe) r));
			} else if(r instanceof FurnaceRecipe) {
				out.add(getIngredients((FurnaceRecipe) r));
			} //TODO: what about brewing?
		}
		
		return out;
	}
	
	
}
