/**
 * 
 */
package com.alphahelical.bukkit.sgcore.inventory;

import java.util.HashMap;
import java.util.List;
import java.util.ListIterator;

import org.apache.commons.lang.NullArgumentException;
import org.bukkit.Material;
import org.bukkit.entity.HumanEntity;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;

/**
 * @author kbeckman
 *
 */
public class WrappedInventory implements Inventory {

	private Inventory innerInventory;
	public Inventory getInnerInventory() {
		return innerInventory;
	}
	private void setInnerInventory(Inventory innerInventory) {
		this.innerInventory = innerInventory;
	}

	public WrappedInventory(Inventory innerInventory) {
		if(innerInventory == null) throw new NullArgumentException("innerInventory");
		this.setInnerInventory(innerInventory);
	}

	/* (non-Javadoc)
	 * @see org.bukkit.inventory.Inventory#addItem(org.bukkit.inventory.ItemStack[])
	 */
	@Override
	public HashMap<Integer, ItemStack> addItem(ItemStack... items)
			throws IllegalArgumentException {
		return this.getInnerInventory().addItem(items);
	}

	/* (non-Javadoc)
	 * @see org.bukkit.inventory.Inventory#all(int)
	 */
	@Override
	public HashMap<Integer, ? extends ItemStack> all(int materialId) {
		return this.getInnerInventory().all(materialId);
	}

	/* (non-Javadoc)
	 * @see org.bukkit.inventory.Inventory#all(org.bukkit.Material)
	 */
	@Override
	public HashMap<Integer, ? extends ItemStack> all(Material material)
			throws IllegalArgumentException {
		return this.getInnerInventory().all(material);
	}

	/* (non-Javadoc)
	 * @see org.bukkit.inventory.Inventory#all(org.bukkit.inventory.ItemStack)
	 */
	@Override
	public HashMap<Integer, ? extends ItemStack> all(ItemStack item) {
		return this.getInnerInventory().all(item);
	}

	/* (non-Javadoc)
	 * @see org.bukkit.inventory.Inventory#clear()
	 */
	@Override
	public void clear() {
		this.getInnerInventory().clear();
	}

	/* (non-Javadoc)
	 * @see org.bukkit.inventory.Inventory#clear(int)
	 */
	@Override
	public void clear(int index) {
		this.getInnerInventory().clear(index);
	}

	/* (non-Javadoc)
	 * @see org.bukkit.inventory.Inventory#contains(int)
	 */
	@Override
	public boolean contains(int materialId) {
		return this.getInnerInventory().contains(materialId);
	}

	/* (non-Javadoc)
	 * @see org.bukkit.inventory.Inventory#contains(org.bukkit.Material)
	 */
	@Override
	public boolean contains(Material material) throws IllegalArgumentException {
		return this.getInnerInventory().contains(material);
	}

	/* (non-Javadoc)
	 * @see org.bukkit.inventory.Inventory#contains(org.bukkit.inventory.ItemStack)
	 */
	@Override
	public boolean contains(ItemStack item) {
		return this.getInnerInventory().contains(item);
	}

	/* (non-Javadoc)
	 * @see org.bukkit.inventory.Inventory#contains(int, int)
	 */
	@Override
	public boolean contains(int materialId, int amount) {
		return this.getInnerInventory().contains(materialId, amount);
	}

	/* (non-Javadoc)
	 * @see org.bukkit.inventory.Inventory#contains(org.bukkit.Material, int)
	 */
	@Override
	public boolean contains(Material material, int amount)
			throws IllegalArgumentException {
		return this.getInnerInventory().contains(material, amount);
	}

	/* (non-Javadoc)
	 * @see org.bukkit.inventory.Inventory#contains(org.bukkit.inventory.ItemStack, int)
	 */
	@Override
	public boolean contains(ItemStack item, int amount) {
		return this.getInnerInventory().contains(item, amount);
	}

	/* (non-Javadoc)
	 * @see org.bukkit.inventory.Inventory#containsAtLeast(org.bukkit.inventory.ItemStack, int)
	 */
	@Override
	public boolean containsAtLeast(ItemStack item, int amount) {
		return this.getInnerInventory().containsAtLeast(item, amount);
	}

	/* (non-Javadoc)
	 * @see org.bukkit.inventory.Inventory#first(int)
	 */
	@Override
	public int first(int materialId) {
		return this.getInnerInventory().first(materialId);
	}

	/* (non-Javadoc)
	 * @see org.bukkit.inventory.Inventory#first(org.bukkit.Material)
	 */
	@Override
	public int first(Material material) throws IllegalArgumentException {
		return this.getInnerInventory().first(material);
	}

