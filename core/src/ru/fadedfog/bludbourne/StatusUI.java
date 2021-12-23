package ru.fadedfog.bludbourne;

import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.WidgetGroup;
import com.badlogic.gdx.scenes.scene2d.ui.Label;

public class StatusUI extends Window {
	private Image hpBar;
	private Image mpBar;
	private Image xpBar;
	private ImageButton inventoryButton;
	private int levelVal = 1;
	private int goldVal = 0;
	private int hpVal = 50;
	private int mpVal = 50;
	private int xpVal = 0;
	
	public StatusUI() {
		super("status", Utility.STATUSUI_SKIN);
		
		WidgetGroup group1 = new WidgetGroup();
		WidgetGroup group2 = new WidgetGroup();
		WidgetGroup group3 = new WidgetGroup();
		
		hpBar = new Image(Utility.STATUSUI_TEXTUREATLAS.findRegion("HP_Bar"));
		Image barHP = new Image(Utility.STATUSUI_TEXTUREATLAS.findRegion("Bar"));
		mpBar = new Image(Utility.STATUSUI_TEXTUREATLAS.findRegion("MP_Bar"));
		Image barMP = new Image(Utility.STATUSUI_TEXTUREATLAS.findRegion("Bar"));
		xpBar = new Image(Utility.STATUSUI_TEXTUREATLAS.findRegion("XP_Bar"));
		Image barXP = new Image(Utility.STATUSUI_TEXTUREATLAS.findRegion("Bar"));
		
		Label hpLabel = new Label(" hp:", Utility.STATUSUI_SKIN);
		Label hp = new Label(String.valueOf(hpVal), Utility.STATUSUI_SKIN);
		Label mpLabel = new Label(" mp:", Utility.STATUSUI_SKIN);
		Label mp = new Label(String.valueOf(mpVal), Utility.STATUSUI_SKIN);
		Label xpLabel = new Label(" xp:", Utility.STATUSUI_SKIN);
		Label xp = new Label(String.valueOf(xpVal), Utility.STATUSUI_SKIN);
		Label levelLabel = new Label(" lv:", Utility.STATUSUI_SKIN);
		Label level = new Label(String.valueOf(levelVal), Utility.STATUSUI_SKIN);
		Label gpLabel = new Label(" gp:", Utility.STATUSUI_SKIN);
		Label gp = new Label(String.valueOf(goldVal), Utility.STATUSUI_SKIN);
		
		inventoryButton = new ImageButton(Utility.STATUSUI_SKIN, "inventory-button");
		inventoryButton.getImageCell().size(32, 32);
		
		hpBar.setPosition(3, 6);
		mpBar.setPosition(3, 6);
		xpBar.setPosition(3, 6);
		
		group1.addActor(barHP);
		group1.addActor(hpBar);
		group2.addActor(barMP);
		group2.addActor(mpBar);
		group3.addActor(barXP);
		group3.addActor(xpBar);
		
		defaults().expand().fill();
		
		this.pad(this.getPadTop() + 10, 10, 10, 10);
		this.add();
		this.add();
		this.add(inventoryButton).align(Align.right);
		this.row();
		
		this.add(group1).size(barHP.getWidth(), barHP.getHeight());
		this.add(hpLabel);
		this.add(hp).align(Align.left);
		this.row();
		
		this.add(group2).size(barMP.getWidth(), barMP.getHeight());
		this.add(mpLabel);
		this.add(mp).align(Align.left);
		this.row();
		
		this.add(group3).size(barXP.getWidth(), barXP.getHeight());
		this.add(xpLabel);
		this.add(xp).align(Align.left);
		this.row();
		
		this.pack();
		
	}
	
	public ImageButton getInventoryButton() {
		return inventoryButton;
	}
	

}
