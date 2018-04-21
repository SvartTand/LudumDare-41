package com.svarttand.ld41.ui;

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
import com.svarttand.ld41.states.PlayState;

public class PlayUI {
	
	public enum State {NONE, BUILD, A, B};
	public State currentState;
	
	private OrthographicCamera camera;
	private Viewport viewport;
	
	private Stage stage;
	
	private TextButton.TextButtonStyle style;
	private Skin skin;
	private BitmapFont font;
	
	private Button buildButton;
	private Button buildButton2;
	
	public PlayUI(TextureAtlas atlas, final PlayState game){
		camera = new OrthographicCamera();
		viewport = new StretchViewport(Application.V_WIDTH, Application.V_HEIGHT, camera);
		stage = new Stage(viewport);
		
		currentState = State.NONE;
		font = new BitmapFont();
	    skin = new Skin(atlas);
	    style = new TextButton.TextButtonStyle();
	    style.font = font;
	    style.up = skin.getDrawable("Button");
	    style.down = skin.getDrawable("ButtonPressed");
	    style.checked = skin.getDrawable("ButtonChecked");
	    style.disabled = skin.getDrawable("Button");
	    
	    
	    buildButton = new TextButton("Build", style);
	    buildButton.setPosition(Application.V_WIDTH*0.01f, Application.V_HEIGHT*0.01f);
	    buildButton.addListener( new ClickListener() {
	         @Override
	         public void clicked(InputEvent event, float x, float y) {
	        	 System.out.println("build pressed");
	        		 currentState = State.BUILD;
	        		 buildButton2.setChecked(false);
	        		 noChecked();
	        	 
	            }
	        });
	    stage.addActor(buildButton);
	    
	    buildButton2 = new TextButton("Build2", style);
	    buildButton2.setPosition(Application.V_WIDTH*0.15f, Application.V_HEIGHT*0.01f);
	    buildButton2.addListener( new ClickListener() {
	         @Override
	         public void clicked(InputEvent event, float x, float y) {
	        	 System.out.println("build2 pressed");
	        		 currentState = State.A;
	        		 buildButton.setChecked(false);
	        		 noChecked();
	        	 
	            }
	        });
	    stage.addActor(buildButton2);
	}
	
	private void noChecked(){
		if (!buildButton.isChecked() || !buildButton2.isChecked()) {
			currentState = State.NONE;
		}
		
	}
	
	public void dispose(){
		stage.dispose();
	}

	public Stage getStage() {
		return stage;
		
	}
}
