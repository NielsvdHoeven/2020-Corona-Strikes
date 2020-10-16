package world;

import nl.han.ica.oopg.objects.Sprite;
import nl.han.ica.oopg.objects.SpriteObject;

public class Platform extends SpriteObject {

    WorldApp world;

    public Platform(WorldApp world) {
        super(new Sprite(world.MEDIA_URL.concat("platformPack_tile040.png")));
        this.world = world;
        this.x = 300;
        this.y = 500;
    }

    @Override
    public void update() {
        if (x <= 300) {
            setxSpeed(2);
        } else if (x >= 600) {
            setxSpeed(-2);
        }
    }
}
