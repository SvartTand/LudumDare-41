package com.svarttand.ld41.ui;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.svarttand.ld41.Application;
import com.svarttand.ld41.misc.AudioHandler;
import com.svarttand.ld41.states.PlayState;
import com.svarttand.ld41.world.TowerType;

public class PlayUI {
	
	public enum State {NONE, TOWER1, TOWER2, HOUSE, UPGRADE};
	public State currentState;
	
	private static final int MAX_HP = 50;
	private static final float RECT_WIDTH = 300;
	private static final float RECT_HEIGHT = 30;
	
	private OrthographicCamera camera;
	private Viewport viewport;
	
	private Stage stage;
	
	private TextButton.TextButtonStyle style;
	private Skin skin;
	private BitmapFont font;
	
	private Button buildButton;
	private Button buildButton2;
	private Button buildHouseButton;
	private Button upgradeButton;
	
	private ArrayList<Button> buttonList;
	
	private Label resources;
	
	private Label Tower1Cost;
	private Label Tower2Cost;
	private Label HouseCost;
	private Label UpgradeCost;
	private LabelStyle labelStyle;
	private Label score;
	private Label hpText;
	private Label nextWave;
	
	private PlayState state;
	
	private int hp;
	
	
	
	ArrayList<FloatingText> floatingTexts;
	
	
	public PlayUI(TextureAtlas atlas, final PlayState state){
		camera = new OrthographicCamera();
		viewport = new StretchViewport(Application.V_WIDTH, Application.V_HEIGHT, camera);
		stage = new Stage(viewport);
		this.state = state;
		buttonList = new ArrayList<Button>();
		floatingTexts = new ArrayList<FloatingText>();
		
		currentState = State.NONE;
		font = new BitmapFont();
	    skin = new Skin(atlas);
	    style = new TextButton.TextButtonStyle();
	    style.font = font;
	    style.up = skin.getDrawable("Button");
	    style.down = skin.getDrawable("ButtonPressed");
	    style.checked = skin.getDrawable("ButtonChecked");
	    style.disabled = skin.getDrawable("Button");
	    
	    hp = 50;
	    
	    generateLabels();
	    
	    buildButton = new TextButton("Build Tower", style);
	    buildButton.setPosition(Application.V_WIDTH*0.01f, Application.V_HEIGHT*0.01f);
	    buildButton.addListener( new ClickListener() {
	         @Override
	         public void clicked(InputEvent event, float x, float y) {
	        	 System.out.println("build pressed");
	        	 resetButtons(buildButton);
	        		 currentState = State.TOWER1;
	        		 noChecked();
	        		 state.getAudioHandler().playSound(AudioHandler.SELECT);
	        	 
	            }
	        });
	    stage.addActor(buildButton);
	    
	    
	    buildButton2 = new TextButton("Build Tower2", style);
	    buildButton2.setPosition(Application.V_WIDTH*0.14f, Application.V_HEIGHT*0.01f);
	    buildButton2.addListener( new ClickListener() {
	         @Override
	         public void clicked(InputEvent event, float x, float y) {
	        	 System.out.println("Buld Tower2");
	        	 	resetButtons(buildButton2);
	        		 currentState = State.TOWER2;
	        		 noChecked();
	        		 state.getAudioHandler().playSound(AudioHandler.SELECT);
	        	 
	            }
	        });
	    stage.addActor(buildButton2);
	    
	    buildHouseButton = new TextButton("Build House", style);
	    buildHouseButton.setPosition(Application.V_WIDTH*0.27f, Application.V_HEIGHT*0.01f);
	    buildHouseButton.addListener( new ClickListener() {
	         @Override
	         public void clicked(InputEvent event, float x, float y) {
	        	 System.out.println("Build House Pressed");
	        		 resetButtons(buildHouseButton);
	        		 currentState = State.HOUSE;
	        		 noChecked();
	        		 state.getAudioHandler().playSound(AudioHandler.SELECT);
	        		 
	        	 
	            }
	        });
	    stage.addActor(buildHouseButton);
	    
	    upgradeButton = new TextButton("Upgrade", style);
	    upgradeButton.setPosition(Application.V_WIDTH*0.4f, Application.V_HEIGHT*0.01f);
	    upgradeButton.addListener( new ClickListener() {
	         @Override
	         public void clicked(InputEvent event, float x, float y) {
	        	 System.out.println("Upgrade Pressed");
	        	 resetButtons(upgradeButton);
	        	 currentState = State.UPGRADE;
	        	 noChecked();
	        	 state.getAudioHandler().playSound(AudioHandler.SELECT);
	        	 
	            }
	        });
	    stage.addActor(upgradeButton);
	    
	    
	    buttonList.add(buildButton);
	    buttonList.add(buildButton2);
	    buttonList.add(buildHouseButton);
	    buttonList.add(upgradeButton);
	    
	}
	
