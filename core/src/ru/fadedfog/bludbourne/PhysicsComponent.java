package ru.fadedfog.bludbourne;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;

public abstract class PhysicsComponent implements Component{
	private static final String TAG = PhysicsComponent.class.getSimpleName();
	public Rectangle boundingBox;
	protected BoundingBoxLoation boundingBoxLocation;
	
	public static enum BoundingBoxLocation {
		BOTTOM_LEFT, BOTTOM_CENTER, CENTER;
	}
	
	public PhysicsComponent() {
		
	}
	
	public abstract void update(Entity entity, MapManager mapMg, float delta);
	
	protected boolean isCollisionWithMapEntities(Entity entity, MapManager mapMg) {
		Array<Entity> entities = mapMg.getCurrentMapEntities();
		boolean isCollisionWithMapEntities = false;
		
		for (Entity mapEntity: entities) {
			if (mapEntity.equals(entity)) {
				continue;
			}
			
			Rectangle targetRect = mapEntity.getCurrentBoundingBox();
			if (boundingBox.overlaps(targetRect)) {
				entity.sendMessage(MESSAGE.COLLISION_WITH_ENTITY);
				isCollisionWithMapEntities = true;
				break;
			}
 		}
		
		return isCollisionWithMapEntities;
	}
	
	protected boolean isCollision(Entity entitySource, Entity entityTarget) {
		boolean isCollisionWithMapEntities = false;
		
		if (entitySource.equals(entityTarget)) {
			return false;
		}
		
		if (entitySource.getCurrentBoundingBox().
				overlaps(entityTarget.getCurrentBoundingBox())) {
			entitySource.sendMessage(MESSAGE.COLLISION_WITH_ENTITY);
			isCollisionWithMapEntities = true;
		}
		
		return isCollisionWithMapEntities;
	}
	
	protected boolean isCollisionWithMapLayer(Entity entity, MapManager mapMg) {
		
	}
	
	protected void setNextPositionToCurrent(Entity entity) {
		
	}
	
	protected void calculateNextPostion(float deltaTime) {
		
	}
	
	protected void initBoundingBox(float percentageWidthReduced, 
			float percentageHeightReduced) {
		float width;
		float height;
		float origWidth = Entity.FRAME_WIDTH;
		float origHeight = Entity.FRAME_HEIGHT;
		float widthReductionAmount = 1.0f - percentageWidthReduced;
		float heightReductionAmount = 1.0f - percentageHeightReduced;
		
		if (widthReductionAmount > 0 && widthReductionAmount < 1) {
			width = Entity.FRAME_WIDTH * widthReductionAmount;
		} else {
			width = Entity.FRAME_WIDTH;
		}
		
		if (heightReductionAmount > 0 && heightReductionAmount < 1) {
			height = Entity.FRAME_HEIGHT * heightReductionAmount;
		} else {
			height = Entity.FRAME_HEIGHT;
		}
		
		if (width == 0 || height == 0) {
			Gdx.app.debug(TAG, "Width and Height are 0!! " + width + ":" + height);
		}
		
		float minX;
		float minY;
		
		if (Map.UNIT_SCALE > 0) {
			minX = nextEntityPosition.x / Map.UNIT_SCALE;
			minY = nextEntityPosition.y / Map.UNIT_SCALE;
		} else {
			minX = nextEntityPosition.x;
			minY = nextEntityPosition.y;
		} 
		
		boundingBox.setWidth(width);
		boundingBox.setHeight(height);
		
		switch(boundingBoxLocation) {
			case BOTTOM_LEFT:
				boundingBox.set(minX, minY, width, height);
				break;
			case BOTTOM_CENTER:
				boundingBox.setCenter(minX + origWidth / 2, 
									minY + origHeight / 4);
				break;
			case CENTER: 
				boundingBox.setCenter(minX + origWidth / 2, 
									minY + origHeight / 2);
				break;
		}
	}
	
	protected void updateBoundingBoxPosition(Vector2 position) {
		float minX;
		float minY;
		
		if (Map.UNIT_SCALE > 0) {
			minX = position.x / Map.UNIT_SCALE;
			minY = position.y / Map.UNIT_SCALE;
		} else {
			minX = position.x;
			minY = position.y;
		}
		
		switch (boundingBoxLocation) {
			case BOTTOM_LEFT:
				boundingBox.set(minX, minY, 
						boundingBox.getWidth(), boundingBox.getHeight());
				break;
			case BOTTOM_CENTER:
				boundingBox.setCenter(minX + Entity.FRAME_WIDTH / 2,
									minY + Entity.FRAME_HEIGHT / 4);
				break;
			case CENTER:
				boundingBox.setCenter(minX + Entity.FRAME_WIDTH / 2,
									minY + Entity.FRAME_HEIGHT / 2);
				break;
		}
	}
	
	@Override
	public void receiveMessage(String fullMessage) {
		
	}

	@Override
	public void dispose() {
		
	}

}
