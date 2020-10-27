package world;

import nl.han.ica.oopg.objects.GameObject;
import processing.core.PGraphics;

public class TextObject extends GameObject {

    private String text;
    private int color = 0;
    public TextObject(String text) {
        this.text = text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public void update() {

    }

    @Override
    public void draw(PGraphics g) {
        g.textAlign(g.LEFT, g.TOP);
        g.textSize(40);
        g.text(text, getX(), getY());
    }

    public void setColor(int color){
        this.color = color;
    }
}
