package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Align;


public class GameScreen implements Screen {
    private Game g;
    private Stage stage;
    public GameScreen(Game game){
        this.g = game;
        stage = new Stage();
        Gdx.input.setInputProcessor(stage);

    }
    @Override
    public void show() {

        Texture backg = new Texture(Gdx.files.internal("core\\assets\\Pictures\\BackgScreen.png"));
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

        Skin skin = new Skin(Gdx.files.internal("core\\assets\\skin\\plain-james\\plain-james-ui.json"));
        TextButton button1 = new TextButton("Option1", skin);
        TextButton button2 = new TextButton("Option2", skin);
        TextButton button3 = new TextButton("Option3", skin);
        TextButton button4 = new TextButton("Option4", skin);

        button1.addListener(new ClickListener(){
           @Override
           public void clicked(InputEvent event,float x, float y){
                System.out.println("hhey");
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

        table.add(button1).width(Value.percentWidth(.25F, table));
        table.add(button2).width(Value.percentWidth(.25F, table));
        table.add(button3).width(Value.percentWidth(.25F, table));
        table.add(button4).width(Value.percentWidth(.25F, table));

//        button1.getLabel().setFontScale(0.5f, 1.0f);
//        button2.getLabel().setFontScale(0.5f, 1.0f);
//        button3.getLabel().setFontScale(0.5f, 1.0f);
//        button4.getLabel().setFontScale(0.5f, 1.0f);
        stage.addActor(table);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0f, 0f, 0f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
        stage.draw();

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