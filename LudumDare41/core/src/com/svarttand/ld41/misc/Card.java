package com.svarttand.ld41.misc;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.svarttand.ld41.sprites.CardType;

public class Card {
	
	private static final int DURATION = 10;
	private Label text;
	private int time;
	private float x;
	private float y;
	private boolean b;
	
	public Card(float posX,float posY) {
		x = posX;
		y = posY;
		b = false;
		time = 0;
		
		
	}
	
	public void init(CardType type, LabelStyle style){
		text = new Label(type.getExplanation(), style);
		text.setPosition(x, y);
		b = true;
		time = 0;
	}
	
	public void update(float delta){
		time+=delta;
	}
	
	public void render(SpriteBatch batch, TextureAtlas atlas){
		if (time < DURATION && b) {
			batch.draw(atlas.findRegion("Card"), x, y);
			text.draw(batch, 1);
		}
	}
	
	

}
