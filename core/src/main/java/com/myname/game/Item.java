package com.myname.game;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Item {

    public int getID() {
        return ID;
    }

    public String getName() {
        return name;
    }

    public TextureRegion getIcon() {
        return icon;
    }

    private int ID;
    private String name;
    private TextureRegion icon;

    public Item(TextureRegion icon, int ID, String name) {
        this.icon = icon;
        this.ID = ID;
        this.name = name;
    }

}
