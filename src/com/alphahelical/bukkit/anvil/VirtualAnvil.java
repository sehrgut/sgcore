/**
 * 
 */
package com.alphahelical.bukkit.anvil;

import java.util.Iterator;
import java.util.Map;

import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.EnchantmentStorageMeta;
import org.bukkit.inventory.meta.ItemMeta;

import com.alphahelical.bukkit.EnchantmentInfo;
import com.alphahelical.bukkit.ItemInfo;
import com.alphahelical.bukkit.MaterialInfo;

/**
 * @author Keith Beckman
 *
 */
public class VirtualAnvil {

	private int levelCost;
	private ItemStack input;
	private ItemStack scrap;
	private ItemStack result;
	private int scrapCost;
	private Player player;
	private String newName;
	
	public VirtualAnvil(Player player, ItemStack input, ItemStack scrap, String newName) {
		this.player = player;
		this.input = input.clone();
		this.scrap = scrap.clone();
		this.newName = newName;
		
		this.computeCost();
	}

	public ItemStack getResult() { return this.result; }
	public int getLevelCost() { return this.levelCost; }
	public int getScrapCost() { return this.scrapCost; }
	
	
	public int getOldRemainingDurability() {
		return this.input.getType().getMaxDurability() - this.input.getDurability();
	}
	
	public int getNewRemainingDurability() {
		return this.result.getType().getMaxDurability() - this.result.getDurability();
	}
	
