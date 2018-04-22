package com.svarttand.ld41.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.svarttand.ld41.Application;
import com.svarttand.ld41.input.GameController;
import com.svarttand.ld41.misc.AudioHandler;
import com.svarttand.ld41.misc.Card;
import com.svarttand.ld41.misc.ParticleHandler;
import com.svarttand.ld41.misc.Resources;
import com.svarttand.ld41.misc.ScreenShake;
import com.svarttand.ld41.sprites.CardType;
import com.svarttand.ld41.sprites.MobHandler;
import com.svarttand.ld41.ui.PlayUI;
import com.svarttand.ld41.world.TileMap;
import com.svarttand.ld41.world.TowerHandler;

public class PlayState extends State{
	
	private TextureAtlas textureAtlas;
	private Viewport viewport;
	private TileMap map;
	private GameController controller;
	private InputMultiplexer multiplexer;
	
	private ParticleHandler particleHandler;
	
	private MobHandler mobHandler;
	private TowerHandler towerHandler;

	private Resources resources;
	
	private ShapeRenderer sRenderer;
	
	private AudioHandler audioHandler;
	
	private ScreenShake screenShake;
	
	private float baseX;
	private float baseY;
	
	private PlayUI ui;
	public PlayState(GameStateManager gsm, TextureAtlas atlas) {
		super(gsm);
		viewport = new StretchViewport(Application.V_WIDTH, Application.V_HEIGHT, cam);
		viewport.update(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		map = new TileMap();
		textureAtlas = atlas;
		resources = new Resources(this);
		ui = new PlayUI(textureAtlas, this);
		particleHandler = new ParticleHandler(atlas);
		audioHandler = new AudioHandler(gsm.assetManager);
		
		mobHandler = new MobHandler(this);
		towerHandler = new TowerHandler(this);
		controller = new GameController(this);
		multiplexer = new InputMultiplexer();
		multiplexer.addProcessor(controller);

		multiplexer.addProcessor(ui.getStage());
		Gdx.input.setInputProcessor(multiplexer);
		screenShake = new ScreenShake();
		
		sRenderer = new ShapeRenderer();
		
		baseX = Application.V_WIDTH*0.5f;
		baseY = Application.V_HEIGHT*0.5f;
		//card = new Card(Application.V_WIDTH*0.5f, Application.V_HEIGHT*0.5f);
		
	}

	@Override
	protected void handleInput(float delta) {
		
	}

	@Override
	public void update(float delta) {
		mobHandler.update(delta);
		towerHandler.update(delta);
		ui.updateTimer(mobHandler.getTimeToNextWave());
		ui.updateFloatinTexts(delta);
		cam.position.x = baseX;
		cam.position.y = baseY;
		//screenShake.update(delta, cam);
		//card.update(delta);
	}

	@Override
	public void render(SpriteBatch batch, float delta) {
		screenShake.update(delta, cam);
		batch.setProjectionMatrix(cam.combined);
		cam.update();
		Gdx.gl.glClearColor(0, 0, 0, 0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		map.render(batch, textureAtlas);
		towerHandler.render(batch, textureAtlas);
		towerHandler.renderLabels(batch);
		mobHandler.render(batch, textureAtlas);
		batch.draw(textureAtlas.findRegion("Panel"), 0, 0);
		batch.draw(textureAtlas.findRegion("PanelTopRight"), Application.V_WIDTH - textureAtlas.findRegion("PanelTopRight").getRegionWidth(), Application.V_HEIGHT- textureAtlas.findRegion("PanelTopRight").getRegionHeight());
		batch.draw(textureAtlas.findRegion("PanelTopLeft"), 0, Application.V_HEIGHT- textureAtlas.findRegion("PanelTopLeft").getRegionHeight());
		batch.draw(textureAtlas.findRegion("PanelTopCenter"), Application.V_WIDTH*0.5f - textureAtlas.findRegion("PanelTopCenter").getRegionWidth()*0.5f, Application.V_HEIGHT- textureAtlas.findRegion("PanelTopCenter").getRegionHeight());
		particleHandler.render(batch, delta);
		ui.render(batch);
		//card.render(batch, textureAtlas);
		batch.end();
		ui.render(sRenderer);
		ui.getStage().draw();
		
		
	}

	@Override
	public void dispose() {
		ui.getStage().dispose();
		controller.dispose();
		audioHandler.dispose();
		
	}

	@Override
	public void resize(int width, int height) {
		viewport.update(width, height);
		
	}

	public AudioHandler getAudioHandler(){
		return audioHandler;
	}
	
	public ScreenShake getShake(){
		return screenShake;
	}
	
	public TileMap getMap() {
		return map;
	}
	
	public PlayUI getUI(){
		return ui;
	}
	
	public TowerHandler getTowers(){
		return towerHandler;
	}

	public MobHandler getMobs() {
		return mobHandler;
	}
	
	public Resources getResources(){
		return resources;
	}
	
	public ParticleHandler getParticleHandler(){
		return particleHandler;
	}
	
	public void updateCard(CardType type){
//		card.init(type, style);
//		System.out.println("set");
	}

	public void gameLost() {
		gsm.pop();
		
	}

}
