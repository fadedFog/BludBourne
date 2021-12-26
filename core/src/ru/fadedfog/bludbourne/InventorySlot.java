package ru.fadedfog.bludbourne;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Stack;
import com.badlogic.gdx.utils.Array;

public class InventorySlot extends Stack {
	private Label numItemsLabel;
	private int numItemsVal = 0;
	private int filterItemType;
	
	public InventorySlot() {
		
	}
	
	public InventorySlot(int filterItemType, Image customBackgroundDecal) {
		
	}
	
	public void decrementItemCount() {
		
	}
	
	public void incrementItemCount() {
		
	}
	
	public void add(Actor actor) {
		
	}
	
	public void add(Array<Actor> array) {
		
	}
	
	public Array<Actor> getAllInventoryItems() {

	}
	
	public void clearAllInventoryItems() {
		
	}
	
	private void checkVisibilityOfItemCount() {
		
	}
	
	public boolean hasItem() {
		
	}
	
	public int getNumItems() {
		
	}
	
	public boolean doesAcceptItemUseType(int itemUseType) {
		
	}
	
	public InventoryItem getTopInventoryItem() {
		
	}
	
	public static void swapSlots(InventorySlot inventorySlotSource,
			InventorySlot inventorySlotTarget, InventoryItem dragActor) {
		
	}
	
}
