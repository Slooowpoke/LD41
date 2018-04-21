package com.coxon.rhythm.graphics;

import com.badlogic.gdx.graphics.Texture;

import java.util.HashMap;

public class ImageLoader {

    public HashMap<String, Texture> loadImages(){
        HashMap<String, Texture> images = new HashMap<String, Texture>();

        images.put("Player", new Texture("images/player/player.png"));
        images.put("Enemy", new Texture("images/enemy/enemy.png"));
        images.put("UpNote", new Texture("images/notes/UpNote.png"));
        images.put("DownNote", new Texture("images/notes/DownNote.png"));
        images.put("RightNote", new Texture("images/notes/RightNote.png"));
        images.put("LeftNote", new Texture("images/notes/LeftNote.png"));
        return images;
    }

}
