package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Align;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;


public class GameScreen implements Screen {
    private Game g;
    private Stage stage;
    public GameController gameController;
    TextButton button1;
    TextButton button2;
    TextButton button3;
    TextButton button4;

    String mText;
    HashMap<TextButton, AI> AIList = new HashMap<TextButton, AI>();

    private BitmapFont font = new BitmapFont();
    private SpriteBatch batch = new SpriteBatch();
    protected int state = 2; // -1 for dead end, 1 for escape, 2 for start
    protected String[] batTexts = new String[4];
    private String mainImg = "core\\assets\\Pictures\\main.png";
    private String deadEndImg = "core\\assets\\Pictures\\deadendscreen.png";
    private String escapeImg = "core\\assets\\Pictures\\winscreen.png";

    private String bluebat = "core\\assets\\Pictures\\bluebat.png";
    private String yellowbat = "core\\assets\\Pictures\\yellowbat.png";
    private String pinkbat = "core\\assets\\Pictures\\pinkbat.png";
    private String greenbat = "core\\assets\\Pictures\\greenbat.png";

    private String helloText = "These four bats seem like they want to help guide me out of the forest, but they are all pointing in different directions. Which bat should I trust?";
    private String okayText = "I followed my chosen bat until we hit another crossroads. Should I continue following the same bat?";
    private String deadEndText = "I followed my chosen bat until we hit an impassable terrain. This does not look good.";
    private String escapeText = "I followed my chosen bat until we saw a piercing light through the trees. Excited, I ran forward. In the horizon I could see my city’s glittering skyline. Escape!";


    public GameScreen(Game game, GameController gameController){
        this.g = game;
        stage = new Stage();
        Gdx.input.setInputProcessor(stage);
        this.gameController = gameController;
        font.setColor(Color.WHITE);

    }
    @Override
    public void show() {
        Texture backg;
        if (state == 0 || state == 2) {
            backg = new Texture(Gdx.files.internal(mainImg));
        } else if (state == 1) {
            backg = new Texture(Gdx.files.internal(escapeImg));
        } else {
            backg = new Texture(Gdx.files.internal(deadEndImg));
        }
        Image background = new Image(backg);
        background.setSize(stage.getWidth(), stage.getHeight() * 0.8f);
        background.setPosition(Gdx.graphics.getWidth()/35, Gdx.graphics.getHeight()/4.5f);
        //background.setAlign(Align.top);
        System.out.println(background.getX());
        System.out.println(background.getY());
        stage.addActor(background);


        Table table = new Table();
        table.setDebug(true);
        table.setWidth(stage.getWidth());
        table.setHeight(stage.getHeight()*0.2f);

        Skin skin = new Skin(Gdx.files.internal("core\\assets\\skin\\plain-james\\skin\\plain-james-ui.json"));


        Drawable blueDrawable = new TextureRegionDrawable(new Texture(Gdx.files.internal(bluebat)));
        Button.ButtonStyle blueButtonStyle = new Button.ButtonStyle(blueDrawable, blueDrawable, blueDrawable);
        Button button1 = new Button(blueButtonStyle);

        Drawable yellowDrawable = new TextureRegionDrawable(new Texture(Gdx.files.internal(yellowbat)));
        Button.ButtonStyle yellowButtonStyle = new Button.ButtonStyle(yellowDrawable, yellowDrawable, yellowDrawable);
        Button button2 = new Button(yellowButtonStyle);

        Drawable pinkDrawable = new TextureRegionDrawable(new Texture(Gdx.files.internal(pinkbat)));
        Button.ButtonStyle pinkButtonStyle = new Button.ButtonStyle(pinkDrawable, pinkDrawable, pinkDrawable);
        Button button3 = new Button(pinkButtonStyle);

        Drawable greenDrawable = new TextureRegionDrawable(new Texture(Gdx.files.internal(greenbat)));
        Button.ButtonStyle greenButtonStyle = new Button.ButtonStyle(greenDrawable, greenDrawable, greenDrawable);
        Button button4 = new Button(greenButtonStyle);

        button1.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event,float x, float y){
                System.out.println("Pressed");
                mText = "Button Pressed!";

                batch.begin();
                font.draw(batch, mText, 30, (int) (stage.getHeight() * 0.2) + 20, stage.getWidth() - 20, 10, true);
                batch.end();
            }
        });

        button2.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event,float x, float y){

            }
        });

        button3.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event,float x, float y){

            }
        });

        button4.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event,float x, float y){

            }
        });

        table.add(button1).width(Value.percentWidth(.25F, table)).height(Value.percentHeight(1F, table));
        table.add(button2).width(Value.percentWidth(.25F, table)).height(Value.percentHeight(1F, table));
        table.add(button3).width(Value.percentWidth(.25F, table)).height(Value.percentHeight(1F, table));
        table.add(button4).width(Value.percentWidth(.25F, table)).height(Value.percentHeight(1F, table));

//        button1.getLabel().setFontScale(0.5f, 1.0f);
//        button2.getLabel().setFontScale(0.5f, 1.0f);
//        button3.getLabel().setFontScale(0.5f, 1.0f);
//        button4.getLabel().setFontScale(0.5f, 1.0f);
        stage.addActor(table);
    }
    public void update(TextButton buttonPassed){
        AI ai = AIList.get(buttonPassed);
        gameController.update(ai.getRecommendedPath());
        for(TextButton button : AIList.keySet()){
            AI ai_t = AIList.get(button);
            double conf = ai_t.getProbability();
            String batTexts;
            if (conf <= 25) {
                batTexts = "I don't really know the way but just give me a chance!";
            } else if (conf <= 50) {
                batTexts = "I'm not certain of the way but I have a few hunches!";
            } else if (conf <= 75) {
                batTexts = "I'm fairly sure of the way!";
            } else {
                batTexts = "I definitely know where to go! Follow me!";
            }
            button.setText(batTexts);
        }
    }
    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0f, 0f, 0f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
        stage.draw();

        batch.begin();

        if (state == 2) {
            mText = helloText;
            //state = 0;
        } else if (state == 0) {
            mText = okayText;
        } else if (state == 1) {
            mText = escapeText;
        } else {
            mText = deadEndText;
        }
        font.draw(batch, mText, 10, (int) (stage.getHeight() * 0.2) + 20, stage.getWidth() - 20, 10, true);

        batch.end();
    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}