    private void computeCost() {
        ItemStack itemstack = this.input;

        this.levelCost = 0;
        int i = 0;
        byte b0 = 0;
        int j = 0;

        if (itemstack == null) {
            this.result = (ItemStack) null;
            this.levelCost = 0;
        } else {
            ItemStack itemstack1 = itemstack.clone();
            ItemStack itemstack2 = this.scrap;
            
            Material material1 = itemstack1.getType();
            Material material2 = itemstack2.getType();
            
            MaterialInfo materialinfo1 = new MaterialInfo(itemstack1);
            MaterialInfo materialinfo2 = new MaterialInfo(itemstack2);
            
            Map<Enchantment, Integer> map = itemstack1.getEnchantments();
            boolean isBookEnchantment = false;
            
            ItemInfo iteminfo1 = new ItemInfo(itemstack1);
            ItemInfo iteminfo2 = (itemstack2 != null ? new ItemInfo(itemstack2) : null);
            
            int k = b0 + iteminfo1.getRepairPenalty() + (iteminfo2 == null ? 0 : iteminfo2.getRepairPenalty());

            this.scrapCost = 0;
            int l;
            int i1;
            int j1;
            int k1;
            int l1;
            Iterator<Enchantment> iterator;
            Enchantment enchantment;

            if (itemstack2 != null) {
//            	ItemMeta meta1 = itemstack1.getItemMeta();
            	ItemMeta meta2 = itemstack2.getItemMeta();
            	
            	EnchantmentStorageMeta enchantedBook = (meta2 instanceof EnchantmentStorageMeta ? (EnchantmentStorageMeta)meta2 : null);
            	
                isBookEnchantment = materialinfo2.is(Material.ENCHANTED_BOOK) && enchantedBook.hasEnchants();
                if (materialinfo1.hasDurability() && materialinfo1.baseMaterialIs(itemstack2)) {
                    l = Math.min(itemstack1.getDurability(), material1.getMaxDurability() / 4);
                    if (l <= 0) {
                        this.result =(ItemStack) null;
                        this.levelCost = 0;
                        return;
                    }

                    for (i1 = 0; l > 0 && i1 < itemstack2.getAmount(); ++i1) {
                        j1 = itemstack1.getDurability() - l;
                        itemstack1.setDurability((short)j1);
                        i += Math.max(1, l / 100) + map.size();
                        l = Math.min(itemstack1.getDurability(), material1.getMaxDurability() / 4);
                    }

                    this.scrapCost = i1;
                } else {
                    if (!isBookEnchantment && (itemstack1.getTypeId() != itemstack2.getTypeId() || !materialinfo1.hasDurability())) {
                        this.result =(ItemStack) null;
                        this.levelCost = 0;
                        return;
                    }

                    if (materialinfo1.hasDurability() && !isBookEnchantment) {
                        l = material1.getMaxDurability() - itemstack.getDurability();
                        i1 = material2.getMaxDurability() - itemstack2.getDurability();
                        j1 = i1 + material1.getMaxDurability() * 12 / 100;
                        int i2 = l + j1;

                        k1 = material1.getMaxDurability() - i2;
                        if (k1 < 0) {
                            k1 = 0;
                        }

                        if (k1 < itemstack1.getDurability()) {
                            itemstack1.setDurability((short)k1);
                            i += Math.max(1, j1 / 100);
                        }
                    }

                    Map<Enchantment, Integer> map1 = itemstack2.getEnchantments();

                    iterator = map1.keySet().iterator();

                    while (iterator.hasNext()) {
                        enchantment = iterator.next();
                        j1 = enchantment.getId();
                        k1 = map.containsKey(enchantment) ? map.get(enchantment) : 0;
                        l1 = map1.get(enchantment);
                        int j2;

                        if (k1 == l1) {
                            ++l1;
                            j2 = l1;
                        } else {
                            j2 = Math.max(l1, k1);
                        }

                        l1 = j2;
                        int k2 = l1 - k1;
                        boolean canEnchant = enchantment.canEnchantItem(itemstack);

                        if (this.player.getGameMode().equals(GameMode.CREATIVE) || materialinfo1.is(Material.ENCHANTED_BOOK)) {
                            canEnchant = true;
                        }

                        Iterator<Enchantment> iterator1 = map.keySet().iterator();

                        while (iterator1.hasNext()) {
                        	Enchantment enchantment2 = iterator1.next();
                            int l2 = enchantment2.getId();

                            if (l2 != j1 && enchantment.equals(enchantment2)) {
                                canEnchant = false;
                                i += k2;
                            }
                        }

                        if (canEnchant) {
                            if (l1 > enchantment.getMaxLevel()) {
                                l1 = enchantment.getMaxLevel();
                            }

                            map.put(enchantment, l1);
                            int i3 = 0;

                            EnchantmentInfo enchantmentinfo1 = new EnchantmentInfo(enchantment);
                            
                            switch (enchantmentinfo1.getRandomWeight()) {
                            case 1:
                                i3 = 8;
                                break;

                            case 2:
                                i3 = 4;

                            case 3:
                            case 4:
                            case 6:
                            case 7:
                            case 8:
                            case 9:
                            default:
                                break;

                            case 5:
                                i3 = 2;
                                break;

                            case 10:
                                i3 = 1;
                            }

                            if (isBookEnchantment) {
                                i3 = Math.max(1, i3 / 2);
                            }

                            i += i3 * k2;
                        }
                    }
                }
            }

            ItemMeta meta = itemstack.getItemMeta();
            ItemInfo iteminfo = new ItemInfo(itemstack);
            MaterialInfo materialinfo = new MaterialInfo(itemstack);
            
            if (this.newName != null && !this.newName.equalsIgnoreCase(iteminfo.getName()) && !this.newName.isEmpty()) {
                j = materialinfo.hasDurability() ? 7 : itemstack.getAmount() * 5;
                i += j;
                if (meta.hasDisplayName()) {
                    k += j / 2;
                }

                meta.setDisplayName(this.newName);
                itemstack.setItemMeta(meta);
            }

            l = 0;

            for (iterator = map.keySet().iterator(); iterator.hasNext(); k += l + k1 * l1) {
                enchantment = iterator.next();
                j1 = enchantment.getId();
                k1 = map.get(enchantment);
                l1 = 0;
                ++l;
                
                EnchantmentInfo enchantmentinfo = new EnchantmentInfo(enchantment);
                switch (enchantmentinfo.getRandomWeight()) {
                case 1:
                    l1 = 8;
                    break;

                case 2:
                    l1 = 4;

                case 3:
                case 4:
                case 6:
                case 7:
                case 8:
                case 9:
                default:
                    break;

                case 5:
                    l1 = 2;
                    break;

                case 10:
                    l1 = 1;
                }

                if (isBookEnchantment) {
                    l1 = Math.max(1, l1 / 2);
                }
            }

            if (isBookEnchantment) {
                k = Math.max(1, k / 2);
            }

            this.levelCost = k + i;
            if (i <= 0) {
                itemstack1 = null;
            }

            if (j == i && j > 0 && this.levelCost >= 40) {
                // this.h.getLogger().info("Naming an item only, cost too high; giving discount to cap cost to 39 levels"); // CraftBukkit - remove debug
                this.levelCost = 39;
            }

            if (this.levelCost >= 40 && !this.player.getGameMode().equals(GameMode.CREATIVE)) {
                itemstack1 = null;
            }

            if (itemstack1 != null) {
                i1 = iteminfo1.getRepairPenalty();
                if (itemstack2 != null && i1 < iteminfo2.getRepairPenalty()) {
                    i1 = iteminfo2.getRepairPenalty();
                }

                if (meta.hasDisplayName()) {
                    i1 -= 9;
                }

                if (i1 < 0) {
                    i1 = 0;
                }

                i1 += 2;
                iteminfo1.setRepairPenalty(i1);
                itemstack1.addEnchantments(map);
            }

            this.result = itemstack1;
            //this.b(); // send itemcrafted event
        }
    }


}

    