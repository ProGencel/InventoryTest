package com.myname.game;

import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Stack;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

public class Slot extends Button {

    private Item item = null;
    private TextureRegionDrawable texture;
    private Stack stack;
    private Image itemImage;

    public Slot(TextureRegionDrawable texture)
    {
        super(texture);
        stack = new Stack();
        itemImage = new Image();
        stack.add(itemImage);
        this.texture = texture;

        this.add(stack).fill().expand();
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
        if(item == null)
        {
            itemImage.setDrawable(null);
        }
        else
        {
            System.out.println("asd");
            itemImage.setDrawable(new TextureRegionDrawable(item.getIcon()));
        }
    }
}
