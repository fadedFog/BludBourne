package ru.fadedfog.bludbourne;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

public class InventoryItem extends Image {
	private int itemAttributes;
	private int itemUseType;
	private String itemShortDescription;
	private ItemTypeID itemTypeID;
	
    public enum ItemTypeID {
        ARMOR01,ARMOR02,ARMOR03,ARMOR04,ARMOR05,
        BOOTS01,BOOTS02,BOOTS03,BOOTS04,BOOTS05,
        HELMET01,HELMET02,HELMET03,HELMET04,HELMET05,
        SHIELD01,SHIELD02,SHIELD03,SHIELD04,SHIELD05,
        WANDS01,WANDS02,WANDS03,WANDS04,WANDS05,
        WEAPON01,WEAPON02,WEAPON03,WEAPON04,WEAPON05,
        POTIONS01,POTIONS02,POTIONS03,
        SCROLL01,SCROLL02,SCROLL03,
        HERB001,BABY001,HORNS001,FUR001,
        NONE
        ;
    }
	
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

	public boolean isSameItemType(InventoryItem targetActor) {
		return  itemTypeID == targetActor.getItemTypeID();
	}
	
}
