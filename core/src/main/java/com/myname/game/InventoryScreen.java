package com.myname.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop;
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

    private DragAndDrop dragAndDrop;

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
            slots[0].setItem(item0);

        mainTable.add(slotTable);
        //stage.setDebugAll(true);
        stage.addActor(mainTable);

        dragAndDrop = new DragAndDrop();

        setDragAndDrop();

    }

    @Override
    public void show() {

    }

    private void setDragAndDrop()
    {
        dragAndDrop.addSource(new DragAndDrop.Source(slots[0]) {
            @Override
            public DragAndDrop.Payload dragStart(InputEvent event, float x, float y, int pointer) {
                if (slots[0].getItem() == null) {
                    return null;
                }
                else
                {
                    final DragAndDrop.Payload payload = new DragAndDrop.Payload();
                    payload.setObject(slots[0].getItem());

                    Image dragImage = new Image(slots[0].getItem().getIcon());
                    payload.setDragActor(dragImage);

                    slots[0].setItem(null);

                    return payload;
                }
            }

            @Override
            public void dragStop(InputEvent event, float x, float y, int pointer, DragAndDrop.Payload payload, DragAndDrop.Target target) {

                if(target == null)
                {
                    slots[0].setItem(item0);
                }

            }
        });

        dragAndDrop.addTarget(new DragAndDrop.Target(slots[1]) {
            @Override
            public boolean drag(DragAndDrop.Source source, DragAndDrop.Payload payload, float x, float y, int pointer) {
                return true;
            }

            @Override
            public void drop(DragAndDrop.Source source, DragAndDrop.Payload payload, float x, float y, int pointer) {
                slots[1].setItem((Item) payload.getObject());
            }
        });

    }

    @Override
    public void render(float delta) {

        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

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