	private void generateLabels(){
		labelStyle = new LabelStyle(font, Color.WHITE);
		Tower1Cost = new Label("Gold: " + (int)TowerType.BASIC.getCost() , labelStyle);
		Tower1Cost.setPosition(Application.V_WIDTH *0.01f, Application.V_HEIGHT*0.09f);
		
		Tower2Cost = new Label("Gold: " +(int) TowerType.BASIC2.getCost() , labelStyle);
		Tower2Cost.setPosition(Application.V_WIDTH *0.14f, Application.V_HEIGHT*0.09f);
		
		HouseCost = new Label("Gold: " +(int) TowerType.HOUSE.getCost() , labelStyle);
		HouseCost.setPosition(Application.V_WIDTH *0.27f, Application.V_HEIGHT*0.09f);
		
		UpgradeCost = new Label("Gold: Cost * Level" , labelStyle);
		UpgradeCost.setPosition(Application.V_WIDTH *0.4f, Application.V_HEIGHT*0.09f);
		
		resources = new Label("Gold: " + (int) state.getResources().getGold() + ", Population: " + (int)state.getResources().getPopulation() + "/" + (int) state.getResources().getHousing(), labelStyle);
		resources.setPosition(Application.V_WIDTH*0.5f - resources.getWidth()*0.5f, Application.V_HEIGHT*0.975f);
		
		score = new Label("Score: " + (int)state.getResources().getScore(), labelStyle);
		score.setPosition(Application.V_WIDTH * 0.01f, Application.V_HEIGHT*0.975f);
		
		nextWave = new Label("Next Wave in: " + 60, labelStyle);
		nextWave.setPosition((Application.V_WIDTH*0.99f - nextWave.getWidth()), Application.V_HEIGHT*0.975f);
		
		hpText = new Label("" + hp + "/" + MAX_HP, labelStyle);
		hpText.setPosition((Application.V_WIDTH*0.78f - hpText.getWidth()), Application.V_HEIGHT*0.045f);
		
		stage.addActor(Tower1Cost);
		stage.addActor(Tower2Cost);
		stage.addActor(HouseCost);
		stage.addActor(UpgradeCost);
		stage.addActor(resources);
		stage.addActor(score);
		stage.addActor(nextWave);
		stage.addActor(hpText);
	}
	public void updateTimer(float time){
		nextWave.setText("Next Wave in: " +(int) time);
	}
	
	public void updateResources(){
		resources.setText("Gold: " +(int) state.getResources().getGold() + ", Population: " + (int) state.getResources().getPopulation() + "/" + (int)state.getResources().getHousing());
		resources.setPosition(Application.V_WIDTH*0.5f - resources.getWidth()*0.5f, Application.V_HEIGHT*0.975f);
		score.setText("Score: " + (int) state.getResources().getScore());
	}
	
	public void updateFloatinTexts(float delta){
		for (int i = 0; i < floatingTexts.size(); i++) {
			if (floatingTexts.get(i).update(delta)) {
				floatingTexts.get(i).getLabel().setText("");
				floatingTexts.remove(i);
				
			}
		}
	}
	
	public void addNewFloatingText(String text,float x, float y, float duration, boolean gravity, int size){
		floatingTexts.add(new FloatingText(text, x, y, duration, labelStyle, gravity, size));
		
	}
	
	private void resetButtons(Button button){
		for (int i = 0; i < buttonList.size(); i++) {
			if (buttonList.get(i) != button) {
				buttonList.get(i).setChecked(false);
			}
			
		}
	}
	
	public void render(SpriteBatch batch){
		for (int i = 0; i < floatingTexts.size(); i++) {
			floatingTexts.get(i).getLabel().draw(batch, 1);
		}
	}
	
	private void noChecked(){
		for (int i = 0; i < buttonList.size(); i++) {
			if (buttonList.get(i).isChecked()) {
				return;
			}
		}
		currentState = State.NONE;
		
	}
	
	public void render(ShapeRenderer renderer){
		renderer.setProjectionMatrix(camera.combined);
		renderer.begin(ShapeType.Filled);
		renderer.setColor(Color.BLACK);
		renderer.rect(Application.V_WIDTH*0.6f - MAX_HP*0.5f, Application.V_HEIGHT*0.04f, RECT_WIDTH, RECT_HEIGHT);
		renderer.setColor(Color.FIREBRICK);
		renderer.rect(Application.V_WIDTH*0.6f - MAX_HP*0.5f, Application.V_HEIGHT*0.04f, (float)hp/MAX_HP * RECT_WIDTH, RECT_HEIGHT);
		renderer.end();
		
	}
	
	public void takeHP(float dmg){
		hp -= dmg;
		hpText.setText("" + hp + "/" + MAX_HP);
		if (hp <= 0) {
			state.gameLost();
		}
	}
	
	public void dispose(){
		stage.dispose();
		font.dispose();
	}

	public Stage getStage() {
		return stage;
		
	}
}
