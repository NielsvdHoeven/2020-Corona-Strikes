package world;

import nl.han.ica.oopg.objects.Sprite;
import nl.han.ica.oopg.objects.SpriteObject;

public class Boss extends SpriteObject {
    private int health;


    public Boss(Sprite sprite, int health) {
        super(sprite);
        this.health = health;
    }

    @Override
    public void update() {

    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }
}
