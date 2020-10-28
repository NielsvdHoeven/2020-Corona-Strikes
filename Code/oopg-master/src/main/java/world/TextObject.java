package world;

import nl.han.ica.oopg.objects.GameObject;
import processing.core.PGraphics;

public class TextObject extends GameObject {

    private String text;
    private int x, y;

    public TextObject(String text, int x, int y) {
        this.text = text;
        this.x = x;
        this.y = y;
    }

    /**
     * Zet de text van het TextObject
     */
    public void setText(String text) {
        this.text = text;

    }


    @Override
    public void update() {

    }

    @Override
    public void draw(PGraphics g) {
        g.textAlign(g.CENTER, g.TOP);
        g.textSize(40);
        g.text(text, x, y);
    }
}
