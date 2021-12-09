package ru.fadedfog.bludbourne;


import java.util.UUID;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

public class Entity {
	private static final String TAG = Entity.class.getSimpleName();
	private static final String defaultSpritePath = "sprites/characters/Warrior.png";
	private Vector2 velocity;
	private String entityID;
	private Direction currentDirection = Direction.LEFT;
	private Direction previousDirection = Direction.UP;
	private Animation walkLeftAnimation;
	private Animation walkRightAnimation;
	private Animation walkUpAnimation;
	private Animation walkDownAnimation;
	private Array<TextureRegion> walkLeftFrames;
	private Array<TextureRegion> walkRightFrames;
	private Array<TextureRegion> walkUpFrames;
	private Array<TextureRegion> walkDownFrames;
	protected Vector2 nextPlayerPosition;
	protected Vector2 currentPlayerPosition;
	protected State state = State.IDLE;
	protected float frameTime = 0f;
	protected Sprite frameSprite = null;
	protected TextureRegion currentFrame = null;
	public final int FRAME_WIDTH = 16;
	public final int FRAME_HEIGHT = 16;
	public static Rectangle boundingBox; 
	
	public enum State {
		IDLE, WALKING;
	}
	
	public enum Direction {
		UP, RIGHT, DOWN, LEFT;
	}
	
	public Entity() {
		initEntity();
	}
	
	public void initEntity() {
		this.entityID = UUID.randomUUID().toString();
		this.nextPlayerPosition = new Vector2();
		this.currentPlayerPosition = new Vector2();
		this.boundingBox = new Rectangle();
		this.velocity = new Vector2(2f, 2f);
		
		Utility.loadTextureAsset(defaultSpritePath);
		loadDefaultSprite();
		loadAllAnimation();
	}
	
	public void update(float delta) {
		frameTime = (frameTime + delta) % 5;
		setBoudingBoxSize(0f,  0.5f);
	}
	
	public void init(float startX, float startY) {
		this.currentPlayerPosition.x = startX;
		this.currentPlayerPosition.y = startY;
		this.nextPlayerPosition.x = startX;
		this.nextPlayerPosition.y = startY;
	}
	
	public void setBoudingBoxSize(float percentageWidthReduced, float percentageHeightReduced) {
		float width;
		float height;
		
		float widthReductionAmount = 1.0f - percentageWidthReduced;
		float heightReductionAmount = 1.0f - percentageHeightReduced;
		
		if (widthReductionAmount > 0 && widthReductionAmount < 1) {
			width = FRAME_WIDTH * widthReductionAmount;
		} else {
			width = FRAME_WIDTH;
		}
		
		if (heightReductionAmount > 0 && heightReductionAmount < 1) {
			height = FRAME_HEIGHT * heightReductionAmount;
		} else {
			height = FRAME_HEIGHT;
		}
		
		if (width == 0 || height == 0) {
			Gdx.app.debug(TAG, "Width and height are 0!! " + width + ":" + height);
		}
		
		float minX = nextPlayerPosition.x;;
		float minY = nextPlayerPosition.y;;
		if (MapManager.UNIT_SCALE > 0) {
			minX = nextPlayerPosition.x / MapManager.UNIT_SCALE;
			minY = nextPlayerPosition.y / MapManager.UNIT_SCALE;
		}
		
		boundingBox.set(minX, minY, width, height);
	}
	
	private void loadDefaultSprite() {
		Texture texture = Utility.getTextureAsset(defaultSpritePath);
		TextureRegion[][] textureFrames = TextureRegion.split(texture, FRAME_WIDTH, FRAME_HEIGHT);
		frameSprite = new Sprite(textureFrames[0][0].getTexture(), 0, 0, FRAME_WIDTH, FRAME_HEIGHT);
		currentFrame = textureFrames[0][0];
	}
	
	private void loadAllAnimation() {
		Texture texture = Utility.getTextureAsset(defaultSpritePath);
		TextureRegion[][] textureFrames = TextureRegion.split(texture, FRAME_WIDTH, FRAME_HEIGHT);
		walkDownFrames = new Array<TextureRegion>(4);
		walkLeftFrames = new Array<TextureRegion>(4);
		walkRightFrames = new Array<TextureRegion>(4);
		walkUpFrames = new Array<TextureRegion>(4);
		
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				TextureRegion region = textureFrames[i][j];
				if (region == null) {
					Gdx.app.debug(TAG, "Got null animation frame " + i + "," + j);
				}
				
				switch (i) {
					case 0:
						walkDownFrames.insert(j, region);
						break;

					case 1:
						walkLeftFrames.insert(j, region);
						break;
						
					case 2:
						walkRightFrames.insert(j, region);
						break;
						
					case 3:
						walkUpFrames.insert(j, region);
						break;
				}
			}
		}
		
		walkDownAnimation = new Animation(0,25f, walkDownFrames, Animation.PlayMode.LOOP);
		walkUpAnimation = new Animation(0,25f, walkUpFrames, Animation.PlayMode.LOOP);
		walkLeftAnimation = new Animation(0,25f, walkLeftFrames, Animation.PlayMode.LOOP);
		walkRightAnimation = new Animation(0,25f, walkRightFrames, Animation.PlayMode.LOOP);
		
	}
	
	public void dispose() {
		Utility.unloadAsset(defaultSpritePath);
	}
	
	public void setState(State state) {
		this.state = state;
	}
	
	public Sprite getFrameSprite() {
		return frameSprite;
	}
	
	public TextureRegion getFrame() {
		return currentFrame;
	}
	
	public Vector2 getCurrentPosition() {
		return currentPlayerPosition;
	}
	
	public void setCurrentPosition(float currentPositionX, float currentPositionY) {
		frameSprite.setX(currentPositionX);
		frameSprite.setY(currentPositionY);
		this.currentPlayerPosition.x = currentPositionX;
		this.currentPlayerPosition.y = currentPositionY;
	}
	
	public void setDirection(Direction direction, float deltaTime) {
		this.previousDirection = this.currentDirection;
		this.currentDirection = direction;
		
		switch (currentDirection) {
			case DOWN:
				currentFrame = (TextureRegion) walkDownAnimation.getKeyFrame(frameTime);
				break;
				
			case LEFT:
				currentFrame = (TextureRegion) walkLeftAnimation.getKeyFrame(frameTime);
				break;
				
			case UP:
				currentFrame = (TextureRegion) walkUpAnimation.getKeyFrame(frameTime);
				break;
				
			case RIGHT:
				currentFrame = (TextureRegion) walkRightAnimation.getKeyFrame(frameTime);
				break;
		}
	} 
	
	public void setNextPositionToCurrent() {
		setCurrentPosition(nextPlayerPosition.x, nextPlayerPosition.y);
	}
	
	public void calculateNextPosition(Direction currentDirection, float deltaTime) {
		float testX = currentPlayerPosition.x;
		float testY = currentPlayerPosition.y;
		
		velocity.scl(deltaTime);
		
		switch (currentDirection) {
			case DOWN:
				testY -= velocity.y;
				break;
				
			case LEFT:
				testX -= velocity.x;
				break;

			case UP:
				testY += velocity.y;
				break;
				
			case RIGHT:
				testX += velocity.x;
				break;
		}
		
		nextPlayerPosition.x = testX;
		nextPlayerPosition.y = testY;
		
		velocity.scl(1f / deltaTime);
	}
	
}
