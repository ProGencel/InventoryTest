package com.myname.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class Main extends Game {

    private AssetManager manager;

    @Override
    public void create() {
        manager = new AssetManager();

        manager.load("book.png", Texture.class);
        manager.load("orb.png", Texture.class);
        manager.load("cloth.png", Texture.class);
        manager.load("slot.png", Texture.class);
        manager.finishLoading();

        setScreen(new InventoryScreen(manager));
    }

    @Override
    public void dispose()
    {
        manager.dispose();
    }
}
