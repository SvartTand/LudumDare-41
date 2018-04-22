package com.svarttand.ld41.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.svarttand.ld41.Application;
import com.svarttand.ld41.states.MenuState;
import com.svarttand.ld41.ui.PlayUI.State;

public class MenuUI {
	
	private OrthographicCamera camera;
	private Viewport viewport;
	
	private Stage stage;
	
	private TextButton.TextButtonStyle style;
	private Skin skin;
	private BitmapFont font;
	
	private Button playButton;
	
	public MenuUI(final MenuState state, TextureAtlas atlas) {
		camera = new OrthographicCamera();
		viewport = new StretchViewport(Application.V_WIDTH, Application.V_HEIGHT, camera);
		stage = new Stage(viewport);
		
		
		font = new BitmapFont();
	    skin = new Skin(atlas);
	    style = new TextButton.TextButtonStyle();
	    style.font = font;
	    style.up = skin.getDrawable("Button");
	    style.down = skin.getDrawable("ButtonPressed");
	    
	    playButton = new TextButton("Play", style);
	    playButton.setPosition(Application.V_WIDTH*0.5f-playButton.getWidth()*0.5f, Application.V_HEIGHT*0.5f -playButton.getHeight()*0.5f);
	    playButton.addListener( new ClickListener() {
	         @Override
	         public void clicked(InputEvent event, float x, float y) {
	        	 System.out.println("Play pressed");
	        	 state.addPlayState();
	            }
	        });
	    stage.addActor(playButton);
	    
	    Gdx.input.setInputProcessor(stage);
	}
	
	public void dispos(){
		stage.dispose();
		font.dispose();
	}
	
	public Stage getStage(){
		return stage;
	}

	public void init() {
		Gdx.input.setInputProcessor(stage);
		
	}

}
