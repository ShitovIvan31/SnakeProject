package main;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public enum Sprite {
    BODY("snake"), CHERRIES("cherries");

    private Texture texture;

    private Sprite(String texturename){
        try {
            this.texture = TextureLoader.getTexture("PNG", new FileInputStream(new File("res/"+texturename+".png")));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public Texture getTexture(){
        return this.texture;
    }
}