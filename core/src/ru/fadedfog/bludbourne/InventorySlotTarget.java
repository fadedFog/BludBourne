package ru.fadedfog.bludbourne;

import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop.Payload;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop.Source;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop.Target;

public class InventorySlotTarget extends Target {
	private InventorySlot targetSlot;
	
	public InventorySlotTarget(InventorySlot actor) {
		super(actor);
		targetSlot = actor;
	}

	@Override
	public boolean drag(Source source, Payload payload, 
			float x, float y, int pointer) {
		return true;
	}
	
	@Override
	public void reset(Source source, Payload payload) {
	}

	@Override
	public void drop(Source source, Payload payload, float x, float y, int pointer) {
		InventoryItem sourceActor = (InventoryItem) payload.getDragActor();
		InventoryItem targetActor = targetSlot.getTopInventoryItem();
		InventorySlot sourceSlot = ((InventorySlotSource) source).getSourceSlot();
		
		if (sourceActor == null) {
			return;
		}
		
		if (!targetSlot.doesAcceptItemUseType(sourceActor.getItemUseType())) {
			sourceSlot.add(sourceActor);
			return;
		}
		
		if (!targetSlot.hasItem()) {
			targetSlot.add(sourceActor);
		} else {
			if (sourceActor.isSameItemType(targetActor) 
					&& sourceActor.isStackable()) {
				targetSlot.add(sourceActor);
			} else {
				InventorySlot.swapSlots(sourceSlot,
						targetSlot, sourceActor);
			}
		}
	}

}
