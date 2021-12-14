package ru.fadedfog.bludbourne;

import java.util.Hashtable;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.GridPoint2;
import com.badlogic.gdx.utils.Array;


public abstract class GraphicsComponent implements Component {
	protected Hashtable<Entity.AnimationType, Animation<TextureRegion>> animations;
	protected TextureRegion currentFrame = null;
	protected ShapeRenderer shapeRenderer;
	protected float frameTime = 0;
	protected Entity.State currentState;
	protected Entity.Direction currentDirection;
	
	
	protected GraphicsComponent() {
		
	}
	
	public abstract void update(Entity entity, MapManager mapMg, Batch batch, float delta);
	
	protected void updateAnimations(float delta) {
		frameTime = (frameTime + delta) % 5;
		
		switch (currentDirection) {
			case DOWN: 
				if (currentState == Entity.State.WALKING) {
					Animation<TextureRegion> animation = animations.get(Entity.AnimationType.WALK_DOWN);
					if (animation == null) {
						return;
					}
					
					currentFrame = animation.getKeyFrame(frameTime);
				} else if (currentState == Entity.State.IDLE) {
					Animation<TextureRegion> animation = animations.get(Entity.AnimationType.WALK_DOWN);
					if (animation == null) {
						return;
					}
					
					currentFrame = animation.getKeyFrames()[0];
				} else if (currentState == Entity.State.IMMOBILE) {
					Animation<TextureRegion> animation = animations.get(Entity.AnimationType.IMMOBLE);
					if (animation == null) {
						return;
					}
					
					currentFrame = animation.getKeyFrame(frameTime);
				}
				break;
			case LEFT:
				if (currentState == Entity.State.WALKING) {
					Animation<TextureRegion> animation = animations.get(Entity.AnimationType.WALK_LEFT);
					if (animation == null) {
						return;
					}
					
					currentFrame = animation.getKeyFrame(frameTime);
				} else if (currentState == Entity.State.IDLE) {
					Animation<TextureRegion> animation = animations.get(Entity.AnimationType.WALK_LEFT);
					if (animation == null) {
						return;
					}
					
					currentFrame = animation.getKeyFrames()[0];
				} else if (currentState == Entity.State.IMMOBILE) {
					Animation<TextureRegion> animation = animations.get(Entity.AnimationType.IMMOBLE);
					if (animation == null) {
						return;
					}
					
					currentFrame = animation.getKeyFrame(frameTime);
				}
				break;
			case RIGHT:
				if (currentState == Entity.State.WALKING) {
					Animation<TextureRegion> animation = animations.get(Entity.AnimationType.WALK_RIGHT);
					if (animation == null) {
						return;
					}
					
					currentFrame = animation.getKeyFrame(frameTime);
				} else if (currentState == Entity.State.IDLE) {
					Animation<TextureRegion> animation = animations.get(Entity.AnimationType.WALK_RIGHT);
					if (animation == null) {
						return;
					}
					
					currentFrame = animation.getKeyFrames()[0];
				} else if (currentState == Entity.State.IMMOBILE) {
					Animation<TextureRegion> animation = animations.get(Entity.AnimationType.IMMOBLE);
					if (animation == null) {
						return;
					}
					
					currentFrame = animation.getKeyFrame(frameTime);
				}
				break;
			case UP:
				if (currentState == Entity.State.WALKING) {
					Animation<TextureRegion> animation = animations.get(Entity.AnimationType.WALK_UP);
					if (animation == null) {
						return;
					}
					
					currentFrame = animation.getKeyFrame(frameTime);
				} else if (currentState == Entity.State.IDLE) {
					Animation<TextureRegion> animation = animations.get(Entity.AnimationType.WALK_UP);
					if (animation == null) {
						return;
					}
					
					currentFrame = animation.getKeyFrames()[0];
				} else if (currentState == Entity.State.IMMOBILE) {
					Animation<TextureRegion> animation = animations.get(Entity.AnimationType.IMMOBLE);
					if (animation == null) {
						return;
					}
					
					currentFrame = animation.getKeyFrame(frameTime);
				}
				break;
		}
	}
	
	protected Animation loadAnimation(String firstTexture, String secondTexture, 
			Array<GridPoint2> points, float frameDuraction) {
		Utility.loadTextureAsset(firstTexture);
		Texture texture1 = Utility.getTextureAsset(firstTexture);
		
		Utility.loadTextureAsset(secondTexture);
		Texture texture2 = Utility.getTextureAsset(secondTexture);
		
		TextureRegion[][] texture1Frames = TextureRegion.split(texture1, 
				Entity.FRAME_WIDTH, Entity.FRAME_HEIGHT);
		TextureRegion[][] texture2Frames = TextureRegion.split(texture2, 
				Entity.FRAME_WIDTH, Entity.FRAME_HEIGHT);
		
		Array<TextureRegion> animationKeyFrame = new Array<TextureRegion>(2);
		GridPoint2 point = points.first();
		animationKeyFrame.add(texture1Frames[point.x][point.y]);
		animationKeyFrame.add(texture2Frames[point.x][point.y]);
		
		return new Animation(frameDuraction, 
				animationKeyFrame, Animation.PlayMode.LOOP);
	}
	
	protected Animation loadAnimation(String textureName, Array<GridPoint2> points, 
			float frameDuraction) {
		Utility.loadTextureAsset(textureName);
		Texture texture = Utility.getTextureAsset(textureName);
		
		TextureRegion[][] textureFrames = TextureRegion.split(texture, 
				Entity.FRAME_WIDTH, Entity.FRAME_HEIGHT);
		Array<TextureRegion> anmationKeyFrames = new Array<TextureRegion>(points.size);
		
		for (GridPoint2 point: points) {
			anmationKeyFrames.add(textureFrames[point.x][point.y]);
		}
		
		return new Animation(frameDuraction, anmationKeyFrames, Animation.PlayMode.LOOP);
	}
}
