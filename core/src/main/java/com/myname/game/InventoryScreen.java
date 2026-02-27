package com.myname.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import jdk.vm.ci.meta.Constant;

public class InventoryScreen implements Screen {

    private AssetManager manager;

    private Array<Item> items;

    private Item item0;
    private Item item1;
    private Item item2;

    private Stage stage;

    private Table mainTable;
    private Table slotTable;

    private Viewport viewport;

    private Slot[] slots;

    public InventoryScreen(AssetManager manager)
    {
        viewport = new ExtendViewport(640,360);
        this.manager = manager;
        items = new Array<>();
        stage = new Stage(viewport);
        Gdx.input.setInputProcessor(stage);
        mainTable = new Table();
        mainTable.setFillParent(true);

        slotTable = new Table();
        item0 = new Item(new TextureRegion((Texture) manager.get("book.png")),0,"Book");

        slots = new Slot[5];
        slots[0] = new Slot(new TextureRegionDrawable((Texture) manager.get("slot.png")));
        slots[1] = new Slot(new TextureRegionDrawable(manager.get("slot.png",Texture.class)));
        slots[2] = new Slot(new TextureRegionDrawable(manager.get("slot.png",Texture.class)));
        slots[3] = new Slot(new TextureRegionDrawable(manager.get("slot.png",Texture.class)));
        slots[4] = new Slot(new TextureRegionDrawable(manager.get("slot.png",Texture.class)));

        slotTable.add(slots[0]);
        slotTable.add(slots[1]);
        slotTable.add(slots[2]);
        slotTable.add(slots[3]);
        slotTable.add(slots[4]);


        mainTable.add(slotTable);
        //stage.setDebugAll(true);
        stage.addActor(mainTable);

    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {

        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        if(Gdx.input.isKeyJustPressed(Input.Keys.C))
        {
            slots[2].setItem(item0);
        }
        else if(Gdx.input.isKeyJustPressed(Input.Keys.S))
        {
            slots[2].setItem(null);
        }

        stage.act();
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
        stage.dispose();
    }
}
