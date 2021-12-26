package ru.fadedfog.bludbourne;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

public class InventoryItem extends Image {
	private int itemAttributes;
	private int itemUseType;
	private String itemShortDescription;
	private ItemTypeID itemTypeID;
	
	public InventoryItem() {
		
	}
	
	public InventoryItem(TextureRegion textureRegion, int itemAttributes, ItemTypeID itemTypeID, int itemUseType) {
		this.itemAttributes = itemAttributes;
		this.itemTypeID = itemTypeID;
	}
	
	public InventoryItem(InventoryItem inventoryItem) {
		
	}
	
	public ItemTypeID getItemTypeID() {
		return itemTypeID;
	}
	
	public void setItemTypeID(ItemTypeID itemTypeID) {
		
	}
	
	public int getItemAttributes() {
		return itemAttributes;
	}
	
	public void setItemAttributes(int itemAttributes) {
		this.itemAttributes = itemAttributes;
	}
	
	public int getItemUseType() {
		return itemUseType;
	}
	
	public void setItemUseType(int itemUseType) {
		this.itemUseType = itemUseType;
	}

	public String getItemShortDescription() {
		return itemShortDescription;
	}

	public void setItemShortDescription(String itemShortDescription) {
		this.itemShortDescription = itemShortDescription;
	}
	
	public boolean isStackable() {
		return false;
	}
	
	public boolean isStackable(InventoryItem candidateInventoryItem) {
		return false;
	}
	
}
