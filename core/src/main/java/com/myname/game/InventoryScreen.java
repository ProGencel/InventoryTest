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
import com.badlogic.gdx.scenes.scene2d.Touchable;
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
        dragAndDrop.setTapSquareSize(2f);
        dragAndDrop.setDragTime(0);

        setDragAndDrop();

    }

    @Override
    public void show() {

    }

    private void setDragAndDrop()
    {

        for(Slot slot : slots)
        {
            dragAndDrop.addSource(new DragAndDrop.Source(slot) {
                @Override
                public DragAndDrop.Payload dragStart(InputEvent event, float x, float y, int pointer) {

                    /*
                    This method is called when dragging starts.
                     */

                    DragAndDrop.Payload payload = new DragAndDrop.Payload();

                    if(slot.getItem() == null)
                    {
                        return null;
                    }
                    else
                    {
                        payload.setObject(slot.getItem());

                        Image image = new Image(slot.getItem().getIcon());
                        payload.setDragActor(image);    //Sets the icon at the cursor while dragging.

                        dragAndDrop.setDragActorPosition(image.getWidth()/2, -image.getHeight()/2);
                        image.setTouchable(Touchable.disabled);

                        slot.setItem(null);
                        return payload;
                    }
                }

                @Override
                public void dragStop(InputEvent event, float x, float y, int pointer, DragAndDrop.Payload payload, DragAndDrop.Target target) {
                    /*
                    This method is called when an item drops outside a slot.
                     */

                    if(target == null)
                    {
                        slot.setItem((Item)payload.getObject());
                    }
                }
            });

            dragAndDrop.addTarget(new DragAndDrop.Target(slot) {
                @Override
                public boolean drag(DragAndDrop.Source source, DragAndDrop.Payload payload, float x, float y, int pointer) {

                    /*
                    Triggered when an item enters this slot; if it returns true, the drop method is
                    executed and the item is placed.
                     */

                    return true;
                }

                @Override
                public void drop(DragAndDrop.Source source, DragAndDrop.Payload payload, float x, float y, int pointer) {
                    /*
                    Triggers when an item is dropped in this slot.
                     */

                    if(payload.getObject() != null)
                    {
                        slot.setItem((Item)payload.getObject());
                    }

                }
            });
        }
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