	/* (non-Javadoc)
	 * @see org.bukkit.inventory.Inventory#first(org.bukkit.inventory.ItemStack)
	 */
	@Override
	public int first(ItemStack item) {
		return this.getInnerInventory().first(item);
	}

	/* (non-Javadoc)
	 * @see org.bukkit.inventory.Inventory#firstEmpty()
	 */
	@Override
	public int firstEmpty() {
		return this.getInnerInventory().firstEmpty();
	}

	/* (non-Javadoc)
	 * @see org.bukkit.inventory.Inventory#getContents()
	 */
	@Override
	public ItemStack[] getContents() {
		return this.getInnerInventory().getContents();
	}

	/* (non-Javadoc)
	 * @see org.bukkit.inventory.Inventory#getHolder()
	 */
	@Override
	public InventoryHolder getHolder() {
		return this.getInnerInventory().getHolder();
	}

	/* (non-Javadoc)
	 * @see org.bukkit.inventory.Inventory#getItem(int)
	 */
	@Override
	public ItemStack getItem(int index) {
		return this.getInnerInventory().getItem(index);
	}

	/* (non-Javadoc)
	 * @see org.bukkit.inventory.Inventory#getMaxStackSize()
	 */
	@Override
	public int getMaxStackSize() {
		return this.getInnerInventory().getMaxStackSize();
	}

	/* (non-Javadoc)
	 * @see org.bukkit.inventory.Inventory#getName()
	 */
	@Override
	public String getName() {
		return this.getInnerInventory().getName();
	}

	/* (non-Javadoc)
	 * @see org.bukkit.inventory.Inventory#getSize()
	 */
	@Override
	public int getSize() {
		return this.getInnerInventory().getSize();
	}

	/* (non-Javadoc)
	 * @see org.bukkit.inventory.Inventory#getTitle()
	 */
	@Override
	public String getTitle() {
		return this.getInnerInventory().getTitle();
	}

	/* (non-Javadoc)
	 * @see org.bukkit.inventory.Inventory#getType()
	 */
	@Override
	public InventoryType getType() {
		return this.getInnerInventory().getType();
	}

	/* (non-Javadoc)
	 * @see org.bukkit.inventory.Inventory#getViewers()
	 */
	@Override
	public List<HumanEntity> getViewers() {
		return this.getInnerInventory().getViewers();
	}

	/* (non-Javadoc)
	 * @see org.bukkit.inventory.Inventory#iterator()
	 */
	@Override
	public ListIterator<ItemStack> iterator() {
		return this.getInnerInventory().iterator();
	}

	/* (non-Javadoc)
	 * @see org.bukkit.inventory.Inventory#iterator(int)
	 */
	@Override
	public ListIterator<ItemStack> iterator(int index) {
		return this.getInnerInventory().iterator(index);
	}

	/* (non-Javadoc)
	 * @see org.bukkit.inventory.Inventory#remove(int)
	 */
	@Override
	public void remove(int materialId) {
		this.getInnerInventory().remove(materialId);
	}

	/* (non-Javadoc)
	 * @see org.bukkit.inventory.Inventory#remove(org.bukkit.Material)
	 */
	@Override
	public void remove(Material material) throws IllegalArgumentException {
		this.getInnerInventory().remove(material);
	}

	/* (non-Javadoc)
	 * @see org.bukkit.inventory.Inventory#remove(org.bukkit.inventory.ItemStack)
	 */
	@Override
	public void remove(ItemStack item) {
		this.getInnerInventory().remove(item);

	}

	/* (non-Javadoc)
	 * @see org.bukkit.inventory.Inventory#removeItem(org.bukkit.inventory.ItemStack[])
	 */
	@Override
	public HashMap<Integer, ItemStack> removeItem(ItemStack... items)
			throws IllegalArgumentException {
		return this.getInnerInventory().removeItem(items);
	}

	/* (non-Javadoc)
	 * @see org.bukkit.inventory.Inventory#setContents(org.bukkit.inventory.ItemStack[])
	 */
	@Override
	public void setContents(ItemStack[] items) throws IllegalArgumentException {
		this.getInnerInventory().setContents(items);
	}

	/* (non-Javadoc)
	 * @see org.bukkit.inventory.Inventory#setItem(int, org.bukkit.inventory.ItemStack)
	 */
	@Override
	public void setItem(int index, ItemStack item) {
		this.getInnerInventory().setItem(index, item);
	}

	/* (non-Javadoc)
	 * @see org.bukkit.inventory.Inventory#setMaxStackSize(int)
	 */
	@Override
	public void setMaxStackSize(int size) {
		this.getInnerInventory().setMaxStackSize(size);
	}

}
